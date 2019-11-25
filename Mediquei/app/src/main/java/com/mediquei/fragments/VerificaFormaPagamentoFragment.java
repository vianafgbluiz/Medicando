package com.mediquei.fragments;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mediquei.R;
import com.mediquei.adapter.AdapterFormaPgmtCompra;
import com.mediquei.datamodel.LinkServerDataModel;
import com.mediquei.helper.Formatacoes;
import com.mediquei.helper.UsuarioPreferences;
import com.mediquei.model.Bancarios;
import com.mediquei.model.Endereco;
import com.mediquei.util.UtilMediquei;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Observable;

/**
 * A simple {@link Fragment} subclass.
 */
public class VerificaFormaPagamentoFragment extends Fragment {
    private RecyclerView recyclerFormaPagamento;
    private TextView textNome, textInfoCartao;
    private ImageView image;
    private ProgressBar pb;
    private int idUser;

    private List<Bancarios> bancariosList;
    private AdapterFormaPgmtCompra adapterFormaPgmtCompra;

    /*Interface*/
    public interface InterfaceComunicacaoForma{
        void setBancarios(Bancarios bancariosEscolhidoUser);
    }

    private InterfaceComunicacaoForma listener;

    private List<Boolean> listaBool = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof InterfaceComunicacaoForma){
            listener = (InterfaceComunicacaoForma) context;
        } else {
            throw new RuntimeException("Deve implementar Interface");
        }
    }

    public VerificaFormaPagamentoFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_verifica_forma_pagamento, container, false);

        /*Recuperar as Variaveis pelo ID*/
        recyclerFormaPagamento = view.findViewById(R.id.recyclerFormaPagamentoEscolhida);
        textNome = view.findViewById(R.id.textViewFormaEscolhida);
        textInfoCartao = view.findViewById(R.id.textViewInfoAddForma);
        pb = view.findViewById(R.id.progressBarFormaPgmt);
        image = view.findViewById(R.id.backProgressBarFormaPagamento);
        idUser = new UsuarioPreferences(Objects.requireNonNull(getActivity()).getApplicationContext()).recuperarIdUsuarioPreferences();

        return view;
    }

    public AdapterFormaPgmtCompra.FormaPgmentoOnClickListener formaPgmentoOnClickListener() {
        return new AdapterFormaPgmtCompra.FormaPgmentoOnClickListener() {
            @Override
            public void formaPgmtOnClick(AdapterFormaPgmtCompra.HolderFormaCompra holder, int position) {
                    Bancarios banc = bancariosList.get(position);
                    textNome.setText(banc.getBanNomeCartao());
                    textInfoCartao.setText(Formatacoes.formataTextoCartoesBandeira(banc.getBanNumero()));
                    listener.setBancarios(banc);

            }
        };
    }


    @Override
    public void onResume() {
        super.onResume();
        BuscarFormaPagamentoAsyncTask task =
                new BuscarFormaPagamentoAsyncTask(Objects.requireNonNull(getActivity()).getApplicationContext(), idUser);
        task.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public class BuscarFormaPagamentoAsyncTask extends AsyncTask<String, String, String> {

        /*Declarando as variaveis da conexao HTTP*/
        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;

        /*Declarando o Contexto*/
        Context context;

        /*Declarando o Button*/
        int idUser;

        private AlertDialog alerta;

        /*Construtor*/
        public BuscarFormaPagamentoAsyncTask(Context context, int idUser) {
            this.builder = new Uri.Builder();

            this.context = context;
            this.idUser = idUser;

            builder.appendQueryParameter("verificar", "app_validar_busca");
            builder.appendQueryParameter("idUser", String.valueOf(idUser));
        }

        @Override
        protected void onPreExecute() {
            pb.setVisibility(View.VISIBLE);
            image.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                url = new URL(UtilMediquei.URL_WEB_SERVICE + LinkServerDataModel.getAPIBuscarDadosCompra());
            } catch (MalformedURLException e) {
                UtilMediquei.showToastError(context, "Houve erro! Servidor incorreto.");
            } catch (Exception e) {
                UtilMediquei.showToastError(context, "Houve erro! Tente novamente mais tarde.");
            }

            try {
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(UtilMediquei.READ_TIMEOUT);
                conn.setConnectTimeout(UtilMediquei.CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("charset", "utf-8");

                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.connect();

                String query = builder.build().getEncodedQuery();

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(Objects.requireNonNull(query));
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException erro) {
                UtilMediquei.showToastError(context, "Houve erro! Tente novamente mais tarde.");
            }

            try {
                int response_code = conn.getResponseCode();
                if (response_code == HttpURLConnection.HTTP_OK) {
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    return (result.toString());
                } else {
                    return ("Erro de conexão");
                }
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("Teste", "onPostExecute: " + result);
            bancariosList = new ArrayList<>();

            try {
                JSONArray jsonArray = new JSONArray(result);
                if (jsonArray.length() != 0) {
                    JSONObject obj = jsonArray.getJSONObject(0);
                    if (obj.getBoolean("sucesso")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            Bancarios banUser = new Bancarios();
                            banUser.setBanId(object.getInt("ban_id"));
                            banUser.setBanNomeCartao(object.getString("ban_nome_cartao"));
                            banUser.setBanNumero(object.getString("ban_numero"));
                            banUser.setBanCodSeguranca(object.getString("ban_codseguranca"));
                            banUser.setBanValidade(object.getString("ban_validade"));
                            banUser.setBanIdBandeira(object.getInt("ban_id_bandeira"));
                            banUser.setBanCPF(object.getString("ban_cpf"));
                            bancariosList.add(banUser);
                        }

                        adapterFormaPgmtCompra = new AdapterFormaPgmtCompra(bancariosList, formaPgmentoOnClickListener());

                        /*Configura o RecyclerView*/
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Objects.requireNonNull(getActivity()).getApplicationContext());
                        recyclerFormaPagamento.setLayoutManager(layoutManager);
                        recyclerFormaPagamento.setHasFixedSize(true);
                        recyclerFormaPagamento.setAdapter(adapterFormaPgmtCompra);
                    } else {
                        UtilMediquei.showToastInfo(context,
                                "Verifique se possui algum cartão cadastrado!");
                    }
                } else {
                    UtilMediquei.showToastInfo(context, "Verifique se possui algum cartão cadastrado!");
                }
            } catch (JSONException e) {
                UtilMediquei.showToastInfo(context, "Verifique se possui algum cartão cadastrado!");
            } finally {
                if (pb != null && pb.getVisibility() == View.VISIBLE) {
                    pb.setVisibility(View.GONE);
                    image.setVisibility(View.GONE);
                }
            }
        }

    }
}
