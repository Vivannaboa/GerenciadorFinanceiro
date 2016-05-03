package Dao.Localidade;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import Dao.ContaDao;
import Dao.DbHelper;
import DataModel.DataModel;
import Entities.Localidade.Cidade;
import Entities.Localidade.Pais;

/**
 * Created by vivan on 26/01/2016.
 */
public class CidadeDao {
    public static final String NOME_TABELA = "Cidade";
    public static final String COLUNA_ID = "id_cidade";
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_CODIGO_DO_IBGE = "codigo_do_ibge";
    public static final String COLUNA_UF = "uf";

    public static String CRIAR_TABELA_CIDADE() {
        String query = "CREATE TABLE " + NOME_TABELA;
        query += " (";
        query += COLUNA_ID + " " + DataModel.TIPO_INTEIRO_PK + ", ";
        query += PaisDao.COLUNA_ID + " " + DataModel.TIPO_INTEIRO + ", ";
        query += COLUNA_NOME + " " + DataModel.TIPO_TEXTO + ", ";
        query += COLUNA_CODIGO_DO_IBGE + " " + DataModel.TIPO_INTEIRO  + ", ";
        query += COLUNA_UF + " " + DataModel.TIPO_TEXTO + ", ";
        query += "FOREIGN KEY ("+ PaisDao.COLUNA_ID +") REFERENCES " + NOME_TABELA +"("+PaisDao.COLUNA_ID+")";
        query += ")";
        return query;
    }


    public static final String SCRIPT_DELECAO_TABELA =  "DROP TABLE IF EXISTS " + NOME_TABELA;

    private SQLiteDatabase dataBase = null;
    private static Context contexto;
    private static CidadeDao instance;

    public static CidadeDao getInstance(Context context) {
        if(instance == null) {
            instance = new CidadeDao(context);
            contexto = context;
        }
        return instance;
    }

    private CidadeDao(Context context) {
        DbHelper dbHelper = DbHelper.getInstance(context);
        dataBase = dbHelper.getWritableDatabase();
    }

    //inserir
    public void insertCidade(Cidade cidade) {
        ContentValues values = gerarContentValeuesCidade(cidade);
        dataBase.insert(NOME_TABELA, null, values);
    }
    //atualizar
    public void updateCidade(Cidade cidade){
        ContentValues valores = gerarContentValeuesCidade(cidade);

        String[] valoresParaSubstituir = {
                String.valueOf(cidade.getIdCidade())
        };
        dataBase.update(NOME_TABELA, valores, COLUNA_ID + " = ?", valoresParaSubstituir);
    }
    //apaga
    public void deleteCidade(Cidade cidade){
        String[] valoresParaSubstituir = {
                String.valueOf(cidade.getIdCidade())
        };
        dataBase.delete(NOME_TABELA, COLUNA_ID + " =  ?", valoresParaSubstituir);
    }
    //busca um registro
    public Cidade getCidade(int id) {
        String queryReturnAll = "SELECT * FROM " + NOME_TABELA +" WHERE "+COLUNA_ID+" =" + id;
        Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
        List<Cidade> movimentacoes = construirCidadePorCursor(cursor);
        Cidade cidade = new Cidade();
        cidade.setIdCidade(movimentacoes.get(0).getIdCidade());
        cidade.setPais(movimentacoes.get(0).getPais());
        cidade.setNome(movimentacoes.get(0).getNome());
        cidade.setCodigoDoIbge(movimentacoes.get(0).getCodigoDoIbge());
        cidade.setUf(movimentacoes.get(0).getUf());
        return cidade;
    }

    //retorna Todos
    public List<Cidade> selectTodosOsCidade() {
        String queryReturnAll = "SELECT * FROM " + NOME_TABELA;
        Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
        List<Cidade> listaCidades = construirCidadePorCursor(cursor);
        return listaCidades;
    }

    private List<Cidade> construirCidadePorCursor(Cursor cursor) {
        List<Cidade> listaCidades = new ArrayList<Cidade>();
        PaisDao  paisDao = PaisDao.getInstance(contexto);
        if(cursor == null)
            return listaCidades;
        try {

            if (cursor.moveToFirst()) {
                do {

                    int indexID = cursor.getColumnIndex(COLUNA_ID);
                    int indexIdPais = cursor.getColumnIndex(PaisDao.COLUNA_ID);
                    int indexNome = cursor.getColumnIndex(COLUNA_NOME);
                    int indexCodigoDoIbge = cursor.getColumnIndex(COLUNA_CODIGO_DO_IBGE);
                    int indexUf = cursor.getColumnIndex(COLUNA_UF);

                    int id = cursor.getInt(indexID);
                    Pais pais = paisDao.getPais(cursor.getInt(indexIdPais));
                    String nome = cursor.getString(indexNome);
                    String codigoDoIbge = cursor.getString(indexCodigoDoIbge);
                    String uf =cursor.getString(indexUf);


                    Cidade cidade = new Cidade(id,pais,nome,codigoDoIbge,uf);

                    listaCidades.add(cidade);

                } while (cursor.moveToNext());
            }

        } finally {
            cursor.close();
        }
        return listaCidades;
    }

    private ContentValues gerarContentValeuesCidade(Cidade cidade) {
        ContentValues values = new ContentValues();
        values.put(PaisDao.COLUNA_CODIGO, cidade.getPais().getIdPais());
        values.put(COLUNA_NOME, cidade.getNome());
        values.put(COLUNA_CODIGO_DO_IBGE, cidade.getCodigoDoIbge());
        values.put(COLUNA_UF, cidade.getUf());
        return values;
    }

    public void fecharConexao() {
        if(dataBase != null && dataBase.isOpen())
            dataBase.close();
    }

}
