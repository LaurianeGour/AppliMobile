package com.ensim.calandarplus.Controllers.Fragments;

/**
 * Author : Lauriane GOURAUD
 */

import android.widget.TextView;

import com.example.calandarplus.R;

import butterknife.BindView;

//Non implémenté
public class GererAgendas extends BaseFragment {
    //Definition des éléments de la vu (fragment_gerer_agendas.xml)
    @BindView(R.id.TV_frag_gere_agenda) TextView textView;

    //Retourne une instance du fragment GererAgendas
    public static GererAgendas newInstance() {
        return new GererAgendas();
    }

    //Retourne l'identifiant du Layout
    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_gerer_agendas;
    }

    //Configurer interface graphique du fragment
    @Override
    protected void configureDesign() {
    }

    //Mettre à jour interface graphique du fragment
    @Override
    protected void updateDesign() {
        this.updateTextView(this.buttonTag);
    }


    private void updateTextView(int tag) {
        this.buttonTag = tag;
        switch(tag){
            default:
                break;
        }

    }
}
