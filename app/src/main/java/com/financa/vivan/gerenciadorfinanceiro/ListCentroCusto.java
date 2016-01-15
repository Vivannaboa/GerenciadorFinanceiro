package com.financa.vivan.gerenciadorfinanceiro;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.financa.vivan.gerenciadorfinanceiro.dummy.DummyContent;

import java.util.List;

import Dao.CentoDeCustoDao;
import Dao.ContaDao;
import Entities.CentroDeCusto;
import Entities.Conta;


public class ListCentroCusto extends Fragment {


    private final CadastroCentroCusto mCadastroCentroCusto = new CadastroCentroCusto();


    private OnFragmentInteractionListener mListener;


    private AbsListView mListView;
    private ListAdapter mAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        CentoDeCustoDao centoDeCustoDao = new CentoDeCustoDao(getActivity());
        //Traz os dados do banco
        List<CentroDeCusto> listCentoDeCustoDao = centoDeCustoDao.selectTodasAsCentroDeCustos();
        listCentoDeCustoDao.toArray();
        // TODO: Change Adapter to display your content
        mAdapter = new ArrayAdapter<CentroDeCusto>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, listCentoDeCustoDao);

        View view = inflater.inflate(R.layout.fragment_centrocusto, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                             @Override
                                             public void onItemClick(
                                                     AdapterView<?> arg0, View arg1, int arg2, long id) {
                                                 int count1 = 0;
                                                 CentoDeCustoDao centoDeCustoDao = new CentoDeCustoDao(getActivity());
                                                 CentroDeCusto centoDeCusto;
                                                 centoDeCusto = centoDeCustoDao.getCentroDeCusto(arg2 + 1);
                                                 Fragment newFragment = new CadastroCentroCusto().newInstance(centoDeCusto, "Atualizar");
                                                 FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                                 transaction.replace(R.id.content, newFragment);
                                                 transaction.addToBackStack(null);
                                                 transaction.commit();
                                             }
                                         }
        );


        // Set OnItemClickListener so we can be notified on item clicks

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, mCadastroCentroCusto)
                        .addToBackStack(null)
                        .commit();


            }
        });


        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }

}
