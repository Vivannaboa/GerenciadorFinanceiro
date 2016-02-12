package Dao.Localidade;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import Dao.DbHelper;
import DataModel.DataModel;
import Entities.Localidade.Logradouro;

/**
 * Created by vivan on 26/01/2016.
 */
public class LogradouroDao {
    private Context context;
    private static DataModel dataModel = new DataModel();


    public  LogradouroDao(Context context){
        this.context=context;
    }

    public void insertLogradouro(Logradouro logradouro) {
        DbHelper dbHelper = new DbHelper(context);
        ContentValues cv = new ContentValues();
        cv.put(dataModel.getDESCRICAO(), logradouro.getDescricao());
        dbHelper.insert(dataModel.getTabelaLogradouro(), cv);
    }

    public void updateLogradouro(Logradouro logradouro, int id) {
        DbHelper dbHelper = new DbHelper(context);
        ContentValues cv = new ContentValues();
        cv.put(dataModel.getDESCRICAO(), logradouro.getDescricao());
        dbHelper.update(dataModel.getTabelaLogradouro(), cv, dataModel.getID(dataModel.getTabelaLogradouro())+ "=" + id);
    }
    public void deleteLogradouro(int id){
        DbHelper dbHelper = new DbHelper(context);
        ContentValues cv = new ContentValues();
        dbHelper.delete(dataModel.getTabelaLogradouro(),dataModel.getID(dataModel.getTabelaLogradouro())+ "=" + id);
    }

    public Logradouro getLogradouro(int id) {
        DbHelper dbHelper = new DbHelper(context);
        ContentValues cv = new ContentValues();
        String sqlSelectLogradouros = "Select * From " + dataModel.getTabelaLogradouro() + " WHERE " +dataModel.getID(dataModel.getTabelaLogradouro()) + "=" + id;
        Cursor c = dbHelper.select(sqlSelectLogradouros);
        Logradouro logradouro = new Logradouro();
        if (c.moveToFirst()) {
            logradouro.setIdLogradouro(c.getInt(0));
            logradouro.setDescricao(c.getString(1));
        }
        return logradouro;
    }


    public List<Logradouro> selectTodosOsLogradouros() {
        DbHelper dbHelper = new DbHelper(context);
        List<Logradouro> listaLogradouro = new ArrayList<Logradouro>();
        String sqlSelectTodosOsAsLogradouros = "Select * From centrodecusto";
        Cursor c = dbHelper.select(sqlSelectTodosOsAsLogradouros);
        if (c.moveToFirst()) {
            do {
                Logradouro logradouro = new Logradouro();
                logradouro.setIdLogradouro(c.getInt(0));
                logradouro.setDescricao(c.getString(1));
            } while (c.moveToNext());
        }
        return listaLogradouro;
    }
}
