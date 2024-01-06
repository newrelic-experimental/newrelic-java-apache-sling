package org.apache.sling.api.resource;


import javax.servlet.http.HttpServletRequest;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;

import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type = MatchType.Interface)
public abstract class ResourceResolver {

    @Trace
    public Resource resolve(HttpServletRequest req) {
        Resource res = Weaver.callOriginal();

        if (req instanceof HttpServletRequest) {
            NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "Sling","ResourceResolver", getClass().getSimpleName(), "resolve", res.getName()});
        }
        return res;
    }

    @Trace
    public Resource resolve(HttpServletRequest req, String var1) {
        Resource res = Weaver.callOriginal();

        if (req instanceof HttpServletRequest) {
            NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "Sling", "ResourceResolver", getClass().getSimpleName(), "resolve", res.getName()});
        }
        return res;
    }
}
