package com.mediquei.activities.introducao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.mediquei.R;
import com.mediquei.activities.primarias.MainActivity;
import com.mediquei.helper.UsuarioPreferences;

public class IntrodutorioActivity extends IntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Verifica se ja logou antes
        UsuarioPreferences idUsuarioPreferences = new UsuarioPreferences(IntrodutorioActivity.this);
        if(idUsuarioPreferences.recuperarIdUsuarioPreferences() != 0){
            chamarMain();
        }*/

        /*Setando a tela in FullScreen e os inativando os botões de back e next*/
        setFullscreen(true);
        setButtonBackVisible(false);
        setButtonNextVisible(false);

        /*Aqui estamos adicionando os Sliders*/
        addSlide(new FragmentSlide.Builder()
                .fragment(R.layout.intro_1)
                .background(R.color.cor_fundo_intro)
                .canGoBackward(false)
                .build()
        );

        addSlide(new FragmentSlide.Builder()
                .fragment(R.layout.intro_2)
                .background(R.color.cor_fundo_intro)
                .build()
        );

        addSlide(new FragmentSlide.Builder()
                .fragment(R.layout.intro_3)
                .background(R.color.cor_fundo_intro)
                .build()
        );

        addSlide(new FragmentSlide.Builder()
                .fragment(R.layout.intro_4)
                .background(R.color.cor_fundo_intro)
                .build()
        );

        addSlide(new FragmentSlide.Builder()
                .fragment(R.layout.intro_cadastro)
                .background(R.color.cor_fundo_intro)
                .canGoForward(false)
                .build()
        );
    }

    private void chamarMain() {
        Intent i = new Intent(IntrodutorioActivity.this, MainActivity.class);
        startActivity(i);
    }

    /*Abre a Tela de Cadastro*/
    public void abrirCadastro(View view) {
        Intent i = new Intent(IntrodutorioActivity.this, CadastroActivity.class);
        startActivity(i);
    }

    /*Abre a Tela de Login*/
    public void abrirLogin(View view) {
        Intent i = new Intent(IntrodutorioActivity.this, LoginActivity.class);
        startActivity(i);
    }
}
