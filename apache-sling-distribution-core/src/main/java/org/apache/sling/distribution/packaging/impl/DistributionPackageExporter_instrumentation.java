package org.apache.sling.distribution.packaging.impl;

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

@Weave(originalName = "org.apache.sling.distribution.packaging.impl.DistributionPackageExporter", type = MatchType.Interface)
public abstract class DistributionPackageExporter_instrumentation {

    @Trace(dispatcher = true)
    public void exportPackages( ResourceResolver resourceResolver,  DistributionRequest distributionRequest,
        DistributionPackageProcessor packageProcessor) throws DistributionException {

        Map<String, Object> attrs = new HashMap<>();

        NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "Sling", "DistributionPackageExporter", getClass().getSimpleName(), "forward"});
        try {
            if (distributionRequest != null && packageProcessor != null) {
                // Modify this section based on the methods and properties of DistributionRequest
            	Util.recordValue(attrs, "request", distributionRequest); // toString.
                Util.recordValue(attrs, "package", packageProcessor.toString());
                Util.recordValue(attrs, "package.size", packageProcessor.getPackagesSize());
                Util.recordValue(attrs, "package.count", packageProcessor.getPackagesCount());
            }

            Util.recordResourceResolver(attrs, resourceResolver);

        } catch (Exception e) {
            handleException("error evaluating exportPackages", e);
        }

        if (attrs != null) {
            NewRelic.getAgent().getTracedMethod().addCustomAttributes(attrs);
        }

        Weaver.callOriginal();
    }

    @Trace(dispatcher = true)
    public DistributionPackage getPackage( ResourceResolver resourceResolver,  String distributionPackageId) throws DistributionException {

        Map<String, Object> attrs = new HashMap<>();

        NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "Sling", "DistributionPackageExporter", getClass().getSimpleName(), "forward"});
        try {
            Util.recordValue(attrs, "distributionPackageId", distributionPackageId); // toString.
        	Util.recordResourceResolver(attrs, resourceResolver);

        } catch (Exception e) {
            handleException("error evaluating getPackage", e);
        }

        if (attrs != null) {
            NewRelic.getAgent().getTracedMethod().addCustomAttributes(attrs);
        }


		return Weaver.callOriginal();
    }



    private void handleException(String message, Throwable e) {
        NewRelic.getAgent().getLogger().log(Level.INFO, "Custom DistributionPackageExporter Instrumentation - " + message);
        NewRelic.getAgent().getLogger().log(Level.FINER, "Custom DistributionPackageExporter Instrumentation - " + message + ": " + e.getMessage());
    }
}
