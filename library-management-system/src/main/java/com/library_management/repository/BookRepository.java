package main.java.com.library_management.repository;

import main.java.com.library_management.interfaces.Repository;
import main.java.com.library_management.model.Book;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class BookRepository implements Repository<Book, String> {
    ArrayList<Book> books = new ArrayList<>();

    public void create(Book book) {
        books.add(book);
    }

    public void update(String isbn, Book book) {
        books.removeIf(b -> b.getIsbn().equals(isbn));
        books.add(book);
    }

    public ArrayList<Book> list() {
        return books;
    }

    public void delete(String isbn) {
       books.removeIf(book -> book.getIsbn().equals(isbn));
    }

    public Optional<Book> findById(String isbn) {
        return books.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst();
    }
}
