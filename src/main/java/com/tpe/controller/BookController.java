package com.tpe.controller;


import com.tpe.domain.Book;
import com.tpe.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController//Body
//@Controller//Model , View
@RequestMapping("/books")// http://localhost:8080/books
public class BookController {

    @Autowired
    private BookService service;

    //CREATE
    //1- Save a Book & Return :Message
    // http://localhost:8080/books + POST + JSON format body
    @PostMapping
    public ResponseEntity<String> saveBook(@Valid @RequestBody Book book){

        service.saveBook(book);

      return new ResponseEntity<>("Kitap başarıyla kaydedildi.", HttpStatus.CREATED);//201
    }

    //READ
    //2- Get All Books, Return:List<Book>
    // http://localhost:8080/books + GET
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> bookList=service.getAll();
        return ResponseEntity.ok(bookList);//200
    }

    //3- Get a Book by its ID , Return:Book
    // http://localhost:8080/books/2 + GET
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long identity){

        Book found=service.getBookById(identity);

        return new ResponseEntity<>(found,HttpStatus.OK) ;//200
    }

    //4- Delete a Book by its ID, Return : message
    // http://localhost:8080/books/2 + DELETE
    @DeleteMapping("/{no}")
    public ResponseEntity<String> deleteBook(@PathVariable("no") Long id){

        service.deleteBookById(id);

        return ResponseEntity.ok("Kitap başarıyla silindi. ID "+id);
    }


}
