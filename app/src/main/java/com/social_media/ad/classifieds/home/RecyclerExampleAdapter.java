package com.social_media.ad.classifieds.home;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.ViewGroup;

import com.social_media.ad.classifieds.model.SocialListBean;

import java.util.ArrayList;
import java.util.List;

public class RecyclerExampleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SocialListBean> socialListBeans;
    private Context mContext;
    private final String TAG = "RecyclerExampleAdapter";
    public RecyclerExampleAdapter(Context context) {
        mContext = context;
        socialListBeans = new ArrayList<>();
    }

    @Override
    public ViewWrapper<RecyclerViewExampleItem> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewWrapper<RecyclerViewExampleItem>(new RecyclerViewExampleItem(mContext));
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        RecyclerViewExampleItem rvei = (RecyclerViewExampleItem) viewHolder.itemView;
        int count = socialListBeans.size();

        //Log.e(TAG,"List count : "+count);
        /*String str = getItem(position);
        int img = getImage(position);
        int icon = geticonImage(position);
        boolean isShow = isShow(position);*/
        rvei.bind(socialListBeans, position, mContext);
    }

    @Override
    public int getItemCount() {
        return socialListBeans.size();
    }

    @Override
    public long getItemId(int position) {
        return (long)position;
    }

    /*public String getItem(int position) {
        return socialListBeans.get(position).getName();
    }

    public int getImage(int position) {
        return socialListBeans.get(position).getImage();
    }

    public int geticonImage(int position) {
        return socialListBeans.get(position).getIconimg();
    }

    public boolean isShow(int position) {
        return socialListBeans.get(position).isShow();
    }*/


    public void addAll(List<SocialListBean> lst)
    {
        this.socialListBeans = lst;
    }
}
