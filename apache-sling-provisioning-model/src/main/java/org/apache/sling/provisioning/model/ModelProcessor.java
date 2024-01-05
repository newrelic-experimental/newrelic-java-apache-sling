package org.apache.sling.provisioning.model;

import java.util.HashMap;
import java.util.Map;


import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.apache.sling.provisioning.model.Util;



@Weave(type = MatchType.BaseClass)
abstract class ModelProcessor {


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
			Util.handleException( getClass().getSimpleName(),"error evaluating process", e);

		}
		return Weaver.callOriginal();
	}




}
