package modulo5.ddam.markmota.tk.space;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import modulo5.ddam.markmota.tk.space.data.ApodService;
import modulo5.ddam.markmota.tk.space.data.Data;
import modulo5.ddam.markmota.tk.space.model.Apod;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by markmota on 8/12/16.
 */
public class TodayApodFragment extends Fragment {
    // Here we replace the code setting the variables binding to the views using butter knife
    @BindView(R.id.activity_main_image)
    SimpleDraweeView appImg;
    @BindView(R.id.activity_main_title)
    TextView titleImg;
    @BindView(R.id.activity_main_desc)
    TextView descImg;
    @BindView(R.id.activity_main_date)
    TextView dateImg;
    @BindView(R.id.activity_main_copy)
    TextView copyImg;
    String imageToUse;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mainView= inflater.inflate(R.layout.activity_main, container, false);
        ButterKnife.bind(this,mainView);
        return mainView;

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ApodService apodService= Data.getRetrofitInstance().create(ApodService.class);
        Call<Apod> callApodService =apodService.getTodayPod(BuildConfig.NasaApiKey);

        callApodService.enqueue(new Callback<Apod>(){
            @Override
            public  void onResponse(Call<Apod> call, Response<Apod> response){
                Log.d("APODTitle",response.body().getTitle());
                Log.d("APODUrl",response.body().getUrl());
                imageToUse=response.body().getUrl();
                String titleImage=response.body().getTitle();
                String descImage=response.body().getExplanation();
                String dateImage=response.body().getDate();
                String copyImage=response.body().getCopyright();
                titleImg.setText(titleImage);
                descImg.setText(descImage);
                dateImg.setText(dateImage);
                if(TextUtils.isEmpty(copyImage))
                    copyImg.setText("by Anonymous");
                else
                    copyImg.setText("by "+copyImage);

                appImg.setImageURI(imageToUse);

            }
            @Override
            public void onFailure(Call<Apod> call,Throwable t){

            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.share_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {

            case R.id.share_today_apod:
                Snackbar.make(getView(),"Share options clicked",Snackbar.LENGTH_INDEFINITE).show();
                shareText("Diplomado UNAM "+imageToUse);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }
    private void shareText(String text){
        Intent shareIntent=new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,text);
        startActivity(Intent.createChooser(shareIntent,"Compartir con amigos"));
    }





}
