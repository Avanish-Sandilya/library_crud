package com.example.library_crud.controller;
import com.example.library_crud.model.Library;
import com.example.library_crud.service.LibraryService;
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
    public List<Library> getBooks(@RequestParam (required = false) String name){
        return libraryService.getBooks(name);
    }

    @GetMapping("/{id}")
    public Optional<Library> getBooksById(@PathVariable Long id){
        return libraryService.getBookById(id);
    }

    @PostMapping
    public Library addBook(@Valid @RequestBody Library library){
        return libraryService.addBook(library);
    }

    @PutMapping("/{id}")
    public Library updateBook(@PathVariable Long id, @Valid @RequestBody Library bookDetails){
        return libraryService.updateBook(id,bookDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteBook( @PathVariable Long id){
        libraryService.deleteBook(id);
    }


}
