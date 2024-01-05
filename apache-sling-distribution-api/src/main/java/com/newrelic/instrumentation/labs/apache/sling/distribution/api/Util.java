package com.newrelic.instrumentation.labs.apache.sling.distribution.api;

import java.util.Map;
import java.util.logging.Level;

import com.newrelic.api.agent.NewRelic;

public class Util {


	public static void recordValue(Map<String,Object> attributes, String key, Object value) {
		if(key != null && !key.isEmpty() && value != null) {
			attributes.put(key, value);
		}
	}

	public static  void handleException(String className, String message, Throwable e) {
		//NewRelic.getAgent().getLogger().log(Level.INFO, "Custom" + className  +" Instrumentation - " + message);
		NewRelic.getAgent().getLogger().log(Level.FINER, "Custom" + className +" Instrumentation - " + message + ": " + e.getMessage());
	}

}