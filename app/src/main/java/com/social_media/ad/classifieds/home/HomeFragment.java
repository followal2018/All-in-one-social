package com.social_media.ad.classifieds.home;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.clockbyte.admobadapter.bannerads.AdmobBannerRecyclerAdapterWrapper;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.social_media.ad.classifieds.R;
import com.social_media.ad.classifieds.model.SocialListBean;
import com.social_media.ad.classifieds.utils.AppConstants;
import com.social_media.ad.classifieds.utils.SharedObjects;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    RecyclerView rvMessages;
    AdmobBannerRecyclerAdapterWrapper adapterWrapper;
    List<SocialListBean> socialListBeanList;
    SharedObjects sharedObjects;
    NestedScrollView scrollView;
    InterstitialAd mInterstitialAd;
    RecyclerExampleAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                initRecyclerViewItems();
            }
        });
//        swipeRefreshLayout.post(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        swipeRefreshLayout.setRefreshing(true);
//                                        initRecyclerViewItems();
//
//                                    }
//                                }
//        );

        //  InterstitialAd();
        MobileAds.initialize(getContext(), getString(R.string.test_admob_app_id));
//        scrollView = v.findViewById(R.id.scrollView);
        rvMessages = v.findViewById(R.id.rvMessages);
//        rvMessages.setFocusable(false);
        initRecyclerViewItems();
        /*Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        }, 1000);*/


//        final AdView mAdView = v.findViewById(R.id.adView1);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
//        mAdView.setAdListener(new AdListener() {
//            @Override
//            public void onAdLoaded() {
//                mAdView.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onAdFailedToLoad(int errorCode) {
//            }
//
//            @Override
//            public void onAdOpened() {
//            }
//
//            @Override
//            public void onAdLeftApplication() {
//            }
//
//            @Override
//            public void onAdClosed() {
//            }
//        });
//        InterstitialAd();
        return v;
    }

    public void InterstitialAd() {
        mInterstitialAd = new InterstitialAd(getActivity());

        // set the ad unit ID
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        AdRequest adRequest = new AdRequest.Builder()
                .build();

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {

//                counterForInterstitialAds();
            }

            @Override
            public void onAdClosed() {
                InterstitialAd();
                super.onAdClosed();
            }
        });
    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }


    }

    private void initRecyclerViewItems() {
        sharedObjects = new SharedObjects(getActivity());
        socialListBeanList = new ArrayList<>();
        rvMessages.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecyclerExampleAdapter(getActivity());
        String[] testDevicesIds = new String[]{AdRequest.DEVICE_ID_EMULATOR};
//

        try {
            adapterWrapper = AdmobBannerRecyclerAdapterWrapper.builder(getActivity())
                    .setLimitOfAds(20)
                    .setFirstAdIndex(6)
                    .setNoOfDataBetweenAds(6)
                    .setAdapter(adapter)
                    .setSingleAdUnitId(getString(R.string.app_id))
                    /*.setAdViewWrappingStrategy(new BannerAdViewWrappingStrategyBase() {
                        @NonNull
                        @Override
                        protected ViewGroup getAdViewWrapper(ViewGroup parent) {
                            return (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.native_express_ad_container,
                                    parent, false);
                        }

                        @Override
                        protected void recycleAdViewWrapper(@NonNull ViewGroup wrapper, @NonNull AdView ad) {
                            ViewGroup container = (ViewGroup) wrapper.findViewById(R.id.ad_container);
                            for (int i = 0; i < container.getChildCount(); i++) {
                                View v = container.getChildAt(i);
                                if (v instanceof AdView) {
                                    container.removeViewAt(i);

                                    rvMessages.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {

                                            try {
                                                adapter.notifyDataSetChanged();
                                            } catch (Exception ex) {

                                            }

                                        }
                                    }, 1000);


                                    break;
                                }
                            }

                            Log.d("AdView", adapterWrapper.getItemCount() + " recycleAdViewWrapper");
                        }

                        @Override
                        protected void addAdViewToWrapper(@NonNull ViewGroup wrapper, @NonNull AdView ad) {
                            ViewGroup container = (ViewGroup) wrapper.findViewById(R.id.ad_container);
                            container.addView(ad);

                            Log.d("AdView", adapterWrapper.getItemCount() + " addAdViewToWrapper");
                        }
                    })*/
                    .build();

            rvMessages.setAdapter(adapterWrapper);
        } catch (Exception e) {

        }
//        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);
//        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                if (adapterWrapper.getItemViewType(position) == adapterWrapper.getViewTypeAdBanner())
//                    return 3;
//                else return 1;
//            }
//        });

        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                //set span 2 for ad block, otherwise 1
                if(adapterWrapper.getItemViewType(position) == adapterWrapper.getViewTypeAdBanner())
                    return 3;
                else return 1;
            }
        });

        
//        rvMessages.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        rvMessages.setLayoutManager(mLayoutManager);
        Boolean whatShow = sharedObjects.preferencesEditor.getBoolean(AppConstants.WHATSAPP);
        Boolean fbShow = sharedObjects.preferencesEditor.getBoolean(AppConstants.FACEBOOK);
        Boolean instaShow = sharedObjects.preferencesEditor.getBoolean(AppConstants.INSTAGRAM);
        Boolean pinterestShow = sharedObjects.preferencesEditor.getBoolean(AppConstants.PINTEREST);
        Boolean twitterShow = sharedObjects.preferencesEditor.getBoolean(AppConstants.TWITTER);
        Boolean tumblr = sharedObjects.preferencesEditor.getBoolean(AppConstants.TUMBLR);
        Boolean snapchatShow = sharedObjects.preferencesEditor.getBoolean(AppConstants.SNAPCHAT);
        Boolean linkedinShow = sharedObjects.preferencesEditor.getBoolean(AppConstants.LINKEDIN);
        Boolean quikr = sharedObjects.preferencesEditor.getBoolean(AppConstants.QUIKR);
        Boolean tiktok = sharedObjects.preferencesEditor.getBoolean(AppConstants.TIKTOK);
        Boolean likee = sharedObjects.preferencesEditor.getBoolean(AppConstants.LIKEE);
        Boolean olx = sharedObjects.preferencesEditor.getBoolean(AppConstants.OLX);
        Boolean oorgin = sharedObjects.preferencesEditor.getBoolean(AppConstants.OORGIN);
        Boolean locanto = sharedObjects.preferencesEditor.getBoolean(AppConstants.LOCANTO);
        Boolean click = sharedObjects.preferencesEditor.getBoolean(AppConstants.CLICK);
        Boolean clickindia = sharedObjects.preferencesEditor.getBoolean(AppConstants.CLICKINDIA);
        Boolean magicbricks = sharedObjects.preferencesEditor.getBoolean(AppConstants.MAGICBRICKS);
        Boolean acres = sharedObjects.preferencesEditor.getBoolean(AppConstants.ACRES);
        Boolean indeed = sharedObjects.preferencesEditor.getBoolean(AppConstants.INDEED);
        Boolean amazon = sharedObjects.preferencesEditor.getBoolean(AppConstants.AMAZON);
        Boolean flipcart = sharedObjects.preferencesEditor.getBoolean(AppConstants.FLIPCART);
        Boolean myntra = sharedObjects.preferencesEditor.getBoolean(AppConstants.MYNTRA);
        Boolean snapdeal = sharedObjects.preferencesEditor.getBoolean(AppConstants.SNAPDEAL);
        Boolean nykaa = sharedObjects.preferencesEditor.getBoolean(AppConstants.NYKAA);
        Boolean bigbasket = sharedObjects.preferencesEditor.getBoolean(AppConstants.BIGBASKET);
        Boolean grofers = sharedObjects.preferencesEditor.getBoolean(AppConstants.GROFERS);
        Boolean healthkart = sharedObjects.preferencesEditor.getBoolean(AppConstants.HEALTHKART);
        Boolean netmeds = sharedObjects.preferencesEditor.getBoolean(AppConstants.NETMEDS);
        Boolean mg = sharedObjects.preferencesEditor.getBoolean(AppConstants.MG);
        Boolean messanger = sharedObjects.preferencesEditor.getBoolean(AppConstants.MESSANGER);
        Boolean reddit = sharedObjects.preferencesEditor.getBoolean(AppConstants.REDDIT);
        Boolean youtube = sharedObjects.preferencesEditor.getBoolean(AppConstants.YOUTUBE);
        boolean medium = sharedObjects.preferencesEditor.getBoolean(AppConstants.MEDIUM);
        boolean stumbleupon = sharedObjects.preferencesEditor.getBoolean(AppConstants.STUMBLEUPON);
        boolean myspace = sharedObjects.preferencesEditor.getBoolean(AppConstants.MYSPACE);
        boolean alibaba = sharedObjects.preferencesEditor.getBoolean(AppConstants.AliBABA);
        boolean aliexpress = sharedObjects.preferencesEditor.getBoolean(AppConstants.ALIEXPRESS);
        boolean amazoncom = sharedObjects.preferencesEditor.getBoolean(AppConstants.AMAZONCOM);
        if (whatShow)
            socialListBeanList.add(new SocialListBean("Whatsapp Status", R.drawable.bg_whatsapp, whatShow, R.drawable.ic_logo_whatsapp));
        if (fbShow)
            socialListBeanList.add(new SocialListBean("Facebook", R.drawable.bg_fb, fbShow, R.drawable.ic_fbicon));
        if (messanger)
            socialListBeanList.add(new SocialListBean("Messanger", R.drawable.bg_messanger, messanger, R.drawable.ic_messenger));
        if (youtube)
            socialListBeanList.add(new SocialListBean("Youtube", R.drawable.bg_youtube, youtube, R.drawable.ic_youtube));
        if (instaShow)
            socialListBeanList.add(new SocialListBean("Instagram", R.drawable.bg_insta, instaShow, R.drawable.ic_instaicon));
        if (pinterestShow)
            socialListBeanList.add(new SocialListBean("Pinterest", R.drawable.bg_pintrest, pinterestShow, R.drawable.ic_pinticon));
        if (twitterShow)
            socialListBeanList.add(new SocialListBean("Twitter", R.drawable.bg_twit, twitterShow, R.drawable.ic_twiticon));
        if (tumblr)
            socialListBeanList.add(new SocialListBean("Tumblr", R.drawable.bg_tumblr, tumblr, R.drawable.ic_tumblr));
        if (snapchatShow)
            socialListBeanList.add(new SocialListBean("Snapchat", R.drawable.bg_snapchat, snapchatShow, R.drawable.ic_snapicon));
        if (linkedinShow)
            socialListBeanList.add(new SocialListBean("Linkedin", R.drawable.bg_linkdedin, linkedinShow, R.drawable.ic_linkdicon));
        if (reddit)
            socialListBeanList.add(new SocialListBean("Reddit", R.drawable.bg_reddit, reddit, R.drawable.ic_reddit));
        if (medium)
            socialListBeanList.add(new SocialListBean("Medium", R.drawable.bg_medium, medium, R.drawable.ic_medium));
        if (stumbleupon)
            socialListBeanList.add(new SocialListBean("Stumbleupon", R.drawable.bg_stumbleupon, stumbleupon, R.drawable.ic_stumbleupon));
        if (myspace)
            socialListBeanList.add(new SocialListBean("Myspace", R.drawable.bg_myspace, myspace, R.drawable.ic_myspace));
        if (tiktok)
            socialListBeanList.add(new SocialListBean("Tiktok", R.drawable.bg_tiktok, tiktok, R.drawable.ic_logo_tiktok));
        if (likee)
            socialListBeanList.add(new SocialListBean("Likee", R.drawable.bg_likee, likee, R.drawable.ic_logo_likee));
        if (quikr)
            socialListBeanList.add(new SocialListBean("Quikr", R.drawable.bg_quikr, quikr, R.drawable.ic_logo_quicker));
        if (olx)
            socialListBeanList.add(new SocialListBean("OLX", R.drawable.bg_olx, olx, R.drawable.ic_logo_olx));
        if (oorgin)
            socialListBeanList.add(new SocialListBean("OOrgin", R.drawable.bg_oorigin, oorgin, R.drawable.ic_logo_oorigin));
        if (locanto)
            socialListBeanList.add(new SocialListBean("Locanto", R.drawable.bg_locanto, locanto, R.drawable.ic_logo_locanto));
        if (clickindia)
            socialListBeanList.add(new SocialListBean("Clickindia", R.drawable.bg_clickindia, clickindia, R.drawable.ic_logo_clickindia));
        if (magicbricks)
            socialListBeanList.add(new SocialListBean("Magicbricks", R.drawable.bg_magicbricks, magicbricks, R.drawable.ic_logo_magicbricks));
        if (acres)
            socialListBeanList.add(new SocialListBean("99Acres", R.drawable.bg_linkdedin, acres, R.drawable.ic_99acre_logo));
        if (click)
            socialListBeanList.add(new SocialListBean("Click", R.drawable.bg_click, click, R.drawable.ic_logo_click));
        if (indeed)
            socialListBeanList.add(new SocialListBean("Indeed", R.drawable.bg_fb, indeed, R.drawable.ic_logo_indeed));
        if (amazon)
            socialListBeanList.add(new SocialListBean("Amazon", R.drawable.bg_linkdedin, amazon, R.drawable.ic_logo_amazon));
        if (amazoncom)
            socialListBeanList.add(new SocialListBean("Amazoncom", R.drawable.bg_amazoncom, amazoncom, R.drawable.ic_logo_amazon));
        if (flipcart)
            socialListBeanList.add(new SocialListBean("flipkart", R.drawable.bg_fb, flipcart, R.drawable.ic_logo_flipkart));
        if (myntra)
            socialListBeanList.add(new SocialListBean("Myntra", R.drawable.bg_insta, myntra, R.drawable.ic_logo_myntra));
        if (snapdeal)
            socialListBeanList.add(new SocialListBean("Snapdeal", R.drawable.bg_snapdeal, snapdeal, R.drawable.ic_logo_snapdeal));
        if (nykaa)
            socialListBeanList.add(new SocialListBean("Nykaa", R.drawable.bg_insta, nykaa, R.drawable.ic_logo_nykaa));
        if (bigbasket)
            socialListBeanList.add(new SocialListBean("Bigbasket", R.drawable.bg_bigbasket, bigbasket, R.drawable.ic_logo_bigbasket));
        if (grofers)
            socialListBeanList.add(new SocialListBean("Grofers", R.drawable.bg_grofers, grofers, R.drawable.ic_logo_grofers));
        if (healthkart)
            socialListBeanList.add(new SocialListBean("Healthkart", R.drawable.bg_health, healthkart, R.drawable.ic_logo_healthkart));
        if (netmeds)
            socialListBeanList.add(new SocialListBean("Netmeds", R.drawable.bg_bigbasket, netmeds, R.drawable.ic_logo_netmeds));
        if (mg)
            socialListBeanList.add(new SocialListBean("1Mg", R.drawable.bg_fb, mg, R.drawable.ic_logo_1mg));
        if (alibaba)
            socialListBeanList.add(new SocialListBean("Alibaba", R.drawable.bg_alibaba, alibaba, R.drawable.ic_alibaba));
        if (aliexpress)
            socialListBeanList.add(new SocialListBean("Aliexpress", R.drawable.bg_aliexpress, aliexpress, R.drawable.ic_aliexpress));

        /*    socialListBeanList .clear();*/
        adapter.addAll(socialListBeanList);
        adapter.notifyDataSetChanged();
        if (socialListBeanList.size() == 0) {
            Toast.makeText(getActivity(), "All apps hide", Toast.LENGTH_SHORT).show();
        }
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

//        handler = null;

//        adapterWrapper.release();
    }

    @Override
    public void onPause() {
//        adapterWrapper.pauseAll();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapterWrapper.resumeAll();
//        counterForInterstitialAds();
    }

    private class MyGridLayoutManager extends GridLayoutManager {

        public MyGridLayoutManager(Context ctx, int spanCount) {
            super(ctx, spanCount);
        }

        @Override
        public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
            try {
                super.onLayoutChildren(recycler, state);
            } catch (IndexOutOfBoundsException e) {
                Log.e("Error", "IndexOutOfBoundsException in RecyclerView happens");
            }
        }
    }


//    Handler handler;

//    private void counterForInterstitialAds() {
//
//        if (handler == null) {
//            handler = new Handler();
//        }
//
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                showInterstitial();
//            }
////        },30000);  //-----------30 sec
//        }, 120000);  //-----------2 minutes
//
//
//    }


}
