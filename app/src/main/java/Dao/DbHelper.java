package Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import Dao.Localidade.BairroDao;
import Dao.Localidade.CidadeDao;
import Dao.Localidade.EnderecoDao;
import Dao.Localidade.LogradouroDao;
import Dao.Localidade.PaisDao;
import Entities.CentroDeCusto;
import Entities.Conta;

import static DataModel.DataModel.*;

/**
 * Created by vivan on 02/11/15.
 */
public class DbHelper extends SQLiteOpenHelper {
    private static final String NOME_BASE = "GerenciadorFinanceiro";
    private static final int VERSAO_BASE = 31;
    private static DbHelper instance;
    public DbHelper(Context context) {
        super(context, NOME_BASE, null, VERSAO_BASE);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ContaDao.CRIAR_TABELA_CONTA());
        db.execSQL(CentoDeCustoDao.CRIAR_TABELA_CENTRO_DE_CUSTO());
        db.execSQL(MovimentacaoDao.CRIAR_TABELA_MOVIMENTACAO());
        db.execSQL(BairroDao.CRIAR_TABELA_BAIRRO());
        db.execSQL(CidadeDao.CRIAR_TABELA_CIDADE());
        db.execSQL(PaisDao.CRIAR_TABELA_PAIS());
        db.execSQL(LogradouroDao.CRIAR_TABELA_LOGRADOURO());
        db.execSQL(EnderecoDao.CRIAR_TABELA_ENDERECO());
        //db.execSQL(PaisDao.CRIAR_TABELA_PAIS());
        db.execSQL(PessoaDao.CRIAR_TABELA_PESSOA());

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ContaDao.SCRIPT_DELECAO_TABELA);
        db.execSQL(CentoDeCustoDao.SCRIPT_DELECAO_TABELA);
        db.execSQL(MovimentacaoDao.SCRIPT_DELECAO_TABELA);
        db.execSQL(BairroDao.SCRIPT_DELECAO_TABELA);
        db.execSQL(CidadeDao.SCRIPT_DELECAO_TABELA);
        db.execSQL(PaisDao.SCRIPT_DELECAO_TABELA);
        db.execSQL(LogradouroDao.SCRIPT_DELECAO_TABELA);
        db.execSQL(PaisDao.SCRIPT_DELECAO_TABELA);
        db.execSQL(PessoaDao.SCRIPT_DELECAO_TABELA);
        db.execSQL(EnderecoDao.SCRIPT_DELECAO_TABELA);
        onCreate(db);
    }

    public static DbHelper getInstance(Context context) {
        if(instance == null)
            instance = new DbHelper(context);
        return instance;
    }

    public Cursor select(String query) throws SQLException {
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery(query, null);
    }

    public void insert(String table, ContentValues values) throws SQLException {
        SQLiteDatabase db = getWritableDatabase();
        db.insert(table, null, values);
        //db.close();
    }

    public int getInsert(String table, ContentValues values) throws SQLException {
        int id ;
        SQLiteDatabase db = getWritableDatabase();
        id=(int)db.insert(table, null, values);
        //db.close();
        return id;
    }
    public void delete(String table, String where) throws SQLException {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(table, where, null);
        db.close();
    }

    public void update(String table, ContentValues values, String where) {
        SQLiteDatabase db = getWritableDatabase();
        db.update(table, values, where, null);
        //db.close();
    }
    public int getSequencia(String table){
        String query = "select seq from sqlite_sequence where name= " +table;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        return c.getInt(0);
    }

}
