package com.ensim.calandarplus.Controllers.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ensim.calandarplus.Models.Adapter_categorie;
import com.ensim.calandarplus.Models.CategorieDB;
import com.ensim.calandarplus.Models.CategorieHelper;
import com.ensim.calandarplus.Models.TacheDB;
import com.ensim.calandarplus.Models.TacheHelper;
import com.example.calandarplus.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsTache extends AppCompatActivity {

    private static String TAG = "DetailTache";

    //Definition des éléments de la vu (activity_details_tache.xml)
    @BindView(R.id.include_toolbar_detail_tache) Toolbar toolbar;
    @BindView(R.id.Button_add) ImageView imageadd;
    @BindView(R.id.nom_tache_detail) TextView nom_tache;
    @BindView(R.id.nom_cat_detail) TextView nom_cat;

    private int id_tache;
    private TacheHelper tache_helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_tache);

        //Récupération des éléments de la vu (activity_main.xml)
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        id_tache=extras.getInt("id_tache");
        Log.d(TAG, "id_tache : "+id_tache);

        this.configurerToolBar();
        Log.d(TAG, "Configuration Toolbar ok");

        this.ConfigurerTextView();
    }

    //Configure la toolbar : titre et bouton retour
    private void configurerToolBar(){
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            Log.d(TAG, "Actionbar non null");
            //Affiche le bouton retour vers la page précédente
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setTitle(R.string.details_tache);
        //Rends invisible le bouton + de la toolbar
        imageadd.setVisibility(View.INVISIBLE);
    }

    //Configure les TextView devant prendre des valeurs dans la base de donnée
    private void ConfigurerTextView() {
        tache_helper = new TacheHelper(this);
        CategorieHelper cat_helper = new CategorieHelper(this);

        //Lit le contenu de la table tache
        SQLiteDatabase db_tache = tache_helper.getReadableDatabase();

        //Requete SQL à l'aide de l'helper de tache :
        // Cherche les lignes de la tache tache dont la catégorie associé correspond à l'id trouvé plus haut
        //Renvoit un cursor contenant les Id de catégorie et le nom des taches associées
        Cursor cursor_tache = db_tache.query(TacheDB.Tache.TABLE,
                new String[] {TacheDB.Tache.COL_TACHE_NAME, TacheDB.Tache.COL_ID_CAT},
                TacheDB.Tache._ID +" = ?" , new String[] {String.valueOf(id_tache)}, null, null, null
        );

        while(cursor_tache.moveToNext()){
            Log.d(TAG, "Cursor tache non null");
            nom_tache.setText(cursor_tache.getString(cursor_tache.getColumnIndex(TacheDB.Tache.COL_TACHE_NAME)));
            String id_cat = cursor_tache.getString(cursor_tache.getColumnIndex(TacheDB.Tache.COL_ID_CAT));
            Log.d(TAG, "Recuperation non tache et id catégorie");
            Log.d(TAG, "id catégorie : " + id_cat);
            SQLiteDatabase db_cat = cat_helper.getReadableDatabase();
            Cursor cursor_cat = db_cat.query(CategorieDB.Categorie.TABLE,
                    new String[] {CategorieDB.Categorie.COL_CAT_NAME, CategorieDB.Categorie._ID},
                    CategorieDB.Categorie._ID +" = ?" , new String[] {id_cat}, null, null, null
            );
            while(cursor_cat.moveToNext()){
                 Log.d(TAG, "Cursor catégorie non null");
                int index = cursor_cat.getColumnIndex(CategorieDB.Categorie.COL_CAT_NAME);
                Log.d(TAG, "Index : "+index);
                String cat = cursor_cat.getString(index);
                Log.d(TAG, "Nom catégorie : " + cat);
                nom_cat.setText(cat);
                Log.d(TAG, "Recuperation nom catégorie");
            }
            db_cat.close();
        }
        db_tache.close();
    }
}
