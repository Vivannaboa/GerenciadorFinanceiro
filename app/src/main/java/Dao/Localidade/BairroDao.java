package Dao.Localidade;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import Dao.DbHelper;
import DataModel.DataModel;
import Entities.Localidade.Bairro;

/**
 * Created by vivan on 23/01/2016.
 */
public class BairroDao {
    public static final String NOME_TABELA = "Bairro";
    public static final String COLUNA_ID = "id_bairro";
    public static final String COLUNA_DESCRICAO = "descricao";


    public static String CRIAR_TABELA_BAIRRO() {
        String query = "CREATE TABLE " + NOME_TABELA;
        query += "(";
        query += COLUNA_ID + " " + DataModel.TIPO_INTEIRO_PK + ", ";
        query += COLUNA_DESCRICAO + " " + DataModel.TIPO_TEXTO;
        query += ")";
        return query;
    }
    public static final String SCRIPT_DELECAO_TABELA =  "DROP TABLE IF EXISTS " + NOME_TABELA;

    private SQLiteDatabase dataBase = null;
    private static Context contexto;
    private static BairroDao instance;

    public static BairroDao getInstance(Context context) {
        if(instance == null) {
            instance = new BairroDao(context);
            contexto = context;
        }
        return instance;
    }

    private BairroDao(Context context) {
        DbHelper dbHelper = DbHelper.getInstance(context);
        dataBase = dbHelper.getWritableDatabase();
    }

    //inserir
    public void insertBairro(Bairro bairro) {
        ContentValues values = gerarContentValeuesBairro(bairro);
        dataBase.insert(NOME_TABELA, null, values);
    }

    //atualizar
    public void updateBairro(Bairro bairro) {
        ContentValues valores = gerarContentValeuesBairro(bairro);

        String[] valoresParaSubstituir = {
                String.valueOf(bairro.getIdBairro())
        };
        dataBase.update(NOME_TABELA, valores, COLUNA_ID + " = ?", valoresParaSubstituir);
    }

    //apaga
    public void deleteBairro(Bairro bairro) {
        String[] valoresParaSubstituir = {
                String.valueOf(bairro.getIdBairro())
        };
        dataBase.delete(NOME_TABELA, COLUNA_ID + " =  ?", valoresParaSubstituir);
    }

    //busca um registro
    public Bairro getBairro(int id) {
        String queryReturnAll = "SELECT * FROM " + NOME_TABELA + " WHERE " + COLUNA_ID + " =" + id;
        Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
        List<Bairro> movimentacoes = construirBairroPorCursor(cursor);
        Bairro bairro = new Bairro();
        bairro.setIdBairro(movimentacoes.get(0).getIdBairro());
        bairro.setDescricao(movimentacoes.get(0).getDescricao());
        return bairro;
    }

    //retorna Todos
    public List<Bairro> selectTodosOsBairro() {
        String queryReturnAll = "SELECT * FROM " + NOME_TABELA;
        Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
        List<Bairro> listaBairros = construirBairroPorCursor(cursor);
        return listaBairros;
    }

    private List<Bairro> construirBairroPorCursor(Cursor cursor) {
        List<Bairro> listaBairros = new ArrayList<Bairro>();
        if (cursor == null)
            return listaBairros;
        try {

            if (cursor.moveToFirst()) {
                do {

                    int indexID = cursor.getColumnIndex(COLUNA_ID);
                    int indexDescricao = cursor.getColumnIndex(COLUNA_DESCRICAO);

                    int id = cursor.getInt(indexID);
                    String descricao = cursor.getString(indexDescricao);

                    Bairro bairro = new Bairro(id, descricao);

                    listaBairros.add(bairro);

                } while (cursor.moveToNext());
            }

        } finally {
            cursor.close();
        }
        return listaBairros;
    }

    private ContentValues gerarContentValeuesBairro(Bairro bairro) {
        ContentValues values = new ContentValues();
        values.put(COLUNA_DESCRICAO, bairro.getDescricao());
        return values;
    }

    public void fecharConexao() {
        if (dataBase != null && dataBase.isOpen())
            dataBase.close();
    }

}


