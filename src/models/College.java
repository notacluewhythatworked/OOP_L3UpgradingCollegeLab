package models;

public class College {

    public void collegeName(){
        String collegeName = Person.promptForString("Welcome to your new college! Let's get started by naming your college! \nWhat is your college's name?\n", false);
        System.out.println("Great! We've created " + collegeName + "!");
    }
}
