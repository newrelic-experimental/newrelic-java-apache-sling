
package org.apache.sling.distribution.trigger.impl;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.observation.Event;

import org.apache.sling.distribution.DistributionRequest;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.distrubution.trigger.Util;


@Weave(type = MatchType.BaseClass)
public abstract class AbstractJcrEventTrigger {

	@Trace(dispatcher = true)
	protected DistributionRequest processEvent(Event event) throws RepositoryException {

		Map<String, Object> attrs = new HashMap<>();
		DistributionRequest result =null;

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "Sling", "AbstractJcrEventTrigger", getClass().getSimpleName(), "processEvent"});

		if (event != null) {
			Util.recordValue(attrs, "event.path", event.getPath());
			Util.recordValue(attrs, "event.type", event.getType());

		}


		if (attrs != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attrs);
		}

		try {
			result = Weaver.callOriginal();
		} catch (Exception e) {
			if(RepositoryException.class.isInstance(e)) {
				NewRelic.noticeError(e);
				throw (RepositoryException)e;
			}
		}
		return result;



	}

}
