package com.mediquei.activities.introducao;

import android.Manifest;
import android.annotation.SuppressLint;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.mediquei.R;
import com.mediquei.helper.SolicitarPermissao;
import com.mediquei.helper.VerificaStatusInternet;
import com.mediquei.model.Usuario;
import com.mediquei.util.UtilMediquei;
import com.mediquei.util.UtilUsuario;
import com.rengwuxian.materialedittext.MaterialEditText;

public class CadastroActivity extends AppCompatActivity {

    /*Solicitando Permissões*/
    private String[] permission = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    /*Declarando as variáveis*/
    private Usuario usuario;
    private MaterialEditText editNome, editEmail, editSenha;
    private Button btnCadastrar;

    //atributo da classe.
    private AlertDialog alerta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().hide();

        /*Recupero os itens do Layout da Activity*/
        editEmail = findViewById(R.id.editEmailCadastro);
        editSenha = findViewById(R.id.editSenhaCadastro);
        editNome = findViewById(R.id.editNomeCadastro);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        /*Solicitando as permissões necessarias*/
        SolicitarPermissao.validarPermissoes(permission, this, 1);

        /*Setando o OnClick*/
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = editNome.getText().toString();
                String email = editEmail.getText().toString();
                String senha = editSenha.getText().toString();
                if (!nome.isEmpty()){
                    if(!email.isEmpty()){
                        if(!senha.isEmpty()){
                            if(VerificaStatusInternet.isOnline(CadastroActivity.this)){
                                usuario = new Usuario(nome,email, senha);
                                cadastrarUsuarios();
                            }
                        } else {
                            UtilMediquei.showToastShort(CadastroActivity.this, "Digite a Senha!");
                        }
                    } else {
                        UtilMediquei.showToastShort(CadastroActivity.this, "Digite o Email!");
                    }
                } else {
                    UtilMediquei.showToastShort(CadastroActivity.this, "Digite o Nome");
                }

            }
        });
    }

    private void cadastrarUsuarios() {
        UtilUsuario.InsertUsuarioAsyncTask task =
                        new UtilUsuario.InsertUsuarioAsyncTask(CadastroActivity.this, usuario);
        task.execute();
    }

    public void mostrarTermosUso(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater li = getLayoutInflater();
        @SuppressLint("InflateParams") View v = li.inflate(R.layout.layout_termos_uso_dialog, null);

        v.findViewById(R.id.buttonConfirmarTermosDeUso).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alerta.dismiss();
            }
        });

        builder.setView(v);
        alerta = builder.create();
        alerta.show();
    }
}
