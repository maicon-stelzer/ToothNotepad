package com.example.toothnotepad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBNotaHelper extends SQLiteOpenHelper {
    public static final String nomeBanco = "toothNotepad.db";
    public static final String nomeTabela = "ToothNotepad";
    public static final String colunasTabela = "(id INTEGER PRIMARY KEY, Titulo TEXT, Texto TEXT)";
    public static final String apagarTabela = "DROP TABLE IF EXISTS " + nomeTabela;
    public static final String criarTabela = "CREATE TABLE IF NOT EXISTS " + nomeTabela + colunasTabela;

    public DBNotaHelper(Context context) {
        super(context, nomeBanco, null, 1);
        onCreate(this.getWritableDatabase());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(criarTabela);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(apagarTabela);
        onCreate(db);
    }

    public void adicionarDadosTabela(Nota nota) {
        SQLiteDatabase bancoDados = this.getWritableDatabase();
        ContentValues valoresNota = new ContentValues();

        valoresNota.put("Titulo", nota.TITULO);
        valoresNota.put("Texto", nota.TEXTO);

        bancoDados.insert(nomeTabela, null, valoresNota);
    }

    public Cursor selecionarTodosDadosTabela() {
        SQLiteDatabase bancoDados = this.getWritableDatabase();
        return bancoDados.rawQuery("SELECT * FROM " + nomeTabela, null);
    }
}
