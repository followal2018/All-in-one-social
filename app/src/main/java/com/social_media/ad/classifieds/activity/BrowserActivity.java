package com.social_media.ad.classifieds.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.social_media.ad.classifieds.R;
import com.social_media.ad.classifieds.activitystart.activitys.MainActivity;
import com.social_media.ad.classifieds.rcprogressbar.RoundCornerProgressBar;

public class BrowserActivity extends AppCompatActivity {

    public static final int REQUEST_SELECT_FILE = 100;
    private static final String TAG = BrowserActivity.class.getSimpleName();
    private final static int FILECHOOSER_RESULTCODE = 1;
    public ValueCallback<Uri[]> uploadMessage;
    WebView webView;
    String url;
    ImageView gohomes;
    private AdView mAdView;
    private RoundCornerProgressBar webPageLoadingProgress;
    private ValueCallback<Uri> mUploadMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        webView = findViewById(R.id.webview);
        gohomes = findViewById(R.id.gohomes);
        mAdView = findViewById(R.id.adViewss);
        webPageLoadingProgress = findViewById(R.id.loadingPageProgress);
        LoadBanner();


        if (getIntent().hasExtra("comeFrom")) {

            String urlString = getIntent().getExtras().getString("comeFrom");
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            //webView.loadUrl(urlString);
            webView.getSettings().setAllowFileAccess(true);
            webView.getSettings().setDomStorageEnabled(true);

            switch (urlString) {

                case "Whatsapp Status":
                    Intent intents = new Intent(this, MainActivity.class);
                    startActivity(intents);
                    break;
                case "Facebook":
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window.setStatusBarColor(Color.parseColor("#f14357af"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#3D4FA0"));
                    //imgappmanage.setText("Facebook");
                    webView.loadUrl("https://m.facebook.com");
                    break;
                case "Instagram":
                    Window window1 = getWindow();
                    window1.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window1.setStatusBarColor(Color.parseColor("#f53c8e"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#DD3680"));
                    //imgappmanage.setText("Instagram");
                    webView.loadUrl("https://www.instagram.com/");
                    break;
                case "Pinterest":
                    Window window2 = getWindow();
                    window2.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window2.setStatusBarColor(Color.parseColor("#961417"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#c6181c"));
                    //imgappmanage.setText("Pinterest");
                    webView.loadUrl("https://www.pinterest.com/");

                    break;
                case "Twitter":
                    Window window3 = getWindow();
                    window3.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window3.setStatusBarColor(Color.parseColor("#58a7e3"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#189AF1"));
                    //imgappmanage.setText("Twitter");
                    webView.loadUrl("https://www.twitter.com/");
                    break;
                case "Tumblr":
                    Window window4 = getWindow();
                    window4.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window4.setStatusBarColor(Color.parseColor("#4c769e"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#3A5875"));
                    //imgappmanage.setText("Tumblr");
                    webView.loadUrl("https://www.tumblr.com/");
                    break;
                case "Snapchat":
                    Window window5 = getWindow();
                    window5.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window5.setStatusBarColor(Color.parseColor("#f1cb49"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#edcf66"));
                    //imgappmanage.setText("Snapchat");
                    webView.loadUrl("https://accounts.snapchat.com/");
                    break;

                case "Linkedin":
                    Window window8 = getWindow();
                    window8.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window8.setStatusBarColor(Color.parseColor("#1b9be8"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#0f72b5"));
                    //imgappmanage.setText("Linkedin");
                    webView.loadUrl("https://www.linkedin.com/");
                    break;
                case "Tiktok":
                    Window window10 = getWindow();
                    window10.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window10.setStatusBarColor(Color.parseColor("#000000"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#FC0429"));
                    //imgappmanage.setText("Tiktok");
                    webView.loadUrl("https://www.tiktok.com/trending");
                    break;

                case "Likee":
                    Window window11 = getWindow();
                    window11.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window11.setStatusBarColor(Color.parseColor("#5739AF"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#381B8B"));
                    //imgappmanage.setText("Likee");
                    webView.loadUrl("https://likee.video/main/m_trending");
                    break;

                case "OLX":
                    Window window12 = getWindow();
                    window12.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window12.setStatusBarColor(Color.parseColor("#3F3D3D"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#1B1B1B"));
                    //imgappmanage.setText("OLX");
                    webView.loadUrl("https://www.olx.in");
                    break;
                case "OOrgin":
                    Window window13 = getWindow();
                    window13.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window13.setStatusBarColor(Color.parseColor("#3F3D3D"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#1B1B1B"));
                    //imgappmanage.setText("OOrgin");
                    webView.loadUrl("https://in.oorgin.com");
                    break;
                case "Locanto":
                    Window window14 = getWindow();
                    window14.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window14.setStatusBarColor(Color.parseColor("#154679"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#0A2C50"));
                    //imgappmanage.setText("Locanto");
                    webView.loadUrl("https://m.locanto.com");
                    break;
                case "Click":
                    Window window15 = getWindow();
                    window15.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window15.setStatusBarColor(Color.parseColor("#C5512F"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#DA3B0D"));
                    //imgappmanage.setText("Click");
                    webView.loadUrl("https://www.click.in");
                    break;
                case "Clickindia":
                    Window window16 = getWindow();
                    window16.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window16.setStatusBarColor(Color.parseColor("#C5512F"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#DA3B0D"));
                    //imgappmanage.setText("Clickindia");
                    webView.loadUrl("https://wap.clickindia.com");
                    break;
                case "Magicbricks":
                    Window window17 = getWindow();
                    window17.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window17.setStatusBarColor(Color.parseColor("#E72328"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#D3080E"));
                    //imgappmanage.setText("Magicbricks");
                    webView.loadUrl("https://www.magicbricks.com");
                    break;
                case "99Acres":
                    Window window18 = getWindow();
                    window18.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window18.setStatusBarColor(Color.parseColor("#f14357af"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#3D4FA0"));
                    //imgappmanage.setText("99Acres");
                    webView.loadUrl("https://www.99acres.com");
                    break;
                case "Indeed":
                    Window window19 = getWindow();
                    window19.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window19.setStatusBarColor(Color.parseColor("#f14357af"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#3D4FA0"));
                    //imgappmanage.setText("Indeed");
                    webView.loadUrl("https://www.indeed.co.in/m/");
                    break;
                case "Amazon":
                    Window window20 = getWindow();
                    window20.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window20.setStatusBarColor(Color.parseColor("#222121"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#0F0E0E"));
                    //imgappmanage.setText("Amazon");
                    webView.loadUrl("https://www.amazon.in");
                    break;
                case "flipkart":
                    Window window21 = getWindow();
                    window21.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window21.setStatusBarColor(Color.parseColor("#0573A7"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#1E7EAC"));
                    //imgappmanage.setText("Flipcart");
                    webView.loadUrl("https://www.flipkart.com/");
                    break;
                case "Myntra":
                    Window window22 = getWindow();
                    window22.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window22.setStatusBarColor(Color.parseColor("#333435"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#686B6F"));
                    //imgappmanage.setText("Myntra");
                    webView.loadUrl("https://www.myntra.com");
                    break;
                case "Snapdeal":
                    Window window23 = getWindow();
                    window23.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window23.setStatusBarColor(Color.parseColor("#E62B6A"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#EE3875"));
                    //imgappmanage.setText("Snapdeal");
                    webView.loadUrl("https://m.snapdeal.com/");
                    break;
                case "Nykaa":
                    Window window24 = getWindow();
                    window24.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window24.setStatusBarColor(Color.parseColor("#F05489"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#D5336A"));
                    //imgappmanage.setText("Nykaa");
                    webView.loadUrl("https://www.nykaa.com");
                    break;
                case "Bigbasket":
                    Window window25 = getWindow();
                    window25.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window25.setStatusBarColor(Color.parseColor("#e65d38"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#d4380e"));
                    //imgappmanage.setText("Bigbasket");
                    webView.loadUrl("https://www.bigbasket.com");
                    break;
                case "Amazoncom":
                    Window window37 = getWindow();
                    window37.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window37.setStatusBarColor(Color.parseColor("#222121"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#0F0E0E"));
                    //imgappmanage.setText("Amazoncom");
                    webView.loadUrl("https://www.amazon.com");
                    break;
                case "Alibaba":
                    Window window38 = getWindow();
                    window38.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window38.setStatusBarColor(Color.parseColor("#222121"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#0F0E0E"));
                    //imgappmanage.setText("Alibaba");
                    webView.loadUrl("https://m.alibaba.com");
                    break;
                case "Aliexpress":
                    Window window39 = getWindow();
                    window39.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window39.setStatusBarColor(Color.parseColor("#222121"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#0F0E0E"));
                    //imgappmanage.setText("Aliexpress");
                    webView.loadUrl("https://m.aliexpress.com");
                    break;
                case "Grofers":
                    Window window26 = getWindow();
                    window26.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window26.setStatusBarColor(Color.parseColor("#89EC42"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#6FCE2C"));
                    //imgappmanage.setText("Grofers");
                    webView.loadUrl("https://grofers.com");
                    break;
                case "Healthkart":
                    Window window27 = getWindow();
                    window27.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window27.setStatusBarColor(Color.parseColor("#16D7F0"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#30C8DB"));
                    //imgappmanage.setText("Healthkart");
                    webView.loadUrl("https://www.healthkart.com");
                    break;
                case "Netmeds":
                    Window window28 = getWindow();
                    window28.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window28.setStatusBarColor(Color.parseColor("#094E57"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#053A41"));
                    //imgappmanage.setText("Netmeds");
                    webView.loadUrl("https://m.netmeds.com");
                    break;
                case "1Mg":
                    Window window29 = getWindow();
                    window29.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window29.setStatusBarColor(Color.parseColor("#04A6F0"));
                    }
                    // linearLayout.setBackgroundColor(Color.parseColor("#32AEE7"));
                    //imgappmanage.setText("1Mg");
                    webView.loadUrl("https://www.1mg.com");
                    break;
                case "Messanger":
                    Window window36 = getWindow();
                    window36.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window36.setStatusBarColor(Color.parseColor("#04A6F0"));
                    }
                    // linearLayout.setBackgroundColor(Color.parseColor("#32AEE7"));
                    //imgappmanage.setText("Messanger");
                    webView.loadUrl("https://m.facebook.com/messages");
                    break;
                case "Quikr":
                    Window window30 = getWindow();
                    window30.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window30.setStatusBarColor(Color.parseColor("#04A6F0"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#32AEE7"));
                    //imgappmanage.setText("Quikr");
                    webView.loadUrl("https://www.quikr.com");
                    break;
                case "Youtube":
                    Window window31 = getWindow();
                    window31.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window31.setStatusBarColor(Color.parseColor("#04A6F0"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#32AEE7"));
                    //imgappmanage.setText("Youtube");
                    webView.loadUrl("https://www.youtube.com");
                    break;
                case "Reddit":
                    Window window32 = getWindow();
                    window32.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window32.setStatusBarColor(Color.parseColor("#04A6F0"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#32AEE7"));
                    //imgappmanage.setText("Reddit");
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.loadUrl("https://www.reddit.com");
                    break;
                case "Medium":
                    Window window33 = getWindow();
                    window33.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window33.setStatusBarColor(Color.parseColor("#04A6F0"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#32AEE7"));
                    //imgappmanage.setText("Medium");
                    webView.loadUrl("https://medium.com");
                    break;
                case "Stumbleupon":
                    Window window34 = getWindow();
                    window34.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window34.setStatusBarColor(Color.parseColor("#04A6F0"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#32AEE7"));
                    //imgappmanage.setText("Stumbleupon");
                    webView.loadUrl("https://www.stumbleupon.com");
                    break;
                case "MySpace":
                    Window window35 = getWindow();
                    window35.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window35.setStatusBarColor(Color.parseColor("#04A6F0"));
                    }
                    //linearLayout.setBackgroundColor(Color.parseColor("#32AEE7"));
                    //imgappmanage.setText("MySpace");

                    webView.loadUrl("https://www.myspace.com/");
                    break;


            }

        } else {
            finish();
        }

        gohomes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BrowserActivity.this, HomeActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addCategory(Intent.CATEGORY_HOME);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();


            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                Log.e(TAG, "onProgressChanged :-> " + newProgress);
                webPageLoadingProgress.setProgress(newProgress);
                super.onProgressChanged(view, newProgress);
            }

            protected void openFileChooser(ValueCallback uploadMsg, String acceptType) {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "File Browser"), FILECHOOSER_RESULTCODE);
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                if (uploadMessage != null) {
                    uploadMessage.onReceiveValue(null);
                    uploadMessage = null;
                }
                uploadMessage = filePathCallback;

                Intent intent = fileChooserParams.createIntent();
                try {
                    startActivityForResult(intent, REQUEST_SELECT_FILE);
                } catch (ActivityNotFoundException e) {
                    uploadMessage = null;
                    Toast.makeText(getApplicationContext().getApplicationContext(), "Cannot Open File Chooser", Toast.LENGTH_LONG).show();
                    return false;
                }
                return true;
            }

            //For Android 4.1 only
            protected void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                mUploadMessage = uploadMsg;
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "File Browser"), FILECHOOSER_RESULTCODE);
            }

            protected void openFileChooser(ValueCallback<Uri> uploadMsg) {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
            }
        });

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                Uri uri = request.getUrl();

                return false;
                //return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                webPageLoadingProgress.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                webPageLoadingProgress.setVisibility(View.GONE);
                webPageLoadingProgress.setProgress(0);
                super.onPageFinished(view, url);
            }
        });
    }

    private void LoadBanner() {

        MobileAds.initialize(this, getString(R.string.test_admob_app_id));

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
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
