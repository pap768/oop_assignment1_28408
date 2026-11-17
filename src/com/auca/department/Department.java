package com.auca.department;

import com.auca.interfaces.*;
import com.auca.teacher.Teacher;
import com.auca.exceptions.ValidationException;

public class Department implements Identifiable, Registrable {
    private String departmentId, departmentName, departmentCode, officeLocation, contactEmail;
    private Teacher headTeacher;

    public Department(String id, String name, String code, String office, String email, Teacher headTeacher) {
        this.departmentId = id;
        this.departmentName = name;
        this.departmentCode = code;
        this.officeLocation = office;
        this.contactEmail = email;
        this.headTeacher = headTeacher;
    }

    @Override public String getId() { return departmentId; }
    @Override public String getDisplayName() { return departmentName; }
    @Override public boolean isActive() { return true; }

    @Override
    public void validate() throws ValidationException {
        if (departmentId == null || departmentId.isBlank()) throw new ValidationException("ID required.");
        if (departmentName == null || departmentName.isBlank()) throw new ValidationException("Name required.");
    }
    
    @Override public String getRegistrationSummary() { return getId() + " | " + getDisplayName(); }
    @Override public void printDetails() { System.out.println(getRegistrationSummary()); }

    // Getters and Setters
    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }

    public String getDepartmentCode() { return departmentCode; }
    public void setDepartmentCode(String departmentCode) { this.departmentCode = departmentCode; }

    public String getOfficeLocation() { return officeLocation; }
    public void setOfficeLocation(String officeLocation) { this.officeLocation = officeLocation; }

    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }

    public Teacher getHeadTeacher() { return headTeacher; }
    public void assignHeadTeacher(Teacher headTeacher) { this.headTeacher = headTeacher; }
}