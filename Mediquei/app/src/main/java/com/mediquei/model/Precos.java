package com.mediquei.model;

public class Precos {
    /*Atributos do DB*/
    private int pMedicamento;
    private int pFarmacia;
    private Double pPreco;

    /*Contrutores*/
    public Precos() {
    }

    public Precos(int pMedicamento, int pFarmacia, Double pPreco) {
        this.pMedicamento = pMedicamento;
        this.pFarmacia = pFarmacia;
        this.pPreco = pPreco;
    }

    /*Getters and Setters*/

    public int getpMedicamento() {
        return pMedicamento;
    }

    public void setpMedicamento(int pMedicamento) {
        this.pMedicamento = pMedicamento;
    }

    public int getpFarmacia() {
        return pFarmacia;
    }

    public void setpFarmacia(int pFarmacia) {
        this.pFarmacia = pFarmacia;
    }

    public Double getpPreco() {
        return pPreco;
    }

    public void setpPreco(Double pPreco) {
        this.pPreco = pPreco;
    }
}
