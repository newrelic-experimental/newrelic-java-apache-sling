package org.apache.sling.engine.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
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
import com.newrelic.api.agent.weaver.NewField;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(originalName = "org.apache.sling.engine.impl.SlingRequestProcessorImpl", type = MatchType.BaseClass)
public abstract class SlingRequestProcessorImpl_instrumentation {

   @NewField
	private Map<String, Object> attributes = null;

    private void addAttribute(String key, Object value) {
        if (attributes == null) attributes = new HashMap<>();
        attributes.put(key, value);
    }

    @Trace
    public void doProcessRequest( HttpServletRequest servletRequest,
                                   HttpServletResponse servletResponse,
                                   ResourceResolver resourceResolver) throws IOException {
        NewRelic.getAgent().getTracedMethod().setMetricName(new String[]{"Custom", "Sling", getClass().getSimpleName(), "doProcessRequest"});
        Weaver.callOriginal();
        return;
    }

    @Trace
    public void dispatchRequest( ServletRequest request,
                                 ServletResponse response, final Resource resource,
                                 RequestPathInfo resolvedURL,
                                 DispatchingInfo dispatchingInfo)
            throws IOException, ServletException {
        NewRelic.getAgent().getTracedMethod().setMetricName(new String[]{"Custom", "Sling", getClass().getSimpleName(), "dispatchRequest"});

        addAttribute("resolvedURL.Extension", resolvedURL.getExtension());
        addAttribute("resolvedURL.ResourcePath", resolvedURL.getResourcePath());
        addAttribute("resolvedURL.SelectorString", resolvedURL.getSelectorString());
        addAttribute("dispatchingInfo.ContextPath", dispatchingInfo.getContextPath());
        addAttribute("dispatchingInfo.PathInfo", dispatchingInfo.getPathInfo());
        addAttribute("dispatchingInfo.RequestURI", dispatchingInfo.getRequestUri());
        addAttribute("dispatchingInfo.QueryString", dispatchingInfo.getQueryString());
        if (attributes != null) NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);

        Weaver.callOriginal();
        return;
    }

    @Trace
    public void processComponent(SlingHttpServletRequest request,
                                 SlingHttpServletResponse response,
                                 FilterChainType filterChainType) throws IOException,
            ServletException {
        NewRelic.getAgent().getTracedMethod().setMetricName(new String[]{"Custom", "Sling", getClass().getSimpleName(), "processComponent"});
        Weaver.callOriginal();
        return;
    }
}
