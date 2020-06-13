package com.ensim.calandarplus.Controllers.Fragments;

/**
 * Author : Lauriane GOURAUD
 */

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.calandarplus.R;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ToDoList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ToDoList extends BaseFragment {
    //Definition des éléments de la vu (fragment_to_do_lists.xml)
    @BindView(R.id.zone_texte_frag_todolist) TextView textView;
    @BindView(R.id.recycler_view_categorie) RecyclerView recyclerView_categorie;
    @BindView(R.id.recycler_view_task) RecyclerView recyclerView_task;

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
        recyclerView_categorie.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    //Mettre à jour interface graphique du fragment
    @Override
    protected void updateDesign() {
    }

}
