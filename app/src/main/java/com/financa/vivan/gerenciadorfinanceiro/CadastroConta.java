package com.financa.vivan.gerenciadorfinanceiro;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import Dao.ContaDao;
import Dao.DbHelper;
import Entities.Conta;
import Util.Validador;


public class CadastroConta extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static EditText edtNome;
    private static EditText edtNumero;
    private static Button btnCadastrar;
    private static Button btnCancelar;
    private static CadastroConta fragment;
    private View view;
    private static Conta CONTAATUALIZAR;
    private String mParam2="";
    private OnFragmentInteractionListener mListener;

       // para Atualizar um cadastro utilizei uma nova instancia
    public static CadastroConta newInstance(Conta conta, String param2) {
        fragment = new CadastroConta();
        Bundle args = new Bundle();
        //recuperando o objeto conta
        CONTAATUALIZAR = conta;
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CadastroConta() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam2 = getArguments().getString(ARG_PARAM2);

        }

    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        //criando os componentes
        view =inflater.inflate(R.layout.fragment_cadastro_conta, container, false);
        edtNome = (EditText)view.findViewById(R.id.edtNome);
        edtNumero=(EditText)view.findViewById(R.id.edtNumero);
        btnCadastrar=(Button)view.findViewById(R.id.btnCadastrar);
        btnCancelar=(Button)view.findViewById(R.id.btnCancelar);
        //verifica se esta atualizando
        if (mParam2.equals("Atualizar")) {
            edtNome.setText(CONTAATUALIZAR.getNome().toString());
            edtNumero.setText(CONTAATUALIZAR.getNumero().toString());
            btnCadastrar.setText("Atualizar");
        }
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtNome.getText().clear();
                edtNumero.getText().clear();
                getFragmentManager().popBackStack();
            }});
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mParam2.equals("Atualizar")){
                    if (Validador.validateNotNull(edtNome, "O nome deve ser informado!") && Validador.validateNotNull(edtNumero, "O numero deve ser preenchido!")) {
                        try {
                            DbHelper dbHelper = new DbHelper(getActivity());
                            Conta conta = new Conta();
                            conta.setNome(edtNome.getText().toString());
                            conta.setNumero(edtNumero.getText().toString());
                            ContaDao contaDao = new ContaDao(getActivity());
                            contaDao.updateConta(conta,CONTAATUALIZAR.getId());
                            edtNome.getText().clear();
                            edtNumero.getText().clear();
                            getFragmentManager().popBackStack();
                        }catch (Exception e){
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                            builder1.setMessage(e.getMessage().toString());
                            builder1.show();
                        }
                    }
                }else {
                    if (Validador.validateNotNull(edtNome, "O nome deve ser informado!") && Validador.validateNotNull(edtNumero, "O numero deve ser preenchido!")) {
                        try {
                            Conta conta = new Conta();
                            conta.setNome(edtNome.getText().toString());
                            conta.setNumero(edtNumero.getText().toString());
                            ContaDao dao= new ContaDao(getActivity());
                            dao.insertConta(conta);
                            edtNome.getText().clear();
                            edtNumero.getText().clear();
                            getFragmentManager().popBackStack();
                        }catch (Exception e){
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


    public void onBackPressed() {
        edtNome.getText().clear();
        edtNumero.getText().clear();
        getFragmentManager().popBackStack();
       }


}
