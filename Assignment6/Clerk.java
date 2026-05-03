/*
 * Class: CMSC203 CRN 32324
 * Instructor: Grigoriy Grinberg
 * Description:
 * This is a program to make the user interface to setup Bob’s Circus for users to buy tickets, 
 * view the animals within the circus and the buildings within the circus.
 * Due: 05/04/2026
 * Platform/compiler: Eclipse / javac
 * Integrity Pledge:
 * I pledge that I have completed the programming assignment independently. 
 * I have not copied the code from a student or any source. 
 * I have not given my code to any student.
 * Name: Marcus Kemel Collins
 */
public class Clerk extends Person {

    public Clerk(String name, int age, int yearsWorked, String job, String imagePath) {
        super(name, age, yearsWorked, job, imagePath);
    }

    @Override
    public String toString() {
        return "Clerk - " + super.toString();
    }
}
