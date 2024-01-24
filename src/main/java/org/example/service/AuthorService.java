package org.example.service;

import org.example.models.Author;

import java.util.List;

public interface AuthorService {

    //    CRUD

    boolean createAuthorTable ();
    String saveAuthor (Author newAuthor);
    String updateAuthorById(Long id,Author author);
    Author findById(Long id);
    void deleteAuthorById(Long id);
    List<Author> findAllAuthors();
    void dropAuthorTable();
    boolean cleanTable();
    List<Author> sortDateOfBirth ();
}
