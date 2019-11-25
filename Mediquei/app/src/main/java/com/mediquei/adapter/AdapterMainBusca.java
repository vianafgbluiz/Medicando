package com.mediquei.adapter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mediquei.R;
import com.mediquei.model.Buscas;

import java.util.List;

public class AdapterMainBusca extends RecyclerView.Adapter<AdapterMainBusca.HolderBusca> {

    private List<Buscas> listaBuscas;
    private BuscasMainOnClickListener buscasMainOnClickListener;

    /*Interface para o Click para excluir o cartão*/
    public interface BuscasMainOnClickListener {
        void buscasMainOnClick(HolderBusca holder, int position);
    }

    public List<Buscas> getListaBuscas() {
        return listaBuscas;
    }

    /*Construtor*/
    public AdapterMainBusca(List<Buscas> listaBuscas, BuscasMainOnClickListener buscasMainOnClickListener) {
        this.listaBuscas = listaBuscas;
        this.buscasMainOnClickListener = buscasMainOnClickListener;
    }

    @Override
    public HolderBusca onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = null;
        item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_main_buscar, parent, false);
        return new HolderBusca(item);
    }

    @Override
    public void onBindViewHolder(final HolderBusca holder,final int position) {
        Buscas busca = listaBuscas.get(position);

        holder.textBusca.setText(busca.getDscBusca());
        if(busca.getRecenteBusca() == 0){
            holder.imageBuscaSugestao.setImageResource(R.drawable.main_buscas_sugestao);
        } else {
            holder.textSugestao.setVisibility(View.GONE);
            holder.imageBuscaSugestao.setImageResource(R.drawable.main_busca_recentes);
        }

        if (buscasMainOnClickListener != null) {
            holder.constraintMainBuscar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    buscasMainOnClickListener.buscasMainOnClick(holder, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaBuscas != null ? listaBuscas.size() : 0;
    }

    public class HolderBusca extends RecyclerView.ViewHolder{
        private ImageView imageBuscaSugestao;
        private TextView textBusca, textSugestao;
        private ConstraintLayout constraintMainBuscar;

        public HolderBusca(View itemView) {
            super(itemView);
            imageBuscaSugestao = itemView.findViewById(R.id.imageBuscaSugestao);
            textBusca = itemView.findViewById(R.id.textMainBusca);
            textSugestao = itemView.findViewById(R.id.textSugestao);
            constraintMainBuscar = itemView.findViewById(R.id.constraintMainBuscar);
        }
    }
}
