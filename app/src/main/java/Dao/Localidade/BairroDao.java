package Dao.Localidade;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;
import DataModel.DataModel;
import Dao.DbHelper;
import Entities.Localidade.Bairro;

/**
 * Created by vivan on 23/01/2016.
 */
public class BairroDao {

        private Context context;
        private static DataModel dataModel = new DataModel();


        public  BairroDao(Context context){

            this.context=context;
        }

        public void insertBairro(Bairro bairro) {
            DbHelper dbHelper = new DbHelper(context);
            ContentValues cv = new ContentValues();
            cv.put(dataModel.getDESCRICAO(), bairro.getDescricao());
            dbHelper.insert(dataModel.getTabelaBairro(), cv);
        }

        public void updateBairro(Bairro bairro, int id){
            DbHelper dbHelper = new DbHelper(context);
            ContentValues cv = new ContentValues();
            cv.put(dataModel.getDESCRICAO(), bairro.getDescricao());
            dbHelper.update(dataModel.getTabelaBairro(), cv, dataModel.getID(dataModel.getTabelaBairro())+ "=" + id);
        }
        public void deleteBairro(int id){
            DbHelper dbHelper = new DbHelper(context);
            ContentValues cv = new ContentValues();
            dbHelper.delete(dataModel.getTabelaBairro(),dataModel.getID(dataModel.getTabelaBairro())+ "=" + id);
        }

        public Bairro getBairro(int id) {
            DbHelper dbHelper = new DbHelper(context);
            ContentValues cv = new ContentValues();
            String sqlSelectBairros = "Select * From " + dataModel.getTabelaBairro() + " WHERE " +dataModel.getID(dataModel.getTabelaBairro()) + "=" + id;
            Cursor c = dbHelper.select(sqlSelectBairros);
            Bairro bairro = new Bairro();
            if (c.moveToFirst()) {
                bairro.setIdBairro(c.getInt(0));
                bairro.setDescricao(c.getString(1));
            }
            return bairro;
        }


        public List<Bairro> selectTodosOsBairros() {
            DbHelper dbHelper = new DbHelper(context);
            List<Bairro> listaBairro = new ArrayList<Bairro>();
            String sqlSelectTodosOsAsBairros = "Select * From centrodecusto";
            Cursor c = dbHelper.select(sqlSelectTodosOsAsBairros);
            if (c.moveToFirst()) {
                do {
                    Bairro bairro = new Bairro();
                    bairro.setIdBairro(c.getInt(0));
                    bairro.setDescricao(c.getString(1));
                    listaBairro.add(bairro);
                } while (c.moveToNext());
            }
            return listaBairro;
        }
    }


