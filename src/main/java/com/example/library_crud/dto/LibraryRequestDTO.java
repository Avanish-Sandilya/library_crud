package com.example.library_crud.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LibraryRequestDTO {

    @NotBlank(message = "Book name cannot be blank")
    @Size(min = 2, message = "Book name must contain at least 2 letters")
    private String bookName;

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @NotBlank(message = "Author name cannot be blank")
    private String author;

    @NotBlank(message = "Year cannot be blank")
    private int year;

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }
}
