package com.ensim.calandarplus.Models;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ensim.calandarplus.Controllers.Fragments.ToDoList;
import com.example.calandarplus.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author : Lauriane GOURAUD
 */

//Permet d'afficher facielemnt les taches dans des recyclerview
public class Adapter_tache extends RecyclerView.Adapter<Adapter_tache.ViewHolder>{

    private static final String TAG = "AdapterTache";

    private Adapter_categorie adapter_cat;
    private List<TacheDB.Tache> list_taches;
    private ToDoList todolist_frag;
    public Context context;

    //1 element
    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.nom_tache) TextView text_tache_name;
        @BindView(R.id.delete_tache)  ImageView delete_tache;
        private View itemView;

        private int id;

        public ViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "ViewHolder");
            this.itemView = itemView;

            ButterKnife.bind(this,itemView);

            //Gestion du listener de la textview du om d'une catégorie
            // (présent dans le layout cards_tache)
            text_tache_name.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onclick text_tache_name");
                    //Utilisation de l'instance du fragment récupéré dans le constructeur de Adapter_tache
                    // pour appeler la méthode Detail_Tache du fragment ToDoList
                    // sur un champ de texte du layout cards_tache
                    Adapter_tache.this.todolist_frag.Detail_Tache(v, id);
                }
            });
            //Gestion du listener du bouton de suppression de tache
            // (présent dans le layout cards_tache)
            delete_tache.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onclick delete_tache");
                    //Utilisation de l'instance du fragment récupéré dans le constructeur de Adapter_tache
                    // pour appeler la méthode DeleteTache du fragment ToDoList
                    // sur un icon du layout cards_tache
                    Adapter_tache.this.todolist_frag.DeleteTache(v);
                }
            });
        }
    }

    //Constructeur qui prend en paramètre l'instance d'un adapter_categorie (pour recuperer ensuite l'instance de la todolist associée)
    public Adapter_tache(List<TacheDB.Tache> list_taches, Adapter_categorie adapter) {
        Log.d(TAG, "ConstructeurAdapteur");
        this.list_taches = list_taches;
        this.adapter_cat = adapter;
        this.todolist_frag = adapter.GetTodolist_frag();
    }


    //Créer une nouvelle instance de la classe ViewHolder et la lie au bon layout
    @NonNull
    @Override
    public Adapter_tache.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");

        context = parent.getContext();
        //Lie chaque instance de ViewHolder à un layout cards_tache
        View view = LayoutInflater.from(context).inflate(R.layout.cards_tache, parent, false);
        return new Adapter_tache.ViewHolder(view);
    }

    //Gère le contenu des ViewHolder
    @Override
    public void onBindViewHolder(@NonNull Adapter_tache.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder");
        final TacheDB.Tache tache = list_taches.get(position);
        Log.d(TAG, "name tache : "+ tache.getName());
        holder.text_tache_name.setText(tache.getName());
        holder.id = tache.getId();
    }

    // Retourne le nombre d'élements (ici tache) existantes
    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount");
        return list_taches.size();
    }

}
