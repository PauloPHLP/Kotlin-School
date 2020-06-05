package com.example.paulo.trabalhofinalandroid.activity.activity.professor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterViewFlipper;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paulo.trabalhofinalandroid.R;
import com.example.paulo.trabalhofinalandroid.activity.adapter.AdapterProfessores;
import com.example.paulo.trabalhofinalandroid.activity.dao.ProfessorDao;
import com.example.paulo.trabalhofinalandroid.activity.model.Professor;

import java.util.ArrayList;

public class EditarProfessorActivity extends AppCompatActivity {

    //region Variables

    Spinner spnListaProfessores;
    TextView edtTxtNome;
    TextView edtTxtData;
    TextView edtTxtNascimento;
    TextView edtTxtEndereco;
    TextView edtTextEmailProfessor;
    TextView edtTxtTelefoneProfessor;
    TextView edtTxtFormacoesProfessor;
    Button btnCadastrarProfessor;
    long retornoDB;
    Professor professorEditado;
    ProfessorDao professorDao;
    ArrayList<Professor> arrayListProfessores;

    //endregion

    //region onCreate method

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_professor);

        professorDao = new ProfessorDao(this);
        spnListaProfessores = findViewById(R.id.spnListaProfessores);
        edtTxtNome = findViewById(R.id.edtTxtNome);
        edtTxtData = findViewById(R.id.edtTxtData);
        edtTxtNascimento = findViewById(R.id.edtTxtNascimento);
        edtTxtEndereco = findViewById(R.id.edtTxtEndereco);
        edtTextEmailProfessor = findViewById(R.id.edtTextEmailProfessor);
        edtTxtTelefoneProfessor = findViewById(R.id.edtTxtTelefoneProfessor);
        edtTxtFormacoesProfessor = findViewById(R.id.edtTxtFormacoesProfessor);
        btnCadastrarProfessor = findViewById(R.id.btnCadastrarProfessor);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, professorDao.listarProfessores());
        final ArrayAdapter<Professor> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnListaProfessores.setAdapter(spinnerArrayAdapter);

        spnListaProfessores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                professorEditado = arrayListProfessores.get(position);
                edtTxtNome.setText(arrayListProfessores.get(position).getNome());
                edtTxtData.setText(arrayListProfessores.get(position).getDataDeNascimento());
                edtTxtNascimento.setText(arrayListProfessores.get(position).getLocalDeNascimento());
                edtTxtEndereco.setText(arrayListProfessores.get(position).getEndereco());
                edtTextEmailProfessor.setText(arrayListProfessores.get(position).getEmail());
                edtTxtTelefoneProfessor.setText(arrayListProfessores.get(position).getTelefone());
                edtTxtFormacoesProfessor.setText(arrayListProfessores.get(position).getFormacoes());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //endregion

    //region OnClick methods

    public void btnEditarProfessorOnClick(View view) {
        retornoDB = professorDao.editarProfessor(professorEditado);
        professorDao.close();
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