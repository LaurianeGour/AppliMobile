package com.ensim.calandarplus.Controllers.Fragments;

/**
 * Author : Lauriane GOURAUD
 */

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.calandarplus.R;

import butterknife.BindView;

public class Parametres extends BaseFragment {
    //Definition des éléments de la vu (fragment_agenda_semaine.xml)

    //Retourne une instance du fragment AgendaSemaine
    public static Parametres newInstance() {
        return new Parametres();
    }

    //Retourne l'identifiant du Layout
    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_parametres;
    }

    //Configurer interface graphique du fragment
    @Override
    protected void configureDesign() {
    }

    //Mettre à jour interface graphique du fragment
    @Override
    protected void updateDesign() {
    }

}
