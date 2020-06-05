package com.example.paulo.trabalhofinalandroid.activity.activity.professor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.paulo.trabalhofinalandroid.R;

public class ControleDeProfessoresActivity extends AppCompatActivity {

    //region Variables

    ImageButton imgBtnAdicionarProfessor;
    ImageButton imgBtnExcluirProfessor;
    ImageButton imgBtnListarProfessores;
    ImageButton imgBtnEditarProfessor;

    //endregion

    //region onCreate method

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controle_de_professores);

        imgBtnAdicionarProfessor = findViewById(R.id.imgBtnAdicionar);
        imgBtnExcluirProfessor = findViewById(R.id.imgBtnExcluir);
        imgBtnListarProfessores = findViewById(R.id.imgBtnListar);
        imgBtnEditarProfessor = findViewById(R.id.imgBtnEditar);
    }

    //endregion

    //region OnClick methods

    protected void imgBtnAdicionarProfessorOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), AdicionarProfessorActivity.class);

        startActivity(intent);
        Toast.makeText(getApplicationContext(),"Adicionar professor", Toast.LENGTH_SHORT).show();
    }

    protected void imgBtnEditarProfessorOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), EditarProfessorActivity.class);

        startActivity(intent);
        Toast.makeText(getApplicationContext(),"Editar professor", Toast.LENGTH_SHORT).show();
    }

    protected void imgBtnListarProfessoresOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), ListarProfessoresActivity.class);

        startActivity(intent);
        Toast.makeText(getApplicationContext(),"Lista de professores", Toast.LENGTH_SHORT).show();
    }

    protected void imgBtnExcluirProfessorOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), ExcluirProfessorActivity.class);

        startActivity(intent);
        Toast.makeText(getApplicationContext(),"Excluir professor", Toast.LENGTH_SHORT).show();
    }

    //endregion
}