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
}
