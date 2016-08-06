package modulo5.ddam.markmota.tk.space;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by markmota on 8/5/16.
 */
public class NasaApodViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.nasa_apod_items_image)
    ImageView appImg;
    @BindView(R.id.nasa_apod_items_title)
    TextView titleImg;

    @BindView(R.id.nasa_apod_items_date)
    TextView dateImg;


    public NasaApodViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
