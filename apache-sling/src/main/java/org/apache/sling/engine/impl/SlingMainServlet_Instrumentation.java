package org.apache.sling.engine.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.newrelic.api.agent.Logger;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.NewField;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(originalName = "org.apache.sling.engine.impl.SlingMainServlet", type = MatchType.BaseClass)
public abstract class SlingMainServlet_Instrumentation {

    @NewField
    private static List<String> customTxnNamingExtensions = null;

    public SlingMainServlet_Instrumentation() {
        Logger nrLogger = NewRelic.getAgent().getLogger();
        customTxnNamingExtensions = new ArrayList<String>();
        Object extensionsParamObject = NewRelic.getAgent().getConfig().getValue("sling.naming.labs.extensions");
        if (extensionsParamObject != null) {
            String extensionParam = (String) extensionsParamObject;
            try {
                String[] customTxnNamingExtensionsArray = extensionParam.split("\\s*,\\s*");
                for (int i = 0; i < customTxnNamingExtensionsArray.length; i++) {
                    String name = customTxnNamingExtensionsArray[i];
                    customTxnNamingExtensions.add(name.trim());
                }
            } catch (Throwable t) {
                nrLogger.log(Level.INFO, "Custom SlingMainServlet Instrumentation - Error setting up custom txn naming extension names ");
                nrLogger.log(Level.FINER, "Custom SlingMainServlet Instrumentation - Error setting up custom txn naming extension names " + t.getMessage());
            }
        } else {
            customTxnNamingExtensions.add("html");
            nrLogger.log(Level.INFO, "Custom SlingMainServlet Instrumentation - sling.naming.labs.extensions not defined.");
            nrLogger.log(Level.INFO, "Custom SlingMainServlet Instrumentation - use \"sling.naming.labs.extensions: [comma separated resource extension names]\" in newrelic.yml");
        }
    }

    @Trace(dispatcher = true)
    public void service(ServletRequest request, ServletResponse response)
            throws javax.servlet.ServletException {
        NewRelic.getAgent().getTracedMethod().setMetricName(new String[]{"Custom", "Sling", "SlingMainServlet", getClass().getSimpleName(), "service"});

        if (customTxnNamingExtensions != null) {
            try {
                if (request instanceof HttpServletRequest) {
                    HttpServletRequest httpRequest = (HttpServletRequest) request;
                    String uri = httpRequest.getRequestURI();
                    if (uri != null) {
                        String[] segments = uri.split("/");
                        if (uri.contains(".")) {
                            String extension = uri.substring(uri.lastIndexOf(".") + 1);
                            if (customTxnNamingExtensions.contains(extension)) {
                                StringBuffer txnNameBuffer = new StringBuffer();
                                for (int i = 0; i < segments.length - 2; i++) {
                                    if (i < 3) {
                                        txnNameBuffer.append(segments[i]).append("/");
                                    } else if (i == 3) {
                                        txnNameBuffer.append("...").append("/");
                                    }
                                }

                                txnNameBuffer.append("*." + extension);

                                String tmptxnName = new String(txnNameBuffer);
                                String txnName = tmptxnName.substring(1);
                                NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.CUSTOM_HIGH, true, "Sling", new String[]{"service", txnName});
                            } else {
                                String txnName = new String("*/*." + extension);
                                NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.CUSTOM_HIGH, true, "Sling", new String[]{"service", txnName});
                            }
                        } else {
                            StringBuffer txnNameBuffer = new StringBuffer();
                            for (int i = 0; i < segments.length - 2; i++) {
                                if (i < 2) {
                                    txnNameBuffer.append(segments[i]).append("/");
                                } else if (i == 2) {
                                    txnNameBuffer.append("...").append("/");
                                }
                            }
                            txnNameBuffer.append(segments[segments.length - 1]);
                            String txnName = new String(txnNameBuffer);
                            NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.CUSTOM_HIGH, true, "Sling", new String[]{"service", txnName});
                        }
                    }

                    String requestURL = httpRequest.getRequestURL().toString();
                    NewRelic.addCustomParameter("request_url", requestURL);
                }
            } catch (Throwable e) {
                NewRelic.getAgent().getLogger().log(Level.INFO, "Custom SlingMainServlet Instrumentation - error evaluating txn naming ");
                NewRelic.getAgent().getLogger().log(Level.FINER, "Custom SlingMainServlet Instrumentation - error evaluating txn naming " + e.getMessage());
            }
        }
        Weaver.callOriginal();
    }
}
