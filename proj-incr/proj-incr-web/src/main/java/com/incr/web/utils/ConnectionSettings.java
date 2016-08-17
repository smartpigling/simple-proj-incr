package com.incr.web.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="conn.url")
public class ConnectionSettings {

	private String url;

	public String getUrl() {
		return url;
	}
	
	
}
