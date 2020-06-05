package com.example.paulo.trabalhofinalandroid.activity.activity.materia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.paulo.trabalhofinalandroid.R;
import com.example.paulo.trabalhofinalandroid.activity.adapter.AdapterMaterias;
import com.example.paulo.trabalhofinalandroid.activity.adapter.AdapterProfessores;
import com.example.paulo.trabalhofinalandroid.activity.dao.MateriaDao;
import com.example.paulo.trabalhofinalandroid.activity.dao.ProfessorDao;
import com.example.paulo.trabalhofinalandroid.activity.model.Materia;
import com.example.paulo.trabalhofinalandroid.activity.model.Professor;

import java.util.ArrayList;

public class EditarMateriaActivity extends AppCompatActivity {

    //region Variables

    Spinner spnListaMaterias;
    TextView edtTxtNome;
    TextView edtTxtArea;
    TextView edtTxtCargaHoraria;
    TextView edtTxtCampus;
    Spinner spnProfessor;
    MateriaDao materiaDao;
    ArrayList<Materia> arrayListMaterias;
    ProfessorDao professorDao;
    ArrayList<Professor> arrayListProfessores;
    Materia materiaEditada;

    //endregion

    //region onCreate method

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_materia);

        materiaDao = new MateriaDao(this);
        professorDao = new ProfessorDao(this);
        spnListaMaterias = findViewById(R.id.spnListaMaterias);
        edtTxtNome = findViewById(R.id.edtTxtNome);
        edtTxtArea = findViewById(R.id.edtTxtArea);
        edtTxtCargaHoraria = findViewById(R.id.edtTxtCargaHoraria);
        edtTxtCampus = findViewById(R.id.edtTxtCampus);
        spnProfessor = findViewById(R.id.spnProfessor);

        materiaEditada = new Materia();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, materiaDao.listarMaterias());
        final ArrayAdapter<Materia> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnListaMaterias.setAdapter(spinnerArrayAdapter);

        spnListaMaterias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                edtTxtNome.setText(arrayListMaterias.get(position).getNome());
                edtTxtArea.setText(arrayListMaterias.get(position).getArea());
                edtTxtCargaHoraria.setText(arrayListMaterias.get(position).getCargaHoraria());
                edtTxtCampus.setText(arrayListMaterias.get(position).getCampus());
                //materiaEditada.setId(arrayListMaterias.get(position).getId());
                materiaEditada.setNome(edtTxtNome.getText().toString());
                materiaEditada.setArea(edtTxtArea.getText().toString());
                materiaEditada.setCargaHoraria(edtTxtCargaHoraria.getText().toString());
                materiaEditada.setCampus(edtTxtCampus.getText().toString());
                materiaEditada.setProfessor("professor");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter arrayAdapterProfessores = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, professorDao.listarProfessores());
        final ArrayAdapter<Professor> spinnerArrayAdapterProfessores = arrayAdapterProfessores;
        spinnerArrayAdapterProfessores.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnProfessor.setAdapter(spinnerArrayAdapterProfessores);

        spnProfessor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //endregion

    //region OnClick methods

    public void btnEditarMateriaOnClick(View view) {
        MateriaDao materiaDao = new MateriaDao(getBaseContext());
        materiaDao.editarMateria(materiaEditada);
//        AdapterMaterias adapterMaterias = new AdapterMaterias(arrayListMaterias);
//        adapterMaterias.atualizarMateria(materiaEditada);
    }

    //endregion

    //region CRUD methods

    public void popularListaProfessores() {
        professorDao = new ProfessorDao(this);
        arrayListProfessores = professorDao.listarProfessores();

        professorDao.close();
    }

    public void popularListaMaterias() {
        materiaDao = new MateriaDao(this);

        arrayListMaterias = materiaDao.listarMaterias();
        materiaDao.close();
    }

    @Override
    protected void onResume() {
        super.onResume();

        popularListaMaterias();
        popularListaProfessores();
    }

    //endregion
}