package com.example.paulo.trabalhofinalandroid.activity.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.paulo.trabalhofinalandroid.activity.model.Professor;

import java.util.ArrayList;
import java.util.Date;

public class ProfessorDao extends SQLiteOpenHelper {

    //region Variables

    private static final String NOME_BANCO = "Professor.db";
    private static final int VERSAO = 1;
    private static final String TABELA = "professor";
    private static final String ID = "id";
    private static final String NOME = "nome";
    private static final String DATA_NASCIMENTO = "data";
    private static final String LOCAL_NASCIMENTO = "local";
    private static final String ENDERECO = "endereco";
    private static final String EMAIL = "email";
    private static final String TELEFONE = "telefone";
    private static final String FORMACOES = "formacoes";

    //endregion

    //region Constructor

    public ProfessorDao(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    //endregion

    //region Default methods

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA + " ( " +
                " " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " " + NOME + " TEXT, " + DATA_NASCIMENTO + " TEXT, " +
                " " + LOCAL_NASCIMENTO + " TEXT, " + ENDERECO + " TEXT, " +
                " " + EMAIL + " TEXT, " + TELEFONE + " TEXT, " + FORMACOES + " TEXT );";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IS EXISTS " + TABELA;
        db.execSQL(sql);
        onCreate(db);
    }

    //endregion

    //region CRUD methods

    public long cadastrarProfessor(Professor p) {
        ContentValues values = new ContentValues();
        long retornoDB;

        values.put(NOME, p.getNome());
        values.put(DATA_NASCIMENTO, p.getDataDeNascimento());
        values.put(LOCAL_NASCIMENTO, p.getLocalDeNascimento());
        values.put(ENDERECO, p.getEndereco());
        values.put(EMAIL, p.getEmail());
        values.put(TELEFONE, p.getTelefone());
        values.put(FORMACOES, p.getFormacoes());

        retornoDB = getWritableDatabase().insert(TABELA, null, values);

        return retornoDB;
    }

    public ArrayList<Professor> listarProfessores() {
        String[] coluns = {ID, NOME, DATA_NASCIMENTO, LOCAL_NASCIMENTO, ENDERECO, EMAIL, TELEFONE, FORMACOES};

        Cursor cursor = getWritableDatabase().query(TABELA, coluns, null, null, null,null,"nome",null);

        ArrayList<Professor> listaProfessores = new ArrayList<Professor>();

        while (cursor.moveToNext()) {
            Professor p = new Professor();

            p.setNome(cursor.getString(1));
            p.setDataDeNascimento(cursor.getString(2));
            p.setLocalDeNascimento(cursor.getString(3));
            p.setEndereco(cursor.getString(4));
            p.setEmail(cursor.getString(5));
            p.setTelefone(cursor.getString(6));
            p.setFormacoes(cursor.getString(7));

            listaProfessores.add(p);
        }

        return listaProfessores;
    }

    public long editarProfessor(Professor p) {
        ContentValues values = new ContentValues();
        long retornoDB;

        values.put(NOME, p.getNome());
        values.put(DATA_NASCIMENTO, p.getDataDeNascimento());
        values.put(LOCAL_NASCIMENTO, p.getLocalDeNascimento());
        values.put(ENDERECO, p.getEndereco());
        values.put(EMAIL, p.getEmail());
        values.put(TELEFONE, p.getTelefone());
        values.put(FORMACOES, p.getFormacoes());

        String[] args = {String.valueOf(p.getId())};
        retornoDB = getWritableDatabase().update(TABELA, values, "id=?", args);

        return retornoDB;
    }

    public boolean excluirProfessor(int id) {
        return getWritableDatabase().delete(TABELA, "ID=?", new String[]{ id + "" }) > 0;
    }

    //endregion
}