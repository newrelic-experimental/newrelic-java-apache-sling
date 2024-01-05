package org.apache.sling.distribution.packaging.impl;


import java.util.HashMap;
import java.util.Map;

import org.apache.sling.distribution.packaging.DistributionPackage;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.distrubution.trigger.Util;

@Weave(originalName = "org.apache.sling.distribution.packaging.impl.DistributionPackageProcessor", type = MatchType.Interface)
public abstract class DistributionPackageProcessor_instrumentation {

	@Trace(dispatcher = true)
	public void process(DistributionPackage distributionPackage) {

		Map<String, Object> attrs = new HashMap<>();

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "Sling", "DistributionPackageProcessor", getClass().getSimpleName(), "forward"});
		try {
			if (distributionPackage != null ) {
				// Modify this section based on the methods and properties of DistributionRequest

				Util.recordValue(attrs, "package,name", distributionPackage);
				Util.recordValue(attrs, "package.type", distributionPackage.getType());
				Util.recordValue(attrs, "package.size", distributionPackage.getSize());
			}



		} catch (Exception e) {
			Util.handleException(getClass().getSimpleName(),"error evaluting process", e);
		}

		if (attrs != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attrs);
		}

		Weaver.callOriginal();
	}


}
