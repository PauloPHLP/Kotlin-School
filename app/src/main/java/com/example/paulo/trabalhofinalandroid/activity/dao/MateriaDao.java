package com.example.paulo.trabalhofinalandroid.activity.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.paulo.trabalhofinalandroid.activity.model.Materia;
import com.example.paulo.trabalhofinalandroid.activity.model.Professor;

import java.util.ArrayList;

public class MateriaDao extends SQLiteOpenHelper {

    //region Variables

    private static final String NOME_BANCO = "Materia.db";
    private static final int VERSAO = 1;
    private static final String TABELA = "materia";
    private static final String ID = "id";
    private static final String NOME = "nome";
    private static final String AREA = "area";
    private static final String CARGA_HORARIA = "carga";
    private static final String CAMPUS = "campus";
    private static final String PROFESSOR = "professor";

    //endregion

    //region Constructor

    public MateriaDao(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    //endregion

    //region Default methods

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA + " ( " +
                " " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " " + NOME + " TEXT, " + AREA + " TEXT, " +
                " " + CARGA_HORARIA + " TEXT, " + CAMPUS + " TEXT, " + PROFESSOR + " TEXT );";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IS EXISTS " + TABELA;
        db.execSQL(sql);
    }

    //endregion

    //region CRUD methods

    public long cadastrarMateria(Materia m) {
        ContentValues values = new ContentValues();
        long retornoDB;

        values.put(NOME, m.getNome());
        values.put(AREA, m.getArea());
        values.put(CARGA_HORARIA, m.getCargaHoraria());
        values.put(CAMPUS, m.getCampus());
        values.put(PROFESSOR, m.getProfessor());

        retornoDB = getWritableDatabase().insert(TABELA, null, values);

        return retornoDB;
    }

    public ArrayList<Materia> listarMaterias() {
        String[] coluns = {ID, NOME, AREA, CARGA_HORARIA, CAMPUS, PROFESSOR};

        Cursor cursor = getWritableDatabase().query(TABELA, coluns, null, null, null, null, "nome", null);

        ArrayList<Materia> listaMaterias = new ArrayList<>();

        while (cursor.moveToNext()) {
            Materia m = new Materia();

            m.setNome(cursor.getString(1));
            m.setArea(cursor.getString(2));
            m.setCargaHoraria(cursor.getString(3));
            m.setCampus(cursor.getString(4));
            m.setProfessor(cursor.getString(5));

            listaMaterias.add(m);
        }

        return listaMaterias;
    }

    public boolean editarMateria(Materia m) {
        ContentValues values = new ContentValues();

        values.put(NOME, m.getNome());
        values.put(AREA, m.getArea());
        values.put(CARGA_HORARIA, m.getCargaHoraria());
        values.put(CAMPUS, m.getCampus());
        values.put(PROFESSOR, m.getProfessor());

        return getWritableDatabase().update(TABELA, values, "ID=?", new String[]{ ID + "" }) > 0;
    }

    public boolean excluirMateria(int id) {
        return getWritableDatabase().delete(TABELA, "ID=?", new String[]{ id + "" }) > 0;
    }

    //endregion
}