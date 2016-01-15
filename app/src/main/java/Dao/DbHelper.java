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

import static DataModel.DataModel.criaTabelaCentroDeCusto;
import static DataModel.DataModel.criaTabelaMovimentacao;
import static DataModel.DataModel.criarTabelaConta;

/**
 * Created by vivan on 02/11/15.
 */
public class DbHelper extends SQLiteOpenHelper {
    private static final String NOME_BASE = "GerenciadorFinanceiro";
    private static final int VERSAO_BASE = 14;

    public DbHelper(Context context) {
        super(context, NOME_BASE, null, VERSAO_BASE);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(criarTabelaConta());
        db.execSQL(criaTabelaCentroDeCusto());
        db.execSQL(criaTabelaMovimentacao());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table Conta");
        db.execSQL("Drop table movimentacao");
        db.execSQL("Drop table centrodecusto");
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
