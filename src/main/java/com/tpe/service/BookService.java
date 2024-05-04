package com.tpe.service;

import com.tpe.domain.Book;
import com.tpe.exceptions.BookNotFoundException;
import com.tpe.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    //1-b
    public void saveBook(Book book) {

        bookRepository.save(book);

    }

    //2-b
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    //3-b
    public Book getBookById(Long identity) {

        Book book=bookRepository.findById(identity).
                orElseThrow(()->new BookNotFoundException("Kitap bulunamadı, ID : "+identity));

        return book;
    }

    //4-b
    public void deleteBookById(Long id) {
        //bı idye sahip book var mı
        Book book=getBookById(id);
        //bookRepository.delete(book);
        bookRepository.deleteById(id);
    }
}
