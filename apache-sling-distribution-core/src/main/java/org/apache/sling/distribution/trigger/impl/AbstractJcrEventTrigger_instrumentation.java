
package org.apache.sling.distribution.trigger.impl;

import java.util.HashMap;
import javax.jcr.RepositoryException;
import javax.jcr.observation.Event;

import java.util.Map;
import java.util.logging.Level;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.distribution.DistributionRequest;
import org.apache.sling.distribution.DistributionResponse;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.distrubution.trigger.Util;


@Weave(originalName = "org.apache.sling.distribution.trigger.impl.AbstractJcrEventTrigger", type = MatchType.BaseClass)
public abstract class AbstractJcrEventTrigger_instrumentation {

	@Trace(dispatcher = true)
	 protected DistributionRequest processEvent(Event event) throws RepositoryException {

		Map<String, Object> attrs = new HashMap<>();

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "Sling", "AbstractJcrEventTrigger", getClass().getSimpleName(), "processEvent"});
		try {


			if (event != null) {
				Util.recordValue(attrs, "event.path", event.getPath());
				Util.recordValue(attrs, "event.type", event.getType());
			
			}

		}
		catch (Exception e) {
			handleException("error evaluating processEvent", e);

		}
		if (attrs != null) {
            NewRelic.getAgent().getTracedMethod().addCustomAttributes(attrs);
        }
		
		return Weaver.callOriginal();



	}
	private void handleException(String message, Throwable e) {
		NewRelic.getAgent().getLogger().log(Level.INFO, "Custom AbstractJcrEventTrigger Instrumentation - " + message);
		NewRelic.getAgent().getLogger().log(Level.FINER, "Custom AbstractJcrEventTrigger Instrumentation - " + message + ": " + e.getMessage());
	}
}
