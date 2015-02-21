package com.sid.miniurl.service.url;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.sid.miniurl.db.entity.URLDetails;

public interface URLShortService {

	String makeShort(URL url);

	URL getFullURL(String shortCode) throws MalformedURLException;

	List<URLDetails> getDetails(String shortCode);

	void addDetails(String shortCode, String countryCode);
}
