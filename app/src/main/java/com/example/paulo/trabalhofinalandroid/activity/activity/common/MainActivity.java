package com.example.paulo.trabalhofinalandroid.activity.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.paulo.trabalhofinalandroid.R;
import com.example.paulo.trabalhofinalandroid.activity.activity.materia.ControleDeMateriasActivity;
import com.example.paulo.trabalhofinalandroid.activity.activity.professor.ControleDeProfessoresActivity;

public class MainActivity extends AppCompatActivity {

    //region Variables

    ImageButton imgBtnProfessores;
    ImageButton imgBtnMaterias;

    //endregion

    //region onCreate method

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgBtnProfessores = findViewById(R.id.imgBtnProfessores);
        imgBtnMaterias = findViewById(R.id.imgBtnMaterias);
    }

    //endregion

    //region OnClick methods

    protected void imgBtnProfessoresOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), ControleDeProfessoresActivity.class);

        startActivity(intent);
        Toast.makeText(getApplicationContext(),"Controle de professores", Toast.LENGTH_SHORT).show();
    }

    protected void imgBtnMateriasOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), ControleDeMateriasActivity.class);

        startActivity(intent);
        Toast.makeText(getApplicationContext(),"Controle de matérias", Toast.LENGTH_SHORT).show();
    }

    //endregion
}