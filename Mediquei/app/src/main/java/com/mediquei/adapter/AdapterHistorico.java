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
import com.mediquei.controller.PedidosController;
import com.mediquei.helper.Formatacoes;
import com.mediquei.model.Pedidos;

import java.util.EventListener;
import java.util.List;

public class AdapterHistorico extends RecyclerView.Adapter<AdapterHistorico.HolderHistorico> {

    private List<Pedidos> pedidosList;
    private HistoricoOnClickListener historicoOnClickListener;

    /*Interface*/
    public interface HistoricoOnClickListener {
        void historicoOnClick(HolderHistorico holder, int position);
    }

    public AdapterHistorico(List<Pedidos> pedidosList, HistoricoOnClickListener historicoOnClickListener) {
        this.pedidosList = pedidosList;
        this.historicoOnClickListener = historicoOnClickListener;
    }

    @NonNull
    @Override
    public HolderHistorico onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_historico_pedido, parent, false);
        return new HolderHistorico(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderHistorico holder, final int position) {
        Pedidos pedido = pedidosList.get(position);
        holder.textPedido.setText("Nº Pedido: " + pedido.getPedId());
        holder.textStatus.setText("Status: " + PedidosController.retornaStatusPedido(pedido.getPedIdSituacao()));
        holder.textValor.setText("R$ " + Formatacoes.formataDuasCasasDecimaisPreco(pedido.getPedValorTotal()));
        if(pedido.getPedIdSituacao() == 4){
            holder.textDataEntregue.setText("Entregue em: " + pedido.getPedDataEntregue());
        } else {
            holder.textDataEntregue.setText("");
        }

        if(historicoOnClickListener != null){
            holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    historicoOnClickListener.historicoOnClick(holder, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return pedidosList != null ? pedidosList.size() : 0;
    }

    public class HolderHistorico extends RecyclerView.ViewHolder {
        private TextView textPedido, textStatus, textDataEntregue, textValor;
        private ConstraintLayout constraintLayout;
        private ImageView image;

        public HolderHistorico(View itemView) {
            super(itemView);
            textPedido = itemView.findViewById(R.id.textNumeroPedido);
            textStatus = itemView.findViewById(R.id.textStatusHistorico);
            textDataEntregue = itemView.findViewById(R.id.textDataEntrega);
            textValor = itemView.findViewById(R.id.textValorHistorico);

            constraintLayout = itemView.findViewById(R.id.constraintHistorico);

            image = itemView.findViewById(R.id.imageRemedioHistorico);
        }
    }
}
