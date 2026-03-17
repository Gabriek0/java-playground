package main.java.com.library_management.model;

import java.time.LocalDate;

public class Loan {
    Book book;
    Member member;
    LocalDate loanDate;
    LocalDate dueDate;
    LocalDate returnDate;

    public Loan(Book book, Member member, LocalDate loanDate, LocalDate dueDate, LocalDate returnDate) {
        this.book = book;
        this.member = member;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public boolean isOverdue() {
        return dueDate.isBefore(returnDate);
    }
}
