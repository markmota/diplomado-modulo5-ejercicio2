package modulo5.ddam.markmota.tk.space.data;

/**
 * Created by markmota on 7/30/16.
 */


import modulo5.ddam.markmota.tk.space.BuildConfig;
import modulo5.ddam.markmota.tk.space.model.Apod;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApodService {
    @GET("planetary/apod")
    Call<Apod>  getTodayPod(@Query("api_key") String apiKey, @Query("date") String date);
}
