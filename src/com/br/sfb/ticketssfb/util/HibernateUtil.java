package com.br.sfb.ticketssfb.util;



import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import com.br.sfb.ticketssfb.Ticketssfb;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author John Lenon Freire Pinto
 */
public class HibernateUtil {
	
	 private static final SessionFactory sessionFactory;
	
	static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.         	 		 
		 Configuration cfg = new Configuration().configure(Ticketssfb.class.getClass().getResource("/hibernate.cfg.xml"));
		 System.out.println(Ticketssfb.class.getClass().getResource("/hibernate.cfg.xml"));
	    sessionFactory = cfg.buildSessionFactory();
	    sessionFactory.openSession();

        } catch (Throwable ex) {
            // Log the exception. 
            System.out.println("Initial SessionFactory creation failed." + ex);
            throw new RuntimeException(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
   
}

