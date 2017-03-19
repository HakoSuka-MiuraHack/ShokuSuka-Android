package android.shokusuka.miurahack.com.shokusuka;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;

public class MainActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private TextView info;
    private LoginButton loginButton;
//    private Facebook facebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        info = (TextView) findViewById(R.id.text);
//        facebook = new FacebookFactory().getInstance();
//        facebook.setOAuthAppId("1121827291273203", "b94ac5654f6ec27a75ec750d0e987284");
//        facebook.setOAuthPermissions("public_profile,email,user_photos,user_posts,publish_pages,publish_actions");

        checkFacebookLogin();
//        setPostButton();
    }

    private void checkFacebookLogin() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_main);
        loginButton = (LoginButton) findViewById(R.id.login_button);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("TAG", "onSuccess");
                Log.d("TAH", "Auth Token: " + loginResult.getAccessToken().getToken());
                Log.d("TAG", info.getText().toString());
                info.setText("User ID:  " +
                        loginResult.getAccessToken().getUserId() + "\n" +
                        "Auth Token: " + loginResult.getAccessToken().getToken());
//                facebook.setOAuthAccessToken(
//                        new AccessToken(loginResult.getAccessToken().getToken(), null));
            }

            @Override
            public void onCancel() {
                Log.d("TAG", "onCancel");
                info.setText("Login attempt cancelled.");
            }

            @Override
            public void onError(FacebookException e) {
                Log.d("TAG", "onError");
                info.setText("Login attempt failed.");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

//    private void setPostButton() {
//        findViewById(R.id.post_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EditText edittext = (EditText) findViewById(R.id.editText);
//                String text = edittext.getText().toString();
//                new FacebookPostTask(facebook).execute(text);
//            }
//        });
//    }
}
