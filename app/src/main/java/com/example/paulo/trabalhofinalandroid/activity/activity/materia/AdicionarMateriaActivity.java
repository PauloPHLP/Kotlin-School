package com.example.paulo.trabalhofinalandroid.activity.activity.materia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paulo.trabalhofinalandroid.R;
import com.example.paulo.trabalhofinalandroid.activity.dao.MateriaDao;
import com.example.paulo.trabalhofinalandroid.activity.dao.ProfessorDao;
import com.example.paulo.trabalhofinalandroid.activity.model.Materia;
import com.example.paulo.trabalhofinalandroid.activity.model.Professor;

import java.util.ArrayList;

public class AdicionarMateriaActivity extends AppCompatActivity {

    //region Variables

    TextView edtTxtNome;
    TextView edtTxtArea;
    TextView edtTxtCargaHoraria;
    TextView edtTxtCampus;
    Spinner spnProfessor;
    Button btnCancelarMateria;
    Button btnCadastrarMateria;
    Materia materia, materiaAlt;
    MateriaDao materiaDao;
    ProfessorDao professorDao;
    ArrayList<Professor> arrayListProfessores;
    int pos;

    //endregion

    //region onCreate method

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_materia);

        Intent intent = getIntent();
        materiaAlt = (Materia) intent.getSerializableExtra("materia-enviado");
        materia = new Materia();
        materiaDao = new MateriaDao(this);
        professorDao = new ProfessorDao(this);

        edtTxtNome = findViewById(R.id.edtTxtNome);
        edtTxtArea = findViewById(R.id.edtTxtArea);
        edtTxtCargaHoraria = findViewById(R.id.edtTxtCargaHoraria);
        edtTxtCampus = findViewById(R.id.edtTxtCampus);
        spnProfessor = findViewById(R.id.spnProfessor);
        btnCancelarMateria = findViewById(R.id.btnCancelarMateria);
        btnCadastrarMateria = findViewById(R.id.btnCadastrarMateria);

        ArrayAdapter arrayAdapterProfessores = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, professorDao.listarProfessores());
        final ArrayAdapter<Professor> spinnerArrayAdapterProfessores = arrayAdapterProfessores;
        spinnerArrayAdapterProfessores.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnProfessor.setAdapter(spinnerArrayAdapterProfessores);

        spnProfessor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pos = spnProfessor.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //region onCreate method

    //region OnClick methods

    public void btnCancelarOnClick(View view) {
        edtTxtNome.setText("");
        edtTxtArea.setText("");
        edtTxtCargaHoraria.setText("");
        edtTxtCampus.setText("");
    }

    public void btnCadastrarMateriaOnClick(View view) {
        materia.setNome(edtTxtNome.getText().toString());
        materia.setArea(edtTxtArea.getText().toString());
        materia.setCargaHoraria(edtTxtCargaHoraria.getText().toString());
        materia.setCampus(edtTxtCampus.getText().toString());
        materia.setProfessor(arrayListProfessores.get(pos).getNome());
        //arrayListProfessores.get(pos).getNome()

        materiaDao.cadastrarMateria(materia);

        Toast.makeText(getApplicationContext(),"Matéria cadastrada com sucesso!", Toast.LENGTH_LONG).show();

        finish();
    }

    //endregion

    //region CRUD methods

    public void popularListaProfessores() {
        professorDao = new ProfessorDao(this);
        arrayListProfessores = professorDao.listarProfessores();

        professorDao.close();
    }

    @Override
    protected void onResume() {
        super.onResume();

        popularListaProfessores();
    }

    //endregion
}