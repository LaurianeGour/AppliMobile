package com.ensim.calandarplus.Models;

/**
 * Author : Lauriane GOURAUD
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

//Permet de faciliter la communication avec la base de données TacheDB : Création de tache, suppression de taches, recupération de taches, etc
public class TacheHelper extends SQLiteOpenHelper{

    public final static String TAG ="TacheHelper";

    public TacheHelper(Context context){
        super(context, TacheDB.DB_NAME, null, TacheDB.DB_VERSION);
        Log.d(TAG, "Constructeur");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Creer table");
        String createTableCategorie =
                "CREATE TABLE  "+ TacheDB.Tache.TABLE+
                        " ( " + TacheDB.Tache._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        TacheDB.Tache.COL_TACHE_NAME + " TEXT NOT NULL, "+
                        TacheDB.Tache.COL_ID_CAT + " TEXT NOT NULL); ";
        db.execSQL(createTableCategorie);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "Upgrade table");
        db.execSQL("DROP TABLE IF EXISTS " + TacheDB.Tache.TABLE);
        onCreate(db);
    }

}
