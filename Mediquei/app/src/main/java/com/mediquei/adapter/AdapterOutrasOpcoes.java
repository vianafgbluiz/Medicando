package com.mediquei.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mediquei.R;
import com.mediquei.helper.Formatacoes;
import com.mediquei.model.OutrasOpcoes;

import java.util.ArrayList;
import java.util.List;


public class AdapterOutrasOpcoes extends RecyclerView.Adapter<AdapterOutrasOpcoes.OutrasOpcoesHolder>{

    private List<OutrasOpcoes> outrasOpcoesList;
    private EscolherOnClickListener escolherOnClickListener;

    /*Interface*/
    public interface EscolherOnClickListener {
        void escolherOnClick(OutrasOpcoesHolder holder, int position);
    }

    /*Construtor*/
    public AdapterOutrasOpcoes(List<OutrasOpcoes> outrasOpcoesList, EscolherOnClickListener escolherOnClickListener) {
        this.outrasOpcoesList = outrasOpcoesList;
        this.escolherOnClickListener = escolherOnClickListener;
    }

    @NonNull
    @Override
    public OutrasOpcoesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = null;
        item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_outras_opcoes, parent, false);
        return new OutrasOpcoesHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull final OutrasOpcoesHolder holder, final int position) {
        OutrasOpcoes opcoes = outrasOpcoesList.get(position);

        holder.textNomeFarmacia.setText(opcoes.getNomeFarmacia());
        holder.textValor.setText("R$ " + Formatacoes.formataDuasCasasDecimaisPreco(opcoes.getPrecoMedicamento()));
        if(escolherOnClickListener != null){
            holder.btnEscolher.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    escolherOnClickListener.escolherOnClick(holder, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return outrasOpcoesList != null ? outrasOpcoesList.size() : 0;
    }

    public class OutrasOpcoesHolder extends RecyclerView.ViewHolder {
        private Button btnEscolher;
        private TextView textNomeFarmacia, textValor;

        public OutrasOpcoesHolder(View itemView) {
            super(itemView);
            btnEscolher = itemView.findViewById(R.id.btnEscolherAdapterOpcoes);
            textNomeFarmacia = itemView.findViewById(R.id.textNomeFarmaciaAdapterOpcoes);
            textValor = itemView.findViewById(R.id.textPrecoAdapterOpcoes);
        }
    }
}
