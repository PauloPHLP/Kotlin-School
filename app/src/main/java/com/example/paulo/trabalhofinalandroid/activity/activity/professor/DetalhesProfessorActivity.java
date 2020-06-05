package com.example.paulo.trabalhofinalandroid.activity.activity.professor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.paulo.trabalhofinalandroid.R;

public class DetalhesProfessorActivity extends AppCompatActivity {

    TextView edtTxtNome;
    TextView edtTxtData;
    TextView edtTxtNascimento;
    TextView edtTxtEndereco;
    TextView edtTextEmailProfessor;
    TextView edtTxtTelefoneProfessor;
    TextView edtTxtFormacoesProfessor;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_professor);

        intent = getIntent();

        edtTxtNome = findViewById(R.id.edtTxtNome);
        edtTxtData = findViewById(R.id.edtTxtData);
        edtTxtNascimento = findViewById(R.id.edtTxtNascimento);
        edtTxtEndereco = findViewById(R.id.edtTxtEndereco);
        edtTextEmailProfessor = findViewById(R.id.edtTextEmailProfessor);
        edtTxtTelefoneProfessor = findViewById(R.id.edtTxtTelefoneProfessor);
        edtTxtFormacoesProfessor = findViewById(R.id.edtTxtFormacoesProfessor);

        configurarDados();
    }

    public void configurarDados() {
        edtTxtNome.setText(intent.getStringExtra("edtTxtNome"));
        edtTxtData.setText(intent.getStringExtra("edtTxtData"));
        edtTxtNascimento.setText(intent.getStringExtra("edtTxtNascimento"));
        edtTxtEndereco.setText(intent.getStringExtra("edtTxtEndereco"));
        edtTextEmailProfessor.setText(intent.getStringExtra("edtTextEmailProfessor"));
        edtTxtTelefoneProfessor.setText(intent.getStringExtra("edtTxtTelefoneProfessor"));
        edtTxtFormacoesProfessor.setText(intent.getStringExtra("edtTxtFormacoesProfessor"));
    }
}