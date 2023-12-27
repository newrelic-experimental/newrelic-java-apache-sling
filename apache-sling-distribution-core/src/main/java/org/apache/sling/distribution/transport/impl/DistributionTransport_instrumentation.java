package org.apache.sling.distribution.transport.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.distribution.DistributionRequest;
import org.apache.sling.distribution.common.DistributionException;
import org.apache.sling.distribution.packaging.DistributionPackage;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.distrubution.trigger.Util;

@Weave(originalName = "org.apache.sling.distribution.transport.impl.DistributionTransport", type = MatchType.Interface)
public abstract class DistributionTransport_instrumentation {

    @Trace(dispatcher = true)
    public void deliverPackage(ResourceResolver resourceResolver, DistributionPackage distributionPackage,
             DistributionTransportContext context) throws DistributionException {

        Map<String, Object> attrs = new HashMap<>();

        NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "Sling", "DistributionTransport", getClass().getSimpleName(), "forward"});
        try {
            if (distributionPackage != null) {
                // Modify this section based on the methods and properties of DistributionRequest
                Util.recordValue(attrs, "package.name", distributionPackage);
                Util.recordValue(attrs, "package.type", distributionPackage.getType());
                Util.recordValue(attrs, "package.size", distributionPackage.getSize());
            }

            Util.recordResourceResolver(attrs, resourceResolver);

        } catch (Exception e) {
            handleException("error evaluating deliverPackage", e);
        }

        if (attrs != null) {
            NewRelic.getAgent().getTracedMethod().addCustomAttributes(attrs);
        }

        Weaver.callOriginal();
    }

    @Trace(dispatcher = true)
    public RemoteDistributionPackage retrievePackage( ResourceResolver resourceResolver,  DistributionRequest request,
         DistributionTransportContext context) throws DistributionException {

        Map<String, Object> attrs = new HashMap<>();

        NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "Sling", "DistributionTransport", getClass().getSimpleName(), "forward"});
        try {
            Util.recordValue(attrs, "request", request); // toString.
        	Util.recordResourceResolver(attrs, resourceResolver);

        } catch (Exception e) {
            handleException("error evaluating retrievePackage", e);
        }

        if (attrs != null) {
            NewRelic.getAgent().getTracedMethod().addCustomAttributes(attrs);
        }


		return Weaver.callOriginal();
    }



    private void handleException(String message, Throwable e) {
        NewRelic.getAgent().getLogger().log(Level.INFO, "Custom DistributionTransport Instrumentation - " + message);
        NewRelic.getAgent().getLogger().log(Level.FINER, "Custom DistributionTransport Instrumentation - " + message + ": " + e.getMessage());
    }
}
