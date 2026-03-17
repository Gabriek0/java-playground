package main.java.com.library_management.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Member {
    String id;
    String email;
    String name;
    LocalDate registrationDate;
    ArrayList<Loan> loans;

    public Member(String id, String email, String name, LocalDate registrationDate, ArrayList<Loan> loans) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.registrationDate = registrationDate;
        this.loans = loans;
    }

    public boolean isElegibleToLoan() {
        return loans.size() < 3;
    }
}
