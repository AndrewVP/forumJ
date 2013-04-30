package org.forumj.hibernate.db.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("deprecation")
public class HibernateUtil {

	   
	   private static SessionFactory sessionFactory = null;
	   
	    static {
	        try {
	                //creates the session factory from hibernate.cfg.xml
	            Configuration conf=new Configuration();    
	        	sessionFactory = conf.configure().buildSessionFactory();
	        } catch (Exception e) {
	              e.printStackTrace();
	        }
	    }
	  
	    public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }
}
