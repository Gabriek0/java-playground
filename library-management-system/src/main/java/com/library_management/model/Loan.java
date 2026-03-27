package main.java.com.library_management.model;

import main.java.com.library_management.enums.LoanStatus;

import java.time.LocalDate;
import java.util.UUID;

public class Loan {
    private String id;
    private Book book;
    private Member member;
    private LoanStatus loanStatus;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public Loan(Book book, Member member, LoanStatus loanStatus, LocalDate loanDate, LocalDate dueDate, LocalDate returnDate) {
        this.id = UUID.randomUUID().toString();
        this.book = book;
        this.member = member;
        this.loanDate = loanDate;
        this.loanStatus = loanStatus;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public String getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public Member getMember() {
        return member;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    @Override
    public String toString() {
        return "Id: " + id + '\n' + book.toString() + member.toString() + "Loan Date: " + loanDate + '\n' + "Due Date: " + dueDate + '\n' + "Return Date: " + returnDate + '\n';
    }
}
