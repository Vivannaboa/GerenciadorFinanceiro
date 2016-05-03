package Entities.Localidade;

/**
 * Created by vivan on 22/01/2016.
 */
public class Endereco {
    private int idEndereco;
    private int idPessoa;
    private String numero;
    private String complemento;
    private Bairro bairro;
    private Logradouro logradouro;
    private Cidade cidade;

    public Endereco() {
    }

    public Endereco(int idEndereco,
                    int idPessoa,
                    Bairro bairro,
                    Logradouro logradouro,
                    Cidade cidade,
                    String numero,
                    String complemento
    ) {

        this.idEndereco = idEndereco;
        this.idPessoa = idPessoa;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.cidade = cidade;
    }


    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }


    public Logradouro getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "idEndereco=" + idEndereco +
                ", idPessoa=" + idPessoa +
                ", numero='" + numero + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro=" + bairro +
                ", logradouro=" + logradouro +
                ", cidade=" + cidade +
                '}';
    }
}
