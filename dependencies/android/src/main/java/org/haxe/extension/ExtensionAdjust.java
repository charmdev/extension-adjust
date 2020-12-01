package org.haxe.extension;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.res.AssetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.util.Log;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustConfig;
import com.adjust.sdk.LogLevel;
import com.adjust.sdk.AdjustEvent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class ExtensionAdjust extends Extension {

	private static final String TAG = "ExtensionAdjust trace hx";

	public static int test (int inputValue) {
		
		Log.i(TAG, "----------------> ok " + inputValue);

		return inputValue;
		
	}

	public static void trackEvent(String eventName, String eventData) {
		AdjustEvent adjustEvent = new AdjustEvent(eventName);
		Log.i(TAG, "evt data " + eventData);
		if (eventData != null && eventData.length() != 0)
		{
			try
			{
				JSONObject jObject = new JSONObject(eventData);
				Iterator<String> keys = jObject.keys();
				while (keys.hasNext())
				{
					String key = (String) keys.next();
					String value = (String) jObject.get(key);
					adjustEvent.addCallbackParameter(key, value);
					Log.i(TAG, "added parameter " + key + ":" + value);
				}
				Log.i(TAG, "parameters added");
			}
			catch (final JSONException e)
			{
				Log.e(TAG, "json parsing error: " + e.getMessage());
			}
		}
		Adjust.trackEvent(adjustEvent);
		Log.i(TAG, eventName + " tracked");
	}

	public static void trackRevenueEvent(String eventName, float value, String currency) {
		AdjustEvent adjustEvent = new AdjustEvent(eventName);
		adjustEvent.setRevenue(value, currency);
		Adjust.trackEvent(adjustEvent);
		Log.i(TAG, eventName + " " + value + " " + currency + " tracked");
	}
	
	
	/**
	 * Called when an activity you launched exits, giving you the requestCode 
	 * you started it with, the resultCode it returned, and any additional data 
	 * from it.
	 */
	public boolean onActivityResult (int requestCode, int resultCode, Intent data) {
		
		return true;
		
	}

	/**
	 * Called when the activity receives th results for permission requests.
	 */
	public boolean onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

		return true;

	}
	
	
	/**
	 * Called when the activity is starting.
	 */
	public void onCreate (Bundle savedInstanceState) {
		Log.i(TAG, "----------------> on create");
		String appToken = "7n0161fjtr40";
		String environment = AdjustConfig.ENVIRONMENT_PRODUCTION;
		AdjustConfig config = new AdjustConfig(mainContext, appToken, environment);
		config.setLogLevel(LogLevel.VERBOSE);
		Adjust.onCreate(config);

		mainActivity.getApplication().registerActivityLifecycleCallbacks(new AdjustLifecycleCallbacks());
	}
	
	
	/**
	 * Perform any final cleanup before an activity is destroyed.
	 */
	public void onDestroy () {
		
		
		
	}
	
	
	/**
	 * Called as part of the activity lifecycle when an activity is going into
	 * the background, but has not (yet) been killed.
	 */
	public void onPause () {
		
		
		
	}
	
	
	/**
	 * Called after {@link #onStop} when the current activity is being 
	 * re-displayed to the user (the user has navigated back to it).
	 */
	public void onRestart () {
		
		
		
	}
	
	
	/**
	 * Called after {@link #onRestart}, or {@link #onPause}, for your activity 
	 * to start interacting with the user.
	 */
	public void onResume () {
		
		
		
	}
	
	
	/**
	 * Called after {@link #onCreate} &mdash; or after {@link #onRestart} when  
	 * the activity had been stopped, but is now again being displayed to the 
	 * user.
	 */
	public void onStart () {
		
		
		
	}
	
	
	/**
	 * Called when the activity is no longer visible to the user, because 
	 * another activity has been resumed and is covering this one. 
	 */
	public void onStop () {
		
		
		
	}


	private static final class AdjustLifecycleCallbacks implements ActivityLifecycleCallbacks {
		@Override
		public void onActivityResumed(Activity activity)
		{
			Adjust.onResume();
		}

		@Override
		public void onActivityPaused(Activity activity)
		{
			Adjust.onPause();
		}

		public void onActivityCreated(Activity activity, Bundle state) {

		}

		public void onActivityDestroyed(Activity activity) {

		}

		public void onActivityPostCreated(Activity activity, Bundle state) {

		}

		public void onActivityPostDestroyed(Activity activity) {

		}

		public void onActivityPostPaused(Activity activity) {

		}

		public void onActivityPostResumed(Activity activity) {

		}

		public void onActivityPostSaveInstanceState(Activity activity, Bundle state) {

		}

		public void onActivityPostStarted(Activity activity) {

		}

		public void onActivityPostStopped(Activity activity) {

		}

		public void onActivityPreCreated(Activity activity, Bundle state) {

		}

		public void onActivityPreDestroyed(Activity activity) {

		}

		public void onActivityPrePaused(Activity activity) {

		}

		public void onActivityPreResumed(Activity activity) {

		}

		public void onActivityPreSaveInstanceState(Activity activity, Bundle state) {

		}

		public void onActivityPreStarted(Activity activity) {

		}

		public void onActivityPreStopped(Activity activity) {

		}

		public void onActivitySaveInstanceState(Activity activity, Bundle state) {

		}

		public void onActivityStarted(Activity activity) {

		}

		public void onActivityStopped(Activity activity) {

		}
	}
	
}

