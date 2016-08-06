package modulo5.ddam.markmota.tk.space;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        //Settings toolbar

        // Setting support to Action Bar
        toolbar.setTitle("Photo Detail");
        // Setting back icon
        toolbar.setNavigationIcon(R.drawable.ic_action_arrow_left);

        setSupportActionBar(toolbar);
        // back icon click listener
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Getting the info sended from the precious activity
        titleImg.setText(getIntent().getExtras().getString("title"));
        dateImg.setText(getIntent().getExtras().getString("date"));
        appImg.setImageURI(getIntent().getExtras().getString("image"));

    }
}
