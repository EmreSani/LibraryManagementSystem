package com.tpe.service;

import com.tpe.domain.Book;
import com.tpe.domain.Owner;
import com.tpe.dto.BookDTO;
import com.tpe.exceptions.BookNotFoundException;
import com.tpe.exceptions.ConflictException;
import com.tpe.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OwnerService ownerService;


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

    //6-b
    public List<Book> filterBooksByTitle(String title) {

        return bookRepository.findByTitle(title);

    }


    //8-b
    public void updateBookById(Long id, BookDTO bookDTO) {

        Book existingBook=getBookById(id);

        //title,author,pdate
        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setAuthor(bookDTO.getAuthor());
        existingBook.setPublicationDate(bookDTO.getPublicationDate());

        bookRepository.save(existingBook);//saveOrUpdate

    }

    //7-b
    public Page<Book> getAllBooksWithPage(Pageable pageable){

        return bookRepository.findAll(pageable);
    }

    //9-b

    public List<Book> getBooksByAuthor(String author) {

        List<Book> bookList=bookRepository.findByAuthorWithJPQL(author);

        if (bookList.isEmpty()){
            throw new BookNotFoundException("Yazara ait kitap bulunamadı!");
        }

        return bookList;

    }

    //ödev
    public List<Book> findBookByTitleAndPubDate(String author, String pubDate) {
        return bookRepository.findByTitleAndPublicationDate(author,pubDate);

    }

    //10-b
    public void addBookToOwner(Long bookID, Long ownerID) {

        Book foundBook=getBookById(bookID);

        Owner foundOwner=ownerService.getOwnerById(ownerID);

        //belirtilen id ye sahip olan kitap daha önce ownera verilmiş mi
        //foundOwner.getBookList().contains(foundBook);

        boolean isBookExists=foundOwner.getBookList().stream().
                anyMatch(
                        book->book.getId().equals(bookID)
                );//eşleşme var mı

        if (isBookExists){
            throw new ConflictException("Bu kitap zaten üyenin listesinde var. Üye ID : "+ownerID);
        } else if (foundBook.getOwner()!=null) {
            throw new ConflictException("Bu kitap başka bir üyenin listesinde var.");
        }else {
            //aktif olan kitabı belirtilen ownera ekleyebiliriz.
            foundBook.setOwner(foundOwner);
           // foundOwner.getBookList().add(foundBook);--->mappedBy ile yapılacak
            bookRepository.save(foundBook);
        }

    }


}
