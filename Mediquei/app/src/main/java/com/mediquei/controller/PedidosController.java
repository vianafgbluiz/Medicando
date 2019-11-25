package com.mediquei.controller;

import com.mediquei.helper.Formatacoes;

public class PedidosController {
    public static String retornaStatusPedido(int id){
        String retorno = "";
        StatusPedidoController status = new StatusPedidoController();
        switch (id){
            case 1:
                retorno = status.getEFETUADO();
                break;
            case 2:
                retorno = status.getAUTORIZADO();
                break;
            case 3:
                retorno = status.getEM_TRANSPORTE();
                break;
            case 4:
                retorno = status.getENTREGUE();
                break;
            case 5:
                retorno = status.getNAO_AUTORIZADO();
                break;
            case 6:
                retorno = status.getCANCELADO();
                break;
            case 7:
                retorno = status.getNAO_ENTREGUE();
                break;
            case 8:
                retorno = status.getAGUARDANDO_RETIRADA();
                break;
        }
        return retorno;
    }

    public static String retornaQuantidade(int id) {
        String retorno = "";
        switch (id){
            case 1:
                retorno = "1 unidade";
                break;
            case 2:
                retorno = "2 unidades";
                break;
            case 3:
                retorno = "3 unidades";
                break;
            case 4:
                retorno = "4 unidades";
                break;
        }
        return retorno;
    }

    public static String retornaFormaPagamento(int id){
        String retorno = "";
        switch (id){
            case 1:
                retorno = "Cartão de Crédito";
                break;
            case 2:
                retorno = "Boleto Bancário";
                break;
        }
        return retorno;
    }

    public static Double retornaValorTotal(int quantidade, Double precoAtual, Double taxaEntrega){
        return (precoAtual * quantidade) + taxaEntrega;
    }

    public static String retornaNumeroParcelas(int qtdParcelas, Double precoFinal){
        return String.valueOf(qtdParcelas) + "x R$ " + Formatacoes.formataDuasCasasDecimaisPreco(precoFinal/qtdParcelas);
    }
}
