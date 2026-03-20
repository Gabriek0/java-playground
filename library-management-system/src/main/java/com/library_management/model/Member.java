package main.java.com.library_management.model;

import java.time.LocalDate;
import java.util.UUID;

public class Member {
    private String id;
    private String email;
    private String name;
    private LocalDate registrationDate;

    public Member(String email, String name, LocalDate registrationDate) {
        this.id = UUID.randomUUID().toString();
        this.email = email;
        this.name = name;
        this.registrationDate = registrationDate;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "Id: " + id + '\n' + "Name: " + name + '\n' + "Email: " + email + '\n' + "Registration date: " + registrationDate;
    }
}
