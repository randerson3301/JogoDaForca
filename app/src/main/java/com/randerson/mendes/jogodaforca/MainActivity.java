package com.randerson.mendes.jogodaforca;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends Activity {

    Button btn[];
    TextView t, txtDica;
    LinearLayout layout;
    int contVitoria, tentativas, contGeral = 0;

    //ambas as variaveis serão utilizadas na tela de fim de jogo
     static int acertos, erros = 0;

     //vetor de caracteres
    char words[][] = {
            {'P', 'H', 'P'},
            {'V', 'I', 'D', 'A'},
            {'A', 'L', 'T', 'U', 'R', 'A'},
             {'J', 'U', 'P', 'I', 'T', 'E', 'R'},
             {'L', 'A', 'S', 'A', 'N', 'H', 'A'},
             {'V', 'E', 'L', 'O', 'C', 'I', 'R', 'A', 'P', 'T', 'O', 'R'},
             {'P', 'A', 'D', 'A', 'R', 'I', 'A'},
             {'E', 'S', 'O', 'T', 'E', 'R', 'I', 'C', 'O'},
             {'C', 'A', 'I', 'X', 'A'},
             {'E', 'L', 'I', 'P', 'S', 'E'},
             {'M', 'A', 'D', 'E', 'I', 'R', 'A'},
             {'A', 'R', 'M', 'A', 'D', 'U', 'R', 'A'},
             {'A', 'L', 'B', 'A', 'T', 'R', 'O', 'Z'},
             {'R', 'E', 'L', 'O', 'G', 'I', 'O'}
    };

    CharSequence dicas[] = {"Linguagem de Script para Web",
            "Se você é programador, nem sabe o que é", "Se você for baixo, não tem",
            "Maior Planeta do Sistema Solar", "Prato de origem italiana", "Dinossauro de baixa " +
            "estatura, mas veloz!", "Local bastante frequentado pela manhã", "Conhecimento para " +
            "poucos", "Guarda qualquer coisa", "Semelhante ao círculo", "Matéria-prima", "Te protege",
    "Ave de alta estatura", "Útil para todos!"};

    int indexWords = (int) Math.floor(Math.random() * words.length);
    //vetor para armazenar os textviews gerados dinamicamente.
    ArrayList<TextView> sequence;


    //método para mostrar cada dica por vez
    public void mostrarDica(int cont) {
           txtDica = new TextView(this);
           txtDica = findViewById(R.id.txtDica);

           txtDica.setText(dicas[cont]);

    }

    //Função que criará os textsViews automaticamente
    public void gerarTexto(int cont){

        layout = findViewById(R.id.MyLayout);

        sequence = new ArrayList<TextView>();

            for (int i = 0; i < words[cont].length; i++) {


                t = new TextView(this);

                //Retirando o layout do textView
                t.setText("_");
                t.setHeight(78);
                t.setWidth(40);
                t.setTextColor(getResources().getColor(R.color.white));
                t.setTextSize(22);

                //Adicionando os elementos t ao vetor
                sequence.add(t);

                layout.addView(t);


            }

    }

    //mudar palavra
    public void incrementar() {
        layout.removeAllViews(); //removendo todos os elementos do layout
        indexWords = (int) Math.floor(Math.random() * words.length);
        
        tentativas = 4;
        contVitoria = 0;
        gerarBtn();
        gerarTexto(indexWords);
        mostrarDica(indexWords);

        setResultado(contGeral);

    }

/*
    public void gerarAleatorioSemRepeticao(int index) {
       ArrayList<Integer> repetidos = new ArrayList<Integer>();

        repetidos.add(index);

        for (int i = 0; i <= repetidos.size(); i++) {
            if (index == repetidos.get(i)) {
                index = (int) Math.floor(Math.random() * words.length);
            }
        }


    }
*/

        View.OnClickListener clicou = (View view) -> {
        int tag = (int) view.getTag();

        int cont = 0;

        if(contGeral < words.length) {

            for (int i = 0; i < words[indexWords].length; i++) {

                if (btn[tag].getText().equals(String.valueOf(words[indexWords][i]))) {
                    cont++;
                    contVitoria++;
                    acertos++;
                    sequence.get(i).setText(btn[tag].getText());
                    view.setEnabled(false);

                } else {
                    view.setEnabled(false);

                }

            }

            if (cont != 0) {
                view.setBackgroundColor(getResources().getColor(R.color.green));

            } else {
                tentativas--;
                erros++;
                if (tentativas > 0) {
                    view.setBackgroundColor(getResources().getColor(R.color.red));

                    AlertDialog.Builder alert = new AlertDialog.Builder(this);
                    alert.setMessage("Você tem apenas: " + tentativas + " chances");
                    alert.setTitle("\uD83D\uDE31 Cuidado");

                    alert.create().show();
                } else if (tentativas == 0) {
                    alert("Infelizmente, você perdeu !", "Essa nãããoo");
                    contGeral++;
                }
            }

            if (contVitoria == words[indexWords].length) {
                alert("😍 Parabéns", "Você VENCEU!");
                contGeral++;

            }
        }
};



    //função para Alert
    private void alert(String msg, String titulo){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle(titulo);
        alert.setMessage(msg);

        alert.setNegativeButton("Finalizar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });


            alert.setPositiveButton("Próximo", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    incrementar();

                }
            });

            alert.setCancelable(false);

            alert.create().show();
    }

    public  void reiniciar() {
        tentativas = 4;
        contVitoria = 0;
       layout.removeAllViews();

        gerarTexto(contGeral);

        gerarBtn();


    }

    private void gerarBtn(){

            int i;

            for (i=0; i < btn.length; i++) {

                int id = getResources().getIdentifier("btn" + i, "id", getPackageName());
                btn[i] = findViewById(id);
                btn[i].setTag(i);
                btn[i].setEnabled(true);
                btn[i].setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                btn[i].setOnClickListener(clicou);
            }
    }

    private void setResultado(int cont) {
        if(cont == words.length / 2) {
            Intent intent = new Intent(this, FimActivity.class);
            startActivity(intent);
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Instanciando objetos
        sequence = new ArrayList<TextView>();
        layout = new LinearLayout(this);
        t = new TextView(this);
        btn = new Button[26];
        tentativas = 4;

        //usando métodos
        gerarBtn();
        gerarTexto(indexWords);
        mostrarDica(indexWords);
    }
}

