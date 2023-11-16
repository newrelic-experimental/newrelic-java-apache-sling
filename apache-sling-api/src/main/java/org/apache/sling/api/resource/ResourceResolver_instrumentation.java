
package org.apache.sling.api.resource;

import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.newrelic.api.agent.Logger;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.Transaction;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import org.apache.sling.api.resource.ResourceResolver;

@Weave(originalName = "org.apache.sling.api.resource.ResourceResolver", type = MatchType.Interface)
public abstract class ResourceResolver_instrumentation {

	
	@Trace
	public Resource resolve(HttpServletRequest req, String var1) {

		Resource res=Weaver.callOriginal();
		
		if (req instanceof HttpServletRequest) {

			Transaction transaction = NewRelic.getAgent().getTransaction();
			if(transaction != null) {
				NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","Sling","resolve",res.getName()});

				transaction.setTransactionName(TransactionNamePriority.FRAMEWORK_LOW, true, "Sling",
						new String[] { "Sling", "ResourceResolver", "resolve"});

				Logger nrLogger = NewRelic.getAgent().getLogger();
				nrLogger.log(Level.FINER, "APACHE-SLING - Starting ResourceResolver resolve method");
				
			
				
			}

		}
		return res;
	}
	
	
	@Trace
	public Resource resolve(HttpServletRequest req) {

		Resource res=Weaver.callOriginal();
		
		if (req instanceof HttpServletRequest) {
		
			Transaction transaction = NewRelic.getAgent().getTransaction();
			if(transaction != null) {
				NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","Sling","resolve",res.getName()});

				transaction.setTransactionName(TransactionNamePriority.FRAMEWORK_LOW, true, "Sling",
						new String[] { "Sling", "ResourceResolver", "resolve"});

				Logger nrLogger = NewRelic.getAgent().getLogger();
				nrLogger.log(Level.FINER, "APACHE-SLING - Starting ResourceResolver resolve method");
				
			
				
			}

		}
		return res;
	}
}