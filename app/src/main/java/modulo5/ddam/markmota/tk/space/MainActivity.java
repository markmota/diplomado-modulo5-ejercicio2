package modulo5.ddam.markmota.tk.space;

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


import java.util.List;

import modulo5.ddam.markmota.tk.space.data.Data;

import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.mars_rover_listing)
    RecyclerView marsRoverListingRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);
        ButterKnife.bind(this);

        //LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        GridLayoutManager gridLayoutManager= new GridLayoutManager(this,2);


        marsRoverListingRecycler.setLayoutManager(gridLayoutManager);

        MarsPhotoService marsService= Data.getRetrofitInstance().create(MarsPhotoService.class);
        Call<MarsPhotos> callMarsService =marsService.getMarsImages(BuildConfig.NasaApiKey,"1000");

        callMarsService.enqueue(new Callback<MarsPhotos>(){
            @Override
            public  void onResponse(Call<MarsPhotos> call, Response<MarsPhotos> response){
                marsRoverListingRecycler.setAdapter(new NasaApodAdapter(response.body()));

            }
            @Override
            public void onFailure(Call<MarsPhotos> call,Throwable t){

            }
        });




    }

}
