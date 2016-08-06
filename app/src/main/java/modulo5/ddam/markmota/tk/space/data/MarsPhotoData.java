package modulo5.ddam.markmota.tk.space.data;
import modulo5.ddam.markmota.tk.space.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by markmota on 8/5/16.
 */
public class MarsPhotoData {
    public static Retrofit getRetrofitInstance(){

        return new Retrofit.Builder().baseUrl(BuildConfig.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
