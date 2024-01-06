package org.apache.sling.distribution.agent.spi;



import java.util.HashMap;
import java.util.Map;

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

@Weave(type = MatchType.Interface)
public abstract class DistributionAgent {

	@Trace(dispatcher = true)
	public DistributionResponse execute( ResourceResolver resourceResolver, DistributionRequest distributionRequest) throws DistributionException {

		Map<String, Object> attrs = new HashMap<>();
		DistributionResponse result =null;

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "Sling", "DistributionAgent", getClass().getSimpleName(), "forward"});

		if (distributionRequest != null ) {
			// Modify this section based on the methods and properties of DistributionRequest

			Util.recordValue(attrs, "request", distributionRequest);

		}
		Util.recordResourceResolver(attrs, resourceResolver);



		if (attrs != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attrs);
		}

		try {
			result = Weaver.callOriginal();
		} catch (Exception e) {
			if(DistributionException.class.isInstance(e)) {
				NewRelic.noticeError(e);
				throw (DistributionException)e;
			}
		}
		return result;

	}

}
