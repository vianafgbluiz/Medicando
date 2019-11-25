package com.mediquei.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.mediquei.R;
import com.mediquei.helper.Verificacao;
import com.mediquei.model.Endereco;
import com.mediquei.util.UtilEndereco;
import com.mediquei.util.UtilMediquei;
import com.rengwuxian.materialedittext.MaterialEditText;

public class PesquisaViaCepFragment extends Fragment {

    /*Declaração das variaveis*/
    private Button btnProcurar;
    private MaterialEditText editCEP;
    ProgressBar pbPesquisa;

    /*Construtor Publico*/
    public PesquisaViaCepFragment() {
        // Required empty public constructor
    }

    // TODO: Nova Instancia quando se precisa passar arguementos
    public static PesquisaViaCepFragment newInstance(Context context) {
        PesquisaViaCepFragment fragment = new PesquisaViaCepFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pesquisa_via_cep, container, false);

        btnProcurar = view.findViewById(R.id.btnProcurarCEP);
        editCEP = view.findViewById(R.id.editViaCEP);
        pbPesquisa = view.findViewById(R.id.progressBarPesquisaViaCEP);

        /*Desativa ProgressBar*/
        pbPesquisa.setVisibility(View.INVISIBLE);

        btnProcurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Verificacao.verificaCEPInserido(editCEP.getText().toString())){
                    UtilEndereco.procurarEnderecoViaCEP task =
                            new UtilEndereco.procurarEnderecoViaCEP((AppCompatActivity) getActivity(), pbPesquisa, editCEP.getText().toString());
                    task.execute();
                } else {
                    UtilMediquei.showToastShort(getActivity().getApplicationContext(), "Preencha corretamente o CEP");
                }
            }
        });
        return view;
    }
}
