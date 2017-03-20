package android.shokusuka.miurahack.com.shokusuka.facebook;

import android.os.AsyncTask;
import android.util.Log;

import com.facebook.AccessToken;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FacebookPostTask extends AsyncTask<String, String, String> {

    public FacebookPostTask() {
    }

    @Override
    protected String doInBackground(String... params) {
        Log.d("Facebook", "on Facebook post " + params[0]);
        return post(params[0]);
    }

    private String post(String message) {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        String userId = accessToken.getUserId();

        Request request = new Request.Builder()
                .url("http://graph.facebook.com/" + userId + "/feed")
                .post((new FormBody.Builder())
                        .add("message", message)
                        .add("access_token", accessToken.getToken())
                        .build())
                .build();

        OkHttpClient client = new OkHttpClient();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
