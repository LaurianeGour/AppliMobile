package com.ensim.calandarplus.Models;

/**
 * Author : Lauriane GOURAUD
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TacheHelper extends SQLiteOpenHelper{

    public TacheHelper(Context context){
        super(context, DataBase.DB_NAME, null, DataBase.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableCategorie =
                "CREATE TABLE  "+ Tache.TABLE+
                        " ( " + Tache._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        Tache.COL_TACHE_NAME + " TEXT NOT NULL, "+
                        Tache.COL_ID_CAT + " TEXT NOT NULL); ";
        db.execSQL(createTableCategorie);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Tache.TABLE);
        onCreate(db);
    }

}
