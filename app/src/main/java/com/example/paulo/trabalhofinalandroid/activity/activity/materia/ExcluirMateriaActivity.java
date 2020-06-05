package com.example.paulo.trabalhofinalandroid.activity.activity.materia;

import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.paulo.trabalhofinalandroid.activity.adapter.AdapterMaterias;
import com.example.paulo.trabalhofinalandroid.activity.dao.MateriaDao;
import com.example.paulo.trabalhofinalandroid.activity.model.Materia;

import java.util.ArrayList;

public class ExcluirMateriaActivity extends AppCompatActivity {

    //region Variables

    private RecyclerView recyclerView;
    MateriaDao materiaDao;
    ArrayList<Materia> arrayListMaterias;

    //endregion

    //region onCreate method

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excluir_materia);

        recyclerView = findViewById(R.id.rcyViewListaMaterias);
        materiaDao = new MateriaDao(this);

        AdapterMaterias adapterMaterias = new AdapterMaterias(materiaDao.listarMaterias());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterMaterias);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                excluirMateriaAlertDialog(view, position);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Toast.makeText(getApplicationContext(), arrayListMaterias.get(position).getNome(), Toast.LENGTH_LONG).show();
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

    public void excluirMateriaAlertDialog(View view, final int position) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("Deletar " + arrayListMaterias.get(position).getNome());
        dialog.setMessage("Tem certeza que deseja excluir esta matéria?");
        dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                materiaDao.excluirMateria(arrayListMaterias.get(position).getId());
                Toast.makeText(getApplicationContext(), "Matéria " + arrayListMaterias.get(position).getNome() + " excluída!", Toast.LENGTH_LONG).show();
            }
        });
        dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Matéria " + arrayListMaterias.get(position).getNome() + " não excluída!", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setIcon(android.R.drawable.ic_delete);
        dialog.setCancelable(false);
        dialog.create();
        dialog.show();
    }

    //endregion

    public void popularListaMaterias() {
        materiaDao = new MateriaDao(this);

        arrayListMaterias = materiaDao.listarMaterias();
        materiaDao.close();
    }

    @Override
    protected void onResume() {
        super.onResume();

        popularListaMaterias();
    }
}