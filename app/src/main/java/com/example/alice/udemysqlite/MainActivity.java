package com.example.alice.udemysqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Fill Database With fake data
     * SQLiteDataBase
     * @param view
     */
    public void insetData(View view) {
        //Access to DB
        SQLiteDatabase db = openOrCreateDatabase("MyDB", MODE_PRIVATE, null);

        db.execSQL("CREATE TABLE IF NOT EXISTS Person( LastName TEXT, FirstName TEXT, Age INT(3)) ");
        db.execSQL("INSERT INTO Person VALUES ('Beltran', 'Alicia', 30)");
        db.execSQL("INSERT INTO Person VALUES ('Beltran', 'Angela', 32)");
        db.execSQL("INSERT INTO Person VALUES ('Castaneda', 'Carmen', 74)");

        db.close();

        Toast.makeText(this, "DB Created", Toast.LENGTH_SHORT).show();
    }

    /**
     * Read data from  table using SQLiteDataBase & Cursor
     * @param view
     */
    public void retriveData(View view) {
        SQLiteDatabase db = openOrCreateDatabase("MyDB", MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM Person", null);
        cursor.moveToFirst();

        do{
            int columnIndex = cursor.getColumnIndex("FirstName");
            Toast.makeText(this, "User: "+ cursor.getString(columnIndex),Toast.LENGTH_SHORT).show();
        }while (cursor.moveToNext());

        db.close();
    }
}
