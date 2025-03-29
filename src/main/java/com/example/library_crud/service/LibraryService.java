package com.example.library_crud.service;

import com.example.library_crud.model.Library;
import com.example.library_crud.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {
    @Autowired
    private LibraryRepository libraryRepository;

    public List<Library> getBooks(String name) {
        if (name != null && !name.isEmpty()) {
            List<Library> names = new ArrayList<>();
            for (var v : libraryRepository.findAll()) {
                if (v.getBookName().equalsIgnoreCase(name)) {
                    names.add(v);
                }
            }
            return names;
        } else {
            return libraryRepository.findAll();
        }
    }

    public Optional<Library> getBookById(Long id) {
        return libraryRepository.findById(id);
    }

    public Library addBook(Library book) {
        return libraryRepository.save(book);
    }

    public Library updateBook(Long id, Library bookDetails) {
        Optional<Library> OptionalBook = libraryRepository.findById(id);

        if (OptionalBook.isPresent()) {
            Library book = OptionalBook.get();
            book.setBookName(bookDetails.getBookName());
            book.setAuthor(bookDetails.getAuthor());
            book.setYear(bookDetails.getYear());
            return libraryRepository.save(book);
        } else {
            throw new RuntimeException("Book not found");
        }


    }

    public void deleteBook(Long id){
        libraryRepository.deleteById(id);
    }

}
