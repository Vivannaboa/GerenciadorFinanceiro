package Dao.Localidade;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import Dao.DbHelper;
import DataModel.DataModel;
import Entities.Localidade.Pais;

/**
 * Created by vivan on 26/01/2016.
 */
public class PaisDao {
    private Context context;
    private static DataModel dataModel = new DataModel();

    public  PaisDao(Context context){
        this.context=context;
    }

    public void insertPais(Pais pais) {
        DbHelper dbHelper = new DbHelper(context);
        ContentValues cv = new ContentValues();
        cv.put(dataModel.getNOME(), pais.getNome());
        dbHelper.insert(dataModel.getTabelaPais(), cv);
    }

    public void updatePais(Pais pais, int id) {
        DbHelper dbHelper = new DbHelper(context);
        ContentValues cv = new ContentValues();
        cv.put(dataModel.getNOME(), pais.getNome());
        dbHelper.update(dataModel.getTabelaPais(), cv, dataModel.getID(dataModel.getTabelaPais())+ "=" + id);
    }
    public void deletePais(int id){
        DbHelper dbHelper = new DbHelper(context);
        ContentValues cv = new ContentValues();
        dbHelper.delete(dataModel.getTabelaPais(),dataModel.getID(dataModel.getTabelaPais())+ "=" + id);
    }

    public Pais getPais(int id) {
        DbHelper dbHelper = new DbHelper(context);
        ContentValues cv = new ContentValues();
        String sqlSelectPais = "Select * From " + dataModel.getTabelaPais() + " WHERE " +dataModel.getID(dataModel.getTabelaPais()) + "=" + id;
        Cursor c = dbHelper.select(sqlSelectPais);
        Pais pais = new Pais();
        if (c.moveToFirst()) {
            pais.setIdPais(c.getInt(0));
            pais.setNome(c.getString(1));
        }
        return pais;
    }


    public List<Pais> selectTodosOsPaiss() {
        DbHelper dbHelper = new DbHelper(context);
        List<Pais> listaPais = new ArrayList<Pais>();
        String sqlSelectTodosOsAsPaiss = "Select * From centrodecusto";
        Cursor c = dbHelper.select(sqlSelectTodosOsAsPaiss);
        if (c.moveToFirst()) {
            do {
                Pais pais = new Pais();
                pais.setIdPais(c.getInt(0));
                pais.setNome(c.getString(1));
            } while (c.moveToNext());
        }
        return listaPais;
    }
}
