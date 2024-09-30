package com.example.roomdatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class MeuAdaptador extends RecyclerView.Adapter<MeuAdaptador.ViewHolder> {
    List<Contato> contatos;

    public MeuAdaptador(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final EditText listId;
        final TextView listNome;
        final TextView listEmail;
        final TextView listNum;


        public ViewHolder(View view) {
            super(view);
            listId = (EditText) view.findViewById(R.id.listId);
            listNome = (TextView) view.findViewById(R.id.listNome);
            listEmail = (TextView) view.findViewById(R.id.listEmail);
            listNum = (TextView) view.findViewById(R.id.listNum);


        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contato_lista, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contato contato = contatos.get(position);
        holder.listId.setText("" + contato.id);
        holder.listNome.setText(contato.nome);
        holder.listNum.setText(contato.telefone);
        holder.listEmail.setText(contato.email);

    }

    @Override
    public int getItemCount() {
        return contatos.size();
    }
}
