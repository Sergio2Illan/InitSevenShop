package com.initseven.factory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateFactory {
	
	public static SessionFactory getSessionFactory() {
		
		SessionFactory session;
		
		try {
			
			session = new Configuration().configure().buildSessionFactory();
			
		} catch (Throwable e) {
			System.err.println("Error al crear la factoria: "+e);
			throw new ExceptionInInitializerError(e);
		}
		
		
		return session;
	}

}
