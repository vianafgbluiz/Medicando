package com.mediquei.fragments;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.mediquei.R;
import com.mediquei.adapter.AdapterEnderecoCompra;
import com.mediquei.controller.ProdutoFinalController;
import com.mediquei.datamodel.EnderecoDataModel;
import com.mediquei.datamodel.LinkServerDataModel;
import com.mediquei.helper.Formatacoes;
import com.mediquei.helper.UsuarioPreferences;
import com.mediquei.model.Endereco;
import com.mediquei.model.ProdutoFinal;
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
import java.util.Locale;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class VerificaEnderecoFragment extends Fragment {

    private RecyclerView recyclerEnderecos;
    private TextView textRua, textInfoAdd;
    private ImageView image;
    private ProgressBar pb;
    private AdapterEnderecoCompra adapterEnderecoCompra;
    private int idUser;
    private int idFarmacia;
    private List<Endereco> enderecoList;

    public VerificaEnderecoFragment() {

    }

    /*Interface*/
    public interface InterfaceComunicacao{
        void setEndereco(Endereco enderecoEscolhidoUser);
    }

    private InterfaceComunicacao listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof  InterfaceComunicacao){
            listener = (InterfaceComunicacao) context;
        } else {
            throw new RuntimeException("Deve implementar Interface");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_verifica_endereco, container, false);

        /*Recuperar as Variaveis pelo ID*/
        recyclerEnderecos = view.findViewById(R.id.recyclerEscolhaEnderecoEntrega);
        textRua = view.findViewById(R.id.textViewRuaEfetuarCompra);
        textInfoAdd = view.findViewById(R.id.textViewInfoAddEfetuarCompra);
        pb = view.findViewById(R.id.progressBarVerificaEndereco);
        image = view.findViewById(R.id.backProgressBarVerificaEndereco);
        idUser = new UsuarioPreferences(Objects.requireNonNull(getActivity()).getApplicationContext()).recuperarIdUsuarioPreferences();

        Bundle data = getArguments();
        if(data != null) {
            idFarmacia = data.getInt("idFarm");
        }

        return view;
    }

    private AdapterEnderecoCompra.EnderecoCompraOnClickListener enderecoCompraOnClick() {
        return new AdapterEnderecoCompra.EnderecoCompraOnClickListener() {
            @Override
            public void enderecoOnClick(AdapterEnderecoCompra.EnderecoCompraHolder holder, int position) {
                Endereco end = enderecoList.get(position);
                List<String> formatados = Formatacoes.formataTextoRua(end);
                textRua.setText(formatados.get(0));
                textInfoAdd.setText(formatados.get(1));
                listener.setEndereco(end);
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        BuscarEnderecoAsyncTask asyncTask =
                new BuscarEnderecoAsyncTask(getActivity().getApplicationContext(), idUser, idFarmacia);
        asyncTask.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public class BuscarEnderecoAsyncTask extends AsyncTask<String, String, String> {

        /*Declarando as variaveis da conexao HTTP*/
        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;

        /*Declarando o Contexto*/
        Context context;

        /*Declarando o Button*/
        Button btnTaxa;
        int idUser, idFarm;


        /*Construtor*/
        public BuscarEnderecoAsyncTask(Context context, int idUser, int idFarm) {
            this.builder = new Uri.Builder();

            this.context = context;
            this.idUser = idUser;
            this.idFarm = idFarm;

            builder.appendQueryParameter("verificar", "app_validar_busca");
            builder.appendQueryParameter("idUser", String.valueOf(idUser));
            builder.appendQueryParameter("idFarm", String.valueOf(idFarm));
        }

        @Override
        protected void onPreExecute() {
            pb.setVisibility(View.VISIBLE);
            image.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                url = new URL(UtilMediquei.URL_WEB_SERVICE + LinkServerDataModel.getAPIBuscarEnderecosFarmUserCompra());
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
            Log.d("Testando Bagulho", "onPostExecute: " + result);
            LatLng enderecoInicial = null;
            LatLng enderecoFinal = null;
            enderecoList = new ArrayList<>();

            try {
                JSONArray jsonArray = new JSONArray(result);
                if (jsonArray.length() != 0) {
                    JSONObject obj = jsonArray.getJSONObject(0);
                    if (obj.getBoolean("sucesso")) {
                        Endereco endFarmacia = new Endereco();
                        endFarmacia.setEndCidade(obj.getString(EnderecoDataModel.getCidade()));
                        endFarmacia.setEndLogradouro(obj.getString(EnderecoDataModel.getLogradouro()));
                        endFarmacia.setEndNumero(obj.getString(EnderecoDataModel.getNumero()));
                        enderecoInicial = retornaLatLng(endFarmacia);

                        for (int i = 1; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            Endereco endUser = new Endereco();
                            endUser.setEndId(object.getInt(EnderecoDataModel.getId()));
                            endUser.setEndUF(object.getString(EnderecoDataModel.getUf()));
                            endUser.setEndCidade(object.getString(EnderecoDataModel.getCidade()));
                            endUser.setEndBairro(object.getString(EnderecoDataModel.getBairro()));
                            endUser.setEndLogradouro(object.getString(EnderecoDataModel.getLogradouro()));
                            endUser.setEndNumero(object.getString(EnderecoDataModel.getNumero()));

                            enderecoFinal = retornaLatLng(endUser);

                            endUser.setTaxaEscolhida(ProdutoFinalController.retornaTaxaEntrega(enderecoInicial, enderecoFinal));
                            endUser.setTaxaCalculada("R$" + Formatacoes.formataDuasCasasDecimaisPreco
                                    (ProdutoFinalController.retornaTaxaEntrega(enderecoInicial, enderecoFinal)));

                            enderecoList.add(endUser);
                        }

                        adapterEnderecoCompra = new AdapterEnderecoCompra(enderecoList, enderecoCompraOnClick());

                        /*Configura o RecyclerView*/
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Objects.requireNonNull(getActivity()).getApplicationContext());
                        recyclerEnderecos.setLayoutManager(layoutManager);
                        recyclerEnderecos.setHasFixedSize(true);
                        recyclerEnderecos.setAdapter(adapterEnderecoCompra);
                    } else {
                        UtilMediquei.showToastError(context,
                                "Não houve sucesso, tente novamente!");
                    }
                } else {
                    UtilMediquei.showToastError(context,
                            "Nenhum registro encontrado no momento!");
                }
            } catch (JSONException e) {
                UtilMediquei.showToastError(context, "Erro JsonException: " + e.getMessage());
            } finally {
                if (pb != null && pb.getVisibility() == View.VISIBLE) {
                    pb.setVisibility(View.GONE);
                    image.setVisibility(View.GONE);
                }
            }
        }

        private LatLng retornaLatLng (Endereco endereco){
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            try {
                List<Address> addressList = geocoder.getFromLocationName(ProdutoFinalController.geraStringFinal(endereco), 1);
                if (addressList != null && addressList.size() > 0) {
                    return new LatLng(addressList.get(0).getLatitude(), addressList.get(0).getLongitude());
                }
            } catch (Exception e) {
                UtilMediquei.showToastError(context, "Não foi possível recuperar o endereço!");
            }
            return null;
        }
    }


}
