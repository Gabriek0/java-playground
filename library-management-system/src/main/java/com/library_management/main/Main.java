package main.java.com.library_management.main;

import main.java.com.library_management.enums.Genre;
import main.java.com.library_management.model.Author;
import main.java.com.library_management.model.Book;
import main.java.com.library_management.repository.BookRepository;
import main.java.com.library_management.service.BookService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Author a1 = new Author("George", "Orwell", LocalDate.of(1903, 6, 25));

        Book b1 = new Book(
                "1984",
                a1,
                "978-0451524935",
                1949,
                Genre.DYSTOPIA,
                5
        );

        BookService bookService = new BookService(new BookRepository());
        bookService.createBook(b1);
        bookService.findByIsbn("978-0451524935").ifPresent(System.out::println);

    }
}