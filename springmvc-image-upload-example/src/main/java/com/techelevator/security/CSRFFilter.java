package com.techelevator.security;

import java.io.IOException;
import java.security.SecureRandom;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

public class CSRFFilter implements Filter {

	private static final String CSRF_TOKEN = "CSRF_TOKEN";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
				
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
				
		if(isGET(httpRequest)) {
			handleGET(httpRequest);
		} else {
			if (isMultipartRequest(httpRequest)) {
				
				String sessionToken = (String)httpRequest.getSession().getAttribute(CSRF_TOKEN);
		          CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		         
		          
		          MultipartHttpServletRequest multipartRequest = commonsMultipartResolver.resolveMultipart(httpRequest);

	                String requestToken = multipartRequest.getParameter("CSRF_TOKEN");
	                handlePOST(requestToken, sessionToken, httpResponse);
	
			} else {
				handlePOST(httpRequest, httpResponse);
			}
			
		}
		if(!response.isCommitted()) {
			chain.doFilter(httpRequest, httpResponse);
		}
	}

	
	private void handlePOST(HttpServletRequest request, HttpServletResponse httpResponse) throws IOException {
		
		String requestToken = (String)(request).getParameter(CSRF_TOKEN);
		String sessionToken = (String)(request).getSession().getAttribute(CSRF_TOKEN);
		handlePOST(requestToken, sessionToken, httpResponse);
	}
	
	private void handlePOST(String requestToken, String sessionToken, HttpServletResponse httpResponse) throws IOException {
		
		if(sessionToken == null || sessionToken.equals(requestToken) == false) {
			httpResponse.sendError(400);
		}
	}


	private boolean isMultipartRequest(ServletRequest request) {
		if (request.getContentType() != null && request.getContentType().contains("multipart/form-data")) {
			return true;
		}
		return false;
	}
	
	private void handleGET(HttpServletRequest httpRequest) {
		if(sessionDoesNotContainToken(httpRequest)) {
			String csrfToken = generateNewToken();
			httpRequest.getSession().setAttribute(CSRF_TOKEN, csrfToken);
		}
	}

	private boolean isGET(HttpServletRequest httpRequest) {
		return httpRequest.getMethod().equalsIgnoreCase("GET");
	}

	private String generateNewToken() {
		SecureRandom random = new SecureRandom();
		String csrfToken = new String(Base64.encode(random.generateSeed(16)));
		return csrfToken;
	}

	private boolean sessionDoesNotContainToken(HttpServletRequest httpRequest) {
		return httpRequest.getSession().getAttribute(CSRF_TOKEN) == null;
	}

	@Override
	public void destroy() {
	}

}
