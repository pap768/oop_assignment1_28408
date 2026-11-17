package com.auca.main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Instantiate the Manager that implements the interface
        SchoolManager manager = new SchoolManager();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=========================================");
            System.out.println("       AUCA_28408 SCHOOL MANAGEMENT          ");
            System.out.println("=========================================");
            System.out.println("1.  Register Department");
            System.out.println("2.  Register Student");
            System.out.println("3.  Register Teacher");
            System.out.println("4.  Register Administrator");
            System.out.println("5.  Register Course");
            System.out.println("6.  Register Classroom");
            System.out.println("7.  Enroll Student");
            System.out.println("8.  Record Assessment");
            System.out.println("9.  Record Fee Payment");
            System.out.println("10. Register Library Book");
            System.out.println("11. Borrow Book");
            System.out.println("12. Return Book");
            System.out.println("13. View All Records");
            System.out.println("14. Search by ID");
            System.out.println("15. Update Record");
            System.out.println("16. Show Student Academic Summary");
            System.out.println("17. Show Payment History");
            System.out.println("18. Show Available Books");
            System.out.println("19. Demonstrate Polymorphism");
            System.out.println("0.  Exit");
            System.out.println("=========================================");
            System.out.print("Select an option: ");

            try {
                String input = sc.nextLine().trim();
                int choice = Integer.parseInt(input);

                // THIS IS THE CRUCIAL PART: 
                // Routing every option to the correct method in SchoolManager
                switch (choice) {
                    case 1  -> manager.registerDepartment();
                    case 2  -> manager.registerStudent();
                    case 3  -> manager.registerTeacher();
                    case 4  -> manager.registerAdministrator();
                    case 5  -> manager.registerCourse();
                    case 6  -> manager.registerClassroom();
                    case 7  -> manager.enrollStudent();
                    case 8  -> manager.recordAssessment();
                    case 9  -> manager.recordFeePayment();
                    case 10 -> manager.registerLibraryBook();
                    case 11 -> manager.borrowBook();
                    case 12 -> manager.returnBook();
                    case 13 -> manager.viewAllRecords();
                    case 14 -> manager.searchById();
                    case 15 -> manager.updateRecord();
                    case 16 -> manager.showStudentAcademicSummary();
                    case 17 -> manager.showPaymentHistory();
                    case 18 -> manager.showAvailableBooks();
                    case 19 -> manager.demonstratePolymorphism();
                    case 0  -> {
                        running = false;
                        System.out.println("Exiting AUCA_ID. Goodbye!");
                    }
                    default -> System.out.println("! Invalid choice. Please enter a number between 0 and 19.");
                }
            } catch (NumberFormatException e) {
                System.out.println("! Invalid input. Please enter a numeric value.");
            } catch (Exception e) {
                // This catches ValidationException, DuplicateRecordException, etc. from the manager
                System.out.println("! Error: " + e.getMessage());
            }
        }
        sc.close();
    }
}