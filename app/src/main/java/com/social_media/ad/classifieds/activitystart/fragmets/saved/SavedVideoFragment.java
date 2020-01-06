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

import com.social_media.ad.classifieds.activitystart.activitys.VideoViewActivity;
import com.social_media.ad.classifieds.activitystart.adepters.StatusVideoAdapter;
import com.social_media.ad.classifieds.activitystart.listener.RecyclerTouchListener;
import com.social_media.ad.classifieds.activitystart.utill.StatusData;


public class SavedVideoFragment extends Fragment {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<String> videoFiles;
    String rootpath;
    StatusVideoAdapter statusVideoAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_video_saved, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootpath = StatusData.getROOTPATH() + getContext().getString(R.string.save_video);
        recyclerView = view.findViewById(R.id.recyclerViewImage);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setUPList();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent intent = new Intent(getActivity(), VideoViewActivity.class);
                intent.putExtra("path", rootpath + "/" + videoFiles.get(position));
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
        videoFiles = StatusData.getSavedVideo(getActivity());
        statusVideoAdapter = new StatusVideoAdapter(videoFiles, rootpath, getActivity());
        recyclerView.setAdapter(statusVideoAdapter);
        statusVideoAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        setUPList();
    }
}