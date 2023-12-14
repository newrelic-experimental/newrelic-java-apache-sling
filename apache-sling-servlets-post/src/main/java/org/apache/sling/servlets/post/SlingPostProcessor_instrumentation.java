package org.apache.sling.servlets.post;

import java.util.List;
import java.util.logging.Level;

import org.apache.sling.api.SlingHttpServletRequest;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.apache.sling.servlets.post.Util;

@Weave(originalName = "org.apache.sling.servlets.post.SlingPostProcessor", type = MatchType.Interface)
public abstract class SlingPostProcessor_instrumentation {

	@Trace(dispatcher = true)
	public void process(SlingHttpServletRequest request, List<Modification> changes)
			    throws Exception {

		try {
			if (request != null) {
				Util.recordRequestAttributes(request);
			}
		} catch (Exception e) {
			handleException("error evaluating request", e);
		}

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[]{"Custom", "Sling", getClass().getSimpleName(), "process"});
		Weaver.callOriginal();
	}



	private void handleException(String message, Throwable e) {
		NewRelic.getAgent().getLogger().log(Level.INFO, "Custom SlingPostProcessor Instrumentation - " + message);
		NewRelic.getAgent().getLogger().log(Level.FINER, "Custom SlingPostProcessor Instrumentation - " + message + ": " + e.getMessage());
	}
}

