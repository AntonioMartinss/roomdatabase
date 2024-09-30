package com.example.roomdatabase;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etNome;
    EditText etTelefone;
    EditText etEmail;
    String DATABASE_NAME = "my-db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNome = findViewById(R.id.etNome);
        etTelefone = findViewById(R.id.etTelefone);
        etEmail = findViewById(R.id.etEmail);

        buscaContatos();


        RecyclerView rvContatos = findViewById(R.id.rvContatos);
        RecyclerView.LayoutManager layout =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvContatos.setLayoutManager(layout);


    }

    public void gravarContato(View view) {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, DATABASE_NAME).build();

        ContatoDAO contatoDAO = db.contatoDAO();
        //instanciar a entidade
        Contato contato = new Contato();
        contato.nome = etNome.getText().toString();
        contato.telefone = etTelefone.getText().toString();
        contato.email = etEmail.getText().toString();


        new Thread(new Runnable() {
            @Override
            public void run() {
                contatoDAO.insert(contato);
            }
        }).start();

    }

    public void deletarContatos(View view) {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, DATABASE_NAME).build();
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        EditText listId = viewGroup.findViewById(R.id.listId);

        ContatoDAO contatoDAO = db.contatoDAO();
        Contato contato = new Contato();
        contato.id = (Integer.parseInt(listId.getText().toString()));
        new Thread(new Runnable() {
            @Override
            public void run() {
                contatoDAO.delete(contato);

            }
        }).start();

    }

    private void buscaContatos() {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, DATABASE_NAME).build();
        ContatoDAO contatoDAO = db.contatoDAO();

        RecyclerView rvContatos = findViewById(R.id.rvContatos);
        rvContatos.setLayoutManager(new LinearLayoutManager(this));

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Contato> contatos = contatoDAO.getAll();
                for(Contato c : contatos ) {
                    RecyclerView rvContatos = findViewById(R.id.rvContatos);
                    MeuAdaptador adaptador = new MeuAdaptador(contatos);
                    rvContatos.setAdapter(adaptador);
                }
            }
        }).start();
    }


}