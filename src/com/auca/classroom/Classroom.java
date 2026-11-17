package com.auca.classroom;

import com.auca.interfaces.*;
import com.auca.exceptions.ValidationException;

public class Classroom implements Identifiable, Registrable {
    private String classroomId, buildingName, roomNumber;
    private int capacity, floorNumber;
    private boolean available;

    public Classroom(String classroomId, String buildingName, String roomNumber, int capacity, int floorNumber, boolean available) {
        this.classroomId = classroomId; this.buildingName = buildingName; this.roomNumber = roomNumber;
        this.capacity = capacity; this.floorNumber = floorNumber; this.available = available;
    }

    @Override public String getId() { return classroomId; }
    @Override public String getDisplayName() { return buildingName + " Room " + roomNumber; }
    @Override public boolean isActive() { return available; }

    @Override
    public void validate() throws ValidationException {
        if (classroomId == null || classroomId.isBlank()) throw new ValidationException("Classroom ID required.");
        if (capacity <= 0) throw new ValidationException("Capacity must be > 0.");
        if (floorNumber < 0) throw new ValidationException("Floor number must be >= 0.");
    }
    
    @Override public String getRegistrationSummary() { return getId() + " | " + getDisplayName(); }
    @Override public void printDetails() { System.out.println(getRegistrationSummary()); }

    public String getBuildingName() { return buildingName; }
    public void setBuildingName(String buildingName) { this.buildingName = buildingName; }
    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public int getFloorNumber() { return floorNumber; }
    public void setFloorNumber(int floorNumber) { this.floorNumber = floorNumber; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}