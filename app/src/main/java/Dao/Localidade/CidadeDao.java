package Dao.Localidade;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import Dao.DbHelper;
import DataModel.DataModel;
import Entities.Localidade.Cidade;

/**
 * Created by vivan on 26/01/2016.
 */
public class CidadeDao {
    private Context context;
    private static DataModel dataModel = new DataModel();

    public  CidadeDao(Context context){
        this.context=context;
    }

    public void insertCidade(Cidade cidade) {
        DbHelper dbHelper = new DbHelper(context);
        ContentValues cv = new ContentValues();
        cv.put(dataModel.getNOME(), cidade.getNome());
        cv.put(dataModel.getID(dataModel.getTabelaPais()),cidade.getPais().getIdPais());
        cv.put(dataModel.getCodigoDoIbge(), cidade.getCodigoDoIbge());
        cv.put(dataModel.getUF(), cidade.getUf());
        dbHelper.insert(dataModel.getTabelaCidade(), cv);
    }

    public void updateCidade(Cidade cidade, int id) {
        DbHelper dbHelper = new DbHelper(context);
        ContentValues cv = new ContentValues();
        cv.put(dataModel.getNOME(), cidade.getNome());
        cv.put(dataModel.getID(dataModel.getTabelaPais()),cidade.getPais().getIdPais());
        cv.put(dataModel.getCodigoDoIbge(), cidade.getCodigoDoIbge());
        cv.put(dataModel.getUF(), cidade.getUf());
        dbHelper.update(dataModel.getTabelaCidade(), cv, dataModel.getID(dataModel.getTabelaCidade())+ "=" + id);
    }
    public void deleteCidade(int id){
        DbHelper dbHelper = new DbHelper(context);
        ContentValues cv = new ContentValues();
        dbHelper.delete(dataModel.getTabelaCidade(),dataModel.getID(dataModel.getTabelaCidade())+ "=" + id);
    }

    public Cidade getCidade(int id) {
        DbHelper dbHelper = new DbHelper(context);
        ContentValues cv = new ContentValues();
        PaisDao paisDao = new PaisDao(context);
        String sqlSelectCidades = "Select * From " + dataModel.getTabelaCidade() + " WHERE " +dataModel.getID(dataModel.getTabelaCidade()) + "=" + id;
        Cursor c = dbHelper.select(sqlSelectCidades);
        Cidade cidade = new Cidade();
        if (c.moveToFirst()) {
            cidade.setIdCidade(c.getInt(0));
            cidade.setNome(c.getString(1));
            cidade.setPais(paisDao.getPais(c.getInt(2)));
            cidade.setCodigoDoIbge(c.getInt(3));
            cidade.setUf(c.getString(4));
        }
        return cidade;
    }


    public List<Cidade> selectTodosOsCidades() {
        DbHelper dbHelper = new DbHelper(context);
        PaisDao paisDao = new PaisDao(context);
        List<Cidade> listaCidade = new ArrayList<Cidade>();
        String sqlSelectTodosOsAsCidades = "Select * From centrodecusto";
        Cursor c = dbHelper.select(sqlSelectTodosOsAsCidades);
        if (c.moveToFirst()) {
            do {
                Cidade cidade = new Cidade();
                cidade.setIdCidade(c.getInt(0));
                cidade.setNome(c.getString(1));
                cidade.setPais(paisDao.getPais(c.getInt(2)));
                cidade.setCodigoDoIbge(c.getInt(3));
                cidade.setUf(c.getString(4));
            } while (c.moveToNext());
        }
        return listaCidade;
    }
}
