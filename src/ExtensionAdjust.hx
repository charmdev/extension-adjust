package;


import haxe.Json;
import nme.JNI;


class ExtensionAdjust {
	
	public static function trackEvent(eventName:String, eventData:Any = null):Void
	{
		var evtDataString:String = null;
		if (eventData != null)
		{
			try
			{
				evtDataString = Json.stringify(eventData);
			}
			catch (e:String)
			{
				trace('event data not parsed ${e}}');
				evtDataString = null;
			}
		}
		trace(evtDataString);
#if android
		return extension_adjust_track_event_jni(eventName, evtDataString);
#else
		trace("trackEvent not implemented");
#end
	}

#if android
	private static var extension_adjust_track_event_jni = JNI.createStaticMethod ("org.haxe.extension.ExtensionAdjust", "trackEvent", "(Ljava/lang/String;Ljava/lang/String;)V");
#end
}
