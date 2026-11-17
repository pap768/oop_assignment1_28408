package com.auca.course;

import com.auca.department.Department;
import com.auca.interfaces.*;
import com.auca.teacher.Teacher;
import com.auca.exceptions.ValidationException;

public class Course implements Identifiable, Registrable {
    private String courseId, courseCode, courseTitle;
    private int creditHours;
    private Department department;
    private Teacher assignedTeacher;

    public Course(String courseId, String courseCode, String courseTitle, int creditHours, Department department, Teacher assignedTeacher) {
        this.courseId = courseId; this.courseCode = courseCode; this.courseTitle = courseTitle;
        this.creditHours = creditHours; this.department = department; this.assignedTeacher = assignedTeacher;
    }

    @Override public String getId() { return courseId; }
    @Override public String getDisplayName() { return courseCode + " - " + courseTitle; }
    @Override public boolean isActive() { return true; }

    @Override
    public void validate() throws ValidationException {
        if (courseId == null || courseId.isBlank()) throw new ValidationException("Course ID required.");
        if (creditHours < 1 || creditHours > 6) throw new ValidationException("Credit hours must be 1-6.");
        if (department == null) throw new ValidationException("Department required.");
        if (assignedTeacher == null) throw new ValidationException("Teacher required.");
    }
    
    @Override public String getRegistrationSummary() { return getId() + " | " + getDisplayName(); }
    @Override public void printDetails() { System.out.println(getRegistrationSummary()); }
    
    public String getCourseId() { return courseId; }
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public String getCourseTitle() { return courseTitle; }
    public void setCourseTitle(String courseTitle) { this.courseTitle = courseTitle; }
    public int getCreditHours() { return creditHours; }
    public void setCreditHours(int creditHours) { this.creditHours = creditHours; }
    public Department getDepartment() { return department; }
    public Teacher getAssignedTeacher() { return assignedTeacher; }
    public void setAssignedTeacher(Teacher assignedTeacher) { this.assignedTeacher = assignedTeacher; }
}