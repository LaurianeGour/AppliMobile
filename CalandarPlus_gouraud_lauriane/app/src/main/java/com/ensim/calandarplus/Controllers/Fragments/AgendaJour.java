package com.ensim.calandarplus.Controllers.Fragments;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ensim.calandarplus.Controllers.Activities.AjouterEvent;
import com.example.calandarplus.R;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgendaJour#newInstance} factory method to
 * create an instance of this fragment.
 */
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
