package com.ensim.calandarplus.Controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.ensim.calandarplus.Controllers.Fragments.AgendaJour;
import com.ensim.calandarplus.Controllers.Fragments.AgendaSemaine;
import com.ensim.calandarplus.Controllers.Fragments.BaseFragment;
import com.ensim.calandarplus.Controllers.Fragments.GererAgendas;
import com.ensim.calandarplus.Controllers.Fragments.Parametres;
import com.ensim.calandarplus.Controllers.Fragments.ToDoList;
import com.example.calandarplus.R;
import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener{

    private static String TAG = "Page Accueil";

    //Les fragments affichés à l'aide de l'activité main Activity
    private Fragment frag_agenda_semaine;
    private Fragment frag_todolist;
    private Fragment frag_gerer_agendas;
    private Fragment frag_agenda_jour;
    private Fragment frag_parametres;

    //Definition des éléments de la vu (activity_main.xml)
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout_main) DrawerLayout drawerLayout;
    @BindView(R.id.nav_view_main) NavigationView navigationView;
    @BindView(R.id.Button_add) ImageView imageadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Récupération des éléments de la vu (activity_main.xml)
        ButterKnife.bind(this);

        this.configurerToolBar();
        this.configurerDrawerLayout();
        this.configurerNavigationView();

        //Affiche le premier fragment à afficher dans la vu : celui qui sera affiché
            //lors du démarrage de l'application
        this.showFirstFragment();

        Log.d(TAG, "Page configurée");
    }

    //Gère l'ouverture et la fermeture du menu de l'application
    public void onBackPressed() {
        Log.d(TAG, "Ouverture ou fermeture du menu");
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //Configure la toolbar : titre et bouton menu
    private void configurerToolBar(){
        setSupportActionBar(toolbar);
        setTitle(TAG);
    }

    //Configure le DrawerLayout : gère l'ouverture et la fermeture du menu avec un listener
    private void configurerDrawerLayout(){
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    //Configure le menu : Listener sur les items du menu
    private void configurerNavigationView(){
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
    }

    //Dans le menu : Action (Changement de fragment) se fait en fonction de l'item du menu cliqué
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_agenda :
                Log.d(TAG, "main menu -> agenda");
                this.showFragAgSem();
                break;
            case R.id.menu_to_do_list:
                Log.d(TAG, "main menu -> todolist");
                this.showFragToDoList();
                break;
            case R.id.menu_gerer_agenda:
                Log.d(TAG, "main menu -> gerer agenda");
                this.showFragGereAg();
                break;
            case R.id.menu_synchro:
                Log.d(TAG, "main menu -> synchro");
                //A completer une fois API implémentée
                break;
            case R.id.menu_parametres:
                Log.d(TAG, "main menu -> paramètres");
                this.showFragParam();
                break;
            default:
                super.onOptionsItemSelected(item);
                break;
        }
        this.drawerLayout.closeDrawer(GravityCompat.START); //Fermeture de la fenetre du menu
        return true;
    }

    //Lance le changement de fragment affiché par l'application
    private void startTransactionFragment(Fragment fragment){
        Log.d(TAG, "Changement Fragment");
        if(!fragment.isVisible()){
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_agenda_sem,fragment).commit();
        }
    }

    //Pour passer sur le fragment fragment_agenda_semaine
    private void showFragAgSem(){
        if (this.frag_agenda_semaine == null) this.frag_agenda_semaine = AgendaSemaine.newInstance();
        this.startTransactionFragment(this.frag_agenda_semaine);
        TAG="AgendaSemaine";
        setTitle(TAG);
        //Rends invisible le bouton + de la toolbar
        imageadd.setVisibility(View.INVISIBLE);
        Log.d(TAG, "New Fragment");
    }

    //Pour passer sur le fragment fragment_agenda_jour
    private void showFragAgJour(){
        if (this.frag_agenda_jour == null) this.frag_agenda_jour = AgendaJour.newInstance();
        this.startTransactionFragment(this.frag_agenda_jour);
        TAG="AgendaJour";
        setTitle(TAG);
        //Rends invisible le bouton + de la toolbar
        imageadd.setVisibility(View.INVISIBLE);
        Log.d(TAG, "New Fragment");
    }

    //Pour passer sur le fragment fragment_gerer_agendas
    private void showFragGereAg(){
        if (this.frag_gerer_agendas == null) this.frag_gerer_agendas = GererAgendas.newInstance();
        this.startTransactionFragment(this.frag_gerer_agendas);
        TAG="GererAgendas";
        setTitle(TAG);
        //Rends visible le bouton + de la toolbar
        imageadd.setVisibility(View.VISIBLE);
        Log.d(TAG, "New Fragment");
    }

    //Pour passer sur le fragment fragment_to_do_list
    private void showFragToDoList(){
        if (this.frag_todolist== null) this.frag_todolist = ToDoList.newInstance();
        this.startTransactionFragment(this.frag_todolist);
        TAG="ToDoList";
        setTitle(TAG);
        //Rends visible le bouton + de la toolbar
        imageadd.setVisibility(View.VISIBLE);
        Log.d(TAG, "New Fragment");
    }

    //Pour passer sur le fragment fragment_to_do_list
    private void showFragParam(){
        if (this.frag_parametres== null) this.frag_parametres = Parametres.newInstance();
        this.startTransactionFragment(this.frag_parametres);
        TAG="Parametres";
        setTitle(TAG);
        //Rends invisible le bouton + de la toolbar
        imageadd.setVisibility(View.INVISIBLE);
        Log.d(TAG, "New Fragment");
    }

    //Affichage du premier fragement : lors sdu lancement de l'application --> agenda de la semaine
    private void showFirstFragment(){
        Log.d(TAG, "Affichage 1er Fragment");
        Fragment visibleFragment = getSupportFragmentManager().findFragmentById(R.id.frag_agenda_sem);
        if (visibleFragment == null){
            this.showFragAgSem();
            this.navigationView.getMenu().getItem(0).setChecked(true);
        }
    }

    //Au clique du bouton jour sur la page Agenda semaine
    public void OpenAgendaJour(View view) {
        Log.d(TAG, "OpenAgendaJour");
        this.showFragAgJour();
    }

    //Au clique du bouton jour sur la page Agenda Jour
    public void OpenAgendaSem(View view) {
        Log.d(TAG, "OpenAgendaSemaine");
        this.showFragAgSem();
    }

    //Gestion des elements cliquables dans les fragments AgendaJour, AgendaSemaine, GérerAgendas, Paramètres et ToDoList
    @Override
    public void onClick(View v) {
        Log.d(TAG, "OnClick");
        switch (v.getId()) {
            //Si bouton pour ajouter un nouvel evenement
            case R.id.Button_jour_add_event:
            case R.id.Button_sem_add_event:
                Intent intent= new Intent(MainActivity.this, AjouterEvent.class);
                startActivity(intent);
                break;

            //Si bouton ajouté de la toolbar
            case R.id.Button_add :
                Intent intent_add = null;
                //Géré uniquement sur les pages sur lesquelles il est affiché
                switch(TAG){
                    //Création de l'indent ...
                    case "ToDoList" :
                        intent_add = new Intent(MainActivity.this, AjouterTache.class);
                        break;
                    case "GererAgendas" :
                        intent_add = new Intent(MainActivity.this, AjouterAgenda.class);
                        break;
                }
                if(intent_add!=null){
                    // ... pour changer de page
                    startActivity(intent_add);
                }else{
                    Log.d(TAG, "Probleme changement de page bouton add");
                }
                break;
        }
    }

}
