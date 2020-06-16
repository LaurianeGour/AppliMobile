package com.ensim.calandarplus.Models;

/**
 * Author : Lauriane GOURAUD
 */

import android.provider.BaseColumns;
import android.util.Log;

//Création des attributs propre à la base de donnée TacheDB
public class TacheDB {
    public static final String DB_NAME = "db_tache";
    public static final int DB_VERSION = 1;

    //Represente une table Tache
    public static class Tache implements BaseColumns {

        public static final String TAG = "Tache";

        public static final String TABLE = "tache";

        public static final String COL_TACHE_NAME = "nom";
        public static final String COL_ID_CAT = "id_cat";

        private String name;
        private int id_cat;


        public Tache(String name, int id){
            Log.d(TAG, "constructeur");
            this.name = name;
            this.id_cat = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name){
            this.name = name;
        }

        public int GetId(){
            return id_cat;
        }

        public void SetId(int id){
            this.id_cat = id;
        }


    }
}



