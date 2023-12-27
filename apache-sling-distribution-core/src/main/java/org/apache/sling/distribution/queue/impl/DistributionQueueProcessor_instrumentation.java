package org.apache.sling.distribution.queue.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.apache.sling.distribution.DistributionRequestType;
import org.apache.sling.distribution.queue.DistributionQueueEntry;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.distrubution.trigger.Util;

@Weave(originalName = "org.apache.sling.distribution.queue.impl.DistributionQueueProcessor", type = MatchType.Interface)
public abstract class DistributionQueueProcessor_instrumentation {

    @Trace(dispatcher = true)
    public boolean process( String queueName,  DistributionQueueEntry queueEntry) {

        Map<String, Object> attrs = new HashMap<>();

        NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "Sling", "DistributionQueueProcessor", getClass().getSimpleName(), "process"});
        try {
            
            Util.recordValue(attrs, "queue.name", queueName);
            if (queueEntry !=null) {
            	Util.recordValue(attrs, "queue.id", queueEntry.getId());
            	Util.recordValue(attrs, "queue.state", queueEntry.getStatus().getItemState().name());
            }
            
        } catch (Exception e) {
            handleException("error evaluating process", e);
        }

        if (attrs != null) {
            NewRelic.getAgent().getTracedMethod().addCustomAttributes(attrs);
        }

        Weaver.callOriginal();
		return false;
    }

    private void handleException(String message, Throwable e) {
        NewRelic.getAgent().getLogger().log(Level.INFO, "Custom DistributionQueueProcessor Instrumentation - " + message);
        NewRelic.getAgent().getLogger().log(Level.FINER, "Custom DistributionQueueProcessor Instrumentation - " + message + ": " + e.getMessage());
    }
}
