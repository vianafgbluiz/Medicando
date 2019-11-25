package com.mediquei.helper;

import com.mediquei.model.Endereco;

import java.util.ArrayList;
import java.util.List;

public class Formatacoes {
    public static String formataTextoCartoesBandeira(String numero){
        String individuais = numero.substring(numero.length() - 4);
        return "Terminado em " + individuais;
    }

    public static String formataTextoEndereco(Endereco endereco){
        String retorno = "";
        if(!endereco.getEndNumero().equals("")){
            retorno += endereco.getEndLogradouro();
            retorno += ", " + endereco.getEndNumero();
            retorno += ", " + endereco.getEndBairro();
            retorno += " - " + endereco.getEndCidade();
        } else {
            retorno += endereco.getEndLogradouro();
            retorno += ", " + endereco.getEndBairro();
            retorno += " - " + endereco.getEndCidade();
        }
        return retorno;
    }

    public static String formataDuasCasasDecimaisPreco(Double preco) {
        return String.format("%.2f", preco);
    }

    public static String formataNomeBancarios(String nome){
        return nome.toUpperCase();
    }

    public static String formataCPF(String cpf){
        StringBuilder stringBuilder = new StringBuilder(cpf);
        stringBuilder.insert(cpf.length() - 2, "-");
        stringBuilder.insert(cpf.length() - 5, ".");
        stringBuilder.insert(cpf.length() - 8, ".");
        return stringBuilder.toString();
    }

    public static String formataValidade(String validade){
        StringBuilder stringBuilder = new StringBuilder(validade);
        stringBuilder.insert(validade.length() - 2, "/");
        stringBuilder.insert(validade.length() - 1, "0");
        stringBuilder.insert(validade.length() - 1, "2");
        return stringBuilder.toString();
    }

    public static List<String> formataTextoRua(Endereco endereco) {
        List<String> formatados = new ArrayList<>();
        String formataRua = endereco.getEndLogradouro() + ", " + endereco.getEndNumero();
        String formataAdd = endereco.getEndBairro() + " - " + endereco.getEndCidade() + ", " + endereco.getEndUF();
        formatados.add(formataRua);
        formatados.add(formataAdd);
        return formatados;
    }
}
