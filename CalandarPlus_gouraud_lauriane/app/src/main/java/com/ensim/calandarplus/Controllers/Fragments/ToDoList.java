package com.ensim.calandarplus.Controllers.Fragments;

/**
 * Author : Lauriane GOURAUD
 */

import androidx.fragment.app.Fragment;

import android.widget.TextView;

import com.example.calandarplus.R;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ToDoList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ToDoList extends BaseFragment {
    //Definition des éléments de la vu (fragment_to_do_lists.xml)
    @BindView(R.id.zone_texte_frag_todolist) TextView textView;

    //Retourne une instance du fragment ToDoList
    public static ToDoList newInstance() {
        return new ToDoList();
    }

    //Retourne l'identifiant du Layout
    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_to_do_list;
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
