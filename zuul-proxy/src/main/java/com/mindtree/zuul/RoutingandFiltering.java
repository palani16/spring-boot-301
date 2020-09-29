package com.mindtree.zuul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

public class RoutingandFiltering extends ZuulFilter{

	private static Logger logger = LoggerFactory.getLogger(RoutingandFiltering.class); 
	
	@Override
	public boolean shouldFilter() {
		return false;
	}

	@Override
	public Object run() throws ZuulException {
		return null;
	}

	@Override
	public String filterType() {
		return null;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}
