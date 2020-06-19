package com.ensim.calandarplus.Controllers.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.Spinner;

import com.ensim.calandarplus.Models.CategorieDB;
import com.ensim.calandarplus.Models.CategorieHelper;
import com.ensim.calandarplus.Models.TacheDB;
import com.ensim.calandarplus.Models.TacheHelper;
import com.example.calandarplus.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsTache extends AppCompatActivity {

    private static String TAG = "DetailTache";

    //Definition des éléments de la vu (activity_details_tache.xml)
    @BindView(R.id.include_toolbar_detail_tache) Toolbar toolbar;
    @BindView(R.id.bouton_add_toolbar) ImageView imageadd;
    @BindView(R.id.nom_tache_detail) EditText nom_tache;
    @BindView(R.id.nom_cat_detail) Spinner nom_cat;
    @BindView(R.id.ET_description_detail) EditText description_tache;

    private int id_tache;
    private TacheHelper tache_helper;
    private HashMap<Integer, String> list_cat;

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

        ArrayAdapter<String> aa = this.ConfigurerSpinner();

        this.ConfigurerView(aa);
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


    //Le spinner contient la liste des catégories existante : pour lier une tache à une catégorie
    public ArrayAdapter<String> ConfigurerSpinner(){
        list_cat = new HashMap<>();

        CategorieHelper cat_helper = new CategorieHelper(this);
        SQLiteDatabase db = cat_helper.getReadableDatabase();

        //Requete SQL à l'aide de l'helper de catégorie :
        //Récupere toutes les catégories (id et nom) dans le cursor
        Cursor cursor = db.query(CategorieDB.Categorie.TABLE,
                new String[] {CategorieDB.Categorie.COL_CAT_NAME, CategorieDB.Categorie._ID},
                null, null, null, null, null
        );

        //Si le curseur est non nul
        while(cursor.moveToNext()){
            Log.d(TAG, "Cursor while");
            //Ajoute à la liste de nom decatégorie le nom d'une catégorie
            String cat = cursor.getString(cursor.getColumnIndex(CategorieDB.Categorie.COL_CAT_NAME));
            int id_cat = cursor.getInt(cursor.getColumnIndex(CategorieDB.Categorie._ID));
            list_cat.put(id_cat, cat);
        }
        cursor.close();
        db.close();


        //Transmet la liste des catégorie au spinner à l'aide d'un adapter de string
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new ArrayList(list_cat.values()));
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //associe l'adapteur au spinnet
        nom_cat.setAdapter(aa);
        return aa;
    }


    //Configure les TextView devant prendre des valeurs dans la base de donnée
    private void ConfigurerView(ArrayAdapter<String> aa) {
        tache_helper = new TacheHelper(this);
        CategorieHelper cat_helper = new CategorieHelper(this);

        //Lit le contenu de la table tache
        SQLiteDatabase db_tache = tache_helper.getReadableDatabase();

        //Requete SQL à l'aide de l'helper de tache :
        // Cherche les lignes de la tache tache dont la catégorie associé correspond à l'id trouvé plus haut
        //Renvoit un cursor contenant les Id de catégorie et le nom des taches associées
        Cursor cursor_tache = db_tache.query(TacheDB.Tache.TABLE,
                new String[] {TacheDB.Tache.COL_TACHE_NAME,TacheDB.Tache.COL_ID_CAT, TacheDB.Tache.COL_DESCR},
                TacheDB.Tache._ID +" = ?" , new String[] {String.valueOf(id_tache)}, null, null, null
        );

        while(cursor_tache.moveToNext()){
            Log.d(TAG, "Cursor tache non null");
            String description = cursor_tache.getString(cursor_tache.getColumnIndex(TacheDB.Tache.COL_DESCR));
            if (description != null){
                description_tache.setText(description);
            }
            nom_tache.setText(cursor_tache.getString(cursor_tache.getColumnIndex(TacheDB.Tache.COL_TACHE_NAME)));
            int id_cat = cursor_tache.getInt(cursor_tache.getColumnIndex(TacheDB.Tache.COL_ID_CAT));
            String categorie = list_cat.get(id_cat);
            Log.d(TAG, "Nom catégorie : "+ categorie);
            nom_cat.setSelection(aa.getPosition(categorie));
        }
        db_tache.close();
    }

    public void EnregisterModif(View view) {
        Log.d(TAG, "EnregisterModif");

        //Enleve automatiquemet la selection de l'edittext
        nom_tache.setEnabled(false);
        nom_tache.setEnabled(true);

        description_tache.setEnabled(false);
        description_tache.setEnabled(true);

        String nom_tache_new = nom_tache.getText().toString();
        String nom_categorie_new = nom_cat.getSelectedItem().toString();
        String description_new = description_tache.getText().toString();
        Log.d(TAG, "nom tache : " +nom_tache_new + " | nom catégorie : " +nom_categorie_new );
        int id_categorie=-1;
        for(Map.Entry categorie : list_cat.entrySet()){
            if(categorie.getValue().toString().compareTo(nom_categorie_new) ==0){
                id_categorie = (int) categorie.getKey();
            }
        }
        Log.d(TAG, "id catégorie : "+id_categorie);
        if(id_categorie!=-1){
            SQLiteDatabase db_tache = tache_helper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(TacheDB.Tache.COL_ID_CAT, id_categorie);
            values.put(TacheDB.Tache.COL_TACHE_NAME, nom_tache_new);
            values.put(TacheDB.Tache.COL_DESCR, description_new);
            db_tache.update(TacheDB.Tache.TABLE, values, TacheDB.Tache._ID + "=" + id_tache, null);
            db_tache.close();

            //Relance l'activité main activity
            Intent intent= new Intent(DetailsTache.this, MainActivity.class);
            startActivity(intent);
        }else{
            AlertDialog erreur_modif = new AlertDialog.Builder(view.getContext())
                    .setMessage(R.string.erreur_modif_tache)
                    .setPositiveButton(R.string.ok,new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    } )
                    .create();
            erreur_modif.show();
            Button buttonPositive = erreur_modif.getButton(DialogInterface.BUTTON_POSITIVE);
            buttonPositive.setTextColor(ContextCompat.getColor(this, R.color.Text));
        }
    }
}
