package com.sid.miniurl;

import org.hibernate.Session;
import org.junit.Test;

import com.sid.miniurl.db.entity.URLDetails;
import com.sid.miniurl.db.entity.URLInfo;
import com.sid.miniurl.db.util.EntityUtil;

public class URLInfoTest {

	@Test
	public void test() {
		Session ses = EntityUtil.getSessionFactory().openSession();
		ses.beginTransaction();
		URLInfo ui = new URLInfo();
		ui.setUrl("test/url");
		ses.persist(ui);
		ses.getTransaction().commit();
		ses.delete(ui);
		ses.beginTransaction();
		ses.getTransaction().commit();
		ses.close();
	}

	@Test
	public void testURLDetails() {
		Session ses = EntityUtil.getSessionFactory().openSession();
		ses.beginTransaction();
		URLInfo ui = new URLInfo();
		ui.setUrl("test/url");
		URLDetails details = new URLDetails();
		details.setCountryCode("IN");
		details.setClickCount(5);
		ui.getUrlDetails().add(details);
		ses.persist(ui);
		ses.getTransaction().commit();
		System.out.println(ui);
		System.out.println(ui.getUrlDetails());

	}
}
