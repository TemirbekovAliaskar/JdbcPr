package org.example.dao;

import org.example.models.Author;

import java.util.List;

public interface AuthorDao {

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
