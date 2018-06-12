package com.example.aditriginarsa.promob1234;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aditriginarsa.promob1234.Helper.dbHelper;

public class InputActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView IV_bg_img, IV_logo_static2_img;
    EditText ET_nama, ET_email;
    TextView TV_judul, TV_jk, TV_hobby, TV_serius;
    Button B_daftar;
    RadioGroup RG_jeniskelamin;
    RadioButton RB_pria, RB_wanita;
    CheckBox CB_makan, CB_tidur, CB_dota;
    SeekBar SB_serius;
    String jeniskelamin;
    int nilai_serius;
    dbHelper dbcon = new dbHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        radioChange();
        seekbarr();
        B_daftar.setOnClickListener(this);
    }

    private void initView(){
        IV_bg_img           = findViewById(R.id.IV_bg);
        ET_nama             = findViewById(R.id.ET_nama);
        ET_email            = findViewById(R.id.ET_email);
        TV_judul            = findViewById(R.id.TV_judul);
        B_daftar            = findViewById(R.id.B_daftar);
        TV_jk               = findViewById(R.id.TV_jk);
        RG_jeniskelamin     = findViewById(R.id.RG_jk);
        RB_pria             = findViewById(R.id.RB_pria);
        RB_wanita           = findViewById(R.id.RB_wanita);
        TV_hobby            = findViewById(R.id.TV_hobby);
        CB_makan            = findViewById(R.id.CB_makan);
        CB_tidur            = findViewById(R.id.CB_tidur);
        CB_dota             = findViewById(R.id.CB_dota);
        SB_serius           = findViewById(R.id.SB_serius);
        TV_serius           = findViewById(R.id.TV_serius);
        IV_logo_static2_img = findViewById(R.id.IV_logostatic2_img);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.B_daftar:
                shwdialog();
                break;
        }
    }

    public void shwdialog(){
        final AlertDialog.Builder a_builder = new AlertDialog.Builder(InputActivity.this);
        @SuppressLint("InflateParams") View aview = getLayoutInflater().inflate(R.layout.dialog_datadiri, null);
        String hobby            = "";
        String email = ET_email.getText().toString().trim();
        String formatemail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String nama = ET_nama.getText().toString().trim();
        String formatnama = "[a-zA-Z ]+";
        if(ET_nama.getText().toString().length()==0) {
            ET_nama.setError("Nama Tidak Boleh Kosong!");
        } else if(!nama.matches(formatnama)) {
            ET_nama.setError("Format Nama Salah!");
        }else if(ET_email.getText().toString().length()==0) {
            ET_email.setError("E-mail Tidak Boleh Kosong!");
        } else if(!email.matches(formatemail)) {
            ET_email.setError("Format E-mail Salah!");
        } else if (RG_jeniskelamin.getCheckedRadioButtonId() == -1){
            Toast.makeText(InputActivity.this,
                    "Pilih Jenis Kelamin",
                    Toast.LENGTH_LONG).show();
        } else{
            final TextView dTV_vnama      = aview.findViewById(R.id.TV_vnama);
            dTV_vnama.setText(ET_nama.getText());
            final TextView dTV_vemail     = aview.findViewById(R.id.TV_vemail);
            dTV_vemail.setText(ET_email.getText());
            final TextView dTV_vjk        = aview.findViewById(R.id.TV_vjk);
            final TextView dTV_vhobby     = aview.findViewById(R.id.TV_vhobby);
            if(CB_makan.isChecked()){
                hobby+="- Makan\n";
            }
            if(CB_tidur.isChecked()){
                hobby+="- Tidur\n";
            }
            if(CB_dota.isChecked()){
                hobby+="- Ngedota\n";
            } if(!CB_makan.isChecked() && !CB_tidur.isChecked() && !CB_dota.isChecked()){
                hobby+="(Tidak Punya Hobby)";
            }
            dTV_vhobby.setText(hobby);
            final TextView dTV_serius     = aview.findViewById(R.id.TV_vserius);
            dTV_serius.setText(getSerius());
            dTV_vjk.setText(jeniskelamin);
            Button B_batal          = aview.findViewById(R.id.B_batal);
            Button B_konfirmasi     = aview.findViewById(R.id.B_konfirmasi);

            a_builder.setView(aview);
            final AlertDialog dialog = a_builder.create();
            dialog.show();

            B_batal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.cancel();
                }
            });

            B_konfirmasi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String nama = dTV_vnama.getText().toString();
                    String email = dTV_vemail.getText().toString();
                    String jk = dTV_vjk.getText().toString();
                    String hobby = dTV_vhobby.getText().toString();
                    String serius = dTV_serius.getText().toString();
                    dbcon.insert(nama.trim(), email.trim(), jk.trim(), hobby.trim(), serius.trim());
                    Intent intent = new Intent(InputActivity.this, ListActivity.class);
                    intent.putExtra("Nama", dTV_vnama.getText().toString());
                    intent.putExtra("E-mail", dTV_vemail.getText().toString());
                    intent.putExtra("Jenis Kelamin", dTV_vjk.getText().toString());
                    intent.putExtra("Hobby", dTV_vhobby.getText().toString());
                    intent.putExtra("Serius", dTV_serius.getText().toString());
                    startActivity(intent);
                }
            });
        }
    }



    @SuppressLint("SetTextI18n")
    public void seekbarr( ){
        TV_serius.setText(SB_serius.getProgress() + "% Serius Bekerja");
        SB_serius.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        nilai_serius = progress;
                        TV_serius.setText("Anda " + progress + "% Serius Bekerja");

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        TV_serius.setText(nilai_serius + "% Serius Bekerja");
                    }
                }
        );
    }

    public String getSerius(){
        return TV_serius.getText().toString();
    }

    public void radioChange(){
        RG_jeniskelamin.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb=group.findViewById(checkedId);
                jeniskelamin=rb.getText().toString();
            }
        });
    }

}

