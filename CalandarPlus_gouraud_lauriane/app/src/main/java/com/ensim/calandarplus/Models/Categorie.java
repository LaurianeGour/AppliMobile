package com.ensim.calandarplus.Models;

import android.provider.BaseColumns;

public class Categorie implements BaseColumns {
        public static final String TABLE = "tasks";
        public static final String COL_TASK_NAME = "name";

        public Categorie(String name){
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
