package org.apache.sling.servlets.post;


import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.servlets.post.Modification;
import org.apache.sling.servlets.post.PostResponse;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type = MatchType.BaseClass)
public abstract class AbstractPostOperation {

	@Trace(dispatcher = true)
	protected void doRun(SlingHttpServletRequest request, PostResponse response, List<Modification> changes) {


		NewRelic.getAgent().getTracedMethod().setMetricName(new String[]{"Custom", "Sling", "bstractPostOperation", getClass().getSimpleName(), "doRun"});
		Weaver.callOriginal();
	}

}

