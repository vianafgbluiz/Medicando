package com.mediquei.controller;

public class StatusPedidoController {
    public final String EFETUADO = "Pedido Efetuado";
    public final String AUTORIZADO = "Pedido Autorizado";
    public final String EM_TRANSPORTE = "Pedido em Transporte";
    public final String ENTREGUE = "Pedido Entregue";
    public final String NAO_AUTORIZADO = "Pedido Não Autorizado";
    public final String CANCELADO = "Pedido Cancelado";
    public final String NAO_ENTREGUE = "Pedido Não Entregue";
    public final String AGUARDANDO_RETIRADA = "Pedido Aguardando Retirada";

    public StatusPedidoController() {
    }

    public String getEFETUADO() {
        return EFETUADO;
    }

    public String getAUTORIZADO() {
        return AUTORIZADO;
    }

    public String getEM_TRANSPORTE() {
        return EM_TRANSPORTE;
    }

    public String getENTREGUE() {
        return ENTREGUE;
    }

    public String getNAO_AUTORIZADO() {
        return NAO_AUTORIZADO;
    }

    public String getCANCELADO() {
        return CANCELADO;
    }

    public String getNAO_ENTREGUE() {
        return NAO_ENTREGUE;
    }

    public String getAGUARDANDO_RETIRADA() {
        return AGUARDANDO_RETIRADA;
    }
}
