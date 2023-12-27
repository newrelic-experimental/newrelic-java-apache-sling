package org.apache.sling.servlets.post;

import java.util.logging.Level;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.servlets.post.exceptions.PreconditionViolatedPersistenceException;
import org.apache.sling.servlets.post.exceptions.TemporaryPersistenceException;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.apache.sling.servlets.post.Util;

@Weave(originalName = "org.apache.sling.servlets.post.PostOperation", type = MatchType.Interface)
public abstract class PostOperation_instrumentation {

	@Trace(dispatcher = true)
	public void run(SlingHttpServletRequest request, PostResponse response,
			SlingPostProcessor[] processors) throws PreconditionViolatedPersistenceException, TemporaryPersistenceException, PersistenceException {

		try {
			if (request != null) {
			Util.recordRequestAttributes(request);
			}
		} catch (Exception e) {
			handleException("error evaluating run", e);
		}

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[]{"Custom", "Sling", "PostOperation", getClass().getSimpleName(), "run"});
		Weaver.callOriginal();
	}



	private void handleException(String message, Throwable e) {
		NewRelic.getAgent().getLogger().log(Level.INFO, "Custom PostOperation Instrumentation - " + message);
		NewRelic.getAgent().getLogger().log(Level.FINER, "Custom PostOperation Instrumentation - " + message + ": " + e.getMessage());
	}
}
