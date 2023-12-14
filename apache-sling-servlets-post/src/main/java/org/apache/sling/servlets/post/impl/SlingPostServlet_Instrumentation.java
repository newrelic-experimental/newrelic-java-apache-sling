package org.apache.sling.servlets.post.impl;

import java.io.IOException;
import java.util.logging.Level;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.apache.sling.servlets.post.Util;

@Weave(originalName = "org.apache.sling.servlets.post.impl.SlingPostServlet", type = MatchType.BaseClass)
public abstract class SlingPostServlet_Instrumentation  {

	@Trace(dispatcher = true)
	protected void doPost(SlingHttpServletRequest request,
            SlingHttpServletResponse response) throws IOException {

		try {
			if (request != null) {
				Util.recordRequestAttributes(request);
			}
		} catch (Exception e) {
			handleException("error evaluating request", e);
		}

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[]{"Custom", "Sling", getClass().getSimpleName(), "doPost"});
		Weaver.callOriginal();
	}



	private void handleException(String message, Throwable e) {
		NewRelic.getAgent().getLogger().log(Level.INFO, "Custom SlingPostServlet Instrumentation - " + message);
		NewRelic.getAgent().getLogger().log(Level.FINER, "Custom SlingPostServlet Instrumentation - " + message + ": " + e.getMessage());
	}
}
