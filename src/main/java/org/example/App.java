package org.example;

import org.example.config.JdbcConfig;
import org.example.dao.AuthorDao;
import org.example.dao.impl.AuthorDaoImpl;
import org.example.models.Author;
import org.example.models.Book;
import org.example.service.AuthorService;
import org.example.service.BookService;
import org.example.service.impl.AuthorServiceImpl;
import org.example.service.impl.BookServiceImpl;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        AuthorService authorService = new AuthorServiceImpl();
        BookService bookService = new BookServiceImpl();
//        authorService.createAuthorTable();
//        authorService.updateAuthorById(1L,new Author("Urmat","Toichikov","u@gmail.com"));
//        authorService.saveAuthor(new Author("Ali","Temirbekov","a@gmail.com","KG",LocalDate.of(2003,2,5)));
//        authorService.saveAuthor(new Author("Urmat","Mirbekov","u@gmail.com","KG",LocalDate.of(1999,4,5)));
//        authorService.saveAuthor(new Author("Jigit","Turumbekov","j@gmail.com","KG",LocalDate.of(2000,1,5)));
//        System.out.println(authorService.findById(1L));
//        authorService.deleteAuthorById(2L);
//        System.out.println(authorService.findAllAuthors());
//        authorService.dropAuthorTable();
//         authorService.cleanTable();
//        System.out.println(authorService.sortDateOfBirth());


//         Book

//        bookService.createBookTable();
//        bookService.saveBook(2L, new Book("Kyamat", "KG", 2020, 195, 2L));
//        bookService.saveBook(3L, new Book("Fenix", "Italia", 2010, 500, 3L));
//        System.out.println(bookService.findAllAuthorBookById(2L));
//        bookService.updateBookById(3L,new Book("Ajar","KG",1200));

//        bookService.cleanTable();
//        bookService.deleteBookById(8L);
//        System.out.println(bookService.findBookById(9L));
//        System.out.println(bookService.findAllBooksByAuthorCountry("KG"));
//        bookService.dropBookTable();
//        System.out.println(bookService.sortByPublishedYear("asc"));
        bookService.updateAuthorsAllBooksPrice(3L,900);

    }
}
