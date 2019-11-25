package com.mediquei.activities.secundarias;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.mediquei.R;
import com.mediquei.helper.UsuarioPreferences;
import com.mediquei.helper.VerificaStatusInternet;
import com.mediquei.helper.Verificacao;
import com.mediquei.model.Bancarios;
import com.mediquei.util.UtilBancarios;
import com.mediquei.util.UtilBuscas;
import com.mediquei.util.UtilMediquei;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.santalu.maskedittext.MaskEditText;

import java.util.Objects;

public class AdicionarFormaPgmtActivity extends AppCompatActivity {

    private ProgressBar pbCadastraFormaPgmt;
    private Button btnCadastrarFormaPgmt;
    private MaskEditText  editCPF, editValidade, editCVV, editNumero;
    private EditText editNome;
    private int idUsuario;
    private UsuarioPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_forma_pgmt);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Forma de Pagamento");

        /*Recupera as Variaveis*/
        btnCadastrarFormaPgmt = findViewById(R.id.btnCadastrarFormaPgmt);
        editNome = findViewById(R.id.editNomeCartaoFormaPgmtReal);
        editCPF = findViewById(R.id.editCPFCartaoFormaPgmt);
        editNumero = findViewById(R.id.editNumeroCartaoFormaPgmt);
        editValidade = findViewById(R.id.editValidadeCartaoFormaPgmt);
        editCVV = findViewById(R.id.editCVVCartaoFormaPgmt);
        pbCadastraFormaPgmt = findViewById(R.id.progressBarCadastrarFormaPgmt);

        /*Setando a Visibilidade*/
        pbCadastraFormaPgmt.setVisibility(View.INVISIBLE);

        /*Recuperando o Id do Usuarios*/
        preferences = new UsuarioPreferences(AdicionarFormaPgmtActivity.this);
        idUsuario = preferences.recuperarIdUsuarioPreferences();
        
        btnCadastrarFormaPgmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(VerificaStatusInternet.isOnline(AdicionarFormaPgmtActivity.this)){
                    Bancarios bancarios = new Bancarios();

                    bancarios.setBanIdUsuario(idUsuario);
                    bancarios.setBanNomeCartao(editNome.getText().toString());
                    bancarios.setBanCPF(editCPF.getRawText());
                    bancarios.setBanNumero(editNumero.getRawText());
                    bancarios.setBanValidade(editValidade.getRawText());
                    bancarios.setBanCodSeguranca(editCVV.getRawText());


                    if(Verificacao.verificaFormaPgmtPreenchido(bancarios)){
                        bancarios = Verificacao.formataCamposBancarios(bancarios);
                        UtilBancarios.cadastrarFormaPgmtUsuario task = new
                                UtilBancarios.cadastrarFormaPgmtUsuario(AdicionarFormaPgmtActivity.this, bancarios, pbCadastraFormaPgmt);
                        task.execute();
                    } else {
                        UtilMediquei.showToastError(AdicionarFormaPgmtActivity.this, "Preencha todos os campos corretamente!");
                    }
                    
                } else {
                    UtilMediquei.showToastInfo(AdicionarFormaPgmtActivity.this, "Não há conexão com a Internet!");
                }
            }
        });
    }

    //Volta para a activity anterior
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return true;
    }
}
