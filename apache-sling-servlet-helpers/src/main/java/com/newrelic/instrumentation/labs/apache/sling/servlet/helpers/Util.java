package com.newrelic.instrumentation.labs.apache.sling.servlet.helpers;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.request.RequestParameterMap;

import com.newrelic.api.agent.NewRelic;

public class Util {


	public static void  recordRequestAttributes(SlingHttpServletRequest request) {
		Map<String, Object> attrs = new HashMap<>();
		recordRequestParams(attrs, request);

		recordValue(attrs, "request.path", request.getPathInfo());
		recordValue(attrs, "request.requestMethod", request.getMethod());
		recordValue(attrs, "request.contentType", request.getContentType());
		recordValue(attrs, "request.queryString", request.getQueryString());

		if (attrs != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attrs);
		}
	}


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