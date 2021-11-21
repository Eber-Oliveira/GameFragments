package com.eber.gamefragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CadastrarFragments#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CadastrarFragments extends Fragment {

    private EditText mEditTextPergunta, mEditTextResposta;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CadastrarFragments() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CadastrarFragments.
     */
    // TODO: Rename and change types and number of parameters
    public static CadastrarFragments newInstance(String param1, String param2) {
        CadastrarFragments fragment = new CadastrarFragments();
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
        return inflater.inflate(R.layout.fragment_cadastrar_fragments, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        //Adicionando um Listener para o botão Jogar
        Button mButtonJogar = getActivity().findViewById(R.id.btnJogar);
        mButtonJogar.setOnClickListener(mJogarListener);
        //Adicionando um Listener para o botão Inseir
        Button mButtomInserir = getActivity().findViewById(R.id.btnInseir);
        mButtomInserir.setOnClickListener(mInserirListener);
        //Atribuindo e vinculando objetos Campo código

        mEditTextPergunta = getActivity().findViewById(R.id.edTxtPergunta);
        mEditTextResposta = getActivity().findViewById(R.id.edTxtResposta);
    }

    //Criando o objeto e Evento Listener do botão Jogar
    private View.OnClickListener mJogarListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new JogarFragment())
                    .commitNow();
        }
    };
    //Criando o objeto e Evento Listener do botão Inserir
    private View.OnClickListener mInserirListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Recuperar os valores digitados pelo usuario
            String pergunta = mEditTextPergunta.getText().toString();
            String resposta = mEditTextResposta.getText().toString();

            //Verificar se os dados estão vazios
            if ((!pergunta.isEmpty())&&(!resposta.isEmpty())){
                //Criar um objeto do tipo questoes com os valores digitados pelo usuario
                Questoes qt = new Questoes(pergunta, resposta);

                 // Atraves da classe DAO insere a questao no Banco de Dados
                BancoDeDados.getBancoDeDados(getActivity()).getDAO().inserirQuestao(qt);

                //Limpar os Campos para inserir uma nova questão
                mEditTextPergunta.setText("");
                mEditTextResposta.setText("");

                //Exibe mensagem para o usuario
                Toast.makeText(getActivity(), "Inserido com sucesso!", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getActivity(), "Por favor preencha todos dados!", Toast.LENGTH_LONG).show();
            }
        }
    };
}
