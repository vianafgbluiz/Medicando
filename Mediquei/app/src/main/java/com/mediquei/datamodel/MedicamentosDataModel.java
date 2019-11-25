package com.mediquei.datamodel;

public class MedicamentosDataModel {

    /* Classe para criar a tabela SQLite e também para
     * devolver os IDS da tabela do banco de Dados Externo
     * Dados da Clase Medicamentos
     */

    private final static String idMedic = "medic_id";
    private final static String nome = "medic_nome";

    /*Getters*/
    public static String getIdMedic() {
        return idMedic;
    }
    public static String getNome() {
        return nome;
    }
}
