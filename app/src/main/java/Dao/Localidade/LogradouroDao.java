package Dao.Localidade;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import Dao.DbHelper;
import DataModel.DataModel;
import Entities.Localidade.Logradouro;

/**
 * Created by vivan on 26/01/2016.
 */
public class LogradouroDao {

    public static final String NOME_TABELA = "Logradouro";
    public static final String COLUNA_ID = "id_logradouro";
    public static final String COLUNA_DESCRICAO = "descricao";


    public static final String CRIAR_TABELA_LOGRADOURO() {
        String query = "CREATE TABLE " + NOME_TABELA;
        query += " (";
        query += COLUNA_ID + " " + DataModel.TIPO_INTEIRO_PK + ", ";
        query += COLUNA_DESCRICAO + " " + DataModel.TIPO_TEXTO ;
        query += ")";
        return query;
    }

    public static final String SCRIPT_DELECAO_TABELA =  "DROP TABLE IF EXISTS " + NOME_TABELA;

    private SQLiteDatabase dataBase = null;
    private static Context contexto;
    private static LogradouroDao instance;

    public static LogradouroDao getInstance(Context context) {
        if(instance == null) {
            instance = new LogradouroDao(context);
            contexto = context;
        }
        return instance;
    }

    private LogradouroDao(Context context) {
        DbHelper dbHelper = DbHelper.getInstance(context);
        dataBase = dbHelper.getWritableDatabase();
    }
    //inserir
    public void insertLogradouro(Logradouro logradouro) {
        ContentValues values = gerarContentValeuesLogradouro(logradouro);
        dataBase.insert(NOME_TABELA, null, values);
    }
    //atualizar
    public void updateLogradouro(Logradouro logradouro){
        ContentValues valores = gerarContentValeuesLogradouro(logradouro);

        String[] valoresParaSubstituir = {
                String.valueOf(logradouro.getIdLogradouro())
        };
        dataBase.update(NOME_TABELA, valores, COLUNA_ID + " = ?", valoresParaSubstituir);
    }
    //apaga
    public void deleteLogradouro(Logradouro logradouro){
        String[] valoresParaSubstituir = {
                String.valueOf(logradouro.getIdLogradouro())
        };
        dataBase.delete(NOME_TABELA, COLUNA_ID + " =  ?", valoresParaSubstituir);
    }
    //busca um registro
    public Logradouro getLogradouro(int id) {
        String queryReturnAll = "SELECT * FROM " + NOME_TABELA +" WHERE "+COLUNA_ID+" =" + id;
        Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
        List<Logradouro> logradouros = construirLogradouroPorCursor(cursor);
        Logradouro logradouro = new Logradouro();
        logradouro.setIdLogradouro(logradouros.get(0).getIdLogradouro());
        logradouro.setDescricao(logradouros.get(0).getDescricao());
        return logradouro;
    }

    //retorna Todos
    public List<Logradouro> selectTodosOsLogradouro() {
        String queryReturnAll = "SELECT * FROM " + NOME_TABELA;
        Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
        List<Logradouro> listaLogradouro = construirLogradouroPorCursor(cursor);
        return listaLogradouro;
    }

    private List<Logradouro> construirLogradouroPorCursor(Cursor cursor) {
        List<Logradouro> logradouros = new ArrayList<Logradouro>();
        if(cursor == null)
            return logradouros;
        try {

            if (cursor.moveToFirst()) {
                do {
                    int indexID = cursor.getColumnIndex(COLUNA_ID);
                    int indexDescricao = cursor.getColumnIndex(COLUNA_DESCRICAO);


                    int id = cursor.getInt(indexID);
                    String descricao = cursor.getString(indexDescricao);

                    Logradouro logradouro = new Logradouro(id,descricao);

                    logradouros.add(logradouro);

                } while (cursor.moveToNext());
            }

        } finally {
            cursor.close();
        }
        return logradouros;
    }

    private ContentValues gerarContentValeuesLogradouro(Logradouro logradouro) {
        ContentValues values = new ContentValues();
        values.put(COLUNA_DESCRICAO, logradouro.getDescricao());
        return values;
    }

    public void fecharConexao() {
        if(dataBase != null && dataBase.isOpen())
            dataBase.close();
    }

}
