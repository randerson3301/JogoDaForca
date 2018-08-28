package com.randerson.mendes.jogodaforca;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TesteActivity extends Activity {

    TextView t;
    LinearLayout layout;
    int cont = 0;

    char words[][] = {
            {'A', 'L', 'T', 'U', 'R', 'A'},
            {'C', 'A', 'S', 'A'},
            {'P', 'H', 'P'}
    };

    public void gerarTexto(int cont){

        layout = findViewById(R.id.MyLayout);

        for (int i = 0; i <= words[cont].length - 1; i++) {
            t = new TextView(this);

            //Retirando o layout do textView
            t.setText("_");
            t.setHeight(70);
            t.setWidth(40);
            t.setTextColor(getResources().getColor(R.color.white));
            t.setTextSize(25);

            layout.addView(t);
        }
    }

    public void incrementar(View v) {
        layout.removeAllViews(); //removendo todos os elementos do layout
        cont++;
        gerarTexto(cont);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_teste);
        layout = new LinearLayout(this);
        t = new TextView(this);
        gerarTexto(cont);


    }
}
