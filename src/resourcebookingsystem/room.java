package resourcebookingsystem;

import java.util.ArrayList;

public class room {

    String email;
    int roomNo;
    String dateBooked;
    String timeSlot;
    ArrayList<String> resources;
    ArrayList<String> refreshments;

    public room(String email, int roomNo, String dateBooked, String timeSlot, ArrayList<String> resources, ArrayList<String> refreshments) {
        this.email = email;
        this.roomNo = roomNo;
        this.dateBooked = dateBooked;
        this.timeSlot = timeSlot;
        this.resources = resources;
        this.refreshments = refreshments;
    }
    
    public String bookedToString(){
        return roomNo + ", " + dateBooked + ", " + timeSlot;
    }

    @Override
    public String toString() {
        return email + ", " + roomNo + ", " + dateBooked + ", " + timeSlot + ", " + resources + ", " + refreshments;
    }

    public String getDateBooked() {
        return dateBooked;
    }

    public void setDateBooked(String dateBooked) {
        this.dateBooked = dateBooked;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public ArrayList<String> getResources() {
        return resources;
    }

    public void setResources(ArrayList<String> resources) {
        this.resources = resources;
    }

    public ArrayList<String> getRefreshments() {
        return refreshments;
    }

    public void setRefreshments(ArrayList<String> refreshments) {
        this.refreshments = refreshments;
    }

}
