package modulo5.ddam.markmota.tk.space;

import android.content.Intent;
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
import android.util.Log;
import android.widget.Toast;


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




    }

}
