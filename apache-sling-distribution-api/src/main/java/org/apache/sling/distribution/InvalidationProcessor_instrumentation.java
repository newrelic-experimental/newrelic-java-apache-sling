package org.apache.sling.distribution;

import java.util.Map;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(originalName = "org.apache.sling.distribution.InvalidationProcessor", type = MatchType.Interface)
public abstract class InvalidationProcessor_instrumentation {

	@Trace(dispatcher = true)
	public void process(Map<String, Object> props) throws InvalidationProcessException {

		Weaver.callOriginal();
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "Sling", "InvalidationProcessor", getClass().getSimpleName(), "process"});

		if ( props != null ) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(props);
		}

	}
}
