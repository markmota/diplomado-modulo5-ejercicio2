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
import modulo5.ddam.markmota.tk.space.login.FBLoginActivity;
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
import android.widget.TextView;
import android.widget.Toast;


import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONException;
import org.json.JSONObject;

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

    private  TextView username;

    private  SimpleDraweeView userPhoto;


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
                        //Snackbar.make(findViewById(android.R.id.content),"Favorite Photo",Snackbar.LENGTH_SHORT).show();
                        chargeFavoritesFragment();
                        return  true;
                    case R.id.item_listing_mars:
                        // charge the default  fragment mars rover
                        chargeRoverFragment();
                        return  true;
                    case R.id.item_listing_today:
                        chargeTodayFragment();
                        return  true;
                    case R.id.item_logout:
                        finish();
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

        getUserInfo();

        // charge the default  fragment mars rover
        chargeRoverFragment();









    }
    private void getUserInfo(){
        // getting information of username and photo
        GraphRequest request=  GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),new GraphRequest.GraphJSONObjectCallback(){
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try{
                    username=(TextView) findViewById(R.id.activity_menu_head_nav_title);
                    userPhoto= (SimpleDraweeView) findViewById(R.id.activity_menu_head_nav_image);
                    //Snackbar.make(findViewById(android.R.id.content),object.getString("name"),Snackbar.LENGTH_SHORT).show();
                   username.setText(object.getString("name"));
                   userPhoto.setImageURI("http://graph.facebook.com/" + object.getString("id") + "/picture?type=large");

                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
        request.executeAsync();
    }
    // Implements rover fragment functionality
    private void chargeRoverFragment() {
        //Toolbar title
        android.support.v4.app.Fragment marsRover=new MarsRoverFragment();
        Bundle args = new Bundle();
        args.putBoolean("IsFavorites", false);
        marsRover.setArguments(args);
        toolbar.setTitle("Mars Rover Photos List");
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_fragmentHolder,marsRover).commit();
    }
    // Implements favorites fragment functionality
    private void chargeFavoritesFragment() {
        //Toolbar title
        android.support.v4.app.Fragment marsRover=new MarsRoverFragment();
        Bundle args = new Bundle();
        args.putBoolean("IsFavorites", true);
        marsRover.setArguments(args);
        toolbar.setTitle("Favorites Photos List");
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_fragmentHolder,marsRover).commit();
    }
    // Implements today photo fragment functionality
    private void chargeTodayFragment() {
        //Toolbar title
        toolbar.setTitle("Today Photo");
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_fragmentHolder,new TodayApodFragment()).commit();

    }

}
