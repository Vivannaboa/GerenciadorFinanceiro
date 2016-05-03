package Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import DataModel.DataModel;
import Entities.Movimentacao;

public class MovimentacaoDao {

    public static final String NOME_TABELA = "Movimentacao";
    public static final String COLUNA_ID = "id_movimentacao";
    public static final String COLUNA_DESCRICAO = "descricao";
    public static final String COLUNA_VALOR = "numero";


    public static final String CRIAR_TABELA_MOVIMENTACAO() {
        String query = "CREATE TABLE " + NOME_TABELA;
        query += " (";
        query += COLUNA_ID + " " + DataModel.TIPO_INTEIRO_PK + ", ";
        query +=  COLUNA_DESCRICAO+ " " + DataModel.TIPO_TEXTO + ", ";
        query += ContaDao.COLUNA_ID + " " + DataModel.TIPO_INTEIRO + ", ";
        query += CentoDeCustoDao.COLUNA_ID + " " + DataModel.TIPO_INTEIRO + ", ";
        query += COLUNA_VALOR + " " + DataModel.TIPO_REAL + ", ";
        query += "FOREIGN KEY (" + ContaDao.COLUNA_ID +") REFERENCES "+ ContaDao.NOME_TABELA+"("+ ContaDao.COLUNA_ID +"),";
        query += "FOREIGN KEY (" + CentoDeCustoDao.COLUNA_ID  +") REFERENCES " +CentoDeCustoDao.NOME_TABELA + "(" + CentoDeCustoDao.COLUNA_ID +")";
        query += ")";
        return query;
    }

    public static final String SCRIPT_DELECAO_TABELA =  "DROP TABLE IF EXISTS " + NOME_TABELA;

    private SQLiteDatabase dataBase = null;
    private static Context contexto;
    private static MovimentacaoDao instance;

    public static MovimentacaoDao getInstance(Context context) {
        if(instance == null) {
            instance = new MovimentacaoDao(context);
            contexto = context;
        }
        return instance;
    }

    private MovimentacaoDao(Context context) {
        DbHelper dbHelper = DbHelper.getInstance(context);
        dataBase = dbHelper.getWritableDatabase();
    }
    //inserir
    public void insertMovimentacao(Movimentacao movimentacao) {
        ContentValues values = gerarContentValeuesMovimentacao(movimentacao);
        dataBase.insert(NOME_TABELA, null, values);
    }
    //atualizar
    public void updateMovimentacao(Movimentacao movimentacao){
        ContentValues valores = gerarContentValeuesMovimentacao(movimentacao);

        String[] valoresParaSubstituir = {
                String.valueOf(movimentacao.getId())
        };
        dataBase.update(NOME_TABELA, valores, COLUNA_ID + " = ?", valoresParaSubstituir);
    }
    //apaga
    public void deleteMovimentacao(Movimentacao movimentacao){
        String[] valoresParaSubstituir = {
                String.valueOf(movimentacao.getId())
        };
        dataBase.delete(NOME_TABELA, COLUNA_ID + " =  ?", valoresParaSubstituir);
    }
    //busca um registro
    public Movimentacao getMovimentacao(int id) {
        String queryReturnAll = "SELECT * FROM " + NOME_TABELA +" WHERE "+COLUNA_ID+" =" + id;
        Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
        List<Movimentacao> movimentacoes = construirMovimentacaoPorCursor(cursor);
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setId(movimentacoes.get(0).getId());
        movimentacao.setDescricao(movimentacoes.get(0).getDescricao());
        movimentacao.setConta(movimentacoes.get(0).getConta());
        movimentacao.setCentroDeCusto(movimentacoes.get(0).getCentroDeCusto());
        movimentacao.setValor(movimentacoes.get(0).getValor());
        return movimentacao;
    }

    //retorna Todos
    public List<Movimentacao> selectTodosOsMovimentacao() {
        String queryReturnAll = "SELECT * FROM " + NOME_TABELA;
        Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
        List<Movimentacao> listaMovimentacao = construirMovimentacaoPorCursor(cursor);
        return listaMovimentacao;
    }

    private List<Movimentacao> construirMovimentacaoPorCursor(Cursor cursor) {
        List<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();
        ContaDao contaDao = ContaDao.getInstance(contexto);
        CentoDeCustoDao centroDeCusto= CentoDeCustoDao.getInstance(contexto);
        if(cursor == null)
            return movimentacoes;
        try {

            if (cursor.moveToFirst()) {
                do {

                    int indexID = cursor.getColumnIndex(COLUNA_ID);
                    int indexDescricao = cursor.getColumnIndex(COLUNA_DESCRICAO);
                    int indexIdConta = cursor.getColumnIndex(ContaDao.COLUNA_ID);
                    int indexIdCentroDeCusto = cursor.getColumnIndex(CentoDeCustoDao.COLUNA_ID);
                    int indexValor = cursor.getColumnIndex(COLUNA_VALOR);

                    int id = cursor.getInt(indexID);
                    String descricao = cursor.getString(indexDescricao);
                    int idConta = cursor.getInt(indexIdConta);
                    int idCentroDeCusto = cursor.getInt(indexIdCentroDeCusto);
                    Double valor =cursor.getDouble(indexValor);


                    Movimentacao movimentacao = new Movimentacao();
                    movimentacao.setId(id);
                    movimentacao.setDescricao(descricao);
                    movimentacao.setConta(contaDao.getConta(idConta));
                    movimentacao.setCentroDeCusto(centroDeCusto.getCentroDeCusto(idCentroDeCusto));
                    movimentacao.setValor(valor);

                    movimentacoes.add(movimentacao);

                } while (cursor.moveToNext());
            }

        } finally {
            cursor.close();
        }
        return movimentacoes;
    }

    private ContentValues gerarContentValeuesMovimentacao(Movimentacao movimentacao) {
        ContentValues values = new ContentValues();
        values.put(COLUNA_DESCRICAO, movimentacao.getDescricao());
        values.put(ContaDao.COLUNA_ID, movimentacao.getConta().getId());
        values.put(CentoDeCustoDao.COLUNA_ID, movimentacao.getCentroDeCusto().getId());
        values.put(COLUNA_VALOR, movimentacao.getValor());
        return values;
    }

    public void fecharConexao() {
        if(dataBase != null && dataBase.isOpen())
            dataBase.close();
    }

}
