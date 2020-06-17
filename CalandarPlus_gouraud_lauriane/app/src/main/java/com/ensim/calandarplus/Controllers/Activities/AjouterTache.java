package com.ensim.calandarplus.Controllers.Activities;

/**
 * Author : Lauriane GOURAUD
 */

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import com.ensim.calandarplus.Models.CategorieDB;
import com.ensim.calandarplus.Models.TacheDB;
import com.ensim.calandarplus.Models.TacheHelper;
import com.example.calandarplus.R;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.BindView;

import butterknife.ButterKnife;
import com.ensim.calandarplus.Models.CategorieHelper;

import java.util.ArrayList;
import java.util.List;

public class AjouterTache extends AppCompatActivity  implements
        AdapterView.OnItemSelectedListener {

    private static String TAG = "Ajouter Tache";

    //Definition des éléments de la vu (activity_main.xml)
    @BindView(R.id.include_toolbar) Toolbar toolbar;
    @BindView(R.id.editText_ajouter_tache) EditText edit_text_tache;
    @BindView(R.id.spinner_lst_cat) Spinner spinner_categorie;
    @BindView(R.id.Button_add) ImageView imageadd;
    @BindView(R.id.valider_add_tache) Button bouton_add_tache;

    private List<String> list_categorie;
    private List<Integer> list_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_tache);

        //Récupération des éléments de la vu (activity_main.xml)
        ButterKnife.bind(this);

        this.configurerToolBar();
        ConfigurerSpinner();

        //Gestion du listener du bouton de validation d'ajout d'une tache
        // (présent dans le layout activity_ajouter_tache)
        bouton_add_tache.setOnClickListener(new View.OnClickListener() {
            //Pour plus de lisibilité, la fonction est définit plus bas
            @Override
            public void onClick(View v) {
                ValiderAjoutTache(v);
            }
        });

        Log.d(TAG, "Page configurée");
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
        setTitle(TAG);
        //Rends invisible le bouton + de la toolbar
        imageadd.setVisibility(View.INVISIBLE);
    }

    //Le spinner contient la liste des catégories existante : pour lier une tache à une catégorie
    public void ConfigurerSpinner(){
        list_categorie = new ArrayList<String>();
        list_id = new ArrayList<Integer>();

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
            int index = cursor.getColumnIndex(CategorieDB.Categorie.COL_CAT_NAME);
            String cat = cursor.getString(index);
            list_categorie.add(cat);

            //Ajoute à la liste d'id decatégorie l'id d'une catégorie
            int index_id = cursor.getColumnIndex(CategorieDB.Categorie._ID);
            int id_cat = cursor.getInt(index_id);
            list_id.add(id_cat);

            //Choix de le faire sur 2 liste car je ne savais pas comment faire la suite avec seulement 1 liste
            //Je n'ai pas voulu perdre de temps à faire des recherches supplémentaires
        }

        cursor.close();
        db.close();

        //Transmet la liste des catégorie au spinner à l'aide d'un adapter de string
        ArrayAdapter<String> aa = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, list_categorie);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //associe l'adapteur au spinnet
        spinner_categorie.setAdapter(aa);
    }

    //Recupère l'élement sélectionné du spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),list_categorie.get(position) , Toast.LENGTH_LONG).show();
    }

    //Vide car par défaut une catégorie est renseigner
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    //S'execute quand le bouton add_tache est enclanché
    public void ValiderAjoutTache(View view) {
        Log.d(TAG, "Valider Ajout tache");

        //Enleve automatiquemet la selection de l'edittext
        edit_text_tache.setEnabled(false);
        edit_text_tache.setEnabled(true);

        TacheHelper tache_helper = new TacheHelper(this);
        //Ouvre la base de donnée de tache en lecture
        SQLiteDatabase db = tache_helper.getWritableDatabase();
        //Values sera les parametres à reneigner : nom de la tache et id de la catégorie associée
        ContentValues values = new ContentValues();
        Log.d(TAG, "Nom cat : " + edit_text_tache.getText().toString());
        values.put(TacheDB.Tache.COL_TACHE_NAME, edit_text_tache.getText().toString());
        String nom_cat = spinner_categorie.getSelectedItem().toString();
        int index = list_categorie.indexOf(nom_cat);
        int id_categorie = list_id.get(index);
        Log.d(TAG, "id cat : " +id_categorie);
        values.put(TacheDB.Tache.COL_ID_CAT, id_categorie);
        //Insert une nouvelle tache dans la table tache
        db.insertWithOnConflict(TacheDB.Tache.TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();



        //Relance l'activité main activity
        Intent intent= new Intent(AjouterTache.this, MainActivity.class);
        startActivity(intent);
    }
}
