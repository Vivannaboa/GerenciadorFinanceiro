package Entities.Localidade;

/**
 * Created by vivan on 23/01/2016.
 */
public class Cidade {
    private int idCidade;
    private Pais pais;
    private String nome;
    private int codigoDoIbge;
    private String uf;

    public int getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(int idCidade) {
        this.idCidade = idCidade;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public int getCodigoDoIbge() {
        return codigoDoIbge;
    }

    public void setCodigoDoIbge(int codigoDoIbge) {
        this.codigoDoIbge = codigoDoIbge;
    }

    @Override
    public String toString() {
        return "Cidade{" +
                "idCidade=" + idCidade +
                ", pais=" + pais +
                ", nome='" + nome + '\'' +
                ", codigoDoIbge=" + codigoDoIbge +
                ", uf='" + uf + '\'' +
                '}';
    }
}
