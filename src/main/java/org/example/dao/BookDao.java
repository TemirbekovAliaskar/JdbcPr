package org.example.dao;

import org.example.models.Book;

import java.util.List;

public interface BookDao{

    String createBookTable();
    boolean saveBook(Long authorId, Book book);
    List<Book> findAllAuthorBookById(Long authorId);
    String updateBookById(Long id, Book book);
    Book findBookById(Long id);
    void deleteBookById(Long id);
    List<Book> findAllBooksByAuthorCountry(String authorCountry);
    void dropBookTable();
    boolean cleanTable();
    List<Book> sortByPublishedYear(String ascOrDesc);
    String updateAuthorsAllBooksPrice(Long authorId, int newBookPrice);
}
