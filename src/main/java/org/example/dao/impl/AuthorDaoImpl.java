package org.example.dao.impl;

import org.example.config.JdbcConfig;
import org.example.dao.AuthorDao;
import org.example.models.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorDaoImpl implements AuthorDao {

    private final Connection connection = JdbcConfig.getConnection();

    @Override
    public boolean createAuthorTable() {
        int execute = 0;
        String query =
                "create table if not exists authors (" +
                        "id serial primary key," +
                        "first_name varchar," +
                        "last_name varchar," +
                        "email varchar," +
                        "country varchar," +
                        "date_of_birth date)";


        try {
            Statement statement = connection.createStatement();
            execute = statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return execute != 0;
    }

    @Override
    public String saveAuthor(Author newAuthor) {
        String query = """
                insert into authors (first_name,last_name,email,country,date_of_birth)
                values (?,?,?,?,?);
                """;

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,newAuthor.getFistName());
            preparedStatement.setString(2,newAuthor.getLastName());
            preparedStatement.setString(3,newAuthor.getEmail());
            preparedStatement.setString(4,newAuthor.getCountry());
            preparedStatement.setDate(5, Date.valueOf(newAuthor.getDateOfBirth()));
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String updateAuthorById(Long id, Author author) {

        String query = "update authors " + "set first_name = ?," + "last_name = ?," + "email = ?" + " where id = ?";
        int execute = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, author.getFistName());
            preparedStatement.setString(2, author.getLastName());
            preparedStatement.setString(3, author.getEmail());
            preparedStatement.setLong(4, id);
            execute = preparedStatement.executeUpdate();
            return "Author update !";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return execute != 0 ? "Updated" : "Failed";
    }

    @Override
    public Author findById(Long id) {
        Author author = new Author();
        String query = """
                select * from authors where id = ?
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()){
                throw new RuntimeException("Authors " + id + "not found !");
            }
            author.setId(resultSet.getLong("id"));
            author.setFistName(resultSet.getString("first_Name"));
            author.setLastName(resultSet.getString("last_Name"));
            author.setEmail(resultSet.getString("email"));
            author.setCountry(resultSet.getString("country"));
            author.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return author;
    }

    @Override
    public void deleteAuthorById(Long id) {
        String query = "delete from authors where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Author> findAllAuthors() {
        List<Author> authors = new ArrayList<>();
        String query = "select * from authors";
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Author author = new Author();
                author.setId(resultSet.getLong("id"));
                author.setFistName(resultSet.getString("first_Name"));
                author.setLastName(resultSet.getString("last_Name"));
                author.setEmail(resultSet.getString("email"));
                author.setCountry(resultSet.getString("country"));
                author.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
                authors.add(author);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return authors;
    }

    @Override
    public void dropAuthorTable() {
        String query = "drop table if exists authors";
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean cleanTable() {
        String query = "truncate table authors";
        int execute = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);){
           execute = preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return execute != 0;
    }

    @Override
    public List<Author> sortDateOfBirth() {
        List<Author> authors = findAllAuthors();
        List<Author> sortedAuthors = authors.stream().sorted(Comparator.comparing(Author::getDateOfBirth)).collect(Collectors.toList());
        return sortedAuthors;
    }
}
