package main.java.com.library_management.repository;

import main.java.com.library_management.interfaces.Repository;
import main.java.com.library_management.model.Book;

import java.util.ArrayList;
import java.util.Optional;

public class BookRepository implements Repository<Book, String> {
    private ArrayList<Book> books = new ArrayList<>();

    public void create(Book item) {
        books.add(item);

    }

    public void update(String isbn, Book item) {
        books.removeIf(b -> b.getIsbn().equals(isbn));
        books.add(item);

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
