package android.shokusuka.miurahack.com.shokusuka;

import android.content.Intent;
import android.os.Bundle;
import android.shokusuka.miurahack.com.shokusuka.facebook.FacebookLoginActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PictureInfoAdapter adapter = new PictureInfoAdapter(getApplicationContext());

        for (int i = 0 ; i < 10 ;  i++)
            adapter.add(new PictureInfo(i,"UserName"+i,"PictureUrl"+i,"MainText"+i));

        int padding = (int) (getResources().getDisplayMetrics().density * 8);
        ListView listView = (ListView) findViewById(R.id.list_item);
        listView.setPadding(padding, 0, padding, 0);
        listView.setScrollBarStyle(ListView.SCROLLBARS_OUTSIDE_OVERLAY);
        listView.setDivider(null);

        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
//        View header = inflater.inflate(R.layout.list_header_footer, listView, false);
//        View footer = inflater.inflate(R.layout.list_header_footer, listView, false);
//        listView.addHeaderView(header, null, false);
//        listView.addFooterView(footer, null, false);
        listView.setAdapter(adapter);



//        if (AccessToken.getCurrentAccessToken() != null) {
//            Log.d("Facebook", "AccessToken: " + AccessToken.getCurrentAccessToken().toString());
//        }

//        initFacebookLogin();
//        initFacebookPost();
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
