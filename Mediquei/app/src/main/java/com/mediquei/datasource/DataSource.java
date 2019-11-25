package com.mediquei.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mediquei.datamodel.BuscasDataModel;
import com.mediquei.model.Buscas;

import java.util.ArrayList;
import java.util.List;

public class DataSource extends SQLiteOpenHelper {

    private static final String DB_NAME = "bd_mediquei.sqlite";
    private static final int DB_VERSION = 1;

    Cursor cursor;
    SQLiteDatabase db;

    public DataSource(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
            db.execSQL(BuscasDataModel.criarTabela());
        } catch (Exception e){
            Log.d("SqLite", "DB ---> ERRO: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /*Criar e Deletar Tabela*/
    public void deletarTabela(String tabela) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + tabela);
        }catch (Exception e){

        }
    }

    public void criarTabela(String queryCriarTabela) {
        try {
            db.execSQL(queryCriarTabela);
        } catch (SQLiteCantOpenDatabaseException e) {
        }
    }

    public boolean insert(String tabela, ContentValues dados){

        boolean sucesso = true;
        try {
            sucesso = db.insert(tabela, null, dados) > 0;
        }catch (Exception e){
            sucesso = false;
            Log.d("SQLite", "Erro --> " + e.getMessage() );
        }
        return sucesso;
    }

    public boolean update(String tabela, ContentValues dados, String medicamento){

        boolean sucesso = true;


        sucesso = db.update(tabela, dados, "busca_dsc = ?",
                new String[]{medicamento}) > 0;

        Log.d("Sucesso", "update: " + String.valueOf(sucesso));

        return sucesso;
    }


    public List<Buscas> getBuscasIniciais(){
        Buscas obj;
        // TIPADA
        List<Buscas> lista = new ArrayList<>();

        String sql = "SELECT * FROM " + BuscasDataModel.getTABELA() +
                        " b  ORDER BY b.busca_recente DESC, b.busca_dsc LIMIT 7";
        Log.d("Log", "getBuscasIniciais: " + sql);
        cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                obj = new Buscas();
                obj.setIdBusca(cursor.getInt(cursor.getColumnIndex(BuscasDataModel.getIdBusca())));
                obj.setDscBusca(cursor.getString(cursor.getColumnIndex(BuscasDataModel.getDscBusca())));
                obj.setRecenteBusca(cursor.getInt(cursor.getColumnIndex(BuscasDataModel.getRecenteBusca())));
                lista.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }

    public List<Buscas> getAllBuscas() {
        Buscas obj;
        // TIPADA
        List<Buscas> lista = new ArrayList<>();

        String sql = "SELECT * FROM " + BuscasDataModel.getTABELA()
                + " ORDER BY busca_dsc";
        cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                obj = new Buscas();
                obj.setIdBusca(cursor.getInt(cursor.getColumnIndex(BuscasDataModel.getIdBusca())));
                obj.setDscBusca(cursor.getString(cursor.getColumnIndex(BuscasDataModel.getDscBusca())));
                obj.setRecenteBusca(cursor.getInt(cursor.getColumnIndex(BuscasDataModel.getRecenteBusca())));
                lista.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }
}
