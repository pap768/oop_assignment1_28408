package com.auca.administrator;

import com.auca.abstracts.Person;
import com.auca.exceptions.ValidationException;
import java.time.LocalDate;

public class Administrator extends Person {
    private String staffNumber, jobTitle, assignedOffice;
    private LocalDate employmentDate;
    private int accessLevel;
    private boolean active;

    public Administrator(String staffNumber, String jobTitle, String assignedOffice, LocalDate employmentDate, 
                         int accessLevel, boolean active, String personId, String firstName, String lastName, 
                         String email, String phoneNumber, LocalDate dateOfBirth) {
        super(personId, firstName, lastName, email, phoneNumber, dateOfBirth);
        this.staffNumber = staffNumber;
        this.jobTitle = jobTitle;
        this.assignedOffice = assignedOffice;
        this.employmentDate = employmentDate;
        this.accessLevel = accessLevel;
        this.active = active;
    }

    @Override
    public void validate() throws ValidationException {
        super.validate();
        if (staffNumber == null || staffNumber.isBlank()) throw new ValidationException("Staff number required.");
        if (accessLevel < 1 || accessLevel > 5) throw new ValidationException("Access level must be 1-5.");
        if (employmentDate == null || employmentDate.isAfter(LocalDate.now())) throw new ValidationException("Employment date cannot be future.");
    }

    @Override public String getRoleDescription() { return "Administrator"; }
    @Override public boolean isActive() { return active; }
    @Override public String getRegistrationSummary() { return super.getRegistrationSummary() + " | " + staffNumber; }

    // Getters and Setters
    public String getStaffNumber() { return staffNumber; }
    public void setStaffNumber(String staffNumber) { this.staffNumber = staffNumber; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public String getAssignedOffice() { return assignedOffice; }
    public void setAssignedOffice(String assignedOffice) { this.assignedOffice = assignedOffice; }

    public LocalDate getEmploymentDate() { return employmentDate; }
    public void setEmploymentDate(LocalDate employmentDate) { this.employmentDate = employmentDate; }

    public int getAccessLevel() { return accessLevel; }
    public void setAccessLevel(int accessLevel) { this.accessLevel = accessLevel; }

    public void setActive(boolean active) { this.active = active; }
}