package com.example.library_crud.dto;

public class LibraryResponseDTO {

    private Long id;
    private String bookName;
    private String author;
    private int year;

    public LibraryResponseDTO(){}

    public LibraryResponseDTO(Long id, String bookName, String author, int year) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
