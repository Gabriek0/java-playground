package main.java.com.library_management.repository;

import main.java.com.library_management.interfaces.Repository;
import main.java.com.library_management.model.Loan;

import java.util.ArrayList;
import java.util.Optional;

public class LoanRepository implements Repository<Loan, String> {
    private ArrayList<Loan> loans = new ArrayList<>();

    public void create(Loan item) {
        loans.add(item);
    }

    public void update(String id, Loan item) {
        loans.removeIf(loan -> loan.getId().equals(id));
        loans.add(item);
    }

    public void delete(String id) {
        loans.removeIf(loan -> loan.getId().equals(id));
    }

    public Optional<Loan> findById(String id) {
        return loans.stream().filter(loan -> loan.getId().equals(id)).findFirst();
    }

    public ArrayList<Loan> list() {
        return loans;
    }
}
