package main.java.com.library_management.main;

import main.java.com.library_management.enums.Genre;
import main.java.com.library_management.model.Author;
import main.java.com.library_management.model.Book;
import main.java.com.library_management.model.Member;
import main.java.com.library_management.repository.BookRepository;
import main.java.com.library_management.repository.LoanRepository;
import main.java.com.library_management.repository.MemberRepository;
import main.java.com.library_management.service.BookService;
import main.java.com.library_management.service.LoanService;
import main.java.com.library_management.service.MemberService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        Scanner sc = new Scanner(System.in);

        BookService bookService = new BookService(new BookRepository());
        MemberService memberService = new MemberService(new MemberRepository());
        LoanService loanService = new LoanService(bookService, memberService, new LoanRepository());

        while(true) {
            System.out.println("=== Library Management ===");
            System.out.println("1 - Books");
            System.out.println("2 - Members");
            System.out.println("3 - Loans");
            System.out.println("0 - Exit");
            System.out.println();

            int option;
            option = sc.nextInt();

            switch(option) {
                case (1):
                    booksMenu(sc, bookService);
                    break;

                case (2):
                    membersMenu(sc, memberService);
                    break;

                case (3):
                    loansMenu(sc, loanService);
                    break;

                case(0):
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option!");
                    sc.close();
                    System.exit(0);
                    break;
            }
        }
    }

    public static void booksMenu(Scanner sc, BookService bookService) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while(true) {
            System.out.println("=== Books ===");
            System.out.println("1 - Add a book");
            System.out.println("2 - List all books");
            System.out.println("3 - Update a book");
            System.out.println("4 - Remove a book");
            System.out.println("0 - Back");
            System.out.println();

            int option = sc.nextInt();
            sc.nextLine();

            switch(option) {
                case 0:
                    return;
                case 1:
                    System.out.println("--- Add a Book ---");
                    System.out.println();

                    System.out.print("Title: ");
                    String title = sc.nextLine();

                    System.out.print("Author name: ");
                    String authorName = sc.nextLine();

                    System.out.print("Author nationality: ");
                    String authorNationality = sc.nextLine();

                    System.out.print("Author birth date (dd/MM/yyyy): ");
                    LocalDate authorBirthDate = LocalDate.parse(sc.nextLine(), formatter);

                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();

                    System.out.print("Year: ");
                    int year = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Genre: ");
                    Genre genre = Genre.valueOf(sc.nextLine());

                    System.out.print("Available copies: ");
                    int availableCopies = sc.nextInt();
                    sc.nextLine();

                    Author author = new Author(authorName, authorNationality, authorBirthDate);
                    Book book = new Book(title, author, isbn, year, genre, availableCopies);

                    System.out.println();
                    bookService.createBook(book);
                    System.out.println();

                    break;
                case 2:
                    System.out.println();
                    System.out.println("--- Book List ---");
                    System.out.println();

                    ArrayList<Book> books = bookService.listBooks();

                    if (books.isEmpty()) {
                        System.out.println("No books have been added yet");
                    } else {
                        books.forEach(b -> System.out.println(b + "\n"));
                    }

                    System.out.println();
                    break;
                case 3:
                    System.out.println();
                    System.out.println("--- Update a Book ---");

                    System.out.print("ISBN: ");
                    String isbnUpd = sc.nextLine();

                    System.out.print("Title: ");
                    String titleUpd = sc.nextLine();

                    System.out.print("Author name: ");
                    String authorNameUpd = sc.nextLine();

                    System.out.print("Author nationality: ");
                    String authorNationalityUpd = sc.nextLine();

                    System.out.print("Author birth date (dd/MM/yyyy): ");
                    LocalDate authorBirthDateUpd = LocalDate.parse(sc.nextLine(), formatter);

                    System.out.print("Year: ");
                    int yearUpd = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Genre: ");
                    Genre genreUpd = Genre.valueOf(sc.nextLine());

                    System.out.print("Available copies: ");
                    int availableCopiesUpd = sc.nextInt();
                    sc.nextLine();

                    Author authorUpd = new Author(authorNameUpd, authorNationalityUpd, authorBirthDateUpd);
                    Book bookUpd = new Book(titleUpd, authorUpd, isbnUpd, yearUpd, genreUpd, availableCopiesUpd);

                    System.out.println();
                    bookService.updateBook(bookUpd);
                    System.out.println();

                    break;

                case 4:
                    System.out.println();
                    System.out.println("--- Remove a Book ---");
                    System.out.println();
                    System.out.print("ISBN: ");
                    String isbnDel = sc.nextLine();

                    bookService.deleteBook(isbnDel);
                    System.out.println();

                    break;
                default:
                    System.out.println("Invalid option!");
                    sc.close();
                    System.exit(0);
                    break;

            }

        }
    }

    public static void membersMenu(Scanner sc, MemberService memberService) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (true) {
            System.out.println("=== Members ===");
            System.out.println("1 - Register a new member");
            System.out.println("2 - List all members");
            System.out.println("3 - Update a member");
            System.out.println("4 - Remove a member");
            System.out.println("0 - Back");
            System.out.println();

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 0:
                    return;

                case 1:
                    System.out.println();
                    System.out.println("--- Register a New Member ---");
                    System.out.println();

                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Registration date (dd/MM/yyyy): ");
                    LocalDate registrationDate = LocalDate.parse(sc.nextLine(), formatter);

                    Member newMember = new Member(email, name, registrationDate);

                    System.out.println();
                    memberService.createMember(newMember);
                    System.out.println();
                    break;

                case 2:
                    System.out.println();
                    System.out.println("--- Member List ---");
                    System.out.println();

                    var members = memberService.listMembers();

                    if (members.isEmpty()) {
                        System.out.println("No members have been registered yet.");
                        System.out.println();
                    } else {
                        members.forEach(m -> {
                            System.out.println(m);
                            System.out.println();
                        });
                    }

                    break;

                case 3:
                    System.out.println();
                    System.out.println("--- Update a Member ---");

                    System.out.print("Member ID: ");
                    String idUpd = sc.nextLine();

                    System.out.print("New name: ");
                    String nameUpd = sc.nextLine();

                    System.out.print("New email: ");
                    String emailUpd = sc.nextLine();

                    System.out.print("New registration date (dd/MM/yyyy): ");
                    LocalDate registrationDateUpd = LocalDate.parse(sc.nextLine(), formatter);

                    Member updatedMember = new Member(emailUpd, nameUpd, registrationDateUpd);

                    System.out.println();
                    memberService.updateMember(idUpd, updatedMember);
                    System.out.println();
                    break;

                case 4:
                    System.out.println();
                    System.out.println("--- Remove a Member ---");
                    System.out.println();
                    System.out.print("Member ID: ");
                    String idDel = sc.nextLine();

                    System.out.println();
                    memberService.removeMember(idDel);
                    System.out.println();
                    break;

                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    public static void loansMenu(Scanner sc, LoanService loanService) {
        while (true) {
            System.out.println("=== Loans ===");
            System.out.println("1 - Loan a book to a member");
            System.out.println("2 - Return a book");
            System.out.println("3 - List all active loans");
            System.out.println("4 - List loans by member");
            System.out.println("0 - Back");
            System.out.println();

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 0:
                    return;

                case 1:
                    System.out.println();
                    System.out.println("--- Loan a Book to a Member ---");
                    System.out.println();

                    System.out.print("Book ISBN: ");
                    String isbn = sc.nextLine();

                    System.out.print("Member ID: ");
                    String memberId = sc.nextLine();

                    loanService.createLoan(isbn, memberId);

                    System.out.println();
                    break;

                case 2:
                    System.out.println();
                    System.out.println("--- Return a Book ---");
                    System.out.println();
                    System.out.print("Loan ID: ");
                    String loanId = sc.nextLine();

                    System.out.println();
                    loanService.returnBook(loanId);
                    System.out.println();
                    break;

                case 3:
                    System.out.println();
                    System.out.println("--- Active Loans ---");
                    System.out.println();

                    var activeLoans = loanService.listActiveLoans();

                    if (activeLoans.isEmpty()) {
                        System.out.println("There are no active loans at the moment.");
                        System.out.println();
                    } else {
                        activeLoans.forEach(loan -> {
                            System.out.println(loan);
                            System.out.println();
                        });
                    }
                    break;

                case 4:
                    System.out.println();
                    System.out.println("--- Loans by Member ---");
                    System.out.println();
                    System.out.print("Member ID: ");
                    String memberIdFilter = sc.nextLine();

                    var memberLoans = loanService.listLoans().stream()
                            .filter(loan -> loan.getMember().getId().equals(memberIdFilter))
                            .toList();

                    System.out.println();

                    if (memberLoans.isEmpty()) {
                        System.out.println("No loans found for this member.");
                        System.out.println();
                    } else {
                        System.out.println("Loans for member " + memberIdFilter + ":");
                        System.out.println();
                        memberLoans.forEach(loan -> {
                            System.out.println(loan);
                            System.out.println();
                        });
                    }
                    break;

                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }
}