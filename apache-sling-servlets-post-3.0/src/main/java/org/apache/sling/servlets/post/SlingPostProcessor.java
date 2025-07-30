package org.apache.sling.servlets.post;

import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.apache.sling.servlets.post.Util;

@Weave(type = MatchType.Interface)
public abstract class SlingPostProcessor {

	@Trace(dispatcher = true)
	public void process(SlingHttpServletRequest request, List<Modification> changes)
			throws Exception {


		NewRelic.getAgent().getTracedMethod().setMetricName(new String[]{"Custom", "Sling", "SlingPostProcessor", getClass().getSimpleName(), "process"});
		try {
			Weaver.callOriginal();
		} catch (Exception e) {

			NewRelic.noticeError(e);
			throw e;

		}
	}
}



