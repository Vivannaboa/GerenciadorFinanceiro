package Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Dao.Localidade.EnderecoDao;
import DataModel.DataModel;
import Entities.Localidade.Endereco;
import Entities.Pessoa;

/**
 * Created by vivan on 26/01/2016.
 */
public class PessoaDao {
    public static final String NOME_TABELA = "Pessoa";
    public static final String COLUNA_ID = "id_pessoa";
    public static final String COLUNA_ATIVO = "Ativo";
    public static final String COLUNA_DATA_CADASTRO = "data_cadastro";
    public static final String COLUNA_NOME_RAZAO_SOCIAL = "nome_razao_social";
    public static final String COLUNA_APELIDO_NOME_FANTAZIA = "apelido_nome_fantazia";
    public static final String COLUNA_CPF_CNPJ = "cpf_cnpj";
    public static final String COLUNA_RG_IE = "rg_ie";
    public static final String COLUNA_TIPO = "tipo";

    public static String CRIAR_TABELA_PESSOA() {
        String query = "CREATE TABLE " + NOME_TABELA;
        query += " (";
        query += COLUNA_ID + " " + DataModel.TIPO_INTEIRO_PK + ", ";
        query += COLUNA_ATIVO + " " + DataModel.TIPO_NUMERIC + ", ";
        query += COLUNA_DATA_CADASTRO + " " + DataModel.TIPO_TEXTO + ", ";
        query += COLUNA_NOME_RAZAO_SOCIAL + " " + DataModel.TIPO_TEXTO + ", ";
        query += COLUNA_APELIDO_NOME_FANTAZIA + " " + DataModel.TIPO_TEXTO + ", ";
        query += COLUNA_CPF_CNPJ + " " + DataModel.TIPO_TEXTO + ", ";
        query += COLUNA_RG_IE + " " + DataModel.TIPO_TEXTO + ", ";
        query += COLUNA_TIPO + " " + DataModel.TIPO_TEXTO + " ";
        query += ")";
        return query;
    }

    public static final String SCRIPT_DELECAO_TABELA = "DROP TABLE IF EXISTS " + NOME_TABELA;

    private SQLiteDatabase dataBase = null;
    private static Context contexto;
    private static PessoaDao instance;

    public static PessoaDao getInstance(Context context) {
        if (instance == null) {
            instance = new PessoaDao(context);
            contexto = context;
        }
        return instance;
    }

    private PessoaDao(Context context) {
        DbHelper dbHelper = DbHelper.getInstance(context);
        dataBase = dbHelper.getWritableDatabase();
    }

    //inserir
    public void insertPessoa(Pessoa pessoa) {
        ContentValues values = gerarContentValeuesPessoa(pessoa);
        dataBase.insert(NOME_TABELA, null, values);
    }

    //atualizar
    public void updatePessoa(Pessoa pessoa) {
        ContentValues valores = gerarContentValeuesPessoa(pessoa);

        String[] valoresParaSubstituir = {
                String.valueOf(pessoa.getIdPessoa())
        };
        dataBase.update(NOME_TABELA, valores, COLUNA_ID + " = ?", valoresParaSubstituir);
    }

    //apaga
    public void deletePessoa(Pessoa pessoa) {
        String[] valoresParaSubstituir = {
                String.valueOf(pessoa.getIdPessoa())
        };
        dataBase.delete(NOME_TABELA, COLUNA_ID + " =  ?", valoresParaSubstituir);
    }

    //busca um registro
    public Pessoa getPessoa(int id) throws ParseException {
        EnderecoDao enderecoDao = EnderecoDao.getInstance(contexto);
        String queryReturnAll = "SELECT * FROM " + NOME_TABELA + " WHERE " + COLUNA_ID + " =" + id;
        Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
        List<Pessoa> listPessoa = construirPessoaPorCursor(cursor);
        Pessoa pessoa = new Pessoa();
        pessoa.setIdPessoa(listPessoa.get(0).getIdPessoa());
        pessoa.setAtivo(listPessoa.get(0).isAtivo());
        pessoa.setDataCadastro(listPessoa.get(0).getDataCadastro().toString());
        pessoa.setNomeRazaoSocial(listPessoa.get(0).getNomeRazaoSocial());
        pessoa.setApelidoNomeFantasia(listPessoa.get(0).getApelidoNomeFantasia());
        pessoa.setCpfCnpj(listPessoa.get(0).getCpfCnpj());
        pessoa.setRgIe(listPessoa.get(0).getRgIe());
        pessoa.setTipo(listPessoa.get(0).getTipo());
        pessoa.setEnderecos((ArrayList<Endereco>) enderecoDao.selectTodosOsEnderecoPessoa(listPessoa.get(0).getIdPessoa()));
        return pessoa;
    }

    //retorna Todos
    public List<Pessoa> selectTodosAsPessoa() {
        String queryReturnAll = "SELECT * FROM " + NOME_TABELA;
        Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
        List<Pessoa> listaPessoas = construirPessoaPorCursor(cursor);
        return listaPessoas;
    }

    private List<Pessoa> construirPessoaPorCursor(Cursor cursor) {
        EnderecoDao enderecoDao = EnderecoDao.getInstance(contexto);
        List<Pessoa> listaPessoas = new ArrayList<Pessoa>();
        if (cursor == null)
            return listaPessoas;
        try {

            if (cursor.moveToFirst()) {
                do {

                    int indexID = cursor.getColumnIndex(COLUNA_ID);
                    int indexDescricao = cursor.getColumnIndex(COLUNA_ATIVO);
                    int indexData = cursor.getColumnIndex(COLUNA_DATA_CADASTRO);
                    int indexAtivo = cursor.getColumnIndex(COLUNA_ATIVO);
                    int indexNomeRazaoSocial = cursor.getColumnIndex(COLUNA_NOME_RAZAO_SOCIAL);
                    int indexApelidoNomeFantasia = cursor.getColumnIndex(COLUNA_APELIDO_NOME_FANTAZIA);
                    int indexCpfCnpj = cursor.getColumnIndex(COLUNA_CPF_CNPJ);
                    int indexRgIe = cursor.getColumnIndex(COLUNA_RG_IE);
                    int indexTipo = cursor.getColumnIndex(COLUNA_TIPO);


                    int id = cursor.getInt(indexID);
                    String dataCadastro = cursor.getString(indexData);
                    int ativo = cursor.getInt(indexAtivo);
                    String nomeRazaoSocial = cursor.getString(indexNomeRazaoSocial);
                    String apelidoNomeFantasia = cursor.getString(indexApelidoNomeFantasia);
                    String cpfCnpj = cursor.getString(indexCpfCnpj);
                    String rgIe = cursor.getString(indexRgIe);
                    String tipo = cursor.getString(indexTipo);
                    ArrayList<Endereco> enderecos = (ArrayList<Endereco>) enderecoDao.selectTodosOsEnderecoPessoa(cursor.getInt(indexID));

                    Pessoa pessoa = new Pessoa();
                    pessoa.setIdPessoa(id);
                    pessoa.setAtivo(ativo);
                    pessoa.setDataCadastro(dataCadastro);
                    pessoa.setApelidoNomeFantasia(nomeRazaoSocial);
                    pessoa.setApelidoNomeFantasia(apelidoNomeFantasia);
                    pessoa.setCpfCnpj(cpfCnpj);
                    pessoa.setRgIe(rgIe);
                    pessoa.setTipo(tipo);
                    pessoa.setEnderecos(enderecos);

                    listaPessoas.add(pessoa);

                } while (cursor.moveToNext());
            }

        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return listaPessoas;
    }

    private ContentValues gerarContentValeuesPessoa(Pessoa pessoa) {
        ContentValues values = new ContentValues();
        values.put(COLUNA_ATIVO, pessoa.isAtivo());
        values.put(COLUNA_DATA_CADASTRO, pessoa.getDataCadastro().toString());
        values.put(COLUNA_NOME_RAZAO_SOCIAL, pessoa.getNomeRazaoSocial());
        values.put(COLUNA_APELIDO_NOME_FANTAZIA, pessoa.getApelidoNomeFantasia());
        values.put(COLUNA_CPF_CNPJ, pessoa.getCpfCnpj());
        values.put(COLUNA_RG_IE, pessoa.getRgIe());
        return values;
    }

    public void fecharConexao() {
        if (dataBase != null && dataBase.isOpen())
            dataBase.close();
    }

}
