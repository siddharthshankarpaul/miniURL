package com.sid.miniurl.service.url;

public class URLShortServiceProvider {
	public static URLShortService getURLShortService() {
		return new URLShortServiceImpl();
	}
}
