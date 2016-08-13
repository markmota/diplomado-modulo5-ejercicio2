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
    // Oveload the function to extend the end point parameters, the fist receive a specific date and the second show the today photo
    @GET("planetary/apod")
    Call<Apod>  getTodayPod(@Query("api_key") String apiKey, @Query("date") String date);

    @GET("planetary/apod")
    Call<Apod>  getTodayPod(@Query("api_key") String apiKey);

}
