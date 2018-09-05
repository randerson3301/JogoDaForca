package com.randerson.mendes.jogodaforca;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FimActivity extends Activity {

    TextView txtAcertos, txtErros, txtResposta;
    ImageView img;
    LinearLayout layout;
    MediaPlayer evilLaugh, winSong;

    int acertos = MainActivity.acertos;
    int erros = MainActivity.erros;


    //Método iniciar jogo
    public void reiniciar(View v){
        Intent intent = new Intent(this, MainActivity.class);
        MainActivity.acertos = 0;
        MainActivity.erros = 0;
        startActivity(intent);
        winSong.stop();
        evilLaugh.stop();

    }

    private void setResultado() {
        if (acertos > erros) {
            txtResposta.setText("YOU WIN");
            img.setImageDrawable(getResources().getDrawable(R.drawable.trofeu));
            layout.setBackgroundColor(getResources().getColor(R.color.green));
            winSong.start();
        } else {
            txtResposta.setText("YOU LOSE");
            txtResposta.setTextColor(getResources().getColor(R.color.white));
            layout.setBackgroundColor(getResources().getColor(R.color.red));
            img.setImageDrawable(getResources().getDrawable(R.drawable.hangman));
            evilLaugh.start();

        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fim);

        //configurando as músicas
        evilLaugh = MediaPlayer.create(this, R.raw.evil_laugh);
        winSong = MediaPlayer.create(this, R.raw.pomp_and_circumstance);

        evilLaugh.setLooping(true);
        winSong.setLooping(true);
        //instanciando...
        txtAcertos = new TextView(this);
        txtErros = new TextView(this);
        txtResposta = new TextView(this);
        img = new ImageView(this);
        layout = new LinearLayout(this);

        txtAcertos = findViewById(R.id.txtAcertos);
        txtErros = findViewById(R.id.txtErros);
        txtResposta = findViewById(R.id.txtResposta);
        img = findViewById(R.id.img);
        layout = findViewById(R.id.mainLayout);

        setResultado();

        txtAcertos.setText(txtAcertos.getText().toString()+ " " + acertos);
        txtErros.setText(txtErros.getText().toString()+ " " + erros);

    }
}
