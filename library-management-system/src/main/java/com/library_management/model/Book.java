package main.java.com.library_management.model;

import main.java.com.library_management.enums.Genre;

import java.util.UUID;

public class Book {
    private String id;
    private String title;
    private Author author;
    private String isbn;
    private int year;
    private Genre genre;
    private int availableCopies;

    public Book(String title, Author author, String isbn, int year, Genre genre, int availableCopies) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.year = year;
        this.genre = genre;
        this.availableCopies = availableCopies;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    @Override
    public String toString() {
        return "ISBN: " + isbn + '\n' + "Títle: " + title + '\n' + "Year: " + year + '\n' + "Genre: " + genre + '\n' + "Available copies: " + availableCopies + '\n';
    }
}
