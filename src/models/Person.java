package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Person extends College {
    protected String name;
    protected String birthDate;
    ArrayList<Student> students = new ArrayList<>();
    ArrayList<Faculty> faculty = new ArrayList<>();
    ArrayList<Staff> staff = new ArrayList<>();

    //getters
    public String getName() {
        return name;
    }
    public String getBirthDate() {
        return birthDate;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void run(){
        collegeName();
        menu();
    }

    public void students(){
        Student s = new Student();
        s.setName(promptForString("\nWhat is the student's name?\n", false));
        s.setBirthDate(promptForString("\nWhat is " + s.getName() + "'s birthdate?\n", false));
        s.setGPA(promptForLong("\nWhat is " + s.getName() + "'s GPA? (ex. 2.87, 3.50, 4.00)\n", 0.00, 4.00));
//        s.setAdvocate(promptForString("\nWho is the student's advocate?", true));
//        System.out.println("\nGreat job so far! This next part is optional so if you don't want to do it, simply hit enter on the question.");
//        s.studentClasses(promptForString("What is their first class?", true));
        students.add(new Student(s.getName(), s.getBirthDate(), s.getGPA(), s.getAdvocate(), s.studentClasses));
        menu();
    }

    public void faculty(){
        Faculty f = new Faculty();
        f.setName(promptForString("\nWhat is the faculty member's name?\n", false));
        f.setBirthDate(promptForString("\nWhat is " + f.getName() + "'s birthdate?\n", false));
        f.setOfficeHours(promptForString("\nWhat are their office hours (ex. Mon-Thurs, 4-6pm, M-F, 11a-1pm\n", false));
        f.setOfficeNumber(promptForInt("\nWhat is their office number (like their actual office number, not their phone number)?\n", 0, 500));
//        f.setFulltime(promptForBoolean("\nIs " + f.getName() + " a full-time employee?", false));
        faculty.add(new Faculty(f.getName(), f.getBirthDate(), f.getOfficeHours(), f.getOfficeNumber(), f.facultyCourses, f.isFulltime()));
        menu();
    }

    public void staff(){
        Staff t = new Staff();
        t.setName(promptForString("\nWhat is the staff member's name?\n", false));
        t.setBirthDate(promptForString("\nWhat is " + t.getName() + "'s birthdate?\n", false));
        t.setJobTitle(promptForString("\nWhat is their job title? (ex. Financial Aid, Maintenance, etc)\n", false));
//        t.setAdvisingStudents(promptForString("\nWhat students are they advising (if any)?\n", true));
        staff.add(new Staff(t.getName(), t.getBirthDate(), t.getJobTitle(), t.getAdvisingStudents()));
        menu();
    }

    public void menu() {
        //Prompt the user to print names, add a new one, or exit the application
        System.out.println("\nWhat would you like to do now?");
        String userSelection = null;
        boolean isInvalid = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.println("1) Add a student \n2) Add a faculty member \n3) Add a staff member \n4) View the current number of students, faculty, and staff \n5) Print all students, faculty, and staff members to the console \n6) Remove a student, faculty member, or staff member \n7) Exit the application");
            try {
                userSelection = br.readLine();
                isInvalid = userSelection == null || userSelection.isBlank();

                if (isInvalid) {
                    System.out.println("Oof. You really screwed that one up. Let's try that again.");
                }
            } catch (IOException ioe) {
                System.out.println("Wow. Not sure how you managed to get here, but ya did. Go ahead and try something else, or maybe that same thing again. ;)");
            }
        } while (isInvalid);


        switch (userSelection) {
            case "1":
                students();
                break;
            case "2":
                faculty();
                break;
            case "3":
                staff();
                break;
            case "4":
                System.out.println("\nThere are currently " + students.size() + " students, " + faculty.size() + " faculty members, and " + staff.size() + " staff members in the database.");
                menu();
                break;
            case "5":
                System.out.println("STUDENTS:");
                System.out.println(students);
                System.out.println();
                System.out.println("FACULTY:");
                System.out.println(faculty);
                System.out.println();
                System.out.println("STAFF:");
                System.out.println(staff);
                menu();
                break;
            case "6":
                secondaryMenu();
                break;
            case "7":
                System.out.println("Goodbye.");
                break;
        }
    }

    public void secondaryMenu() {
        String userSelection = null;
        boolean isInvalid = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.println("\nLet's purge some people. Who are we choosing first?\n1) Remove a student \n2) Remove a faculty member \n3) Remove a staff member \n4) Go back");
            try {
                userSelection = br.readLine();
                isInvalid = userSelection == null || userSelection.isBlank();

                if (isInvalid) {
                    System.out.println("\nOof. You really screwed that one up. Let's try that again.");
                }
            } catch (IOException ioe) {
                System.out.println("\nWow. Not sure how you managed to get here, but ya did. Go ahead and try something else, or maybe that same thing again. ;)");
            }
        } while (isInvalid);

        switch (userSelection) {
            case "1":
//                        students.remove();
                System.out.println("remove students");
                secondaryMenu();
                break;
            case "2":
//                        faculty.remove();
                System.out.println("remove faculty");
                secondaryMenu();
                break;
            case "3":
//                        staff.remove();
                System.out.println("remove staff");
                secondaryMenu();
                break;
            case "4":
                menu();
                break;
        }
    }

    protected static String promptForString(String prompt, boolean allowBlank) {
        if (prompt == null || prompt.isBlank()) {
            throw new IllegalArgumentException("The prompt cannot be null, empty, or just white space. prompt= " + prompt);
        }
        String input = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean inputIsInvalid = true;

        do {
            System.out.print(prompt);

            try {
                input = br.readLine();
                inputIsInvalid = input == null || (!allowBlank && input.isBlank());

                if (inputIsInvalid) {
                    System.out.println("Your input is invalid. Please try again.");
                }
            } catch (IOException ioe) {
                System.out.println("There was a problem and your input was not received. Please try again.");
            }
        } while (inputIsInvalid);

        return input;
    }

    protected static int promptForInt(String prompt, int min, int max) {
        if(min > max){
            throw new IllegalArgumentException("The mix cannot exceed the max. min=" + min + "; max=" + max);
        }

        int userNum = -1;
        boolean numIsInvalid = true;

        do{
            try{
                userNum = Integer.parseInt(promptForString(prompt, false));
                numIsInvalid = userNum < min || userNum > max;
            } catch (NumberFormatException nfe){

            }

            if(numIsInvalid){
                System.out.println("You must enter a whole number between " + min + " and " + max + ". Please, try again.");
            }
        } while(numIsInvalid);

        return userNum;
    }

    protected static double promptForLong(String prompt, double min, double max) {
        if(min > max){
            throw new IllegalArgumentException("The mix cannot exceed the max. min=" + min + "; max=" + max);
        }

        double userNum = -1;
        boolean numIsInvalid = true;

        do{
            try{
                userNum = Double.parseDouble(promptForString(prompt, false));
                numIsInvalid = userNum < min || userNum > max;
            } catch (NumberFormatException nfe){

            }

            if(numIsInvalid){
                System.out.println("You must enter a number between " + min + " and " + max + ". Please, try again.");
            }
        } while(numIsInvalid);

        return userNum;
    }

    public static void runFAKE() {
        Student s = new Student();
        Faculty f = new Faculty();
        Staff t = new Staff();

        //values that are set after the object is created so they can be cross-referenced before being stored.
        s.setAdvocate(t);
        t.setAdvisingStudents(s.name);

        //writing to database
        s.students.add(new Student(s.getName(), s.getBirthDate(), s.getGPA(), s.getAdvocate(), s.studentClasses));
        f.faculty.add(new Faculty(f.getName(), f.getBirthDate(), f.getOfficeHours(), f.getOfficeNumber(), f.facultyCourses, f.isFulltime()));
        t.staff.add(new Staff(t.getName(), t.getBirthDate(), t.getJobTitle(), t.getAdvisingStudents()));

        //printing the values in the database
        System.out.println(s.students.toString());
        System.out.println();
        System.out.println(f.faculty.toString());
        System.out.println();
        System.out.println(t.staff.toString());
        System.out.println();
    }

    @Override
    public String toString() {
        return toString();
    }
}

//TODO: KNOWN ERRORS
//The ability to remove a specific person from the ArrayList. Not sure how to reference a certain entry based on that.
