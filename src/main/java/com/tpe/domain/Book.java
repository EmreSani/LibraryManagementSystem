package com.tpe.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "t_book")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank(message = "Kitap ismi boşluk olamaz!")
    @NotNull(message = "Kitap ismi girilmelidir!")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Yazar ismi boşluk olamaz!")
    @Size(min = 2,max = 30,message = "Yazar ismi(${validatedValue}) {min} ve {max} karakter arasında olmalıdır!")
    @Column(length = 30,nullable = false)
    private String author;

    @Column(nullable = false)
    private String publicationDate;

    //toString
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                '}';
    }
}
