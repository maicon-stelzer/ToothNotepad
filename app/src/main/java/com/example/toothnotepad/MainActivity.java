package com.example.toothnotepad;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.os.Looper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ActionMenuView;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotActiveException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton novaNota = findViewById(R.id.btnNovaNota);
        novaNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencao = new Intent(getApplicationContext(), EditorTexto.class);
                startActivity(intencao);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView txtTesteInsercao = findViewById(R.id.txtTesteInsercao);
        txtTesteInsercao.setText("");
        DBNotaHelper bancoDados = new DBNotaHelper(getApplicationContext());
        Cursor dados = bancoDados.selecionarTodosDadosTabela();
        if (dados.getCount() >= 1) {
            dados.moveToFirst();
            while (!dados.isAfterLast()) {
                String titulo = dados.getString(dados.getColumnIndex("Titulo"));
                String texto = dados.getString(dados.getColumnIndex("Texto"));
                String conteudo = "Título: " + titulo + "\n" + "Texto: "+ texto + "\n";
                txtTesteInsercao.append(conteudo);
                dados.moveToNext();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.mnu_Sobre:
                Intent intent = new Intent(getApplicationContext(), Sobre.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
