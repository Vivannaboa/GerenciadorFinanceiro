package Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import Entities.Conta;

import static DataModel.DataModel.*;

/**
 * Created by vivan on 02/11/15.
 */
public class DbHelper extends SQLiteOpenHelper {
    private static final String NOME_BASE = "GerenciadorFinanceiro";
    private static final int VERSAO_BASE = 27;

    public DbHelper(Context context) {
        super(context, NOME_BASE, null, VERSAO_BASE);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(criarTabelaConta());
        db.execSQL(criaTabelaCentroDeCusto());
        db.execSQL(criaTabelaMovimentacao());
        db.execSQL(criaTabelaBairro());
        db.execSQL(criaTabelaCidade());
        db.execSQL(criaTabelaPais());
        db.execSQL(criaTabelaLogradouro());
        db.execSQL(criaTabelaPessoa());
        db.execSQL(criaTabelaPessoaFisica());
        db.execSQL(criaTabelaPessoaJuridica());
        db.execSQL(criaTabelaTelefone());
        db.execSQL(criaTabelaEndereco());
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table Conta");
        db.execSQL("Drop table Movimentacao");
        db.execSQL("Drop table Centrodecusto");
        db.execSQL("Drop table Bairro");
        db.execSQL("Drop table Cidade");
        db.execSQL("Drop table Logradouro");
        db.execSQL("Drop table Endereco");
        db.execSQL("Drop table Pais");
        db.execSQL("Drop table Pessoa");
        db.execSQL("Drop table Pessoa_juridica");
        db.execSQL("Drop table Pessoa_fisica");
        db.execSQL("Drop table Telefone");
        onCreate(db);
    }


    public Cursor select(String query) throws SQLException {
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery(query, null);
    }

    public void insert(String table, ContentValues values) throws SQLException {
        SQLiteDatabase db = getWritableDatabase();
        db.insert(table, null, values);
        db.close();
    }

    public void delete(String table, String where) throws SQLException {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(table, where, null);
        db.close();
    }

    public void update(String table, ContentValues values, String where) {
        SQLiteDatabase db = getWritableDatabase();
        db.update(table, values, where, null);
        db.close();
    }


}
