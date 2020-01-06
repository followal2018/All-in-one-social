package com.social_media.ad.classifieds.home;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.social_media.ad.classifieds.R;
import com.social_media.ad.classifieds.activity.BrowserActivity;
import com.social_media.ad.classifieds.activity.WebViewTwitter;
import com.social_media.ad.classifieds.activity.WebviewActivity;
import com.social_media.ad.classifieds.model.SocialListBean;

import java.util.List;

public class RecyclerViewExampleItem extends FrameLayout {
    ImageView imageView, imageicon;
    TextView textView;
    RelativeLayout linearLayout;
    final String TAG = "RecyclerViewItem";
    public RecyclerViewExampleItem(final Context context) {
        super(context);
        inflate(context, R.layout.recyclerview_item1, this);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageicon = (ImageView) findViewById(R.id.image_icon);
        textView = (TextView) findViewById(R.id.textView);
        linearLayout = (RelativeLayout) findViewById(R.id.ll_main);
    }

    public void bind(final List<SocialListBean> socialListBeans, final int position, final Context context) {
        textView.setText(socialListBeans.get(position).getName());
        imageView.setImageResource(socialListBeans.get(position).getImage());
        Log.e(TAG,"Position "+position+" : "+socialListBeans.get(position).getName());
        imageicon.setImageResource(socialListBeans.get(position).getIconimg());
        linearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if(!socialListBeans.get(position).getName().equalsIgnoreCase("Twitter")) {
                    intent = new Intent(context, BrowserActivity.class);
                }else {
                    intent = new Intent(context, BrowserActivity.class);
                }
                intent.putExtra("comeFrom", socialListBeans.get(position).getName());
                context.startActivity(intent);
            }
        });
    }
}
