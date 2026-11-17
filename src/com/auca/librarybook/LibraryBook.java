package com.auca.librarybook;

import com.auca.interfaces.*;
import com.auca.exceptions.ValidationException;

public class LibraryBook implements Identifiable, Registrable {
    private String bookId, isbn, title, author;
    private int totalCopies, availableCopies;

    public LibraryBook(String bookId, String isbn, String title, String author, int totalCopies, int availableCopies) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
    }

    @Override public String getId() { return bookId; }
    @Override public String getDisplayName() { return title + " by " + author; }
    @Override public boolean isActive() { return availableCopies > 0; }

    @Override
    public void validate() throws ValidationException {
        if (bookId == null || bookId.isBlank()) throw new ValidationException("Book ID required.");
        if (totalCopies < 0 || availableCopies < 0) throw new ValidationException("Copies cannot be negative.");
        if (availableCopies > totalCopies) throw new ValidationException("Available copies cannot exceed total.");
    }
    
    @Override public String getRegistrationSummary() { return getId() + " | " + getDisplayName() + " | Available: " + availableCopies; }
    @Override public void printDetails() { System.out.println(getRegistrationSummary()); }

    // Getters and Setters
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public int getTotalCopies() { return totalCopies; }
    public void setTotalCopies(int totalCopies) { this.totalCopies = totalCopies; }

    public int getAvailableCopies() { return availableCopies; }
    public void setAvailableCopies(int availableCopies) { this.availableCopies = availableCopies; }
}