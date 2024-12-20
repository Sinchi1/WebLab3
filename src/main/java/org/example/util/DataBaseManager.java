package org.example.util;

import org.example.orm.ResultTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DataBaseManager {

    private static final DataBaseManager instance = new DataBaseManager();

    private DataBaseManager(){}

    public static DataBaseManager getInstance() {
        return instance;
    }

    SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(ResultTable.class)
            .buildSessionFactory();

    public boolean insert(ResultTable resultTable){
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(resultTable);
            transaction.commit();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public ResultTable getResult(int id){
        try (Session session = factory.openSession()){
            return session.get(ResultTable.class, id);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

}
