package com.mediquei.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.mediquei.datamodel.BancariosDataModel;
import com.mediquei.datamodel.LinkServerDataModel;
import com.mediquei.model.Bancarios;
import com.mediquei.model.Endereco;

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

import static android.view.View.VISIBLE;

public class UtilBancarios {
    public static class cadastrarFormaPgmtUsuario extends AsyncTask<String, String, String> {

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

        public cadastrarFormaPgmtUsuario(Context context, Bancarios bancarios, ProgressBar progressBar){
            this.builder = new Uri.Builder();
            this.context = context;
            this.progressBar = progressBar;

            /*Montando os Parametros de Passagem
             * TODO: Primeira linha é a verificacao, para aceitar apenas requições com esse parametr
             * TODO: Demais linhas é os dados do usuario
             */
            builder.appendQueryParameter("verificar", "app_inserir_forma_pgmt");
            builder.appendQueryParameter(BancariosDataModel.getIdUsuario(), String.valueOf(bancarios.getBanIdUsuario()));
            builder.appendQueryParameter(BancariosDataModel.getNomeCartao(), bancarios.getBanNomeCartao());
            builder.appendQueryParameter(BancariosDataModel.getCpf(), bancarios.getBanCPF());
            builder.appendQueryParameter(BancariosDataModel.getNumero(), bancarios.getBanNumero());
            builder.appendQueryParameter(BancariosDataModel.getCodSeguranca(), bancarios.getBanCodSeguranca());
            builder.appendQueryParameter(BancariosDataModel.getValidade(), bancarios.getBanValidade());
            builder.appendQueryParameter(BancariosDataModel.getIdBandeira(), String.valueOf(bancarios.getBanIdBandeira()));
        }

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                url = new URL(UtilMediquei.URL_WEB_SERVICE + LinkServerDataModel.getAPICadastrarFormaPgmt());
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
            Log.d("WebService", "onPostExecute: " + result);
            if(progressBar != null && progressBar.getVisibility() == VISIBLE){
                progressBar.setVisibility(View.INVISIBLE);
                UtilMediquei.showToastSucess(context, "Forma de pagamento cadastrado com Sucesso!");
            }
        }
    }
}
