package com.tpe.controller;


import com.tpe.domain.Book;
import com.tpe.dto.BookDTO;
import com.tpe.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

    //5- Get a Book by its ID with RequestParam , Return:Book
    // http://localhost:8080/books/q?id=2 + GET
    @GetMapping("/q")
    public ResponseEntity<Book> getBookByIdWithQuery(@RequestParam("id") Long identity){

        Book book=service.getBookById(identity);

        return ResponseEntity.ok(book);//200
    }

    //6- Get a Book by its Title with RequestParam
    //http://localhost:8080/books/search?title=Suç ve Ceza + GET
    @GetMapping("/search")
    public ResponseEntity<List<Book>> filterBooksByTitle(@RequestParam("title") String title){

        List<Book> books=service.filterBooksByTitle(title);

        return ResponseEntity.ok(books);//200
    }

    //8- Update a Book With Using DTO
    // http://localhost:8080/books/update/2 + PUT
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateBook(@PathVariable("id") Long id,@Valid @RequestBody BookDTO bookDTO){

        service.updateBookById(id,bookDTO);

        return ResponseEntity.ok("Kitap başarıyla güncellendi. ID : "+id);

    }

    //7- Get Books With Page
    // http://localhost:8080/books/s?page=1&size=2&sort=publicationDate&direction=ASC + GET
    @GetMapping("/s")
    public ResponseEntity<Page<Book>> getAllBooksWithPage(@RequestParam("page") int page,
                                                          @RequestParam("size") int size,
                                                          @RequestParam("sort") String sortBy,
                                                          @RequestParam("direction") Sort.Direction direction){

        Pageable pageable= PageRequest.of(page-1,size,Sort.by(direction,sortBy));

        Page<Book> booksWithPage=service.getAllBooksWithPage(pageable);

      return ResponseEntity.ok(booksWithPage);//200
    }


    //9- Get a Book By Its Author Using JPQL-->findByAuthor
    // http://localhost:8080/books/a?author=AB
    @GetMapping("/a")
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam("author") String author){

        List<Book> books=service.getBooksByAuthor(author);

        return ResponseEntity.ok(books);
    }

    //ÖDEV://--> Get Books By Its Author and PublicationDate
    ////--> http://localhost:8080/books/filter?author=Martin Eden&pubDate=1900
    //findByAuthorAndPublicationDate(author, pubDate);

    @GetMapping("/filter")
    public ResponseEntity<List<Book>> filterByTitleAndPublicationDate(@RequestParam("author") String author,
                                                                      @RequestParam("pubDate") String pubDate){

        List<Book> books=service.findBookByTitleAndPubDate(author,pubDate);
        return ResponseEntity.ok(books); //2

    }


    //ÖDEV://--> Get Books By Its Title Which Contains a Pattern
    //--> http://localhost:8080/books/filterbook?word=Eden




    //10- Add a Book to an Owner
    // http://localhost:8080/books/add?book=3&owner=1 + PATCH
     @PatchMapping("/add")
    public ResponseEntity<String> addBookToOwner(@RequestParam("book") Long bookID,
                                                 @RequestParam("owner") Long ownerID){

        service.addBookToOwner(bookID,ownerID);

        return ResponseEntity.ok("Kitap üyeye eklendi.");
     }







}
