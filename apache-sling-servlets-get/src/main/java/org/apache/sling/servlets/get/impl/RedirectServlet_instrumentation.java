package org.apache.sling.servlets.get.impl;

import java.io.IOException;
import java.util.logging.Level;

import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.apache.sling.servlets.get.Util;

@Weave(originalName = "org.apache.sling.servlets.get.impl.RedirectServlet", type = MatchType.BaseClass)
public abstract class RedirectServlet_instrumentation {

	@Trace(dispatcher = true)
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
	IOException {

		try {
			if (request != null) {
				 Util.recordRequestAttributes(request);
			}
		} catch (Exception e) {
			handleException("error evaluating request", e);
		}

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[]{"Custom", "Sling", getClass().getSimpleName(), "doGet"});
		Weaver.callOriginal();
	}



	private void handleException(String message, Throwable e) {
		NewRelic.getAgent().getLogger().log(Level.INFO, "Custom RedirectServlet Instrumentation - " + message);
		NewRelic.getAgent().getLogger().log(Level.FINER, "Custom RedirectServlet Instrumentation - " + message + ": " + e.getMessage());
	}
}
