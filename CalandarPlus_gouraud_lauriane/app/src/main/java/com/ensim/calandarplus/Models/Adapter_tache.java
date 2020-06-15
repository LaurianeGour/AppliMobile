package com.ensim.calandarplus.Models;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ensim.calandarplus.Controllers.Fragments.ToDoList;
import com.example.calandarplus.R;

import java.util.List;

/**
 * Author : Lauriane GOURAUD
 */

public class Adapter_tache extends RecyclerView.Adapter<Adapter_tache.ViewHolder>{

    private static final String TAG = "AdapterTache";

    private List<Tache> list_taches;
    private ToDoList todolist_frag;
    public Context context;

    //1 element
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView text_tache_name;
        private ImageView delete_tache;
        private View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "ViewHolder");
            this.itemView = itemView;


            text_tache_name = (TextView) itemView.findViewById(R.id.nom_tache);

            text_tache_name.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onclick text_tache_name");
                    //Utilisation de l'instance du fragment récupéré dans le constructeur de Adapter_tache
                    // pour appeler la méthode Detail_Tache du fragment ToDoList
                    // sur un champ de texte du layout cards_tache
                    Adapter_tache.this.todolist_frag.Detail_Tache(v);
                }
            });

            delete_tache = (ImageView) itemView.findViewById(R.id.delete_tache);

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

    //Constructeur qui prend en paramètre l'instance du fragment todolist
    public Adapter_tache(List<Tache> list_taches, Adapter_categorie adapter) {
        Log.d(TAG, "ConstructeurAdapteur");
        this.list_taches = list_taches;
        this.todolist_frag = adapter.GetTodolist_frag();
    }


    //Créer une nouvelle instance de la classe ViewHolder et la lie au bon layout
    @NonNull
    @Override
    public Adapter_tache.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
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
        final Tache tache = list_taches.get(position);
        Log.d(TAG, "_____name : "+ tache.getName());
        if ( tache.GetId() == 0){
            holder.text_tache_name.setText(tache.getName());
        }
    }

    // Retourne le nombre d'élements (ici tache) existantes
    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount");
        return list_taches.size();
    }

}
