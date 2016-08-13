package modulo5.ddam.markmota.tk.space;

import android.app.Fragment;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import modulo5.ddam.markmota.tk.space.data.MarsPhotoService;
import modulo5.ddam.markmota.tk.space.model.MarsPhotos;
import modulo5.ddam.markmota.tk.space.model.Photo;
import retrofit2.Call;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import java.util.List;

import modulo5.ddam.markmota.tk.space.data.Data;

import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
   @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.listing_navigation_view)
    NavigationView navigationView;
    @BindView(R.id.listing_navigation_drawer)
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);

        // Setting support to Action Bar
        setSupportActionBar(toolbar);

        // Implements the actions of the buttons in the list
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                drawerLayout.closeDrawers();


                switch (item.getItemId()){
                    case R.id.item_listing_favorite:
                        Snackbar.make(findViewById(android.R.id.content),"Favorite Photo",Snackbar.LENGTH_INDEFINITE).show();
                        return  true;
                    case R.id.item_listing_mars:
                        // charge the default  fragment mars rover
                        chargeRoverFragment();
                        return  true;
                    case R.id.item_listing_today:
                        chargeTodayFragment();
                        return  true;


                }
                return false;
            }
        });
        // Implements the drawer toggle and the actions when open and close
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };


        // To orientate the icon of the menu correctly
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // charge the default  fragment mars rover
        chargeRoverFragment();



    }
    // Implements rover fragment functionality
    private void chargeRoverFragment() {
        //Toolbar title
        toolbar.setTitle("Mars Rover Photos List");
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_fragmentHolder,new MarsRoverFragment()).commit();
    }
    // Implements today photo fragment functionality
    private void chargeTodayFragment() {
        //Toolbar title
        toolbar.setTitle("Today Photo");
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_fragmentHolder,new TodayApodFragment()).commit();
    }

}
