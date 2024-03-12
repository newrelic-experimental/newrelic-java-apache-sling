package org.apache.sling.event.impl.jobs.queues;

import org.apache.sling.event.impl.jobs.JobHandler;
import org.apache.sling.event.impl.jobs.JobImpl;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TracedMethod;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave
public abstract class JobQueueImpl {

	@Trace(dispatcher = true)
	private void startJob(final JobHandler handler) {
		JobImpl job = handler.getJob();
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		
		traced.addCustomAttribute("QueueName",job.getQueueName());
		traced.addCustomAttribute("Topic", job.getTopic());
		Weaver.callOriginal();
	}
}
