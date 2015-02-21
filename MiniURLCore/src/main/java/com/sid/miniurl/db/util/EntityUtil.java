package com.sid.miniurl.db.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.sid.miniurl.db.entity.URLDetails;
import com.sid.miniurl.db.entity.URLInfo;

public class EntityUtil {

	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		configuration.addAnnotatedClass(URLInfo.class);
		configuration.addAnnotatedClass(URLDetails.class);
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration
				.getProperties());
		return configuration.buildSessionFactory(builder.build());
	}
}
