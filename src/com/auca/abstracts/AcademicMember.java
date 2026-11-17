package com.auca.abstracts;

import com.auca.department.Department;
import com.auca.exceptions.ValidationException;
import java.time.LocalDate;

public abstract class AcademicMember extends Person {
    private String memberNumber;
    private Department department;
    private LocalDate joinDate;
    private String academicStatus, institutionalEmail, advisorName;

    public AcademicMember(String memberNumber, Department department, LocalDate joinDate, 
                          String academicStatus, String institutionalEmail, String advisorName, 
                          String personId, String firstName, String lastName, String email, 
                          String phoneNumber, LocalDate dateOfBirth) {
        super(personId, firstName, lastName, email, phoneNumber, dateOfBirth);
        this.memberNumber = memberNumber;
        this.department = department;
        this.joinDate = joinDate;
        this.academicStatus = academicStatus;
        this.institutionalEmail = institutionalEmail;
        this.advisorName = advisorName;
    }

    @Override
    public void validate() throws ValidationException {
        super.validate();
        if (memberNumber == null || memberNumber.isBlank()) throw new ValidationException("Member number required.");
        if (department == null) throw new ValidationException("Department required.");
        if (joinDate == null || joinDate.isAfter(LocalDate.now())) throw new ValidationException("Join date invalid.");
    }

    public abstract String getAcademicCategory();
    
    // Getters and Setters
    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
    
    public String getMemberNumber() { return memberNumber; }
    public void setMemberNumber(String memberNumber) { this.memberNumber = memberNumber; }

    public LocalDate getJoinDate() { return joinDate; }
    public void setJoinDate(LocalDate joinDate) { this.joinDate = joinDate; }

    public String getAcademicStatus() { return academicStatus; }
    public void setAcademicStatus(String academicStatus) { this.academicStatus = academicStatus; }

    public String getInstitutionalEmail() { return institutionalEmail; }
    public void setInstitutionalEmail(String institutionalEmail) { this.institutionalEmail = institutionalEmail; }

    public String getAdvisorName() { return advisorName; }
    public void setAdvisorName(String advisorName) { this.advisorName = advisorName; }
}