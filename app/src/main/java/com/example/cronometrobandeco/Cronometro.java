package com.example.cronometrobandeco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Cronometro extends AppCompatActivity {

    private TextView timer;
    private Button btnStart;
    private Button btnStop;
    private Button btnSave;

    private Context cContext;
    private Contagem cContagem;
    private Thread cThreadCrono;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);

        cContext = this;

       timer = findViewById(R.id.timerXML);
       btnStart = findViewById(R.id.startXML);
       btnStop = findViewById(R.id.stopXML);
       btnSave = findViewById(R.id.saveXML);



       btnStart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

              if(cContagem == null){
                  cContagem = new Contagem(cContext);
                  cThreadCrono = new Thread(cContagem);
                  cThreadCrono.start();
                  cContagem.start();
              }

           }
       });

       btnStop.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(cContagem !=null){
                   cContagem.stop();
                   cThreadCrono.interrupt();
                   cThreadCrono = null;

                   cContagem = null;
               }
           }
       });

    }

    public void updateTimerText(final String time){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                timer.setText(time);
            }
        });

    }

}