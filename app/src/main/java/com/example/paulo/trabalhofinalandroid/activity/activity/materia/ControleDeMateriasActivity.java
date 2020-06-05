package com.example.paulo.trabalhofinalandroid.activity.activity.materia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.paulo.trabalhofinalandroid.R;

public class ControleDeMateriasActivity extends AppCompatActivity {

    //region Variables

    ImageButton imgBtnAdicionarMateria;
    ImageButton imgBtnExcluirMateria;
    ImageButton imgBtnListarMaterias;
    ImageButton imgBtnEditarMateria;

    //endregion

    //region onCreate method

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controle_de_materias);

        imgBtnAdicionarMateria = findViewById(R.id.imgBtnAdicionar);
        imgBtnExcluirMateria = findViewById(R.id.imgBtnExcluir);
        imgBtnListarMaterias = findViewById(R.id.imgBtnListarMaterias);
        imgBtnEditarMateria = findViewById(R.id.imgBtnEditar);
    }

    //endregion

    //region OnClick methods

    protected void imgBtnAdicionarMateriaOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), AdicionarMateriaActivity.class);

        startActivity(intent);
        Toast.makeText(getApplicationContext(),"Adicionar matéria", Toast.LENGTH_SHORT).show();
    }

    protected void imgBtnEditarMateriaOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), EditarMateriaActivity.class);

        startActivity(intent);
        Toast.makeText(getApplicationContext(),"Editar matéria", Toast.LENGTH_SHORT).show();
    }

    protected void imgBtnListarMateriasOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), ListarMateriasActivity.class);

        startActivity(intent);
        Toast.makeText(getApplicationContext(),"Lista de matérias", Toast.LENGTH_SHORT).show();
    }

    protected void imgBtnExcluirMateriaOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), ExcluirMateriaActivity.class);

        startActivity(intent);
        Toast.makeText(getApplicationContext(),"Excluir matéria", Toast.LENGTH_SHORT).show();
    }

    //endregion
}