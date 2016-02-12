package com.financa.vivan.gerenciadorfinanceiro;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public  class CadastroPessoaDados extends Fragment {
    private  EditText editTextNome;
    private  EditText editTextLogradouro ;
    private  EditText editTextCpf ;
    private  EditText editTextRg;
    private  EditText editTextCnpj;
    private  EditText editTextIe;
    private  TextView textViewTipo ;
    private  TextView textViewNome;
    private  TextView textViewLogradouro ;
    private  TextView textViewCpf ;
    private  TextView textViewRg;
    private  TextView textViewCnpj ;
    private  TextView textViewIe ;
    private  Spinner spinnerTipo;
    private  List<String> strtipos ;
    private  View rootView;
    private static final String ARG_SECTION_NUMBER = "section_number";

    public CadastroPessoaDados() {
    }

    public static CadastroPessoaDados newInstance(int sectionNumber) {
        CadastroPessoaDados fragment = new CadastroPessoaDados();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_cadastro_pessoa_dados, container, false);
        editTextNome = (EditText) rootView.findViewById(R.id.editTextNome);
        editTextCpf = (EditText) rootView.findViewById(R.id.editTextCpf);
        editTextRg = (EditText) rootView.findViewById(R.id.editTextRg);
        editTextCnpj = (EditText) rootView.findViewById(R.id.editTextCnpj);
        editTextIe = (EditText) rootView.findViewById(R.id.editTextIe);
        textViewTipo = (TextView) rootView.findViewById(R.id.textViewTipo);
        textViewNome = (TextView) rootView.findViewById(R.id.textViewNome);
        textViewCpf = (TextView) rootView.findViewById(R.id.textViewCpf);
        textViewRg = (TextView) rootView.findViewById(R.id.textViewRg);
        textViewCnpj = (TextView) rootView.findViewById(R.id.textViewCnpj);
        textViewIe = (TextView) rootView.findViewById(R.id.textViewIe);
        spinnerTipo = (Spinner)rootView.findViewById(R.id.spinnerTipo);
        strtipos = new ArrayList<String>();
        strtipos.add("Jurídica");
        strtipos.add("Física");


        ArrayAdapter adaptadorTipo = new ArrayAdapter(rootView.getContext(), android.R.layout.simple_spinner_item, strtipos);
        adaptadorTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo.setAdapter(adaptadorTipo);
        spinnerTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        textViewCpf.setVisibility(View.GONE);
                        editTextCpf.setVisibility(View.GONE);
                        textViewRg.setVisibility(View.GONE);
                        editTextRg.setVisibility(View.GONE);
                        textViewCnpj.setVisibility(View.VISIBLE);
                        editTextCnpj.setVisibility(View.VISIBLE);
                        textViewIe.setVisibility(View.VISIBLE);
                        editTextIe.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        textViewCpf.setVisibility(View.VISIBLE);
                        editTextCpf.setVisibility(View.VISIBLE);
                        textViewRg.setVisibility(View.VISIBLE);
                        editTextRg.setVisibility(View.VISIBLE);
                        textViewCnpj.setVisibility(View.GONE);
                        editTextCnpj.setVisibility(View.GONE);
                        textViewIe.setVisibility(View.GONE);
                        editTextIe.setVisibility(View.GONE);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        return rootView;
    }

    public EditText getEditTextNome() {
        return editTextNome;
    }

    public void setEditTextNome(EditText editTextNome) {
        this.editTextNome = editTextNome;
    }
    public EditText getEditTextLogradouro() {
        return editTextLogradouro;
    }

    public void setEditTextLogradouro(EditText editTextLogradouro) {
        this.editTextLogradouro = editTextLogradouro;
    }

    public EditText getEditTextCpf() {
        return editTextCpf;
    }

    public void setEditTextCpf(EditText editTextCpf) {
        this.editTextCpf = editTextCpf;
    }

    public EditText getEditTextRg() {
        return editTextRg;
    }

    public void setEditTextRg(EditText editTextRg) {
        this.editTextRg = editTextRg;
    }

    public EditText getEditTextCnpj() {
        return editTextCnpj;
    }

    public void setEditTextCnpj(EditText editTextCnpj) {
        this.editTextCnpj = editTextCnpj;
    }

    public EditText getEditTextIe() {
        return editTextIe;
    }

    public void setEditTextIe(EditText editTextIe) {
        this.editTextIe = editTextIe;
    }

    public Spinner getSpinnerTipo() {
        return spinnerTipo;
    }

    public void setSpinnerTipo(Spinner spinnerTipo) {
        this.spinnerTipo = spinnerTipo;
    }

}