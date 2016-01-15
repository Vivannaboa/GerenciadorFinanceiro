package com.financa.vivan.gerenciadorfinanceiro;

import android.app.Activity;
import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import Dao.CentoDeCustoDao;
import Dao.ContaDao;
import Dao.DbHelper;
import Dao.MovimentacaoDao;
import Entities.CentroDeCusto;
import Entities.Conta;
import Entities.Movimentacao;
import Util.Validador;


public class CadastroMovimentacao extends Fragment {
    private static final String ARG_PARAM2 = "param2";


    private String mParam2 = "";
    private View view;
    private EditText edtValor;
    private EditText edtDescricao;
    private Spinner spinnerCentroCusto;
    private Spinner spinnerConta;
    private Button btnCadastrar;
    private Button btnCancelar;
    private List<String> strtipos = new ArrayList<String>();
    private Movimentacao MOVIMENTACAO_ATUALIZAR;
    //para ficar melhor de capturar os obj selecionados no spinner
    private Conta conta;
    private CentroDeCusto centroDeCusto;


    private OnFragmentInteractionListener mListener;

    // TODO: Rename and change types and number of parameters
    public static CadastroMovimentacao newInstance(Movimentacao movimentacao, String param2) {
        CadastroMovimentacao fragment = new CadastroMovimentacao();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        fragment.MOVIMENTACAO_ATUALIZAR = movimentacao;
        return fragment;
    }

    public CadastroMovimentacao() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //recuperando objetos
        view = inflater.inflate(R.layout.fragment_cadastrto_movimentacao, container, false);
        edtValor =(EditText)view.findViewById(R.id.edtValor);
        edtDescricao=(EditText)view.findViewById(R.id.edtDescricao);
        spinnerCentroCusto=(Spinner)view.findViewById(R.id.spinnerCentroCusto);
        spinnerConta =(Spinner)view.findViewById(R.id.spinnerConta);
        btnCadastrar=(Button)view.findViewById(R.id.btnCadastrar);
        btnCancelar=(Button)view.findViewById(R.id.btnCancelar);



        //conectando ao banco
        final CentoDeCustoDao centoDeCustoDao = new CentoDeCustoDao(getActivity());
        ContaDao contaDao =new ContaDao(getActivity());


        //carregando spinner Centro de custo
        ArrayAdapter adaptadorCentroCusto = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,centoDeCustoDao.selectTodasAsCentroDeCustos() );
        adaptadorCentroCusto.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCentroCusto.setAdapter(adaptadorCentroCusto);
        spinnerCentroCusto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView parent, View arg1, int arg2, long arg3) {
                centroDeCusto = (CentroDeCusto) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView arg0) {
            }
        });

        //Carregando Spinner Conta
        ArrayAdapter adaptadorConta = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,contaDao.selectTodasAsContas());
        adaptadorConta.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerConta.setAdapter(adaptadorConta);
        //recuperar o obj selecionado no spinner
        spinnerConta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView parent, View arg1, int arg2, long arg3) {
                conta =(Conta)parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView arg0) {
            }
        });
        if (mParam2.equals("Atualizar")) {
            edtDescricao.setText(MOVIMENTACAO_ATUALIZAR.getDescricao().toString());
            edtValor.setText(String.valueOf(MOVIMENTACAO_ATUALIZAR.getValor()));
            btnCadastrar.setText("Atualizar");
        }
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtDescricao.getText().clear();
                edtValor.getText().clear();
                spinnerConta.setAdapter(null);
                spinnerCentroCusto.setAdapter(null);
                getFragmentManager().popBackStack();
            }
        });
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mParam2.equals("Atualizar")) {
                    if (Validador.validateNotNull(edtDescricao, "A descrição deve ser informada!") && Validador.validateNotNull(edtValor, "O valor deve ser preenchido!")) {
                        try {
                            ContaDao contaDao = new ContaDao(getActivity());
                            Movimentacao movimentacao = new Movimentacao();
                            movimentacao.setValor(Double.parseDouble(edtValor.getText().toString()));
                            movimentacao.setDescricao(edtDescricao.getText().toString());
                            movimentacao.setCentroDeCusto(centroDeCusto);
                            movimentacao.setConta(conta);
                            MovimentacaoDao movimentacaoDao = new MovimentacaoDao(getActivity());
                            movimentacaoDao.updateMovimentacao(movimentacao, MOVIMENTACAO_ATUALIZAR.getId());
                            if (centroDeCusto.getTipoTranzacao().equals("Saida")){
                                //contaDao.updateConta();
                                conta.sacar(Double.parseDouble(edtValor.getText().toString()));
                                contaDao.updateConta(conta,conta.getId());
                            }else{
                                conta.depositar(Double.parseDouble(edtValor.getText().toString()));
                                contaDao.updateConta(conta,conta.getId());
                            }
                            edtDescricao.getText().clear();
                            edtValor.getText().clear();
                            spinnerConta.setAdapter(null);
                            spinnerCentroCusto.setAdapter(null);
                            getFragmentManager().popBackStack();
                        } catch (Exception e) {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                            builder1.setMessage(e.getMessage().toString());
                            builder1.show();
                        }
                    }
                } else {
                    if (Validador.validateNotNull(edtValor, "O valor deve ser preenchido!")) {
                        try {
                            ContaDao contaDao = new ContaDao(getActivity());
                            Movimentacao movimentacao = new Movimentacao();
                            movimentacao.setValor(Double.parseDouble(edtValor.getText().toString()));
                            movimentacao.setDescricao(edtDescricao.getText().toString());
                            movimentacao.setCentroDeCusto(centroDeCusto);
                            movimentacao.setConta(conta);
                            MovimentacaoDao movimentacaoDao = new MovimentacaoDao(getActivity());
                            movimentacaoDao.insertMovimentacao(movimentacao);
                            if (centroDeCusto.getTipoTranzacao().equals("Saida")){
                                conta.sacar(Double.parseDouble(edtValor.getText().toString()));
                                contaDao.updateConta(conta,conta.getId());
                            }else{
                                conta.depositar(Double.parseDouble(edtValor.getText().toString()));
                                contaDao.updateConta(conta,conta.getId());
                            }
                            edtDescricao.getText().clear();
                            edtValor.getText().clear();
                            spinnerConta.setAdapter(null);
                            spinnerCentroCusto.setAdapter(null);
                            getFragmentManager().popBackStack();
                        } catch (Exception e) {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                            builder1.setMessage(e.getMessage().toString());
                            builder1.show();
                        }
                    }
                }
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
