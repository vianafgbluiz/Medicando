package com.mediquei.model;

public class Buscas {
    private int idBusca;
    private String dscBusca;
    private int recenteBusca;

    /*Construtores*/
    public Buscas() {
    }

    public Buscas(int idBusca, String dscBusca, int recenteBusca) {
        this.idBusca = idBusca;
        this.dscBusca = dscBusca;
        this.recenteBusca = recenteBusca;
    }

    /*Construtor para salvar sem o ID*/

    public Buscas(String dscBusca, int recenteBusca) {
        this.dscBusca = dscBusca;
        this.recenteBusca = recenteBusca;
    }

    /*Getters and Setters*/
    public int getIdBusca() {
        return idBusca;
    }

    public void setIdBusca(int idBusca) {
        this.idBusca = idBusca;
    }

    public String getDscBusca() {
        return dscBusca;
    }

    public void setDscBusca(String dscBusca) {
        this.dscBusca = dscBusca;
    }

    public int getRecenteBusca() {
        return recenteBusca;
    }

    public void setRecenteBusca(int recenteBusca) {
        this.recenteBusca = recenteBusca;
    }
}
