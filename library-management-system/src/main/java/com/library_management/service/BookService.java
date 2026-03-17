package main.java.com.library_management.service;

import main.java.com.library_management.enums.Genre;
import main.java.com.library_management.interfaces.Repository;
import main.java.com.library_management.model.Book;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

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
        Optional<Book> book = Optional.empty();

        ArrayList<Book> books = listBooks();

        for (Book value : books) {
            if (Objects.equals(value.getIsbn(), isbn)) {
                book = Optional.of(value);
                break;
            }
        }

        return book;
    }

    public Optional<Book> findByGenre(Genre genre) {
        Optional<Book> book = Optional.empty();

        ArrayList<Book> books = listBooks();

        for (Book value : books) {
            if (Objects.equals(value.getGenre(), genre.toString())) {
                book = Optional.of(value);
                break;
            }
        }

        return book;
    };

//    public Optional<Book> findByAuthor(Author author) {
//        Optional<Book> book = Optional.empty();
//
//        ArrayList<Book> books = listBooks();
//
//        for (Book value : books) {
//            if (Objects.equals(value.getAuthor(), author)) {
//                book = Optional.of(value);
//                break;
//            }
//        }
//
//        return book;
//    };
}
