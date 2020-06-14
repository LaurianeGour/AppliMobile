package com.ensim.calandarplus.Models;

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

public class Adapter_categorie extends RecyclerView.Adapter<Adapter_categorie.ViewHolder>{

    private static final String TAG = "AdapterCategorie";

    private List<Categorie> list_categorie;

    private ToDoList todolist_frag;

    public Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView text_categorie_name;
        private Button delete_categorie;
        private View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "ViewHolder");
            this.itemView = itemView;


            text_categorie_name = (TextView) itemView.findViewById(R.id.nom_categorie);
            delete_categorie = (Button) itemView.findViewById(R.id.delete_categorie);

            delete_categorie.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Adapter_categorie.this.todolist_frag.DeleteCategorie(v);
                }
            });
        }
    }

    public Adapter_categorie(List<Categorie> list_categorie, ToDoList frag) {
        Log.d(TAG, "ConstructeurAdapteur");
        this.list_categorie = list_categorie;
        this.todolist_frag = frag;
    }


    @NonNull
    @Override
    public Adapter_categorie.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        Log.d(TAG, "onCreateViewHolder");

        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.cards_categorie, parent, false);
        return new Adapter_categorie.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_categorie.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder");
        final Categorie categorie = list_categorie.get(position);

        Log.d(TAG, "_____name : "+ categorie.getName());
        holder.text_categorie_name.setText(categorie.getName());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount");
        return list_categorie.size();
    }
}
