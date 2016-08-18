package modulo5.ddam.markmota.tk.space;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.facebook.internal.BoltsMeasurementEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import modulo5.ddam.markmota.tk.space.model.Apod;
import modulo5.ddam.markmota.tk.space.model.MarsPhotos;
import modulo5.ddam.markmota.tk.space.model.ModelImg;
import modulo5.ddam.markmota.tk.space.model.Photo;
import modulo5.ddam.markmota.tk.space.sql.ImgDataSource;

/**
 * Created by markmota on 8/5/16.
 */
public class NasaApodAdapter extends RecyclerView.Adapter<NasaApodViewHolder> {
    private List<Photo> marsPhotos=new ArrayList<Photo>();
    private OnItemClickListener onItemClickListener;

    public NasaApodAdapter(){};
    public NasaApodAdapter(List<Photo> apods){
        this.marsPhotos=apods;
    }


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
        holder.appImg.setImageURI(photo.getImgSrc());
        holder.dateImg.setText(photo.getEarthDate());
        holder.titleImg.setText(photo.getCamera().getName());
        holder.setItemClick(photo,onItemClickListener);


    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setMarsPhotos(MarsPhotos marsPhotos, Context context, Boolean justFavorites) {

        List<Photo> marsPhotosHolder;
        marsPhotosHolder = marsPhotos.getPhotos();
        ImgDataSource imgDataSource= new ImgDataSource(context);
        // Decide if we have to show all the photos or just the favorites
        if(justFavorites) {
            for (int i = 0; i < marsPhotosHolder.size(); i++) {

                ModelImg itemInfo = imgDataSource.getInfoItem(marsPhotosHolder.get(i).getImgSrc());

                if (itemInfo != null) {
                    Log.d("Adding to the list: ", marsPhotosHolder.get(i).getImgSrc());
                    this.marsPhotos.add(marsPhotosHolder.get(i));
                }
            }
        }
        else{
            this.marsPhotos=marsPhotosHolder;
        }
        for (int i = 0; i < this.marsPhotos.size(); i++) {

            Log.d("Photos on list: ", this.marsPhotos.get(i).getImgSrc());

        }



    }

    @Override
    public int getItemCount() {
        return marsPhotos !=null? marsPhotos.size():0;
    }

    public interface OnItemClickListener {
        void onItemClick(Photo photo);
    }
}
