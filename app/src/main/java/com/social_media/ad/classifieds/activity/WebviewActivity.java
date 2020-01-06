package com.social_media.ad.classifieds.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.GeolocationPermissions;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.social_media.ad.classifieds.R;
import com.social_media.ad.classifieds.activitystart.activitys.MainActivity;
import com.social_media.ad.classifieds.settings.SettingActivity;
import com.social_media.ad.classifieds.utils.SharedObjects;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

public class WebviewActivity extends AppCompatActivity {
    SharedObjects sharedObjects;
    public static WebView webView;
    private String comeFrom;
    private static final String TAG = HomeActivity.class.getSimpleName();
    private String mCM;
    private ValueCallback<Uri> mUM;
    private ValueCallback<Uri[]> mUMA;
    private final static int FCR = 1;
    ProgressBar progressBar;
    ImageView imghome, imgsetting, gohome;
    LinearLayout linearLayout;
    FrameLayout frameLayout;
    TextView imgappmanage;
    private AdView mAdView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar loadingPageProgress;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (Build.VERSION.SDK_INT >= 21) {
            Uri[] results = null;
            //Check if response is positive
            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == FCR) {
                    if (null == mUMA) {
                        return;
                    }
                    if (intent == null || intent.getData() == null) {
                        //Capture Photo if no image available
                        if (mCM != null) {
                            results = new Uri[]{Uri.parse(mCM)};
                        }
                    } else {
                        String dataString = intent.getDataString();
                        if (dataString != null) {
                            results = new Uri[]{Uri.parse(dataString)};
                        }
                    }
                }
            }
            mUMA.onReceiveValue(results);
            mUMA = null;
        } else {
            if (requestCode == FCR) {
                if (null == mUM) return;
                Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
                mUM.onReceiveValue(result);
                mUM = null;
            }
        }

    }

    @SuppressLint({"SetJavaScriptEnabled", "WrongViewCast", "ClickableViewAccessibility", "ResourceAsColor"})

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        imghome = findViewById(R.id.imghome);
        imgsetting = findViewById(R.id.imgsetting);
        gohome = findViewById(R.id.gohome);
        linearLayout = findViewById(R.id.ll_tab);
        frameLayout = findViewById(R.id.flContent);
        loadingPageProgress = findViewById(R.id.loadingPageProgress);
        loadingPageProgress.setVisibility(View.GONE);
        loadingPageProgress.setMax(100);
        loadingPageProgress.setProgress(1);
        imgappmanage = findViewById(R.id.imgappmanage);
        mAdView = findViewById(R.id.adView1);
        LoadBanner();
        gohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WebviewActivity.this, HomeActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addCategory(Intent.CATEGORY_HOME);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();


            }
        });
        imghome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WebviewActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });
        imgsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WebviewActivity.this, SettingActivity.class);
                startActivity(i);
            }
        });

        sharedObjects = new SharedObjects(this);
        Boolean textbig = sharedObjects.preferencesEditor.getBoolean("big");
        Boolean textsmall = sharedObjects.preferencesEditor.getBoolean("small");
        Boolean textreguler = sharedObjects.preferencesEditor.getBoolean("reguler");
        Boolean textexlarge = sharedObjects.preferencesEditor.getBoolean("exlarge");
        Boolean textexsmall = sharedObjects.preferencesEditor.getBoolean("exsmall");
        Boolean enablelocation = sharedObjects.preferencesEditor.getBoolean("LOCATION");
        Boolean saveData = sharedObjects.preferencesEditor.getBooleanRadio("saveData");

        webView = (WebView) findViewById(R.id.webview);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.isShown();
        webView.setKeepScreenOn(true);
        webView.setWebChromeClient(new GeoWebChromeClient());
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                loadingPageProgress.setProgress(newProgress);
            }

        });
        webView.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Android 4.4.4; One Build/KTU84L.H4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.135 Mobile Safari/537.36");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        //webSettings.setLoadWithOverviewMode(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setSupportZoom(true);
        webView.getSettings().setSupportMultipleWindows(true);
        webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDatabasePath("data/data/com.exempt/databases");
        webSettings.setUserAgentString(webSettings.getUserAgentString() + " (XY ClientApp)");
        webSettings.setSavePassword(false);
        webSettings.setSupportMultipleWindows(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAppCacheMaxSize(5 * 1024 * 1024);
        webSettings.setAllowFileAccessFromFileURLs(true);
        //webSettings.setUseWideViewPort(true);
        //webSettings.setLoadWithOverviewMode(true);
        webSettings.setBuiltInZoomControls(false);
        CookieManager.getInstance().setAcceptCookie(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }


        if (saveData) {
            webView.getSettings().setLoadsImagesAutomatically(false);
        } else {
            //webView.getSettings().setLoadsImagesAutomatically(true);
        }

        if (enablelocation) {
            webSettings.setGeolocationEnabled(true);
        } else {
            webSettings.setGeolocationEnabled(false);
        }

        if (textreguler == true) {
            webSettings.setTextSize(WebSettings.TextSize.NORMAL);
            //webSettings.setTextZoom(150);
        } else if (textsmall == true) {
            webSettings.setTextSize(WebSettings.TextSize.SMALLER);
        } else if (textbig == true) {
            webSettings.setTextSize(WebSettings.TextSize.LARGER);
        } else if (textexlarge == true) {
            webSettings.setTextSize(WebSettings.TextSize.LARGEST);
        } else if (textexsmall == true) {
            webSettings.setTextSize(WebSettings.TextSize.SMALLEST);
        } else {
            webSettings.setTextSize(WebSettings.TextSize.NORMAL);
        }
        /*if (Build.VERSION.SDK_INT >= 21) {
            webSettings.setMixedContentMode(0);
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else if (Build.VERSION.SDK_INT >= 19) {
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else if (Build.VERSION.SDK_INT < 19) {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }*/

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.google.com"));
                Bundle b = new Bundle();
                b.putBoolean("new_window", true); //sets new window
                intent.putExtras(b);
                startActivity(intent);
                return true;
               /* if((String.valueOf(request.getUrl())).contains("https://")) {
                    view.loadUrl(String.valueOf(request.getUrl()));


                } else {
                    Intent intent = new Intent(Intent.ACTION_VIEW, request.getUrl());
                    view.getContext().startActivity(intent);
                }

                return true;*/
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
                loadingPageProgress.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                final Handler h = new Handler() {
                    @Override
                    public void handleMessage(Message message) {
                        progressBar.setVisibility(View.GONE);
                        loadingPageProgress.setVisibility(View.GONE);
                    }
                };
                h.sendMessageDelayed(new Message(), 1000);

                swipeRefreshLayout.setRefreshing(false);
                view.setVisibility(View.VISIBLE);

            }
        });

        final Intent intent = getIntent();
        comeFrom = intent.getStringExtra("comeFrom");
        if (comeFrom !=null)
        {
        switch (comeFrom) {

            case "Whatsapp":
           Intent intents = new Intent(this, MainActivity.class);
                startActivity(intents);
                break;
            case "Facebook":
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setStatusBarColor(Color.parseColor("#f14357af"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#3D4FA0"));
                imgappmanage.setText("Facebook");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://m.facebook.com");
                break;
            case "Instagram":
                Window window1 = getWindow();
                window1.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window1.setStatusBarColor(Color.parseColor("#f53c8e"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#DD3680"));
                imgappmanage.setText("Instagram");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://www.instagram.com/");
                break;
            case "Pinterest":
                Window window2 = getWindow();
                window2.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window2.setStatusBarColor(Color.parseColor("#961417"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#c6181c"));
                imgappmanage.setText("Pinterest");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://www.pinterest.com/");

                break;
            case "Twitter":
                Window window3 = getWindow();
                window3.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window3.setStatusBarColor(Color.parseColor("#58a7e3"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#189AF1"));
                imgappmanage.setText("Twitter");
                webView = null;
                webView = findViewById(R.id.webview);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://www.twitter.com/");
                break;
            case "Tumblr":
                Window window4 = getWindow();
                window4.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window4.setStatusBarColor(Color.parseColor("#4c769e"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#3A5875"));
                imgappmanage.setText("Tumblr");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://www.tumblr.com/");
                break;
            case "Snapchat":
                Window window5 = getWindow();
                window5.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window5.setStatusBarColor(Color.parseColor("#f1cb49"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#edcf66"));
                imgappmanage.setText("Snapchat");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://accounts.snapchat.com/");
                break;

            case "Linkedin":
                Window window8 = getWindow();
                window8.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window8.setStatusBarColor(Color.parseColor("#1b9be8"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#0f72b5"));
                imgappmanage.setText("Linkedin");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://www.linkedin.com/");
                break;
            case "Tiktok":
                Window window10 = getWindow();
                window10.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window10.setStatusBarColor(Color.parseColor("#000000"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#FC0429"));
                imgappmanage.setText("Tiktok");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://www.tiktok.com/trending");
                break;

            case "Likee":
                Window window11 = getWindow();
                window11.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window11.setStatusBarColor(Color.parseColor("#5739AF"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#381B8B"));
                imgappmanage.setText("Likee");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://likee.video/main/m_trending");
                break;

            case "OLX":
                Window window12 = getWindow();
                window12.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window12.setStatusBarColor(Color.parseColor("#3F3D3D"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#1B1B1B"));
                imgappmanage.setText("OLX");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://www.olx.in");
                break;
            case "OOrgin":
                Window window13 = getWindow();
                window13.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window13.setStatusBarColor(Color.parseColor("#3F3D3D"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#1B1B1B"));
                imgappmanage.setText("OOrgin");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://in.oorgin.com");
                break;
            case "Locanto":
                Window window14 = getWindow();
                window14.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window14.setStatusBarColor(Color.parseColor("#154679"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#0A2C50"));
                imgappmanage.setText("Locanto");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://m.locanto.com");
                break;
            case "Click":
                Window window15 = getWindow();
                window15.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window15.setStatusBarColor(Color.parseColor("#C5512F"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#DA3B0D"));
                imgappmanage.setText("Click");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://www.click.in");
                break;
            case "Clickindia":
                Window window16 = getWindow();
                window16.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window16.setStatusBarColor(Color.parseColor("#C5512F"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#DA3B0D"));
                imgappmanage.setText("Clickindia");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://wap.clickindia.com");
                break;
            case "Magicbricks":
                Window window17 = getWindow();
                window17.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window17.setStatusBarColor(Color.parseColor("#E72328"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#D3080E"));
                imgappmanage.setText("Magicbricks");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://www.magicbricks.com");
                break;
            case "99Acres":
                Window window18 = getWindow();
                window18.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window18.setStatusBarColor(Color.parseColor("#f14357af"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#3D4FA0"));
                imgappmanage.setText("99Acres");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://www.99acres.com");
                break;
            case "Indeed":
                Window window19 = getWindow();
                window19.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window19.setStatusBarColor(Color.parseColor("#f14357af"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#3D4FA0"));
                imgappmanage.setText("Indeed");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://www.indeed.co.in/m/");
                break;
            case "Amazon":
                Window window20 = getWindow();
                window20.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window20.setStatusBarColor(Color.parseColor("#222121"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#0F0E0E"));
                imgappmanage.setText("Amazon");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://www.amazon.in");
                break;
            case "Flipcart":
                Window window21 = getWindow();
                window21.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window21.setStatusBarColor(Color.parseColor("#0573A7"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#1E7EAC"));
                imgappmanage.setText("Flipcart");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://www.flipkart.com/");
                break;
            case "Myntra":
                Window window22 = getWindow();
                window22.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window22.setStatusBarColor(Color.parseColor("#333435"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#686B6F"));
                imgappmanage.setText("Myntra");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://www.myntra.com");
                break;
            case "Snapdeal":
                Window window23 = getWindow();
                window23.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window23.setStatusBarColor(Color.parseColor("#E62B6A"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#EE3875"));
                imgappmanage.setText("Snapdeal");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://m.snapdeal.com/");
                break;
            case "Nykaa":
                Window window24 = getWindow();
                window24.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window24.setStatusBarColor(Color.parseColor("#F05489"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#D5336A"));
                imgappmanage.setText("Nykaa");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://www.nykaa.com");
                break;
            case "Bigbasket":
                Window window25 = getWindow();
                window25.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window25.setStatusBarColor(Color.parseColor("#e65d38"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#d4380e"));
                imgappmanage.setText("Bigbasket");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://www.bigbasket.com");
                break;
            case "Amazoncom":
                Window window37 = getWindow();
                window37.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window37.setStatusBarColor(Color.parseColor("#222121"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#0F0E0E"));
                imgappmanage.setText("Amazoncom");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://www.amazon.com");
                break;
            case "Alibaba":
                Window window38 = getWindow();
                window38.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window38.setStatusBarColor(Color.parseColor("#222121"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#0F0E0E"));
                imgappmanage.setText("Alibaba");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://m.alibaba.com");
                break;
            case "Aliexpress":
                Window window39 = getWindow();
                window39.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window39.setStatusBarColor(Color.parseColor("#222121"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#0F0E0E"));
                imgappmanage.setText("Aliexpress");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://m.aliexpress.com");
                break;
            case "Grofers":
                Window window26 = getWindow();
                window26.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window26.setStatusBarColor(Color.parseColor("#89EC42"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#6FCE2C"));
                imgappmanage.setText("Grofers");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://grofers.com");
                break;
            case "Healthkart":
                Window window27 = getWindow();
                window27.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window27.setStatusBarColor(Color.parseColor("#16D7F0"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#30C8DB"));
                imgappmanage.setText("Healthkart");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://www.healthkart.com");
                break;
            case "Netmeds":
                Window window28 = getWindow();
                window28.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window28.setStatusBarColor(Color.parseColor("#094E57"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#053A41"));
                imgappmanage.setText("Netmeds");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://m.netmeds.com");
                break;
            case "1Mg":
                Window window29 = getWindow();
                window29.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window29.setStatusBarColor(Color.parseColor("#04A6F0"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#32AEE7"));
                imgappmanage.setText("1Mg");
                webView.loadUrl("https://www.1mg.com");
                break;
            case "Messanger":
                Window window36 = getWindow();
                window36.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window36.setStatusBarColor(Color.parseColor("#04A6F0"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#32AEE7"));
                imgappmanage.setText("Messenger");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://m.facebook.com/messages");
                break;
            case "Quikr":
                Window window30 = getWindow();
                window30.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window30.setStatusBarColor(Color.parseColor("#04A6F0"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#32AEE7"));
                imgappmanage.setText("Quikr");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://www.quikr.com");
                break;
            case "Youtube":
                Window window31= getWindow();
                window31.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window31.setStatusBarColor(Color.parseColor("#04A6F0"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#32AEE7"));
                imgappmanage.setText("Youtube");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://www.youtube.com");
                break;
            case "Reddit":
                Window window32= getWindow();
                window32.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window32.setStatusBarColor(Color.parseColor("#04A6F0"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#32AEE7"));
                imgappmanage.setText("Reddit");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://www.reddit.com");
                break;
            case "Medium":
                Window window33= getWindow();
                window33.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window33.setStatusBarColor(Color.parseColor("#04A6F0"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#32AEE7"));
                imgappmanage.setText("Medium");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://medium.com");
                break;
            case "Stumbleupon":
                Window window34= getWindow();
                window34.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window34.setStatusBarColor(Color.parseColor("#04A6F0"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#32AEE7"));
                imgappmanage.setText("Stumbleupon");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://www.stumbleupon.com");
                break;
            case "MySpace":
                Window window35= getWindow();
                window35.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window35.setStatusBarColor(Color.parseColor("#04A6F0"));
                }
                linearLayout.setBackgroundColor(Color.parseColor("#32AEE7"));
                imgappmanage.setText("MySpace");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://www.myspace.com/");
                break;
        }
        }
        webView.setWebChromeClient(new WebChromeClient() {
            //For Android 3.0+
            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                mUM = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("*/*");
                WebviewActivity.this.startActivityForResult(Intent.createChooser(i, "File Chooser"), FCR);
            }

            // For Android 3.0+, above method not supported in some android 3+ versions, in such case we use this
            public void openFileChooser(ValueCallback uploadMsg, String acceptType) {
                mUM = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("*/*");
                WebviewActivity.this.startActivityForResult(
                        Intent.createChooser(i, "File Browser"),
                        FCR);

            }

            //For Android 4.1+
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                mUM = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("*/*");
                WebviewActivity.this.startActivityForResult(Intent.createChooser(i, "File Chooser"), WebviewActivity.FCR);
            }

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                super.onGeolocationPermissionsShowPrompt(origin, callback);
                callback.invoke(origin, true, false);
            }

            //For Android 5.0+
            public boolean onShowFileChooser(
                    WebView webView, ValueCallback<Uri[]> filePathCallback,
                    FileChooserParams fileChooserParams) {
                if (mUMA != null) {
                    mUMA.onReceiveValue(null);
                }
                mUMA = filePathCallback;
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(WebviewActivity.this.getPackageManager()) != null) {
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                        takePictureIntent.putExtra("PhotoPath", mCM);
                    } catch (IOException ex) {
                        Log.e(TAG, "Image file creation failed", ex);
                    }
                    if (photoFile != null) {
                        mCM = "file:" + photoFile.getAbsolutePath();
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                    } else {
                        takePictureIntent = null;
                    }
                }
                Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
                contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
                contentSelectionIntent.setType("*/*");
                Intent[] intentArray;
                if (takePictureIntent != null) {
                    intentArray = new Intent[]{takePictureIntent};
                } else {
                    intentArray = new Intent[0];
                }
                Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
                chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
                chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
                startActivityForResult(chooserIntent, FCR);
                return true;
            }
        });


        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.reload();
            }
        });
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);
                                        webView.reload();

                                    }
                                }
        );
    }

    private void LoadBanner() {

        MobileAds.initialize(WebviewActivity.this, getString(R.string.test_admob_app_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mAdView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(WebviewActivity.this, "onAdFailedToLoad : " + errorCode, Toast.LENGTH_SHORT).show();
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
    }

    public class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.e("jjjjj", webView.getUrl());
            //view.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);

            /*if (url.startsWith("http:") || url.startsWith("https:")) {
                return false;
            }
            return true;*/
            view.loadUrl(url);
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
            swipeRefreshLayout.setRefreshing(false);
            view.setVisibility(View.VISIBLE);
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Toast.makeText(getApplicationContext(), "Failed loading app!", Toast.LENGTH_SHORT).show();
        }
    }

    // Create an image file
    private File createImageFile() throws IOException {
        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "img_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        progressBar.setVisibility(View.GONE);
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        HomeActivity.materialDesignFAM.close(true);
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public class GeoWebChromeClient extends WebChromeClient {
        @Override
        public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
            callback.invoke(origin, true, false);
        }
    }

}
