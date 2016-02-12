package DataModel;

import java.util.Stack;

/**
 * Created by vivan on 19/10/2015.
 * teste para ver se esta comitando certo
 * vamos ver se esta certo
 */
public class DataModel {
    private static final String DB_NAME = "gerenciadoFinanceiro.sqlite";
    //tabelas
    private static final String TABELA_CONTA = "Conta";
    private static final String TABELA_MOVIMENTACAO = "Movimentacao";
    private static final String TABELA_CENTRODECUSTO = "Centrodecusto";
    private static final String TABELA_BAIRRO = "Bairro";
    private static final String TABELA_CIDADE = "Cidade";
    private static final String TABELA_ENDERECO ="Endereco" ;
    private static final String TABELA_LOGRADOURO = "Logradouro";
    private static final String TABELA_PAIS="Pais";
    private static final String TABELA_PESSOA="Pessoa";
    private static final String TABELA_PESSOAFISICA="Pessoa_fisica";
    private static final String TABELA_PESSOAJURIDICA="Pessoa_juridica";
    private static final String TABELA_TELEFONE = "Telefone";
    private static final String TABELA_ESTADO = "Estado";


    //campos
    private static  String SEPARADOR = "_";
    private static  String ID = "id";
    private static  String NOME = "nome";
    private static  String NUMERO = "numero";
    private static  String SALDO = "saldo";
    private static  String DESCRICAO = "descricao";
    private static  String TIPOTRANZACAO = "tipoTranzacao";
    private static  String ATIVO = "ativo";
    private static  String VALOR ="valor" ;
    private static  String COMPLEMENTO = "complemento";
    private static  String DATA_CADASTRO = "data_cadastro";
    private static  String APELIDO = "apelido";
    private static  String CPF = "cpf";
    private static  String RG = "rg";
    private static  String CNPJ = "cnpj";
    private static  String IE = "ie";
    private static  String NOME_FANTASIA = "nome_fantasia";
    private static  String RAZAO_SOCIAL = "razao_social";
    private static  String UF = "uf";
    private static  String CODIGO_DO_IBGE = "codigo_do_ibge";

    //tipos
    private static final String TIPO_TEXTO = "TEXT";
    private static final String TIPO_INTEIRO = "INTEGER";
    private static final String TIPO_INTEIRO_PK = "INTEGER PRIMARY KEY AUTOINCREMENT";
    private static final String TIPO_REAL ="REAL" ;
    private static final String TIPO_NUMERIC = "NUMERIC";
    private static final String TIPO_BLOB = "BLOB";


    public static String criarTabelaConta(){
        String query =  "CREATE TABLE " + getTABELA_CONTA();
        query += " (";
        query += getID(getTABELA_CONTA())+ " " + TIPO_INTEIRO_PK + ", ";
        query += getNOME() + " " + TIPO_TEXTO + ", ";
        query += getNUMERO() + " " + TIPO_TEXTO + ", ";
        query += getSaldo() + " " + TIPO_REAL + " ";
        query += ")";
        return query;
    }
    public static String criaTabelaMovimentacao() {
        String query = "CREATE TABLE " + getTABELA_MOVIMENTACAO();
        query += " (";
        query += getID(getTABELA_MOVIMENTACAO()) + " " + TIPO_INTEIRO_PK + ", ";
        query += getDESCRICAO() + " " + TIPO_TEXTO + ", ";
        query += getID(getTabelaConta()) + " " + TIPO_INTEIRO + ", ";
        query += getID(getTabelaCentrodecusto()) + " " + TIPO_INTEIRO + ", ";
        query += getValor() + " " + TIPO_REAL + ", ";
        query += "FOREIGN KEY (" + getID(getTABELA_CONTA())+") REFERENCES "+getTABELA_CONTA()+"("+getID(getTABELA_CONTA())+"),";
        query += "FOREIGN KEY (" + getID(getTABELA_CENTRODECUSTO()) +") REFERENCES " +getTabelaCentrodecusto() + "(" +getID(getTabelaCentrodecusto())+")";
        query += ")";
        return query;
    }
    public static String criaTabelaCentroDeCusto() {

        String query = "CREATE TABLE " + getTABELA_CENTRODECUSTO();
        query += " (";
        query += getID(getTABELA_CENTRODECUSTO())+" " + TIPO_INTEIRO_PK + ", ";
        query += getDESCRICAO() + " " + TIPO_TEXTO + ", ";
        query += getTIPOTRANZACAO() + " " + TIPO_TEXTO + ", ";
        query += getATIVO() + " " + TIPO_INTEIRO + " ";
        query += ")";
        return query;
    }
    public static String criaTabelaBairro() {
        String query = "CREATE TABLE " + getTabelaBairro();
        query += "(";
        query += getID(getTabelaBairro()) + " " + TIPO_INTEIRO_PK + ", ";
        query += getDESCRICAO() + " " + TIPO_TEXTO;
        query += ")";
        return query;
    }
    public static String criaTabelaCidade() {
        String query = "CREATE TABLE " + getTabelaCidade();
        query += " (";
        query += getID(getTabelaCidade())+ " " + TIPO_INTEIRO_PK + ", ";
        query += getID(getTabelaPais()) + " " + TIPO_INTEIRO + ", ";
        query += getNOME() + " " + TIPO_TEXTO + ", ";
        query += getCodigoDoIbge() + " " + getTipoInteiro() + ", ";
        query += getUF() + " " + TIPO_TEXTO + ", ";
        query += "FOREIGN KEY ("+getID(getTabelaPais()) +") REFERENCES " +getTabelaCidade()+"("+getID(getTabelaCidade())+")";
        query += ")";
        return query;
    }
    public static String criaTabelaEndereco() {
        String query = "CREATE TABLE " + getTabelaEndereco();
        query += " (";
        query += getID(getTabelaEndereco())+ " " + TIPO_INTEIRO_PK + ", ";
        query += getID(getTabelaPessoa())  + " " + TIPO_INTEIRO + ", ";
        query += getNUMERO() + " " + TIPO_TEXTO + ", ";
        query += getCOMPLEMENTO() + " " + TIPO_TEXTO + ", ";
        query += getID(getTabelaBairro()) + " " + TIPO_INTEIRO + ", ";
        query += getID(getTabelaLogradouro()) + " " + TIPO_INTEIRO + ", ";
        query += getID(getTabelaCidade()) + " " + TIPO_INTEIRO + ", ";
        query += "FOREIGN KEY ("+ getID(getTabelaBairro()) +") REFERENCES " +getTabelaBairro()+"("+getID(getTabelaBairro())+"),";
        query += "FOREIGN KEY ("+ getID(getTabelaLogradouro()) +") REFERENCES " +getTabelaLogradouro()+"("+getID(getTabelaLogradouro())+"),";
        query += "FOREIGN KEY ("+ getID(getTabelaPessoa()) +") REFERENCES " +getTabelaPessoa()+"("+getID(getTabelaPessoa())+")";
        query += "FOREIGN KEY ("+ getID(getTabelaCidade()) +") REFERENCES " +getTabelaCidade()+"("+getID(getTabelaCidade())+")";
        query += ")";
        return query;
    }
    public static String criaTabelaLogradouro() {
        String query = "CREATE TABLE " + getTabelaLogradouro();
        query += " (";
        query += getID(getTabelaLogradouro()) + " " + TIPO_INTEIRO_PK + ", ";
        query += getDESCRICAO() + " " + TIPO_TEXTO ;
        query += ")";
        return query;
    }
    public static String criaTabelaPais() {
        String query = "CREATE TABLE " + getTabelaPais();
        query += " (";
        query += getID(getTabelaPais()) + " " + TIPO_INTEIRO_PK + ", ";
        query += getNOME() + " " + TIPO_TEXTO ;
        query += ")";
        return query;
    }
    public static String criaTabelaPessoa() {
        String query = "CREATE TABLE " + getTabelaPessoa();
        query += " (";
        query += getID(getTabelaPessoa()) + " " + TIPO_INTEIRO_PK + ", ";
        query += getATIVO() + " "  + getTipoNumeric() + ", ";
        query += getDataCadastro() + " " + TIPO_TEXTO + ", ";
        query += getID(getTabelaEndereco()) + " " + TIPO_INTEIRO + ", ";
        query += "FOREIGN KEY (" + getID( getTabelaEndereco()) +") REFERENCES " +getTabelaEndereco()+"("+getID(getTabelaEndereco())+")";
        query += ")";
        return query;
    }
    public static String criaTabelaPessoaFisica() {
        String query = "CREATE TABLE " + getTabelaPessoaFisica();
        query += " (";
        query += getID(getTabelaPessoaFisica()) + " " + TIPO_INTEIRO_PK + ", ";
        query += getNOME() + " " + TIPO_TEXTO + ", ";
        query += getAPELIDO() + " " + TIPO_TEXTO + ", ";
        query += getCPF() + " " + TIPO_TEXTO + ", ";
        query += getRG() + " " + TIPO_TEXTO + ", ";
        query += getID(getTabelaPessoa()) + " " + TIPO_INTEIRO + ", ";
        query += "FOREIGN KEY (" + getID(getTabelaPessoa()) +") REFERENCES " +getTabelaPessoa()+"("+getID(getTabelaPessoa())+")";
        query += ")";
        return query;
    }
    public static String criaTabelaPessoaJuridica() {
        String query = "CREATE TABLE " + getTabelaPessoaJuridica();
        query += " (";
        query += getID(getTabelaPessoaJuridica()) + " " + TIPO_INTEIRO_PK + ", ";
        query += getCNPJ() + " " + TIPO_TEXTO + ", ";
        query += getRazaoSocial() + " " + TIPO_TEXTO + ", ";
        query += getNomeFantasia() + " " + TIPO_TEXTO + ", ";
        query += getIE() + " " + TIPO_TEXTO + ", ";
        query += getID(getTabelaPessoa()) + " " + TIPO_INTEIRO + ", ";
        query += "FOREIGN KEY (" + getID(getTabelaPessoa()) +") REFERENCES " +getTabelaPessoa()+"("+getID(getTabelaPessoa())+")";
        query += ")";
        return query;
    }
    public static String criaTabelaTelefone() {
        String query = "CREATE TABLE " + getTabelaTelefone();
        query += " (";
        query += getID(getTabelaTelefone()) + " " + getTipoInteiroPk() + ", ";
        query += getID(getTabelaPessoa()) + " " + getTipoInteiro() + ", ";
        query += getNUMERO() + " " + TIPO_TEXTO + ", ";
        query += "FOREIGN KEY (" + getID(getTabelaPessoa()) +") REFERENCES " +getTabelaPessoa()+"("+getID(getTabelaPessoa())+")";
        query += ")";
        return query;
    }
   /* public static String criaTabelaEstado() {
        String query = "CREATE TABLE " + getTabelaEstado();
        query += " (";
        query += getID() + "_" +getTabelaEstado()+ " " + getTipoInteiroPk() + ", ";
        query += getNOME() + " " + TIPO_TEXTO + ", ";
        query += getUF() + " " + TIPO_TEXTO ;
        query += ")";
        return query;
    }*/

    public static String getDB_NAME() {
        return DB_NAME;
    }


    public static String getTABELA_CONTA() {
        return TABELA_CONTA;
    }


    public static String getID(String tabela) {
        return ID + SEPARADOR + tabela;
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

    public static String getTabelaConta() {
        return TABELA_CONTA;
    }

    public static String getTabelaMovimentacao() {
        return TABELA_MOVIMENTACAO;
    }

    public static String getTabelaCentrodecusto() {
        return TABELA_CENTRODECUSTO;
    }

    public static String getTabelaBairro() {
        return TABELA_BAIRRO;
    }

    public static String getTabelaCidade() {
        return TABELA_CIDADE;
    }

    public static String getTabelaEndereco() {
        return TABELA_ENDERECO;
    }

    public static String getTabelaLogradouro() {
        return TABELA_LOGRADOURO;
    }

    public static String getTabelaPais() {
        return TABELA_PAIS;
    }

    public static String getTabelaPessoa() {
        return TABELA_PESSOA;
    }

    public static String getTabelaPessoaFisica() {
        return TABELA_PESSOAFISICA;
    }

    public static String getTabelaPessoaJuridica() {
        return TABELA_PESSOAJURIDICA;
    }

    public static String getCOMPLEMENTO() {
        return COMPLEMENTO;
    }

    public static String getDataCadastro() {
        return DATA_CADASTRO;
    }

    public static String getAPELIDO() {
        return APELIDO;
    }

    public static String getNomeFantasia() {
        return NOME_FANTASIA;
    }

    public static String getCNPJ() {
        return CNPJ;
    }

    public static String getCPF() {
        return CPF;
    }

    public static String getRG() {
        return RG;
    }

    public static String getIE() {
        return IE;
    }

    public static String getRazaoSocial() {
        return RAZAO_SOCIAL;
    }
    public static String getTipoNumeric() {
        return TIPO_NUMERIC;
    }

    public static String getTipoTexto() {
        return TIPO_TEXTO;
    }

    public static String getTipoInteiro() {
        return TIPO_INTEIRO;
    }

    public static String getTipoInteiroPk() {
        return TIPO_INTEIRO_PK;
    }

    public static String getTipoReal() {
        return TIPO_REAL;
    }

    public static String getTipoBlob() {
        return TIPO_BLOB;
    }

    public static String getTabelaTelefone() {
        return TABELA_TELEFONE;
    }

    public static String getTabelaEstado() {
        return TABELA_ESTADO;
    }

    public static String getUF() {
        return UF;
    }

    public static String getSEPARADOR() {
        return SEPARADOR;
    }

    public static void setSEPARADOR(String SEPARADOR) {
        DataModel.SEPARADOR = SEPARADOR;
    }

    public static String getCodigoDoIbge() {
        return CODIGO_DO_IBGE;
    }

    public static void setCodigoDoIbge(String codigoDoIbge) {
        CODIGO_DO_IBGE = codigoDoIbge;
    }
}


