package com.ensim.calandarplus.Models;

/**
 * Author : Lauriane GOURAUD
 */

import android.app.FragmentManager;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ensim.calandarplus.Controllers.Activities.MainActivity;
import com.ensim.calandarplus.Controllers.Fragments.ToDoList;
import com.example.calandarplus.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//Permet l'affichage les elements (ici des catégories) un à un dans une recycler view
public class Adapter_categorie extends RecyclerView.Adapter<Adapter_categorie.ViewHolder>{

    private static final String TAG = "AdapterCategorie";

    private List<CategorieDB.Categorie> list_categorie;
    private ToDoList todolist_frag;
    public Context context;


    //1 element
    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.nom_categorie)TextView text_categorie_name;
        @BindView(R.id.delete_categorie)Button delete_categorie;
        @BindView(R.id.Add_tache_cat) Button add_tache;
        @BindView(R.id.recycler_view_task) RecyclerView recyclerView_task;

        private CategorieHelper cat_helper;
        private TacheHelper tache_helper;
        private Adapter_tache m_adapter;
        private View itemView;



        public ViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "ViewHolder");
            this.itemView = itemView;

            ButterKnife.bind(this,itemView);
            Log.d(TAG, "BindVIew ok");
            configureDesign();
        }

        protected void configureDesign(){

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

            recyclerView_task.setLayoutManager(new LinearLayoutManager(Adapter_categorie.this.todolist_frag.getContext(), LinearLayoutManager.VERTICAL, false));

            tache_helper = new TacheHelper(Adapter_categorie.this.todolist_frag.getContext());
            cat_helper = new CategorieHelper(Adapter_categorie.this.todolist_frag.getContext());
        }

        protected void UpdateDesign_tache() {
            Log.d(TAG, "configureDesign_tache");
            List<TacheDB.Tache> tache_list = new ArrayList<>();
            Log.d(TAG, "taille list : "+tache_list.size());
            //Lit le contenu de la table catégorie
            SQLiteDatabase db_cat = cat_helper.getReadableDatabase();

            SQLiteDatabase db_tache = tache_helper.getReadableDatabase();

            String val = text_categorie_name.getText().toString();
            Log.d(TAG, "nom catégorie : " + val);
            Cursor cursor_cat = db_cat.query(CategorieDB.Categorie.TABLE,
                    new String[] {CategorieDB.Categorie._ID},
                    CategorieDB.Categorie.COL_CAT_NAME +" = ?" , new String[] {val}, null, null, null
            );
            Log.d(TAG, "Ok avant while cursor");
            int id_cat=-1;
            while(cursor_cat.moveToNext()){
                Log.d(TAG, "Cursor while 1 ");
                id_cat = cursor_cat.getInt(cursor_cat.getColumnIndex(CategorieDB.Categorie._ID));
            }

            cursor_cat.close();
            Log.d(TAG, "cursor_cat.close");

            if(id_cat != -1){
                String id = ""+id_cat;
                Log.d(TAG, id);

                Cursor cursor_tache = db_tache.query(TacheDB.Tache.TABLE,
                        new String[] {TacheDB.Tache.COL_TACHE_NAME, TacheDB.Tache.COL_ID_CAT},
                        TacheDB.Tache.COL_ID_CAT +" = ?" , new String[] {id}, null, null, null
                );

                Log.d(TAG, "cursor_tache avant while");

                while(cursor_tache.moveToNext()){
                    Log.d(TAG, "Cursor while");
                    TacheDB.Tache newTache = new TacheDB.Tache(cursor_tache.getString(cursor_tache.getColumnIndex(TacheDB.Tache.COL_TACHE_NAME)),
                            cursor_tache.getInt(cursor_tache.getColumnIndex(TacheDB.Tache.COL_ID_CAT)));
                    tache_list.add(newTache);
                }
                //Affiche les catégories trouvées dans la table dans la recyclerview
                m_adapter = new Adapter_tache(tache_list, Adapter_categorie.this);
                recyclerView_task.setAdapter(m_adapter);
                m_adapter.notifyDataSetChanged();
                Log.d(TAG, "m_adapteur taille : "+m_adapter.getItemCount());
                cursor_tache.close();
            }

            Log.d(TAG, "avant fermuture db");
            db_tache.close();
            db_cat.close();
        }

    }

    //Constructeur qui prend en paramètre l'instance du fragment todolist
    public Adapter_categorie(List<CategorieDB.Categorie> list_categorie, ToDoList frag) {
        Log.d(TAG, "ConstructeurAdapteur");
        this.list_categorie = list_categorie;
        this.todolist_frag = frag;
    }


    //Créer une nouvelle instance de la classe ViewHolder et la lie au bon layout
    @NonNull
    @Override
    public Adapter_categorie.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
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
        final CategorieDB.Categorie categorie = list_categorie.get(position);

        Log.d(TAG, "_____name : "+ categorie.getName());
        holder.text_categorie_name.setText(categorie.getName());
        holder.UpdateDesign_tache();
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
