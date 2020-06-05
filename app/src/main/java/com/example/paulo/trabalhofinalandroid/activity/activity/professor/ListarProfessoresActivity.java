package com.example.paulo.trabalhofinalandroid.activity.activity.professor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.paulo.trabalhofinalandroid.R;
import com.example.paulo.trabalhofinalandroid.activity.activity.common.RecyclerItemClickListener;
import com.example.paulo.trabalhofinalandroid.activity.adapter.AdapterProfessores;
import com.example.paulo.trabalhofinalandroid.activity.dao.ProfessorDao;
import com.example.paulo.trabalhofinalandroid.activity.model.Professor;

import java.util.ArrayList;

public class ListarProfessoresActivity extends AppCompatActivity {

    //region Variables

    private RecyclerView recyclerView;
    ProfessorDao professorDao;
    ArrayList<Professor> arrayListProfessores;

    //endregion

    //region onCreate method

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_professores);

        recyclerView = findViewById(R.id.rcyViewListaProfessores);
        professorDao = new ProfessorDao(this);

        AdapterProfessores adapterProfessores = new AdapterProfessores(professorDao.listarProfessores());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterProfessores);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Bundle bundle = new Bundle();
                                bundle.putString("edtTxtNome", arrayListProfessores.get(position).getNome());
                                bundle.putString("edtTxtData", arrayListProfessores.get(position).getDataDeNascimento());
                                bundle.putString("edtTxtNascimento", arrayListProfessores.get(position).getLocalDeNascimento());
                                bundle.putString("edtTxtEndereco", arrayListProfessores.get(position).getEndereco());
                                bundle.putString("edtTextEmailProfessor", arrayListProfessores.get(position).getEmail());
                                bundle.putString("edtTxtTelefoneProfessor", arrayListProfessores.get(position).getTelefone());
                                bundle.putString("edtTxtFormacoesProfessor", arrayListProfessores.get(position).getFormacoes());

                                Intent intent = new Intent(getApplicationContext(), DetalhesProfessorActivity.class);
                                intent.putExtras(bundle);

                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Toast.makeText(getApplicationContext(), arrayListProfessores.get(position).getNome(), Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );
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