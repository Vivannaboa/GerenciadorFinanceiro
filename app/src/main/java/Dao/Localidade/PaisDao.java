package Dao.Localidade;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import Dao.DbHelper;
import DataModel.DataModel;
import Entities.Localidade.Pais;

/**
 * Created by vivan on 26/01/2016.
 */
public class PaisDao {
    public static final String NOME_TABELA = "Pais";
    public static final String COLUNA_ID = "id_pais";
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_CODIGO = "codigo";


    public static final String CRIAR_TABELA_PAIS() {
        String query = "CREATE TABLE " + NOME_TABELA;
        query += " (";
        query += COLUNA_ID + " " + DataModel.TIPO_INTEIRO_PK + ", ";
        query += COLUNA_NOME + " " + DataModel.TIPO_TEXTO + ", ";
        query += COLUNA_CODIGO + " " + DataModel.TIPO_TEXTO ;
        query += ")";
        return query;
    }

    public static final String SCRIPT_DELECAO_TABELA =  "DROP TABLE IF EXISTS " + NOME_TABELA;

    private SQLiteDatabase dataBase = null;
    private static Context contexto;
    private static PaisDao instance;

    public static PaisDao getInstance(Context context) {
        if(instance == null) {
            instance = new PaisDao(context);
            contexto = context;
        }
        return instance;
    }

    private PaisDao(Context context) {
        DbHelper dbHelper = DbHelper.getInstance(context);
        dataBase = dbHelper.getWritableDatabase();
    }
    //inserir
    public void insertPais(Pais pais) {
        ContentValues values = gerarContentValeuesPais(pais);
        dataBase.insert(NOME_TABELA, null, values);
    }
    //atualizar
    public void updatePais(Pais pais){
        ContentValues valores = gerarContentValeuesPais(pais);

        String[] valoresParaSubstituir = {
                String.valueOf(pais.getIdPais())
        };
        dataBase.update(NOME_TABELA, valores, COLUNA_ID + " = ?", valoresParaSubstituir);
    }
    //apaga
    public void deletePais(Pais pais){
        String[] valoresParaSubstituir = {
                String.valueOf(pais.getIdPais())
        };
        dataBase.delete(NOME_TABELA, COLUNA_ID + " =  ?", valoresParaSubstituir);
    }
    //busca um registro
    public Pais getPais(int id) {
        String queryReturnAll = "SELECT * FROM " + NOME_TABELA +" WHERE "+COLUNA_ID+" =" + id;
        Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
        List<Pais> paises = construirPaisPorCursor(cursor);
        Pais pais = new Pais();
        pais.setIdPais(paises.get(0).getIdPais());
        pais.setNome(paises.get(0).getNome());
        pais.setCodigo(paises.get(0).getCodigo());
        return pais;
    }

    //retorna Todos
    public List<Pais> selectTodosOsPais() {
        String queryReturnAll = "SELECT * FROM " + NOME_TABELA;
        Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
        List<Pais> listaPais = construirPaisPorCursor(cursor);
        return listaPais;
    }

    private List<Pais> construirPaisPorCursor(Cursor cursor) {
        List<Pais> paises = new ArrayList<Pais>();
        if(cursor == null)
            return paises;
        try {

            if (cursor.moveToFirst()) {
                do {
                    int indexID = cursor.getColumnIndex(COLUNA_ID);
                    int indexNome = cursor.getColumnIndex(COLUNA_NOME);
                    int indexCodigo = cursor.getColumnIndex(COLUNA_CODIGO);

                    int id = cursor.getInt(indexID);
                    String nome = cursor.getString(indexNome);
                    String codigo = cursor.getString(indexCodigo);

                    Pais pais = new Pais(id,nome,codigo);

                    paises.add(pais);

                } while (cursor.moveToNext());
            }

        } finally {
            cursor.close();
        }
        return paises;
    }

    private ContentValues gerarContentValeuesPais(Pais pais) {
        ContentValues values = new ContentValues();
        values.put(COLUNA_NOME, pais.getNome());
        values.put(COLUNA_CODIGO, pais.getCodigo());
        return values;
    }

    public void fecharConexao() {
        if(dataBase != null && dataBase.isOpen())
            dataBase.close();
    }
}
