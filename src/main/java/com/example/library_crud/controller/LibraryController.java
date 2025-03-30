package com.example.library_crud.controller;
import com.example.library_crud.model.Library;
import com.example.library_crud.service.LibraryService;
import com.example.library_crud.dto.LibraryRequestDTO;
import com.example.library_crud.dto.LibraryResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/library")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    @GetMapping
    public List<LibraryResponseDTO> getBooks(@RequestParam (required = false) String name){
        return libraryService.getBooks(Optional.ofNullable(name));
    }

    @GetMapping("/{id}")
    public Optional<LibraryResponseDTO> getBooksById(@PathVariable Long id){
        return libraryService.getBookById(id);
    }

    @PostMapping
    public LibraryResponseDTO addBook(@Valid @RequestBody LibraryRequestDTO library){
        return libraryService.addBook(library);
    }

    @PutMapping("/{id}")
    public LibraryResponseDTO updateBook(@PathVariable Long id, @Valid @RequestBody LibraryRequestDTO bookDetails){
        return libraryService.updateBook(id,bookDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteBook( @PathVariable Long id){
        libraryService.deleteBook(id);
    }


}
