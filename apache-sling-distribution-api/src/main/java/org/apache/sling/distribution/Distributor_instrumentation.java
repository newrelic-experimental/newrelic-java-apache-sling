package org.apache.sling.distribution;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.apache.sling.api.resource.ResourceResolver;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.apache.sling.distribution.api.Util;

@Weave(originalName = "org.apache.sling.distribution.Distributor", type = MatchType.Interface)
public abstract class Distributor_instrumentation {

	@Trace(dispatcher = true)
	public DistributionResponse distribute(String agentName, ResourceResolver resourceResolver,
			DistributionRequest distributionRequest) throws Exception {

		Map<String, Object> attrs = new HashMap<>();

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "Sling", "Distributor", getClass().getSimpleName(), "distribute"});
		try {


			if (agentName != null) {
				Util.recordValue(attrs, "distribute.AgentName", agentName);
			}

			if (resourceResolver != null) {
				Util.recordValue(attrs, "distribute.UserID", resourceResolver.getUserID());
			}

			if (distributionRequest != null) {
				Util.recordValue(attrs, "distribute.RequestType", distributionRequest.getRequestType());
			}

		}
		catch (Exception e) {
			handleException("error evaluating distribute", e);

		}
		if (attrs != null) {
            NewRelic.getAgent().getTracedMethod().addCustomAttributes(attrs);
        }
		
		return Weaver.callOriginal();



	}
	private void handleException(String message, Throwable e) {
		NewRelic.getAgent().getLogger().log(Level.INFO, "Custom Distributor Instrumentation - " + message);
		NewRelic.getAgent().getLogger().log(Level.FINER, "Custom Distributor Instrumentation - " + message + ": " + e.getMessage());
	}
}
