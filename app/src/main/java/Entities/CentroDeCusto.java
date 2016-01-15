package Entities;

/**
 * Created by vivan on 17/10/2015.
 */
public class CentroDeCusto {
    private int id;
    private String descricao;
    private String tipoTranzacao;
    private boolean ativo;

    public CentroDeCusto() {
    }

    public CentroDeCusto(String descricao, String tipoTranzacao, boolean ativo) {
        this.descricao = descricao;
        this.tipoTranzacao = tipoTranzacao;
        this.ativo = ativo;
    }

    public String getTipoTranzacao() {
        return tipoTranzacao;
    }

    public void setTipoTranzacao(String tipoTranzacao) {
        this.tipoTranzacao = tipoTranzacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        if (ativo == 1) {
            this.ativo = true;
        }else{
            this.ativo = false;
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString(){
        return getDescricao();
    }


    public void setAtivo(boolean checked) {
        this.ativo = checked;
    }
}
