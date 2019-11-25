package com.mediquei.util;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.gms.maps.model.LatLng;
import com.mediquei.R;
import com.mediquei.controller.ProdutoFinalController;
import com.mediquei.datamodel.EnderecoDataModel;
import com.mediquei.datamodel.LinkServerDataModel;
import com.mediquei.model.Endereco;

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
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class UtilProdutoFinal {
    public static class BuscarDadosCalculoTaxaAsyncTask extends AsyncTask<String, String, String> {
        /*Declarando as variaveis da conexao HTTP*/
        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;

        /*Declarando o Contexto*/
        @SuppressLint("StaticFieldLeak")
        Context context;

        /*Declarando o Button*/
        @SuppressLint("StaticFieldLeak")
        Button btnTaxa;
        int idUser, idFarm;

        private AlertDialog alerta;

        /*Construtor*/

        public BuscarDadosCalculoTaxaAsyncTask(Context context, Button btnTaxa, int idUser, int idFarm) {
            this.builder = new Uri.Builder();

            this.context = context;
            this.btnTaxa = btnTaxa;
            this.idUser = idUser;
            this.idFarm = idFarm;

            builder.appendQueryParameter("verificar", "app_validar_busca");
            builder.appendQueryParameter("idUser", String.valueOf(idUser));
            builder.appendQueryParameter("idFarm", String.valueOf(idFarm));
        }

        @Override
        protected void onPreExecute() {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = li.inflate(R.layout.layout_progress_dialog, null);

            builder.setView(v);

            alerta = builder.create();
            alerta.show();
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(alerta.getWindow().getAttributes());
            lp.width = 300;
            lp.height = 300;
            alerta.getWindow().setAttributes(lp);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                url = new URL(UtilMediquei.URL_WEB_SERVICE + LinkServerDataModel.getAPIBuscarEnderecosFarmUser());
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
            LatLng enderecoInicial = null;
            LatLng enderecoFinal = null;

            try {
                JSONArray jsonArray = new JSONArray(result);
                if (jsonArray.length() != 0) {
                    JSONObject obj = jsonArray.getJSONObject(0);
                    if (obj.getBoolean("sucesso")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            Endereco endereco = new Endereco();
                            endereco.setEndCidade(object.getString(EnderecoDataModel.getCidade()));
                            endereco.setEndLogradouro(object.getString(EnderecoDataModel.getLogradouro()));
                            endereco.setEndNumero(object.getString(EnderecoDataModel.getNumero()));

                            /*Verifica se é usuario ou nao*/
                            if (object.getString(EnderecoDataModel.getTipo()).equals("U")) {
                                enderecoFinal = retornaLatLng(endereco);
                            } else {
                                enderecoInicial = retornaLatLng(endereco);
                            }
                        }
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
                if (alerta != null && alerta.isShowing()) {
                    alerta.dismiss();
                    if (enderecoFinal != null && enderecoInicial != null) {
                        btnTaxa.setText(ProdutoFinalController.formataTaxaEntrega(enderecoInicial, enderecoFinal));
                    }
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
