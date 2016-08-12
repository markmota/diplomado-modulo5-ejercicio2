package modulo5.ddam.markmota.tk.space;

import android.content.Intent;
import android.support.design.widget.NavigationView;
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
    //@BindView(R.id.mars_rover_listing)
    //RecyclerView marsRoverListingRecycler;
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

        //Settings toolbar
        // Setting support to Action Bar
        setSupportActionBar(toolbar);
        toolbar.setTitle("Nasa Photos");
        // Implements the actions of the buttons in the list
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
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

        /*
        //LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        GridLayoutManager gridLayoutManager= new GridLayoutManager(this,2);
        marsRoverListingRecycler.setLayoutManager(gridLayoutManager);

        final NasaApodAdapter nasaApodAdapter=new NasaApodAdapter();
        nasaApodAdapter.setOnItemClickListener(new NasaApodAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(Photo photo){
                //Log.d("TagMarduk",photo.getImgSrc());
                //Toast.makeText(getApplicationContext(),photo.getImgSrc(),Toast.LENGTH_SHORT).show();
                // Start to configure the start of the next  activity
                Intent intent= new Intent(getApplicationContext(),DetailActivity.class);
                // Variables to send to the next activity
                intent.putExtra("title",photo.getCamera().getName());
                intent.putExtra("image",photo.getImgSrc());
                intent.putExtra("date",photo.getEarthDate());

                startActivity(intent);


            }

        });
        MarsPhotoService marsService= Data.getRetrofitInstance().create(MarsPhotoService.class);
        Call<MarsPhotos> callMarsService =marsService.getMarsImages(BuildConfig.NasaApiKey,"1000");

        callMarsService.enqueue(new Callback<MarsPhotos>(){
            @Override
            public  void onResponse(Call<MarsPhotos> call, Response<MarsPhotos> response){
                nasaApodAdapter.setMarsPhotos(response.body());
                marsRoverListingRecycler.setAdapter(nasaApodAdapter);

            }
            @Override
            public void onFailure(Call<MarsPhotos> call,Throwable t){

            }
        });
        */



    }

}
