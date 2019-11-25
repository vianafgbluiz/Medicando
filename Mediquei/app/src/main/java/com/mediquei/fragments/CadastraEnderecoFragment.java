package com.mediquei.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mediquei.R;
import com.mediquei.helper.VerificaStatusInternet;
import com.mediquei.helper.Verificacao;
import com.mediquei.model.Endereco;
import com.mediquei.util.UtilEndereco;
import com.mediquei.util.UtilMediquei;
import com.rengwuxian.materialedittext.MaterialEditText;

public class CadastraEnderecoFragment extends Fragment {

    /*Declaracao das Variaveis*/
    private Endereco enderecoCompleto;
    private MaterialEditText editCEP, editLogradouro,
            editNumero, editComplemento, editUF, editCidade, editBairro;

    private Button btnCadastarEndereco;

    private ProgressBar pbCadastraEndereco;

    public CadastraEnderecoFragment() {

    }

    public static CadastraEnderecoFragment newInstance(String entrada){
        CadastraEnderecoFragment f = new CadastraEnderecoFragment();
        Bundle args = new Bundle();
        f.setArguments(args);
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastra_endereco, container, false);

        /*Recuperando Variavel*/
        editCEP = view.findViewById(R.id.editCEPCadastrarEndereco);
        editLogradouro = view.findViewById(R.id.editLogradouroCadastrarEndereco);
        editNumero = view.findViewById(R.id.editNumeroCadastrarEndereco);
        editComplemento = view.findViewById(R.id.editCmpltoCadastrarEndereco);
        editUF = view.findViewById(R.id.editUFCadastrarEndereco);
        editCidade = view.findViewById(R.id.editCidadeCadastrarEndereco);
        editBairro = view.findViewById(R.id.editBairroCadastrarEndereco);

        btnCadastarEndereco = view.findViewById(R.id.btnCadastrarEndereco);

        pbCadastraEndereco = view.findViewById(R.id.progressBarCadastraEndereco);
        pbCadastraEndereco.setVisibility(View.INVISIBLE);

        Bundle args = getArguments();
        if(args != null){
            enderecoCompleto = (Endereco) args.getSerializable("enderecoCompleto");

            /*Coloca Todos os Valores nos seus respectivos Campos*/
            assert enderecoCompleto != null;
            editCEP.setText(enderecoCompleto.getEndCep());
            editLogradouro.setText(enderecoCompleto.getEndLogradouro());
            editUF.setText(enderecoCompleto.getEndUF());
            editCidade.setText(enderecoCompleto.getEndCidade());
            editBairro.setText(enderecoCompleto.getEndBairro());

            /*Define os que possuem valores como não mudaveis*/
            editCEP.setEnabled(false);
            editUF.setEnabled(false);
            editCidade.setEnabled(false);
        }

        btnCadastarEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(VerificaStatusInternet.isOnline(getActivity().getApplicationContext())){
                    /*Definindo os campos a ser cadastrado*/
                    enderecoCompleto.setEndCep(editCEP.getText().toString());
                    enderecoCompleto.setEndUF(editUF.getText().toString());
                    enderecoCompleto.setEndCidade(editCidade.getText().toString());
                    enderecoCompleto.setEndBairro(editBairro.getText().toString());
                    enderecoCompleto.setEndLogradouro(editLogradouro.getText().toString());
                    enderecoCompleto.setEndComplemento(editComplemento.getText().toString());
                    enderecoCompleto.setEndNumero(editNumero.getText().toString());

                    enderecoCompleto = Verificacao.formataEnderecos(getActivity().getApplicationContext(), enderecoCompleto);
                    UtilEndereco.cadastrarEnderecoUsuario task = new UtilEndereco.cadastrarEnderecoUsuario(getActivity().getApplicationContext(), enderecoCompleto, pbCadastraEndereco);
                    task.execute();

                } else {
                    UtilMediquei.showToastError(getActivity().getApplicationContext(), "Não conectado a Internet");
                }

            }
        });
        return view;
    }

}
