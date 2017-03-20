package android.shokusuka.miurahack.com.shokusuka;

import android.os.AsyncTask;

import facebook4j.Facebook;

public class FacebookPostTask extends AsyncTask<String, String, String> {
    private Facebook facebook;

    public FacebookPostTask(Facebook facebook) {
        this.facebook = facebook;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            return facebook.postStatusMessage(params[0]);
        } catch (facebook4j.FacebookException e) {
            e.printStackTrace();
        }
        return "";
    }
}
