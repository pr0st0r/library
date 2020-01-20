package ru.prostor.library.dao;

import ru.prostor.library.entity.Author;

public interface AuthorDao {
    /**
     * Получить автора
     * @param fullName - имя автора
     * @return Author
     */
    Author getAuthor(String fullName);
}
