package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.dto.BookRequest;
import com.example.librarymanagementsystem.entitiy.Book;
import com.example.librarymanagementsystem.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book createBook(BookRequest bookRequest) {
        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setIsbn(bookRequest.getIsbn());

        if (bookRequest.getIsAvailable() != null) {
            book.setIsAvailable(bookRequest.getIsAvailable());
        } else {
            book.setIsAvailable(true);
        }

        return bookRepository.save(book);
    }

    public Book updateBook(Long id, BookRequest bookRequest) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
            existingBook.setTitle(bookRequest.getTitle());
            existingBook.setAuthor(bookRequest.getAuthor());
            existingBook.setIsbn(bookRequest.getIsbn());

            if (bookRequest.getIsAvailable() != null) {
                existingBook.setIsAvailable(bookRequest.getIsAvailable());
            }

            return bookRepository.save(existingBook);
        }
        return null;
    }

    public boolean deleteBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Book> getLatestBooks() {
        return bookRepository.findTop5ByOrderByIdDesc();
    }
}