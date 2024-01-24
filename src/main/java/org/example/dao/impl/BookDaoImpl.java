package org.example.dao.impl;

import org.example.config.JdbcConfig;
import org.example.dao.BookDao;
import org.example.models.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
     private final Connection connection = JdbcConfig.getConnection();
    @Override
    public String createBookTable() {
        String query = """
                create table books (
                id serial primary key,
                name varchar,
                country varchar,
                published_year int,
                price int,
                author_id int references authors(id))
                """;
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(query);
            return "Created book";
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return "Failed !";
    }

    @Override
    public boolean saveBook(Long authorId, Book book) {
        int execute = 0;
        String query = """
                insert into books (name,country,published_year,price,author_id)
                values (?,?,?,?,?);
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,book.getName());
            preparedStatement.setString(2,book.getCountry());
            preparedStatement.setInt(3,book.getPublishedYear());
            preparedStatement.setInt(4,book.getPrice());
            preparedStatement.setLong(5,authorId);
            execute = preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return execute != 0? true:false;
    }

    @Override
    public List<Book> findAllAuthorBookById(Long authorId) {
        List<Book> books = new ArrayList<>();
        String query = """
                select * from books b inner join authors a on b.author_id = a.id where b.author_id = ?
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1,authorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                books.add(new Book(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("country"),
                        resultSet.getInt("published_year"),
                        resultSet.getInt("price"),
                        resultSet.getLong("author_id")
                ));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return books;
    }

    @Override
    public String updateBookById(Long id, Book book) {
        int execute =0;
        String query = """
                update books set name = ?,country = ?,price = ? where id = ?;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,book.getName());
            preparedStatement.setString(2,book.getCountry());
            preparedStatement.setInt(3,book.getPrice());
            preparedStatement.setLong(4,id);
            execute = preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return execute != 0 ? "Updated!":"Failed!";
    }

    @Override
    public Book findBookById(Long id) {
        Book book = new Book();
        String query = """
                select * from books where id = ?
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()){
                throw new RuntimeException("Book" +id + "not found !") ;
            }
            book.setId(resultSet.getLong("id"));
            book.setName(resultSet.getString("name"));
            book.setCountry(resultSet.getString("country"));
            book.setPublishedYear(resultSet.getInt("published_year"));
            book.setPrice(resultSet.getInt("price"));
            book.setAuthor(resultSet.getLong("author_id"));

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return book;
    }

    @Override
    public void deleteBookById(Long id) {
        String query = """
                delete from books where id = ?
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

//    @Override
//    public List<Book> findAllBooksByAuthorCountry(String authorCountry) {
//        List<Book> books = new ArrayList<>();
//        String query = """
//            SELECT * FROM books b INNER JOIN authors a ON b.author_id = a.id WHERE a.country = ?
//            """;
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setString(1, authorCountry);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                Book book = new Book(
//                        resultSet.getLong("id"),
//                        resultSet.getString("name"),
//                        resultSet.getString("country"),
//                        resultSet.getInt("published_year"),
//                        resultSet.getInt("price"),
//                        resultSet.getLong("author_id")
//                );
//                books.add(book);
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return books;
//    }

    @Override
    public List<Book> findAllBooksByAuthorCountry(String authorCountry) {
        List<Book> books = new ArrayList<>();
        String query = """
                select * from books b inner join authors a on b.author_id = a.id where a.country = ?
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,authorCountry);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setName(resultSet.getString("name"));
                book.setCountry(resultSet.getString("country"));
                book.setPublishedYear(resultSet.getInt("published_year"));
                book.setPrice(resultSet.getInt("price"));
                book.setAuthor(resultSet.getLong("author_id"));
                books.add(book);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return books;
    }

    @Override
    public void dropBookTable() {
        String query = """
                drop table if exists books
                """;
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
            System.out.println("Succesfull delete table ");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean cleanTable() {
        int execute = 0;
        String query = """
                truncate table books;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
             execute = preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return execute != 0 ? true:false;
    }

    @Override
    public List<Book> sortByPublishedYear(String ascOrDesc) {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books ORDER BY published_year asc ";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("country"),
                        resultSet.getInt("published_year"),
                        resultSet.getInt("price"),
                        resultSet.getLong("author_id")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return books;
    }

    @Override
    public String updateAuthorsAllBooksPrice(Long authorId, int newBookPrice) {
        String query = """
                update books set price = ? where author_id = ?
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);){
            preparedStatement.setInt(1,newBookPrice);
            preparedStatement.setLong(2,authorId);

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
