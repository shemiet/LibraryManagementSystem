package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.entitiy.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}