package org.example.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {

        private Long id;
        private String name;
        private String country;
        private int publishedYear;
        private int price;
        private Long author;

    public Book(String name, String country, int publishedYear, int price, Long author) {
        this.name = name;
        this.country = country;
        this.publishedYear = publishedYear;
        this.price = price;
        this.author = author;
    }

    public Book(String name, String country,int price) {
        this.name = name;
        this.country = country;
        this.price = price;
    }
}
