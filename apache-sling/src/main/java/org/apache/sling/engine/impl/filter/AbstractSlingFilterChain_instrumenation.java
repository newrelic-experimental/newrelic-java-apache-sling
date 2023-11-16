package org.apache.sling.engine.impl.filter;

import java.io.IOException;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.newrelic.api.agent.Logger;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Transaction;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(originalName = "org.apache.sling.engine.impl.filter.AbstractSlingFilterChain",type = MatchType.BaseClass)
public abstract class AbstractSlingFilterChain_instrumenation {

	public void doFilter(final ServletRequest request, final ServletResponse response)
            throws ServletException, IOException {
		
		Logger nrLogger = NewRelic.getAgent().getLogger();
		nrLogger.log(Level.FINER, "APACHE-SLING - Starting AbstractSlingFilterChain doFiter method");

		Transaction transaction = NewRelic.getAgent().getTransaction();
		if(transaction != null) {
			NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","Sling","AbstractSlingFilterChain","doFiter"});

		}
		Weaver.callOriginal();
		return ;

	}
}
