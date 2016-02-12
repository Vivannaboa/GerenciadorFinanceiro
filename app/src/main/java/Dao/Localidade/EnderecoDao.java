package Dao.Localidade;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import Dao.DbHelper;
import DataModel.DataModel;
import Entities.Localidade.Endereco;

/**
 * Created by vivan on 26/01/2016.
 */
public class EnderecoDao {
    private Context context;
    private static DataModel dataModel = new DataModel();
//    private final DbHelper dbHelper = new DbHelper(context);
//    private final ContentValues cv = new ContentValues();
//    private final BairroDao bairroDao = new BairroDao(context);
//    private final LogradouroDao logradouroDao = new LogradouroDao(context);
//    private final CidadeDao cidadeDao = new CidadeDao(context);

    public  EnderecoDao(Context context){
        this.context=context;
    }

    public void insertEndereco(Endereco endereco) {
        DbHelper dbHelper = new DbHelper(context);
        ContentValues cv = new ContentValues();
        cv.put(dataModel.getID(dataModel.getTabelaPessoa()),endereco.getIdPessoa());
        cv.put(dataModel.getNUMERO(), endereco.getNumero());
        cv.put(dataModel.getCOMPLEMENTO(), endereco.getComplemento());
        cv.put(dataModel.getID(dataModel.getTabelaBairro()), endereco.getBairro().getIdBairro());
        cv.put(dataModel.getID(dataModel.getTabelaLogradouro()), endereco.getLogradouro().getIdLogradouro());
        cv.put(dataModel.getID(dataModel.getTabelaCidade()),endereco.getCidade().getIdCidade());
        dbHelper.insert(dataModel.getTabelaEndereco(), cv);
    }

    public void updateEndereco(Endereco endereco, int id) {
        DbHelper dbHelper = new DbHelper(context);
        ContentValues cv = new ContentValues();
        cv.put(dataModel.getID(dataModel.getTabelaPessoa()),endereco.getIdPessoa());
        cv.put(dataModel.getNUMERO(), endereco.getNumero());
        cv.put(dataModel.getCOMPLEMENTO(), endereco.getComplemento());
        cv.put(dataModel.getID(dataModel.getTabelaBairro()), endereco.getBairro().getIdBairro());
        cv.put(dataModel.getID(dataModel.getTabelaLogradouro()), endereco.getLogradouro().getIdLogradouro());
        cv.put(dataModel.getID(dataModel.getTabelaCidade()),endereco.getCidade().getIdCidade());
        dbHelper.update(dataModel.getTabelaEndereco(), cv, dataModel.getID(dataModel.getTabelaEndereco())+ "=" + id);
    }
    public void deleteEndereco(int id){
        DbHelper dbHelper = new DbHelper(context);
        ContentValues cv = new ContentValues();
        dbHelper.delete(dataModel.getTabelaEndereco(),dataModel.getID(dataModel.getTabelaEndereco())+ "=" + id);
    }

    public Endereco getEndereco(int id) {
        DbHelper dbHelper = new DbHelper(context);
        ContentValues cv = new ContentValues();
        BairroDao bairroDao = new BairroDao(context);
        LogradouroDao logradouroDao = new LogradouroDao(context);
        CidadeDao cidadeDao = new CidadeDao(context);
        String sqlSelectEnderecos = "Select * From " + dataModel.getTabelaEndereco() + " WHERE " +dataModel.getID(dataModel.getTabelaEndereco()) + "=" + id;
        Cursor c = dbHelper.select(sqlSelectEnderecos);
        Endereco endereco = new Endereco();
        if (c.moveToFirst()) {
            endereco.setIdEndereco(c.getInt(0));
            endereco.setIdPessoa(c.getInt(1));
            endereco.setNumero(c.getString(2));
            endereco.setComplemento(c.getString(3));
            endereco.setBairro(bairroDao.getBairro(c.getInt(4)));
            endereco.setLogradouro(logradouroDao.getLogradouro(c.getInt(5)));
            endereco.setCidade(cidadeDao.getCidade(c.getInt(6)));
        }
        return endereco;
    }


    public List<Endereco> selectTodosOsEnderecos() {
        DbHelper dbHelper = new DbHelper(context);
        ContentValues cv = new ContentValues();
        BairroDao bairroDao = new BairroDao(context);
        LogradouroDao logradouroDao = new LogradouroDao(context);
        CidadeDao cidadeDao = new CidadeDao(context);
        List<Endereco> listaEndereco = new ArrayList<Endereco>();
        String sqlSelectTodosOsAsEnderecos = "Select * From Endereco";
        Cursor c = dbHelper.select(sqlSelectTodosOsAsEnderecos);
        if (c.moveToFirst()) {
            do {
                Endereco endereco = new Endereco();
                endereco.setIdEndereco(c.getInt(0));
                endereco.setIdPessoa(c.getInt(1));
                endereco.setNumero(c.getString(2));
                endereco.setComplemento(c.getString(3));
                endereco.setBairro(bairroDao.getBairro(c.getInt(4)));
                endereco.setLogradouro(logradouroDao.getLogradouro(c.getInt(5)));
                endereco.setCidade(cidadeDao.getCidade(c.getInt(6)));
                listaEndereco.add(endereco);
            } while (c.moveToNext());
        }
        return listaEndereco;
    }
}
