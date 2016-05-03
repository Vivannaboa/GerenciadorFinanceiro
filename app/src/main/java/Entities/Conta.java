package Entities;

/**
 * Created by vivan on 16/10/2015.
 */
public class Conta {
    private int id;
    private String nome;
    private String numero;
    private double saldo;

    public Conta(){}


    public Conta(int id, String nome, String numero) {
        this.id = id;
        this.nome = nome;
        this.numero = numero;
        this.saldo = 0.0;
    }
    public Conta(int id, String nome, String numero,double saldo) {
        this.id = id;
        this.nome = nome;
        this.numero = numero;
        this.saldo = saldo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void sacar(double valor) {
        this.saldo -= valor;
    }
    public void depositar(double valor){
        this.saldo += valor;
    }

    public String toString() {
        return this.getNome() +" - " + getSaldo();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
