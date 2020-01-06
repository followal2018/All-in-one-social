package com.social_media.ad.classifieds.notification;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.onesignal.OSPermissionSubscriptionState;
import com.onesignal.OneSignal;
import com.social_media.ad.classifieds.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class MainActivity extends AppCompatActivity {
   WebView mWebView;
   TextView connection;
   @Override
   protected void onCreate(Bundle savedInstanceState) {

      super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_mainm);
     connection = findViewById(R.id.ceck_int);
       mWebView = (WebView) findViewById(R.id.webview);

// string url which you have to load into a web view
       String url = "https://primeprepare.com/mobile";
       mWebView.getSettings().setJavaScriptEnabled(true);
      mWebView.getSettings().setUseWideViewPort(true);
      ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
      NetworkInfo i = manager.getActiveNetworkInfo();
      boolean hasConnect = (i!= null && i.isConnected() && i.isAvailable());



      final TextView textView = findViewById(R.id.debug_view);
      textView.setText("OneSignal is Ready!");

      /* Deprecated, use getPermissionSubscriptionState, addPermissionObserver, or add SubscriptionObserver instead
      OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
         @Override
         public void idsAvailable(String userId, String registrationId) {
            String text = "OneSignal UserID:\n" + userId + "\n\n";

            if (registrationId != null)
               text += "Google Registration Id:\n" + registrationId;
            else
               text += "Google Registration Id:\nCould not subscribe for push";

            TextView textView = (TextView)findViewById(R.id.debug_view);
            textView.setText(text);
         }
      });
      */
      Button onSendTagsButton = findViewById(R.id.send_tags_button);
      onSendTagsButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            JSONObject tags = new JSONObject();
            try {
               tags.put("key1", "value1");
               tags.put("user_name", "Jon");
            } catch (JSONException e) {
               e.printStackTrace();
            }
            OneSignal.sendTags(tags);
            textView.setText("Tags sent!");
         }

      });

      Button onGetTagsButton = findViewById(R.id.get_tags_button);
      onGetTagsButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            final Collection<String> receivedTags = new ArrayList<>();
            OneSignal.getTags(new OneSignal.GetTagsHandler() {
               @Override
               public void tagsAvailable(final JSONObject tags) {
                  Log.d("debug", tags.toString());
                  new Handler(Looper.getMainLooper()).post(new Runnable() {
                     @Override
                     public void run() {
                        receivedTags.add(tags.toString());
                        textView.setText("Tags Received: " + receivedTags);
                     }
                  });
               }
            });
         }
      });

      Button onDeleteOrUpdateTagsButton = findViewById(R.id.delete_update_tags_button);
      onDeleteOrUpdateTagsButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            OneSignal.getTags(new OneSignal.GetTagsHandler() {
               @Override
               public void tagsAvailable(final JSONObject tags) {
                  Log.d("debug", "Current Tags on User: " + tags.toString());
                  OneSignal.deleteTag("key1");
                  OneSignal.sendTag("updated_key", "updated_value");
                  new Handler(Looper.getMainLooper()).post(new Runnable() {
                     @Override
                     public void run() {
                        textView.setText("Updated Tags: " + tags);
                     }
                  });
               }
            });
         }
      });

      Button onGetIDsAvailableButton = findViewById(R.id.get_ids_available_button);
      onGetIDsAvailableButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            OSPermissionSubscriptionState status = OneSignal.getPermissionSubscriptionState();
            boolean isEnabled = status.getPermissionStatus().getEnabled();
            boolean isSubscribed = status.getSubscriptionStatus().getSubscribed();
            boolean subscriptionSetting = status.getSubscriptionStatus().getUserSubscriptionSetting();

            String userID = status.getSubscriptionStatus().getUserId();
            String pushToken = status.getSubscriptionStatus().getPushToken();

            textView.setText("PlayerID: " + userID + "\nPushToken: " + pushToken);
         }
      });

      Button onPromptLocationButton = (Button)(findViewById(R.id.prompt_location_button));
      onPromptLocationButton.setOnClickListener(new View.OnClickListener(){
         @Override
         public void onClick(View view) {
            OneSignal.promptLocation();
            /*
            Make sure you have one of the following permissions in your AndroidManifest.xml
            <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
            <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
             */
         }
      });

      Button onSendNotification1 = findViewById(R.id.send_notification_button);
      onSendNotification1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            OSPermissionSubscriptionState status = OneSignal.getPermissionSubscriptionState();
            String userId = status.getSubscriptionStatus().getUserId();
            boolean isSubscribed = status.getSubscriptionStatus().getSubscribed();

            textView.setText("Subscription Status, is subscribed:" + isSubscribed);

            if (!isSubscribed)
               return;

            try {
               JSONObject notificationContent = new JSONObject("{'contents': {'en': 'The notification message or body'}," +
                       "'include_player_ids': ['" + userId + "'], " +
                       "'headings': {'en': 'Notification Title'}, " +
                       "'big_picture': 'http://i.imgur.com/DKw1J2F.gif'}");
               OneSignal.postNotification(notificationContent, null);
            } catch (JSONException e) {
               e.printStackTrace();
            }
         }
      });

      Button onSendNotification2 = findViewById(R.id.send_notification_button2);
      onSendNotification2.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            OSPermissionSubscriptionState status = OneSignal.getPermissionSubscriptionState();
            String userID = status.getSubscriptionStatus().getUserId();
            boolean isSubscribed = status.getSubscriptionStatus().getSubscribed();

            textView.setText("Subscription Status, is subscribed:" + isSubscribed);

            if (!isSubscribed)
               return;

            try {
               OneSignal.postNotification(new JSONObject("{'contents': {'en':'Tag substitution value for key1 = {{key1}}'}, " +
                           "'include_player_ids': ['" + userID + "'], " +
                           "'headings': {'en': 'Tag sub Title HI {{user_name}}'}, " +
                           "'data': {'openURL': 'https://imgur.com'}," +
                           "'buttons':[{'id': 'id1', 'text': 'Go to GreenActivity'}, {'id':'id2', 'text': 'Go to MainActivity'}]}"),
                     new OneSignal.PostNotificationResponseHandler() {
                        @Override
                        public void onSuccess(JSONObject response) {
                           Log.i("OneSignalExample", "postNotification Success: " + response);
                        }

                        @Override
                        public void onFailure(JSONObject response) {
                           Log.e("OneSignalExample", "postNotification Failure: " + response);
                        }
                     });
            } catch (JSONException e) {
               e.printStackTrace();
            }

         }
      });

      final Switch onSetSubscriptionSwitch = findViewById(R.id.set_subscription_switch);
      onSetSubscriptionSwitch.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            if (onSetSubscriptionSwitch.isChecked()) {
               OneSignal.setSubscription(true);
               textView.setText("User CAN receive notifications if turned on in Phone Settings");
            }
            else {
               OneSignal.setSubscription(false);
               textView.setText("User CANNOT receive notifications, even if they are turned on in Phone Settings");
            }
         }
      });
   }

}
