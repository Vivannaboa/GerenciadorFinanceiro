package com.financa.vivan.gerenciadorfinanceiro;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import Dao.ContaDao;
import Dao.Localidade.BairroDao;
import Dao.Localidade.CidadeDao;
import Dao.Localidade.EnderecoDao;
import Dao.Localidade.LogradouroDao;
import Dao.Localidade.PaisDao;
import Entities.Conta;
import Entities.Localidade.Bairro;
import Entities.Localidade.Cidade;
import Entities.Localidade.Endereco;
import Entities.Localidade.Logradouro;
import Entities.Localidade.Pais;

public  class CadastroPessoaEnderco extends Fragment {
    ListView listViewConta;
    private static final String ARG_SECTION_NUMBER = "section_number";

    public CadastroPessoaEnderco() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static CadastroPessoaEnderco newInstance(int sectionNumber) {
        CadastroPessoaEnderco fragment = new CadastroPessoaEnderco();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cadastro_pessoa_endereco, container, false);
        listViewConta = (ListView) rootView.findViewById(R.id.listViewEndereco);
        ViewGroup footer = (ViewGroup) inflater.inflate(R.layout.footer, listViewConta,false);
        EnderecoDao enderecoDao = new EnderecoDao(getActivity());
        CidadeDao cidadeDao = new CidadeDao(getActivity());
        PaisDao paisDao = new PaisDao(getActivity());
        BairroDao bairroDao = new BairroDao(getActivity());
        LogradouroDao logradouroDao =new LogradouroDao(getActivity());
        List<Endereco> enderecos = enderecoDao.selectTodosOsEnderecos();
        for (int i = 0 ; i < enderecos.size(); i++){

            enderecoDao.deleteEndereco(i);
        }

        Pais pais = new Pais();
        pais.setNome("Brasil");
        paisDao.insertPais(pais);

        Cidade cidade = new Cidade();
        cidade.setUf("RS");
        cidade.setPais(pais);
        cidade.setCodigoDoIbge(0);
        cidade.setNome("Parobé");
        cidadeDao.insertCidade(cidade);

        Logradouro logradouro =new Logradouro();
        logradouro.setDescricao("Antonio dos Santos");
        logradouroDao.insertLogradouro(logradouro);

        Bairro bairro = new Bairro();
        bairro.setDescricao("Centro");
        bairroDao.insertBairro(bairro);

        Endereco endereco = new Endereco();
        endereco.setCidade(cidade);
        endereco.setLogradouro(logradouro);
        endereco.setBairro(bairro);
        endereco.setNumero("82");
        enderecoDao.insertEndereco(endereco);

        enderecos.toArray();

        ArrayAdapter<Endereco> adapter = new ArrayAdapter<Endereco>(this.getContext(), android.R.layout.simple_list_item_1, enderecos);
        listViewConta.setAdapter(adapter);
        listViewConta.addFooterView(footer, null, true);
        return rootView;
    }
}