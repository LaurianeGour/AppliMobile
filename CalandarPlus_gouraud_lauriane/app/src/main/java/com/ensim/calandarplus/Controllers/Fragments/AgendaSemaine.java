package com.ensim.calandarplus.Controllers.Fragments;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ensim.calandarplus.Controllers.Activities.AjouterEvent;
import com.ensim.calandarplus.Controllers.Activities.MainActivity;
import com.example.calandarplus.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AgendaSemaine extends BaseFragment{

    private static String TAG = "Frag Semaine";

    //Definition des éléments de la vu (fragment_agenda_semaine.xml)
    @BindView(R.id.Button_sem_add_event) ImageView addevent;

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
