package com.tpe.controller;

import com.tpe.domain.Owner;
import com.tpe.dto.OwnerDTO;
import com.tpe.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    @Autowired
    private OwnerService service;


    //1- Save an Owner
    // http://localhost:8080/owners/save + JSON + POST
    @PostMapping("/save")
    public ResponseEntity<String> saveOwner(@Valid @RequestBody OwnerDTO owner){

        service.saveOwner(owner);

         return new ResponseEntity<>("Üye başarılı bir şekilde oluşturuldu.", HttpStatus.CREATED);//201
    }


    //2- Find All Owners
    // http://localhost:8080/owners + GET
    @GetMapping
    public ResponseEntity<List<Owner>> getAllOwners(){

        List<Owner> ownerList=service.getAll();

        return ResponseEntity.ok(ownerList);//200
    }



    //3- Find an Owner By ID
    // http://localhost:8080/owners/2 + GET
    @GetMapping("/{id}")
    public ResponseEntity<OwnerDTO> getOwnerById(@PathVariable Long id){

        OwnerDTO ownerDTO=service.getOwnerDTOById(id);

        return ResponseEntity.ok(ownerDTO);//200
    }

    //ÖDEV(practice): delete owner, update owner

    //ÖDEV: list all books of an owner which is given ID



}
