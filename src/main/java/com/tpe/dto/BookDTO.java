package com.tpe.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


//update
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    @NotBlank(message = "Kitap ismi boşluk olamaz!")
    @NotNull(message = "Kitap ismi girilmelidir!")
    private String title;

    @NotBlank(message = "Yazar ismi boşluk olamaz!")
    @Size(min = 2,max = 30,message = "Yazar ismi(${validatedValue}) {min} ve {max} karakter arasında olmalıdır!")
    private String author;

    private String publicationDate;
}
