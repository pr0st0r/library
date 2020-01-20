package ru.prostor.library.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.prostor.library.entity.Author;
import ru.prostor.library.util.HibernateSessionFactoryUtil;

import javax.persistence.NoResultException;
import javax.persistence.Query;

@Repository
public class AuthorDaoImpl implements AuthorDao{

    @Override
    public Author getAuthor(String fullName) throws NoResultException {
        String sql = "from Author where fullName = :fullName";

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(sql);
        query.setParameter("fullName", fullName);
        Author author = (Author) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return author;
    }
}
