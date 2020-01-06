package com.social_media.ad.classifieds.activitystart.fragmets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import com.social_media.ad.classifieds.R;

import com.social_media.ad.classifieds.activitystart.adepters.ViewpagerAdepter;
import com.social_media.ad.classifieds.activitystart.fragmets.saved.SavedImageFragment;
import com.social_media.ad.classifieds.activitystart.fragmets.saved.SavedVideoFragment;


public class SavedFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(getString(R.string.saved));
        return inflater.inflate(R.layout.fragment_saved,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout = getActivity().findViewById(R.id.tablayout);
        viewPager = getView().findViewById(R.id.viewpager);
        ViewpagerAdepter adepter = new ViewpagerAdepter(getChildFragmentManager());
        adepter.addFragment(new SavedImageFragment(), getContext().getString(R.string.image_status));
        adepter.addFragment(new SavedVideoFragment(), getContext().getString(R.string.video_status));
        viewPager.setAdapter(adepter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
