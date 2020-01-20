package ru.prostor.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.prostor.library.dao.AuthorDao;
import ru.prostor.library.dao.BookDao;
import ru.prostor.library.entity.Author;
import ru.prostor.library.entity.Book;

import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService{

    @Autowired
    private BookDao bookDao;
    @Autowired
    private AuthorDao authorDao;

    @Override
    public void addBook(Book book) {
        bookDao.insert(book);
    }

    @Override
    public Book getBook(String title) {
        return bookDao.findByTitle(title);
    }

    @Override
    public Author getAuthor(String fullName) {
        return authorDao.getAuthor(fullName);
    }

    @Override
    public List<Book> getBooks() {
        return bookDao.findAll();
    }

    @Override
    public void deleteBook(Long id) {
        bookDao.delete(id);
    }


}
