package com.newrelic.instrumentation.labs.apache.sling.provisioning.model;

import java.util.Map;

import org.apache.sling.provisioning.model.Feature;
import org.apache.sling.provisioning.model.Model;



public class Util {


	public static void recordValue(Map<String,Object> attributes, String key, Object value) {
		if(key != null && !key.isEmpty() && value != null) {
			attributes.put(key, value);
		}
	}
	public static void recordModelFeature(Map<String, Object> attributes, Model model) {

		int i = 0;

		for(Feature feature : model.getFeatures()) {
			String sName = feature.getName();
			recordValue(attributes, "Feature: "+ ++i, sName);
		}
	}
}