package com.mediquei.controller;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.mediquei.helper.Formatacoes;
import com.mediquei.model.Endereco;

public class ProdutoFinalController {
    public static String formataEmpresa(String empresa){
        return "por " + empresa;
    }

    public static String formataPreco(int quantidadeProduto, Double precoFinal){
        precoFinal *= quantidadeProduto;
        return "R$ " + Formatacoes.formataDuasCasasDecimaisPreco(precoFinal);
    }

    public static String formataFormaPgmt(int qtsVezes, int quantidadeProduto , Double preco){
        preco *= quantidadeProduto;
        Double precoFinal = preco/qtsVezes;
        return String.valueOf(qtsVezes) + "x R$" + Formatacoes.formataDuasCasasDecimaisPreco(precoFinal);
    }

    public static String formataQtdadeProdutos(int qtde){
        return "Quantidade: " + String.valueOf(qtde);
    }

    public static String[] retornaArrayQuantidadeParcelas(){
        return new String[] {"1x", "2x", "3x", "4x", "5x", "6x", "7x", "8x", "9x", "10x", };
    }

    public static String formataTaxaEntrega(LatLng enderecoInicial, LatLng enderecoDestino){
        return "Taxa de Entrega: R$ " + Formatacoes.formataDuasCasasDecimaisPreco(retornaTaxaEntrega(enderecoInicial,enderecoDestino));
    }

    public static Double retornaTaxaEntrega(LatLng enderecoInicial, LatLng enderecoDestino){
        Location localInicial = new Location("Local Inicial");
        localInicial.setLatitude(enderecoInicial.latitude);
        localInicial.setLongitude(enderecoInicial.longitude);

        Location localFinal = new Location("Local Final");
        localFinal.setLatitude(enderecoDestino.latitude);
        localFinal.setLongitude(enderecoDestino.longitude);

        float distancia = localInicial.distanceTo(localFinal)/1000;

        return 5.50;
    }

    public static String geraStringFinal(Endereco endereco){
        return endereco.getEndLogradouro() + ", " + endereco.getEndNumero() + " - " + endereco.getEndCidade();
    }
}
