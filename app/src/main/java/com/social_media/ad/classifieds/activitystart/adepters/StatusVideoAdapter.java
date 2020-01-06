package com.social_media.ad.classifieds.activitystart.adepters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;

import android.os.AsyncTask;
import android.os.CancellationSignal;
import android.util.Log;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.util.ArrayList;

import com.social_media.ad.classifieds.R;

public class StatusVideoAdapter extends RecyclerView.Adapter<StatusVideoAdapter.MyViewHolder> {


    private ArrayList<String> videos;
    private String path;
    private Context context;

    public StatusVideoAdapter(ArrayList<String> videos, String path, Context context) {
        this.videos = videos;
        this.path = path;
        this.context = context;
    }


    @NonNull
    @Override
    public StatusVideoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_item, null);
        return new StatusVideoAdapter.MyViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull StatusVideoAdapter.MyViewHolder myViewHolder, int i) {
        //Log.i("video : ", path + "/" + videos.get(i));
        Glide.with(context)
                .load(path + "/" + videos.get(i))
                .apply(new RequestOptions().centerCrop().placeholder(android.R.color.black).centerCrop())
                .into(myViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return videos == null ? 0 : videos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.statusItem_imageView);
        }
    }
}

