package com.br.sfb.ticketssfb;

import com.br.sfb.ticketssfb.dao.DAOTicket;
import com.br.sfb.ticketssfb.modelo.ModeloTicket;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
//import org.hibernate.classic.Session;

public class New2 {

	public static void main(String[] args) {
		Configuration cfg = new Configuration().configure(Ticketssfb.class.getClass().getResource("/hibernate.cfg.xml"));
		 System.out.println(Ticketssfb.class.getClass().getResource("/hibernate.cfg.xml"));
	    SessionFactory sessionFactory = cfg.buildSessionFactory();
//	    Session ss = sessionFactory.openSession();
            System.out.println("oiii");
            ModeloTicket em = new ModeloTicket();
	//em.setNome("oii");
	DAOTicket dao = new DAOTicket();
	//dao.adicionar(em);
            try {
                System.out.println("oiii");
                  //  ss.beginTransaction();
                  //  ss.save(em);
                   // ss.getTransaction().commit();
                       System.out.println("aaaa");
            } catch (Exception e) {
                 System.out.println("oiii");
            }
    
           
        sessionFactory.close();
	}

}
