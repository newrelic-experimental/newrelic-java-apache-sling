package org.apache.sling.api.resource;


import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

import jakarta.servlet.http.HttpServletRequest;

@Weave(type = MatchType.Interface)
public abstract class ResourceResolver {

	@Trace
	public Resource resolve(String absPath) {
		Resource res = Weaver.callOriginal();

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "Sling","ResourceResolver", getClass().getSimpleName(), "resolve", res.getName()});
		return res;
	}

	@Trace
	public Resource resolve(HttpServletRequest req, String resourcePath) {
		Resource res = Weaver.callOriginal();

		if (req instanceof HttpServletRequest) {
			NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "Sling", "ResourceResolver", getClass().getSimpleName(), "resolve", res.getName()});
		}
		return res;
	}
}
