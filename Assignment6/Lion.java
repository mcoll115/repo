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
public class Lion implements Animal {
    private String name;
    private int age;
    private String species;
    private String color;
    private String imagePath;

    public Lion(String name, int age, String species, String color, String imagePath) {
        this.name = name;
        this.age = age;
        this.species = species;
        this.color = color;
        this.imagePath = imagePath;
    }

    @Override
    public void makeSound() {
    	System.out.println("Roar!!");
    }

    @Override
    public void move() {
    	System.out.println("Stalks silently.\n");
    }

    @Override
    public String getName() {
    	return this.name;
    }

    @Override
    public int getAge() {
    	return this.age;
    }

    @Override
    public String getSpecies() {
    	return this.species;
    }

    @Override
    public String getColor() {
    	return this.color;
    }

    @Override
    public String getImagePath() {
    	return this.imagePath;
    }

    @Override
    public String toString() {
    	return String.format("Lion [Name: %s, Age: %d, Species: %s, Color: %s]", 
    			this.name, this.age, this.species, this.color);
    }
}

