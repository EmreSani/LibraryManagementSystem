package com.tpe.controller;


import com.tpe.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//Body
//@Controller//Model , View
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;

}