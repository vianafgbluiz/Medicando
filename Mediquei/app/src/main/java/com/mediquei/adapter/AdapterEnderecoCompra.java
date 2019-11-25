package com.mediquei.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mediquei.R;
import com.mediquei.helper.Formatacoes;
import com.mediquei.model.Endereco;

import java.util.List;

public class AdapterEnderecoCompra extends RecyclerView.Adapter<AdapterEnderecoCompra.EnderecoCompraHolder> {

    private List<Endereco> listaEndereco;
    private EnderecoCompraOnClickListener enderecoCompraOnClickListener;

    /*Interface para o Click*/
    public interface EnderecoCompraOnClickListener {
        void enderecoOnClick(EnderecoCompraHolder holder, int position);
    }

    public AdapterEnderecoCompra(List<Endereco> listaEndereco, EnderecoCompraOnClickListener enderecoCompraOnClickListener) {
        this.listaEndereco = listaEndereco;
        this.enderecoCompraOnClickListener = enderecoCompraOnClickListener;
    }

    @NonNull
    @Override
    public EnderecoCompraHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_escolher_endereco, parent, false);
        return new EnderecoCompraHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final EnderecoCompraHolder holder, final int position) {
        Endereco endereco = listaEndereco.get(position);
        List<String> formatados = Formatacoes.formataTextoRua(endereco);
        holder.textRua.setText(formatados.get(0));
        holder.textInfo.setText(formatados.get(1));
        holder.textTaxa.setText(endereco.getTaxaCalculada());
        if(enderecoCompraOnClickListener != null){
            holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    enderecoCompraOnClickListener.enderecoOnClick(holder, position);
                    //holder.constraintLayout.setBackgroundColor();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaEndereco != null ? listaEndereco.size() : 0;
    }

    public class EnderecoCompraHolder extends RecyclerView.ViewHolder {
        private TextView textRua, textInfo, textTaxa;
        private ConstraintLayout constraintLayout;

        public EnderecoCompraHolder(View itemView) {
            super(itemView);
            textRua = itemView.findViewById(R.id.textViewRuaAdapter);
            textInfo = itemView.findViewById(R.id.textViewInfoAddAdapter);
            textTaxa = itemView.findViewById(R.id.textTaxaEntregaAdapter);
            constraintLayout = itemView.findViewById(R.id.constraintAdapterEnderecoCompra);
        }
    }
}
