package org.apache.sling.engine.impl.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(originalName = "org.apache.sling.engine.impl.filter.AbstractSlingFilterChain", type = MatchType.BaseClass)
public abstract class AbstractSlingFilterChain_instrumenation {

    public void doFilter( ServletRequest request,  ServletResponse response)
            throws ServletException, IOException {

      
        NewRelic.getAgent().getTracedMethod().setMetricName(new String[]{"Custom", "Sling", getClass().getSimpleName(), "doFilter"});

        Weaver.callOriginal();
        return;
    }
}
