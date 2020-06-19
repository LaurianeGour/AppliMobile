package com.ensim.calandarplus.Controllers.Fragments;

/**
 * Author : Lauriane GOURAUD
 */

import android.widget.ImageView;
import com.example.calandarplus.R;

import butterknife.BindView;

//Non implémenté
public class AgendaSemaine extends BaseFragment{

    private static String TAG = "Frag Semaine";

    //Definition des éléments de la vu (fragment_agenda_semaine.xml)
    @BindView(R.id.bouton_sem_add_event) ImageView addevent;

    //Retourne une instance du fragment AgendaSemaine
    public static AgendaSemaine newInstance() {
        return new AgendaSemaine();
    }

    //Retourne l'identifiant du Layout
    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_agenda_semaine;
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
