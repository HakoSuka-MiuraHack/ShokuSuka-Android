package android.shokusuka.miurahack.com.shokusuka;

import android.content.Intent;
import android.os.Bundle;
import android.shokusuka.miurahack.com.shokusuka.facebook.FacebookLoginActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFacebookLogin();
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
}
