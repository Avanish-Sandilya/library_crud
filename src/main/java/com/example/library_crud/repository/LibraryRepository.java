package com.example.library_crud.repository;

import com.example.library_crud.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<Library,Long> {
    List<Library> findByBookNameContainingIgnoreCase(String bookName);

}
