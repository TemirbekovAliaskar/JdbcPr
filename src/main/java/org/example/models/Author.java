package org.example.models;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Author {


    private Long id;
    private String fistName;
    private String lastName;
    private String email;
    private String country;
    private LocalDate dateOfBirth;

    public Author(String fistName, String lastName, String email) {
        this.fistName = fistName;
        this.lastName = lastName;
        this.email = email;
    }

    public Author(String fistName, String lastName, String email, String country, LocalDate dateOfBirth) {
        this.fistName = fistName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
    }
}
