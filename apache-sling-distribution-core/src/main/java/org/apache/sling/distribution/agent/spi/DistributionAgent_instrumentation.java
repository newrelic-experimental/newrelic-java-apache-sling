package org.apache.sling.distribution.agent.spi;



import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.distribution.DistributionRequest;
import org.apache.sling.distribution.DistributionResponse;
import org.apache.sling.distribution.common.DistributionException;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.distrubution.trigger.Util;

@Weave(originalName = "org.apache.sling.distribution.agent.spi.DistributionAgent", type = MatchType.Interface)
public abstract class DistributionAgent_instrumentation {

	@Trace(dispatcher = true)
	public DistributionResponse execute( ResourceResolver resourceResolver, DistributionRequest distributionRequest) throws DistributionException {

		Map<String, Object> attrs = new HashMap<>();

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "Sling", "DistributionAgent", getClass().getSimpleName(), "forward"});
		try {
			if (distributionRequest != null ) {
				// Modify this section based on the methods and properties of DistributionRequest

				Util.recordValue(attrs, "request", distributionRequest);

			}
			Util.recordResourceResolver(attrs, resourceResolver);


		} catch (Exception e) {
			handleException("error evaluating execute", e);
		}

		if (attrs != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attrs);
		}


		return  Weaver.callOriginal();
	}


	private void handleException(String message, Throwable e) {
		NewRelic.getAgent().getLogger().log(Level.INFO, "Custom DistributionAgent Instrumentation - " + message);
		NewRelic.getAgent().getLogger().log(Level.FINER, "Custom DistributionAgent Instrumentation - " + message + ": " + e.getMessage());
	}
}
