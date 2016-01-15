package DataModel;

/**
 * Created by vivan on 19/10/2015.
 * teste para ver se esta comitando certo
 * vamos ver se esta certo
 */
public class DataModel {
    private static final String DB_NAME = "gerenciadoFinanceiro.sqlite";
    //tabelas
    private static final String TABELA_CONTA = "conta";
    private static final String TABELA_MOVIMENTACAO = "movimentacao";
    private static final String TABELA_CENTRODECUSTO = "centrodecusto";
    //campos
    private static final String ID = "id";
    private static final String NOME = "nome";
    private static final String NUMERO = "numero";
    private static final String SALDO = "saldo";
    private static final String DESCRICAO = "descricao";
    private static final String TIPOTRANZACAO = "tipoTranzacao";
    private static final String ATIVO = "ativo";
    private static final String VALOR ="valor" ;

    //tipos
    private static final String TIPO_TEXTO = "TEXT";
    private static final String TIPO_INTEIRO = "INTEGER";
    private static final String TIPO_INTEIRO_PK = "INTEGER PRIMARY KEY AUTOINCREMENT";
    private static final String TIPO_REAL ="REAL" ;



    public static String criarTabelaConta(){
        String query =  "CREATE TABLE " + getTABELA_CONTA();
        query += " (";
        query += getID() + " " + TIPO_INTEIRO_PK + ", ";
        query += getNOME() + " " + TIPO_TEXTO + ", ";
        query += getNUMERO() + " " + TIPO_TEXTO + ", ";
        query += getSaldo() + " " + TIPO_REAL + " ";
        query += ")";
        return query;
    }

    public static String criaTabelaMovimentacao() {

        String query = "CREATE TABLE " + getTABELA_MOVIMENTACAO();
        query += " (";
        query += getID() + " " + TIPO_INTEIRO_PK + ", ";
        query += getDESCRICAO() + " " + TIPO_TEXTO + ", ";
        query += getID()+"Conta" + " " + TIPO_INTEIRO + ", ";
        query += getID()+"CentroCusto"+ " " + TIPO_INTEIRO + ", ";
        query += getValor() + " " + TIPO_REAL + ", ";
        query += "FOREIGN KEY (idConta) REFERENCES conta(id),";
        query += "FOREIGN KEY (idCentroCusto) REFERENCES centrodecusto(id)";
        query += ")";
        return query;
    }

    public static String criaTabelaCentroDeCusto() {

        String query = "CREATE TABLE " + getTABELA_CENTRODECUSTO();
        query += " (";
        query += getID() + " " + TIPO_INTEIRO_PK + ", ";
        query += getDESCRICAO() + " " + TIPO_TEXTO + ", ";
        query += getTIPOTRANZACAO() + " " + TIPO_TEXTO + ", ";
        query += getATIVO() + " " + TIPO_INTEIRO + " ";
        query += ")";
        return query;
    }

    public static String getDB_NAME() {
        return DB_NAME;
    }


    public static String getTABELA_CONTA() {
        return TABELA_CONTA;
    }


    public static String getID() {
        return ID;
    }


    public static String getNOME() {
        return NOME;
    }


    public static String getNUMERO() {
        return NUMERO;
    }

    public static String getSaldo() {
        return SALDO;
    }

    public static String getTABELA_MOVIMENTACAO() {
        return TABELA_MOVIMENTACAO;
    }

    public static String getDESCRICAO() {
        return DESCRICAO;
    }

    public static String getTABELA_CENTRODECUSTO() {
        return TABELA_CENTRODECUSTO;
    }

    public static String getTIPOTRANZACAO() {
        return TIPOTRANZACAO;
    }

    public static String getATIVO() {
        return ATIVO;
    }

    public static String getValor() {
        return VALOR;
    }
}


