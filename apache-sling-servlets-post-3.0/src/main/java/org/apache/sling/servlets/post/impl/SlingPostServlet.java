package org.apache.sling.servlets.post.impl;

import java.io.IOException;

import org.apache.sling.api.SlingJakartaHttpServletRequest;
import org.apache.sling.api.SlingJakartaHttpServletResponse;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.apache.sling.servlets.post.Util;

@Weave(type = MatchType.BaseClass)
public abstract class SlingPostServlet  {

	@Trace(dispatcher = true)
	protected void doPost(SlingJakartaHttpServletRequest request,
			SlingJakartaHttpServletResponse response) throws IOException {

		if (request != null) {
			Util.recordRequestAttributes(request);
		}

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[]{"Custom", "Sling", "SlingPostServlet", getClass().getSimpleName(), "doPost"});
		try {
			Weaver.callOriginal();
		} catch (Exception e) {
			if(IOException.class.isInstance(e)) {
				NewRelic.noticeError(e);
				throw (IOException)e;
			}
		}
	}
}
