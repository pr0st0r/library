package ru.prostor.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.prostor.library.exceptions.BookExistsException;
import ru.prostor.library.entity.Author;
import ru.prostor.library.entity.Book;
import ru.prostor.library.services.LibraryService;

import javax.persistence.NoResultException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LibraryController {

    private Map<String, String> response = new HashMap<>();;

    @Autowired
    private LibraryService service;

    @RequestMapping(value = "/api/getBooks/", method = RequestMethod.POST)
    public @ResponseBody List<Book> getBooks() {
        return service.getBooks();
    }

    @PostMapping(value = "/api/saveBook")
    public @ResponseBody Map<String, String> saveBook(Book book, @RequestParam String  authors){
        boolean bookExists = false;
        String status = "";
        String message = "";

        try {
            Book bookFromDb = service.getBook(book.getTitle());
            if(book.equals(bookFromDb)) throw new BookExistsException("Книга c таким(и) автором(и) уже существует");
            else {
                book.setId(bookFromDb.getId());
                message = "Книга обновлена";
            }
        }catch (NoResultException e){
            System.out.println(e.getMessage());
        }
        catch (BookExistsException e){
            status = "error";
            message = e.getMessage();
            bookExists = true;
        }

        if(!bookExists){
            try{

                    for(Author author : book.getAuthors()){
                        try {
                            Author authorFromDb = service.getAuthor(author.getFullName());
                            if(author.equals(authorFromDb)){
                                author.setId(authorFromDb.getId());
                            }
                        }catch (NoResultException e){
                            System.out.println(e.getMessage());
                        }
                    }
                service.addBook(book);
                status = "success";
                if(message.equals("")) message = "Книга добавлена";
            }catch (Exception e){
                System.out.println(e.getMessage());
                status = "error";
                message = "Ошибка добавления книги";
            }
        }

        this.response.put("status", status);
        this.response.put("message", message);
        return response;
    }


    @RequestMapping(value = "/api/deleteBook/{id}/", method = RequestMethod.POST)
    public @ResponseBody Map<String, String> deleteBook(@PathVariable long id) {
       String status = "";
       String message = "";

        try {
            service.deleteBook(id);
            status = "success";
            message = "Книга удалена";
        }catch (Exception e){
            System.out.println(e.getMessage());
            status = "error";
            message = "Ошибка удаления книги";
        }

        this.response.put("status", status);
        this.response.put("message", message);
        return response;
    }
}
