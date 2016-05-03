package com.financa.vivan.gerenciadorfinanceiro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Dao.Localidade.BairroDao;
import Dao.Localidade.CidadeDao;
import Dao.Localidade.EnderecoDao;
import Dao.Localidade.LogradouroDao;
import Dao.Localidade.PaisDao;
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
        EnderecoDao enderecoDao =  EnderecoDao.getInstance(getActivity());
        CidadeDao cidadeDao =  CidadeDao.getInstance(getActivity());
        PaisDao paisDao =  PaisDao.getInstance(getActivity());
        BairroDao bairroDao =  BairroDao.getInstance(getActivity());
        LogradouroDao logradouroDao = LogradouroDao.getInstance(getActivity());
//        List<Endereco> enderecos = enderecoDao.selectTodosOsEnderecos();
//        for (int i = 0 ; i < enderecos.size(); i++){
//
//            enderecoDao.deleteEndereco(i);
//        }

        Pais pais = new Pais();
        pais.setNome("Brasil");
        // pais.setIdPais(paisDao.getInsertPais(pais));

        Cidade cidade = new Cidade();
        cidade.setUf("RS");
        cidade.setPais(pais);
        cidade.setCodigoDoIbge("0");
        cidade.setNome("Igrejinha");
        //cidade.setIdCidade(cidadeDao.getInsertCidade(cidade));

        Logradouro logradouro =new Logradouro();
        logradouro.setDescricao("Blablabla");
        //logradouro.setIdLogradouro(logradouroDao.getInsertLogradouro(logradouro));

        Bairro bairro = new Bairro();
        bairro.setDescricao("Zona Norte");
        //bairro.setIdBairro(bairroDao.getInsertBairro(bairro));

        Endereco endereco = new Endereco();
        endereco.setCidade(cidade);
        endereco.setLogradouro(logradouro);
        endereco.setBairro(bairro);
        endereco.setNumero("82");
        //enderecoDao.insertEndereco(endereco);

        //enderecos.toArray().toString();
        // ArrayAdapter<Endereco> adapter = new ArrayAdapter<Endereco>(this.getContext(), R.layout.list_view_01_row, enderecos);
        //listViewConta.setAdapter(adapter);
        //listViewConta.addFooterView(footer, null, true);
        // setupList();
        return rootView;
    }
//    private void setupList() {
//        listViewConta.setAdapter(createAdapter());
//        listViewConta.setOnItemClickListener(new ListItemClickListener());
//    }

    //   private CardsAdapter createAdapter() {
    // EnderecoDao enderecoDao = new EnderecoDao(getActivity());
    // List<Endereco> enderecos = enderecoDao.selectTodosOsEnderecos();
//        ArrayList<String> items = new ArrayList<String>();
//        for (int i = 0; i < enderecos.size(); i++) {
//            items.add(i,"\n" + "Logradouro: " + enderecos.get(i).getLogradouro().getDescricao() + "\n" +
//                               "Número: " + enderecos.get(i).getNumero() +"\n" +
//                               "Bairro: " + enderecos.get(i).getBairro().getDescricao() +"\n"
//
//
//            );

    //  }

    // return new CardsAdapter(getActivity(), items, new ListItemButtonClickListener());
    // }

//    private final class ListItemButtonClickListener implements View.OnClickListener {
//        @Override
//        public void onClick(View v) {
//            for (int i = listViewConta.getFirstVisiblePosition(); i <= listViewConta.getLastVisiblePosition(); i++) {
//                if (v == listViewConta.getChildAt(i - listViewConta.getFirstVisiblePosition()).findViewById(R.id.list_item_card_button_1)) {
//                    // PERFORM AN ACTION WITH THE ITEM AT POSITION i
//                    Toast.makeText(getActivity(), "Clicked on Left Action Button of List Item " + i, Toast.LENGTH_SHORT).show();
//                } else if (v == listViewConta.getChildAt(i - listViewConta.getFirstVisiblePosition()).findViewById(R.id.list_item_card_button_2)) {
//                    // PERFORM ANOTHER ACTION WITH THE ITEM AT POSITION i
//                    Toast.makeText(getActivity(), "Clicked on Right Action Button of List Item " + i, Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    }
//
//    private final class ListItemClickListener implements AdapterView.OnItemClickListener {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            Toast.makeText(getActivity(), "Clicked on List Item " + position, Toast.LENGTH_SHORT).show();
//        }
//    }
}