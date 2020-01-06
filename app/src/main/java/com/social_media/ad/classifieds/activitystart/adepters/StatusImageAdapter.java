package com.social_media.ad.classifieds.activitystart.adepters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import com.social_media.ad.classifieds.R;

public class StatusImageAdapter extends RecyclerView.Adapter<StatusImageAdapter.MyViewHolder> {

    private ArrayList<String> images;
    private String path;
    Context context;

    public StatusImageAdapter(ArrayList<String> images, String path) {
        this.images = images;
        this.path = path;
    }
    @NonNull
    @Override
    public StatusImageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        this.context = viewGroup.getContext();
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_item, null);
        StatusImageAdapter.MyViewHolder viewHolder = new StatusImageAdapter.MyViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final StatusImageAdapter.MyViewHolder myViewHolder, int i) {
        Glide.with(context)
                .load(path + "/" + images.get(i))
                .apply(new RequestOptions().centerCrop().placeholder(android.R.color.black).fitCenter())
                .into(myViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return images == null ? 0 : images.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.statusItem_imageView);

        }
    }
}
