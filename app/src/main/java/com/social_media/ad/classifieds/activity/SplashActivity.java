package com.social_media.ad.classifieds.activity;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.github.omadahealth.lollipin.lib.managers.AppLock;
import com.github.omadahealth.lollipin.lib.managers.LockManager;
import com.social_media.ad.classifieds.R;
import com.social_media.ad.classifieds.lock_screen.CustomPinActivity;
import com.social_media.ad.classifieds.utils.SharedObjects;

public class SplashActivity extends AppCompatActivity implements ForceUpdateChecker.OnUpdateNeededListener {
    private static final int SPLASH_TIME_OUT = 6000;
    SharedObjects sharedObjects;
    Animation mAnimation_1;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        LockManager<CustomPinActivity> lockManager = LockManager.getInstance();
        lockManager.enableAppLock(this, CustomPinActivity.class);
        lockManager.getAppLock().setShouldShowForgot(false);
        sharedObjects = new SharedObjects(this);

        img=findViewById(R.id.img);
        initFunctionality();
        mAnimation_1 = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);
       /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Boolean isLock = sharedObjects.preferencesEditor.getBoolean("isLock");
                if (!isLock) {
                    Intent intent = new Intent(SplashActivity.this, CustomPinActivity.class);
                    intent.putExtra(AppLock.EXTRA_TYPE, AppLock.UNLOCK_PIN);
                    startActivity(intent);
                } else {
                    Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(i);
                }
                finish();
            }
        }, SPLASH_TIME_OUT);*/
    }
    private void initFunctionality() {
//        if (AppUtilities.isNetworkAvailable(mContext)) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                img.startAnimation(mAnimation_1);
                mAnimation_1.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        Boolean isLock = sharedObjects.preferencesEditor.getBoolean("isLock");
                        if (!isLock) {
                            Intent intent = new Intent(SplashActivity.this, CustomPinActivity.class);
                            intent.putExtra(AppLock.EXTRA_TYPE, AppLock.UNLOCK_PIN);
                            startActivity(intent);
                        } else {
                            Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                            startActivity(i);
                        }
                        finish();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        }, SPLASH_TIME_OUT);

    }

    @Override
    public void onUpdateNeeded(final String updateUrl) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("New version available")
                .setMessage("Please, update app to new version to continue reposting.")
                .setPositiveButton("Update",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                redirectStore(updateUrl);
                            }
                        }).setNegativeButton("No, thanks",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }).create();
        dialog.show();
    }
    private void redirectStore(String updateUrl) {
        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }
}
