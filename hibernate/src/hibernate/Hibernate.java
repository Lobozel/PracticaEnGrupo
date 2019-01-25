/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import hibernate.entity.Actor;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author drago
 */
public class Hibernate {

    SessionFactory sfactory = hibernate.util.NewHibernateUtil.getSessionFactory();
    Session session = sfactory.openSession();

    public static void main(String[] args) {
        Hibernate hibernate= new Hibernate();
        hibernate.actualizar();
        
    }

    public void insertar() {
        
        Transaction tx = null;
        try {
            tx = session.beginTransaction();//comienza la transacción
            //código de persistencia (/get/load/delete/save/update)
            Actor actor = new Actor("Sergey", "Shevchenko", new Date(106, 2, 15));
            session.save(actor);
            tx.commit();
            session.close();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }

    }
    
    public void eliminar(){
        Transaction tx = null;
        try {
            tx = session.beginTransaction();//comienza la transacción
            //código de persistencia (/get/load/delete/save/update)
            Actor actor = new Actor();
            actor = (Actor) session.load(Actor.class, (short) 201);
            session.delete(actor);
            tx.commit();
            session.close();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }
    
    public void actualizar(){
        Transaction tx = null;
        try {
            tx = session.beginTransaction();//comienza la transacción
            //código de persistencia (/get/load/delete/save/update)
            Actor actor = new Actor();
            actor = (Actor) session.load(Actor.class, (short) 202);
            actor.setLastName("Patata");
            session.update(actor);
            tx.commit();
            session.close();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public void consulta() {
        Query q = session.createQuery("select Sergey from Actor");
        Actor actor = new Actor();
        Iterator<?> iter = q.iterate();
        while (iter.hasNext()) {
            actor = (Actor) iter.next();
            System.out.println(actor.toString());
        }
    }

}
