package com.example.librarymanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BookRequest {

    @NotBlank(message = "Kitap adı boş bırakılamaz.")
    @Size(min = 2, max = 100, message = "Kitap adı 2-100 karakter olmalıdır.")
    private String title;

    @NotBlank(message = "Yazar adı boş bırakılamaz.")
    private String author;

    private String isbn;
    private Boolean isAvailable;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public Boolean getIsAvailable() { return isAvailable; }
    public void setIsAvailable(Boolean isAvailable) { this.isAvailable = isAvailable; }
}