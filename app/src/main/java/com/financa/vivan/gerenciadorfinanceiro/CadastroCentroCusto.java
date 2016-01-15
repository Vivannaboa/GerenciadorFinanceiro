package com.financa.vivan.gerenciadorfinanceiro;

import android.app.Activity;
import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import Dao.CentoDeCustoDao;
import Dao.ContaDao;
import Entities.CentroDeCusto;
import Entities.Conta;
import Util.Validador;


public class CadastroCentroCusto extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam2 = "";
    private OnFragmentInteractionListener mListener;

    private EditText edtDescricao;
    private Spinner spinnerTipo;
    private CheckBox checkBoxAtivo;
    private Button btnCadastrar;
    private Button btnCancelar;
    private  View view;
    private CentroDeCusto CENTRODECUSTO_ATUALIZAR;

    private List<String> strtipos = new ArrayList<String>();

    // TODO: Rename and change types and number of parameters
    public static CadastroCentroCusto newInstance(CentroDeCusto centroDeCusto, String param2) {
        CadastroCentroCusto fragment = new CadastroCentroCusto();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        fragment.CENTRODECUSTO_ATUALIZAR =   centroDeCusto;

        return fragment;
    }

    public CadastroCentroCusto() {
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
        view = inflater.inflate(R.layout.fragment_cadastro_centro_custo, container, false);
        edtDescricao =(EditText)view.findViewById(R.id.edtDescricao);
        spinnerTipo = (Spinner)view.findViewById(R.id.spinnerTipo);
        checkBoxAtivo =(CheckBox)view.findViewById(R.id.checkBoxAtivo);
        btnCadastrar = (Button)view.findViewById(R.id.btnCadastrar);
        btnCancelar = (Button)view.findViewById(R.id.btnCancelar);

        //carregando dados spinner tipo
        strtipos.add("Saida");
        strtipos.add("Entrada");
        ArrayAdapter adaptadorTipo = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, strtipos);
        adaptadorTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo.setAdapter(adaptadorTipo);
        if (mParam2.equals("Atualizar")) {
            edtDescricao.setText(CENTRODECUSTO_ATUALIZAR.getDescricao());
            int selectionPosition= adaptadorTipo.getPosition(CENTRODECUSTO_ATUALIZAR.getTipoTranzacao().toString());
            spinnerTipo.setSelection(selectionPosition);
            checkBoxAtivo.setChecked(CENTRODECUSTO_ATUALIZAR.isAtivo());
            btnCadastrar.setText("Atualizar");
        }


        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtDescricao.getText().clear();
                spinnerTipo.setAdapter(null);
                getFragmentManager().popBackStack();
            }});

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mParam2.equals("Atualizar")){
                    try {
                        CentroDeCusto centroDeCusto = new CentroDeCusto();
                        centroDeCusto.setDescricao(edtDescricao.getText().toString());
                        centroDeCusto.setTipoTranzacao(spinnerTipo.getSelectedItem().toString());
                        centroDeCusto.setAtivo(checkBoxAtivo.isChecked());
                        CentoDeCustoDao dao = new CentoDeCustoDao(getActivity());
                        dao.updateCentroDeCusto(centroDeCusto, CENTRODECUSTO_ATUALIZAR.getId());
                        edtDescricao.getText().clear();
                        spinnerTipo.setAdapter(null);
                        getFragmentManager().popBackStack();

                    } catch (Exception e) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                        builder1.setMessage(e.getMessage().toString());
                        builder1.show();
                    }
                }else {
                    if (Validador.validateNotNull(edtDescricao, "A descrição não pode ser vazia!")) {
                        try {
                            CentroDeCusto centroDeCusto = new CentroDeCusto();
                            centroDeCusto.setDescricao(edtDescricao.getText().toString());
                            centroDeCusto.setTipoTranzacao(spinnerTipo.getSelectedItem().toString());
                            centroDeCusto.setAtivo(checkBoxAtivo.isChecked());
                            CentoDeCustoDao dao = new CentoDeCustoDao(getActivity());
                            dao.insertCentroDeCusto(centroDeCusto);
                            edtDescricao.getText().clear();
                            spinnerTipo.setAdapter(null);
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
