package org.apache.sling.api.request.builder.impl;

import com.newrelic.api.agent.weaver.SkipIfPresent;

/**
 * This class exists in org.apache.sling.api 3.0.0+ but not in 2.x.
 * If present on the classpath, this module will be skipped,
 * preventing double instrumentation with the apache-sling-api-3.0 module.
 */
@SkipIfPresent
public class SlingHttpServletRequestBuilderImpl {
}
