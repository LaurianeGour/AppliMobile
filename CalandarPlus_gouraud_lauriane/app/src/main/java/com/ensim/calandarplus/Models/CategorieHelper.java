package com.ensim.calandarplus.Models;

/**
 * Author : Lauriane GOURAUD
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CategorieHelper extends SQLiteOpenHelper{

    public CategorieHelper(Context context){
        super(context, DataBase.DB_NAME, null, DataBase.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableCategorie =
                "CREATE TABLE  "+ Categorie.TABLE+
                        " ( " + Categorie._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        Categorie.COL_CAT_NAME + " TEXT NOT NULL); ";
        db.execSQL(createTableCategorie);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Categorie.TABLE);
        onCreate(db);
    }
}
