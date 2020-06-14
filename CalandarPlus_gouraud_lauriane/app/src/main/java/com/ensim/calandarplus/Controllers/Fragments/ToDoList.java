package com.ensim.calandarplus.Controllers.Fragments;

/**
 * Author : Lauriane GOURAUD
 */

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
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

        m_helper = new CategorieHelper(getContext());
        bouton_add_categorie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Add_new_categorie(v);
                }
            });

        updateDesign();
    }

    //Mettre à jour interface graphique du fragment
    @Override
    protected void updateDesign() {
        Log.d(TAG, "updateDesign");
        List<Categorie> categorie_list = new ArrayList<>();
        Log.d(TAG, "taille list : "+categorie_list.size());
        SQLiteDatabase db = m_helper.getReadableDatabase();
        Cursor cursor = db.query(Categorie.TABLE,
                new String[] {Categorie._ID, Categorie.COL_TASK_NAME},
                null, null, null, null, null
                );

        while(cursor.moveToNext()){
            Log.d(TAG, "Cursor while");
            int index = cursor.getColumnIndex(Categorie.COL_TASK_NAME);
            Categorie newC_cat = new Categorie(cursor.getString(index));
            categorie_list.add(newC_cat);
        }

        m_adapter = new Adapter_categorie(categorie_list, ToDoList.this);
        recyclerView_categorie.setAdapter(m_adapter);
        m_adapter.notifyDataSetChanged();
        Log.d(TAG, "m_adapteur taille : "+m_adapter.getItemCount());
        cursor.close();
        db.close();
    }

    public void Add_new_categorie(View view){
        Log.d(TAG, "Add_new_categorie");
        final EditText categorieEditText = new EditText(view.getContext());
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
                        values.put(Categorie.COL_TASK_NAME, new_categorie);
                        db.insertWithOnConflict(Categorie.TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                        db.close();
                        updateDesign();
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .create();
        dialog.show();
        updateDesign();
    }

    public void DeleteCategorie(View view){
        Log.d(TAG, "Delete_categorie");
        View parent = (View) view.getParent();
        TextView textView = (TextView) parent.findViewById(R.id.nom_categorie);
        String categorie = String.valueOf(textView.getText());
        SQLiteDatabase db = m_helper.getWritableDatabase();
        db.delete(Categorie.TABLE, Categorie.COL_TASK_NAME + " = ? ", new String[] {categorie});
        db.close();
        updateDesign();
    }

}
