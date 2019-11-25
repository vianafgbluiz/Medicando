package com.mediquei.controller;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.mediquei.activities.primarias.MainActivity;
import com.mediquei.datamodel.BuscasDataModel;
import com.mediquei.datasource.DataSource;
import com.mediquei.model.Buscas;

import java.util.ArrayList;
import java.util.List;

public class BuscasController extends DataSource {

    ContentValues dados;
    Context context;

    public BuscasController(Context context) {
        super(context);
        this.context = context;
    }

    /*Cria a tabela inical, caso seja a primeira vez da pessoa no App*/
    public void criarBuscasIniciais(){
        List<Buscas> buscasList = new ArrayList<>();
        for(Buscas busca : buscasList){
            salvar(busca);
            Log.d("sql", "criarBuscasIniciais: " + busca.getDscBusca());
        }
    }

    public boolean salvar(Buscas obj){
        boolean sucesso = true;
        dados = new ContentValues();
        dados.put(BuscasDataModel.getDscBusca(), obj.getDscBusca());
        dados.put(BuscasDataModel.getRecenteBusca(), obj.getRecenteBusca());
        sucesso = insert(BuscasDataModel.getTABELA(), dados);
        return sucesso;
    }

    public boolean alterar(Buscas obj) {
        boolean sucesso = true;
        dados = new ContentValues();
        dados.put(BuscasDataModel.getRecenteBusca(), 1);
        sucesso = update(BuscasDataModel.getTABELA(), dados, obj.getDscBusca());
        return sucesso;
    }

    public List<Buscas> listarBuscas(){
        List<Buscas> listaRetorno = getBuscasIniciais();
        if(listaRetorno != null){
            return listaRetorno;
        }
        return null;
    }

    public List<Buscas> listaBuscaCompleta(){
        List<Buscas> listaCompleta = getAllBuscas();
        if(listaCompleta != null){
            return listaCompleta;
        }
        return null;
    }
}
