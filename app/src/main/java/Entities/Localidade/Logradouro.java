package Entities.Localidade;

/**
 * Created by vivan on 23/01/2016.
 */
public class Logradouro {
    private int idLogradouro;
    private String descricao;

    public Logradouro(int idLogradouro,String descricao) {
        this.descricao = descricao;
        this.idLogradouro = idLogradouro;
    }

    public Logradouro() {
    }


    public int getIdLogradouro() {
        return idLogradouro;
    }

    public void setIdLogradouro(int idLogradouro) {
        this.idLogradouro = idLogradouro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Logradouro{" +
                "idLogradouro=" + idLogradouro +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
