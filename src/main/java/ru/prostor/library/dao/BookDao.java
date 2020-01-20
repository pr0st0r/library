package ru.prostor.library.dao;

import ru.prostor.library.entity.Book;

import java.util.List;

public interface BookDao {
    /**
     * Добавить книгу
     * @param book - книга
     */
    void insert(Book book);

    /**
     * Получить книгу по названию
     * @param title - название книги
     */
    Book findByTitle(String title);

    /**
     * Получить все книги
     * @return list book
     */
    List<Book> findAll();

    /**
     * Удалить книгу
     * @param id - id книги в бд
     */
    void delete(Long id);
}
