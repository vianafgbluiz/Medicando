package com.mediquei.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.mediquei.R;
import com.mediquei.helper.Formatacoes;
import com.mediquei.model.ResultadoBusca;
import com.mediquei.model.mDescricao;

import java.util.List;

public class AdapterResultadoBusca extends RecyclerView.Adapter<AdapterResultadoBusca.HolderResultado> {

    private List<ResultadoBusca> listaResultados;
    private ResultadosBuscasOnClickListener resultadosBuscasOnClickListener;

    /*Interface para o Click para excluir o cartão*/
    public interface ResultadosBuscasOnClickListener {
        void resultadoBuscaOnClick(HolderResultado holder, int position);
    }

    /*Construtor*/
    public AdapterResultadoBusca(List<ResultadoBusca> listaResultados, ResultadosBuscasOnClickListener resultadosBuscasOnClickListener) {
        this.listaResultados = listaResultados;
        this.resultadosBuscasOnClickListener = resultadosBuscasOnClickListener;
    }

    @Override
    public HolderResultado onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_resultados_busca, parent, false);
        return new HolderResultado(item);
    }

    @Override
    public void onBindViewHolder(final HolderResultado holder, final int position) {
        ResultadoBusca descricao = listaResultados.get(position);
        holder.textNome.setText(descricao.getMedicNome());
        holder.textDescricao.setText(descricao.getDescDes());
        holder.textQuantidade.setText(descricao.getDescQuantidade());
        holder.btnValor.setText("R$ " + Formatacoes.formataDuasCasasDecimaisPreco(descricao.getpPreco()));
        if(!descricao.getDescReceita().equals("S")){
            holder.textReceita.setVisibility(View.GONE);
        }

        if(resultadosBuscasOnClickListener != null){
            holder.btnValor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    resultadosBuscasOnClickListener.resultadoBuscaOnClick(holder, position);
                }
            });
        }

        holder.lottieFavoritar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.lottieFavoritar.playAnimation();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaResultados != null ? listaResultados.size() : 0;
    }

    public class HolderResultado extends RecyclerView.ViewHolder {
        /*Atributos*/
        private TextView textNome, textDescricao, textQuantidade, textReceita;
        private Button btnValor;
        private LottieAnimationView lottieFavoritar;

        public HolderResultado(View itemView) {
            super(itemView);
            textNome = itemView.findViewById(R.id.textNomeRemedioResultado);
            textDescricao = itemView.findViewById(R.id.textDescricaoRemedioResultado);
            textQuantidade = itemView.findViewById(R.id.textQuantidadeRemedioResultados);
            textReceita = itemView.findViewById(R.id.textReceitaObrigatoriaResultado);
            btnValor = itemView.findViewById(R.id.btnValorRemedioResultado);
            lottieFavoritar = itemView.findViewById(R.id.favoritarResultadoAnimation);
        }
    }
}
