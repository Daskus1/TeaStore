package tools.descartes.petsupplystore.registryclient.rest;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class TrackingFilter implements Filter {

	Logger logger = Logger.getLogger(TrackingFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		BasicConfigurator.configure();
		logger.setLevel(Level.INFO);
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			String trackingInfo = httpRequest.getHeader("KiekerTracingInfo");
			if (trackingInfo != null && !trackingInfo.equals(""))
				logger.info(trackingInfo);
		} else {
			logger.error("Something went wrong");
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
