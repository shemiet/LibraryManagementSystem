package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.entitiy.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findTop5ByOrderByIdDesc();
}



