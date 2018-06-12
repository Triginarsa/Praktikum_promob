package com.example.aditriginarsa.promob1234;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;


public class splashscreen extends Activity {
    //set waktu lama sPlashscreen
    ImageView IV_logoawal;
    ProgressBar PB_loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //MULAI KODING

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // KODING

        setContentView(R.layout.splashscreen);


        //KODING LAGI

        int lamaTampilSplash = 1500;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // to do auto generated stub
                Intent apasih = new Intent(splashscreen.this,ListActivity.class);
                startActivity(apasih);


                animasi();

                this.selesai();
            }

            private void selesai(){
                //auto
            }
        }, lamaTampilSplash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                this.selesai();
            }

            private void selesai(){
                //auto
            }
        }, 100);

    }



    public void animasi(){
        IV_logoawal = findViewById(R.id.IV_logoawal);
        IV_logoawal.setVisibility(View.GONE);
        PB_loading  = findViewById(R.id.PB_loading);
        PB_loading.setVisibility(View.GONE);
    }
}