package org.apache.sling.servlets.post;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.servlets.post.exceptions.PreconditionViolatedPersistenceException;
import org.apache.sling.servlets.post.exceptions.TemporaryPersistenceException;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type = MatchType.Interface)
public abstract class PostOperation {

	@Trace(dispatcher = true)
	public void run(SlingHttpServletRequest request, PostResponse response,
			SlingPostProcessor[] processors) throws PreconditionViolatedPersistenceException, TemporaryPersistenceException, PersistenceException {



		NewRelic.getAgent().getTracedMethod().setMetricName(new String[]{"Custom", "Sling", "PostOperation", getClass().getSimpleName(), "run"});
		try {
			Weaver.callOriginal();
		} catch (Exception e) {
			if(PreconditionViolatedPersistenceException.class.isInstance(e)) {
				NewRelic.noticeError(e);
				throw (PreconditionViolatedPersistenceException)e;
			} else if(TemporaryPersistenceException.class.isInstance(e)) {
				NewRelic.noticeError(e);
				throw (TemporaryPersistenceException)e;

			}
			else if(PersistenceException.class.isInstance(e)) {
				NewRelic.noticeError(e);
				throw (PersistenceException)e;

			}
		}
	}


}
