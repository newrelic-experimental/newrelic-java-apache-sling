package org.apache.sling.distribution;

import java.util.Map;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type = MatchType.Interface)
public abstract class InvalidationProcessor {

	@Trace(dispatcher = true)
	public void process(Map<String, Object> props) throws InvalidationProcessException {


		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "Sling", "InvalidationProcessor", getClass().getSimpleName(), "process"});

		if ( props != null ) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(props);
		}

		try {
			Weaver.callOriginal();
		} catch (Exception e) {
			if(InvalidationProcessException.class.isInstance(e)) {
				NewRelic.noticeError(e);
				throw (InvalidationProcessException)e;
			}
		}

	}
}
