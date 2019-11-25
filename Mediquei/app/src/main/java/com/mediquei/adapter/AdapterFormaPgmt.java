package com.mediquei.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mediquei.R;
import com.mediquei.helper.Formatacoes;
import com.mediquei.helper.Verificacao;
import com.mediquei.model.Bancarios;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterFormaPgmt extends RecyclerView.Adapter<AdapterFormaPgmt.HolderPgmt> {

    private List<Bancarios> listaBancarios;
    private FormaPgtmOnClickListener formaPgtmOnClickListener;

    /*Interface para o Click para excluir o cartão*/
    public interface FormaPgtmOnClickListener {
        void formaPgmtOnClick(HolderPgmt holder, int position);
    }

    /*Construtor*/
    public AdapterFormaPgmt(List<Bancarios> listaBancarios, FormaPgtmOnClickListener formaPgtmOnClickListener){
        this.listaBancarios = listaBancarios;
        this.formaPgtmOnClickListener = formaPgtmOnClickListener;
    }

    @NonNull
    @Override
    public HolderPgmt onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = null;
        item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_forma_pgmt_minha_conta, parent, false);
        return new HolderPgmt(item);
    }

    @Override
    public void onBindViewHolder(final HolderPgmt holder, final int position) {
        Bancarios bancarios = listaBancarios.get(position);
        bancarios.setBanIdBandeira(Verificacao.retornImagemEscolhida(bancarios.getBanIdBandeira()));
        holder.imageAdapterBandeira.setImageResource(bancarios.getBanIdBandeira());
        holder.textApdaterFormaPgmt.setText(Formatacoes.formataTextoCartoesBandeira(bancarios.getBanNumero()));

        if (formaPgtmOnClickListener != null) {
            holder.constraintFormaPgmt.setOnClickListener(new View.OnClickListener() {
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

    public class HolderPgmt extends RecyclerView.ViewHolder{

        private ImageView imageAdapterBandeira;
        private TextView textApdaterFormaPgmt;
        private ConstraintLayout constraintFormaPgmt;

        public HolderPgmt(View itemView) {
            super(itemView);
            imageAdapterBandeira = itemView.findViewById(R.id.imageAdapterBandeira);
            textApdaterFormaPgmt = itemView.findViewById(R.id.textApdaterFormaPgmt);
            constraintFormaPgmt = itemView.findViewById(R.id.constraintAdapterFormaPgmt);
        }
    }
}
