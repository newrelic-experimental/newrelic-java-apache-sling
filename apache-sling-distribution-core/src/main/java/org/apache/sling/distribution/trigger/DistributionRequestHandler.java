package org.apache.sling.distribution.trigger;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.distribution.DistributionRequest;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.distrubution.trigger.Util;


@Weave(type = MatchType.Interface)
public abstract class DistributionRequestHandler {

	@Trace(dispatcher = true)
	public void handle( ResourceResolver resourceResolver, DistributionRequest request) {

		Map<String, Object> attrs = new HashMap<>();

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "Sling", "DistributionRequestHandler", getClass().getSimpleName(), "handle"});
		try {


			if (request != null) {
				Util.recordValue(attrs, "request.type", request.getRequestType().name());

			}

			Util.recordResourceResolver(attrs, resourceResolver);

		}
		catch (Exception e) {
			Util.handleException(getClass().getSimpleName(),"error evaluating handle", e);

		}
		if (attrs != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attrs);
		}

		Weaver.callOriginal();



	}

}
