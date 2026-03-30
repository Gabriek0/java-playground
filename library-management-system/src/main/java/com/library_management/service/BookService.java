package main.java.com.library_management.service;

import main.java.com.library_management.enums.Genre;
import main.java.com.library_management.interfaces.Repository;
import main.java.com.library_management.model.Book;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class BookService {
    private final Repository<Book, String> bookRepository;

    public BookService(Repository<Book, String> bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void createBook(Book book) {
        if (findByIsbn(book.getIsbn()).isPresent()) {
            System.out.println("A book with ISBN " + book.getIsbn() + " already exists");
            return;
        }

        bookRepository.create(book);

        System.out.println(book);
        System.out.println("Book successfully created!");
    }

    public void updateBook(Book book) {
        if (findByIsbn(book.getIsbn()).isEmpty()) {
            System.out.println("No book found with ISBN " + book.getIsbn());
            return;
        }

        bookRepository.update(book.getIsbn(), book);

        System.out.println(book);
        System.out.println("Book successfully updated!");
    }

    public void deleteBook(String isbn) {
        if (findByIsbn(isbn).isEmpty()) {
            System.out.println("No book found with ISBN " + isbn);
            return;
        }

        bookRepository.delete(isbn);
        System.out.println("Book successfully deleted!");
    }

    public ArrayList<Book> listBooks() {
        return bookRepository.list();
    }

    public Optional<Book> findByIsbn(String isbn) {
        return listBooks().stream().filter(book -> book.getIsbn().equals(isbn)).findFirst();
    }

    public List<Book> findByGenre(Genre genre) {
        return bookRepository.list().stream().filter(book -> book.getGenre() == genre).collect(Collectors.toList());
    };

    public List<Book> findByAuthor(String authorName) {
        return bookRepository.list().stream().filter(book -> book.getAuthor().getName().equals(authorName)).collect(Collectors.toList());
    };
}
