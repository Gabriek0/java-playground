package main.java.com.library_management.service;

import main.java.com.library_management.enums.LoanStatus;
import main.java.com.library_management.interfaces.Repository;
import main.java.com.library_management.model.Book;
import main.java.com.library_management.model.Loan;
import main.java.com.library_management.model.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class LoanService {
    private static final int MIN_LOAN_DAYS = 14;
    private static final int MAX_LOANS_AMOUNT = 3;

    private final BookService bookService;
    private final MemberService memberService;
    private final Repository<Loan, String> loanRepository;

    public LoanService(BookService bookService, MemberService memberService, Repository<Loan, String> loanRepository) {
        this.bookService = bookService;
        this.memberService = memberService;
        this.loanRepository = loanRepository;
    }

    public void createLoan(String isbn, String memberId) {
        Optional<Book> book = bookService.findByIsbn(isbn);

        if (book.isEmpty()) {
            System.out.println("No book found with ISBN " + isbn);
            return;
        }

        if (book.get().getAvailableCopies() == 0) {
            System.out.println("There are no available copies of this book");
            return;
        }

        Optional<Member> member = memberService.findById(memberId);

        if (member.isEmpty()) {
            System.out.println("No member found with ID " + memberId);
            return;
        }

        if (isExceededLoans(memberId)) {
            System.out.println("This member has reached the maximum loan limit of " + MAX_LOANS_AMOUNT + " books");
            return;
        }

        Loan loan = new Loan(book.get(), member.get(), LoanStatus.BORROWED, LocalDate.now(), LocalDate.now().plusDays(MIN_LOAN_DAYS), null);
        loanRepository.create(loan);

        book.get().setAvailableCopies(book.get().getAvailableCopies() - 1);
        bookService.updateBook(book.get());

        System.out.println(loan);
        System.out.println("Loan successfully created!");
    }

    public void returnBook(String loanId) {
        Optional<Loan> loan = findLoanById(loanId);

        if (loan.isEmpty()) {
            System.out.println("No loan found with ID " + loanId);
            return;
        }

        loan.get().setLoanStatus(LoanStatus.RETURNED);
        loan.get().setReturnDate(LocalDate.now());
        loanRepository.update(loan.get().getId(), loan.get());

        Book book = loan.get().getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookService.updateBook(book);

        System.out.println(loan.get());
        System.out.println("Book successfully returned!");
    }

    public void updateLoan(Loan item) {
        if (findLoanById(item.getId()).isEmpty()) {
            System.out.println("No loan found with ID " + item.getId());
            return;
        }

        loanRepository.update(item.getId(), item);

        System.out.println(item);
        System.out.println("Loan successfully updated!");
    }

    public void removeLoan(String id) {
        if (findLoanById(id).isEmpty()) {
            System.out.println("No loan found with ID " + id);
            return;
        }

        loanRepository.delete(id);
        System.out.println("Loan successfully removed!");
    }

    public ArrayList<Loan> listLoans() {
        return loanRepository.list();
    }

    public List<Loan> listActiveLoans() {
        return listLoans().stream().filter(loan -> loan.getLoanStatus() == LoanStatus.BORROWED).collect(Collectors.toList());
    }

    public Optional<Loan> findLoanById(String id) {
        return loanRepository.findById(id);
    }

    private boolean isExceededLoans(String memberId) {
        return listLoans().stream()
                .filter(loan -> loan.getMember().getId().equals(memberId))
                .filter(loan -> loan.getLoanStatus() == LoanStatus.BORROWED)
                .count() >= MAX_LOANS_AMOUNT;
    }
}
