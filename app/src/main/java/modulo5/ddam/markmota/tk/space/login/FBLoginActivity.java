package modulo5.ddam.markmota.tk.space.login;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import modulo5.ddam.markmota.tk.space.MainActivity;
import modulo5.ddam.markmota.tk.space.R;

public class FBLoginActivity extends AppCompatActivity implements FacebookCallback<LoginResult> {
    private CallbackManager callbackManager;
    @BindView(R.id.login_face_button)
    LoginButton loginButton;
    @BindView(R.id.activity_nasa_image)
    SimpleDraweeView nasaImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fblogin);
        ButterKnife.bind(this);

        nasaImage.setImageURI("https://static.festisite.com/static/partylogo/img/logos/nasa.png");

        // Helps to listening the facebook login result
        callbackManager=CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager,this);

        if(AccessToken.getCurrentAccessToken() !=null){
            Snackbar.make(findViewById(android.R.id.content),"Ya esta logeado",Snackbar.LENGTH_SHORT).show();
            changeToMainActivity();
        }

    }
    private  void changeToMainActivity(){
        // Changing to facebook log  activity
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    @Override
    public void onSuccess(LoginResult loginResult) {
        Snackbar.make(findViewById(android.R.id.content),"exito de log",Snackbar.LENGTH_SHORT).show();
        changeToMainActivity();
    }

    @Override
    public void onCancel() {
        Snackbar.make(findViewById(android.R.id.content),"Cancelado",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onError(FacebookException error) {
        Snackbar.make(findViewById(android.R.id.content),error.getMessage(),Snackbar.LENGTH_INDEFINITE).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}
