package com.social_media.ad.classifieds.settings;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.social_media.ad.classifieds.Adapter.AdapterSectionRecycler;
import com.social_media.ad.classifieds.R;
import com.social_media.ad.classifieds.activity.HomeActivity;
import com.social_media.ad.classifieds.model.Child;
import com.social_media.ad.classifieds.model.SectionHeader;
import com.social_media.ad.classifieds.utils.AppConstants;
import com.social_media.ad.classifieds.utils.SharedObjects;

import java.util.ArrayList;
import java.util.List;

public class AppsActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mFbTv;
    private Switch mFbSwitch;
    private LinearLayout mFbLin;
    private ImageView mAppsArrow;
    private TextView mInstaTv;
    private Switch mInstaSwitch;
    private LinearLayout mInstaLin;
    private TextView mPinTv;
    private Switch mPinSwitch;
    private LinearLayout mPinLin;
    private TextView mTwitTv;
    private Switch mTwitSwitch;
    private LinearLayout mTwitLin;
    private TextView mSkypeTv;
    private Switch mSkypeSwitch;
    private LinearLayout mSkypeLin;
    private TextView mSnapchatTv;
    private Switch mSnapchatSwitch;
    private LinearLayout mSnapchatLin;
 /*   private TextView mGplusTv;
    private Switch mGplusSwitch;
    private LinearLayout mGplusLin;
    private TextView mTelegramTv;
    private Switch mTelegramSwitch;
    private LinearLayout mTelegramLin;*/
    private TextView mLinkedinTv;
    private Switch mLinkedinSwitch;
    private LinearLayout mLinkedinLin;
/*    private TextView mQuoraTv;
    private Switch mQuoraSwitch;
    private LinearLayout mQuoraLin;*/
  /**/
  private TextView mMessagnerTv;
    private Switch mMessagnerSwitch;
    private LinearLayout mMessagnerLin;
    //
    private TextView mYoutubeTv;
    private Switch mYoutubeSwitch;
    private LinearLayout mYoutubeLin;
    //
    private TextView mRedditTv;
    private Switch mRedditSwitch;
    private LinearLayout mRedditLin;
    //
    private TextView mMediumTv;
    private Switch mMediumSwitch;
    private LinearLayout mMediumLin;
    //
    private TextView mStumbleuponTv;
    private Switch mStumbleuponSwitch;
    private LinearLayout mStumbleuponLin;
    //
    private TextView mMySpaceTv;
    private Switch mMySpaceSwitch;
    private LinearLayout mMySpaceLin;
    /**/
    SharedObjects sharedObjects;
    ImageView imghome, imgsetting;
    LinearLayout linearLayout;
    FrameLayout frameLayout;
    TextView imgappmanage;
    private AdView mAdView;
    Child child;
    RecyclerView recyclerView;
    AdapterSectionRecycler adapterRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps);

        initView();



        MobileAds.initialize(AppsActivity.this, getString(R.string.test_admob_app_id));

        mAdView = findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mAdView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
            }

            @Override
            public void onAdOpened() {
            }

            @Override
            public void onAdLeftApplication() {
            }

            @Override
            public void onAdClosed() {
            }
        });

        List<Child> childList = new ArrayList<>();
        childList.add(new Child("Facebook", AppConstants.FACEBOOK, R.drawable.fb));
        childList.add(new Child("Twitter", AppConstants.TWITTER, R.drawable.ic_twitter));
        childList.add(new Child("Instagram", AppConstants.INSTAGRAM, R.drawable.insta));
        childList.add(new Child("TikTok", AppConstants.TIKTOK, R.drawable.ic_tiktok));
        childList.add(new Child("Tumblr", AppConstants.TUMBLR, R.drawable.ic_tumblr_2));
        childList.add(new Child("Likee", AppConstants.LIKEE, R.drawable.ic_likee));
        childList.add(new Child("Pinterest", AppConstants.PINTEREST, R.drawable.ic_pinterest));
        childList.add(new Child("Snapchat", AppConstants.SNAPCHAT, R.drawable.ic_snapchat));
        childList.add(new Child("Linkedin", AppConstants.LINKEDIN, R.drawable.ic_linkedin));
        childList.add( new Child("Messanger",AppConstants.MESSANGER, R.drawable.messenger));
        childList.add( new Child("Youtube",AppConstants.YOUTUBE, R.drawable.youtube));
        childList.add( new Child("Reddit",AppConstants.REDDIT, R.drawable.reddit));
        childList.add( new Child("Medium",AppConstants.MEDIUM, R.drawable.medium));
        childList.add( new Child("Stumbleupon",AppConstants.STUMBLEUPON, R.drawable.stumbleupon));
        childList.add( new Child("MySpace",AppConstants.MYSPACE, R.drawable.myspace));
        List<SectionHeader> sections = new ArrayList<>();
        sections.add(new SectionHeader(childList, "Social Media"));
        childList = new ArrayList<>();
        childList.add(new Child("Olx", AppConstants.OLX, R.drawable.ic_olx));
        childList.add(new Child("Quikr", AppConstants.QUIKR, R.drawable.ic_quikr));
        childList.add(new Child("OOrgin", AppConstants.OORGIN, R.drawable.ic_oorigin));
        childList.add(new Child("Locanto", AppConstants.LOCANTO, R.drawable.ic_locanto));
        childList.add(new Child("Click", AppConstants.CLICK, R.drawable.ic_click));
        childList.add(new Child("ClickIndia", AppConstants.CLICKINDIA, R.drawable.ic_clickindia));
        childList.add(new Child("Magicbricks", AppConstants.MAGICBRICKS, R.drawable.ic_magicbricks));
        childList.add(new Child("99acres", AppConstants.ACRES, R.drawable.ic_99acres));
        childList.add(new Child("Indeed", AppConstants.INDEED, R.drawable.ic_indeed));
        sections.add(new SectionHeader(childList, "Classifieds"));

        childList = new ArrayList<>();
        childList.add(new Child("Amazon", AppConstants.AMAZON, R.drawable.ic_amazon));
        childList.add(new Child("Amazoncom", AppConstants.AMAZONCOM,R.drawable.ic_amazon));
        childList.add(new Child("Flipkart", AppConstants.FLIPCART, R.drawable.ic_flipcart));
        childList.add(new Child("Myntra", AppConstants.MYNTRA, R.drawable.ic_myntra));
        childList.add(new Child("Snapdeal", AppConstants.SNAPDEAL, R.drawable.ic_snapdeal));
        childList.add(new Child("Nykaa", AppConstants.NYKAA, R.drawable.ic_nykaa));
        childList.add(new Child("Bigbasket", AppConstants.BIGBASKET, R.drawable.ic_bb));
        childList.add(new Child("Grofers", AppConstants.GROFERS, R.drawable.ic_grofers));
        childList.add(new Child("Healthkart", AppConstants.HEALTHKART, R.drawable.ic_healthkart));
        childList.add(new Child("Netmeds", AppConstants.NETMEDS, R.drawable.ic_nexexpress));
        childList.add(new Child("1mg", AppConstants.MG, R.drawable.ic_1mg));
        childList.add(new Child("Alibaba",AppConstants.AliBABA,R.drawable.ic_alibabaa));
        childList.add(new Child("Aliexpress",AppConstants.ALIEXPRESS,R.drawable.ic_aliexpresso));
        sections.add(new SectionHeader(childList, "Shopping"));
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(AppsActivity.this));
        adapterRecycler = new AdapterSectionRecycler(this, sections);
        recyclerView.setAdapter(adapterRecycler);



    }

    private void initView() {
        mFbTv = findViewById(R.id.tv_fb);
        mFbSwitch = findViewById(R.id.fbSwitch);
        mFbSwitch.setOnClickListener(this);
        mFbLin = findViewById(R.id.lin_fb);
        mFbLin.setOnClickListener(this);
        mAppsArrow = findViewById(R.id.arrow_apps);
        mInstaTv = findViewById(R.id.tv_insta);
        mInstaSwitch = findViewById(R.id.instaSwitch);
        mInstaSwitch.setOnClickListener(this);
        mInstaLin = findViewById(R.id.lin_insta);
        mInstaLin.setOnClickListener(this);
        mPinTv = findViewById(R.id.tv_pin);
        mPinSwitch = findViewById(R.id.pinSwitch);
        mPinSwitch.setOnClickListener(this);
        mPinLin = findViewById(R.id.lin_pin);
        mPinLin.setOnClickListener(this);
        mTwitTv = findViewById(R.id.tv_twit);
        mTwitSwitch = findViewById(R.id.twitSwitch);
        mTwitSwitch.setOnClickListener(this);
        mTwitLin = findViewById(R.id.lin_twit);
        mTwitLin.setOnClickListener(this);
        mSkypeTv = findViewById(R.id.tv_skype);
        mSkypeSwitch = findViewById(R.id.skypeSwitch);
        mSkypeSwitch.setOnClickListener(this);
        mSkypeLin = findViewById(R.id.lin_skype);
        mSkypeLin.setOnClickListener(this);
        mSnapchatTv = findViewById(R.id.tv_snapchat);
        mSnapchatSwitch = findViewById(R.id.snapchatSwitch);
        mSnapchatSwitch.setOnClickListener(this);
        mSnapchatLin = findViewById(R.id.lin_snapchat);
        mSnapchatLin.setOnClickListener(this);
    /*    mGplusTv = findViewById(R.id.tv_gplus);
        mGplusSwitch = findViewById(R.id.gplusSwitch);
        mGplusSwitch.setOnClickListener(this);
        mGplusLin = findViewById(R.id.lin_gplus);
        mGplusLin.setOnClickListener(this);
        mTelegramTv = findViewById(R.id.tv_telegram);
        mTelegramSwitch = findViewById(R.id.telegramSwitch);
        mTelegramSwitch.setOnClickListener(this);
        mTelegramLin = findViewById(R.id.lin_telegram);
        mTelegramLin.setOnClickListener(this);*/
        mLinkedinTv = findViewById(R.id.tv_linkedin);
        mLinkedinSwitch = findViewById(R.id.linkedinSwitch);
        mLinkedinSwitch.setOnClickListener(this);
        mLinkedinLin = findViewById(R.id.lin_linkedin);
        mLinkedinLin.setOnClickListener(this);
  /*      mQuoraTv = findViewById(R.id.tv_quora);
        mQuoraSwitch = findViewById(R.id.quoraSwitch);
        mQuoraSwitch.setOnClickListener(this);
        mQuoraLin = findViewById(R.id.lin_quora);
        mQuoraLin.setOnClickListener(this);*/
        /**/
        mMessagnerTv = findViewById(R.id.tv_messanger);
        mMessagnerSwitch = findViewById(R.id.messangerSwitch);
        mMessagnerSwitch.setOnClickListener(this);
        mMessagnerLin = findViewById(R.id.lin_messanger);
        mMessagnerLin.setOnClickListener(this);

        /**/
        /**/
        mYoutubeTv = findViewById(R.id.tv_youtube);
        mYoutubeSwitch = findViewById(R.id.youtubeSwitch);
        mYoutubeSwitch.setOnClickListener(this);
        mYoutubeLin = findViewById(R.id.lin_youtube);
        mYoutubeLin.setOnClickListener(this);
        /**/

        /**/
        mRedditTv = findViewById(R.id.tv_reddit);
        mRedditSwitch = findViewById(R.id.redditSwitch);
        mRedditSwitch.setOnClickListener(this);
        mRedditLin = findViewById(R.id.lin_reddit);
        mRedditLin.setOnClickListener(this);
        /**/

        /**/
        mMediumTv = findViewById(R.id.tv_medium);
        mMediumSwitch = findViewById(R.id.mediumSwitch);
        mMediumSwitch.setOnClickListener(this);
        mMediumLin = findViewById(R.id.lin_medium);
        mMediumLin.setOnClickListener(this);
        /**/

        /**/
        mStumbleuponTv = findViewById(R.id.tv_stumbleupon);
        mStumbleuponSwitch = findViewById(R.id.stumbleuponSwitch);
        mStumbleuponSwitch.setOnClickListener(this);
        mStumbleuponLin = findViewById(R.id.lin_stumbleupon);
        mStumbleuponLin.setOnClickListener(this);
        /**/
        /**/
        mMySpaceTv = findViewById(R.id.tv_mySpace);
        mMySpaceSwitch = findViewById(R.id.mySpaceSwitch);
        mMySpaceSwitch.setOnClickListener(this);
        mMySpaceLin = findViewById(R.id.lin_mySpace);
        mMySpaceLin.setOnClickListener(this);
        /**/

        imghome = findViewById(R.id.imghome);
        imgsetting = findViewById(R.id.imgsetting);
        linearLayout = findViewById(R.id.ll_tab);
        frameLayout = findViewById(R.id.flContent);
        imgappmanage = findViewById(R.id.imgappmanage);
        imghome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // onBackPressed();
                Intent i = new Intent(AppsActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });

        imgsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onBackPressed();
                Intent i = new Intent(AppsActivity.this, SettingActivity.class);
                startActivity(i);
            }
        });

       /* sharedObjects = new SharedObjects(this);
        Boolean fbShow = sharedObjects.preferencesEditor.getBoolean(AppConstants.FACEBOOK);
        Boolean instaShow = sharedObjects.preferencesEditor.getBoolean(AppConstants.INSTAGRAM);
        Boolean pinterestShow = sharedObjects.preferencesEditor.getBoolean(AppConstants.PINTEREST);
        Boolean twitterShow = sharedObjects.preferencesEditor.getBoolean(AppConstants.TWITTER);
      //  Boolean skypeShow = sharedObjects.preferencesEditor.getBoolean(AppConstants.SKYPE);
        Boolean snapchatShow = sharedObjects.preferencesEditor.getBoolean(AppConstants.SNAPCHAT);
        Boolean gplusShow = sharedObjects.preferencesEditor.getBoolean(AppConstants.GPLUS);
        Boolean telegramShow = sharedObjects.preferencesEditor.getBoolean(AppConstants.TELEGRAM);
        Boolean linkedinShow = sharedObjects.preferencesEditor.getBoolean(AppConstants.LINKEDIN);
        Boolean quoraShow = sharedObjects.preferencesEditor.getBoolean(AppConstants.QUORA);


        mFbSwitch.setChecked(fbShow);
        mInstaSwitch.setChecked(instaShow);
        mPinSwitch.setChecked(pinterestShow);
        mTwitSwitch.setChecked(twitterShow);
      //  mSkypeSwitch.setChecked(skypeShow);
        mSnapchatSwitch.setChecked(snapchatShow);
        mGplusSwitch.setChecked(gplusShow);
        mTelegramSwitch.setChecked(telegramShow);
        mLinkedinSwitch.setChecked(linkedinShow);
        mQuoraSwitch.setChecked(quoraShow);*/


       /* mFbSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // do something when check is selected
                    sharedObjects.preferencesEditor.setBoolean(AppConstants.FACEBOOK, true);
                } else {
                    //do something when unchecked
                    sharedObjects.preferencesEditor.setBoolean(AppConstants.FACEBOOK, false);
                }
            }
        });


        mInstaSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // do something when check is selected
                    sharedObjects.preferencesEditor.setBoolean(AppConstants.INSTAGRAM, true);
                } else {
                    //do something when unchecked
                    sharedObjects.preferencesEditor.setBoolean(AppConstants.INSTAGRAM, false);
                }
            }
        });

        mPinSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // do something when check is selected
                    sharedObjects.preferencesEditor.setBoolean(AppConstants.PINTEREST, true);
                } else {
                    //do something when unchecked
                    sharedObjects.preferencesEditor.setBoolean(AppConstants.PINTEREST, false);
                }
            }
        });

        mTwitSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // do something when check is selected
                    sharedObjects.preferencesEditor.setBoolean(AppConstants.TWITTER, true);
                } else {
                    //do something when unchecked
                    sharedObjects.preferencesEditor.setBoolean(AppConstants.TWITTER, false);
                }
            }
        });



        mSnapchatSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // do something when check is selected
                    sharedObjects.preferencesEditor.setBoolean(AppConstants.SNAPCHAT, true);
                } else {
                    //do something when unchecked
                    sharedObjects.preferencesEditor.setBoolean(AppConstants.SNAPCHAT, false);
                }
            }
        });


        mGplusSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // do something when check is selected
                    sharedObjects.preferencesEditor.setBoolean(AppConstants.GPLUS, true);
                } else {
                    //do something when unchecked
                    sharedObjects.preferencesEditor.setBoolean(AppConstants.GPLUS, false);
                }
            }
        });

        mTelegramSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // do something when check is selected
                    sharedObjects.preferencesEditor.setBoolean(AppConstants.TELEGRAM, true);
                } else {
                    //do something when unchecked
                    sharedObjects.preferencesEditor.setBoolean(AppConstants.TELEGRAM, false);
                }
            }
        });

        mLinkedinSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // do something when check is selected
                    sharedObjects.preferencesEditor.setBoolean(AppConstants.LINKEDIN, true);
                } else {
                    //do something when unchecked
                    sharedObjects.preferencesEditor.setBoolean(AppConstants.LINKEDIN, false);
                }
            }
        });

        mQuoraSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // do something when check is selected
                    sharedObjects.preferencesEditor.setBoolean(AppConstants.QUORA, true);
                } else {
                    //do something when unchecked
                    sharedObjects.preferencesEditor.setBoolean(AppConstants.QUORA, false);
                }
            }
        });*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
           /* case R.id.fbSwitch:
                // TODO 18/04/30

                break;
            case R.id.lin_fb:
                // TODO 18/04/30
                if (mFbSwitch.isChecked()) {
                    mFbSwitch.setChecked(false);
                } else {
                    mFbSwitch.setChecked(true);
                }
                break;
            case R.id.instaSwitch:
                // TODO 18/04/30
                break;
            case R.id.lin_insta:
                // TODO 18/04/30
                if (mInstaSwitch.isChecked()) {
                    mInstaSwitch.setChecked(false);
                } else {
                    mInstaSwitch.setChecked(true);
                }
                break;
            case R.id.pinSwitch:
                // TODO 18/04/30
                break;
            case R.id.lin_pin:
                // TODO 18/04/30
                if (mPinSwitch.isChecked()) {
                    mPinSwitch.setChecked(false);
                } else {
                    mPinSwitch.setChecked(true);
                }
                break;
            case R.id.twitSwitch:
                // TODO 18/04/30
                break;
            case R.id.lin_twit:
                // TODO 18/04/30
                if (mTwitSwitch.isChecked()) {
                    mTwitSwitch.setChecked(false);
                } else {
                    mTwitSwitch.setChecked(true);
                }
                break;
            case R.id.skypeSwitch:
                // TODO 18/04/30
                break;
            case R.id.lin_skype:
                // TODO 18/04/30
                if (mSkypeSwitch.isChecked()) {
                    mSkypeSwitch.setChecked(false);
                } else {
                    mSkypeSwitch.setChecked(true);
                }
                break;
            case R.id.snapchatSwitch:
                // TODO 18/04/30
                break;
            case R.id.lin_snapchat:
                // TODO 18/04/30
                if (mSnapchatSwitch.isChecked()) {
                    mSnapchatSwitch.setChecked(false);
                } else {
                    mSnapchatSwitch.setChecked(true);
                }
                break;
            case R.id.gplusSwitch:
                // TODO 18/04/30
                break;
            case R.id.lin_gplus:
                // TODO 18/04/30
                if (mGplusSwitch.isChecked()) {
                    mGplusSwitch.setChecked(false);
                } else {
                    mGplusSwitch.setChecked(true);
                }
                break;
            case R.id.telegramSwitch:
                // TODO 18/04/30
                break;
            case R.id.lin_telegram:
                // TODO 18/04/30
                if (mTelegramSwitch.isChecked()) {
                    mTelegramSwitch.setChecked(false);
                } else {
                    mTelegramSwitch.setChecked(true);
                }
                break;
            case R.id.linkedinSwitch:
                // TODO 18/04/30
                break;
            case R.id.lin_linkedin:
                // TODO 18/04/30
                if (mLinkedinSwitch.isChecked()) {
                    mLinkedinSwitch.setChecked(false);
                } else {
                    mLinkedinSwitch.setChecked(true);
                }
                break;
            case R.id.quoraSwitch:
                // TODO 18/04/30
                break;
            case R.id.lin_quora:
                // TODO 18/04/30
                if (mQuoraSwitch.isChecked()) {
                    mQuoraSwitch.setChecked(false);
                } else {
                    mQuoraSwitch.setChecked(true);
                }
                break;
            default:
                break;*/
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
