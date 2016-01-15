package Entities;

/**
 * Created by vivan on 17/10/2015.
 */
public class Movimentacao {
    private int id;
    private String descricao;
    private Conta conta;
    private CentroDeCusto centroDeCusto;
    private double valor;

    public Movimentacao(int id, String descricao, Conta conta, CentroDeCusto centroDeCusto, double valor) {
        this.id = id;
        this.descricao = descricao;
        this.conta = conta;
        this.centroDeCusto = centroDeCusto;
        this.valor = valor;
    }

    public Movimentacao() {

    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public CentroDeCusto getCentroDeCusto() {
        return centroDeCusto;
    }

    public void setCentroDeCusto(CentroDeCusto centroDeCusto) {
        this.centroDeCusto = centroDeCusto;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getDescricao() + " - " + getValor()+ " - "  + getConta().getNome();
    }
}
