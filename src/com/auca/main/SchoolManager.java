package com.auca.main;

import com.auca.administrator.Administrator;
import com.auca.assessment.Assessment;
import com.auca.bookloan.BookLoan;
import com.auca.classroom.Classroom;
import com.auca.course.Course;
import com.auca.department.Department;
import com.auca.enrollment.Enrollment;
import com.auca.exceptions.*;
import com.auca.feepayment.FeePayment;
import com.auca.interfaces.ManagementSystem;
import com.auca.librarybook.LibraryBook;
import com.auca.student.Student;
import com.auca.teacher.Teacher;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SchoolManager implements ManagementSystem {

    private static final Scanner SC = new Scanner(System.in);

    private final List<Department> departments = new ArrayList<>();
    private final List<Student> students = new ArrayList<>();
    private final List<Teacher> teachers = new ArrayList<>();
    private final List<Administrator> administrators = new ArrayList<>();
    private final List<Course> courses = new ArrayList<>();
    private final List<Classroom> classrooms = new ArrayList<>();
    private final List<Enrollment> enrollments = new ArrayList<>();
    private final List<Assessment> assessments = new ArrayList<>();
    private final List<FeePayment> feePayments = new ArrayList<>();
    private final List<LibraryBook> libraryBooks = new ArrayList<>();
    private final List<BookLoan> bookLoans = new ArrayList<>();

    @Override
    public void registerDepartment() throws Exception {
        System.out.println("\n--- REGISTER DEPARTMENT ---");
        String id = readString("Department ID: ");
        if (findDepartment(id) != null) throw new DuplicateRecordException("Department ID exists.");
        String name = readString("Name: ");
        String code = readString("Code: ");
        String office = readString("Office: ");
        String email = readString("Email: ");

        Department d = new Department(id, name, code, office, email, null);
        d.validate();
        departments.add(d);
        System.out.println("✔ Saved: " + d.getRegistrationSummary());
    }

    @Override
    public void registerStudent() throws Exception {
        System.out.println("\n--- REGISTER STUDENT ---");
        if (departments.isEmpty()) {
            System.out.println("No department exists. Please create one first.");
            registerDepartment();
            if (departments.isEmpty()) return;
        }

        Department dept = selectDepartment();
        if (dept == null) return;

        String personId = readString("Person ID: ");
        String firstName = readString("First name: ");
        String lastName = readString("Last name: ");
        String email = readString("Email: ");
        String phone = readString("Phone number: ");
        LocalDate dob = readDate("Date of birth (YYYY-MM-DD): ");
        String studentNumber = readString("Student number: ");
        String programName = readString("Program name: ");
        LocalDate admissionDate = readDate("Admission date (YYYY-MM-DD): ");
        int studyYear = readInt("Study year: ");
        String guardianPhone = readString("Guardian phone: ");
        boolean scholarshipStatus = readBoolean("Scholarship status (true/false): ");
        String memberNumber = readString("Member number: ");
        String institutionalEmail = readString("Institutional email: ");
        String advisorName = readString("Advisor name: ");

        Student s = new Student(studentNumber, programName, admissionDate, studyYear, guardianPhone, scholarshipStatus,
                memberNumber, dept, LocalDate.now(), "ACTIVE", institutionalEmail, advisorName,
                personId, firstName, lastName, email, phone, dob);
        s.validate();
        students.add(s);
        System.out.println("✔ Student saved: " + s.getRegistrationSummary());
    }

    @Override
    public void registerTeacher() throws Exception {
        System.out.println("\n--- REGISTER TEACHER ---");
        if (departments.isEmpty()) {
            System.out.println("No department exists. Please create one first.");
            registerDepartment();
            if (departments.isEmpty()) return;
        }

        Department dept = selectDepartment();
        if (dept == null) return;

        String personId = readString("Person ID: ");
        String firstName = readString("First name: ");
        String lastName = readString("Last name: ");
        String email = readString("Email: ");
        String phone = readString("Phone number: ");
        LocalDate dob = readDate("Date of birth (YYYY-MM-DD): ");
        String employeeNumber = readString("Employee number: ");
        String specialization = readString("Specialization: ");
        LocalDate hireDate = readDate("Hire date (YYYY-MM-DD): ");
        BigDecimal salary = readBigDecimal("Salary: ");
        String officeNumber = readString("Office number: ");
        String employmentType = readString("Employment type: ");
        String memberNumber = readString("Member number: ");
        String institutionalEmail = readString("Institutional email: ");
        String advisorName = readString("Advisor name: ");

        Teacher t = new Teacher(employeeNumber, specialization, hireDate, salary, officeNumber, employmentType,
                memberNumber, dept, LocalDate.now(), "ACTIVE", institutionalEmail, advisorName,
                personId, firstName, lastName, email, phone, dob);
        t.validate();
        teachers.add(t);
        System.out.println("✔ Teacher saved: " + t.getRegistrationSummary());
    }

    @Override
    public void registerAdministrator() throws Exception {
        System.out.println("\n--- REGISTER ADMINISTRATOR ---");
        String personId = readString("Person ID: ");
        String firstName = readString("First name: ");
        String lastName = readString("Last name: ");
        String email = readString("Email: ");
        String phone = readString("Phone number: ");
        LocalDate dob = readDate("Date of birth (YYYY-MM-DD): ");
        String staffNumber = readString("Staff number: ");
        String jobTitle = readString("Job title: ");
        String office = readString("Assigned office: ");
        LocalDate employmentDate = readDate("Employment date (YYYY-MM-DD): ");
        int accessLevel = readInt("Access level (1-5): ");
        boolean active = readBoolean("Active (true/false): ");

        Administrator a = new Administrator(staffNumber, jobTitle, office, employmentDate, accessLevel, active,
                personId, firstName, lastName, email, phone, dob);
        a.validate();
        administrators.add(a);
        System.out.println("✔ Administrator saved: " + a.getRegistrationSummary());
    }

    @Override
    public void registerCourse() throws Exception {
        System.out.println("\n--- REGISTER COURSE ---");
        if (departments.isEmpty()) {
            System.out.println("No department exists. Please create one first.");
            registerDepartment();
            if (departments.isEmpty()) return;
        }
        if (teachers.isEmpty()) {
            System.out.println("No teacher exists. Please create one first.");
            registerTeacher();
            if (teachers.isEmpty()) return;
        }

        Department dept = selectDepartment();
        Teacher teacher = selectTeacher();
        if (dept == null || teacher == null) return;

        String courseId = readString("Course ID: ");
        String courseCode = readString("Course code: ");
        String title = readString("Course title: ");
        int credits = readInt("Credit hours: ");

        Course c = new Course(courseId, courseCode, title, credits, dept, teacher);
        c.validate();
        courses.add(c);
        System.out.println("✔ Course saved: " + c.getRegistrationSummary());
    }

    @Override
    public void registerClassroom() throws Exception {
        System.out.println("\n--- REGISTER CLASSROOM ---");
        String classroomId = readString("Classroom ID: ");
        String building = readString("Building name: ");
        String roomNumber = readString("Room number: ");
        int capacity = readInt("Capacity: ");
        int floor = readInt("Floor number: ");
        boolean available = readBoolean("Available (true/false): ");

        Classroom c = new Classroom(classroomId, building, roomNumber, capacity, floor, available);
        c.validate();
        classrooms.add(c);
        System.out.println("✔ Classroom saved: " + c.getRegistrationSummary());
    }

    @Override
    public void enrollStudent() throws Exception {
        System.out.println("\n--- ENROLL STUDENT ---");
        if (students.isEmpty()) {
            System.out.println("No student exists.");
            return;
        }
        if (courses.isEmpty()) {
            System.out.println("No course exists.");
            return;
        }

        Student student = selectStudent();
        Course course = selectCourse();
        if (student == null || course == null) return;

        String enrollmentId = readString("Enrollment ID: ");
        if (findEnrollment(enrollmentId) != null) throw new DuplicateRecordException("Enrollment ID exists.");
        String semester = readString("Semester (Sem1/Sem2/Sem3): ");
        LocalDate enrollmentDate = LocalDate.now();
        Enrollment e = new Enrollment(enrollmentId, student, course, enrollmentDate, semester, "ACTIVE");
        e.validate();
        enrollments.add(e);
        System.out.println("✔ Enrollment saved: " + e.getRegistrationSummary());
    }

    @Override
    public void recordAssessment() throws Exception {
        System.out.println("\n--- RECORD ASSESSMENT ---");
        if (enrollments.isEmpty()) {
            System.out.println("No active enrollment exists.");
            return;
        }

        Enrollment enrollment = selectEnrollment();
        if (enrollment == null) return;

        String assessmentId = readString("Assessment ID: ");
        String type = readString("Assessment type: ");
        double maxScore = readDouble("Maximum score: ");
        double obtained = readDouble("Obtained score: ");
        LocalDate date = readDate("Assessment date (YYYY-MM-DD): ");

        Assessment a = new Assessment(assessmentId, enrollment, type, maxScore, obtained, date);
        a.validate();
        assessments.add(a);
        System.out.println("✔ Assessment saved: " + a.getRegistrationSummary());
    }

    @Override
    public void recordFeePayment() throws Exception {
        System.out.println("\n--- RECORD FEE PAYMENT ---");
        if (students.isEmpty()) {
            System.out.println("No student exists.");
            return;
        }

        Student student = selectStudent();
        if (student == null) return;

        String paymentId = readString("Payment ID: ");
        BigDecimal amount = readBigDecimal("Amount: ");
        LocalDate paymentDate = readDate("Payment date (YYYY-MM-DD): ");
        String paymentMethod = readString("Payment method: ");
        String receiptNumber = readString("Receipt number: ");

        FeePayment p = new FeePayment(paymentId, student, amount, paymentDate, paymentMethod, receiptNumber);
        p.validate();
        feePayments.add(p);
        System.out.println("✔ Payment saved: " + p.getRegistrationSummary());
    }

    @Override
    public void registerLibraryBook() throws Exception {
        System.out.println("\n--- REGISTER LIBRARY BOOK ---");
        String bookId = readString("Book ID: ");
        String isbn = readString("ISBN: ");
        String title = readString("Title: ");
        String author = readString("Author: ");
        int totalCopies = readInt("Total copies: ");
        int availableCopies = readInt("Available copies: ");

        LibraryBook b = new LibraryBook(bookId, isbn, title, author, totalCopies, availableCopies);
        b.validate();
        libraryBooks.add(b);
        System.out.println("✔ Book saved: " + b.getRegistrationSummary());
    }

    @Override
    public void borrowBook() throws Exception {
        System.out.println("\n--- BORROW BOOK ---");
        if (students.isEmpty()) {
            System.out.println("No student exists.");
            return;
        }
        if (libraryBooks.isEmpty()) {
            System.out.println("No library book exists.");
            return;
        }

        Student student = selectStudent();
        LibraryBook book = selectLibraryBook();
        if (student == null || book == null) return;
        if (book.getAvailableCopies() <= 0) throw new ValidationException("No available copy of this book.");

        String loanId = readString("Loan ID: ");
        LocalDate borrowDate = LocalDate.now();
        LocalDate dueDate = borrowDate.plusDays(14);
        BookLoan loan = new BookLoan(loanId, student, book, borrowDate, dueDate, null);
        loan.validate();
        bookLoans.add(loan);
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        System.out.println("✔ Book borrowed: " + loan.getRegistrationSummary());
    }

    @Override
    public void returnBook() throws Exception {
        System.out.println("\n--- RETURN BOOK ---");
        if (bookLoans.isEmpty()) {
            System.out.println("No book loans exist.");
            return;
        }

        BookLoan loan = selectBookLoan();
        if (loan == null) return;
        if (loan.getReturnedDate() != null) {
            System.out.println("This book loan is already returned.");
            return;
        }

        loan.setReturnedDate(LocalDate.now());
        loan.getBook().setAvailableCopies(loan.getBook().getAvailableCopies() + 1);
        System.out.println("✔ Book returned: " + loan.getBook().getDisplayName());
    }

    @Override
    public void viewAllRecords() {
        System.out.println("\n--- ALL RECORDS ---");
        printSection("Departments", departments);
        printSection("Students", students);
        printSection("Teachers", teachers);
        printSection("Administrators", administrators);
        printSection("Courses", courses);
        printSection("Classrooms", classrooms);
        printSection("Enrollments", enrollments);
        printSection("Assessments", assessments);
        printSection("Fee Payments", feePayments);
        printSection("Library Books", libraryBooks);
        printSection("Book Loans", bookLoans);
    }

    @Override
    public void searchById() {
        System.out.println("\n--- SEARCH BY ID ---");
        String id = readString("Enter ID: ");
        boolean found = false;

        found = printIfMatches("Department", departments, id) || found;
        found = printIfMatches("Student", students, id) || found;
        found = printIfMatches("Teacher", teachers, id) || found;
        found = printIfMatches("Administrator", administrators, id) || found;
        found = printIfMatches("Course", courses, id) || found;
        found = printIfMatches("Classroom", classrooms, id) || found;
        found = printIfMatches("Enrollment", enrollments, id) || found;
        found = printIfMatches("Assessment", assessments, id) || found;
        found = printIfMatches("Payment", feePayments, id) || found;
        found = printIfMatches("Book", libraryBooks, id) || found;
        found = printIfMatches("Loan", bookLoans, id) || found;

        if (!found) System.out.println("No matching record found for ID: " + id);
    }

    @Override
    public void updateRecord() throws Exception {
        System.out.println("\n--- UPDATE RECORD ---");
        System.out.println("1. Student");
        System.out.println("2. Teacher");
        System.out.println("3. Administrator");
        System.out.println("4. Course");
        System.out.println("5. Classroom");
        System.out.println("6. Library Book");
        int choice = readInt("Select record type: ");

        switch (choice) {
            case 1 -> updateStudent();
            case 2 -> updateTeacher();
            case 3 -> updateAdministrator();
            case 4 -> updateCourse();
            case 5 -> updateClassroom();
            case 6 -> updateLibraryBook();
            default -> System.out.println("Unknown option.");
        }
    }

    @Override
    public void showStudentAcademicSummary() {
        System.out.println("\n--- STUDENT ACADEMIC SUMMARY ---");
        if (students.isEmpty()) {
            System.out.println("No students exist.");
            return;
        }

        Student student = selectStudent();
        if (student == null) return;

        List<Enrollment> studentEnrollments = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudent().getId().equalsIgnoreCase(student.getId())) {
                studentEnrollments.add(enrollment);
            }
        }

        System.out.println("Student: " + student.getDisplayName());
        System.out.println("Program: " + student.getProgramName());
        System.out.println("Year: " + student.getStudyYear());
        System.out.println("Enrollments: " + studentEnrollments.size());

        if (studentEnrollments.isEmpty()) {
            System.out.println("No enrollment history.");
            return;
        }

        double total = 0;
        int count = 0;
        for (Enrollment enrollment : studentEnrollments) {
            for (Assessment assessment : assessments) {
                if (assessment.getEnrollment().getId().equalsIgnoreCase(enrollment.getId())) {
                    total += assessment.getObtainedScore();
                    count++;
                }
            }
        }

        if (count == 0) {
            System.out.println("No assessment history.");
        } else {
            System.out.println("Average score: " + (total / count));
        }
    }

    @Override
    public void showPaymentHistory() {
        System.out.println("\n--- PAYMENT HISTORY ---");
        if (students.isEmpty()) {
            System.out.println("No students exist.");
            return;
        }

        Student student = selectStudent();
        if (student == null) return;

        boolean found = false;
        for (FeePayment payment : feePayments) {
            if (payment.getStudent().getId().equalsIgnoreCase(student.getId())) {
                System.out.println(payment.getRegistrationSummary());
                found = true;
            }
        }
        if (!found) System.out.println("No payment history found for this student.");
    }

    @Override
    public void showAvailableBooks() {
        System.out.println("\n--- AVAILABLE BOOKS ---");
        boolean found = false;
        for (LibraryBook book : libraryBooks) {
            if (book.getAvailableCopies() > 0) {
                System.out.println(book.getRegistrationSummary());
                found = true;
            }
        }
        if (!found) System.out.println("No available books at the moment.");
    }

    @Override
    public void demonstratePolymorphism() {
        System.out.println("\n--- DEMONSTRATE POLYMORPHISM ---");
        List<Object> objects = new ArrayList<>();
        if (!departments.isEmpty()) objects.add(departments.get(0));
        if (!students.isEmpty()) objects.add(students.get(0));
        if (!teachers.isEmpty()) objects.add(teachers.get(0));
        if (!courses.isEmpty()) objects.add(courses.get(0));
        if (objects.isEmpty()) {
            System.out.println("No data has been created yet. Create some records first.");
            return;
        }

        for (Object obj : objects) {
            if (obj instanceof Student) {
                Student s = (Student) obj;
                System.out.println("Student => " + s.getRoleDescription() + " | " + s.getRegistrationSummary());
            } else if (obj instanceof Teacher) {
                Teacher t = (Teacher) obj;
                System.out.println("Teacher => " + t.getRoleDescription() + " | " + t.getRegistrationSummary());
            } else if (obj instanceof Department) {
                Department d = (Department) obj;
                System.out.println("Department => " + d.getDisplayName() + " | " + d.getRegistrationSummary());
            } else if (obj instanceof Course) {
                Course c = (Course) obj;
                System.out.println("Course => " + c.getDisplayName() + " | " + c.getRegistrationSummary());
            }
        }
    }

    public Department findDepartment(String id) {
        for (Department d : departments) if (d.getId().equalsIgnoreCase(id)) return d;
        return null;
    }

    public Student findStudent(String id) {
        for (Student s : students) if (s.getId().equalsIgnoreCase(id)) return s;
        return null;
    }

    public Teacher findTeacher(String id) {
        for (Teacher t : teachers) if (t.getId().equalsIgnoreCase(id)) return t;
        return null;
    }

    public Administrator findAdministrator(String id) {
        for (Administrator a : administrators) if (a.getId().equalsIgnoreCase(id)) return a;
        return null;
    }

    public Course findCourse(String id) {
        for (Course c : courses) if (c.getId().equalsIgnoreCase(id)) return c;
        return null;
    }

    public Classroom findClassroom(String id) {
        for (Classroom c : classrooms) if (c.getId().equalsIgnoreCase(id)) return c;
        return null;
    }

    public Enrollment findEnrollment(String id) {
        for (Enrollment e : enrollments) if (e.getId().equalsIgnoreCase(id)) return e;
        return null;
    }

    public LibraryBook findLibraryBook(String id) {
        for (LibraryBook b : libraryBooks) if (b.getId().equalsIgnoreCase(id)) return b;
        return null;
    }

    public BookLoan findBookLoan(String id) {
        for (BookLoan b : bookLoans) if (b.getId().equalsIgnoreCase(id)) return b;
        return null;
    }

    private Department selectDepartment() {
        if (departments.isEmpty()) {
            System.out.println("No department available.");
            return null;
        }
        System.out.println("Select Department:");
        for (int i = 0; i < departments.size(); i++) System.out.println((i + 1) + ". " + departments.get(i).getDisplayName());
        int c = readInt("Select: ");
        if (c < 1 || c > departments.size()) return null;
        return departments.get(c - 1);
    }

    private Student selectStudent() {
        if (students.isEmpty()) {
            System.out.println("No student available.");
            return null;
        }
        System.out.println("Select Student:");
        for (int i = 0; i < students.size(); i++) System.out.println((i + 1) + ". " + students.get(i).getDisplayName());
        int c = readInt("Select: ");
        if (c < 1 || c > students.size()) return null;
        return students.get(c - 1);
    }

    private Teacher selectTeacher() {
        if (teachers.isEmpty()) {
            System.out.println("No teacher available.");
            return null;
        }
        System.out.println("Select Teacher:");
        for (int i = 0; i < teachers.size(); i++) System.out.println((i + 1) + ". " + teachers.get(i).getDisplayName());
        int c = readInt("Select: ");
        if (c < 1 || c > teachers.size()) return null;
        return teachers.get(c - 1);
    }

    private Course selectCourse() {
        if (courses.isEmpty()) {
            System.out.println("No course available.");
            return null;
        }
        System.out.println("Select Course:");
        for (int i = 0; i < courses.size(); i++) System.out.println((i + 1) + ". " + courses.get(i).getDisplayName());
        int c = readInt("Select: ");
        if (c < 1 || c > courses.size()) return null;
        return courses.get(c - 1);
    }

    private Enrollment selectEnrollment() {
        if (enrollments.isEmpty()) {
            System.out.println("No enrollment available.");
            return null;
        }
        System.out.println("Select Enrollment:");
        for (int i = 0; i < enrollments.size(); i++) System.out.println((i + 1) + ". " + enrollments.get(i).getDisplayName());
        int c = readInt("Select: ");
        if (c < 1 || c > enrollments.size()) return null;
        return enrollments.get(c - 1);
    }

    private LibraryBook selectLibraryBook() {
        if (libraryBooks.isEmpty()) {
            System.out.println("No library book available.");
            return null;
        }
        System.out.println("Select Book:");
        for (int i = 0; i < libraryBooks.size(); i++) System.out.println((i + 1) + ". " + libraryBooks.get(i).getDisplayName());
        int c = readInt("Select: ");
        if (c < 1 || c > libraryBooks.size()) return null;
        return libraryBooks.get(c - 1);
    }

    private BookLoan selectBookLoan() {
        if (bookLoans.isEmpty()) {
            System.out.println("No book loan available.");
            return null;
        }
        System.out.println("Select Book Loan:");
        for (int i = 0; i < bookLoans.size(); i++) System.out.println((i + 1) + ". " + bookLoans.get(i).getDisplayName());
        int c = readInt("Select: ");
        if (c < 1 || c > bookLoans.size()) return null;
        return bookLoans.get(c - 1);
    }

    private void updateStudent() throws Exception {
        if (students.isEmpty()) {
            System.out.println("No students exist.");
            return;
        }
        Student student = selectStudent();
        if (student == null) return;
        System.out.println("Current: " + student.getRegistrationSummary());
        String firstName = readString("New first name (blank to keep): ");
        if (!firstName.isBlank()) student.setFirstName(firstName);
        String lastName = readString("New last name (blank to keep): ");
        if (!lastName.isBlank()) student.setLastName(lastName);
        String email = readString("New email (blank to keep): ");
        if (!email.isBlank()) student.setEmail(email);
        String phone = readString("New phone (blank to keep): ");
        if (!phone.isBlank()) student.setPhoneNumber(phone);
        String program = readString("New program name (blank to keep): ");
        if (!program.isBlank()) student.setProgramName(program);
        int year = readInt("New study year (0 to keep): ");
        if (year != 0) student.setStudyYear(year);
        student.validate();
        System.out.println("✔ Student updated.");
    }

    private void updateTeacher() throws Exception {
        if (teachers.isEmpty()) {
            System.out.println("No teachers exist.");
            return;
        }
        Teacher teacher = selectTeacher();
        if (teacher == null) return;
        System.out.println("Current: " + teacher.getRegistrationSummary());
        String specialization = readString("New specialization (blank to keep): ");
        if (!specialization.isBlank()) teacher.setSpecialization(specialization);
        BigDecimal salary = readBigDecimal("New salary (0 to keep): ");
        if (salary.compareTo(BigDecimal.ZERO) > 0) teacher.setSalary(salary);
        teacher.validate();
        System.out.println("✔ Teacher updated.");
    }

    private void updateAdministrator() throws Exception {
        if (administrators.isEmpty()) {
            System.out.println("No administrators exist.");
            return;
        }
        Administrator admin = administrators.get(selectIndex(administrators.size()));
        if (admin == null) return;
        System.out.println("Current: " + admin.getRegistrationSummary());
        String jobTitle = readString("New job title (blank to keep): ");
        if (!jobTitle.isBlank()) admin.setJobTitle(jobTitle);
        int accessLevel = readInt("New access level (0 to keep): ");
        if (accessLevel != 0) admin.setAccessLevel(accessLevel);
        admin.validate();
        System.out.println("✔ Administrator updated.");
    }

    private void updateCourse() throws Exception {
        if (courses.isEmpty()) {
            System.out.println("No courses exist.");
            return;
        }
        Course course = selectCourse();
        if (course == null) return;
        System.out.println("Current: " + course.getRegistrationSummary());
        String title = readString("New title (blank to keep): ");
        if (!title.isBlank()) {
            String courseId = course.getId();
            String courseCode = course.getCourseCode();
            int creditHours = course.getCreditHours();
            Department dept = course.getDepartment();
            Teacher assignedTeacher = course.getAssignedTeacher();
            course = new Course(courseId, courseCode, title, creditHours, dept, assignedTeacher);
            for (int i = 0; i < courses.size(); i++) {
                if (courses.get(i).getId().equalsIgnoreCase(courseId)) {
                    courses.set(i, course);
                    break;
                }
            }
        }
        int credits = readInt("New credit hours (0 to keep): ");
        if (credits != 0) {
            String courseId = course.getId();
            String courseCode = course.getCourseCode();
            String existingTitle = course.getCourseTitle();
            Department dept = course.getDepartment();
            Teacher assignedTeacher = course.getAssignedTeacher();
            course = new Course(courseId, courseCode, existingTitle, credits, dept, assignedTeacher);
            for (int i = 0; i < courses.size(); i++) {
                if (courses.get(i).getId().equalsIgnoreCase(courseId)) {
                    courses.set(i, course);
                    break;
                }
            }
        }
        System.out.println("✔ Course updated.");
    }

    private void updateClassroom() throws Exception {
        if (classrooms.isEmpty()) {
            System.out.println("No classrooms exist.");
            return;
        }
        System.out.println("Select Classroom:");
        for (int i = 0; i < classrooms.size(); i++) System.out.println((i + 1) + ". " + classrooms.get(i).getDisplayName());
        int index = readInt("Select: ");
        if (index < 1 || index > classrooms.size()) return;
        Classroom classroom = classrooms.get(index - 1);
        String building = readString("New building (blank to keep): ");
        if (!building.isBlank()) classroom.setBuildingName(building);
        String room = readString("New room number (blank to keep): ");
        if (!room.isBlank()) classroom.setRoomNumber(room);
        int capacity = readInt("New capacity (0 to keep): ");
        if (capacity != 0) classroom.setCapacity(capacity);
        System.out.println("✔ Classroom updated.");
    }

    private void updateLibraryBook() throws Exception {
        if (libraryBooks.isEmpty()) {
            System.out.println("No library books exist.");
            return;
        }
        LibraryBook book = selectLibraryBook();
        if (book == null) return;
        String title = readString("New title (blank to keep): ");
        if (!title.isBlank()) book.setTitle(title);
        String author = readString("New author (blank to keep): ");
        if (!author.isBlank()) book.setAuthor(author);
        int copies = readInt("New available copies (0 to keep): ");
        if (copies != 0) book.setAvailableCopies(copies);
        book.validate();
        System.out.println("✔ Library book updated.");
    }

    private int selectIndex(int size) {
        int index = readInt("Select: ");
        if (index < 1 || index > size) return -1;
        return index - 1;
    }

    private boolean printIfMatches(String label, List<?> records, String id) {
        for (Object item : records) {
            if (item instanceof Department d && d.getId().equalsIgnoreCase(id)) {
                System.out.println(label + ": " + d.getRegistrationSummary());
                return true;
            }
            if (item instanceof Student s && s.getId().equalsIgnoreCase(id)) {
                System.out.println(label + ": " + s.getRegistrationSummary());
                return true;
            }
            if (item instanceof Teacher t && t.getId().equalsIgnoreCase(id)) {
                System.out.println(label + ": " + t.getRegistrationSummary());
                return true;
            }
            if (item instanceof Administrator a && a.getId().equalsIgnoreCase(id)) {
                System.out.println(label + ": " + a.getRegistrationSummary());
                return true;
            }
            if (item instanceof Course c && c.getId().equalsIgnoreCase(id)) {
                System.out.println(label + ": " + c.getRegistrationSummary());
                return true;
            }
            if (item instanceof Classroom c && c.getId().equalsIgnoreCase(id)) {
                System.out.println(label + ": " + c.getRegistrationSummary());
                return true;
            }
            if (item instanceof Enrollment e && e.getId().equalsIgnoreCase(id)) {
                System.out.println(label + ": " + e.getRegistrationSummary());
                return true;
            }
            if (item instanceof Assessment a && a.getId().equalsIgnoreCase(id)) {
                System.out.println(label + ": " + a.getRegistrationSummary());
                return true;
            }
            if (item instanceof FeePayment p && p.getId().equalsIgnoreCase(id)) {
                System.out.println(label + ": " + p.getRegistrationSummary());
                return true;
            }
            if (item instanceof LibraryBook b && b.getId().equalsIgnoreCase(id)) {
                System.out.println(label + ": " + b.getRegistrationSummary());
                return true;
            }
            if (item instanceof BookLoan l && l.getId().equalsIgnoreCase(id)) {
                System.out.println(label + ": " + l.getRegistrationSummary());
                return true;
            }
        }
        return false;
    }

    private void printSection(String name, List<?> records) {
        System.out.println("\n[" + name + "]");
        if (records.isEmpty()) {
            System.out.println("No records.");
            return;
        }
        for (Object record : records) {
            if (record instanceof Student s) System.out.println(s.getRegistrationSummary());
            else if (record instanceof Teacher t) System.out.println(t.getRegistrationSummary());
            else if (record instanceof Administrator a) System.out.println(a.getRegistrationSummary());
            else if (record instanceof Department d) System.out.println(d.getRegistrationSummary());
            else if (record instanceof Course c) System.out.println(c.getRegistrationSummary());
            else if (record instanceof Classroom c) System.out.println(c.getRegistrationSummary());
            else if (record instanceof Enrollment e) System.out.println(e.getRegistrationSummary());
            else if (record instanceof Assessment a) System.out.println(a.getRegistrationSummary());
            else if (record instanceof FeePayment p) System.out.println(p.getRegistrationSummary());
            else if (record instanceof LibraryBook b) System.out.println(b.getRegistrationSummary());
            else if (record instanceof BookLoan l) System.out.println(l.getRegistrationSummary());
        }
    }

    public String readString(String prompt) {
        System.out.print(prompt);
        return SC.nextLine().trim();
    }

    public int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readString(prompt));
            } catch (Exception e) {
                System.out.println("! Enter a valid number.");
            }
        }
    }

    public double readDouble(String prompt) {
        while (true) {
            try {
                return Double.parseDouble(readString(prompt));
            } catch (Exception e) {
                System.out.println("! Enter a valid decimal number.");
            }
        }
    }

    public LocalDate readDate(String prompt) {
        while (true) {
            try {
                return LocalDate.parse(readString(prompt));
            } catch (DateTimeParseException e) {
                System.out.println("! Invalid date. Enter a real calendar date in YYYY-MM-DD format, e.g. 2000-02-22.");
            }
        }
    }

    public BigDecimal readBigDecimal(String prompt) {
        while (true) {
            try {
                return new BigDecimal(readString(prompt));
            } catch (Exception e) {
                System.out.println("! Enter a valid money amount.");
            }
        }
    }

    public boolean readBoolean(String prompt) {
        while (true) {
            String value = readString(prompt).toLowerCase();
            if (value.equals("true")) return true;
            if (value.equals("false")) return false;
            System.out.println("! Enter true or false.");
        }
    }
}