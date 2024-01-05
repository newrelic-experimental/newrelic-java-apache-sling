package org.apache.sling.servlethelpers.internalrequests;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.apache.sling.servlet.helpers.Util;

@Weave(type = MatchType.BaseClass)
public abstract class InternalRequest {

	protected InternalRequest(ResourceResolver resourceResolver, String path) {}

	@Trace(dispatcher = true)
	public InternalRequest execute() throws IOException {
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[]{"Custom", "Sling", "InternalRequest", getClass().getSimpleName(), "execute"});
		return Weaver.callOriginal();
	}

	@Trace
	protected void delegateExecute(SlingHttpServletRequest request, SlingHttpServletResponse response, ResourceResolver resourceResolver)
			throws ServletException, IOException {

		try {
			if (request != null) {
				Util.recordRequestAttributes(request);
			}
		} catch (Exception e) {
			Util.handleException(getClass().getSimpleName(),"error evaluating delegateExecute", e);
		}

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[]{"Custom", "Sling", "InternalRequest",getClass().getSimpleName(), "delegateExecute"});
		Weaver.callOriginal();
	}

}
