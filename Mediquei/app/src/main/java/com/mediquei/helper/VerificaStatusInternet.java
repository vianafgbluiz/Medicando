package com.mediquei.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.mediquei.util.UtilMediquei;

public class VerificaStatusInternet {
    public static boolean isOnline(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = null;
            if (cm != null) {
                netInfo = cm.getActiveNetworkInfo();
            }
            return netInfo != null && netInfo.isConnectedOrConnecting();
        }
        catch(Exception ex){
            UtilMediquei.showToastShort(context, "Erro ao verificar conexão com a Internet!");
            return false;
        }
    }
}
