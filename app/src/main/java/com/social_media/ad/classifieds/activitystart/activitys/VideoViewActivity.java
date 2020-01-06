package com.social_media.ad.classifieds.activitystart.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import com.social_media.ad.classifieds.R;
import com.social_media.ad.classifieds.activitystart.utill.Utill;


public class VideoViewActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    VideoView videoView;
    String path = "";
    String call = "";
    RelativeLayout relativeLayout;
    Toolbar toolbar;
    int f = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(getApplicationContext(), perms)) {
            methodRequiresTwoPermission();
            Toast.makeText(getApplicationContext(), "Allow All Permissions", Toast.LENGTH_LONG).show();
        } else {
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        videoView = findViewById(R.id.videoView);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        relativeLayout = findViewById(R.id.main_vid);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (f == 0) {
                    getSupportActionBar().hide();
                    f = 1;
                } else {
                    f = 0;
                    getSupportActionBar().show();
                }
            }
        });
        try {
            path = getIntent().getExtras().getString("path");
            call = getIntent().getExtras().getString("call");
        } catch (Exception e) {
            Toast.makeText(this, getString(R.string.something_is_wrong), Toast.LENGTH_SHORT).show();
        }
        if (!path.equals("")) {
            videoView.setVideoPath(path);
            videoView.setMediaController(new MediaController(this));
            videoView.start();

        }
    }

    Menu menu = null;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.saved_menu, menu);
        this.menu = menu;
        hideAndShow();
        return true;
    }

    private void hideAndShow() {
        if (Utill.checkIsVideoSave(VideoViewActivity.this, new File(path))) {
            menu.findItem(R.id.menu_save).setVisible(false);
            menu.findItem(R.id.menu_delete).setVisible(true);
        } else {
            menu.findItem(R.id.menu_delete).setVisible(false);
            menu.findItem(R.id.menu_save).setVisible(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_save) {
            String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
            if (!EasyPermissions.hasPermissions(getApplicationContext(), perms)) {
                methodRequiresTwoPermission();

            }
            Utill.addVideoToGallery(this, path);
            hideAndShow();
        } else if (item.getItemId() == R.id.menu_delete) {
            Utill.deleteVideoToSave(VideoViewActivity.this, new File(path));
            if (!call.equals("")) {
                if (call.equals("recent")) {
                    hideAndShow();
                } else if (call.equals("saved")) {
                   this.finish();
                }
            }
        } else if (item.getItemId() == R.id.menu_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.setType("video/*");
            sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(path)));
            startActivity(sendIntent);
        }
        return true;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @AfterPermissionGranted(00)
    private void methodRequiresTwoPermission() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // Already have permission, do the thing
            // ...
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, "this need this Permissions",
                    00, perms);
        }

    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        methodRequiresTwoPermission();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        methodRequiresTwoPermission();
    }
}