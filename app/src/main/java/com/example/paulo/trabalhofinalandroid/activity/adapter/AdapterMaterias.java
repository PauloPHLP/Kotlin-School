package com.example.paulo.trabalhofinalandroid.activity.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.paulo.trabalhofinalandroid.R;
import com.example.paulo.trabalhofinalandroid.activity.model.Materia;

import java.util.List;

public class AdapterMaterias extends RecyclerView.Adapter<AdapterMaterias.MyViewHolderMaterias> {

    //region Variables

    private final List<Materia> materias;

    //endregion

    //region Constructor

    public AdapterMaterias(List<Materia> materias) {
        this.materias = materias;
    }

    //endregion

    //region Default methods

    @NonNull
    @Override
    public MyViewHolderMaterias onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AdapterMaterias.MyViewHolderMaterias(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_lista_materias, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderMaterias myViewHolderMaterias, int i) {
        myViewHolderMaterias.materiaPicture.setImageResource(R.drawable.materia);
        myViewHolderMaterias.nome.setText(materias.get(i).getNome());
        myViewHolderMaterias.campus.setText(materias.get(i).getCampus());
        myViewHolderMaterias.professor.setText(materias.get(i).getProfessor());
    }

    @Override
    public int getItemCount() {
        return materias != null ? materias.size() : 0;
    }

    public class MyViewHolderMaterias extends RecyclerView.ViewHolder {
        ImageView materiaPicture;
        TextView nome;
        TextView campus;
        TextView professor;

        public MyViewHolderMaterias(@NonNull View itemView) {
            super(itemView);

            materiaPicture = itemView.findViewById(R.id.crcImgViewMateriaPicture);
            nome = itemView.findViewById(R.id.txtViewNomeMateria);
            campus = itemView.findViewById(R.id.txtViewAreaMateria);
            professor = itemView.findViewById(R.id.txtViewProfessorMateria);
        }
    }

    //endregion

    public void atualizarMateria(Materia materia){
        materias.set(materias.indexOf(materia), materia);
        notifyItemChanged(materias.indexOf(materia));
    }

    public void removerMateria(Materia materia){
        int position = materias.indexOf(materia);
        materias.remove(position);
        notifyItemRemoved(position);
    }

}