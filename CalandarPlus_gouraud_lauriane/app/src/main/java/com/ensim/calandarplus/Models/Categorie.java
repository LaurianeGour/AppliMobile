package com.ensim.calandarplus.Models;

import android.provider.BaseColumns;
import android.util.Log;

public class Categorie implements BaseColumns {
        public static final String TABLE = "tasks";
        public static final String COL_TASK_NAME = "name";
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
