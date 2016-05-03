package com.financa.vivan.gerenciadorfinanceiro;

import android.annotation.TargetApi;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
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
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        CentoDeCustoDao centoDeCustoDao =  CentoDeCustoDao.getInstance(getActivity());
        //Traz os dados do banco
        List<CentroDeCusto> listCentoDeCustoDao = centoDeCustoDao.selectTodasAsCentroDeCustos();
        listCentoDeCustoDao.toArray();
        mAdapter = new ArrayAdapter<CentroDeCusto>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, listCentoDeCustoDao);

        view = inflater.inflate(R.layout.fragment_centrocusto_list, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                             @Override
                                             public void onItemClick(
                                                     AdapterView<?> arg0, View arg1, int arg2, long id) {
                                                 int count1 = 0;
                                                 CentoDeCustoDao centoDeCustoDao =  CentoDeCustoDao.getInstance(getActivity());
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
        FloatingActionButton fab = (android.support.design.widget.FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(), SecondActivity.class);
                startActivity(it);
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
