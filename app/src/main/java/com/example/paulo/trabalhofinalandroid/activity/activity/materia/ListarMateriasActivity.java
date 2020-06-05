package com.example.paulo.trabalhofinalandroid.activity.activity.materia;

import android.content.Intent;
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

public class ListarMateriasActivity extends AppCompatActivity {

    //region Variables

    private RecyclerView recyclerView;
    MateriaDao materiaDao;
    ArrayList<Materia> arrayListMaterias;

    //endregion

    //region onCreate method

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_materias);

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
                                Bundle bundle = new Bundle();
                                bundle.putString("edtTxtNome", arrayListMaterias.get(position).getNome());
                                bundle.putString("edtTxtArea", arrayListMaterias.get(position).getArea());
                                bundle.putString("edtTxtCargaHoraria", arrayListMaterias.get(position).getCargaHoraria());
                                bundle.putString("edtTxtCampus", arrayListMaterias.get(position).getCampus());
                                bundle.putString("edtTxtProfessor", arrayListMaterias.get(position).getProfessor());

                                Intent intent = new Intent(getApplicationContext(), DetalhesMateriaActivity.class);
                                intent.putExtras(bundle);

                                startActivity(intent);
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

    //region CRUD methods

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

    //endregion
}