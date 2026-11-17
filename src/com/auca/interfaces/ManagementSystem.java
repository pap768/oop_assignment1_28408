package com.auca.interfaces;

public interface ManagementSystem {
    void registerDepartment() throws Exception;
    void registerStudent() throws Exception;
    void registerTeacher() throws Exception;
    void registerAdministrator() throws Exception;
    void registerCourse() throws Exception;
    void registerClassroom() throws Exception;
    void enrollStudent() throws Exception;
    void recordAssessment() throws Exception;
    void recordFeePayment() throws Exception;
    void registerLibraryBook() throws Exception;
    void borrowBook() throws Exception;
    void returnBook() throws Exception;
    void viewAllRecords();
    void searchById();
    void updateRecord() throws Exception;
    void showStudentAcademicSummary();
    void showPaymentHistory();
    void showAvailableBooks();
    void demonstratePolymorphism();
}