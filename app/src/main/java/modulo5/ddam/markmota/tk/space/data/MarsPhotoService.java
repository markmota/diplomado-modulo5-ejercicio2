package modulo5.ddam.markmota.tk.space.data;
import modulo5.ddam.markmota.tk.space.model.MarsPhotos;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
/**
 * Created by markmota on 8/5/16.
 */
public interface MarsPhotoService {
    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    Call<MarsPhotos> getMarsImages(@Query("api_key") String apiKey, @Query("sol") String sol);

}
