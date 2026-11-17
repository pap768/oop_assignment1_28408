package com.auca.assessment;

import com.auca.enrollment.Enrollment;
import com.auca.interfaces.*;
import com.auca.exceptions.ValidationException;
import java.time.LocalDate;

public class Assessment implements Identifiable, Registrable {
    private String assessmentId, assessmentType;
    private Enrollment enrollment;
    private double maximumScore, obtainedScore;
    private LocalDate assessmentDate;

    public Assessment(String assessmentId, Enrollment enrollment, String assessmentType, double maximumScore, double obtainedScore, LocalDate assessmentDate) {
        this.assessmentId = assessmentId;
        this.enrollment = enrollment;
        this.assessmentType = assessmentType;
        this.maximumScore = maximumScore;
        this.obtainedScore = obtainedScore;
        this.assessmentDate = assessmentDate;
    }

    @Override public String getId() { return assessmentId; }
    @Override public String getDisplayName() { return assessmentType + " for " + enrollment.getStudent().getDisplayName(); }
    @Override public boolean isActive() { return enrollment.isActive(); }

    @Override
    public void validate() throws ValidationException {
        if (assessmentId == null || assessmentId.isBlank()) throw new ValidationException("ID required.");
        if (enrollment == null || !enrollment.isActive()) throw new ValidationException("Active Enrollment required.");
        if (maximumScore <= 0) throw new ValidationException("Max score must be > 0.");
        if (obtainedScore < 0 || obtainedScore > maximumScore) throw new ValidationException("Obtained score out of bounds.");
    }
    
    @Override public String getRegistrationSummary() { return getId() + " | " + getDisplayName(); }
    @Override public void printDetails() { System.out.println(getRegistrationSummary()); }
    
    // Getters and Setters
    public Enrollment getEnrollment() { return enrollment; }
    public void setEnrollment(Enrollment enrollment) { this.enrollment = enrollment; }

    public String getAssessmentType() { return assessmentType; }
    public void setAssessmentType(String assessmentType) { this.assessmentType = assessmentType; }

    public double getMaximumScore() { return maximumScore; }
    public void setMaximumScore(double maximumScore) { this.maximumScore = maximumScore; }

    public double getObtainedScore() { return obtainedScore; }
    public void setObtainedScore(double obtainedScore) { this.obtainedScore = obtainedScore; }

    public LocalDate getAssessmentDate() { return assessmentDate; }
    public void setAssessmentDate(LocalDate assessmentDate) { this.assessmentDate = assessmentDate; }
}