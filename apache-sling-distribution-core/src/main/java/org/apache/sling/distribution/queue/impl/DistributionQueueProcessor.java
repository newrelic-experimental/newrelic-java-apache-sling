package org.apache.sling.distribution.queue.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.distribution.queue.DistributionQueueEntry;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.distrubution.trigger.Util;

@Weave(type = MatchType.Interface)
public abstract class DistributionQueueProcessor {

	@Trace(dispatcher = true)
	public boolean process( String queueName,  DistributionQueueEntry queueEntry) {

		Map<String, Object> attrs = new HashMap<>();

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "Sling", "DistributionQueueProcessor", getClass().getSimpleName(), "process"});

		Util.recordValue(attrs, "queue.name", queueName);
		if (queueEntry !=null) {
			Util.recordValue(attrs, "queue.id", queueEntry.getId());
			Util.recordValue(attrs, "queue.state", queueEntry.getStatus().getItemState().name());
		}


		if (attrs != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attrs);
		}

		return Weaver.callOriginal();

	}


}
