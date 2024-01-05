package org.apache.sling.servlets.post.impl.operations;


import java.util.List;

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

@Weave(type = MatchType.BaseClass)
public abstract class AbstractPostOperation {

	@Trace(dispatcher = true)
	protected  void doRun(SlingHttpServletRequest request,
			PostResponse response,
			List<Modification> changes) throws PersistenceException {

		try {
			if (request != null) {
				Util.recordRequestAttributes(request);
			}
		} catch (Exception e) {
			Util.handleException(getClass().getSimpleName(), "error evaluating doRun", e);
		}

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[]{"Custom", "Sling", "bstractPostOperation", getClass().getSimpleName(), "doRun"});
		try {
			Weaver.callOriginal();
		} catch (Exception e) {
			if(PersistenceException.class.isInstance(e)) {
				NewRelic.noticeError(e);
				throw (PersistenceException)e;
			}
		}
	}

}

