package;


import haxe.Json;
import nme.JNI;


class ExtensionAdjust {
	
	public static function test(inputValue:Int):Int
	{
#if android
		return extension_adjust_test_jni(inputValue);
#else
		return extension_adjust_test(inputValue);
#end
	}

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
		trace('track ${eventName}, params: ${evtDataString}');
#if android
		return extension_adjust_track_event_jni(eventName, evtDataString);
#else
		trace("trackEvent not implemented");
#end
	}

	public static function trackRevenueEvent(eventName:String, value:Float, currency:String):Void
	{
		trace('track revenue ${eventName}, value: ${value}, currency: ${currency}');
#if android
		return extension_adjust_track_revenue_event_jni(eventName, value, currency);
#else
		trace("trackRevenueEvent not implemented");
#end
	}

	private static function extension_adjust_test(val:Int):Int
	{
		trace('-----------------------> ExtensionAdjust: test default ok ${val}');
		return val;
	}

#if android
	private static var extension_adjust_test_jni = JNI.createStaticMethod ("org.haxe.extension.ExtensionAdjust", "test", "(I)I");
	private static var extension_adjust_track_event_jni = JNI.createStaticMethod ("org.haxe.extension.ExtensionAdjust", "trackEvent", "(Ljava/lang/String;Ljava/lang/String;)V");
	private static var extension_adjust_track_revenue_event_jni = JNI.createStaticMethod ("org.haxe.extension.ExtensionAdjust", "trackRevenueEvent", "(Ljava/lang/String;FLjava/lang/String;)V");
#end
}
