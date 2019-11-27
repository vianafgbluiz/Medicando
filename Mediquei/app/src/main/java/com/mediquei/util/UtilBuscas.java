package com.mediquei.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.mediquei.activities.introducao.IntrodutorioActivity;
import com.mediquei.activities.primarias.MainActivity;
import com.mediquei.controller.BuscasController;
import com.mediquei.datamodel.BuscasDataModel;
import com.mediquei.datamodel.LinkServerDataModel;
import com.mediquei.helper.UsuarioPreferences;
import com.mediquei.model.Buscas;

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
import java.util.Objects;

import okhttp3.internal.Util;

public class UtilBuscas {
    public static class BuscarNomeMedicamentoAsyncTask extends AsyncTask<String, String, String> {
        /*Progress Bar passada para poder inicializar*/
        @SuppressLint("StaticFieldLeak")
        ProgressBar progressBar;

        /*Declarando as variaveis da conexao HTTP*/
        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;

        /*Declarando o Contexto*/
        @SuppressLint("StaticFieldLeak")
        Context context;

        public BuscarNomeMedicamentoAsyncTask(ProgressBar progressBar, Context context) {
            this.builder = new Uri.Builder();

            this.progressBar = progressBar;
            this.context = context;

            builder.appendQueryParameter("verificar", "app_buscar_medicamentos");
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                url = new URL(UtilMediquei.URL_WEB_SERVICE + LinkServerDataModel.getAPIBuscarMedicamentos());
            } catch (MalformedURLException e) {
                UtilMediquei.showToastShort(context, "Houve erro! Servidor incorreto.");
            } catch (Exception e) {
                UtilMediquei.showToastShort(context, "Houve erro! Tente novamente mais tarde.");
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
                UtilMediquei.showToastShort(context, "Houve erro! Tente novamente mais tarde.");
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
            Log.d("WebService", "onPostExecute: " + result);
            BuscasController controller = new BuscasController(context);
            try {
                JSONArray jsonArray = new JSONArray(result);
                JSONObject obj = jsonArray.getJSONObject(0);
                if(obj.getBoolean("sucesso")){
                    for (int i = 0; i < jsonArray.length(); i++) {
                        obj = jsonArray.getJSONObject(i);
                        Buscas busca = new Buscas();
                        busca.setDscBusca(obj.getString("medic_nome"));
                        busca.setRecenteBusca(0);
                        controller.salvar(busca);
                    }
                }

                if(new UsuarioPreferences(context).recuperarIdUsuarioPreferences() != 0){
                    context.startActivity(new Intent(context, MainActivity.class));
                } else {
                    context.startActivity(new Intent(context, IntrodutorioActivity.class));
                }
            } catch (JSONException e) {
                Log.d("WebService", "JSONException: " + e.getMessage());
                UtilMediquei.showToastInfo(context, "Servidor Indisponível");
            } finally {
                if(progressBar != null && progressBar.getVisibility() == View.VISIBLE){
                    progressBar.setVisibility(View.GONE);
                }
            }
        }
    }

    public static class BuscarNomeMedicamentoReloadAsyncTask extends AsyncTask<String, String, String> {
        /*Declarando as variaveis da conexao HTTP*/
        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;

        /*Declarando o Contexto*/
        @SuppressLint("StaticFieldLeak")
        Context context;

        public BuscarNomeMedicamentoReloadAsyncTask(Context context) {
            this.builder = new Uri.Builder();
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                url = new URL(UtilMediquei.URL_WEB_SERVICE + LinkServerDataModel.getAPIBuscarMedicamentos());
            } catch (MalformedURLException e) {
                UtilMediquei.showToastShort(context, "Houve erro! Servidor incorreto.");
            } catch (Exception e) {
                UtilMediquei.showToastShort(context, "Houve erro! Tente novamente mais tarde.");
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
                UtilMediquei.showToastShort(context, "Houve erro! Tente novamente mais tarde.");
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
            Log.d("WebService", "onPostExecute: " + result);
            BuscasController controller = new BuscasController(context);

            controller.deletarTabela(BuscasDataModel.getTABELA());
            controller.criarTabela(BuscasDataModel.criarTabela());

            try {
                JSONObject obj = new JSONObject(result);
                if(obj.getBoolean("sucesso")){
                    for (int i = 0; i < obj.length(); i++) {
                        Log.d("WebService", "onPostExecute: " + i);
//                        internalo = jsonArray.getJSONObject(i);
//                        Buscas busca = new Buscas();
//                        busca.setDscBusca(obj.getString("medic_nome"));
//                        busca.setRecenteBusca(0);
//                        controller.salvar(busca);
                    }
                    UtilMediquei.showToastSucess(context, "Foi atualizado com Sucesso!");
                }


            } catch (JSONException e) {
                Log.d("WebService", "JSONException: " + e.getMessage());
                UtilMediquei.showToastInfo(context, "Servidor Indisponível");
            }
        }
    }
}
