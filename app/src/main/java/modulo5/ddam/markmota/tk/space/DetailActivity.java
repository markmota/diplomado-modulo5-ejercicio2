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

import java.sql.Timestamp;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import modulo5.ddam.markmota.tk.space.model.ModelImg;
import modulo5.ddam.markmota.tk.space.sql.ImgDataSource;

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.activity_detail_image)
    SimpleDraweeView appImg;
    @BindView(R.id.activity_detail_title)
    TextView titleImg;
    @BindView(R.id.activity_detail_date)
    TextView dateImg;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    MenuItem favoritesButton;
    MenuItem eraseButton;
    private String imageToUse;
    private String date;
    private String title;

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
        date=getIntent().getExtras().getString("date");
        title=getIntent().getExtras().getString("title");
        titleImg.setText(title);
        dateImg.setText(date);
        imageToUse=getIntent().getExtras().getString("image");
        appImg.setImageURI(imageToUse);





    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        // Binding the elements of the menu
        favoritesButton=menu.findItem(R.id.settings_menu_item);
        eraseButton=menu.findItem(R.id.settings_menu_erase);

        // Checking if the image is in favorites to show the correct menu botton
        ImgDataSource imgDataSource= new ImgDataSource(getApplicationContext());
        ModelImg itemInfo=imgDataSource.getInfoItem(imageToUse);

        if(itemInfo!=null){
            favoritesButton.setVisible(false);
            eraseButton.setVisible(true);
        }
        else {
            favoritesButton.setVisible(true);
            eraseButton.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {

            case R.id.settings_menu_item:
                add_item();
                //Snackbar.make(findViewById(android.R.id.content),"Add to favorites",Snackbar.LENGTH_INDEFINITE).show();
                return true;
            case R.id.share_today_apod:
                //Snackbar.make(findViewById(android.R.id.content),"Share options clicked",Snackbar.LENGTH_INDEFINITE).show();
                shareText("Diplomado UNAM "+imageToUse);
                return true;
            case R.id.settings_menu_erase:
                //Snackbar.make(findViewById(android.R.id.content),"Remove from favorites",Snackbar.LENGTH_INDEFINITE).show();
                remove_item();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
    // Adding item to favorites database
    private void add_item() {
        boolean saved=false; //status of the operation in the database
        Date today=new Date();
        Timestamp timestamp=new Timestamp(today.getTime());
        ModelImg modelImg=new ModelImg(0,imageToUse,null,date,title,String.valueOf(timestamp));
        ImgDataSource imgDataSource= new ImgDataSource(getApplicationContext());
        saved=imgDataSource.saveItem(modelImg);
        // Check if was saved
        if(saved){
            favoritesButton.setVisible(false);
            eraseButton.setVisible(true);
            Snackbar.make(findViewById(android.R.id.content),"Imagen guardada en tus favoritos",Snackbar.LENGTH_SHORT).show();
        }
        else{
            favoritesButton.setVisible(true);
            eraseButton.setVisible(false);
            Snackbar.make(findViewById(android.R.id.content),"Lo siento, no pude guardar la imagen en tus favoritos, ¿lo intentas nuevamente?",Snackbar.LENGTH_INDEFINITE).show();
        }

    }
    // Removing item from favorites
    private void remove_item() {
        ImgDataSource imgDataSource= new ImgDataSource(getApplicationContext());
        imgDataSource.deleteItem(imageToUse);
        favoritesButton.setVisible(true);
        eraseButton.setVisible(false);
        Snackbar.make(findViewById(android.R.id.content),"Listo! Ya no esta en tus favoritos esta imagen",Snackbar.LENGTH_SHORT).show();


    }
    private void shareText(String text){
        Intent shareIntent=new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,text);
        startActivity(Intent.createChooser(shareIntent,"Compartir con amigos"));
    }
}
