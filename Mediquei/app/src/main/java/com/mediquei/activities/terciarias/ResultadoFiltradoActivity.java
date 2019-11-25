package com.mediquei.activities.terciarias;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.mediquei.R;
import com.mediquei.activities.EfetuarCompraActivity;
import com.mediquei.activities.OutraOpcoesActivity;
import com.mediquei.controller.ProdutoFinalController;
import com.mediquei.datamodel.mDescricaoDataModel;
import com.mediquei.helper.UsuarioPreferences;
import com.mediquei.helper.VerificaStatusInternet;
import com.mediquei.helper.Verificacao;
import com.mediquei.model.ProdutoFinal;
import com.mediquei.model.ResultadoBusca;
import com.mediquei.util.UtilMediquei;
import com.mediquei.util.UtilProdutoFinal;

public class ResultadoFiltradoActivity extends AppCompatActivity {

    private final int REQUEST_CODE = 1750;

    private ProdutoFinal produtoFinal;
    private TextView textNomeRemedio, textEmpresaRemedio, textPrecoRemedio;
    private LottieAnimationView animationRating, animationFavoritarRemedio;
    private Button btnQuantidade, btnComprar, btnAcionarCesta, btnOutrasOpcoes, btnFormaPgmt, btnTaxaEntrega;

    private int qtdade = 1;
    private int qtsVezes = 1;

    //atributo da classe.
    private AlertDialog alerta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_filtrado);
        Toolbar toolbar = findViewById(R.id.toolbarResultadoFiltrado);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        recuperarVariaveis();
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

    public void showQuantidadeDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater li = getLayoutInflater();
        @SuppressLint("InflateParams") View v = li.inflate(R.layout.layout_quantidade_dialog, null);
        v.findViewById(R.id.btnQuantidadeDialog1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                produtoFinal.setQtdadeProdutoFinal(1);
                btnQuantidade.setText(ProdutoFinalController.formataQtdadeProdutos(produtoFinal.getQtdadeProdutoFinal()));
                textPrecoRemedio.setText(ProdutoFinalController.formataPreco(produtoFinal.getQtdadeProdutoFinal(), produtoFinal.getpPrecoFinal()));
                btnFormaPgmt.setText(ProdutoFinalController.formataFormaPgmt(produtoFinal.getQtdadeParcelasFinal(), produtoFinal.getQtdadeProdutoFinal(),produtoFinal.getpPrecoFinal()));
                alerta.dismiss();
            }
        });

        v.findViewById(R.id.btnQuantidadeDialog2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                produtoFinal.setQtdadeProdutoFinal(2);
                btnQuantidade.setText(ProdutoFinalController.formataQtdadeProdutos(produtoFinal.getQtdadeProdutoFinal()));
                textPrecoRemedio.setText(ProdutoFinalController.formataPreco(produtoFinal.getQtdadeProdutoFinal(), produtoFinal.getpPrecoFinal()));
                btnFormaPgmt.setText(ProdutoFinalController.formataFormaPgmt(produtoFinal.getQtdadeParcelasFinal(), produtoFinal.getQtdadeProdutoFinal(),produtoFinal.getpPrecoFinal()));
                alerta.dismiss();
            }
        });

        v.findViewById(R.id.btnQuantidadeDialog3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                produtoFinal.setQtdadeProdutoFinal(3);
                btnQuantidade.setText(ProdutoFinalController.formataQtdadeProdutos(produtoFinal.getQtdadeProdutoFinal()));
                textPrecoRemedio.setText(ProdutoFinalController.formataPreco(produtoFinal.getQtdadeProdutoFinal(), produtoFinal.getpPrecoFinal()));
                btnFormaPgmt.setText(ProdutoFinalController.formataFormaPgmt(produtoFinal.getQtdadeParcelasFinal(), produtoFinal.getQtdadeProdutoFinal(),produtoFinal.getpPrecoFinal()));
                alerta.dismiss();
            }
        });

        v.findViewById(R.id.btnQuantidadeDialog4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                produtoFinal.setQtdadeProdutoFinal(4);
                btnQuantidade.setText(ProdutoFinalController.formataQtdadeProdutos(produtoFinal.getQtdadeProdutoFinal()));
                textPrecoRemedio.setText(ProdutoFinalController.formataPreco(produtoFinal.getQtdadeProdutoFinal(), produtoFinal.getpPrecoFinal()));
                btnFormaPgmt.setText(ProdutoFinalController.formataFormaPgmt(produtoFinal.getQtdadeParcelasFinal(), produtoFinal.getQtdadeProdutoFinal(),produtoFinal.getpPrecoFinal()));
                alerta.dismiss();
            }
        });

        builder.setView(v);
        alerta = builder.create();
        alerta.show();
    }

    public void showQuantasVezesDialog(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater li = getLayoutInflater();
        @SuppressLint("InflateParams") View v = li.inflate(R.layout.layout_quantidade_parcelas, null);

        final NumberPicker np = v.findViewById(R.id.numberPickerQuantidadeParcelas);
        np.setMinValue(1);
        np.setMaxValue(ProdutoFinalController.retornaArrayQuantidadeParcelas().length);
        np.setDisplayedValues(ProdutoFinalController.retornaArrayQuantidadeParcelas());

        v.findViewById(R.id.btnConfirmarNumberPicker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UtilMediquei.showToastSucess(ResultadoFiltradoActivity.this,
                        "Dividir em " + String.valueOf(np.getValue()) + " parcelas");
                produtoFinal.setQtdadeParcelasFinal(np.getValue());
                btnFormaPgmt.setText(ProdutoFinalController.formataFormaPgmt(produtoFinal.getQtdadeParcelasFinal(), produtoFinal.getQtdadeProdutoFinal(), produtoFinal.getpPrecoFinal()));
                alerta.dismiss();
            }
        });

        builder.setView(v);
        alerta = builder.create();
        alerta.show();
    }

    private void recuperarVariaveis(){
        produtoFinal = new ProdutoFinal();

        /*Recuperando as variaveis*/
        textNomeRemedio = findViewById(R.id.textNomeRemedioFiltrado);
        textEmpresaRemedio = findViewById(R.id.textEmpresaFiltrado);
        textPrecoRemedio = findViewById(R.id.textValorFiltrado);
        animationRating = findViewById(R.id.AnimationRatingFiltrado);
        btnFormaPgmt = findViewById(R.id.btnFormaPagamentoFiltrado);
        btnTaxaEntrega = findViewById(R.id.btnTaxaEntregaFiltrado);
        btnQuantidade = findViewById(R.id.btnQuantidadeFiltrado);
        btnComprar = findViewById(R.id.btnComprarFiltrado);
        btnAcionarCesta = findViewById(R.id.btnAdicionarCestaFiltrado);
        btnOutrasOpcoes = findViewById(R.id.btnOutrasLojasFiltrado);
        animationFavoritarRemedio = findViewById(R.id.animationFavoritarRemedio);

        /*Recuperando os dados*/
        ResultadoBusca resultadoBusca = (ResultadoBusca) getIntent().getSerializableExtra("resBusca");
        if(resultadoBusca != null){
            /*Definindo as Variaveis*/
            produtoFinal.setMedicIdFinal(resultadoBusca.getMedicId());
            produtoFinal.setMedicNomeFinal(resultadoBusca.getMedicNome());
            produtoFinal.setFarmIdFinal(resultadoBusca.getFarmId());
            produtoFinal.setFarmNomeFinal(resultadoBusca.getFarmNome());
            produtoFinal.setpPrecoFinal(resultadoBusca.getpPreco());
            produtoFinal.setDescIdFinal(resultadoBusca.getDescId());
            produtoFinal.setDescReceitaFinal(resultadoBusca.getDescReceita());

            textNomeRemedio.setText(produtoFinal.getMedicNomeFinal());
            textEmpresaRemedio.setText(ProdutoFinalController.formataEmpresa(produtoFinal.getFarmNomeFinal()));
            textPrecoRemedio.setText(ProdutoFinalController.formataPreco(produtoFinal.getQtdadeProdutoFinal(), produtoFinal.getpPrecoFinal()));
            btnFormaPgmt.setText(ProdutoFinalController.formataFormaPgmt(produtoFinal.getQtdadeParcelasFinal(), produtoFinal.getQtdadeProdutoFinal(), produtoFinal.getpPrecoFinal()));
            btnQuantidade.setText(ProdutoFinalController.formataQtdadeProdutos(produtoFinal.getQtdadeProdutoFinal()));
        }

    }

    public void calculaTaxaDeEntrega(View view) {
        if(VerificaStatusInternet.isOnline(ResultadoFiltradoActivity.this)){
            UsuarioPreferences up = new UsuarioPreferences(ResultadoFiltradoActivity.this);
            UtilProdutoFinal.BuscarDadosCalculoTaxaAsyncTask task =
                    new UtilProdutoFinal.BuscarDadosCalculoTaxaAsyncTask(ResultadoFiltradoActivity.this, btnTaxaEntrega, up.recuperarIdUsuarioPreferences(), produtoFinal.getFarmIdFinal());
            task.execute();
        } else {
            UtilMediquei.showToastInfo(ResultadoFiltradoActivity.this, "Conecte-se a Internet para prosseguir-mos!");
        }
    }

    public void concluirCompra(View view) {
        Intent it = new Intent(ResultadoFiltradoActivity.this, EfetuarCompraActivity.class);
        it.putExtra("produtoFinal", produtoFinal);
        startActivity(it);
    }

    public void verificaOutrasOpcoesDeFarmacia(View view) {
        Intent it = new Intent(ResultadoFiltradoActivity.this, OutraOpcoesActivity.class);
        it.putExtra(mDescricaoDataModel.getIdDesc(), produtoFinal.getDescIdFinal());
        startActivityForResult(it, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE){
            if(resultCode == RESULT_OK){
                produtoFinal.setFarmIdFinal(data.getIntExtra("idFarmAtt", produtoFinal.getFarmIdFinal()));
                produtoFinal.setFarmNomeFinal(data.getStringExtra("nomeFarmAtt"));
                produtoFinal.setpPrecoFinal(data.getDoubleExtra("precoAtt", produtoFinal.getpPrecoFinal()));

                textPrecoRemedio.setText(ProdutoFinalController.formataPreco(produtoFinal.getQtdadeProdutoFinal(), produtoFinal.getpPrecoFinal()));
                textEmpresaRemedio.setText(ProdutoFinalController.formataEmpresa(produtoFinal.getFarmNomeFinal()));
                btnFormaPgmt.setText(ProdutoFinalController.formataFormaPgmt(produtoFinal.getQtdadeParcelasFinal(), produtoFinal.getQtdadeProdutoFinal(), produtoFinal.getpPrecoFinal()));
                btnQuantidade.setText(ProdutoFinalController.formataQtdadeProdutos(produtoFinal.getQtdadeProdutoFinal()));
            }
        }
    }

    public void ativarAnimacao(View view) {
        animationFavoritarRemedio.playAnimation();
    }

    public void adicionarCesta(View view) {
        UtilMediquei.showToastInfo(ResultadoFiltradoActivity.this, "Adicionar a cesta será implementado na próxima versão!");
    }
}
