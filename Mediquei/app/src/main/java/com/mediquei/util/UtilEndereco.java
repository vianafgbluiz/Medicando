package com.mediquei.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.mediquei.R;
import com.mediquei.datamodel.EnderecoDataModel;
import com.mediquei.datamodel.LinkServerDataModel;
import com.mediquei.fragments.CadastraEnderecoFragment;
import com.mediquei.model.Endereco;

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

public class UtilEndereco {
    public static class procurarEnderecoViaCEP extends AsyncTask<String, String, String>{

        ProgressBar progressBar;

        /*Declarando as variaveis da conexao HTTP*/
        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;

        /*Declarando o Contexto*/
        @SuppressLint("StaticFieldLeak")
        AppCompatActivity context;


        /*Declarando a String*/
        String cep;

        public procurarEnderecoViaCEP(AppCompatActivity context, ProgressBar progressBar, String cep){
            this.builder = new Uri.Builder();
            this.context = context;
            this.progressBar = progressBar;
            this.cep = cep;

            UtilMediquei.setUrlWebServiceViacep(cep);
        }

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {

            InputStream inputStream = null;
            InputStreamReader inputStreamReader = null;
            StringBuffer buffer = null;

            try {
                url = new URL(UtilMediquei.getUrlWebServiceViacep());
            } catch (MalformedURLException e) {
                UtilMediquei.showToastShort(context, "Houve erro! Servidor incorreto.");
            } catch (Exception e) {
                UtilMediquei.showToastShort(context, "Houve erro! Tente novamente mais tarde.");
            }

            try {
                HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

                // Recupera os dados em Bytes
                inputStream = conexao.getInputStream();

                //inputStreamReader lê os dados em Bytes e decodifica para caracteres
                inputStreamReader = new InputStreamReader( inputStream );

                //Objeto utilizado para leitura dos caracteres do InpuStreamReader
                BufferedReader reader = new BufferedReader( inputStreamReader );
                buffer = new StringBuffer();
                String linha = "";

                while((linha = reader.readLine()) != null){
                    buffer.append( linha );
                }

            } catch (IOException erro) {
                UtilMediquei.showToastShort(context, "Houve erro! Tente novamente mais tarde.");
            }

            assert buffer != null;
            return buffer.toString();

        }

        @Override
        protected void onPostExecute(String resultado) {
            try {
                Log.d("WebService", "onPostExecute: " + resultado);
                Endereco endereco = new Endereco();
                JSONObject object = new JSONObject(resultado);

                /*
                if(object.getBoolean("erro")){
                    UtilMediquei.showToastShort(context.getApplicationContext(), "Verifique os dados inseridos!");
                    progressBar.setVisibility(View.INVISIBLE);
                }*/

                /*Recupera os dados passados*/
                endereco.setEndCep(object.getString("cep"));
                endereco.setEndUF(object.getString("uf"));
                endereco.setEndCidade(object.getString("localidade"));
                endereco.setEndBairro(object.getString("bairro"));
                endereco.setEndLogradouro(object.getString("logradouro"));

                CadastraEnderecoFragment fragment = CadastraEnderecoFragment.newInstance("Trocando Layout");
                Bundle bundle = new Bundle();
                bundle.putSerializable("enderecoCompleto", endereco);
                fragment.setArguments(bundle);

                FragmentTransaction transactionTer = context.getSupportFragmentManager().beginTransaction();
                transactionTer.replace(R.id.containerEndereco, fragment);
                transactionTer.commit();





            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static class cadastrarEnderecoUsuario extends AsyncTask<String, String, String>{

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

        public cadastrarEnderecoUsuario(Context context, Endereco endereco, ProgressBar progressBar){
            this.builder = new Uri.Builder();
            this.context = context;
            this.progressBar = progressBar;

            /*Montando os Parametros de Passagem
             * TODO: Primeira linha é a verificacao, para aceitar apenas requições com esse parametr
             * TODO: Demais linhas é os dados do usuario
             */
            builder.appendQueryParameter("verificar", "app_inserir_endereco");
            builder.appendQueryParameter(EnderecoDataModel.getIdfk(), String.valueOf(endereco.getEndIdfk()));
            builder.appendQueryParameter(EnderecoDataModel.getCep(), endereco.getEndCep());
            builder.appendQueryParameter(EnderecoDataModel.getUf(), endereco.getEndUF());
            builder.appendQueryParameter(EnderecoDataModel.getCidade(), endereco.getEndCidade());
            builder.appendQueryParameter(EnderecoDataModel.getBairro(), endereco.getEndBairro());
            builder.appendQueryParameter(EnderecoDataModel.getLogradouro(), endereco.getEndLogradouro());
            builder.appendQueryParameter(EnderecoDataModel.getComplemento(), endereco.getEndComplemento());
            builder.appendQueryParameter(EnderecoDataModel.getNumero(), endereco.getEndNumero());
        }

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                url = new URL(UtilMediquei.URL_WEB_SERVICE + LinkServerDataModel.getAPICadastrarEnderecoUsuario());
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
                UtilMediquei.showToastSucess(context, "Endereço cadastrado com Sucesso!");
            }
        }
    }
}
