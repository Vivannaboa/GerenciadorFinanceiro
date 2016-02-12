package Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import Dao.Localidade.PaisDao;
import DataModel.DataModel;
import Entities.PessoaJuridica;

/**
 * Created by vivan on 26/01/2016.
 */
public class PessoaJuridicaDao {
    private Context context;
    private static DataModel dataModel = new DataModel();
    private final DbHelper dbHelper = new DbHelper(context);
    private final ContentValues cv = new ContentValues();
    private final PaisDao paisDao = new PaisDao(context);

    public  PessoaJuridicaDao(Context context){
        this.context=context;
    }

    public void insertPessoaJuridica(PessoaJuridica pessoaJuridica) {
        cv.put(dataModel.getRazaoSocial(), pessoaJuridica.getRazaoSocial());
        cv.put(dataModel.getNomeFantasia(), pessoaJuridica.getNomeFantasia());
        cv.put(dataModel.getCNPJ(), pessoaJuridica.getCnpj());
        cv.put(dataModel.getIE(), pessoaJuridica.getIe());
        dbHelper.insert(dataModel.getTabelaPessoaJuridica(), cv);
    }

    public void updatePessoaJuridica(PessoaJuridica pessoaJuridica, int id) {
        cv.put(dataModel.getRazaoSocial(), pessoaJuridica.getRazaoSocial());
        cv.put(dataModel.getNomeFantasia(), pessoaJuridica.getNomeFantasia());
        cv.put(dataModel.getCNPJ(), pessoaJuridica.getCnpj());
        cv.put(dataModel.getIE(), pessoaJuridica.getIe());
        dbHelper.update(dataModel.getTabelaPessoaJuridica(), cv, dataModel.getID(dataModel.getTabelaPessoaJuridica())+ "=" + id);
    }
    public void deletePessoaJuridica(int id){
        dbHelper.delete(dataModel.getTabelaPessoaJuridica(),dataModel.getID(dataModel.getTabelaPessoaJuridica())+ "=" + id);
    }

    public PessoaJuridica getPessoaJuridica(int id) {
        String sqlSelectPessoaJuridicas = "Select * From " + dataModel.getTabelaPessoaJuridica() + " WHERE " +dataModel.getID(dataModel.getTabelaPessoaJuridica()) + "=" + id;
        Cursor c = dbHelper.select(sqlSelectPessoaJuridicas);
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        if (c.moveToFirst()) {
            pessoaJuridica.setIdPessoaJuridica(c.getInt(0));
            pessoaJuridica.setRazaoSocial(c.getString(1));
            pessoaJuridica.setNomeFantasia(c.getString(2));
            pessoaJuridica.setCnpj(c.getString(3));
            pessoaJuridica.setIe(c.getString(4));
        }
        return pessoaJuridica;
    }


    public List<PessoaJuridica> selectTodosOsPessoaJuridicas() {
        DbHelper dbHelper = new DbHelper(context);
        List<PessoaJuridica> listaPessoaJuridica = new ArrayList<PessoaJuridica>();
        String sqlSelectTodosOsAsPessoaJuridicas = "Select * From centrodecusto";
        Cursor c = dbHelper.select(sqlSelectTodosOsAsPessoaJuridicas);
        if (c.moveToFirst()) {
            do {
                PessoaJuridica pessoaJuridica = new PessoaJuridica();
                pessoaJuridica.setIdPessoaJuridica(c.getInt(0));
                pessoaJuridica.setRazaoSocial(c.getString(1));
                pessoaJuridica.setNomeFantasia(c.getString(2));
                pessoaJuridica.setCnpj(c.getString(3));
                pessoaJuridica.setIe(c.getString(4));
            } while (c.moveToNext());
        }
        return listaPessoaJuridica;
    }
}
