package com.mediquei.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mediquei.R;
import com.mediquei.controller.PedidosController;
import com.mediquei.controller.ProdutoFinalController;
import com.mediquei.helper.Formatacoes;
import com.mediquei.model.Pedidos;
import com.mediquei.util.UtilMediquei;

import java.text.ParseException;


public class ConfirmaPedidoFragment extends Fragment {

    private TextView textPedido, textSituacao;
    private TextView textMedicamento, textQuantidade, textValorUnidade;
    private TextView textForma, textQuantas, textCartao, textTaxa;
    private TextView textValor;
    private Pedidos pedidos;

    public ConfirmaPedidoFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_confirma_pedido, container, false);
        /*Recupera do Layout*/
        textPedido = v.findViewById(R.id.textConfirmaPedidoNumeroPedido);
        textSituacao = v.findViewById(R.id.textSituacaoPedido);
        textMedicamento = v.findViewById(R.id.textMedicamentoConfirmaPedido);
        textValorUnidade = v.findViewById(R.id.textValorUnidadeConfirmaPedido);
        textQuantidade = v.findViewById(R.id.textQuantidadeConfirmaPedido);
        textForma = v.findViewById(R.id.textFormaPagamentoConfirmaPedido);
        textQuantas = v.findViewById(R.id.textQuantasVezesConfirmaPedido);
        textCartao = v.findViewById(R.id.textCartaoConfirmaPedido);
        textValor = v.findViewById(R.id.textValorTotalConfirmaCompra);
        textTaxa = v.findViewById(R.id.textTaxaConfirmaPedido);
        setaTexts();

        return v;
    }

    private void setaTexts() {
        Bundle data = getArguments();
        if(data != null) {
            pedidos = (Pedidos) data.getSerializable("pedido");
            Double precoUnidade = pedidos.getPedValorTotal();

            pedidos.setPedValorTotal(PedidosController.retornaValorTotal(pedidos.getPedQtdadeMedicamento(), pedidos.getPedValorTotal(), pedidos.getPedTaxaEntrega()));

            textPedido.setText(pedidos.getPedId());
            textSituacao.setText(PedidosController.retornaStatusPedido( pedidos.getPedIdSituacao()));

            textMedicamento.setText(pedidos.getNomeRemedio());
            textValorUnidade.setText("R$ " + Formatacoes.formataDuasCasasDecimaisPreco(precoUnidade));
            textQuantidade.setText(PedidosController.retornaQuantidade(pedidos.getPedQtdadeMedicamento()));

            textForma.setText(PedidosController.retornaFormaPagamento(pedidos.getPedIdFormaPagamento()));
            textQuantas.setText(PedidosController.retornaNumeroParcelas(pedidos.getPedQtdadeParcelas(), pedidos.getPedValorTotal()));
            textCartao.setText(Formatacoes.formataTextoCartoesBandeira(pedidos.getNumeroCartao()));
            textTaxa.setText("R$ " + Formatacoes.formataDuasCasasDecimaisPreco(pedidos.getPedTaxaEntrega()));

            textValor.setText("R$ " + Formatacoes.formataDuasCasasDecimaisPreco(pedidos.getPedValorTotal()));
        }
    }


}
