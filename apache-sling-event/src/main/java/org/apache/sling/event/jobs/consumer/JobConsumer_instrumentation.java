package org.apache.sling.event.jobs.consumer;

import org.apache.sling.event.jobs.Job;
import org.apache.sling.event.jobs.consumer.JobConsumer.JobResult;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.NewField;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.WeaveAllConstructors;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type = MatchType.Interface, originalName = "org.apache.sling.event.jobs.consumer.JobConsumer")
public abstract class JobConsumer_instrumentation {

	@Trace(dispatcher = true)
	public JobResult process(Job job) {
		return Weaver.callOriginal();
	}
	
	@Weave(type = MatchType.Interface, originalName = "org.apache.sling.event.jobs.consumer.JobConsumer$AsyncHandler")
	public static class AsyncHandler {
		
		@NewField
		private Token token = null;
		
		@WeaveAllConstructors
		public AsyncHandler() {
			if(token == null) {
				Token t = NewRelic.getAgent().getTransaction().getToken();
				if(t != null && t.isActive()) {
					token = t;
				} else if(t != null) {
					t.expire();
					t = null;
				}
			}
		}
		
		@Trace(async = true)
		public void failed() {
			if(token != null) {
				token.linkAndExpire();
				token = null;
			}
			Weaver.callOriginal();
		}
		
		@Trace(async = true)
		public void ok() {
			if(token != null) {
				token.linkAndExpire();
				token = null;
			}
			Weaver.callOriginal();
		}

		@Trace(async = true)
		public void cancel() {
			if(token != null) {
				token.linkAndExpire();
				token = null;
			}
			Weaver.callOriginal();
		}

	}
}
