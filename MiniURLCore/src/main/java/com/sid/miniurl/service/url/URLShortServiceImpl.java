package com.sid.miniurl.service.url;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.sid.miniurl.db.entity.URLDetails;
import com.sid.miniurl.db.entity.URLInfo;
import com.sid.miniurl.db.util.EntityUtil;
import com.sid.miniurl.util.URLShortner;

class URLShortServiceImpl implements URLShortService {
	@Override
	public String makeShort(URL url) {
		String stringURL = url.toString();
		int urlObjectID = insertURLInfo(stringURL);
		return URLShortner.encode(urlObjectID);
	}

	@Override
	public URL getFullURL(String shortCode) throws MalformedURLException {
		int urlID = URLShortner.decode(shortCode);
		String url = getURLInfo(urlID);
		return new URL(url);
	}

	private int insertURLInfo(String stringURL) {
		Session session = EntityUtil.getSessionFactory().openSession();
		session.beginTransaction();
		URLInfo urlInfo = new URLInfo();
		urlInfo.setUrl(stringURL);
		session.persist(urlInfo);
		session.getTransaction().commit();
		closeSession(session);
		return urlInfo.getOid();
	}

	private String getURLInfo(int id) {
		URLInfo urlInfo = getURLInfoByPrimaryKey(id);
		return urlInfo.getUrl();
	}

	private URLInfo getURLInfoByPrimaryKey(int id) {
		Session session = EntityUtil.getSessionFactory().openSession();
		URLInfo urlInfo = (URLInfo) session.get(URLInfo.class, id);
		closeSession(session);
		return urlInfo;
	}

	@Override
	public List<URLDetails> getDetails(String shortCode) {
		int urlID = URLShortner.decode(shortCode);
		System.out.println(" getting url details for " + urlID + " , code " + shortCode);
		Session session = EntityUtil.getSessionFactory().openSession();
		URLInfo urlInfo = (URLInfo) session.get(URLInfo.class, urlID);
		List<URLDetails> urlDetails = urlInfo.getUrlDetails();
		System.out.println(urlDetails);
		closeSession(session);

		return new ArrayList<URLDetails>(urlDetails);
	}

	@Override
	public void addDetails(String shortCode, String countryCode) {
		int urlID = URLShortner.decode(shortCode);
		Session session = EntityUtil.getSessionFactory().openSession();
		session.beginTransaction();
		URLDetails urlDetails = getByUrlIdAndCountry(urlID, countryCode, session);
		if (urlDetails == null) {
			insertURLDetails(urlID, countryCode, session);
		} else {
			updateClickCount(urlDetails, session);
		}
		session.getTransaction().commit();
		session.close();
	}

	private void updateClickCount(URLDetails urlDetails, Session session) {
		urlDetails.setClickCount(urlDetails.getClickCount() + 1);
		session.save(urlDetails);
		session.flush();
	}

	private void insertURLDetails(int urlID, String countryCode, Session session) {
		URLDetails urlDetails = new URLDetails();
		urlDetails.setClickCount(1);
		urlDetails.setCountryCode(countryCode);
		urlDetails.setUrlOID(getURLInfoByPrimaryKey(urlID));
		session.save(urlDetails);
		session.flush();
	}

	private URLDetails getByUrlIdAndCountry(int urlID, String countryCode, Session session) {

		URLDetails urlDetails = (URLDetails) session
				.createQuery("from URLDetails ud where ud.countryCode = :countryCode and ud.urlOID = :urlOID")
				.setString("countryCode", countryCode).setInteger("urlOID", urlID).uniqueResult();
		return urlDetails;
	}

	private void persistAndCloseSession(Object entity, Session session) {
		session.persist(entity);
		closeSession(session);
	}

	private void closeSession(Session session) {
		session.close();
		session.getSessionFactory().close();
	}
}
