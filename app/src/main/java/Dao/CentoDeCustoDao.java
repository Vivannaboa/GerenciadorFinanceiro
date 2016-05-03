package Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import DataModel.DataModel;
import Entities.CentroDeCusto;

/**
 * Created by vivan on 12/11/2015.
 */
public class CentoDeCustoDao {
    public static final String NOME_TABELA = "Centrodecusto";
    public static final String COLUNA_ID = "id_centrodecusto";
    public static final String COLUNA_DESCRICAO = "descricao";
    public static final String COLUNA_TIPO_TRANZACAO = "tipo_tranzacao";
    public static final String COLUNA_ATIVO = "ativo";


    public static final String CRIAR_TABELA_CENTRO_DE_CUSTO() {
        String query = "CREATE TABLE " + NOME_TABELA;
        query += " (";
        query += COLUNA_ID + " " + DataModel.TIPO_INTEIRO_PK + ", ";
        query += COLUNA_DESCRICAO + " " + DataModel.TIPO_TEXTO + ", ";
        query += COLUNA_TIPO_TRANZACAO + " " + DataModel.TIPO_TEXTO + ", ";
        query += COLUNA_ATIVO + " " + DataModel.TIPO_INTEIRO + " ";
        query += ")";
        return query;
    }

    public static final String SCRIPT_DELECAO_TABELA =  "DROP TABLE IF EXISTS " + NOME_TABELA;

    private SQLiteDatabase dataBase = null;
    private static CentoDeCustoDao instance;
    public static CentoDeCustoDao getInstance(Context context) {
        if(instance == null)
            instance = new CentoDeCustoDao(context);
        return instance;
    }
    private CentoDeCustoDao(Context context) {
        DbHelper dbHelper = DbHelper.getInstance(context);
        dataBase = dbHelper.getWritableDatabase();
    }


    public void insertCentroDeCusto(CentroDeCusto centroDeCusto) {
        ContentValues values = gerarContentValeuesCentroDeCusto(centroDeCusto);
        dataBase.insert(NOME_TABELA, null, values);
    }

    public void updateCentroDeCusto(CentroDeCusto centroDeCusto) {
        ContentValues valores = gerarContentValeuesCentroDeCusto(centroDeCusto);
        String[] valoresParaSubstituir = {
                String.valueOf(centroDeCusto.getId())
        };
        dataBase.update(NOME_TABELA, valores, COLUNA_ID + " = ?", valoresParaSubstituir);
    }

    public void deleteCentroDeCusto(CentroDeCusto centroDeCusto) {
        String[] valoresParaSubstituir = {
                String.valueOf(centroDeCusto.getId())
        };
        dataBase.delete(NOME_TABELA, COLUNA_ID + " =  ?", valoresParaSubstituir);
    }

    public CentroDeCusto getCentroDeCusto(int id) {
        String queryReturnAll = "SELECT * FROM " + NOME_TABELA +" WHERE " + COLUNA_ID + "=" + id;
        Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
        List<CentroDeCusto> contas = construirCentroDeCustoPorCursor(cursor);
        CentroDeCusto centroDeCusto = new CentroDeCusto(contas.get(0).getId(),contas.get(0).getDescricao(),contas.get(0).getTipoTranzacao(),contas.get(0).isAtivo());
        return centroDeCusto;
    }


    public List<CentroDeCusto> selectTodasAsCentroDeCustos() {
        String queryReturnAll = "SELECT * FROM " + NOME_TABELA;
        Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
        List<CentroDeCusto> listaCentroDeCusto = construirCentroDeCustoPorCursor(cursor);
        return listaCentroDeCusto;
    }

    private ContentValues gerarContentValeuesCentroDeCusto(CentroDeCusto centroDeCusto) {
        ContentValues values = new ContentValues();
        values.put(COLUNA_DESCRICAO, centroDeCusto.getDescricao());
        values.put(COLUNA_TIPO_TRANZACAO, centroDeCusto.getTipoTranzacao());
        values.put(COLUNA_ATIVO, centroDeCusto.isAtivo());
        return values;
    }

    public void fecharConexao() {
        if(dataBase != null && dataBase.isOpen())
            dataBase.close();
    }
    private List<CentroDeCusto> construirCentroDeCustoPorCursor(Cursor cursor) {
        List<CentroDeCusto> centroDeCustos = new ArrayList<CentroDeCusto>();
        if(cursor == null)
            return centroDeCustos;
        try {

            if (cursor.moveToFirst()) {
                do {

                    int indexID = cursor.getColumnIndex(COLUNA_ID);
                    int indexNome = cursor.getColumnIndex(COLUNA_DESCRICAO);
                    int indexNumero = cursor.getColumnIndex(COLUNA_TIPO_TRANZACAO);
                    int indexSaldo =cursor.getColumnIndex(COLUNA_ATIVO);

                    int id = cursor.getInt(indexID);
                    String descricao = cursor.getString(indexNome);
                    String tipoTranzacao = cursor.getString(indexNumero);
                    int ativo = cursor.getInt(indexSaldo);

                    CentroDeCusto centroDeCusto = new CentroDeCusto(id, descricao, tipoTranzacao, ativo);

                    centroDeCustos.add(centroDeCusto);

                } while (cursor.moveToNext());
            }

        } finally {
            cursor.close();
        }
        return centroDeCustos;
    }
}
