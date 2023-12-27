package org.apache.sling.models.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.ServiceRegistration;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.apache.sling.models.impl.Util;


@Weave(originalName = "org.apache.sling.models.impl.ModelPackageBundleListener", type = MatchType.BaseClass)
public abstract class ModelPackageBundleListener_instrumentation {

	@SuppressWarnings("rawtypes")
	@Trace(dispatcher = true)
	public ServiceRegistration[] addingBundle(Bundle bundle, BundleEvent event) {
		Map<String, Object> attrs = new HashMap<>();

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "Sling", "ModelPackageBundleListener", getClass().getSimpleName(), "addingBundle"});

		try {
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

		}
		catch (Exception e) {
			handleException("error evaluating addingBundle", e);

		}
		return Weaver.callOriginal();
	}


	@SuppressWarnings("rawtypes")
	@Trace(dispatcher = true)
	public void removedBundle(Bundle bundle, BundleEvent event, ServiceRegistration[] object) {
		Map<String, Object> attrs = new HashMap<>();

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "Sling", "ModelPackageBundleListener", getClass().getSimpleName(), "removedBundle"});

		try {
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
			

		}
		catch (Exception e) {
			handleException("error evaluating removedBundle", e);

		}
		Weaver.callOriginal();
	}
	private void handleException(String message, Throwable e) {
		NewRelic.getAgent().getLogger().log(Level.INFO, "Custom ModelPackageBundleListener Instrumentation - " + message);
		NewRelic.getAgent().getLogger().log(Level.FINER, "Custom ModelPackageBundleListener Instrumentation - " + message + ": " + e.getMessage());
	}
}
