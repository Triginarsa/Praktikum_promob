package com.example.aditriginarsa.promob1234;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.aditriginarsa.promob1234.Helper.dbHelper;
import com.example.aditriginarsa.promob1234.adapter.PegawaiAdapter;
import com.example.aditriginarsa.promob1234.api.ApiInterface;
import com.example.aditriginarsa.promob1234.model.PegawaiList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListActivity extends AppCompatActivity implements PegawaiAdapter.ListItemClick, RecycleItemTouchHelper.RecyclerItemTouchHelperListener, View.OnClickListener, Animation.AnimationListener {
    private ApiInterface apiInterface;
    ImageView IV_bg_img, IV_logo_img, IV_logo_static2_img;
    FloatingActionButton fab;
    List<PegawaiList> pegawaiList = new ArrayList<>();
    private PegawaiAdapter TpegawaiAdapter;
    private RecyclerView recyclerView;
    Boolean AnimEnd = false;
    dbHelper SQLite = new dbHelper(this);

    public static final String TAG_ID = "id";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_EMAIL = "email";
    public static final String TAG_JK = "jk";
    public static final String TAG_HOBBY = "hobby";
    public static final String TAG_SERIUS = "serius";
    public static final int UPDATE_CODE= 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_list);
        SQLite = new dbHelper(getApplicationContext());
        initView();
        animasi();
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecycleItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        getAllData();
        fab.setOnClickListener(this);
    }

    private void animasi() {
        IV_bg_img.setVisibility(View.GONE);
        IV_logo_static2_img.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        fab.setVisibility(View.GONE);

        Animation moveLogoAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_logo);
        moveLogoAnimation.setFillAfter(true);
        moveLogoAnimation.setAnimationListener(this);
        IV_logo_img.startAnimation(moveLogoAnimation);
    }

    private void initView() {
        fab = findViewById(R.id.fab);
        IV_bg_img = findViewById(R.id.IV_bg);
        IV_logo_img = findViewById(R.id.IV_logo);
        IV_logo_static2_img = findViewById(R.id.IV_logostatic2_img);
        recyclerView = findViewById(R.id.RV_pegawai);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }



    private void setPegawaiAdapter() {
        TpegawaiAdapter = new PegawaiAdapter(pegawaiList, this);
        recyclerView.setAdapter(TpegawaiAdapter);
    }

    @Override
    public void onItemClick(int position) {
        String pegawai = String.valueOf(pegawaiList.get(position).getId());
        String nama = pegawaiList.get(position).getNama();
        String email = pegawaiList.get(position).getEmail();
        String jk = pegawaiList.get(position).getJk();
        String hobby = pegawaiList.get(position).getHobby();
        String serius = pegawaiList.get(position).getSerius();
        Intent intent = new Intent(ListActivity.this, OutputActivity.class);
        intent.putExtra("Id", pegawai);
        intent.putExtra("Nama", nama);
        intent.putExtra("E-mail", email);
        intent.putExtra("Jenis Kelamin", jk);
        intent.putExtra("Hobby", hobby);
        intent.putExtra("Serius", serius);
        startActivity(intent);
    }

    @Override
    public void onUpdate(int position) {
        String pegawai = String.valueOf(pegawaiList.get(position).getId());
        String nama = pegawaiList.get(position).getNama();
        String email = pegawaiList.get(position).getEmail();
        String jk = pegawaiList.get(position).getJk();
        String hobby = pegawaiList.get(position).getHobby();
        String serius = pegawaiList.get(position).getSerius();
        Intent intent = new Intent(ListActivity.this, EditDetailActivity.class);
        intent.putExtra("Id", pegawai);
        intent.putExtra("Nama", nama);
        intent.putExtra("E-mail", email);
        intent.putExtra("Jenis Kelamin", jk);
        intent.putExtra("Hobby", hobby);
        intent.putExtra("Serius", serius);
        startActivityForResult(intent,UPDATE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==UPDATE_CODE){
            if (resultCode== Activity.RESULT_OK){
                getAllData();
            }
        }
    }

    @Override
    public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction, final int position) {
        if (viewHolder instanceof PegawaiAdapter.MyViewHolder) {
            final String ID_peg = String.valueOf(pegawaiList.get(position).getId());
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
            alertDialogBuilder.setTitle("Peringatan");
            alertDialogBuilder
                    .setMessage("Apakah Anda yakin ingin mengapus data ini?")
                    .setCancelable(false)
                    .setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            SQLite.delete(Integer.parseInt(ID_peg));
                            pegawaiList.clear();
                            getAllData();
                        }
                    })
                    .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            TpegawaiAdapter.CancelRemove(viewHolder.getAdapterPosition());
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(ListActivity.this, InputActivity.class);
        startActivity(intent);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        IV_logo_img.setVisibility(View.GONE);
        IV_logo_static2_img.setVisibility(View.VISIBLE);
        IV_bg_img.setAlpha(0f);
        IV_bg_img.setVisibility(View.VISIBLE);
        fab.setAlpha(0f);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setAlpha(0f);
        fab.setVisibility(View.VISIBLE);

        int mediumAnimationTime = getResources().getInteger(android.R.integer.config_mediumAnimTime);

        IV_bg_img.animate()
                .alpha(1f)
                .setDuration(mediumAnimationTime)
                .setListener(null);
        IV_logo_img.animate()
                .alpha(1f)
                .setDuration(mediumAnimationTime)
                .setListener(null);
        recyclerView.animate()
                .alpha(1f)
                .setDuration(mediumAnimationTime)
                .setListener(null);
        fab.animate()
                .alpha(1f)
                .setDuration(mediumAnimationTime)
                .setListener(null);

        AnimEnd = true;
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this, R.style.MyDialogTheme)
                .setMessage("Apakah ingin keluar ?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).show();
    }

    private void getAllData() {
        ArrayList<HashMap<String, String>> row = SQLite.getAllData();
        pegawaiList=new ArrayList<>();
        for (int i = 0; i < row.size(); i++) {
            String id       = row.get(i).get(TAG_ID);
            String nama     = row.get(i).get(TAG_NAMA);
            String email    = row.get(i).get(TAG_EMAIL);
            String jk       = row.get(i).get(TAG_JK);
            String hobby    = row.get(i).get(TAG_HOBBY);
            String serius   = row.get(i).get(TAG_SERIUS);

            PegawaiList pegawai = new PegawaiList();

            pegawai.setId(id);
            pegawai.setNama(nama);
            pegawai.setEmail(email);
            pegawai.setJk(jk);
            pegawai.setHobby(hobby);
            pegawai.setSerius(serius);

            pegawaiList.add(pegawai);
        }

        setPegawaiAdapter();
    }

}
