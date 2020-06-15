package com.ensim.calandarplus.Controllers.Fragments;

/**
 * Author : Lauriane GOURAUD
 */

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ensim.calandarplus.Controllers.Activities.AjouterTache;
import com.ensim.calandarplus.Controllers.Activities.MainActivity;
import com.ensim.calandarplus.Models.Adapter_categorie;
import com.ensim.calandarplus.Models.Categorie;
import com.ensim.calandarplus.Models.DataBase;
import com.ensim.calandarplus.Models.CategorieHelper;
import com.example.calandarplus.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ToDoList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ToDoList extends BaseFragment {
    //Definition des éléments de la vu (fragment_to_do_lists.xml)

    public static final String TAG = "FragToDoList";


    @BindView(R.id.recycler_view_categorie) RecyclerView recyclerView_categorie;
    @BindView(R.id.button_add_categorie) Button bouton_add_categorie;
    //@BindView(R.id.recycler_view_task) RecyclerView recyclerView_task;

    private CategorieHelper m_helper;
    private Adapter_categorie m_adapter;


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
        Log.d(TAG, "configureDesign");
        recyclerView_categorie.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        //CategorieHelper gère la table Categorie dans la base de donnée
        m_helper = new CategorieHelper(getContext());

        //Permet de gérer le clique du bouton
        bouton_add_categorie.setOnClickListener(new View.OnClickListener() {
            //Pour plus de lisibilité, la fonction est définit plus bas
                @Override
                public void onClick(View v) {
                    Add_new_categorie(v);
                }
            });
        //Met à jour l'affichage du fragement
        updateDesign();
    }

    //Mettre à jour interface graphique du fragment
    @Override
    protected void updateDesign() {
        Log.d(TAG, "updateDesign");
        List<Categorie> categorie_list = new ArrayList<>();
        Log.d(TAG, "taille list : "+categorie_list.size());
        //Lit le contenu de la table catégorie
        SQLiteDatabase db = m_helper.getReadableDatabase();
        Cursor cursor = db.query(Categorie.TABLE,
                new String[] {Categorie._ID, Categorie.COL_CAT_NAME},
                null, null, null, null, null
                );

        while(cursor.moveToNext()){
            Log.d(TAG, "Cursor while");
            int index = cursor.getColumnIndex(Categorie.COL_CAT_NAME);
            Categorie newC_cat = new Categorie(cursor.getString(index));
            categorie_list.add(newC_cat);
        }
        //Affiche les catégories trouvées dans la table dans la recyclerview
        m_adapter = new Adapter_categorie(categorie_list, ToDoList.this);
        recyclerView_categorie.setAdapter(m_adapter);
        m_adapter.notifyDataSetChanged();
        Log.d(TAG, "m_adapteur taille : "+m_adapter.getItemCount());
        cursor.close();
        db.close();
    }

    //Methode appelé au click du bouton bouton_add_categorie (Listener du bouton dans configureDesign)
    public void Add_new_categorie(View view){
        Log.d(TAG, "Add_new_categorie");
        final EditText categorieEditText = new EditText(view.getContext());
        //Choix de renseigner le nom de la nouvelle catégorie dans une AlertDialog pour découvrir son fonctionnement
        AlertDialog dialog = new AlertDialog.Builder(view.getContext())
                .setTitle(R.string.add_categorie)
                .setMessage(R.string.add_new_categorie)
                .setView(categorieEditText)
                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String new_categorie = String.valueOf(categorieEditText.getText());
                        Log.d(TAG, "Nouvelle catégorie : "+new_categorie);
                        SQLiteDatabase db = m_helper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put(Categorie.COL_CAT_NAME, new_categorie);
                        db.insertWithOnConflict(Categorie.TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                        db.close();
                        updateDesign();
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .create();
        dialog.show();
        //Modification de la couleur du text des boutons de l'ALertDialog
        Button buttonPositive = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        buttonPositive.setTextColor(ContextCompat.getColor(getContext(), R.color.Text));
        Button buttonNegative = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        buttonNegative.setTextColor(ContextCompat.getColor(getContext(), R.color.Text));

        updateDesign();
    }
    //Methode appelé au click du bouton delete_categorie (Listener dans la classe  ViewHolder du .java Adapter_categorie)
    public void DeleteCategorie(View view){
        Log.d(TAG, "Delete_categorie");
        View parent = (View) view.getParent();
        //Récupere le nom de la catégorie qu'il faut supprimer
        TextView textView = (TextView) parent.findViewById(R.id.nom_categorie);
        String categorie = String.valueOf(textView.getText());
        //Ecrit dans la base de donnée
        SQLiteDatabase db = m_helper.getWritableDatabase();
        //Suppression de la catégorie
        db.delete(Categorie.TABLE, Categorie.COL_CAT_NAME + " = ? ", new String[] {categorie});
        db.close();
        updateDesign();
    }

    public void DeleteTache(View v){
        Log.d(TAG, "DeleteTache");
    }

    public void Detail_Tache(View v){
        Log.d(TAG, "Detail_Tache");
    }

    public void AddTache(View v, String text_categorie_name){
        Log.d(TAG, "AddTache");
        Intent intent= new Intent(getContext(), AjouterTache.class);
        startActivity(intent);
    }

}
