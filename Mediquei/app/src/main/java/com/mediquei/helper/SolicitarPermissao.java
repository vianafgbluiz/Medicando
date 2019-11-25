package com.mediquei.helper;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class SolicitarPermissao {
    public static  boolean validarPermissoes(String[] permissoes, Activity activity, int requestCode){
        if(Build.VERSION.SDK_INT >= 23){
            List<String> listaPermissoes = new ArrayList<>();
            /*Percorrer as permissões passadas verificando uma a uma*/
            for(String permissao : permissoes){
                Boolean temPermissao = ContextCompat.checkSelfPermission(activity, permissao) == PackageManager.PERMISSION_GRANTED;
                if(!temPermissao) listaPermissoes.add(permissao);
            }

            /*Caso a lista esteja vazia, não é necessario solicitar permissao*/
            if (listaPermissoes.isEmpty()) {return true;}

            /*Cria uma nova lista de strings e transforma em um ARRAY*/
            String[] novasPermissoes = new String[listaPermissoes.size()];
            listaPermissoes.toArray(novasPermissoes);

            /*Solicitar Permissao*/
            ActivityCompat.requestPermissions(activity,novasPermissoes,requestCode);
        }
        return true;
    }
}
