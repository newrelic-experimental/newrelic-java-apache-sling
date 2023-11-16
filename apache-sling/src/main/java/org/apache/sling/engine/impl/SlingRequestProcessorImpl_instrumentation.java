
package org.apache.sling.engine.impl;

import java.io.IOException;
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.sling.api.resource.ResourceResolver;

import com.newrelic.api.agent.Logger;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.Transaction;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(originalName = "org.apache.sling.engine.impl.SlingRequestProcessorImpl",type = MatchType.BaseClass)
public abstract class SlingRequestProcessorImpl_instrumentation {


	@Trace
	public void doProcessRequest(final HttpServletRequest servletRequest,
			final HttpServletResponse servletResponse,
			final ResourceResolver resourceResolver) throws IOException{

		Logger nrLogger = NewRelic.getAgent().getLogger();
		nrLogger.log(Level.FINER, "APACHE-SLING - Starting SlingRequestProcessorImpl doProcessRequest method");

		Transaction transaction = NewRelic.getAgent().getTransaction();
		if(transaction != null) {
			NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","Sling","SlingRequestProcessorImpl","doProcessRequest"});

		}
		Weaver.callOriginal();
		return ;


	}
}
