package modulo5.ddam.markmota.tk.space;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import modulo5.ddam.markmota.tk.space.model.Apod;
import modulo5.ddam.markmota.tk.space.model.MarsPhotos;
import modulo5.ddam.markmota.tk.space.model.Photo;

/**
 * Created by markmota on 8/5/16.
 */
public class NasaApodAdapter extends RecyclerView.Adapter<NasaApodViewHolder> {
    private List<Photo> marsPhotos;

    public  NasaApodAdapter(MarsPhotos marsPhotos){
        this.marsPhotos=marsPhotos.getPhotos();
    }
    @Override
    public NasaApodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NasaApodViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nasa_apod_items,parent,false));
    }

    @Override
    public void onBindViewHolder(NasaApodViewHolder holder, int position) {
        Photo photo = marsPhotos.get(position);

            Picasso.with(holder.appImg.getContext())
                    .load(photo.getImgSrc())
                    .resize(500, 300)
                    .centerCrop()
                    .placeholder(android.R.drawable.ic_input_get)
                    .error(android.R.drawable.ic_dialog_alert)
                    .into(holder.appImg);
        holder.dateImg.setText(photo.getEarthDate());
        holder.titleImg.setText(photo.getCamera().getName());



    }

    @Override
    public int getItemCount() {
        return marsPhotos !=null? marsPhotos.size():0;
    }
}
