package Entities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Entities.Localidade.Endereco;

public class Pessoa {
    private int idPessoa;
    private Date dataCadastro;
    private boolean ativo;
    private String nomeRazaoSocial;
    private String apelidoNomeFantasia;
    private String cpfCnpj;
    private String rgIe;
    private String tipo;
    private ArrayList<Endereco> enderecos;

    public String getNomeRazaoSocial() {
        return nomeRazaoSocial;
    }

    public void setNomeRazaoSocial(String nomeRazaoSocial) {
        this.nomeRazaoSocial = nomeRazaoSocial;
    }

    public String getApelidoNomeFantasia() {
        return apelidoNomeFantasia;
    }

    public void setApelidoNomeFantasia(String apelidoNomeFantasia) {
        this.apelidoNomeFantasia = apelidoNomeFantasia;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getRgIe() {
        return rgIe;
    }

    public void setRgIe(String rgIe) {
        this.rgIe = rgIe;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(ArrayList<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public int isAtivo() {
        int retorno;
        if (ativo==true){
            retorno = 1;
        }else{
            retorno = 0;
        }
        return retorno;
    }

    public void setAtivo(int ativo) {
        if (ativo == 1) {
            this.ativo = true;
        } else {
            this.ativo = false;
        }

    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
        Date date = (Date) formatter.parse(dataCadastro);
        this.dataCadastro = date;
    }


    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

}
