package Dao;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import DataModel.DataModel;
import Entities.Conta;

/**
 * Created by vivan on 11/11/2015.
 */
public class ContaDao {

    public static final String NOME_TABELA = "Conta";
    public static final String COLUNA_ID = "id_conta";
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_NUMERO = "numero";
    public static final String COLUNA_SALDO= "saldo";


    public static final String CRIAR_TABELA_CONTA(){
        String query =  "CREATE TABLE " + NOME_TABELA;
        query += " (";
        query += COLUNA_ID + " " + DataModel.TIPO_INTEIRO_PK + ", ";
        query += COLUNA_NOME + " " + DataModel.TIPO_TEXTO + ", ";
        query += COLUNA_NUMERO + " " + DataModel.TIPO_TEXTO + ", ";
        query += COLUNA_SALDO + " " + DataModel.TIPO_REAL + " ";
        query += ")";
        return query;
    }
    public static final String SCRIPT_DELECAO_TABELA =  "DROP TABLE IF EXISTS " + NOME_TABELA;

    private SQLiteDatabase dataBase = null;

    private static ContaDao instance;

    public static ContaDao getInstance(Context context) {
        if(instance == null)
            instance = new ContaDao(context);
        return instance;
    }

    private ContaDao(Context context) {
        DbHelper dbHelper = DbHelper.getInstance(context);
        dataBase = dbHelper.getWritableDatabase();
    }
    //inserir
    public void insertConta(Conta conta) {
        ContentValues values = gerarContentValeuesConta(conta);
        dataBase.insert(NOME_TABELA, null, values);
    }
    //atualizar
    public void updateConta(Conta conta){
        ContentValues valores = gerarContentValeuesConta(conta);

        String[] valoresParaSubstituir = {
                String.valueOf(conta.getId())
        };
        dataBase.update(NOME_TABELA, valores, COLUNA_ID + " = ?", valoresParaSubstituir);
    }
    //deletar
    public void deleteConta(Conta conta){
        String[] valoresParaSubstituir = {
                String.valueOf(conta.getId())
        };
        dataBase.delete(NOME_TABELA, COLUNA_ID + " =  ?", valoresParaSubstituir);
    }
    //retornar uma conta
    public Conta getConta(int id) {
        String queryReturnAll = "SELECT * FROM " + NOME_TABELA +" WHERE "+ COLUNA_ID +"=" + id;
        Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
        List<Conta> contas = construirContaPorCursor(cursor);
        Conta conta = new Conta(contas.get(0).getId(),contas.get(0).getNome(),contas.get(0).getNumero(),contas.get(0).getSaldo());
        return conta;
    }
    //retorna todas as contas
    public List<Conta> selectTodasAsContas() {
        String queryReturnAll = "SELECT * FROM " + NOME_TABELA;
        Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
        List<Conta> contas = construirContaPorCursor(cursor);

        return contas;
    }

    private List<Conta> construirContaPorCursor(Cursor cursor) {
        List<Conta> contas = new ArrayList<Conta>();
        if(cursor == null)
            return contas;
        try {

            if (cursor.moveToFirst()) {
                do {

                    int indexID = cursor.getColumnIndex(COLUNA_ID);
                    int indexNome = cursor.getColumnIndex(COLUNA_NOME);
                    int indexNumero = cursor.getColumnIndex(COLUNA_NUMERO);
                    int indexSaldo = cursor.getColumnIndex(COLUNA_SALDO);

                    int id = cursor.getInt(indexID);
                    String nome = cursor.getString(indexNome);
                    String numero = cursor.getString(indexNumero);
                    Double saldo = cursor.getDouble(indexSaldo);

                    Conta conta = new Conta(id, nome, numero, saldo);

                    contas.add(conta);

                } while (cursor.moveToNext());
            }

        } finally {
            cursor.close();
        }
        return contas;
    }

    private ContentValues gerarContentValeuesConta(Conta conta) {
        ContentValues values = new ContentValues();
        values.put(COLUNA_NOME, conta.getNome());
        values.put(COLUNA_NUMERO, conta.getNumero());
        values.put(COLUNA_SALDO, conta.getSaldo());
        return values;
    }

    public void fecharConexao() {
        if(dataBase != null && dataBase.isOpen())
            dataBase.close();
    }


}
