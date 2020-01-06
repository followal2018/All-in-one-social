package com.social_media.ad.classifieds.home;

import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

public class ViewWrapper<V extends View> extends RecyclerView.ViewHolder{
    public ViewWrapper(V itemView) {
        super(itemView);
    }
}