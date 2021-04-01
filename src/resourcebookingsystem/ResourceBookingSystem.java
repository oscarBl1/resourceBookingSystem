package resourcebookingsystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ResourceBookingSystem {

    static Scanner scan = new Scanner(System.in);
    static String password = "password123";
    public static void main(String[] args) {
        System.out.println("Would you like to book a room? y/n");
        String booking = scan.next();
        if (booking.equalsIgnoreCase("y")) {
            getInfo();
        }else{
            System.out.println("Are you catering staff? y/n");
            String staff = scan.next();
            if(staff.equalsIgnoreCase("y")){
                System.out.println("Enter the password");
                String pass = scan.next();
                if(pass.equals(password)){
                    System.out.println("Would you like to view the orders? y/n");
                    String viewOrder = scan.next();
                    if(viewOrder.equalsIgnoreCase("y")){
                        cateringStaff();
                    }
                }
            }
        }
    }
    
    public static void cateringStaff(){
        for(int i = 0 ; i < readFile().size(); i++){
            String[] list = readFile().get(i).split(", ");
            if(list[5] != null){
                System.out.println(list[5] + " at " + list[3] + " in room: " + list[1]);
            }
        }
    }

    public static boolean emailVerify(String email) {
        boolean correctEmailFormat = false;
        String emailName = email.split("@")[0];
        String emailProvider = email.split("@")[1].split(".com")[0];
        if (email.contains("@") && email.contains(".com")) {
            correctEmailFormat = true;
            if (emailName.contains("..") || emailProvider.startsWith(".")) {
                correctEmailFormat = false;
            }
        }
        return correctEmailFormat;

    }

    public static boolean compare(int roomBooked, String date, String time) {
        boolean alreadyBooked = false;
        for (int i = 0; i < readFile().size(); i++) {
            String[] list = readFile().get(i).split(", ");
            int room = Integer.parseInt(list[1]);
            String dateB = list[2];
            String timeB = list[3];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date1 = LocalDate.parse(date, formatter);
            LocalDate todayDate = LocalDate.parse(LocalDate.now().format(formatter), formatter);

            if (date1.isBefore(todayDate) || date1.isEqual(todayDate)) {
                System.out.println("Cannot book before today, Please try again");
                alreadyBooked = true;
            } else {
                if (roomBooked == room && date.equalsIgnoreCase(dateB) && time.equalsIgnoreCase(timeB)) {
                    System.out.println("Room already booked try again");
                    alreadyBooked = true;
                } else {
                    //System.out.println("Room is free");
                    alreadyBooked = false;
                }
            }
        }

        return alreadyBooked;

    }

    public static ArrayList<String> readFile() {
        ArrayList<String> bookedRooms = new ArrayList<>();
        String inputLine;
        try {
            BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\bookedRooms.txt"));
            while ((inputLine = br.readLine()) != null) {
                bookedRooms.add(inputLine);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookedRooms;

    }

    public static void writeFile(String bookedRoomInfo) {
        try {
            FileWriter fw = new FileWriter(System.getProperty("user.dir") + "\\bookedRooms.txt", true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(bookedRoomInfo);
            pw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getInfo() {
        try {
            int room;
            while (true) {
                System.out.println("Which room would you like? \nRoom 1 - 2 people \nRoom 2 - 4 people \nRoom 3 - 8 people \nRoom 4 - 15 people \nRoom 5 - 50 people");
                room = scan.nextInt();
                if (room <= 5 && room > 0) {
                    break;
                } else {
                    System.out.println("Incorrect room choice");
                }
            }
            String date;
            String time;
            while (true) {

                System.out.println("Enter your date in the format dd/MM/yyyy");
                date = scan.next();

                System.out.println("Enter your time slot in the format hh:00");
                time = scan.next();

                if (!compare(room, date, time)) {
                    System.out.println("Room is free");
                    break;
                }

            }
            System.out.println("Enter your email address");
            String email = scan.next();
            emailVerify(email);

            ArrayList<String> resources = new ArrayList<>();
            while (true) {
                System.out.println("What resources would you like? 0 to exit");
                String resource = scan.next();
                if (resource.equals("0")) {
                    break;
                }
                resources.add(resource);
            }
            ArrayList<String> refreshments = new ArrayList<>();
            while (true) {
                System.out.println("What refreshments would you like? 0 to exit");
                String refreshment = scan.next();
                if (refreshment.equals("0")) {
                    break;
                }
                refreshments.add(refreshment);
            }

            room r1 = new room(email, room, date, time, resources, refreshments);
            System.out.println(r1.toString());

            writeFile(r1.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
