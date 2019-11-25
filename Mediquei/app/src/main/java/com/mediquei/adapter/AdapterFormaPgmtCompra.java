package com.mediquei.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mediquei.R;
import com.mediquei.helper.Formatacoes;
import com.mediquei.helper.Verificacao;
import com.mediquei.model.Bancarios;

import java.util.List;

public class AdapterFormaPgmtCompra extends RecyclerView.Adapter<AdapterFormaPgmtCompra.HolderFormaCompra> {

    private List<Bancarios> listaBancarios;
    private FormaPgmentoOnClickListener formaPgtmOnClickListener;

    /*Interface para o Click para excluir o cartão*/
    public interface FormaPgmentoOnClickListener {
        void formaPgmtOnClick(HolderFormaCompra holder, int position);
    }

    /*Construtor*/

    public AdapterFormaPgmtCompra(List<Bancarios> listaBancarios, FormaPgmentoOnClickListener formaPgtmOnClickListener) {
        this.listaBancarios = listaBancarios;
        this.formaPgtmOnClickListener = formaPgtmOnClickListener;
    }

    @NonNull
    @Override
    public HolderFormaCompra onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = null;
        item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_escolher_forma_pagamento, parent, false);
        return new HolderFormaCompra(item);
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderFormaCompra holder, final int position) {
        Bancarios bancarios = listaBancarios.get(position);
        bancarios.setBanIdBandeira(Verificacao.retornImagemEscolhida(bancarios.getBanIdBandeira()));
        holder.imageBandeira.setImageResource(bancarios.getBanIdBandeira());
        holder.textBandeira.setText(Formatacoes.formataTextoCartoesBandeira(bancarios.getBanNumero()));

        if (formaPgtmOnClickListener != null){
            holder.constraintEscolhaFormaPgmt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    formaPgtmOnClickListener.formaPgmtOnClick(holder, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaBancarios != null ? listaBancarios.size() : 0;
    }

    public class HolderFormaCompra extends RecyclerView.ViewHolder {
        private ConstraintLayout constraintEscolhaFormaPgmt;
        private ImageView imageBandeira;
        private TextView textBandeira;
        private CheckBox check;

        public HolderFormaCompra(View itemView) {
            super(itemView);
            imageBandeira = itemView.findViewById(R.id.imageAdapterEscolherFormaPagamento);
            textBandeira = itemView.findViewById(R.id.textApdaterEscolherFormaPgmt);
            constraintEscolhaFormaPgmt = itemView.findViewById(R.id.constraintEscolhaFormaPgmt);
        }
    }
}
