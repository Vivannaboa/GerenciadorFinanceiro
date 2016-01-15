package com.financa.vivan.gerenciadorfinanceiro;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
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
import Dao.MovimentacaoDao;
import Entities.CentroDeCusto;
import Entities.Movimentacao;

public class ListMovimentacao extends Fragment implements AbsListView.OnItemClickListener {


    private OnFragmentInteractionListener mListener;
    private AbsListView mListView;
    private ListAdapter mAdapter;
    private final CadastroMovimentacao mCadastroMovimentacao = new CadastroMovimentacao();


    public ListMovimentacao() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        MovimentacaoDao movimentacaoDao = new MovimentacaoDao(getActivity());
        //Traz os dados do banco
        List<Movimentacao> listMovimentacao = movimentacaoDao.selectTodosOsMovimentacao();
        listMovimentacao.toArray();
        // TODO: Change Adapter to display your content
        mAdapter = new ArrayAdapter<Movimentacao>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, listMovimentacao);

        View view = inflater.inflate(R.layout.fragment_lancamento, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                             @Override
                                             public void onItemClick(
                                                     AdapterView<?> arg0, View arg1, int arg2, long id) {
                                                 int count1 = 0;
                                                 MovimentacaoDao movimentacaoDao = new MovimentacaoDao(getActivity());
                                                 Movimentacao movimentacao;
                                                 movimentacao = movimentacaoDao.getMovimentacao(arg2 + 1);
                                                 Fragment newFragment = new CadastroMovimentacao().newInstance(movimentacao, "Atualizar");
                                                 FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                                 transaction.replace(R.id.content, newFragment);
                                                 transaction.addToBackStack(null);
                                                 transaction.commit();
                                             }
                                         }
        );

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content,mCadastroMovimentacao)
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
        }
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
