package com.mediquei.activities.introducao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import com.mediquei.R;
import com.mediquei.activities.primarias.MainActivity;
import com.mediquei.controller.BuscasController;
import com.mediquei.datamodel.BuscasDataModel;
import com.mediquei.helper.UsuarioPreferences;
import com.mediquei.util.UtilBuscas;

public class SplashScreen extends AppCompatActivity {
    private ProgressBar progressBar;
    private UsuarioPreferences up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        /*Declaração das Variáveis*/
        progressBar = findViewById(R.id.progressBarSplashScreen);
        up = new UsuarioPreferences(SplashScreen.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*Verifica se é a primeira vez que a pessoa entrou no app*/
        if(!up.recuperarPrimeiraVezPreferences()){
            //Log.d("PrimeiraVez", "Primeira Vez --> SIM " );
//            BuscasController controller = new BuscasController(SplashScreen.this);
//            controller.criarTabela(BuscasDataModel.criarTabela());

            /*Salva que foi executado*/
            up.salvarPrimeiraVezPreferences(true);

            /*Buscar os Dados*/
//            UtilBuscas.BuscarNomeMedicamentoAsyncTask task =
//                    new UtilBuscas.BuscarNomeMedicamentoAsyncTask(progressBar,SplashScreen.this);
//            task.execute();
        } else {
            //Log.d("PrimeiraVez", "Primeira Vez --> NAO " );
            if(up.recuperarIdUsuarioPreferences() != 0){
                //Log.d("PrimeiraVez", "ID --> DIFERENTE DE ZERO" );
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
            } else {
                //Log.d("PrimeiraVez", "ID --> IGUAL ZERO" );
                startActivity(new Intent(SplashScreen.this, IntrodutorioActivity.class));
            }
        }
    }
}
