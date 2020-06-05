package com.example.paulo.trabalhofinalandroid.activity.activity.professor;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.paulo.trabalhofinalandroid.R;
import com.example.paulo.trabalhofinalandroid.activity.activity.common.RecyclerItemClickListener;
import com.example.paulo.trabalhofinalandroid.activity.adapter.AdapterProfessores;
import com.example.paulo.trabalhofinalandroid.activity.dao.ProfessorDao;
import com.example.paulo.trabalhofinalandroid.activity.model.Professor;

import java.util.ArrayList;

public class ExcluirProfessorActivity extends AppCompatActivity {

    //region Variables

    private RecyclerView recyclerView;
    ProfessorDao professorDao;
    Professor professor;
    ArrayList<Professor> arrayListProfessores;

    //endregion

    //region onCreate method

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excluir_professor);

        recyclerView = findViewById(R.id.rcyViewListaProfessores);
        professor= new Professor();
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
                                professor = arrayListProfessores.get(position);
                                professorDao = new ProfessorDao(view.getContext());

                                excluirProfessorAlertDialog(view, position, professor, professorDao);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Toast.makeText(getApplicationContext(), "Paulo Lima", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );
    }

    //endregion

    //region AlertDialog methods

    public void excluirProfessorAlertDialog(View view, final int position, final Professor professor, final ProfessorDao professorDao) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("Deletar " + arrayListProfessores.get(position).getNome());
        dialog.setMessage("Tem certeza que deseja excluir este professor?");
        dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean sucesso = professorDao.excluirProfessor(professor.getId());

                if (sucesso) {
                    removerProfessor(professor);
                    Toast.makeText(getApplicationContext(), "Professor " + arrayListProfessores.get(position).getNome() + " excluído!", Toast.LENGTH_LONG).show();
                }


            }
        });
        dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Professor " + arrayListProfessores.get(position).getNome() + " não excluído!", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setIcon(android.R.drawable.ic_delete);
        dialog.setCancelable(false);
        dialog.create();
        dialog.show();
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

    public void removerProfessor(Professor professor) {
        int position = arrayListProfessores.indexOf(professor);
        arrayListProfessores.remove(position);
    }

    //endregion
}