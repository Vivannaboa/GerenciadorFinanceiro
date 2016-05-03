/*
 * Copyright 2015 Steven Mulder
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.financa.vivan.gerenciadorfinanceiro;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import Dao.ContaDao;
import Entities.Conta;

public class ListConta extends Fragment {

    private View view;
    private final CadastroConta mCadastroConta = new CadastroConta();
    ListView listViewConta;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        //Conexão com o banco
        ContaDao contaDao =  ContaDao.getInstance(getActivity().getBaseContext());
        //Traz os dados do banco
        List<Conta> contas = contaDao.selectTodasAsContas();
        //contaDao.fecharConexao();
        contas.toArray().toString();

        view = inflater.inflate(R.layout.fragment_conta, container, false);

        listViewConta = (ListView) view.findViewById(R.id.listViewConta);

        listViewConta.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                 @Override
                                                 public void onItemClick(
                                                         AdapterView<?> arg0, View arg1, int arg2, long id) {
                                                     int count1 = 0;
                                                     Conta conta = new Conta();

                                                     ContaDao contaDao =  ContaDao.getInstance(getActivity());
                                                     conta= contaDao.getConta(arg2+1);
                                                     //contaDao.fecharConexao();

                                                     Fragment newFragment = new CadastroConta().newInstance(conta,"Atualizar");
                                                     FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                                     transaction.replace(R.id.content, newFragment);
                                                     transaction.addToBackStack(null);
                                                     transaction.commit();
                                                 }
                                             }
        );


        ArrayAdapter<Conta> adapter = new ArrayAdapter<Conta>(getActivity(), android.R.layout.simple_list_item_1, contas);
        listViewConta.setAdapter(adapter);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                 Intent it = new Intent(getActivity(), CadastroPessoaActivity.class);
//                startActivity(it);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, mCadastroConta)
                        .addToBackStack(null)
                        .commit();

            }
        });
        return view;

    }


}
