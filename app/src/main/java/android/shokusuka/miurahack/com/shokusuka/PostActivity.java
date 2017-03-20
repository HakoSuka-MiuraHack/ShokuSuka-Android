package android.shokusuka.miurahack.com.shokusuka;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Environment;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class PostActivity extends Activity implements LocationListener {
    private static final int REQUEST_GALLERY = 0;
    private final static int RESULT_CAMERA = 1001;
    LocationManager locman;//for GSP
    private int ImgCount = 0;
    ImageButton SnapButton, LiraryButton;
    ImageView imagepreView1,imagepreView2,imagepreView3;
    Button PostCancel,PostButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_activity);
        locman = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        PostCancel = (Button) findViewById(R.id.PostCancel);
        PostButton = (Button) findViewById(R.id.PostButton);

        PostCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // クリック時の処理
                // 前の画面に戻る
                finish();
            }
        });

        PostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // クリック時の処理
                // APIをたたく
            }
        });

                //写真撮影
                SnapButton = (ImageButton) findViewById(R.id.SanpButton);
                SnapButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // クリック時の処理
                        // 写真撮影画面呼び出し
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, RESULT_CAMERA);
                    }
                });

                LiraryButton = (ImageButton) findViewById(R.id.ImageLibrary);//画像リストへの遷移
                LiraryButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // クリック時の処理
                        // ギャラリー呼び出し
                        callGraly();
                    }
                });

            /*preview用画像取得*/
                imagepreView1= (ImageView) findViewById(R.id.PreviewImgae1);
                imagepreView2= (ImageView) findViewById(R.id.PreviewImgae2);
                imagepreView3= (ImageView) findViewById(R.id.PreviewImgae3);

                imagepreView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // クリック時の処理
                        ImgCount--;
                        deleatPreViewImage(0,ImgCount);
                    }
                });
                imagepreView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // クリック時の処理
                        ImgCount--;
                        deleatPreViewImage(1,ImgCount);

                    }
                });
                imagepreView3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // クリック時の処理
                        ImgCount--;
                        deleatPreViewImage(2,ImgCount);
                    }
                });
            }
            @Override
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                // TODO Auto-generated method stub
                // 選択した画像を表示
                int flag = 0;
                Bitmap bitmap = null;
                //写真撮影後の処理
                if (requestCode == RESULT_CAMERA) {
                    bitmap = (Bitmap) data.getExtras().get("data");
                    //imageView.setImageBitmap(bitmap);
                    saveImageExternal(bitmap);
                    //imageView.setImageBitmap(bitmap);
                    flag++;
                }

                //画像リストタップ後の処理
                if(requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
                    try {
                        InputStream in = getContentResolver().openInputStream(data.getData());
                        bitmap = BitmapFactory.decodeStream(in);
                        in.close();
                    } catch (Exception e) {
                    }
                    flag++;
                }
                setImageResouse(bitmap, ImgCount);
                ImgCount++;
            }

            //画像リストの呼び出し
            public void callGraly(){
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_GALLERY);
            }

            private void setImageInvisible(int num){
                if(num == 0) {
                    imagepreView1.setVisibility(View.INVISIBLE);
                }else if(num ==1) {
                    imagepreView2.setVisibility(View.INVISIBLE);
                }else{
                    imagepreView3.setVisibility(View.INVISIBLE);
                }
            }
            private Bitmap getImageRessouse(int num){
                if(num == 0) {
                    return ((BitmapDrawable)imagepreView1.getDrawable()).getBitmap();
                }else if(num ==1) {
                    return ((BitmapDrawable)imagepreView2.getDrawable()).getBitmap();
                }else{
                    return ((BitmapDrawable)imagepreView3.getDrawable()).getBitmap();
                }
            }
            private void setImageResouse(Bitmap bitmap,int num){
                if(num == 0) {
                    imagepreView1.setImageBitmap(bitmap);
                    imagepreView1.setVisibility(View.VISIBLE);
                }else if(num ==1) {
                    imagepreView2.setImageBitmap(bitmap);
                    imagepreView2.setVisibility(View.VISIBLE);
                }else{
                    imagepreView3.setImageBitmap(bitmap);
                    imagepreView3.setVisibility(View.VISIBLE);
                }
            }

            private void deleatPreViewImage(int choiceNum, int ImgCount){
                Log.d("TESE", "deleatPreViewImage: img=" +ImgCount);
                for(int i = choiceNum ; i<= ImgCount ; i++) {
                    Log.d("TESE", "deleatPreViewImage: i=" +i);
                    if(ImgCount > 0 && i != choiceNum
                            )
                        setImageResouse(getImageRessouse(i), i -1);
                    setImageInvisible(i);
                }
            }
            /*-------------------------------------------------GPS----------------------*/
            @Override
            protected void onResume(){
                if (locman != null){
                    locman.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,0,this);
                }
                super.onResume();
            }

            @Override
            protected void onPause(){
                if (locman != null){
                    locman.removeUpdates(this);
                }
                super.onPause();
            }

            @Override
            public void onLocationChanged(Location location){
                TextView textView1 = (TextView) findViewById(R.id.comment);
                textView1.setText("Latitude:Longitude - "
                        +String.valueOf(location.getLatitude()) +":"+String.valueOf(location.getLongitude()));
                Log.v("----------", "----------");
                Log.v("Latitude", String.valueOf(location.getLatitude()));
                Log.v("Longitude", String.valueOf(location.getLongitude()));
                Log.v("Accuracy", String.valueOf(location.getAccuracy()));
                Log.v("Altitude", String.valueOf(location.getAltitude()));
                Log.v("Time", String.valueOf(location.getTime()));
                Log.v("Speed", String.valueOf(location.getSpeed()));
                Log.v("Bearing", String.valueOf(location.getBearing()));
            }

            @Override
            public void onProviderDisabled(String provider){

            }

            @Override
            public void onProviderEnabled(String provider){
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras){
                switch(status){
                    case LocationProvider.AVAILABLE:
                        Log.v("Status","AVAILABLE");
                        break;
                    case LocationProvider.OUT_OF_SERVICE:
                        Log.v("Status","OUT_OF_SERVICE");
                        break;
                    case  LocationProvider.TEMPORARILY_UNAVAILABLE:
                        Log.v("Status","TEMPORARILY_UNAVAILABLE");
                        break;
                }
            }
            /*--------------------------------------写真のローカルストレージへの保存-------------------------------------*/
            private void saveImageExternal(Bitmap bmp) {

                //外部ストレージへのアクセスを確認する
                if (!isExternalStorageWritable()) {
                    Log.i("saveImageExternal", "External Storage Not Writable.");
                    return;
                }

                //パスを取得する
                String storagePath = Environment.getExternalStorageDirectory().getAbsolutePath();
                String directoryName = "/MyPhoto/";
                String fileName = directoryName + generateFileName();

                //保存先のディレクトリがなければ作成する
                File file = new File(storagePath + directoryName);
                try {
                    if (!file.exists()) {
                        file.mkdir();
                    }
                } catch (SecurityException e) {
                    e.printStackTrace();
                }

                //ファイルを外部ストレージに保存して、ギャラリーに追加する
                file = new File(storagePath, fileName);
                FileOutputStream out = null;
                try {
                    out = new FileOutputStream(file);
                    bmp.compress(Bitmap.CompressFormat.JPEG, 100, out);
                    out.flush();

                    addImageToGallery(file.getAbsolutePath());
                } catch (IOException e ) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (out != null)
                            out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            private boolean isExternalStorageWritable() {
                String state = Environment.getExternalStorageState();
                return Environment.MEDIA_MOUNTED.equals(state);
            }

            private boolean isExternalStorageReadable() {
                String state = Environment.getExternalStorageState();
                return Environment.MEDIA_MOUNTED.equals(state) ||
                        Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
            }

            private void addImageToGallery(String filePath) {
                try {
                    ContentValues values = new ContentValues();

                    values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
                    values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                    values.put(MediaStore.MediaColumns.DATA, filePath);

                    //values.put(MediaStore.Images.Media.LATITUDE,location.getLatitude());
                    getApplicationContext().getContentResolver()
                            .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            private String generateFileName() {
                Date date = new Date();
                SimpleDateFormat fileNameFormat = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH);
                return fileNameFormat.format(date) + ".jpg";
            }
        }