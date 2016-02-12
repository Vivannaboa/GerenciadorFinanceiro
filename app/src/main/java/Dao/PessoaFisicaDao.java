package Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import Dao.Localidade.PaisDao;
import DataModel.DataModel;
import Entities.PessoaFisica;

/**
 * Created by vivan on 26/01/2016.
 */
public class PessoaFisicaDao {
    private Context context;
    private static DataModel dataModel = new DataModel();
    private final DbHelper dbHelper = new DbHelper(context);
    private final ContentValues cv = new ContentValues();
    private final PaisDao paisDao = new PaisDao(context);

    public  PessoaFisicaDao(Context context){
        this.context=context;
    }

    public void insertPessoaFisica(PessoaFisica pessoaFisica) {
        cv.put(dataModel.getNOME(), pessoaFisica.getNome());
        cv.put(dataModel.getAPELIDO(), pessoaFisica.getApelido());
        cv.put(dataModel.getCPF(), pessoaFisica.getCpf());
        cv.put(dataModel.getRG(), pessoaFisica.getRg());
        dbHelper.insert(dataModel.getTabelaPessoaFisica(), cv);
    }

    public void updatePessoaFisica(PessoaFisica pessoaFisica, int id) {
        cv.put(dataModel.getNOME(), pessoaFisica.getNome());
        cv.put(dataModel.getAPELIDO(), pessoaFisica.getApelido());
        cv.put(dataModel.getCPF(), pessoaFisica.getCpf());
        cv.put(dataModel.getRG(), pessoaFisica.getRg());
        dbHelper.update(dataModel.getTabelaPessoaFisica(), cv, dataModel.getID(dataModel.getTabelaPessoaFisica())+ "=" + id);
    }
    public void deletePessoaFisica(int id){
        dbHelper.delete(dataModel.getTabelaPessoaFisica(),dataModel.getID(dataModel.getTabelaPessoaFisica())+ "=" + id);
    }

    public PessoaFisica getPessoaFisica(int id) {
        String sqlSelectPessoaFisicas = "Select * From " + dataModel.getTabelaPessoaFisica() + " WHERE " +dataModel.getID(dataModel.getTabelaPessoaFisica()) + "=" + id;
        Cursor c = dbHelper.select(sqlSelectPessoaFisicas);
        PessoaFisica pessoaFisica = new PessoaFisica();
        if (c.moveToFirst()) {
            pessoaFisica.setIdPessoaFisica(c.getInt(0));
            pessoaFisica.setNome(c.getString(1));
            pessoaFisica.setApelido(c.getString(2));
            pessoaFisica.setCpf(c.getString(3));
            pessoaFisica.setRg(c.getString(4));
        }
        return pessoaFisica;
    }


    public List<PessoaFisica> selectTodosOsPessoaFisicas() {
        DbHelper dbHelper = new DbHelper(context);
        List<PessoaFisica> listaPessoaFisica = new ArrayList<PessoaFisica>();
        String sqlSelectTodosOsAsPessoaFisicas = "Select * From centrodecusto";
        Cursor c = dbHelper.select(sqlSelectTodosOsAsPessoaFisicas);
        if (c.moveToFirst()) {
            do {
                PessoaFisica pessoaFisica = new PessoaFisica();
                pessoaFisica.setIdPessoaFisica(c.getInt(0));
                pessoaFisica.setNome(c.getString(1));
                pessoaFisica.setApelido(c.getString(2));
                pessoaFisica.setCpf(c.getString(3));
                pessoaFisica.setRg(c.getString(4));
            } while (c.moveToNext());
        }
        return listaPessoaFisica;
    }
}
