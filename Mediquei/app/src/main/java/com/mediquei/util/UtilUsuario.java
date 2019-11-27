package com.mediquei.util;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.mediquei.activities.primarias.MainActivity;
import com.mediquei.datamodel.LinkServerDataModel;
import com.mediquei.datamodel.UsuarioDataModel;
import com.mediquei.helper.Criptografia64;
import com.mediquei.helper.UsuarioPreferences;
import com.mediquei.model.Usuario;

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

import static android.view.View.VISIBLE;

public class UtilUsuario {
    public static class InsertUsuarioAsyncTask extends AsyncTask<String, String, String>{

        /*Progress Bar passada para poder inicializar*/
        ProgressDialog progressDialog;

        /*Declarando as variaveis da conexao HTTP*/
        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;

        /*Declarando o Contexto*/
        Context context;

        public InsertUsuarioAsyncTask (Context context, Usuario usuario) {
            this.builder = new Uri.Builder();
            this.context = context;

            /*Criptografando os Dados*/
            usuario.setSenhaUser(Criptografia64.codificarBase64(usuario.getSenhaUser()));

            /*Montando os Parametros de Passagem
            * TODO: Primeira linha é a verificacao, para aceitar apenas requições com esse parametro
            * TODO: Demais linhas é os dados do usuario
            */
            builder.appendQueryParameter("verificar", "app_inserir_usuario");
            builder.appendQueryParameter(UsuarioDataModel.getNome(), usuario.getNomeUser());
            builder.appendQueryParameter(UsuarioDataModel.getEmail(), usuario.getEmailUser());
            builder.appendQueryParameter(UsuarioDataModel.getSenha(), usuario.getSenhaUser());
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(context);

            progressDialog.setMessage("Cadastrando, por favor espere...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                //url = new URL(UtilMediquei.URL_WEB_SERVICE + "APICadastrarUsuarioTeste.php");
                url = new URL(UtilMediquei.URL_WEB_SERVICE + LinkServerDataModel.getAPICadastrarUsuario());
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
            try{
                JSONObject obj = new JSONObject(result);
                int idUser = obj.getInt("sucesso");
                if(idUser != 0){
                    if(progressDialog != null && progressDialog.isShowing()){
                        progressDialog.dismiss();
                        UsuarioPreferences up = new UsuarioPreferences(context);
                        up.salvarIdUsuarioPreferences(idUser);
                        Intent intent = new Intent(context, MainActivity.class);
                        UtilMediquei.showToastSucess(context, "Cadastro efetuado com sucesso!");
                        context.startActivity(intent);
                    }
                } else {
                    UtilMediquei.showToastError(context, "Não foi possível cadastrar! Tente novamente mais tarde.");
                }
            } catch (Exception e){
                UtilMediquei.showToastError(context, "Não foi possível cadastrar! Tente novamente mais tarde.");
            }
        }
    }

    public static class ValidarLoginAsyncTask extends AsyncTask<String, String, String> {
        /*Progress Bar passada para poder inicializar*/
        @SuppressLint("StaticFieldLeak")
        ProgressBar progressBar;

        /*Declarando as variaveis da conexao HTTP*/
        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;

        /*Usuario*/
        Usuario usuario;

        /*Declarando o Contexto*/
        @SuppressLint("StaticFieldLeak")
        Context context;

        public ValidarLoginAsyncTask(Context context, Usuario usuario, ProgressBar progressBar){
            this.builder = new Uri.Builder();
            this.context = context;
            this.progressBar = progressBar;
            this.usuario = usuario;
            this.usuario.setSenhaUser(Criptografia64.codificarBase64(this.usuario.getSenhaUser()));

            builder.appendQueryParameter("email", usuario.getEmailUser());
            builder.appendQueryParameter("password", usuario.getSenhaUser());
        }

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(VISIBLE);

        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                url = new URL(UtilMediquei.URL_WEB_SERVICE + LinkServerDataModel.getAPIBuscarDadosLogin());
                Log.d("WebService", "onPostExecute: " + UtilMediquei.URL_WEB_SERVICE + LinkServerDataModel.getAPIBuscarDadosLogin());
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
                Log.d("WebService", "onPostExecute: " + response_code);
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
            try{
                JSONObject object = new JSONObject(result);
                if(object.getBoolean("sucesso")){
                    UsuarioPreferences idUsuarioPreferences = new UsuarioPreferences(context);
                    idUsuarioPreferences.salvarIdUsuarioPreferences(object.getInt(UsuarioDataModel.getId()));
                    idUsuarioPreferences.salvarBuscasPreferences(0);
                    context.startActivity(new Intent(context, MainActivity.class));
                } else {
                    UtilMediquei.showToastError(context,
                            "Nenhum registro encontrado no momento!");
                }

            } catch (JSONException e){
                Log.d("WebService", "Erro JsonException: " + e.getMessage());
            } finally {
                if(progressBar != null && progressBar.getVisibility() == VISIBLE){
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        }
    }


}
