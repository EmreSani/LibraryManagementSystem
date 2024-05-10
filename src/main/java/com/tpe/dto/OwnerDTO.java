package com.tpe.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class OwnerDTO {

    @NotBlank(message = "Geçerli bir isim giriniz!")
    private String name;

    @NotBlank(message = "Geçerli bir soyad giriniz!")
    private String lastName;

    private String phoneNumber;

    @Email(message = "Geçerli bir email giriniz!")
    private String email;

}
