package com.social_media.ad.classifieds.activitystart.fragmets.saved;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.io.File;
import java.util.ArrayList;

import com.social_media.ad.classifieds.R;

import com.social_media.ad.classifieds.activitystart.activitys.ImageViewActivity;
import com.social_media.ad.classifieds.activitystart.adepters.StatusImageAdapter;
import com.social_media.ad.classifieds.activitystart.listener.RecyclerTouchListener;
import com.social_media.ad.classifieds.activitystart.utill.StatusData;

public class SavedImageFragment extends Fragment {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<String> imageFiles;
    String rootpath;
    StatusImageAdapter statusImageAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image_saved, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootpath = StatusData.getROOTPATH() + getContext().getString(R.string.save_image);
        recyclerView = view.findViewById(R.id.recyclerViewImage);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setUPList();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        imageFiles = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                //Toast.makeText(getActivity(), imageFiles.get(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), ImageViewActivity.class);
                intent.putExtra("path", rootpath + "/" + imageFiles.get(position));
                intent.putExtra("call", "saved");
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        setUPList();
    }

    private void setUPList() {
        imageFiles = StatusData.getSavedImages(getActivity());

        statusImageAdapter = new StatusImageAdapter(imageFiles, rootpath);
        recyclerView.setAdapter(statusImageAdapter);
        statusImageAdapter.notifyDataSetChanged();

    }

    @Override
    public void onResume() {
        super.onResume();
        setUPList();
    }
}
