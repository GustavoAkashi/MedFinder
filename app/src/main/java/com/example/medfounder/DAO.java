package com.example.medfounder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.medfounder.objetos.hospBD;

import java.util.ArrayList;
import java.util.List;

public class DAO extends SQLiteOpenHelper {

    public DAO (Context context) {
        super(context, "banco",null,3);
    } // declara o nome do banco de dados, e a versão (IMPORTANTE: quando vc atualiza a versão, todos os dados contidos nas versões anteriores são deletados)

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE hospital(convenio TEXT, nome TEXT, endereco TEXT, imagem INTEGER);"; //cria a table hosptital dentro do banco de dados com o nome "banco"
        db.execSQL(sql); // diz que essa string é um comando de sql

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { // checa a versão e caso ela for nova deleta os dados da tabela anterior, assim começando uma nova
        String sql = "DROP TABLE IF EXISTS hospital;";
        db.execSQL(sql);
        onCreate(db);
    }


    // método que insere os hospitais no banco de dados (activity listabd)
    public void insereHospital(hospBD hospBD) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("convenio", hospBD.getConvenio());
        dados.put("nome", hospBD.getNome());
        dados.put("endereco", hospBD.getEndereco());

        db.insert("hospital", null, dados);
    }

    // busca os hospitais para mostrar na tela (activity listabd) --- não sei se realmente é necessário
    public List<hospBD> buscaHospital() {

        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM hospital;";
        Cursor c = db.rawQuery(sql, null);

        List<hospBD> hospbds = new ArrayList<hospBD>();

        while (c.moveToNext()) {
            hospBD hospBD = new hospBD();
            hospBD.setConvenio(c.getString(c.getColumnIndex("convenio")));
            hospBD.setNome(c.getString(c.getColumnIndex("nome")));
            hospBD.setEndereco(c.getString(c.getColumnIndex("endereco")));
            hospbds.add(hospBD);
        }
        return hospbds;

    }


    // busca as informações dos hospitais para colocar dentro do array de hospitais (activity busca) - tem como parametro o convênio desejado, porém pode conter mais condições
    public List<hospBD> buscaHospInf(String convenio) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM hospital WHERE convenio = '"+convenio+"';";
        Cursor c = db.rawQuery(sql, null);

        List<hospBD> hospnome = new ArrayList<hospBD>();

        // percorre os dados enquanto é possível
        while (c.moveToNext()) {
            hospBD hospBD = new hospBD();
            hospBD.setNome(c.getString(c.getColumnIndex("nome")));
            hospBD.setEndereco(c.getString(c.getColumnIndex("endereco")));
            hospnome.add(hospBD);
        }
        return hospnome;
    }
}
