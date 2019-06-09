package service;

/* Сервис отвечающий за подключение к БД через Hybernate
 *
 *@author Aleksei Pronichev
 *@version 09.06.2019
 */

import entities.Result;
import entities.Task;
import entities.Traffic;
import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HybernateSQLservice implements SQLService {
    private SessionFactory factory;

    public HybernateSQLservice() {
        factory = new Configuration()
                .configure("hibernate.xml")
                .buildSessionFactory();
    }

    // метод универсальный, но примет только entity
    public void add(Object entity) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();
        } catch (MappingException e) {
            System.out.println("Попытка добавить некорректный объект в БД");
        } finally {
            session.close();
        }
    }

    // метод универсальный, но примет только entity
    public void remove(Object entity) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        } catch (MappingException e) {
            System.out.println("Попытка удалить некорректный объект из БД");
        } finally {
            session.close();
        }
    }

    @Override
    public Task getTask(String id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Task task = session.get(Task.class, id);
        session.close();
        return task;
    }

    @Override
    public Task[] getTasks() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Task[] tasks = (Task[]) session.createQuery("from tasks").list().toArray();
        session.close();
        return tasks;
    }

    @Override
    public Traffic[] getTraffic(Task task) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Traffic[] traffic = (Traffic[]) session.createQuery("from traffic where master = " + task.getId()).list().toArray();
        session.close();
        return traffic;
    }

    @Override
    public Result[] getResults(Task task) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Result[] results = (Result[]) session.createQuery("from results where master = " + task.getId()).list().toArray();
        session.close();
        return results;
    }
}
