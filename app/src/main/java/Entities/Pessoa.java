package Entities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Entities.Localidade.Endereco;

/**
 * Created by vivan on 22/01/2016.
 */
public  class Pessoa {
    private int idPessoa;
    private Date dataCadastro;
    private boolean ativo;

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

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
        Date date = (Date)formatter.parse(dataCadastro);
        this.dataCadastro =date ;
    }


    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

}
