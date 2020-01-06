package com.social_media.ad.classifieds.activitystart.utill;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Environment;

import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

import com.social_media.ad.classifieds.R;

public final class Utill {

    public static void addImageToGallery(Context context, String source) {
        File myDirectory = new File(Environment.getExternalStorageDirectory(), context.getString(R.string.save_image));

        if (!myDirectory.exists()) {
            myDirectory.mkdirs();
        } else {
            Log.e("directory", "folder already exists");
        }
        //
        File sourceFIle = new File(source);
        String destinationPath = Environment.getExternalStorageDirectory().getAbsolutePath() + context.getString(R.string.save_image) + sourceFIle.getName();


        File destination = new File(destinationPath);

        try {
            FileUtils.copyFile(sourceFIle, destination);
            Toast.makeText(context, context.getString(R.string.image_saved), Toast.LENGTH_LONG).show();
            //MediaStore.Images.Media.insertImage(context.getApplicationContext().getContentResolver(), BitmapFactory.decodeFile(destinationPath),"", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addVideoToGallery(Context context, String source) {
        File myDirectory = new File(Environment.getExternalStorageDirectory(), context.getString(R.string.save_video));

        if (!myDirectory.exists()) {
            myDirectory.mkdirs();
        } else {
            Log.e("directory", "folder already exists");
        }
        //
        File sourceFIle = new File(source);
        String destinationPath = Environment.getExternalStorageDirectory().getAbsolutePath() + context.getString(R.string.save_video) + sourceFIle.getName();

        File destination = new File(destinationPath);

        try {
            FileUtils.copyFile(sourceFIle, destination);
            Toast.makeText(context, context.getString(R.string.video_saved), Toast.LENGTH_LONG).show();
            //MediaStore.Images.Media.insertImage(context.getApplicationContext().getContentResolver(), BitmapFactory.decodeFile(destinationPath),"", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Boolean checkIsImageSave(Context context,File file) {
        String destinationPath = Environment.getExternalStorageDirectory().getAbsolutePath() + context.getString(R.string.save_image) + file.getName();

        File destination = new File(destinationPath);
        if (!destination.exists()) {
            return false;
        } else {
            return true;
        }
    }
    public static Boolean checkIsVideoSave(Context context,File file) {
        String destinationPath = Environment.getExternalStorageDirectory().getAbsolutePath() + context.getString(R.string.save_video) + file.getName();

        File destination = new File(destinationPath);
        if (!destination.exists()) {
            return false;
        } else {
            return true;
        }
    }
    public static void deleteImageToSave(Context context,File file) {
        String destinationPath = Environment.getExternalStorageDirectory().getAbsolutePath() + context.getString(R.string.save_image) + file.getName();
        File destination = new File(destinationPath);
        if (destination.exists()) {
            if (destination.delete())
                Toast.makeText(context,context.getString(R.string.image_deleted),Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(context,context.getString(R.string.image_not_deleted),Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,context.getString(R.string.image_not_deleted),Toast.LENGTH_SHORT).show();
        }
    }
    public static void deleteVideoToSave(Context context,File file) {
        String destinationPath = Environment.getExternalStorageDirectory().getAbsolutePath() + context.getString(R.string.save_video) + file.getName();
        File destination = new File(destinationPath);
        if (destination.exists()) {
            if (destination.delete())
                Toast.makeText(context,context.getString(R.string.video_deleted),Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(context,context.getString(R.string.video_not_deleted),Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,context.getString(R.string.video_not_deleted),Toast.LENGTH_SHORT).show();
        }
    }
}
