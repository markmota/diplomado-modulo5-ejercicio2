package modulo5.ddam.markmota.tk.space;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import modulo5.ddam.markmota.tk.space.model.Apod;

/**
 * Created by markmota on 8/5/16.
 */
public class NasaApodAdapter extends RecyclerView.Adapter<NasaApodViewHolder> {
    private List<Apod> apods;

    public  NasaApodAdapter(ArrayList<Apod> apods){this.apods=apods;}
    @Override
    public NasaApodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NasaApodViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nasa_apod_items,parent,false));
    }

    @Override
    public void onBindViewHolder(NasaApodViewHolder holder, int position) {
        Apod apod=apods.get(position);
        if(apod.getMediaType().equals("image")){
            Picasso.with(holder.appImg.getContext())
                    .load(apod.getUrl())
                    .resize(500, 300)
                    .centerCrop()
                    .placeholder(android.R.drawable.ic_input_get)
                    .error(android.R.drawable.ic_dialog_alert)
                    .into(holder.appImg);
        }
        holder.copyImg.setText(apod.getCopyright());
        holder.dateImg.setText(apod.getDate());
        holder.descImg.setText(apod.getExplanation());
        holder.titleImg.setText(apod.getTitle());

    }

    @Override
    public int getItemCount() {
        return apods !=null? apods.size():0;
    }
}
