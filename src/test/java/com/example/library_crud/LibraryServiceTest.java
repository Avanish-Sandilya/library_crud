package com.example.library_crud.service;

import com.example.library_crud.dto.LibraryRequestDTO;
import com.example.library_crud.dto.LibraryResponseDTO;
import com.example.library_crud.model.Library;
import com.example.library_crud.repository.LibraryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LibraryServiceTest {

    @Mock
    private LibraryRepository libraryRepository;

    @InjectMocks
    private LibraryService libraryService;

    private Library book1;
    private Library book2;

    @BeforeEach
    void setUp() {
        book1 = new Library();
        book1.setId(1L);
        book1.setBookName("The Hobbit");
        book1.setAuthor("J.R.R. Tolkien");
        book1.setYear(1937);

        book2 = new Library();
        book2.setId(2L);
        book2.setBookName("1984");
        book2.setAuthor("George Orwell");
        book2.setYear(1949);
    }

    @Test
    void testGetBooks_ReturnsAllBooks() {
        when(libraryRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        List<LibraryResponseDTO> books = libraryService.getBooks(Optional.empty());

        assertEquals(2, books.size());
        assertEquals("The Hobbit", books.get(0).getBookName());
        assertEquals("1984", books.get(1).getBookName());

        verify(libraryRepository, times(1)).findAll();
    }

    @Test
    void testGetBooksByName_ReturnsFilteredBooks() {
        when(libraryRepository.findByBookNameContainingIgnoreCase("hobbit")).thenReturn(List.of(book1));

        List<LibraryResponseDTO> books = libraryService.getBooks(Optional.of("hobbit"));

        assertEquals(1, books.size());
        assertEquals("The Hobbit", books.get(0).getBookName());

        verify(libraryRepository, times(1)).findByBookNameContainingIgnoreCase("hobbit");
    }

    @Test
    void testGetBookById_ReturnsBook() {
        when(libraryRepository.findById(1L)).thenReturn(Optional.of(book1));

        Optional<LibraryResponseDTO> result = libraryService.getBookById(1L);

        assertTrue(result.isPresent());
        assertEquals("The Hobbit", result.get().getBookName());

        verify(libraryRepository, times(1)).findById(1L);
    }

    @Test
    void testAddBook_SavesBookSuccessfully() {
        LibraryRequestDTO requestDTO = new LibraryRequestDTO();
        requestDTO.setBookName("Dune");
        requestDTO.setAuthor("Frank Herbert");
        requestDTO.setYear(1965);

        Library savedBook = new Library();
        savedBook.setId(3L);
        savedBook.setBookName(requestDTO.getBookName());
        savedBook.setAuthor(requestDTO.getAuthor());
        savedBook.setYear(requestDTO.getYear());

        when(libraryRepository.save(any(Library.class))).thenReturn(savedBook);

        LibraryResponseDTO response = libraryService.addBook(requestDTO);

        assertNotNull(response);
        assertEquals("Dune", response.getBookName());
        assertEquals("Frank Herbert", response.getAuthor());
        assertEquals(1965, response.getYear());

        verify(libraryRepository, times(1)).save(any(Library.class));
    }

    @Test
    void testUpdateBook_UpdatesBookSuccessfully() {
        LibraryRequestDTO requestDTO = new LibraryRequestDTO();
        requestDTO.setBookName("Dune - Updated");
        requestDTO.setAuthor("Frank Herbert");
        requestDTO.setYear(1965);

        when(libraryRepository.findById(1L)).thenReturn(Optional.of(book1));
        when(libraryRepository.save(any(Library.class))).thenReturn(book1);

        LibraryResponseDTO response = libraryService.updateBook(1L, requestDTO);

        assertNotNull(response);
        assertEquals("Dune - Updated", response.getBookName());

        verify(libraryRepository, times(1)).findById(1L);
        verify(libraryRepository, times(1)).save(book1);
    }

    @Test
    void testDeleteBook_RemovesBookSuccessfully() {
        doNothing().when(libraryRepository).deleteById(1L);

        libraryService.deleteBook(1L);

        verify(libraryRepository, times(1)).deleteById(1L);
    }
}
