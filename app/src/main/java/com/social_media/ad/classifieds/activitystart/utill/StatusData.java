package com.social_media.ad.classifieds.activitystart.utill;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;

import com.social_media.ad.classifieds.R;

public final class StatusData {

    private static final String ROOTPATH = Environment.getExternalStorageDirectory().getAbsolutePath();

    public static ArrayList<String> getRecentImages(Context context) {
        File hiddenpath = new File(ROOTPATH + context.getString(R.string.status_path));
        ArrayList<String> imageFiles = new ArrayList<>();
        if (hiddenpath != null) {
            String[] fileName = hiddenpath.list();
            if (fileName.length > 0) {
                for (String f : fileName) {
                    if (HelpperMethods.isImage(f)) {
                        imageFiles.add(f);
                    }
                }
            }
        }
        return imageFiles;
    }

    public static String getROOTPATH() {
        return ROOTPATH;
    }

    public static ArrayList<String> getRecentVideo(Context context) {
        File hiddenpath = new File(ROOTPATH + context.getString(R.string.status_path));
        ArrayList<String> videoFiles = new ArrayList<>();
        if (hiddenpath != null) {
            String[] fileName = hiddenpath.list();
            if (fileName.length > 0) {
                for (String f : fileName) {
                    if (HelpperMethods.isVideo(f)) {
                        videoFiles.add(f);
                    }
                }
            }
        }
        return videoFiles;
    }

    public static ArrayList<String> getSavedImages(Context context) {
        File hiddenpath = new File(ROOTPATH + context.getString(R.string.save_image));
        ArrayList<String> imageFiles = new ArrayList<>();
        if (hiddenpath != null) {
            String[] fileName = hiddenpath.list();
            if (fileName.length > 0) {
                for (String f : fileName) {
                    if (HelpperMethods.isImage(f)) {
                        imageFiles.add(f);
                    }
                }
            }
        }
        return imageFiles;
    }

    public static ArrayList<String> getSavedVideo(Context context) {
        File hiddenpath = new File(ROOTPATH + context.getString(R.string.save_video));
        ArrayList<String> videoFiles = new ArrayList<>();
        if (hiddenpath != null) {
            String[] fileName = hiddenpath.list();
            if (fileName.length > 0) {
                for (String f : fileName) {
                    if (HelpperMethods.isVideo(f)) {
                        videoFiles.add(f);
                    }
                }
            }
        }
        return videoFiles;
    }
}
