package org.apache.sling.servlets.get.impl;

import java.io.IOException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.apache.sling.servlets.get.Util;

@Weave(type = MatchType.BaseClass)
public abstract class SlingInfoServlet {

	@Trace(dispatcher = true)
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws IOException {

		if (request != null) {
			Util.recordRequestAttributes(request);
		}

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[]{"Custom", "Sling", "SlingInfoServlet",getClass().getSimpleName(), "doGet"});
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
