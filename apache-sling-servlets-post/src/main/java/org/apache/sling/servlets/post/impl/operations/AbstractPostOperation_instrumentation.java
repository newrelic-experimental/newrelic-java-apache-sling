package org.apache.sling.servlets.post.impl.operations;


import java.util.List;
import java.util.logging.Level;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.servlets.post.Modification;
import org.apache.sling.servlets.post.PostResponse;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.apache.sling.servlets.post.Util;

@Weave(originalName = "org.apache.sling.servlets.post.impl.operations.AbstractPostOperation", type = MatchType.BaseClass)
public abstract class AbstractPostOperation_instrumentation {

	@Trace(dispatcher = true)
	protected  void doRun(SlingHttpServletRequest request,
            PostResponse response,
            List<Modification> changes) throws PersistenceException {

		try {
			if (request != null) {
				Util.recordRequestAttributes(request);
			}
		} catch (Exception e) {
			handleException("error evaluating request", e);
		}

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[]{"Custom", "Sling", getClass().getSimpleName(), "doRun"});
		Weaver.callOriginal();
	}



	private void handleException(String message, Throwable e) {
		NewRelic.getAgent().getLogger().log(Level.INFO, "Custom AbstractPostOperation Instrumentation - " + message);
		NewRelic.getAgent().getLogger().log(Level.FINER, "Custom AbstractPostOperation Instrumentation - " + message + ": " + e.getMessage());
	}
}

