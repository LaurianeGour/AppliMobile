package com.ensim.calandarplus.Controllers.Fragments;

/**
 * Author : Lauriane GOURAUD
 */

import com.example.calandarplus.R;

//Non implémenté

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
