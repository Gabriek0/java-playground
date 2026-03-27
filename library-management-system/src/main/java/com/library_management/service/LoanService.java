package main.java.com.library_management.service;

import main.java.com.library_management.enums.LoanStatus;
import main.java.com.library_management.interfaces.Repository;
import main.java.com.library_management.model.Book;
import main.java.com.library_management.model.Loan;
import main.java.com.library_management.model.Member;

import java.util.*;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        if (isExceededLoans(memberId)) {
            System.out.println("The user has exceeded the limit of " + MAX_LOANS_AMOUNT + " loans");
            return;
        }

        Optional<Book> book = bookService.findByIsbn(isbn);

        if (book.isEmpty()) {
            System.out.println("The book is not available for loan");
            return;
        }

        if (book.get().getAvailableCopies() == 0) {
            System.out.println("There are no copies of this book available for loan");
            return;
        }

        Optional<Member> member = memberService.findById(memberId);

        if (member.isEmpty()) {
            System.out.println("The user does not exist");
            return;
        }


        Loan loan = new Loan(book.get(), member.get(), LoanStatus.BORROWED, LocalDate.now(), LocalDate.now().plusDays(MIN_LOAN_DAYS), null);
        loanRepository.create(loan);

        book.get().setAvailableCopies(book.get().getAvailableCopies() - 1);
        bookService.updateBook(book.get());
    }

    public void returnBook(String loanId) {
        Optional<Loan> loan = findLoanById(loanId);

        if (loan.isEmpty()) {
            System.out.println("No loans were found with the ID " + loanId);
            return;
        }

        loan.get().setLoanStatus(LoanStatus.RETURNED);
        loan.get().setReturnDate(LocalDate.now());
        updateLoan(loan.get());

        Book book = loan.get().getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookService.updateBook(book);
    }

    public ArrayList<Loan> listLoans() {
        return loanRepository.list();
    }

    public List<Loan> listActiveLoans() {
        return listLoans().stream().filter(loan -> loan.getLoanStatus() == LoanStatus.BORROWED).collect(Collectors.toList());
    }

    public void updateLoan(Loan item) {
        loanRepository.update(item.getId(), item);
    }

    public void removeLoan(String id) {
        loanRepository.delete(id);
    }

    public Optional<Loan> findLoanById(String id) {
        return loanRepository.findById(id);
    }

    private boolean isExceededLoans(String memberId) {
        Stream<Loan> loanStream = listLoans().stream().filter(loan -> loan.getMember().getId().equals(memberId));
        long loansBorrowed = loanStream.filter(loan -> loan.getLoanStatus() == LoanStatus.BORROWED).count();
        return loansBorrowed >= MAX_LOANS_AMOUNT;
    }
}
