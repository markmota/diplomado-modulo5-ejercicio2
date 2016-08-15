package modulo5.ddam.markmota.tk.space;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import modulo5.ddam.markmota.tk.space.data.Data;
import modulo5.ddam.markmota.tk.space.data.MarsPhotoService;
import modulo5.ddam.markmota.tk.space.model.MarsPhotos;
import modulo5.ddam.markmota.tk.space.model.Photo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MarsRoverFragment extends Fragment {
    @BindView(R.id.mars_rover_listing)
    RecyclerView marsRoverListingRecycler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mainView= inflater.inflate(R.layout.activity_listing, container, false);
        ButterKnife.bind(this,mainView);
        return mainView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        GridLayoutManager gridLayoutManager= new GridLayoutManager(getContext(),2);
        marsRoverListingRecycler.setLayoutManager(gridLayoutManager);

        final NasaApodAdapter nasaApodAdapter=new NasaApodAdapter();
        nasaApodAdapter.setOnItemClickListener(new NasaApodAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(Photo photo){
                //Log.d("TagMarduk",photo.getImgSrc());
                //Toast.makeText(getApplicationContext(),photo.getImgSrc(),Toast.LENGTH_SHORT).show();
                // Start to configure the start of the next  activity
                Intent intent= new Intent(getContext(),DetailActivity.class);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.settings_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {

            case R.id.settings_menu_item:
                Snackbar.make(getView(),"Settings updated",Snackbar.LENGTH_INDEFINITE).show();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
