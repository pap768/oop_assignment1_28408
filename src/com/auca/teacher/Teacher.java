package com.auca.teacher;

import com.auca.abstracts.AcademicMember;
import com.auca.department.Department;
import com.auca.exceptions.ValidationException;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Teacher extends AcademicMember {
    private String employeeNumber, specialization, officeNumber, employmentType;
    private LocalDate hireDate;
    private BigDecimal salary;

    public Teacher(String employeeNumber, String specialization, LocalDate hireDate, BigDecimal salary, 
                   String officeNumber, String employmentType, String memberNumber, Department department, 
                   LocalDate joinDate, String academicStatus, String institutionalEmail, String advisorName, 
                   String personId, String firstName, String lastName, String email, String phoneNumber, LocalDate dateOfBirth) {
        super(memberNumber, department, joinDate, academicStatus, institutionalEmail, advisorName, personId, firstName, lastName, email, phoneNumber, dateOfBirth);
        this.employeeNumber = employeeNumber;
        this.specialization = specialization;
        this.hireDate = hireDate;
        this.salary = salary;
        this.officeNumber = officeNumber;
        this.employmentType = employmentType;
    }

    @Override
    public void validate() throws ValidationException {
        super.validate();
        if (salary == null || salary.compareTo(BigDecimal.ZERO) <= 0) throw new ValidationException("Salary must be positive.");
        if (hireDate.isAfter(LocalDate.now())) throw new ValidationException("Hire date cannot be future.");
    }

    @Override public String getRoleDescription() { return "Teacher"; }
    @Override public String getAcademicCategory() { return "Faculty"; }
    @Override public String getRegistrationSummary() { return super.getRegistrationSummary() + " | " + employeeNumber; }

    // Getters and Setters
    public String getEmployeeNumber() { return employeeNumber; }
    public void setEmployeeNumber(String employeeNumber) { this.employeeNumber = employeeNumber; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public LocalDate getHireDate() { return hireDate; }
    public void setHireDate(LocalDate hireDate) { this.hireDate = hireDate; }

    public BigDecimal getSalary() { return salary; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }

    public String getOfficeNumber() { return officeNumber; }
    public void setOfficeNumber(String officeNumber) { this.officeNumber = officeNumber; }

    public String getEmploymentType() { return employmentType; }
    public void setEmploymentType(String employmentType) { this.employmentType = employmentType; }
}