package com.set.config;
import java.util.EnumSet;
import java.util.TimeZone;
import java.util.logging.Logger;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer{
	private Logger logger = Logger.getLogger("WebInit.class");
	
//	@Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        super.onStartup(servletContext);
//
//        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);
//
////        javax.servlet.FilterRegistration.Dynamic auditRequest = servletContext.addFilter("domainRequestFilter", DelegatingFilterProxy.class);
////        auditRequest.addMappingForUrlPatterns(dispatcherTypes, false, "/*");
//
//        /*
//         * javax.servlet.FilterRegistration.Dynamic sessionFiltr = servletContext.addFilter("sessionFilter",
//         * SessionFilter.class); sessionFiltr.addMappingForUrlPatterns(dispatcherTypes, false, "/*");
//         */
//        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
//    }

	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		logger.info("getRootConfigClasses=============");
		return new Class[] { AppConfig.class,WebSecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		logger.info("getServletConfigClasses=============");
		return new Class[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		logger.info("getServletMappings=============");
		logger.info("getServletMappings   new =============");
		return new String[] { "/" };
	}

}
