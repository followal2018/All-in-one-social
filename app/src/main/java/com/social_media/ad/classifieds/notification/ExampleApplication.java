package com.social_media.ad.classifieds.notification;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OSNotification;
import com.onesignal.OneSignal;
import com.social_media.ad.classifieds.activity.App;
import com.social_media.ad.classifieds.activity.ForceUpdateChecker;
import com.social_media.ad.classifieds.activity.HomeActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ExampleApplication extends Application {
   private static final String TAG = App.class.getSimpleName();
   @Override
   public void onCreate() {
      super.onCreate();
      final FirebaseRemoteConfig firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();

      // set in-app defaults
      Map<String, Object> remoteConfigDefaults = new HashMap();
      remoteConfigDefaults.put(ForceUpdateChecker.KEY_UPDATE_REQUIRED, false);
      remoteConfigDefaults.put(ForceUpdateChecker.KEY_CURRENT_VERSION, "6.1");
      remoteConfigDefaults.put(ForceUpdateChecker.KEY_UPDATE_URL,
              "https://play.google.com/store/apps/details?id=com.social_media.ad.classifieds");

      firebaseRemoteConfig.setDefaults(remoteConfigDefaults);
      firebaseRemoteConfig.fetch(60) // fetch every minutes
              .addOnCompleteListener(new OnCompleteListener<Void>() {
                 @Override
                 public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                       Log.d(TAG, "remote config is fetched.");
                       firebaseRemoteConfig.activateFetched();
                    }
                 }
              });

      // Logging set to help debug issues, remove before releasing your app.
      //OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.WARN);

      OneSignal.startInit(this)
              .setNotificationReceivedHandler(new ExampleNotificationReceivedHandler())
              .setNotificationOpenedHandler(new ExampleNotificationOpenedHandler())
              .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
              .unsubscribeWhenNotificationsAreDisabled(true)
              .init();
   }

   private class ExampleNotificationReceivedHandler implements OneSignal.NotificationReceivedHandler {
      @Override
      public void notificationReceived(OSNotification notification) {
         JSONObject data = notification.payload.additionalData;
         String notificationID = notification.payload.notificationID;
         String title = notification.payload.title;
         String body = notification.payload.body;
         String smallIcon = notification.payload.smallIcon;
         String largeIcon = notification.payload.largeIcon;
         String bigPicture = notification.payload.bigPicture;
         String smallIconAccentColor = notification.payload.smallIconAccentColor;
         String sound = notification.payload.sound;
         String ledColor = notification.payload.ledColor;
         int lockScreenVisibility = notification.payload.lockScreenVisibility;
         String groupKey = notification.payload.groupKey;
         String groupMessage = notification.payload.groupMessage;
         String fromProjectNumber = notification.payload.fromProjectNumber;
         String rawPayload = notification.payload.rawPayload;

         String customKey;

         Log.i("OneSignalExample", "NotificationID received: " + notificationID);

         if (data != null) {
            customKey = data.optString("customkey", null);
            if (customKey != null)
               Log.i("OneSignalExample", "customkey set with value: " + customKey);
         }
      }
   }


   private class ExampleNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
      // This fires when a notification is opened by tapping on it.
      @Override
      public void notificationOpened(OSNotificationOpenResult result) {
         OSNotificationAction.ActionType actionType = result.action.type;
         JSONObject data = result.notification.payload.additionalData;
         String launchUrl = result.notification.payload.launchURL; // update docs launchUrl

         String customKey;
         String openURL = "https://primeprepare.com/mobile";
         Object activityToLaunch = HomeActivity.class;

         if (data != null) {
            customKey = data.optString("redirect", null);
            openURL = data.optString("openURL", null);

            if (customKey != null)
               Log.i("OneSignalExample", "customkey set with value: " + customKey);

            if (openURL != null)
               Log.i("OneSignalExample", "openURL to webview with URL value: " + openURL);
         }

         if (actionType == OSNotificationAction.ActionType.ActionTaken) {
            Log.i("OneSignalExample", "Button pressed with id: " + result.action.actionID);

            if (result.action.actionID.equals("id1")) {
               Log.i("OneSignalExample", "button id called: " + result.action.actionID);
               activityToLaunch = GreenActivity.class;
            } else
               Log.i("OneSignalExample", "button id called: " + result.action.actionID);
         }
         // The following can be used to open an Activity of your choice.
         // Replace - getApplicationContext() - with any Android Context.
         // Intent intent = new Intent(getApplicationContext(), YourActivity.class);
         Intent intent = new Intent(getApplicationContext(), (Class<?>) activityToLaunch);
         // intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
         intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
         intent.putExtra("openURL", openURL);
         Log.i("OneSignalExample", "openURL = " + openURL);
         // startActivity(intent);
         startActivity(intent);

         // Add the following to your AndroidManifest.xml to prevent the launching of your main Activity
         //   if you are calling startActivity above.
        /*
           <application ...>
             <meta-data android:name="com.onesignal.NotificationOpened.DEFAULT" android:value="DISABLE" />
           </application>
        */
      }
   }
}
