package com.auca.student;

import com.auca.abstracts.AcademicMember;
import com.auca.department.Department;
import com.auca.exceptions.ValidationException;
import java.time.LocalDate;

public class Student extends AcademicMember {
    private String studentNumber, programName, guardianPhone;
    private LocalDate admissionDate;
    private int studyYear;
    private boolean scholarshipStatus;

    public Student(String studentNumber, String programName, LocalDate admissionDate, int studyYear, 
                   String guardianPhone, boolean scholarshipStatus, String memberNumber, Department department, 
                   LocalDate joinDate, String academicStatus, String institutionalEmail, String advisorName, 
                   String personId, String firstName, String lastName, String email, String phoneNumber, LocalDate dateOfBirth) {
        super(memberNumber, department, joinDate, academicStatus, institutionalEmail, advisorName, personId, firstName, lastName, email, phoneNumber, dateOfBirth);
        this.studentNumber = studentNumber;
        this.programName = programName;
        this.admissionDate = admissionDate;
        this.studyYear = studyYear;
        this.guardianPhone = guardianPhone;
        this.scholarshipStatus = scholarshipStatus;
    }

    @Override
    public void validate() throws ValidationException {
        super.validate();
        if (studyYear < 1 || studyYear > 8) throw new ValidationException("Study year must be 1-8.");
        if (admissionDate.isAfter(LocalDate.now())) throw new ValidationException("Admission date cannot be future.");
    }

    @Override public String getRoleDescription() { return "Student"; }
    @Override public String getAcademicCategory() { return "Undergraduate"; }
    @Override public String getRegistrationSummary() { return super.getRegistrationSummary() + " | " + studentNumber; }

    // Getters and Setters
    public String getStudentNumber() { return studentNumber; }
    public void setStudentNumber(String studentNumber) { this.studentNumber = studentNumber; }

    public String getProgramName() { return programName; }
    public void setProgramName(String programName) { this.programName = programName; }

    public LocalDate getAdmissionDate() { return admissionDate; }
    public void setAdmissionDate(LocalDate admissionDate) { this.admissionDate = admissionDate; }

    public int getStudyYear() { return studyYear; }
    public void setStudyYear(int studyYear) { this.studyYear = studyYear; }

    public String getGuardianPhone() { return guardianPhone; }
    public void setGuardianPhone(String guardianPhone) { this.guardianPhone = guardianPhone; }

    public boolean isScholarshipStatus() { return scholarshipStatus; }
    public void setScholarshipStatus(boolean scholarshipStatus) { this.scholarshipStatus = scholarshipStatus; }
}