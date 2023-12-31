package com.newrelic.instrumentation.labs.apache.sling;

import java.util.Map;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.request.RequestParameterMap;

public class Util {



	public static void recordRequestParams(Map<String, Object> attributes, SlingHttpServletRequest request) {
		if(request != null) {


			RequestParameterMap RPM = request.getRequestParameterMap();

			for (String key : RPM.keySet()) {
				// Get the first value for the named parameter
				RequestParameter[] values = RPM.getValues(key);
				Object firstValue = (values != null && values.length > 0) ? values[0] : null;

				// Add the first value to the new map
				attributes.put("RequestParam-"+key, firstValue);
			}

		}
	}

	public static void recordValue(Map<String,Object> attributes, String key, Object value) {
		if(key != null && !key.isEmpty() && value != null) {
			attributes.put(key, value);
		}
	}

}