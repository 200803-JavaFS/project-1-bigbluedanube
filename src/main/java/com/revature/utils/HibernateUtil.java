package com.revature.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static Session ses;
	private static SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	
	// This is an example of a Singleton Design Pattern.
	// If one already exists, it uses that one.
	// If one doesn't exist, it opens one (of the many created by the SessionFactory).
	public static Session getSession() {
		if(ses == null) {
			ses = sf.openSession();
		}
		return ses;
	}
	
	public static void closeSes() {
		ses.close();
		ses = null;
	}
	
	// we set our fields to static to prevent others from having direct access to them.
	// They can only be accessed using the public methods.

}
