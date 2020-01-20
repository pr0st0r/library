package ru.prostor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.runner.RunWith;
import ru.prostor.library.Application;
import ru.prostor.library.entity.Author;
import ru.prostor.library.entity.Book;
import ru.prostor.library.services.LibraryService;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={Application.class})
public class LibraryServiceTest {
    @Autowired
    private LibraryService service;

    @Before
    public void init(){
        Book book = new Book("Книга", "Описание");
        Author author = new Author("Автор");
        book.getAuthors().add(author);

        service.addBook(book);
    }

    @Test
    public void getBookTest(){
        List<Book> book = service.getBooks();

        Assert.assertEquals(1, book.size());
        Assert.assertEquals("Книга", book.get(0).getTitle());
        Assert.assertEquals("Описание", book.get(0).getDescription());

        Assert.assertNotEquals("Книга1", book.get(0).getTitle());
        Assert.assertNotEquals("Описание1", book.get(0).getDescription());
    }

    @Test
    public void getBookByTitleTest(){
        Book book = service.getBook("Книга");

        Assert.assertNotNull(book);
        Assert.assertEquals("Книга", book.getTitle());
        Assert.assertEquals("Описание", book.getDescription());
    }

    @Test
    public void deleteBookTest(){
        Book book = new Book("Книга2", "Описание2");
        Author author = new Author("Автор2");
        book.getAuthors().add(author);
        service.addBook(book);

        List<Book> books = service.getBooks();
        Assert.assertEquals(2, books.size());

        service.deleteBook(1L);
        List<Book> books2 = service.getBooks();
        Assert.assertEquals(1, books2.size());
    }
}
