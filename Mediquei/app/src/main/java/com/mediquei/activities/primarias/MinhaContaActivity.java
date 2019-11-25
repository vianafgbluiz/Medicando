package com.mediquei.activities.primarias;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mediquei.R;
import com.mediquei.activities.secundarias.AdicionarEnderecoActivity;
import com.mediquei.activities.secundarias.AdicionarFormaPgmtActivity;
import com.mediquei.adapter.AdapterEndereco;
import com.mediquei.adapter.AdapterFormaPgmt;
import com.mediquei.datamodel.LinkServerDataModel;
import com.mediquei.datamodel.UsuarioDataModel;
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

import static android.view.View.VISIBLE;

public class MinhaContaActivity extends AppCompatActivity {

    /*Declarando as variaveis*/
    private RecyclerView recyclerFormaPgmt, recyclerEndereco;
    private TextView textNome, textEmail, textCadastro;
    private UsuarioPreferences idUsuarioPreferences;
    private ImageButton btnAddEndereco, btnAddFormaPagamento;
    private ProgressBar pbMinhaConta;

    /*Variaveis de Auxilio para os RecyclerViews*/
    private AdapterFormaPgmt adapterFormaPgmt;
    private AdapterEndereco adapterEndereco;
    private List<Bancarios> bancariosList = new ArrayList<>();
    private List<Endereco> enderecoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minha_conta);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("");

        /*Recuperar as variaveis passadas pela view*/
        textNome = findViewById(R.id.textNomeMostrarConta);
        textEmail = findViewById(R.id.textEmailMostrarConta);
        textCadastro = findViewById(R.id.textDataMostrarConta);
        btnAddEndereco = findViewById(R.id.btnAddEndereco);
        btnAddFormaPagamento = findViewById(R.id.btnAddFormaPagamento);
        pbMinhaConta = findViewById(R.id.progressBarMinhaConta);
        recyclerEndereco = findViewById(R.id.recyclerEnderecoMinhaConta);
        recyclerFormaPgmt = findViewById(R.id.recyclerFormasPagamentoMinhaConta);

        /*Recuperando o Id*/
        idUsuarioPreferences = new UsuarioPreferences(MinhaContaActivity.this);

        /*Configura os Adapters*/
        adapterFormaPgmt = new AdapterFormaPgmt(bancariosList, formaPgtmOnClickListener());
        adapterEndereco = new AdapterEndereco(enderecoList, enderecoOnClickListener());

        /*Configura os RecyclerViews*/
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerFormaPgmt.setLayoutManager(layoutManager);
        recyclerFormaPgmt.setHasFixedSize(true);
        recyclerFormaPgmt.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerFormaPgmt.setAdapter(adapterFormaPgmt);

        RecyclerView.LayoutManager layoutManagerEnd = new LinearLayoutManager(getApplicationContext());
        recyclerEndereco.setLayoutManager(layoutManagerEnd);
        recyclerEndereco.setHasFixedSize(true);
        recyclerEndereco.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerEndereco.setAdapter(adapterEndereco);
    }

    @Override
    protected void onStart() {
        super.onStart();
        /*Limpa as listas para buscar novamente*/
        enderecoList.clear();
        bancariosList.clear();
        /*Recuperando os dados no banco*/
        BuscarDadosEspecificos asyncTask =
                new BuscarDadosEspecificos(this, idUsuarioPreferences.recuperarIdUsuarioPreferences());
        asyncTask.execute();
    }

    /*Click para novo endereço*/

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

    public void adicionarEndereco(View view) {
        startActivity(new Intent(MinhaContaActivity.this, AdicionarEnderecoActivity.class));
    }

    public void adicionarFormaPagamento(View view) {
        startActivity(new Intent(MinhaContaActivity.this, AdicionarFormaPgmtActivity.class));
    }

    public AdapterFormaPgmt.FormaPgtmOnClickListener formaPgtmOnClickListener() {
        return new AdapterFormaPgmt.FormaPgtmOnClickListener() {
            @Override
            public void formaPgmtOnClick(AdapterFormaPgmt.HolderPgmt holder, int position) {
                Bancarios bancarios = bancariosList.get(position);
            }
        };
    }

    public AdapterEndereco.EnderecoOnClickListener enderecoOnClickListener() {
        return new AdapterEndereco.EnderecoOnClickListener() {
            @Override
            public void formaPgmtOnClick(AdapterEndereco.HolderEndereco holder, int position) {
                Endereco endereco = enderecoList.get(position);
                UtilMediquei.showToastInfo(MinhaContaActivity.this, "Editar será implementado na próxima versão!");
            }
        };
    }

    @SuppressLint("StaticFieldLeak")
    public class BuscarDadosEspecificos extends AsyncTask<String, String, String> {
        /*Progress Bar passada para poder inicializar*/
        @SuppressLint("StaticFieldLeak")
        Context context;

        /*Declarando as variaveis da conexao HTTP*/
        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;

        BuscarDadosEspecificos(Context context, int idusuario){
            this.builder = new Uri.Builder();
            this.context = context;

            builder.appendQueryParameter("verificar", "app_buscar_dados");
            builder.appendQueryParameter(UsuarioDataModel.getId(), String.valueOf(idusuario));
        }

        @Override
        protected void onPreExecute() {
            pbMinhaConta.setVisibility(VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                //url = new URL(UtilMediquei.URL_WEB_SERVICE + "APICadastrarUsuarioTeste.php");
                url = new URL(UtilMediquei.URL_WEB_SERVICE + LinkServerDataModel.getAPIBuscarDadosEspecificos());
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
            try{
                Log.d("Resultado", "onPostExecute: " + result);
                Bancarios bancarios;
                Endereco endereco;
                JSONArray jsonArray = new JSONArray(result);

                /*Recupera os dados do Array*/
                if(jsonArray.length() != 0){
                    JSONObject object = jsonArray.getJSONObject(0);
                    textNome.setText(object.getString(UsuarioDataModel.getNome()));
                    textEmail.setText(object.getString(UsuarioDataModel.getEmail()));
                    textCadastro.setText(object.getString(UsuarioDataModel.getDataCadastro()));

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        if(obj.getString("verificacao").equals("user_cartao")){
                            bancarios = new Bancarios();
                            bancarios.setBanIdBandeira(obj.getInt("ban_id_bandeira"));
                            bancarios.setBanNumero(obj.getString("ban_numero"));
                            bancariosList.add(bancarios);
                        }
                        if(obj.getString("verificacao").equals("user_endereco")){
                            endereco = new Endereco();
                            endereco.setEndLogradouro(Objects.equals(obj.getString("end_logradouro"), "null") ? " " : obj.getString("end_logradouro"));
                            endereco.setEndNumero(Objects.equals(obj.getString("end_numero"), "null") ? " " : obj.getString("end_numero"));
                            endereco.setEndBairro(Objects.equals(obj.getString("end_bairro"), "null") ? " " : obj.getString("end_bairro"));
                            endereco.setEndCidade(Objects.equals(obj.getString("end_cidade"), "null") ? " " : obj.getString("end_cidade"));
                            enderecoList.add(endereco);
                        }
                    }
                    adapterFormaPgmt.notifyDataSetChanged();
                    adapterEndereco.notifyDataSetChanged();
                } else {
                    UtilMediquei.showToastError(context,
                            "Nenhum registro encontrado no momento!");
                }
            } catch (JSONException e){
                UtilMediquei.showToastError(context,"Houve erro, tente novamente mais tarde!");
                //Log.d("WebService", "Erro JsonException: " + e.getMessage());
            } finally {
                if(pbMinhaConta != null && pbMinhaConta.getVisibility() == VISIBLE){
                    pbMinhaConta.setVisibility(View.INVISIBLE);
                }
            }

        }
    }
}
