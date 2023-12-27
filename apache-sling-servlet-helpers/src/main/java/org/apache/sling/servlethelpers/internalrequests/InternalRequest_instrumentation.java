package org.apache.sling.servlethelpers.internalrequests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.apache.sling.servlet.helpers.Util;

@Weave(originalName = "org.apache.sling.servlethelpers.internalrequests.InternalRequest", type = MatchType.BaseClass)
public abstract class InternalRequest_instrumentation {

    protected InternalRequest_instrumentation(ResourceResolver resourceResolver, String path) {}

    @Trace(dispatcher = true)
    public InternalRequest execute() throws IOException {
        NewRelic.getAgent().getTracedMethod().setMetricName(new String[]{"Custom", "Sling", "InternalRequest", getClass().getSimpleName(), "execute"});
        return Weaver.callOriginal();
    }

    @Trace
    protected void delegateExecute(SlingHttpServletRequest request, SlingHttpServletResponse response, ResourceResolver resourceResolver)
            throws ServletException, IOException {

        try {
            if (request != null) {
               Util.recordRequestAttributes(request);
            }
        } catch (Exception e) {
            handleException("error evaluating delegateExecute", e);
        }

        NewRelic.getAgent().getTracedMethod().setMetricName(new String[]{"Custom", "Sling", "InternalRequest",getClass().getSimpleName(), "delegateExecute"});
        Weaver.callOriginal();
    }

   

    private void handleException(String message, Throwable e) {
        NewRelic.getAgent().getLogger().log(Level.INFO, "Custom InternalRequest Instrumentation - " + message);
        NewRelic.getAgent().getLogger().log(Level.FINER, "Custom InternalRequest Instrumentation - " + message + ": " + e.getMessage());
    }
}
