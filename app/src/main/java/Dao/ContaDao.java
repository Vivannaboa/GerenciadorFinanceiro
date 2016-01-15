package Dao;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import Entities.Conta;

/**
 * Created by vivan on 11/11/2015.
 */
public class ContaDao {
   private Context context;

    public  ContaDao(Context context){
        this.context=context;
    }

    public void insertConta(Conta conta) {
        DbHelper dbHelper = new DbHelper(context);
        ContentValues cv = new ContentValues();
        cv.put("nome", conta.getNome());
        cv.put("numero", conta.getNumero());
        cv.put("saldo", conta.getSaldo());
        dbHelper.insert("Conta", cv);
    }
    public void updateConta(Conta conta, int id){
        DbHelper dbHelper = new DbHelper(context);
        ContentValues cv = new ContentValues();
        cv.put("nome", conta.getNome());
        cv.put("numero", conta.getNumero());
        cv.put("saldo", conta.getSaldo());
        dbHelper.update("Conta", cv, "id=" + id);
    }
    public void deleteConta(){

    }

    public Conta getConta(int id) {
        DbHelper dbHelper = new DbHelper(context);
        String sqlSelectContas = "Select * From Conta WHERE id =" + id;
        Cursor c = dbHelper.select(sqlSelectContas);
        Conta conta = new Conta();
        if (c.moveToFirst()) {
            conta.setId(c.getInt(0));
            conta.setNome(c.getString(1));
            conta.setNumero(c.getString(2));
            conta.depositar(c.getDouble(3));
        }
        return conta;
    }


    public List<Conta> selectTodasAsContas() {
        DbHelper dbHelper = new DbHelper(context);
        List<Conta> listaConta = new ArrayList<Conta>();
        String sqlSelectTodasAsContas = "Select * From Conta";
        Cursor c = dbHelper.select(sqlSelectTodasAsContas);
        if (c.moveToFirst()) {
            do {
                Conta conta = new Conta();
                conta.setId(c.getInt(0));
                conta.setNome(c.getString(1));
                conta.setNumero(c.getString(2));
                conta.depositar(c.getDouble(3));
                listaConta.add(conta);
            } while (c.moveToNext());
        }
        return listaConta;
    }
}
