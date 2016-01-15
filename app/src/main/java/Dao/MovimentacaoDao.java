package Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import Entities.CentroDeCusto;
import Entities.Conta;
import Entities.Movimentacao;

/**
 * Created by vivan on 11/11/2015.
 */
public class MovimentacaoDao {
   private Context context;

    public MovimentacaoDao(Context context){
        this.context=context;
    }

    public void insertMovimentacao(Movimentacao movimentacao) {
        DbHelper dbHelper = new DbHelper(context);
        ContentValues cv = new ContentValues();
        cv.put("descricao", movimentacao.getDescricao());
        cv.put("idConta",movimentacao.getConta().getId());
        cv.put("idCentroCusto", movimentacao.getCentroDeCusto().getId());
        cv.put("valor", movimentacao.getValor());
        dbHelper.insert("Movimentacao", cv);
    }
    public void updateMovimentacao(Movimentacao movimentacao, int id){
        DbHelper dbHelper = new DbHelper(context);
        ContentValues cv = new ContentValues();
        cv.put("descricao", movimentacao.getDescricao());
        cv.put("idConta", movimentacao.getConta().getId());
        cv.put("idCentroDeCusto", movimentacao.getCentroDeCusto().getId());
        cv.put("valor", movimentacao.getValor());
        dbHelper.update("Movimentacao", cv, "id=" + id);
    }
    public void deleteMovimentacao(){

    }

    public Movimentacao getMovimentacao(int id) {
        DbHelper dbHelper = new DbHelper(context);
        String sqlSelectodosOsMovimentos = "Select * From Movimentacao WHERE id =" + id;
        Cursor c = dbHelper.select(sqlSelectodosOsMovimentos);
        ContaDao contaDao = new ContaDao(context);
        CentoDeCustoDao centoDeCustoDao = new CentoDeCustoDao(context);

        Movimentacao movimentacao = new Movimentacao();
        if (c.moveToFirst()) {
            movimentacao.setId(c.getInt(0));
            movimentacao.setDescricao(c.getString(1));
            movimentacao.setConta(contaDao.getConta(c.getInt(2)));
            movimentacao.setCentroDeCusto(centoDeCustoDao.getCentroDeCusto(c.getInt(3)));
            movimentacao.setValor(c.getDouble(4));
        }
        return movimentacao;
    }


    public List<Movimentacao> selectTodosOsMovimentacao() {
        DbHelper dbHelper = new DbHelper(context);
        List<Movimentacao> listaMovimentacao = new ArrayList<Movimentacao>();
        String sqlSelectTodasOsMovimentos = "Select * From Movimentacao";
        ContaDao contaDao = new ContaDao(context);
        CentoDeCustoDao centoDeCustoDao = new CentoDeCustoDao(context);
        Cursor c = dbHelper.select(sqlSelectTodasOsMovimentos);
        if (c.moveToFirst()) {
            do {
                Movimentacao movimentacao = new Movimentacao();
                movimentacao.setId(c.getInt(0));
                movimentacao.setDescricao(c.getString(1));
                movimentacao.setConta(contaDao.getConta(c.getInt(2)));
                movimentacao.setCentroDeCusto(centoDeCustoDao.getCentroDeCusto(c.getInt(3)));
                movimentacao.setValor(c.getDouble(4));
                listaMovimentacao.add(movimentacao);
            } while (c.moveToNext());
        }
        return listaMovimentacao;
    }
}
