package Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import Entities.CentroDeCusto;

/**
 * Created by vivan on 12/11/2015.
 */
public class CentoDeCustoDao {
    private Context context;

    public  CentoDeCustoDao(Context context){
        this.context=context;
    }

    public void insertCentroDeCusto(CentroDeCusto centroDeCusto) {
        DbHelper dbHelper = new DbHelper(context);
        ContentValues cv = new ContentValues();
        cv.put("descricao", centroDeCusto.getDescricao());
        cv.put("tipoTranzacao", centroDeCusto.getTipoTranzacao());
        cv.put("ativo", true);
        dbHelper.insert("centrodecusto", cv);
    }
    public void updateCentroDeCusto(CentroDeCusto centroDeCusto, int id){
        DbHelper dbHelper = new DbHelper(context);
        ContentValues cv = new ContentValues();
        cv.put("descricao", centroDeCusto.getDescricao());
        cv.put("tipoTranzacao", centroDeCusto.getTipoTranzacao());
        cv.put("ativo", centroDeCusto.isAtivo());
        dbHelper.update("centrodecusto", cv, "id=" + id);
    }
    public void deleteCentroDeCusto(){

    }

    public CentroDeCusto getCentroDeCusto(int id) {
        DbHelper dbHelper = new DbHelper(context);
        String sqlSelectCentroDeCustos = "Select * From centrodecusto WHERE id =" + id;
        Cursor c = dbHelper.select(sqlSelectCentroDeCustos);
        CentroDeCusto centroDeCusto = new CentroDeCusto();
        if (c.moveToFirst()) {
            centroDeCusto.setId(c.getInt(0));
            centroDeCusto.setDescricao(c.getString(1));
            centroDeCusto.setTipoTranzacao(c.getString(2));
            centroDeCusto.setAtivo(c.getInt(3));
        }
        return centroDeCusto;
    }


    public List<CentroDeCusto> selectTodasAsCentroDeCustos() {
        DbHelper dbHelper = new DbHelper(context);
        List<CentroDeCusto> listaCentroDeCusto = new ArrayList<CentroDeCusto>();
        String sqlSelectTodosOsAsCentroDeCustos = "Select * From centrodecusto";
        Cursor c = dbHelper.select(sqlSelectTodosOsAsCentroDeCustos);
        if (c.moveToFirst()) {
            do {
                CentroDeCusto centroDeCusto = new CentroDeCusto();
                centroDeCusto.setId(c.getInt(0));
                centroDeCusto.setDescricao(c.getString(1));
                centroDeCusto.setTipoTranzacao(c.getString(2));
                centroDeCusto.setAtivo(c.getInt(3));
                listaCentroDeCusto.add(centroDeCusto);
            } while (c.moveToNext());
        }
        return listaCentroDeCusto;
    }
}
