package modulo5.ddam.markmota.tk.space;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
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
        Call<Apod> callApodService =apodService.getTodayPod(BuildConfig.NasaApiKey,"2016-06-27");

        callApodService.enqueue(new Callback<Apod>(){
            @Override
            public  void onResponse(Call<Apod> call, Response<Apod> response){
                Log.d("APODTitle",response.body().getTitle());
                Log.d("APODUrl",response.body().getUrl());
                String imageToUse=response.body().getUrl();
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


}
