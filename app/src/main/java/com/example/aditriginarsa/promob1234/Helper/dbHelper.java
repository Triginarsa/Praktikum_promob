package com.example.aditriginarsa.promob1234.Helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class dbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    static final String db_name = "serverpromob2.db";
    public static final String TABEL_SQLite = "pegawai";
    public static final String c_id = "id";
    public static final String c_nama = "nama";
    public static final String c_email = "email";
    public static final String c_jk = "jk";
    public static final String c_hobby = "hobby";
    public static final String c_serius = "serius";

    public dbHelper(Context context){
        super(context, db_name, null,DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_PEGAWAI_TABLE = "CREATE TABLE " + TABEL_SQLite +
                " ("+c_id + " INTEGER PRIMARY KEY autoincrement, " +
                c_nama + " TEXT NOT NULL, " +
                c_email + " TEXT NOT NULL, " +
                c_jk + " TEXT NOT NULL, " +
                c_hobby + " TEXT NOT NULL, " +
                c_serius + " TEXT NOT NULL" + " )";

        db.execSQL(SQL_CREATE_PEGAWAI_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_SQLite);
        onCreate(db);

    }

    public ArrayList<HashMap<String, String>> getAllData(){
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " + TABEL_SQLite;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(c_id, cursor.getString(0));
                map.put(c_nama, cursor.getString(1));
                map.put(c_email, cursor.getString(2));
                map.put(c_jk, cursor.getString(3));
                map.put(c_hobby, cursor.getString(4));
                map.put(c_serius, cursor.getString(5));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        Log.e("select sqlite", ""+wordList);

        database.close();
        return wordList;
    }

    public void insert (String nama, String email, String jk, String hobby, String serius){
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValue = "INSERT INTO " + TABEL_SQLite + " (nama, email, jk, hobby, serius) "
                + "VALUES ('" + nama + "','" + email + "','" + jk + "','" + hobby + "','" + serius + "')";

        Log.e("insert sqlite", ""+queryValue);
        database.execSQL(queryValue);
        database.close();
    }

    public void update (int id, String nama, String email, String jk, String hobby, String serius){
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "UPDATE "+ TABEL_SQLite + " SET "
                + c_nama + "='" + nama + "',"
                + c_email + "='" + email + "',"
                + c_jk + "='" + jk + "',"
                + c_hobby + "='" + hobby + "',"
                + c_serius + "='" + serius + "'"
                + " WHERE "+ c_id + "=" + "'" + id + "'";

        Log.e("update sqlite", ""+updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    public void delete (int id){
        SQLiteDatabase database = this.getWritableDatabase();

        String deleteQuery = "DELETE FROM " + TABEL_SQLite + " WHERE " + c_id + "='" + id + "'";

        Log.e("delete sqlite", ""+deleteQuery);
        database.execSQL(deleteQuery);
        database.close();
    }
}
