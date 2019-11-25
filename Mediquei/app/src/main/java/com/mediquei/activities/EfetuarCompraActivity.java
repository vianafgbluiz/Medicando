package com.mediquei.activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.mediquei.R;
import com.mediquei.fragments.ConfirmaPedidoFragment;
import com.mediquei.fragments.FotoReceitaFragment;
import com.mediquei.fragments.VerificaEnderecoFragment;
import com.mediquei.fragments.VerificaFormaPagamentoFragment;
import com.mediquei.helper.GeradorIdPedidos;
import com.mediquei.helper.UsuarioPreferences;
import com.mediquei.helper.Verificacao;
import com.mediquei.model.Bancarios;
import com.mediquei.model.Endereco;
import com.mediquei.model.Pedidos;
import com.mediquei.model.ProdutoFinal;
import com.mediquei.util.UtilMediquei;
import com.mediquei.util.UtilPedidos;

import java.util.Objects;

public class EfetuarCompraActivity extends AppCompatActivity
    implements VerificaEnderecoFragment.InterfaceComunicacao,
               VerificaFormaPagamentoFragment.InterfaceComunicacaoForma,
               FotoReceitaFragment.InterfaceComunicacaoFoto{

    private Button btnProsseguir, btnVoltar;
    private Pedidos pedido;
    private FragmentManager fragmentManager;

    /*Fragments Para Conclusao*/
    private FotoReceitaFragment fotoReceitaFragment;
    private ConfirmaPedidoFragment confirmaPedidoFragment;
    private VerificaEnderecoFragment verificaEnderecoFragment;

    private String precisaReceita;
    private android.support.v7.widget.Toolbar toolbar;

    private int mostrarLayout = 1;

    private final int VERIFICA_ENDERECO = 1;
    private final int VERIFICA_FORMA_PAGAMENTO = 2;
    private int adicionouFoto = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_efetuar_compra);
        toolbar = findViewById(R.id.toolbarCompras);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        /*Recupero as Variaveis*/
        recuperarVariaveis();

        verificaEnderecoFragment = new VerificaEnderecoFragment();
        Bundle data = new Bundle();
        data.putInt("idFarm", pedido.getPedIdFarmacia());
        verificaEnderecoFragment.setArguments(data);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.containerEfetuarCompras, verificaEnderecoFragment);
        transaction.commit();

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragments(2);
            }
        });

        btnProsseguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragments(1);
            }
        });
    }

    private void recuperarVariaveis() {
        pedido = new Pedidos();
        btnVoltar = findViewById(R.id.btnVoltarEfetuarCompra);
        btnProsseguir = findViewById(R.id.btnProsseguirEfetuarCompra);

        /*Recupero os dados passados da Intent Anterior*/
        ProdutoFinal produtoFinal = (ProdutoFinal) getIntent().getSerializableExtra("produtoFinal");
        if(produtoFinal != null){
            pedido.setPedId(GeradorIdPedidos.geraAleatoria());
            pedido.setPedIdUser(new UsuarioPreferences(EfetuarCompraActivity.this).recuperarIdUsuarioPreferences());
            pedido.setPedIdFarmacia(produtoFinal.getFarmIdFinal());
            pedido.setPedIdMedicamento(produtoFinal.getMedicIdFinal());
            pedido.setPedIdDesc(produtoFinal.getDescIdFinal());
            pedido.setPedQtdadeMedicamento(produtoFinal.getQtdadeProdutoFinal());
            pedido.setPedValorTotal(produtoFinal.getpPrecoFinal());
            pedido.setPedQtdadeParcelas(produtoFinal.getQtdadeParcelasFinal());
            pedido.setNomeFarmacia(produtoFinal.getFarmNomeFinal());
            pedido.setNomeRemedio(produtoFinal.getMedicNomeFinal());
            precisaReceita = produtoFinal.getDescReceitaFinal();
        }
    }

    public void switchFragments(int comando){
        switch (comando){
            case 1:
                adicionarProximoFragment();
                break;
            case 2:
                retrocederFragmentAntigo();
                break;
            }
    }

    private void retrocederFragmentAntigo() {
        switch (mostrarLayout){
            case 1:
                break;
            case 2:
                mostrarLayout--;
                fragmentManager.popBackStack();
                break;
            case 3:
                mostrarLayout--;
                fragmentManager.popBackStack();
                break;
            case 4:
                mostrarLayout--;
                fragmentManager.popBackStack();
                break;

        }
    }


    private void adicionarProximoFragment() {
        switch(mostrarLayout){
            case 1:
                if(pedido.getPedIdUserEndereco() != 0){
                    mostrarLayout++;
                    fragmentManager = getSupportFragmentManager();
                    FragmentTransaction transactOne = fragmentManager.beginTransaction();
                    transactOne.addToBackStack(null);
                    transactOne.replace(R.id.containerEfetuarCompras, new VerificaFormaPagamentoFragment());
                    transactOne.commit();
                } else {
                    UtilMediquei.showToastInfo(EfetuarCompraActivity.this, "É necessário escolher um endereço!");
                }
                break;
            case 2:
                if(pedido.getPedIdUserBancarios() != 0 && precisaReceita.equals("S")){
                    mostrarLayout++;
                    fotoReceitaFragment = new FotoReceitaFragment();
                    Bundle data = new Bundle();
                    data.putString("numeroPedido", pedido.getPedId());
                    fotoReceitaFragment.setArguments(data);

                    fragmentManager = getSupportFragmentManager();
                    FragmentTransaction transactTwo = fragmentManager.beginTransaction();
                    transactTwo.addToBackStack(null);
                    transactTwo.replace(R.id.containerEfetuarCompras, fotoReceitaFragment);
                    transactTwo.commit();
                } else if (pedido.getPedIdUserBancarios() != 0 && precisaReceita.equals("N")){
                    mostrarLayout = 5;
                    btnProsseguir.setText("Confirmar Compra");
                    btnVoltar.setVisibility(View.GONE);

                    confirmaPedidoFragment = new ConfirmaPedidoFragment();
                    Bundle data = new Bundle();
                    data.putSerializable("pedido", pedido);
                    confirmaPedidoFragment.setArguments(data);

                    fragmentManager = getSupportFragmentManager();
                    FragmentTransaction transactTwo = fragmentManager.beginTransaction();
                    transactTwo.addToBackStack(null);
                    transactTwo.replace(R.id.containerEfetuarCompras, confirmaPedidoFragment);
                    transactTwo.commit();
                } else {
                    UtilMediquei.showToastInfo(EfetuarCompraActivity.this, "É necessário escolher uma forma de pagamento!");
                }
                break;
            case 3:
                if(adicionouFoto == 1){
                    mostrarLayout = 5;
                    btnProsseguir.setText("Confirmar Compra");
                    btnVoltar.setVisibility(View.GONE);

                    confirmaPedidoFragment = new ConfirmaPedidoFragment();
                    Bundle data = new Bundle();
                    data.putSerializable("pedido", pedido);
                    confirmaPedidoFragment.setArguments(data);

                    fragmentManager = getSupportFragmentManager();
                    FragmentTransaction transactThree = fragmentManager.beginTransaction();
                    transactThree.addToBackStack(null);
                    transactThree.replace(R.id.containerEfetuarCompras, confirmaPedidoFragment);
                    transactThree.commit();
                } else if (adicionouFoto == 0){
                    UtilMediquei.showToastInfo(EfetuarCompraActivity.this, "Adicione uma Foto!");
                } else {
                    UtilMediquei.showToastInfo(EfetuarCompraActivity.this, "Aguarde o processamento da Foto");
                }

                break;
            case 5:
                UtilPedidos.confirmaSolicitacaoPedidoAsyncTask task =
                        new UtilPedidos.confirmaSolicitacaoPedidoAsyncTask(EfetuarCompraActivity.this, pedido);
                task.execute();
                break;
        }
    }

    @Override
    public void setEndereco(Endereco enderecoEscolhidoUser) {
        pedido.setPedIdUserEndereco(enderecoEscolhidoUser.getEndId());
        pedido.setPedTaxaEntrega(enderecoEscolhidoUser.getTaxaEscolhida());
    }

    @Override
    public void setBancarios(Bancarios bancariosEscolhidoUser) {
        pedido.setPedIdUserBancarios(bancariosEscolhidoUser.getBanId());
        pedido.setNumeroCartao(bancariosEscolhidoUser.getBanNumero());
    }

    @Override
    public void setUrlLink(String urlLink) {
        pedido.setPedLinkReceita(urlLink);
        adicionouFoto = 1;
        UtilMediquei.showToastSucess(EfetuarCompraActivity.this, "Podemos prosseguir com a compra");
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
}



