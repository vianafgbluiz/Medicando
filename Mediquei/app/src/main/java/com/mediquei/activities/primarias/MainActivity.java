package com.mediquei.activities.primarias;

import android.Manifest;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.mediquei.R;
import com.mediquei.activities.introducao.IntrodutorioActivity;
import com.mediquei.activities.secundarias.AdicionarLembreteActivity;
import com.mediquei.controller.BuscasController;
import com.mediquei.datamodel.BuscasDataModel;
import com.mediquei.fragments.MainBuscaFragment;
import com.mediquei.fragments.MainDescontoFragment;
import com.mediquei.helper.UsuarioPreferences;
import com.mediquei.helper.SolicitarPermissao;
import com.mediquei.model.Buscas;
import com.mediquei.util.UtilBuscas;
import com.mediquei.util.UtilMediquei;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    /*Solicitando Permissões*/
    private String[] permission = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA
    };

    /*Declarando o SearchView e o ImageButton Filtro*/
    private SearchView svMain;
    private ImageView imgReload;


    /*Manipulacao de Fragments*/
    android.support.v4.app.FragmentManager fragmentManager;

    /*Fragment de Busca*/
    private MainBuscaFragment mainBuscaFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        /*Solicitando as permissões necessarias*/
        SolicitarPermissao.validarPermissoes(permission, this, 1);

        /*Recuperando o SearchView e o ImageButton Filtro*/
        svMain = findViewById(R.id.searchViewMain);
        imgReload = findViewById(R.id.imgFiltrarMain);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.containerPrincipal, new MainDescontoFragment());
        transaction.commit();

        svMain.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainBuscaFragment = new MainBuscaFragment();
                fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.add(R.id.containerPrincipal, mainBuscaFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        svMain.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                svMain.clearFocus();
                svMain.onActionViewCollapsed();
                fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.containerPrincipal, new MainDescontoFragment());
                transaction.commit();
                return true;
            }
        });



        svMain.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText != null && !newText.isEmpty()) {
                    mainBuscaFragment.pesquisarRemedio(newText);
                }
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finishAffinity();
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_config) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id_nav = item.getItemId();

        switch (id_nav){
            case R.id.nav_inicio:
                break;
            case R.id.nav_minha_conta:
                startActivity(new Intent(MainActivity.this, MinhaContaActivity.class));
                break;
            case R.id.nav_cesta_compras:
                startActivity(new Intent(MainActivity.this, CestaComprasActivity.class));
                break;
            case R.id.nav_historico:
                startActivity(new Intent(MainActivity.this, HistoricoPedidoActivity.class));
                break;
            case R.id.nav_lembretes:
                startActivity(new Intent(MainActivity.this, AdicionarLembreteActivity.class));
                break;
            case R.id.nav_lojas_parceiras:
                break;
            case R.id.nav_central:
                break;
            case R.id.nav_sair:
                new UsuarioPreferences(MainActivity.this).salvarIdUsuarioPreferences(0);
                startActivity(new Intent(MainActivity.this, IntrodutorioActivity.class));
                finish();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void reloadMedicamentos(View view) {
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Deseja atualizar os medicamentos?")
                .setMessage("Essa ação remove as suas buscas recentes.")
                .setPositiveButton("CONFIRMAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UtilBuscas.BuscarNomeMedicamentoReloadAsyncTask task =
                                new UtilBuscas.BuscarNomeMedicamentoReloadAsyncTask(Objects.requireNonNull(MainActivity.this));
                        task.execute();
                    }
                })
                .setNegativeButton("CANCELAR", null)
                .show();

    }
}
