package modulo5.ddam.markmota.tk.space;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
    private String imageToUse;

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
        imageToUse=getIntent().getExtras().getString("image");
        appImg.setImageURI(imageToUse);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {

            case R.id.settings_menu_item:
                Snackbar.make(findViewById(android.R.id.content),"Add to favorites",Snackbar.LENGTH_INDEFINITE).show();
                return true;
            case R.id.share_today_apod:
                //Snackbar.make(findViewById(android.R.id.content),"Share options clicked",Snackbar.LENGTH_INDEFINITE).show();
                shareText("Diplomado UNAM "+imageToUse);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
    private void shareText(String text){
        Intent shareIntent=new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,text);
        startActivity(Intent.createChooser(shareIntent,"Compartir con amigos"));
    }
}
