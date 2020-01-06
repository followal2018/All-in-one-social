package com.social_media.ad.classifieds.activitystart.fragmets.recent;

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


public class RecentVideoFragment extends Fragment {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_video_recent, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootpath = StatusData.getROOTPATH() + getString(R.string.status_path);
        recyclerView = view.findViewById(R.id.recyclerViewImage);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setUPList();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent intent = new Intent(getActivity(), VideoViewActivity.class);
                intent.putExtra("path", rootpath + "/" + videoFiles.get(position));
                intent.putExtra("call", "recent");
                startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        setUPList();
    }

    String rootpath;
    ArrayList<String> videoFiles;

    private void setUPList() {
        videoFiles = StatusData.getRecentVideo(getActivity());
        StatusVideoAdapter statusVideoAdapter = new StatusVideoAdapter(videoFiles, rootpath, getActivity());
        recyclerView.setAdapter(statusVideoAdapter);
    }
}
