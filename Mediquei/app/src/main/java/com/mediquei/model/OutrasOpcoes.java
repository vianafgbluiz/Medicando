package com.mediquei.model;

/**
 * Created by viana on 07/11/2018.
 */

public class OutrasOpcoes {
    /*Atributos*/
    private int idFarmacia;
    private String nomeFarmacia;
    private Double precoMedicamento;

    /*Construtor*/
    public OutrasOpcoes() {
    }

    /*Getters and Setters*/
    public String getNomeFarmacia() {
        return nomeFarmacia;
    }

    public void setNomeFarmacia(String nomeFarmacia) {
        this.nomeFarmacia = nomeFarmacia;
    }

    public Double getPrecoMedicamento() {
        return precoMedicamento;
    }

    public void setPrecoMedicamento(Double precoMedicamento) {
        this.precoMedicamento = precoMedicamento;
    }

    public int getIdFarmacia() {
        return idFarmacia;
    }

    public void setIdFarmacia(int idFarmacia) {
        this.idFarmacia = idFarmacia;
    }
}
