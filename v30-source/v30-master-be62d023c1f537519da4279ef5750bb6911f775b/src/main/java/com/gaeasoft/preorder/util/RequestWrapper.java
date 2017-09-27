package com.gaeasoft.preorder.util;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestWrapper {

	private HttpServletRequest request;

	private RequestWrapper(HttpServletRequest request) {
		this.request = request;
	}

	public static RequestWrapper of(HttpServletRequest request) {
		return new RequestWrapper(request);
	}

	public static RequestWrapper of(ServletRequest request) {
		return of((HttpServletRequest) request);
	}

	public Map<String, String> headerMap() {
		Map<String, String> convertedHeaderMap = new HashMap<>();

		Enumeration<String> headerMap = request.getHeaderNames();

		while (headerMap.hasMoreElements()) {
			String name = headerMap.nextElement();
			String value = request.getHeader(name);

			convertedHeaderMap.put(name, value);
		}
		return convertedHeaderMap;
	}

	public Map<String, String> parameterMap() {
		Map<String, String> convertedParameterMap = new HashMap<>();
		Map<String, String[]> parameterMap = request.getParameterMap();

		for (String key : parameterMap.keySet()) {
			String[] values = parameterMap.get(key);
			StringBuffer valueString = new StringBuffer();

			for (int i = 0; i < values.length; i++) {
				valueString.append(values[i]);
				if (i < values.length -1) {
					valueString.append(",");
				}
			}

			convertedParameterMap.put(key, valueString.toString());
		}
		return convertedParameterMap;
	}

	public String getRequestUri() {
		return request.getRequestURI();
	}
}

