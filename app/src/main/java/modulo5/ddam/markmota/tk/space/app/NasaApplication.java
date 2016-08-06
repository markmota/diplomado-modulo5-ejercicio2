package modulo5.ddam.markmota.tk.space.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by markmota on 8/6/16.
 */
public class NasaApplication extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        Fresco.initialize(this);
    }
}
