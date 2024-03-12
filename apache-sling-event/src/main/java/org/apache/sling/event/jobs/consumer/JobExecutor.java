package org.apache.sling.event.jobs.consumer;

import org.apache.sling.event.jobs.Job;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TracedMethod;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type = MatchType.Interface)
public abstract class JobExecutor {

	@Trace
	public JobExecutionResult process(Job job, JobExecutionContext context) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.setMetricName("Custom","Sling","JobExecutor",getClass().getSimpleName(),"process");
		traced.addCustomAttribute("QueueName", job.getQueueName());
		traced.addCustomAttribute("Topic", job.getTopic());
		JobExecutionResult jobResult = Weaver.callOriginal();
		if(jobResult != null) {
			String jobResultStr = null;
			if(jobResult.cancelled()) {
				jobResultStr = "Cancelled";
			} else if(jobResult.failed()) {
				jobResultStr = "Failed";
			} else if(jobResult.succeeded()) {
				jobResultStr = "Succeeded";
			}
			traced.addCustomAttribute("JobResult", jobResultStr);
		}
		return jobResult;
	}
}
