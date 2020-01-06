package com.social_media.ad.classifieds.activitystart.adepters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewpagerAdepter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> fragmentTitalList = new ArrayList<>();

    public ViewpagerAdepter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitalList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentTitalList == null ? 0 : fragmentTitalList.size();
    }

    public void addFragment(Fragment fragment, String fragmentTital) {
        fragmentList.add(fragment);
        fragmentTitalList.add(fragmentTital);
    }
}
