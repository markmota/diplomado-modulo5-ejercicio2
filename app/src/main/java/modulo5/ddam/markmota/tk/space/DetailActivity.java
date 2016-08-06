package modulo5.ddam.markmota.tk.space;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.activity_detail_image)
    SimpleDraweeView appImg;
    @BindView(R.id.activity_detail_title)
    TextView titleImg;
    @BindView(R.id.activity_detail_date)
    TextView dateImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        // Getting the info sended from the precious activity
        titleImg.setText(getIntent().getExtras().getString("title"));
        dateImg.setText(getIntent().getExtras().getString("date"));
        appImg.setImageURI(getIntent().getExtras().getString("image"));

    }
}
