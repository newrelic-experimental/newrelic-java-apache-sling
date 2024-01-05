package org.apache.sling.distribution.packaging.impl;

import java.util.HashMap;
import java.util.Map;

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

@Weave(type = MatchType.Interface)
public abstract class DistributionPackageExporter {

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
			Util.handleException(getClass().getSimpleName(),"error evaluating exportPackages", e);
		}

		if (attrs != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attrs);
		}

		try {
			Weaver.callOriginal();
		} catch (Exception e) {
			if(DistributionException.class.isInstance(e)) {
				NewRelic.noticeError(e);
				throw (DistributionException)e;
			}
		}

	}

	@Trace(dispatcher = true)
	public DistributionPackage getPackage( ResourceResolver resourceResolver,  String distributionPackageId) throws DistributionException {

		Map<String, Object> attrs = new HashMap<>();
		DistributionPackage result =null;

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "Sling", "DistributionPackageExporter", getClass().getSimpleName(), "forward"});
		try {
			Util.recordValue(attrs, "distributionPackageId", distributionPackageId); // toString.
			Util.recordResourceResolver(attrs, resourceResolver);

		} catch (Exception e) {
			Util.handleException(getClass().getSimpleName(),"error evaluating getPackage", e);
		}

		if (attrs != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attrs);
		}


		try {
			result = Weaver.callOriginal();
		} catch (Exception e) {
			if(DistributionException.class.isInstance(e)) {
				NewRelic.noticeError(e);
				throw (DistributionException)e;
			}
		}
		return result;
	}




}
