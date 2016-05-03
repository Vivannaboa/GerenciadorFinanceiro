package Dao.Localidade;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import Dao.ContaDao;
import Dao.DbHelper;
import Dao.PessoaDao;
import DataModel.DataModel;
import Entities.Localidade.Bairro;
import Entities.Localidade.Cidade;
import Entities.Localidade.Endereco;
import Entities.Localidade.Endereco;
import Entities.Localidade.Logradouro;

/**
 * Created by vivan on 26/01/2016.
 */
public class EnderecoDao {

    public static final String NOME_TABELA = "Endereco";
    public static final String COLUNA_ID = "id_endereco";
    public static final String COLUNA_NUMERO = "numero";
    public static final String COLUNA_COMPLEMENTO = "complemento";

    public static final String CRIAR_TABELA_ENDERECO() {
        String query = "CREATE TABLE " + NOME_TABELA;
        query += " (";
        query += COLUNA_ID+ " " + DataModel.TIPO_INTEIRO_PK + ", ";
        query += PessoaDao.COLUNA_ID + " " + DataModel.TIPO_INTEIRO + ", ";
        query += COLUNA_NUMERO + " " + DataModel.TIPO_TEXTO + ", ";
        query += COLUNA_COMPLEMENTO + " " + DataModel.TIPO_TEXTO + ", ";
        query += BairroDao.COLUNA_ID + " " + DataModel.TIPO_INTEIRO + ", ";
        query += LogradouroDao.COLUNA_ID + " " + DataModel.TIPO_INTEIRO + ", ";
        query += CidadeDao.COLUNA_ID + " " + DataModel.TIPO_INTEIRO + ", ";
        query += "FOREIGN KEY ("+ BairroDao.COLUNA_ID +") REFERENCES " +BairroDao.NOME_TABELA+"("+BairroDao.COLUNA_ID+"),";
        query += "FOREIGN KEY ("+ LogradouroDao.COLUNA_ID +") REFERENCES " +LogradouroDao.NOME_TABELA+"("+LogradouroDao.COLUNA_ID+"),";
        query += "FOREIGN KEY ("+ PessoaDao.COLUNA_ID +") REFERENCES " +PessoaDao.NOME_TABELA+"("+PessoaDao.COLUNA_ID+")";
        query += "FOREIGN KEY ("+ CidadeDao.COLUNA_ID  +") REFERENCES " +CidadeDao.NOME_TABELA+"("+ ContaDao.COLUNA_ID+")";
        query += ")";
        return query;
    }


    public static final String SCRIPT_DELECAO_TABELA =  "DROP TABLE IF EXISTS " + NOME_TABELA;

    private SQLiteDatabase dataBase = null;
    private static Context contexto;
    private static EnderecoDao instance;

    public static EnderecoDao getInstance(Context context) {
        if(instance == null) {
            instance = new EnderecoDao(context);
            contexto = context;
        }
        return instance;
    }

    private EnderecoDao(Context context) {
        DbHelper dbHelper = DbHelper.getInstance(context);
        dataBase = dbHelper.getWritableDatabase();
    }
    //inserir
    public void insertEndereco(Endereco endereco) {
        ContentValues values = gerarContentValeuesEndereco(endereco);
        dataBase.insert(NOME_TABELA, null, values);
    }
    //atualizar
    public void updateEndereco(Endereco endereco){
        ContentValues valores = gerarContentValeuesEndereco(endereco);

        String[] valoresParaSubstituir = {
                String.valueOf(endereco.getIdEndereco())
        };
        dataBase.update(NOME_TABELA, valores, COLUNA_ID + " = ?", valoresParaSubstituir);
    }
    //apaga
    public void deleteEndereco(Endereco endereco){
        String[] valoresParaSubstituir = {
                String.valueOf(endereco.getIdEndereco())
        };
        dataBase.delete(NOME_TABELA, COLUNA_ID + " =  ?", valoresParaSubstituir);
    }
    //busca um registro
    public Endereco getEndereco(int id) {
        String queryReturnAll = "SELECT * FROM " + NOME_TABELA +" WHERE "+COLUNA_ID+" =" + id;
        Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
        List<Endereco> enderecos = construirEnderecoPorCursor(cursor);
        Endereco endereco = new Endereco();
        endereco.setIdEndereco(enderecos.get(0).getIdEndereco());
        endereco.setIdPessoa(enderecos.get(0).getIdPessoa());
        endereco.setBairro(enderecos.get(0).getBairro());
        endereco.setCidade(enderecos.get(0).getCidade());
        endereco.setLogradouro(enderecos.get(0).getLogradouro());
        endereco.setComplemento(enderecos.get(0).getComplemento());
        endereco.setNumero(enderecos.get(0).getNumero());
        return endereco;
    }

    //retorna Todos
    public List<Endereco> selectTodosOsEnderecoPessoa(int idPessoa) {
        String queryReturnAll = "SELECT * FROM " + NOME_TABELA +" WHERE "+PessoaDao.COLUNA_ID+" =" + idPessoa;
        Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
        List<Endereco> listaEndereco = construirEnderecoPorCursor(cursor);
        return listaEndereco;
    }

    private List<Endereco> construirEnderecoPorCursor(Cursor cursor) {
        List<Endereco> enderecos = new ArrayList<Endereco>();
        BairroDao bairroDao = BairroDao.getInstance(contexto);
        CidadeDao cidadeDao = CidadeDao.getInstance(contexto);
        LogradouroDao logradouroDao =LogradouroDao.getInstance(contexto);
        if(cursor == null)
            return enderecos;
        try {

            if (cursor.moveToFirst()) {
                do {
                    int indexID = cursor.getColumnIndex(COLUNA_ID);
                    int indexIdPessoa = cursor.getColumnIndex(PessoaDao.COLUNA_ID);
                    int indexIdBairro = cursor.getColumnIndex(BairroDao.COLUNA_ID);
                    int indexIdCidade = cursor.getColumnIndex(CidadeDao.COLUNA_ID);
                    int indexIdLogradouro = cursor.getColumnIndex(LogradouroDao.COLUNA_ID);
                    int indexNumero = cursor.getColumnIndex(COLUNA_NUMERO);
                    int indexComplemento = cursor.getColumnIndex(COLUNA_COMPLEMENTO);

                    int id = cursor.getInt(indexID);
                    int idPessoa = cursor.getInt(indexIdPessoa);
                    Bairro bairro = bairroDao.getBairro(cursor.getInt(indexIdBairro));
                    Cidade cidade = cidadeDao.getCidade(cursor.getInt(indexIdCidade));
                    Logradouro logradouro = logradouroDao.getLogradouro(cursor.getInt(indexIdLogradouro));
                    String numero = cursor.getString(indexNumero);
                    String complemento = cursor.getString(indexComplemento);

                    Endereco endereco = new Endereco(
                            id,
                            idPessoa,
                            bairro,
                            logradouro,
                            cidade,
                            numero,
                            complemento
                    );

                    enderecos.add(endereco);

                } while (cursor.moveToNext());
            }

        } finally {
            cursor.close();
        }
        return enderecos;
    }

    private ContentValues gerarContentValeuesEndereco(Endereco endereco) {
        ContentValues values = new ContentValues();
        values.put(PessoaDao.COLUNA_ID , endereco.getIdPessoa());
        values.put(COLUNA_NUMERO,endereco.getNumero());
        values.put(COLUNA_COMPLEMENTO,endereco.getComplemento());
        values.put(BairroDao.COLUNA_ID,endereco.getBairro().getIdBairro());
        values.put(LogradouroDao.COLUNA_ID,endereco.getLogradouro().getIdLogradouro());
        values.put(CidadeDao.COLUNA_ID,endereco.getCidade().getIdCidade());
        return values;
    }

    public void fecharConexao() {
        if(dataBase != null && dataBase.isOpen())
            dataBase.close();
    }


}
