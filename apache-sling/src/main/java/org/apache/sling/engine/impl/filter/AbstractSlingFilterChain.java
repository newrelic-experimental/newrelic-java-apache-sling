package org.apache.sling.engine.impl.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type = MatchType.BaseClass)
public abstract class AbstractSlingFilterChain {

	public void doFilter( ServletRequest request,  ServletResponse response)
	{


		NewRelic.getAgent().getTracedMethod().setMetricName(new String[]{"Custom", "Sling", "AbstractSlingFilterChain", getClass().getSimpleName(), "doFilter"});

		Weaver.callOriginal();
	}
}
