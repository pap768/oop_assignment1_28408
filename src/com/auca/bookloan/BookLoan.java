package com.auca.bookloan;

import com.auca.interfaces.*;
import com.auca.librarybook.LibraryBook;
import com.auca.student.Student;
import com.auca.exceptions.ValidationException;
import java.time.LocalDate;

public class BookLoan implements Identifiable, Registrable {
    private String loanId;
    private Student student;
    private LibraryBook book;
    private LocalDate borrowedDate, dueDate, returnedDate;

    public BookLoan(String loanId, Student student, LibraryBook book, LocalDate borrowedDate, LocalDate dueDate, LocalDate returnedDate) {
        this.loanId = loanId; this.student = student; this.book = book;
        this.borrowedDate = borrowedDate; this.dueDate = dueDate; this.returnedDate = returnedDate;
    }

    @Override public String getId() { return loanId; }
    @Override public String getDisplayName() { return book.getTitle() + " borrowed by " + student.getDisplayName(); }
    @Override public boolean isActive() { return returnedDate == null; }

    @Override
    public void validate() throws ValidationException {
        if (loanId == null || loanId.isBlank()) throw new ValidationException("ID required.");
        if (student == null || book == null) throw new ValidationException("Student and Book required.");
        if (dueDate == null || borrowedDate == null || !dueDate.isAfter(borrowedDate)) throw new ValidationException("Due date must be after borrowed date.");
        if (returnedDate != null && returnedDate.isBefore(borrowedDate)) throw new ValidationException("Return date invalid.");
    }
    
    @Override public String getRegistrationSummary() { return getId() + " | " + getDisplayName(); }
    @Override public void printDetails() { System.out.println(getRegistrationSummary()); }
    
    public LibraryBook getBook() { return book; }
    public LocalDate getReturnedDate() { return returnedDate; }
    public void setReturnedDate(LocalDate returnedDate) { this.returnedDate = returnedDate; }
}