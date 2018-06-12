package com.example.aditriginarsa.promob1234;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class OutputActivity extends AppCompatActivity {
    TextView TV_vnama, TV_vemail, TV_vjk, TV_vhobby, TV_vserius;
    String nama = "", email = "", jk = "", hobby = "", serius = "", status="Sistem: ", id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);
        initView();
        setIntent();
    }

    private void initView(){
        TV_vnama    = findViewById(R.id.TV_vnama);
        TV_vemail   = findViewById(R.id.TV_vemail);
        TV_vjk      = findViewById(R.id.TV_vjk);
        TV_vhobby   = findViewById(R.id.TV_vhobby);
        TV_vserius  = findViewById(R.id.TV_vserius);
    }

    private void setIntent(){
        id      = getIntent().getStringExtra("Id");
        nama    = getIntent().getStringExtra("Nama");
        TV_vnama.setText(nama);
        email   = getIntent().getStringExtra("E-mail");
        TV_vemail.setText(email);
        jk      = getIntent().getStringExtra("Jenis Kelamin");
        TV_vjk.setText(jk);
        hobby   = getIntent().getStringExtra("Hobby");
        TV_vhobby.setText(hobby);
        serius  = getIntent().getStringExtra("Serius");
        TV_vserius.setText(serius);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(status,"onStart()");
        Toast.makeText(getApplicationContext(),"onStart Diaplikasikan", Toast.LENGTH_SHORT).show();
    }

    @Override
        protected void onPause() {
        super.onPause();
        Log.d(status,"onPause()");
        Toast.makeText(getApplicationContext(),"onPause Diaplikasikan", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(status,"onResume()");
        Toast.makeText(getApplicationContext(),"onResume Diaplikasikan", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(status, "onStop()");
        Toast.makeText(getApplicationContext(),"onStop Diaplikasikan", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(status,"onDestroy()");
        Toast.makeText(getApplicationContext(),"onDestroy Diaplikasikan", Toast.LENGTH_SHORT).show();
    }

}
