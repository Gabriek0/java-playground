package main.java.com.library_management.service;

import main.java.com.library_management.enums.Genre;
import main.java.com.library_management.interfaces.Repository;
import main.java.com.library_management.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookService {
    private final Repository<Book, String> bookRepository;

    public BookService(Repository<Book, String> bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void createBook(Book book) {
        Optional<Book> bookExists = findByIsbn(book.getIsbn());

        if (bookExists.isPresent()) {
            System.out.println("There is already a book with the same ISBN");
            return;
        }

        bookRepository.create(book);
    }

    public void updateBook(Book book) {
        Optional<Book> bookExists = findByIsbn(book.getIsbn());

        if (bookExists.isEmpty()) {
            System.out.println("The item you entered does not exist");
            return;
        }

        bookRepository.update(book.getIsbn(), book);
    }

    public void deleteBook(String isbn) {
        this.bookRepository.delete(isbn);
    }

    public ArrayList<Book> listBooks() {
        return this.bookRepository.list();
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
