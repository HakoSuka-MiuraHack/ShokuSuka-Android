package android.shokusuka.miurahack.com.shokusuka;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkFacebookLogin();
        setLogoutButton();
    }

    private void checkFacebookLogin() {
        if (AccessToken.getCurrentAccessToken() == null) {
            startActivity(new Intent(getApplication(), FacebookLoginActivity.class));
        }
    }

    private void setLogoutButton() {
        findViewById(R.id.logout_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
            }
        });

    }
}
