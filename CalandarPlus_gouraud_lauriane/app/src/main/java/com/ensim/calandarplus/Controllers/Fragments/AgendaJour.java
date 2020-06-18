package com.ensim.calandarplus.Controllers.Fragments;

/**
 * Author : Lauriane GOURAUD
 */

import android.widget.ImageView;
import android.widget.TextView;

import com.example.calandarplus.R;

import butterknife.BindView;

//Non implémenté
public class AgendaJour extends BaseFragment {

    private static String TAG = "Frag Jour";

    //Definition des éléments de la vu (fragment_agenda_jour.xml)
    @BindView(R.id.zone_texte_frag_jour) TextView textView;
    @BindView(R.id.Button_jour_add_event) ImageView addevent;

    //Retourne une instance du fragment AgendaJour
    public static AgendaJour newInstance() {
        return new AgendaJour();
    }

    //Retourne l'identifiant du Layout
    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_agenda_jour;
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
