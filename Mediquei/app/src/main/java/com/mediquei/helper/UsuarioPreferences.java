package com.mediquei.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class UsuarioPreferences {
    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;


    private final String CHAVE_ID_USER_PREFENRECE = "valorIdUser";

    private final String CHAVE_BUSCAS_PREFENRECE = "valorBoolBuscas";

    private final String CHAVE_PRIMEIRA_VEZ = "primeiraVez";


    public UsuarioPreferences(Context context) {
        this.context = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        editor = preferences.edit();
    }

    /*IdUsuario*/
    public void salvarIdUsuarioPreferences(int id){
        editor.putInt(CHAVE_ID_USER_PREFENRECE, id);
        editor.commit();
    }

    public int recuperarIdUsuarioPreferences(){
        return  preferences.getInt(CHAVE_ID_USER_PREFENRECE, 0);
    }

    /*Buscas Preferences*/
    public void salvarBuscasPreferences(int id){
        editor.putInt(CHAVE_BUSCAS_PREFENRECE, id);
        editor.commit();
    }

    public int recuperarBuscasPreferences(){
        return  preferences.getInt(CHAVE_BUSCAS_PREFENRECE, 0);
    }

    /*Primeira vez que entrou no App*/
    public void salvarPrimeiraVezPreferences(boolean bool){
        editor.putBoolean(CHAVE_PRIMEIRA_VEZ, bool);
        editor.commit();
    }

    public boolean recuperarPrimeiraVezPreferences(){
        return preferences.getBoolean(CHAVE_PRIMEIRA_VEZ, false);
    }
}
