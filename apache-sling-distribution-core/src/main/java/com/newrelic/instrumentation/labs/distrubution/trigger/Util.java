package com.newrelic.instrumentation.labs.distrubution.trigger;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.request.RequestParameterMap;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.distribution.DistributionRequest;

import com.newrelic.api.agent.NewRelic;

public class Util {


	public static void recordValue(Map<String,Object> attributes, String key, Object value) {
		if(key != null && !key.isEmpty() && value != null) {
			attributes.put(key, value);
		}
	}
	
 
public static void recordResourceResolver(Map<String, Object> attributes, ResourceResolver resourceResolver) {
	
	int i=0;
	if (resourceResolver != null) {
		Iterator<String> attributeNamesIterator = resourceResolver.getAttributeNames();

		while (attributeNamesIterator.hasNext()) {
		    String attributeName = attributeNamesIterator.next();
		    // Do something with the attributeName

		    // Optionally, retrieve the values associated with the attribute
		    Object value = resourceResolver.getAttribute(attributeName);
		    
		    if (value != null) {
		    	Util.recordValue(attributes, "resourceResolver.atribute: " + ++i, value);
		    }
		}		
	
	}
}

}