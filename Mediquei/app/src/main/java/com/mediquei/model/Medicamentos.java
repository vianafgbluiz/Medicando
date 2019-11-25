package com.mediquei.model;

public class Medicamentos {
    /*Atributos do Banco de Dados*/
    private int medicId;
    private String medicNome;

    /*Construtores*/
    public Medicamentos() {
    }

    public Medicamentos(int medicId, String medicNome) {
        this.medicId = medicId;
        this.medicNome = medicNome;
    }

    /*Getters and Setters*/
    public int getMedicId() {
        return medicId;
    }

    public void setMedicId(int medicId) {
        this.medicId = medicId;
    }

    public String getMedicNome() {
        return medicNome;
    }

    public void setMedicNome(String medicNome) {
        this.medicNome = medicNome;
    }
}
