package com.newrelic.instrumentation.labs.apache.sling.models.impl;

import java.util.Map;

public class Util {


	public static void recordValue(Map<String,Object> attributes, String key, Object value) {
		if(key != null && !key.isEmpty() && value != null) {
			attributes.put(key, value);
		}
	}

}