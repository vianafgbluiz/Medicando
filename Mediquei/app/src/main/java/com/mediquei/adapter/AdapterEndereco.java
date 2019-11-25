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
import java.util.Objects;

public class AdapterEndereco extends RecyclerView.Adapter<AdapterEndereco.HolderEndereco> {

    private List<Endereco> listaEndereco;
    private EnderecoOnClickListener enderecoOnClickListener;

    /*Interface para o Click para excluir o cartão*/
    public interface EnderecoOnClickListener {
        void formaPgmtOnClick(HolderEndereco holder, int position);
    }

    public AdapterEndereco(List<Endereco> listaEndereco, EnderecoOnClickListener enderecoOnClickListener) {
        this.listaEndereco = listaEndereco;
        this.enderecoOnClickListener = enderecoOnClickListener;
    }

    @NonNull
    @Override
    public HolderEndereco onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = null;
        item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_endereco_minha_conta, parent, false);
        return new HolderEndereco(item);
    }

    @Override
    public void onBindViewHolder(final HolderEndereco holder, final int position) {
        Endereco endereco = listaEndereco.get(position);
        if(!Objects.equals(endereco.getEndCidade(), " ")){
            holder.textEndereco.setText(Formatacoes.formataTextoEndereco(endereco));
            holder.textEditar.setText("Editar Endereço");
            if (enderecoOnClickListener != null) {
                holder.constraintEndereco.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        enderecoOnClickListener.formaPgmtOnClick(holder, position);
                    }
                });
            }
        } else {
            holder.textEndereco.setText("");
            holder.textEditar.setText("");
        }

    }

    @Override
    public int getItemCount() {
        return listaEndereco != null ? listaEndereco.size() : 0;
    }

    public class HolderEndereco extends RecyclerView.ViewHolder {
        private ConstraintLayout constraintEndereco;
        private TextView textEndereco, textEditar;

        public HolderEndereco(View itemView) {
            super(itemView);
            textEditar = itemView.findViewById(R.id.textEditarEnderecoAdapter);
            textEndereco = itemView.findViewById(R.id.textApdaterEndereco);
            constraintEndereco = itemView.findViewById(R.id.constraintAdapterEndereco);
        }
    }
}
