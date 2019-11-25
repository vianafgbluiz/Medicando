package com.mediquei.fragments;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mediquei.R;
import com.mediquei.config.ConfiguracaoFirebase;
import com.mediquei.util.UtilMediquei;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

public class FotoReceitaFragment extends Fragment {

    private ProgressDialog dialog;
    private Button btnTirarFoto, btnEscolherFoto;
    private ImageView image;
    private ProgressBar pb;
    private StorageReference storage;
    private TextView textResultado;
    private String idPedido;

    private static final int SELECAO_CAMERA  = 100;
    private static final int SELECAO_GALERIA = 200;

    private InterfaceComunicacaoFoto listener;

    /*Interface*/
    public interface InterfaceComunicacaoFoto{
        void setUrlLink(String urlLink);
    }

    public FotoReceitaFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof  InterfaceComunicacaoFoto){
            listener = (InterfaceComunicacaoFoto) context;
        } else {
            throw new RuntimeException("Deve implementar Interface");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_foto_receita, container, false);
        btnTirarFoto = view.findViewById(R.id.btnTirarFoto);
        btnEscolherFoto = view.findViewById(R.id.btnEscolheFoto);
        pb = view.findViewById(R.id.progressBarReceitaFoto);
        image = view.findViewById(R.id.backProgressBarReceitaFoto);
        storage = ConfiguracaoFirebase.getFirebaseStorage();
        textResultado = view.findViewById(R.id.textResultadoFotoReceita);

        Bundle data = getArguments();
        if(data != null) {
            idPedido = data.getString("numeroPedido");
            UtilMediquei.showToastSucess(getActivity().getApplicationContext(), "Sucesso " + idPedido);
        }

        btnTirarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchEscolha(1);
            }
        });

        btnEscolherFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchEscolha(2);
            }
        });
        return view;
    }

    public void switchEscolha(int id){
        switch (id){
            case 1:
                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if ( it.resolveActivity(getActivity().getPackageManager()) != null ){
                    startActivityForResult(it, SELECAO_CAMERA );
                }
                break;
            case 2:
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, SELECAO_GALERIA);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK) {
            Bitmap imagem = null;
            try{
                switch (requestCode){
                    case SELECAO_CAMERA:
                        imagem = (Bitmap) data.getExtras().get("data");
                        break;
                    case SELECAO_GALERIA:
                        Uri localImagemSelecionada = data.getData();
                        imagem = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(getActivity()).getContentResolver(), localImagemSelecionada);
                        break;
                }

                enviarImagem(imagem);


            } catch (Exception e){
                e.getStackTrace();
            }
        }
    }

    private void enviarImagem(Bitmap imagem) {
        pb.setVisibility(View.VISIBLE);
        image.setVisibility(View.VISIBLE);
        if(imagem != null){
                    /*Recuperar os dadaos da imagem*/
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imagem.compress(Bitmap.CompressFormat.JPEG, 80, baos);
            byte[] dadosImagens = baos.toByteArray();

                    /*Criando Nó no Storage Reference*/
            StorageReference imagemPedido = storage.child("pedidos").child(idPedido + ".jpeg");

            UploadTask task = imagemPedido.putBytes(dadosImagens);
            task.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri firebaseURL = taskSnapshot.getDownloadUrl();
                    String urlConvertida = firebaseURL.toString();
                    textResultado.setText(R.string.permissao_prosseguir);
                    listener.setUrlLink(urlConvertida);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    textResultado.setText("Falha ao fazer Upload! Tente novamente.");
                }
            });
        }
    }
}
