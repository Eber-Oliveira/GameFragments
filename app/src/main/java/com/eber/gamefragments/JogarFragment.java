package com.eber.gamefragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JogarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JogarFragment extends Fragment {

    private List<Questoes> mListQuestoes; //Objeto responsavel por armazenar a Lista de Questoes
    private TextView mTxtViewPergunta, mTextViewResposta;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public JogarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JogarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JogarFragment newInstance(String param1, String param2) {
        JogarFragment fragment = new JogarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jogar, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstaceState){
        super.onActivityCreated(savedInstaceState);
        //Adicionar o Listener para o Batão Jogar
        Button mButtonCadastrar = getActivity().findViewById(R.id.btnCadastrar);
        mButtonCadastrar.setOnClickListener(mCadastrarListener);

        //Adicionar o Listener para o Batão PularPergunta
        Button mButtonPularPergunta = getActivity().findViewById(R.id.btnPular);
        mButtonPularPergunta.setOnClickListener(mPularPerguntaListener);

        //Adicionar o Listener para o Batão ExibirResposta
        Button mButtonExibirResposta = getActivity().findViewById(R.id.btnExibirResposta);
        mButtonExibirResposta.setOnClickListener(mExibirRespostaListener);

        //Recuperar os objetos de Visualização da Resposta
        //Recuperar as perguntas Cadastradas
        //Criando o elemento da Lista de Questões
        mListQuestoes = BancoDeDados.getBancoDeDados(getActivity()).getDAO().pesquisarTodasQuestoes();
        mTxtViewPergunta = getActivity().findViewById(R.id.txtViewUpdatePergunta);
        mTextViewResposta = getActivity().findViewById(R.id.txtViewUpdateResposta);


    }
    private View.OnClickListener mCadastrarListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Inicia o Cadastro Fragment
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new CadastrarFragments())
                    .commitNow();
        }
    };
    private View.OnClickListener mPularPerguntaListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            proximnaquestao();
        }
    };

    private View.OnClickListener mExibirRespostaListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mTextViewResposta.setVisibility(View.VISIBLE);
        }
    };

    private void proximnaquestao() {
        //Verificando se a Lista não está vazia
        if(!mListQuestoes.isEmpty()){
            //Recuperar o total das questoes cadastradas
            int totalQuestoes = mListQuestoes.size();

            //Criar um numero aleatorio dentre o toal de questões cadastradas
            int indexAleatorio = new Random().nextInt(totalQuestoes);

            //Recupera uma questão aleatoria
            Questoes qt = mListQuestoes.get(indexAleatorio);

            //Exibe os dados de pergunta e resposta para o usuario
            mTxtViewPergunta.setText(qt.getPergunta());
            mTextViewResposta.setText(qt.getResposta());

            //Mantendo a resposta oculta
            mTextViewResposta.setVisibility(View.GONE);

        }
    }
}