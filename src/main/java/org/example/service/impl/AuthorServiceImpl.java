package org.example.service.impl;

import org.example.dao.AuthorDao;
import org.example.dao.impl.AuthorDaoImpl;
import org.example.models.Author;
import org.example.service.AuthorService;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao = new AuthorDaoImpl();
    @Override
    public boolean createAuthorTable() {
        return  authorDao.createAuthorTable();
    }

    @Override
    public String saveAuthor(Author newAuthor) {
        return authorDao.saveAuthor(newAuthor);
    }

    @Override
    public String updateAuthorById(Long id, Author author) {
        return authorDao.updateAuthorById(id,author);
    }

    @Override
    public Author findById(Long id) {
        return authorDao.findById(id);
    }

    @Override
    public void deleteAuthorById(Long id) {
        authorDao.deleteAuthorById(id);
    }

    @Override
    public List<Author> findAllAuthors() {
        return authorDao.findAllAuthors();
    }

    @Override
    public void dropAuthorTable() {
        authorDao.dropAuthorTable();
    }

    @Override
    public boolean cleanTable() {
        return authorDao.cleanTable();
    }

    @Override
    public List<Author> sortDateOfBirth() {
        return authorDao.sortDateOfBirth();
    }
}
