package Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Dao.Localidade.EnderecoDao;
import Dao.Localidade.PaisDao;
import DataModel.DataModel;
import Entities.Pessoa;
import Entities.PessoaFisica;

/**
 * Created by vivan on 26/01/2016.
 */
public class PessoaDao {
    private Context context;
    private static DataModel dataModel = new DataModel();
    private final DbHelper dbHelper = new DbHelper(context);
    private final ContentValues cv = new ContentValues();
    private final EnderecoDao enderecoDao = new EnderecoDao(context);

    public  PessoaDao(Context context){
        this.context=context;
    }

    public void insertPessoa(Pessoa pessoa) {
        cv.put(dataModel.getDataCadastro(), pessoa.getDataCadastro().toString());
        cv.put(dataModel.getATIVO(), pessoa.isAtivo());
        dbHelper.insert(dataModel.getTabelaPessoa(), cv);
    }

    public void updatePessoa(Pessoa pessoa, int id) {
        cv.put(dataModel.getDataCadastro(), pessoa.getDataCadastro().toString());
        cv.put(dataModel.getATIVO(), pessoa.isAtivo());
        dbHelper.update(dataModel.getTabelaPessoa(), cv, dataModel.getID(dataModel.getTabelaPessoa())+ "=" + id);
    }
    public void deletePessoa(int id){
        dbHelper.delete(dataModel.getTabelaPessoa(),dataModel.getID(dataModel.getTabelaPessoa())+ "=" + id);
    }

    public Pessoa getPessoa(int id) throws ParseException {
        String sqlSelectPessoas = "Select * From " + dataModel.getTabelaPessoa() + " WHERE " +dataModel.getID(dataModel.getTabelaPessoa()) + "=" + id;
        Cursor c = dbHelper.select(sqlSelectPessoas);
        Pessoa pessoa = new Pessoa();
        if (c.moveToFirst()) {
            pessoa.setIdPessoa(c.getInt(0));
            try {
                pessoa.setDataCadastro(c.getString(1));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            pessoa.setAtivo(c.getInt(2));
        }
        return pessoa;
    }

    public List<Pessoa> selectTodosOsPessoas() {
        DbHelper dbHelper = new DbHelper(context);
        List<Pessoa> listaPessoa = new ArrayList<Pessoa>();
        String sqlSelectTodosOsAsPessoas = "Select * From centrodecusto";
        Cursor c = dbHelper.select(sqlSelectTodosOsAsPessoas);
        if (c.moveToFirst()) {
            do {
                Pessoa pessoa = new Pessoa();
                pessoa.setIdPessoa(c.getInt(0));
                try {
                    pessoa.setDataCadastro(c.getString(1));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                pessoa.setAtivo(c.getInt(2));
            } while (c.moveToNext());
        }
        return listaPessoa;
    }
}
