package com.ensim.calandarplus.Models;

/**
 * Author : Lauriane GOURAUD
 */

import android.app.FragmentManager;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.ensim.calandarplus.Controllers.Activities.MainActivity;
import com.ensim.calandarplus.Controllers.Fragments.ToDoList;
import com.example.calandarplus.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//Permet l'affichage les elements (ici des catégories) un à un dans une recycler view
public class Adapter_categorie extends RecyclerView.Adapter<Adapter_categorie.ViewHolder>{

    private static final String TAG = "AdapterCategorie";

    private List<Categorie> list_categorie;
    private ToDoList todolist_frag;
    public Context context;

    //1 element
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView text_categorie_name;
        private Button delete_categorie;
        private View itemView;
        private Button add_tache;

        public ViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "ViewHolder");
            this.itemView = itemView;


            text_categorie_name = (TextView) itemView.findViewById(R.id.nom_categorie);

            delete_categorie = (Button) itemView.findViewById(R.id.delete_categorie);
            //Gestion du listener du bouton de suppression d'une catégorie
                // (présent dans le layout cards_categorie)
            delete_categorie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onclick delete_categorie");
                    //Utilisation de l'instance du fragment récupéré dans le constructeur de Adapter_categorie
                        // pour appeler la méthode DeleteCategorie du fragment ToDoList
                        // sur un bouton du layout cards_categorie
                    Adapter_categorie.this.todolist_frag.DeleteCategorie(v);
                }
            });

            add_tache = (Button) itemView.findViewById(R.id.Add_tache_cat);

            add_tache.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onclick add_tache");
                    //Utilisation de l'instance du fragment récupéré dans le constructeur de Adapter_categorie
                    // pour appeler la méthode DeleteCategorie du fragment ToDoList
                    // sur un bouton du layout cards_categorie
                    Adapter_categorie.this.todolist_frag.AddTache(v, (String) text_categorie_name.getText());
                }
            });
        }
    }

    //Constructeur qui prend en paramètre l'instance du fragment todolist
    public Adapter_categorie(List<Categorie> list_categorie, ToDoList frag) {
        Log.d(TAG, "ConstructeurAdapteur");
        this.list_categorie = list_categorie;
        this.todolist_frag = frag;
    }


    //Créer une nouvelle instance de la classe ViewHolder et la lie au bon layout
    @NonNull
    @Override
    public Adapter_categorie.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        Log.d(TAG, "onCreateViewHolder");

        context = parent.getContext();
        //Lie chaque instance de ViewHolder à un layout cards_categorie
        View view = LayoutInflater.from(context).inflate(R.layout.cards_categorie, parent, false);
        return new Adapter_categorie.ViewHolder(view);
    }

    //Gère le contenu des ViewHolder
    @Override
    public void onBindViewHolder(@NonNull Adapter_categorie.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder");
        final Categorie categorie = list_categorie.get(position);

        Log.d(TAG, "_____name : "+ categorie.getName());
        holder.text_categorie_name.setText(categorie.getName());
    }

    // Retourne le nombre d'élements (ici catégorie) existantes
    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount");
        return list_categorie.size();
    }

    public ToDoList GetTodolist_frag(){
        return todolist_frag;
    }
}
