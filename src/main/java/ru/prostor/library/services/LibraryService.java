package ru.prostor.library.services;

import ru.prostor.library.entity.Author;
import ru.prostor.library.entity.Book;

import java.util.List;

public interface LibraryService {
    /**
     * Добавить книгу
     * @param book
     */
    void addBook(Book book);

    /**
     * Получить книгу
     * @param title
     * @return book
     */
    Book getBook(String title);

    /**
     * Получиь автора
     * @param fullName
     * @return author
     */
    Author getAuthor(String fullName);

    /**
     * Получить все книги
     * @return списко книг
     */
    List<Book> getBooks();

    /**
     * Удалить книгу
     * @param id
     */
    void deleteBook(Long id);
}
