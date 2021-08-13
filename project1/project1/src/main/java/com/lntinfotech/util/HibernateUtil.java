package com.lntinfotech.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    
    private static SessionFactory sf;

    public static SessionFactory getSessionFactory() {

        if(sf == null || sf.isClosed() == true) {
            Configuration cfg = new Configuration();
            cfg.setProperty("hibernate.connection.url", System.getenv("POSTGRES_URL"));
            cfg.setProperty("hibernate.connection.url", System.getenv("POSTGRES_USER"));
            cfg.setProperty("hibernate.connection.url", System.getenv("POSTGRES_PASSWORD"));

            sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
        }

        return sf;
    }
}
