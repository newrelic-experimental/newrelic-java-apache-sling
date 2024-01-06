package org.apache.sling.engine.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestPathInfo;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.engine.impl.filter.ServletFilterManager.FilterChainType;
import org.apache.sling.engine.impl.request.DispatchingInfo;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.apache.sling.Util;

@Weave(type = MatchType.BaseClass)
public abstract class SlingRequestProcessorImpl {

	@Trace
	public void doProcessRequest( HttpServletRequest servletRequest,
			HttpServletResponse servletResponse,
			ResourceResolver resourceResolver)  {
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[]{"Custom", "Sling","SlingRequestProcessorImpl", getClass().getSimpleName(), "doProcessRequest"});
		Weaver.callOriginal();

	}

	@Trace
	public void dispatchRequest( ServletRequest request,
			ServletResponse response, final Resource resource,
			RequestPathInfo resolvedURL,
			DispatchingInfo dispatchingInfo)
	{
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[]{"Custom", "Sling", "SlingRequestProcessorImpl", getClass().getSimpleName(), "dispatchRequest"});

		Map<String, Object> attributes = new HashMap<>();
		Util.recordValue(attributes,"resolvedURL.Extension", resolvedURL.getExtension());
		Util.recordValue(attributes,"resolvedURL.ResourcePath", resolvedURL.getResourcePath());
		Util.recordValue(attributes,"resolvedURL.SelectorString", resolvedURL.getSelectorString());
		Util.recordValue(attributes,"dispatchingInfo.ContextPath", dispatchingInfo.getContextPath());
		Util.recordValue(attributes,"dispatchingInfo.PathInfo", dispatchingInfo.getPathInfo());
		Util.recordValue(attributes,"dispatchingInfo.RequestURI", dispatchingInfo.getRequestUri());
		Util.recordValue(attributes,"dispatchingInfo.QueryString", dispatchingInfo.getQueryString());

		if (attributes != null) NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);

		Weaver.callOriginal();

	}

	@Trace
	public void processComponent(SlingHttpServletRequest request,
			SlingHttpServletResponse response,
			FilterChainType filterChainType)  {
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[]{"Custom", "Sling", "SlingRequestProcessorImpl", getClass().getSimpleName(), "processComponent"});
		Weaver.callOriginal();

	}
}
