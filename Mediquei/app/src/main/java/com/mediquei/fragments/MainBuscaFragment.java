package com.mediquei.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.mediquei.R;
import com.mediquei.activities.secundarias.ResultadoBuscaActivity;
import com.mediquei.adapter.AdapterMainBusca;
import com.mediquei.controller.BuscasController;
import com.mediquei.datamodel.BuscasDataModel;
import com.mediquei.model.Buscas;
import com.mediquei.util.UtilMediquei;

import java.util.ArrayList;
import java.util.List;


public class MainBuscaFragment extends Fragment {

    /*Declaracao das Variaveis*/
    private RecyclerView recyclerMainBusca;
    private List<Buscas> buscasList = new ArrayList<>();
    private AdapterMainBusca adapterMainBusca;
    private BuscasController controller;

    public MainBuscaFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_busca, container, false);

        /*Recupera as variaveis pelo ID*/
        recyclerMainBusca = view.findViewById(R.id.recyclerMainBusca);

        /*Gera a Lista*/
        gerarListaBuscar();

        /*Configura o Adapter*/
        adapterMainBusca = new AdapterMainBusca(buscasList, buscasMainOnClickListener());

        /*Configura o RecyclerView*/
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerMainBusca.setLayoutManager(layoutManager);
        recyclerMainBusca.setHasFixedSize(true);
        recyclerMainBusca.setAdapter(adapterMainBusca);

        return view;
    }

    private void gerarListaBuscar() {
        controller = new BuscasController(getActivity().getApplicationContext());
        buscasList = controller.listarBuscas();
    }

    public AdapterMainBusca.BuscasMainOnClickListener buscasMainOnClickListener() {
        return new AdapterMainBusca.BuscasMainOnClickListener() {
            @Override
            public void buscasMainOnClick(AdapterMainBusca.HolderBusca holder, int position) {
                /*Fecha o KeyBoard ao cliclar em salvar*/
                final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
                }
                List<Buscas> listaAtt = adapterMainBusca.getListaBuscas();
                Buscas busca = listaAtt.get(position);

                controller.alterar(busca);

                Intent it = new Intent(getActivity().getApplicationContext(), ResultadoBuscaActivity.class);
                it.putExtra(BuscasDataModel.getDscBusca(), busca.getDscBusca());
                startActivity(it);
            }
        };
    }

    public void pesquisarRemedio(String medic){
        List<Buscas> listaBuscas = new ArrayList<>();
        for(Buscas busca : controller.listaBuscaCompleta()){
            String nomeMedicamento = busca.getDscBusca().toLowerCase();
            medic = medic.toLowerCase();
            if(nomeMedicamento.contains(medic)){
                listaBuscas.add(busca);
            }
        }

        adapterMainBusca = new AdapterMainBusca(listaBuscas, buscasMainOnClickListener());
        recyclerMainBusca.setAdapter(adapterMainBusca);
        adapterMainBusca.notifyDataSetChanged();
    }

}
