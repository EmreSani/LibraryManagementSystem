package com.tpe.domain;

import com.tpe.dto.OwnerDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_owner")
@Getter
@Setter
@NoArgsConstructor
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank(message = "Geçerli bir isim giriniz!")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Geçerli bir soyad giriniz!")
    @Column(nullable = false)
    private String lastName;

    private String phoneNumber;

    @Email(message = "Geçerli bir email giriniz!")
    @Column(nullable = false,unique = true)
    private String email;

    @Setter(AccessLevel.NONE)
    private LocalDateTime registrationDate=LocalDateTime.now();

    @OneToMany(mappedBy = "owner")
    private List<Book> bookList=new ArrayList<>();


    public Owner(OwnerDTO ownerDTO){
        this.name=ownerDTO.getName();
        this.lastName=ownerDTO.getLastName();
        this.phoneNumber=ownerDTO.getPhoneNumber();
        this.email=ownerDTO.getEmail();
    }


}
