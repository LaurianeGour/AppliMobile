package com.ensim.calandarplus.Models;

/**
 * Author : Lauriane GOURAUD
 */

import android.provider.BaseColumns;
import android.util.Log;

//Création des attributs propre à la base de donnée de l'application
public class CategorieDB {

    public static final String DB_NAME = "CalandarPlus";
    public static final int DB_VERSION = 1;

    //Represente une table
    public static class Categorie implements BaseColumns {
        public static final String TABLE = "Categorie";
        public static final String COL_CAT_NAME = "name";
        public static final String TAG = "Categorie";

        public Categorie(String name){
            Log.d(TAG, "constructeur");
            this.name = name;
        }

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name){
            this.name = name;
        }
    }

}


