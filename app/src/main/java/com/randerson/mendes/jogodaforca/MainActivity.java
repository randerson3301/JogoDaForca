package com.randerson.mendes.jogodaforca;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewStructure;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    Button btn[];
    TextView t;
    LinearLayout layout;
    int indexWords = 0;
    int gabarito[] = {2, 0, 18, 0};

    //vetor de caracteres
    char words[][] = {
            {'A', 'L', 'T', 'U', 'R', 'A'},
            {'C', 'A', 'S', 'A'},
            {'P', 'H', 'P'}
    };

    //vetor para armazenar os textviews gerados dinamicamente.
    ArrayList<TextView> sequence;

    int contVitoria = 0;

    int tentativas = 4;


    //Função que criará os textsViews automaticamente
    public void gerarTexto(int cont){

        layout = findViewById(R.id.MyLayout);

        for (int i = 0; i <= words[cont].length - 1; i++) {
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
        indexWords++;
        gerarTexto(indexWords);
    }


    View.OnClickListener clicou = (View view) -> {
        int tag = (int) view.getTag();

        int cont = 0;

        for (int i = 0; i < words[indexWords].length; i++) {

            if(btn[tag].getText().equals(String.valueOf(words[indexWords][i]))) {
                cont++;
                contVitoria++;
                sequence.get(i).setText(btn[tag].getText());
                view.setEnabled(false);

            } else {
                view.setEnabled(false);

            }

        }

        if(cont != 0) {
            view.setBackgroundColor(getResources().getColor(R.color.green));

        } else {
            tentativas--;

            if (tentativas > 0) {
                view.setBackgroundColor(getResources().getColor(R.color.red));
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setMessage("Você tem apenas: " + tentativas + " chances");
                alert.setTitle("\uD83D\uDE31 Cuidado");

                alert.create().show();
            }

            else if(tentativas == 0) {
                alert("Infelizmente, você perdeu !", "Essa nãããoo");
                reiniciar();

            }
        }

        if(contVitoria == words[indexWords].length) {
            alert("😍 Parabéns", "Você VENCEU!");
            reiniciar();

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

        if(tentativas != 0) {
            alert.setPositiveButton("Próximo", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    incrementar();
                }
            });
        } else {
            alert.setPositiveButton("Reiniciar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    reiniciar();
                }
            });
        }

        alert.create().show();
    }

    private void reiniciar() {
        tentativas = 4;
        contVitoria = 0;
        indexWords = 0;

        layout.removeAllViews();

        gerarTexto(indexWords);

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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Instanciando objetos
        sequence = new ArrayList<TextView>();
        layout = new LinearLayout(this);
        t = new TextView(this);
        btn = new Button[26];
        //usando métodos
        gerarTexto(indexWords);
        gerarBtn();

    }
}

