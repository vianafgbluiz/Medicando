package com.mediquei.activities.primarias;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toolbar;

import com.mediquei.R;
import com.mediquei.adapter.AdapterHistorico;
import com.mediquei.datamodel.LinkServerDataModel;
import com.mediquei.datamodel.PedidosDataModel;
import com.mediquei.helper.UsuarioPreferences;
import com.mediquei.model.Pedidos;
import com.mediquei.util.UtilMediquei;

import org.json.JSONArray;
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

public class HistoricoPedidoActivity extends AppCompatActivity {

    private Button btnFiltrarHistorico;
    private RecyclerView recyclerHistorico;

    private AdapterHistorico adapterHistorico;
    private List<Pedidos> pedidosList = new ArrayList<>();

    private ImageView image;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_pedido);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbarPadrao);
        toolbar.setTitle("Historico de Pedidos");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        /*Recuperar as Variaveis*/
        btnFiltrarHistorico = findViewById(R.id.btnFiltrarHistorico);
        recyclerHistorico = findViewById(R.id.recyclerHistorico);
        pb = findViewById(R.id.progressBarHistorico);
        image = findViewById(R.id.backProgressBarHistorico);

    }

    public AdapterHistorico.HistoricoOnClickListener historicoOnClickListener() {
        return new AdapterHistorico.HistoricoOnClickListener() {
            @Override
            public void historicoOnClick(AdapterHistorico.HolderHistorico holder, int position) {
                UtilMediquei.showToastInfo(HistoricoPedidoActivity.this, "Detalhes do pedido será implementado na próxima versão!");
            }
        };
    }

    //Volta para a activity anterior
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

    @Override
    protected void onStart() {
        super.onStart();
        pedidosList.clear();
        HistoricoPedidoAsyncTask task =
                new HistoricoPedidoAsyncTask(HistoricoPedidoActivity.this, new UsuarioPreferences(HistoricoPedidoActivity.this).recuperarIdUsuarioPreferences());
        task.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public class HistoricoPedidoAsyncTask extends AsyncTask<String, String, String> {
        Context context;

        /*Declarando as variaveis da conexao HTTP*/
        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;

        int idUser;

        public HistoricoPedidoAsyncTask(Context context, int idUser) {
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
                url = new URL(UtilMediquei.URL_WEB_SERVICE + LinkServerDataModel.getAPIBuscarPedidos());
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
            Log.d("WebService", "onPostExecute: " + result);
            pedidosList = new ArrayList<>();

            try {
                JSONArray jsonArray = new JSONArray(result);
                JSONObject obj = jsonArray.getJSONObject(0);
                if(jsonArray.length() != 0){
                    if(obj.getBoolean("sucesso")){
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Pedidos pedidoAtual = new Pedidos();
                            JSONObject object = jsonArray.getJSONObject(i);
                            pedidoAtual.setPedId(object.getString(PedidosDataModel.getId()));
                            pedidoAtual.setPedIdSituacao(object.getInt(PedidosDataModel.getIdSituacao()));
                            pedidoAtual.setPedDataEntregue(object.getString(PedidosDataModel.getDataEntregue()));
                            pedidoAtual.setPedValorTotal(object.getDouble(PedidosDataModel.getValorTotal()));
                            pedidosList.add(pedidoAtual);
                        }

                        adapterHistorico = new AdapterHistorico(pedidosList, historicoOnClickListener());

                        /*Configura o RecyclerView*/
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
                        recyclerHistorico.setLayoutManager(layoutManager);
                        recyclerHistorico.setHasFixedSize(true);
                        recyclerHistorico.setAdapter(adapterHistorico);
                    } else {
                        UtilMediquei.showToastError(context, "Servidor instável, tente novamente mais tarde.");
                    }
                } else {
                    UtilMediquei.showToastInfo(context, "Nenhum registro encontrado.");
                }

            } catch (Exception e){
                UtilMediquei.showToastError(context, "Erro JsonException: " + e.getMessage());
            }finally {
                if (pb != null && pb.getVisibility() == View.VISIBLE) {
                    pb.setVisibility(View.GONE);
                    image.setVisibility(View.GONE);
                }
            }
        }
    }
}

