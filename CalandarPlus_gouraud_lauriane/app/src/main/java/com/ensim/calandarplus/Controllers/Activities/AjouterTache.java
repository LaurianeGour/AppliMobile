package com.ensim.calandarplus.Controllers.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.util.Log;
import com.example.calandarplus.R;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AjouterTache extends AppCompatActivity {

    private static String TAG = "Ajouter Tache";

    //Definition des éléments de la vu (activity_main.xml)
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.Button_add) ImageView imageadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_tache);

        //Récupération des éléments de la vu (activity_main.xml)
        ButterKnife.bind(this);

        this.configurerToolBar();

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

}
