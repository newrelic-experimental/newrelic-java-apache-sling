package org.apache.sling.models.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.apache.sling.provisioning.model.Model;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.apache.sling.provisioning.model.Util;



@Weave(originalName = "org.apache.sling.provisioning.model.ModelProcessor", type = MatchType.BaseClass)
abstract class ModelProcessor_instrumentation {


	@Trace(dispatcher = true)
	public  Model process( Model model) {
		Map<String, Object> attrs = new HashMap<>();

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "Sling", "ModelProcessor", getClass().getSimpleName(), "process"});

		try {
			if (model != null ) {
				Util.recordValue(attrs, "model.name", model);
				Util.recordValue(attrs, "model.location", model.getLocation());
				Util.recordModelFeature(attrs, model);

			}

			if ( attrs != null ) {
				NewRelic.getAgent().getTracedMethod().addCustomAttributes(attrs);
			}

		}
		catch (Exception e) {
			handleException("error evaluating process", e);

		}
		return Weaver.callOriginal();
	}



	private void handleException(String message, Throwable e) {
		NewRelic.getAgent().getLogger().log(Level.INFO, "Custom ModelProcessor Instrumentation - " + message);
		NewRelic.getAgent().getLogger().log(Level.FINER, "Custom ModelProcessor Instrumentation - " + message + ": " + e.getMessage());
	}
}
