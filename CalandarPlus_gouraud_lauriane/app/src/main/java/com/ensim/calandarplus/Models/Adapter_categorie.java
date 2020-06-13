package com.ensim.calandarplus.Models;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calandarplus.R;

import java.util.List;

import butterknife.BindView;

public class Adapter_categorie extends RecyclerView.Adapter<Adapter_categorie.ViewHolder>{

    private static final String TAG = "AdapterCategorie";

    private List<Categorie> list_categorie;

    public Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.nom_categorie) TextView text_categorie_name;
        private View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public Adapter_categorie(List<Categorie> list_categorie) {
        this.list_categorie = list_categorie;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public Adapter_categorie.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        // create a new view
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.cards_categorie, parent, false);
        Adapter_categorie.ViewHolder vh = new Adapter_categorie.ViewHolder(view);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(Adapter_categorie.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder");
        final Categorie categorie = list_categorie.get(position);

        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.text_categorie_name.setText(categorie.getName());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return list_categorie.size();
    }
}
