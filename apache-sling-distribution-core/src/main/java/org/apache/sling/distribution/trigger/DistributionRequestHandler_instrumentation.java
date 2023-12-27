package org.apache.sling.distribution.trigger;

import java.util.HashMap;
import java.util.Iterator;

import javax.jcr.RepositoryException;
import javax.jcr.observation.Event;

import java.util.Map;
import java.util.logging.Level;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.distribution.DistributionRequest;
import org.apache.sling.distribution.DistributionResponse;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.distrubution.trigger.Util;


@Weave(originalName = "org.apache.sling.distribution.trigger.DistributionRequestHandler", type = MatchType.Interface)
public abstract class DistributionRequestHandler_instrumentation {

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
			handleException("error evaluating handle", e);

		}
		if (attrs != null) {
            NewRelic.getAgent().getTracedMethod().addCustomAttributes(attrs);
        }
		
		Weaver.callOriginal();



	}
	private void handleException(String message, Throwable e) {
		NewRelic.getAgent().getLogger().log(Level.INFO, "Custom resourceResolver Instrumentation - " + message);
		NewRelic.getAgent().getLogger().log(Level.FINER, "Custom resourceResolver Instrumentation - " + message + ": " + e.getMessage());
	}
}
