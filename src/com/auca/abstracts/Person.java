package com.auca.abstracts;
import com.auca.interfaces.*;
import com.auca.exceptions.ValidationException;
import java.time.LocalDate;

public abstract class Person implements Identifiable, Registrable {
    private String personId, firstName, lastName, email, phoneNumber;
    private LocalDate dateOfBirth;

    public Person(String personId, String firstName, String lastName, String email, String phoneNumber, LocalDate dateOfBirth) {
        this.personId = personId; this.firstName = firstName; this.lastName = lastName;
        this.email = email; this.phoneNumber = phoneNumber; this.dateOfBirth = dateOfBirth;
    }

    @Override public String getId() { return personId; }
    @Override public String getDisplayName() { return firstName + " " + lastName; }
    @Override public boolean isActive() { return true; }

    @Override
    public void validate() throws ValidationException {
        if (personId == null || personId.isBlank()) throw new ValidationException("Person ID required.");
        if (firstName == null || firstName.trim().length() < MIN_NAME_LENGTH) throw new ValidationException("First name too short.");
        if (email == null || !email.matches("^[\\w.+-]+@[\\w-]+\\.[\\w.]+$")) throw new ValidationException("Invalid email.");
        if (phoneNumber == null || !phoneNumber.matches("^\\+?\\d{10,15}$")) {
            throw new ValidationException("Phone must contain 10-15 digits, optionally starting with +, with no spaces or hyphens.");
        }
        if (dateOfBirth == null || !dateOfBirth.isBefore(LocalDate.now())) throw new ValidationException("DOB must be past.");
    }

    @Override public String getRegistrationSummary() { return getId() + " | " + getDisplayName(); }
    @Override public void printDetails() { System.out.println(this); }
    
    public abstract String getRoleDescription();
    
    // Getters
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }

    public String getPersonId() { return personId; }
    public void setPersonId(String personId) { this.personId = personId; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
}