package db;

import models.File;
import models.Folder;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBHelper {

    private static Transaction transaction;
    private static Session session;

    public static void save(Object object) {

        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void delete(Object object) {

        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static <T> List<T> getAll(Class classType) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<T> results = null;
        Criteria cr = session.createCriteria(classType);
        cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        results = getList(cr);
        return results;
    }

    public static List<File> findFilesInFolder(Folder folder) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<File> results = null;
        Criteria cr = session.createCriteria(File.class);
        cr.add(Restrictions.eq("folder", folder));
        results = getList(cr);
        return results;
    }

    public







    public static <T> List<T> getList(Criteria cr) {
        List<T> results = null;
        try {
            transaction = session.beginTransaction();
            results = cr.list();
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    public static <T> T getUniqueResult(Criteria cr) {
        T result = null;
        try {
            transaction = session.beginTransaction();
            result = (T) cr.uniqueResult();
            transaction.commit();

        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

}
