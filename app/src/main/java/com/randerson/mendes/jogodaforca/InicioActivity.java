package com.randerson.mendes.jogodaforca;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

public class InicioActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);


    }


    //Método iniciar jogo
    public void iniciar(View v){
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);

    }
}
