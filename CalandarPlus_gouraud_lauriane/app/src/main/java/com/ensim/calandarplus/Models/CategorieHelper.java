package com.ensim.calandarplus.Models;

/**
 * Author : Lauriane GOURAUD
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

//Permet de faciliter la communication avec la base de données CategorieDB : Création de Categorie, suppression de Categories, recupération de Categorie, etc
public class CategorieHelper extends SQLiteOpenHelper{

    public final static String TAG ="CategorieHelper";

    public CategorieHelper(Context context){
        super(context, CategorieDB.DB_NAME, null, CategorieDB.DB_VERSION);
        Log.d(TAG, "Constructeur");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Creer table");
        String createTableCategorie =
                "CREATE TABLE  "+ CategorieDB.Categorie.TABLE+
                        " ( " + CategorieDB.Categorie._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        CategorieDB.Categorie.COL_CAT_NAME + " TEXT NOT NULL); ";
        db.execSQL(createTableCategorie);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "Upgrade table");
        db.execSQL("DROP TABLE IF EXISTS " + CategorieDB.Categorie.TABLE);
        onCreate(db);
    }

}
