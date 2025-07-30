package org.apache.sling.engine.impl.filter;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

@Weave(type = MatchType.BaseClass)
public abstract class AbstractSlingFilterChain {

	public void doFilter( ServletRequest request,  ServletResponse response)
	{


		NewRelic.getAgent().getTracedMethod().setMetricName(new String[]{"Custom", "Sling", "AbstractSlingFilterChain", getClass().getSimpleName(), "doFilter"});

		Weaver.callOriginal();
	}
}
