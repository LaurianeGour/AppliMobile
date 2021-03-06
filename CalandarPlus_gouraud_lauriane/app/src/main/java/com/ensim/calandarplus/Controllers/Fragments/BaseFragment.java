package com.ensim.calandarplus.Controllers.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import icepick.Icepick;
import icepick.State;

/**
 * Author : Lauriane GOURAUD
 */

public abstract class BaseFragment extends Fragment {

    @State int buttonTag;

    //Forcer le developpeur à implémenter ces methodes
    protected abstract int getFragmentLayout();
    protected abstract void configureDesign();
    protected abstract void updateDesign();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(getFragmentLayout(), container, false);
        ButterKnife.bind(this,view); //Lier les elements de la vu avec le .java
        this.configureDesign();
        return(view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState); //Restorer l'état de la vue (avec le bundle)
        this.updateDesign();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }
}
