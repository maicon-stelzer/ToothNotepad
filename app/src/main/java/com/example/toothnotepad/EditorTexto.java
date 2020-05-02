package com.example.toothnotepad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditorTexto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_texto);

        FloatingActionButton btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtTitulo = findViewById(R.id.txtTitulo);
                EditText txtTexto = findViewById(R.id.txtTexto);

                String titulo = txtTitulo.getText().toString();
                String texto = txtTexto.getText().toString();

                Nota novaNota = new Nota();
                novaNota.TITULO = titulo;
                novaNota.TEXTO = texto;

                DBNotaHelper notaHelper = new DBNotaHelper(getApplicationContext());
                notaHelper.adicionarDadosTabela(novaNota);
                finish();
            }
        });
    }
}
