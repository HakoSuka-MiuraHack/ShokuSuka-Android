package android.shokusuka.miurahack.com.shokusuka;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.shokusuka.miurahack.com.shokusuka.facebook.FacebookLoginActivity;
import android.shokusuka.miurahack.com.shokusuka.facebook.FacebookPostTask;
import android.shokusuka.miurahack.com.shokusuka.facebook.FacebookShareActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (AccessToken.getCurrentAccessToken() != null) {
            Log.d("Facebook", "AccessToken: " + AccessToken.getCurrentAccessToken().toString());
        }

        initFacebookLogin();
        initFacebookPost();
    }

    private void initFacebookLogin() {
        final Button fbLoginButton = (Button) findViewById(R.id.fb_login_button);
        fbLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FacebookLoginActivity.class));
            }
        });
    }

    private void initFacebookPost() {
        final Button fbPostButton = (Button) findViewById(R.id.fb_post_button);
        fbPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bitmap image = Bitmap();
                SharePhoto photo = new SharePhoto.Builder()
//                        .setBitmap(image)
                        .build();
                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(photo)
                        .build();
//                startActivity(new Intent(MainActivity.this, FacebookShareActivity.class));
//                new FacebookPostTask().execute("Hello, Facebook");
            }
        });
    }
}
