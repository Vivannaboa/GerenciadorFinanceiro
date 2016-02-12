package Entities.Localidade;

/**
 * Created by vivan on 23/01/2016.
 */
public class Pais {
    private int idPais;
    private String nome;

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    @Override
    public String toString() {
        return "Pais{" +
                "idPais=" + idPais +
                ", nome='" + nome + '\'' +
                '}';
    }
}
