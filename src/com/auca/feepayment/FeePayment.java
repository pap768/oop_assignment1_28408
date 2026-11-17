package com.auca.feepayment;

import com.auca.interfaces.*;
import com.auca.student.Student;
import com.auca.exceptions.ValidationException;
import java.math.BigDecimal;
import java.time.LocalDate;

public class FeePayment implements Identifiable, Registrable {
    private String paymentId, paymentMethod, receiptNumber;
    private Student student;
    private BigDecimal amount;
    private LocalDate paymentDate;

    public FeePayment(String paymentId, Student student, BigDecimal amount, LocalDate paymentDate, String paymentMethod, String receiptNumber) {
        this.paymentId = paymentId;
        this.student = student;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.receiptNumber = receiptNumber;
    }

    @Override public String getId() { return paymentId; }
    @Override public String getDisplayName() { return "Payment of " + amount + " by " + student.getDisplayName(); }
    @Override public boolean isActive() { return true; }

    @Override
    public void validate() throws ValidationException {
        if (paymentId == null || paymentId.isBlank()) throw new ValidationException("ID required.");
        if (student == null) throw new ValidationException("Student required.");
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) throw new ValidationException("Amount must be > 0.");
        if (receiptNumber == null || receiptNumber.isBlank()) throw new ValidationException("Receipt number required.");
    }
    
    @Override public String getRegistrationSummary() { return getId() + " | " + getDisplayName(); }
    @Override public void printDetails() { System.out.println(getRegistrationSummary()); }

    // Getters and Setters
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getReceiptNumber() { return receiptNumber; }
    public void setReceiptNumber(String receiptNumber) { this.receiptNumber = receiptNumber; }
}