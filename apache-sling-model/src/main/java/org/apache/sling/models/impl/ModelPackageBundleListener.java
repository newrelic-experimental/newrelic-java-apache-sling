package org.apache.sling.models.impl;

import java.util.HashMap;
import java.util.Map;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.ServiceRegistration;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.apache.sling.models.impl.Util;


@Weave(type = MatchType.BaseClass)
public abstract class ModelPackageBundleListener {

	@SuppressWarnings("rawtypes")
	@Trace(dispatcher = true)
	public ServiceRegistration[] addingBundle(Bundle bundle, BundleEvent event) {
		Map<String, Object> attrs = new HashMap<>();


		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "Sling", "ModelPackageBundleListener", getClass().getSimpleName(), "addingBundle"});


		if (bundle != null ) {
			Util.recordValue(attrs, "bundle.name", bundle);
			Util.recordValue(attrs, "bundle.symbolicName", bundle.getSymbolicName());
			Util.recordValue(attrs, "bundle.state", bundle.getState());
			Util.recordValue(attrs, "bundle.id", bundle.getBundleId());
		}
		if (event != null ) {
			Util.recordValue(attrs, "event.name", event);
			Util.recordValue(attrs, "event.type", event.getType());
		}

		if ( attrs != null ) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attrs);
		}

		return Weaver.callOriginal();
	}


	@SuppressWarnings("rawtypes")
	@Trace(dispatcher = true)
	public void removedBundle(Bundle bundle, BundleEvent event, ServiceRegistration[] object) {
		Map<String, Object> attrs = new HashMap<>();

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "Sling", "ModelPackageBundleListener", getClass().getSimpleName(), "removedBundle"});


		if (bundle != null ) {
			Util.recordValue(attrs, "bundle.name", bundle);
			Util.recordValue(attrs, "bundle.symbolicName", bundle.getSymbolicName());
			Util.recordValue(attrs, "bundle.state", bundle.getState());
			Util.recordValue(attrs, "bundle.id", bundle.getBundleId());
		}
		if (event != null ) {
			Util.recordValue(attrs, "event.name", event);
			Util.recordValue(attrs, "event.type", event.getType());
		}

		if ( attrs != null ) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attrs);
		}

		Weaver.callOriginal();
	}

}
