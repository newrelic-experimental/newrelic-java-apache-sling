package org.apache.sling.scripting.core.impl;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.sling.api.scripting.SlingBindings;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave
abstract class DefaultSlingScript {

	public abstract String getServletName();
	
	@Trace
	public void service(ServletRequest req, ServletResponse res) {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","Sling","DefaultSlingScript","service",getServletName());
		
		Weaver.callOriginal();
	}
	
	@Trace
	public Object call(SlingBindings props, String method, Object... args) {
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Method", method);
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","Sling","DefaultSlingScript","call");
		return Weaver.callOriginal();
	}
}
