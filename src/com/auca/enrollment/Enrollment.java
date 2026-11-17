package com.auca.enrollment;

import com.auca.course.Course;
import com.auca.interfaces.*;
import com.auca.student.Student;
import com.auca.exceptions.ValidationException;
import java.time.LocalDate;

public class Enrollment implements Identifiable, Registrable {
    private String enrollmentId, semester, enrollmentStatus;
    private Student student;
    private Course course;
    private LocalDate enrollmentDate;

    public Enrollment(String enrollmentId, Student student, Course course, LocalDate enrollmentDate, String semester, String enrollmentStatus) {
        this.enrollmentId = enrollmentId; this.student = student; this.course = course;
        this.enrollmentDate = enrollmentDate; this.semester = semester; this.enrollmentStatus = enrollmentStatus;
    }

    @Override public String getId() { return enrollmentId; }
    @Override public String getDisplayName() { return student.getDisplayName() + " -> " + course.getCourseTitle(); }
    @Override public boolean isActive() { return "ACTIVE".equalsIgnoreCase(enrollmentStatus); }

    @Override
    public void validate() throws ValidationException {
        if (enrollmentId == null || enrollmentId.isBlank()) throw new ValidationException("Enrollment ID required.");
        if (student == null) throw new ValidationException("Student required.");
        if (course == null) throw new ValidationException("Course required.");
        if (enrollmentDate == null || enrollmentDate.isAfter(LocalDate.now())) throw new ValidationException("Date invalid.");
        if (!semester.equals("Sem1") && !semester.equals("Sem2") && !semester.equals("Sem3")) throw new ValidationException("Invalid semester.");
    }
    
    @Override public String getRegistrationSummary() { return getId() + " | " + getDisplayName(); }
    @Override public void printDetails() { System.out.println(getRegistrationSummary()); }
    
    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public String getSemester() { return semester; }
}