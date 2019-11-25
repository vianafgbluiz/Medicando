package com.mediquei.helper;

import java.util.Random;

public class GeradorIdPedidos {
    public static String geraAleatoria(){
        String letras = "0123456789";
        StringBuilder armazena = new StringBuilder();
        Random random = new Random();
        int index = -1;
        for( int i = 0; i < 8; i++ ) {
            index = random.nextInt( letras.length() );
            armazena.append(letras, index, index + 1);
        }

        return armazena.toString();
    }
}
