package com.example.cronometrobandeco;

import android.content.Context;

public class Contagem implements Runnable{

    public static final long MILLIS_TO_MINUTES = 60000;
    public static final long MILLIS_TO_HOURS = 3600000;

    private Context cContext;
    private long cStartTime;

    private boolean cIsRunning;

    public Contagem(Context cContext) {
        this.cContext = cContext;
    }


     public void start(){
        cStartTime = System.currentTimeMillis();
        cIsRunning = true;
     }

     public void stop(){

        cIsRunning = false;

     }


    @Override
    public void run() {

        while(cIsRunning){

           long since = System.currentTimeMillis() - cStartTime;

               int seconds = (int) ((since / 1000) % 60);
               int minutes = (int) (((since / MILLIS_TO_MINUTES)) % 60);
               int hours = (int) ((since / (MILLIS_TO_HOURS))%24);
               int millis = (int) since % 1000;

            ((Cronometro)cContext).updateTimerText(String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, millis));
        }


    }
}
