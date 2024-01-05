package org.apache.sling.distribution.transport.impl;

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

@Weave(originalName = "org.apache.sling.distribution.transport.impl.DistributionTransport", type = MatchType.Interface)
public abstract class DistributionTransport {

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
			Util.handleException(getClass().getSimpleName(),"error evaluating deliverPackage", e);
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
	public RemoteDistributionPackage retrievePackage( ResourceResolver resourceResolver,  DistributionRequest request,
			DistributionTransportContext context) throws DistributionException {

		Map<String, Object> attrs = new HashMap<>();

		RemoteDistributionPackage result = null;

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "Sling", "DistributionTransport", getClass().getSimpleName(), "forward"});
		try {
			Util.recordValue(attrs, "request", request); // toString.
			Util.recordResourceResolver(attrs, resourceResolver);

		} catch (Exception e) {
			Util.handleException(getClass().getSimpleName(),"error evaluating retrievePackage", e);
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
