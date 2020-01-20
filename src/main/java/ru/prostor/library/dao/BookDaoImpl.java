package ru.prostor.library.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.prostor.library.entity.Book;
import ru.prostor.library.util.HibernateSessionFactoryUtil;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao{

    @Override
    public void insert(Book book) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(book);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Book findByTitle(String title) throws NoResultException {
        String sql = "from Book where title = :title";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        session.beginTransaction();
        Query query = session.createQuery(sql);
        query.setParameter("title", title);
        Book book = (Book) query.getSingleResult();
        session.getTransaction().commit();

        return book;
    }

    @Override
    public List<Book> findAll() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Book").list();
    }

    @Override
    public void delete(Long id){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Book book = session.load(Book.class, id);
        session.delete(book);
        session.getTransaction().commit();
        session.close();
    }
}
