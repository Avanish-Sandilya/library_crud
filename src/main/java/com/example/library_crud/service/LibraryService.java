package com.example.library_crud.service;

import com.example.library_crud.model.Library;
import com.example.library_crud.repository.LibraryRepository;
import com.example.library_crud.dto.LibraryRequestDTO;
import com.example.library_crud.dto.LibraryResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryService {
    @Autowired
    private LibraryRepository libraryRepository;

    public List<LibraryResponseDTO> getBooks(Optional<String> bookName) {
        return (bookName.isPresent() ? libraryRepository.findByBookNameContainingIgnoreCase(bookName.get()) : libraryRepository.findAll())
                .stream()
                .map(book -> new LibraryResponseDTO(book.getId(), book.getBookName(), book.getAuthor(), book.getYear()))
                .collect(Collectors.toList());
    }

    public Optional<LibraryResponseDTO> getBookById(Long id) {
        return libraryRepository.findById(id)
                .map(book-> new LibraryResponseDTO(book.getId(), book.getBookName(), book.getAuthor(), book.getYear()));
    }

    public LibraryResponseDTO addBook(LibraryRequestDTO requestDTO) {
        Library book=new Library();
        book.setBookName(requestDTO.getBookName());
        book.setAuthor(requestDTO.getAuthor());
        book.setYear(requestDTO.getYear());

        Library savedBook = libraryRepository.save(book);
        return new LibraryResponseDTO(savedBook.getId(),savedBook.getBookName(),savedBook.getAuthor(),savedBook.getYear());
    }

    public LibraryResponseDTO updateBook(Long id, LibraryRequestDTO requestDTO) {
        Library book = libraryRepository.findById(id).orElseThrow(()->new RuntimeException("Book not found"));
        book.setBookName(requestDTO.getBookName());
        book.setAuthor(requestDTO.getAuthor());
        book.setYear(requestDTO.getYear());

        Library updateBook = libraryRepository.save(book);
        return new LibraryResponseDTO(updateBook.getId(), updateBook.getBookName(), updateBook.getAuthor(), updateBook.getYear());
    }

    public void deleteBook(Long id) {
        libraryRepository.deleteById(id);
    }

}
