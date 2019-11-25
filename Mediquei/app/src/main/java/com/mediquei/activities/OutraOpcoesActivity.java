package com.mediquei.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.mediquei.R;
import com.mediquei.adapter.AdapterOutrasOpcoes;
import com.mediquei.datamodel.FarmaciasDataModel;
import com.mediquei.datamodel.LinkServerDataModel;
import com.mediquei.datamodel.PrecosDataModel;
import com.mediquei.datamodel.VerificacoesWebService;
import com.mediquei.datamodel.mDescricaoDataModel;
import com.mediquei.model.OutrasOpcoes;
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


public class OutraOpcoesActivity extends AppCompatActivity {

    private RecyclerView recyclerOpcoes;
    private AdapterOutrasOpcoes adapterOutrasOpcoes;
    private List<OutrasOpcoes> outrasOpcoesList;
    private int descId;

    private ProgressBar pb;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outra_opcoes);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Outras Farmácias");

        recyclerOpcoes = findViewById(R.id.recyclerOutrasOpcoes);
        pb = findViewById(R.id.progressBarOutrasOpcoes);
        image = findViewById(R.id.backProgressBarOutrasOpcoes);

        /*Recupera String Passada*/
        Bundle dados = getIntent().getExtras();
        if(dados!=null){
            descId = dados.getInt(mDescricaoDataModel.getIdDesc());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        BuscarOutrasOpcoesAsyncTask task =
                new BuscarOutrasOpcoesAsyncTask(descId, OutraOpcoesActivity.this);
        task.execute();
    }

    public AdapterOutrasOpcoes.EscolherOnClickListener escolherOnClickListener(){
        return new AdapterOutrasOpcoes.EscolherOnClickListener() {
            @Override
            public void escolherOnClick(AdapterOutrasOpcoes.OutrasOpcoesHolder holder, int position) {
                OutrasOpcoes escolha = outrasOpcoesList.get(position);

                Intent returnIntent = new Intent();
                returnIntent.putExtra("nomeFarmAtt", escolha.getNomeFarmacia());
                returnIntent.putExtra("idFarmAtt", escolha.getIdFarmacia());
                returnIntent.putExtra("precoAtt", escolha.getPrecoMedicamento());
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        };
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return true;
    }

    public class BuscarOutrasOpcoesAsyncTask extends AsyncTask<String, String, String> {
        private int id;

        /*Declarando as variaveis da conexao HTTP*/
        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;

        private Context context;

        public BuscarOutrasOpcoesAsyncTask(int id, Context context) {
            this.builder = new Uri.Builder();
            this.id = id;
            this.context = context;

            builder.appendQueryParameter("verificar", VerificacoesWebService.getVerificaAppBuscarOutrasOpcoes());
            builder.appendQueryParameter(mDescricaoDataModel.getIdDesc(), String.valueOf(id));
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb.setVisibility(View.VISIBLE);
            image.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                url = new URL(UtilMediquei.URL_WEB_SERVICE + LinkServerDataModel.getAPIBuscarOutrasOpcoes());
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
            outrasOpcoesList = new ArrayList<>();
            try{
                JSONArray jsonArray = new JSONArray(result);
                if(jsonArray.length() != 0){
                    JSONObject object = jsonArray.getJSONObject(0);
                    if(object.getBoolean("sucesso")){
                        for (int i = 0; i < jsonArray.length(); i++) {
                            object = jsonArray.getJSONObject(i);

                            OutrasOpcoes opcao = new OutrasOpcoes();
                            opcao.setIdFarmacia(object.getInt(FarmaciasDataModel.getIdFarmacia()));
                            opcao.setNomeFarmacia(object.getString(FarmaciasDataModel.getNomeFarmacia()));
                            opcao.setPrecoMedicamento(object.optDouble(PrecosDataModel.getPreco()));

                            outrasOpcoesList.add(opcao);
                        }

                        adapterOutrasOpcoes = new AdapterOutrasOpcoes(outrasOpcoesList, escolherOnClickListener());

                        /*Configura o RecyclerView*/
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerOpcoes.setLayoutManager(layoutManager);
                        recyclerOpcoes.setHasFixedSize(true);
                        recyclerOpcoes.setAdapter(adapterOutrasOpcoes);
                    } else {
                        UtilMediquei.showToastError(context,
                                "Não houve sucesso, tente novamente!");
                    }
                } else {
                    UtilMediquei.showToastError(context,
                            "Nenhum registro encontrado no momento!");
                }
            } catch (JSONException e){
                Log.d("WebService", "Erro JsonException: " + e.getMessage());
            } finally {
                if (pb != null && pb.getVisibility() == View.VISIBLE) {
                    pb.setVisibility(View.GONE);
                    image.setVisibility(View.GONE);
                }
            }
        }
    }
}
