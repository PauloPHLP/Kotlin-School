package com.example.paulo.trabalhofinalandroid.activity.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.paulo.trabalhofinalandroid.R;
import com.example.paulo.trabalhofinalandroid.activity.model.Professor;

import java.util.List;

public class AdapterProfessores extends RecyclerView.Adapter<AdapterProfessores.MyViewHolderProfessores> {

    //region Variables

    private final List<Professor> professores;

    //endregion

    //region Constructor

    public AdapterProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    //endregion

    //region Default methods

    @NonNull
    @Override
    public MyViewHolderProfessores onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolderProfessores(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_lista_professores, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderProfessores myViewHolderProfessores, int i) {
        myViewHolderProfessores.profilePicture.setImageResource(R.drawable.adicionar_professor);
        myViewHolderProfessores.nome.setText(professores.get(i).getNome());
        myViewHolderProfessores.email.setText(professores.get(i).getEmail());
        myViewHolderProfessores.formacoes.setText(professores.get(i).getFormacoes());
    }

    @Override
    public int getItemCount() {
        return professores != null ? professores.size() : 0;
    }

    public class MyViewHolderProfessores extends RecyclerView.ViewHolder {
        ImageView profilePicture;
        TextView nome;
        TextView email;
        TextView formacoes;

        public MyViewHolderProfessores(@NonNull View itemView) {
            super(itemView);

            profilePicture = itemView.findViewById(R.id.crcImgViewProfilePictureProfessor);
            nome = itemView.findViewById(R.id.txtViewNome);
            email = itemView.findViewById(R.id.txtViewEmail);
            formacoes = itemView.findViewById(R.id.txtViewFormacoes);
        }
    }

    //endregion

    //region CRUD methods

    public void atualizarProfessor(Professor professor) {
        professores.set(professores.indexOf(professor), professor);
        notifyItemChanged(professores.indexOf(professor));
    }

    public void removerProfessor(Professor professor) {
        int position = professores.indexOf(professor);
        professores.remove(position);
        notifyItemRemoved(position);
    }

    //endregion
}