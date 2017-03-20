package android.shokusuka.miurahack.com.shokusuka;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by tourn on 2017/03/20.
 */

public class PictureInfoAdapter extends ArrayAdapter<PictureInfo> {

        LayoutInflater mInflater;
        PackageManager packageManager;

        public PictureInfoAdapter(Context context) {
                super(context, 0);
                mInflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                convertView = mInflater.inflate(R.layout.adapter_list_item_picture, parent, false);
                }

                PictureInfo info = getItem(position);

                TextView tv = (TextView) convertView.findViewById(R.id.title);
                //tv.setText(info.applicationInfo.loadLabel(packageManager));
                tv = (TextView) convertView.findViewById(R.id.Time);
                //tv.setText(info.packageName + "\n" + "versionName : " + info.versionName + "\nversionCode : " + info.versionCode);
                ImageView iv = (ImageView) convertView.findViewById(R.id.icon);
                //iv.setImageDrawable(info.applicationInfo.loadIcon(packageManager));


                return convertView;
                }
}

