package com.example.paulo.trabalhofinalandroid.activity.activity.materia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.paulo.trabalhofinalandroid.R;

public class DetalhesMateriaActivity extends AppCompatActivity {

    TextView edtTxtNome;
    TextView edtTxtArea;
    TextView edtTxtCargaHoraria;
    TextView edtTxtCampus;
    TextView edtTxtProfessor;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_materia);

        intent = getIntent();

        edtTxtNome = findViewById(R.id.edtTxtNome);
        edtTxtArea = findViewById(R.id.edtTxtArea);
        edtTxtCargaHoraria = findViewById(R.id.edtTxtCargaHoraria);
        edtTxtCampus = findViewById(R.id.edtTxtCampus);
        edtTxtProfessor = findViewById(R.id.edtTxtProfessor);

        configurarDados();
    }

    private void configurarDados() {
        edtTxtNome.setText(intent.getStringExtra("edtTxtNome"));
        edtTxtArea.setText(intent.getStringExtra("edtTxtArea"));
        edtTxtCargaHoraria.setText(intent.getStringExtra("edtTxtCargaHoraria"));
        edtTxtCampus.setText(intent.getStringExtra("edtTxtCampus"));
        edtTxtProfessor.setText(intent.getStringExtra("edtTxtProfessor"));
    }
}