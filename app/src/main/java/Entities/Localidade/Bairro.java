package Entities.Localidade;

/**
 * Created by vivan on 23/01/2016.
 */
public class Bairro {
    private int idBairro;
    private String descricao;

    public Bairro(int idBairro, String descricao) {
        this.idBairro = idBairro;
        this.descricao = descricao;
    }

    public Bairro() {
    }

    public int getIdBairro() {
        return idBairro;
    }

    public void setIdBairro(int idBairro) {
        this.idBairro = idBairro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Bairro{" +
                "idBairro=" + idBairro +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
