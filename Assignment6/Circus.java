/*
 * Class: CMSC203 CRN 32324
 * Instructor: Grigoriy Grinberg
 * Description:
 * This is a program to make the user interface to setup Bob’s Circus for users to buy tickets, 
 * view the this.animals within the circus and the this.buildings within the circus.
 * Due: 05/04/2026
 * Platform/compiler: Eclipse / javac
 * Integrity Pledge:
 * I pledge that I have completed the programming assignment independently. 
 * I have not copied the code from a student or any source. 
 * I have not given my code to any student.
 * Name: Marcus Kemel Collins
 */
import java.util.ArrayList;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Circus {
    private List<Animal> animals;
    private List<Person> persons;
    private List<Building> buildings;

    public Circus() {
        this.animals = new ArrayList<>();
        this.persons = new ArrayList<>();
        this.buildings = new ArrayList<>();
    }
    
    // Getters
    public List<Animal> getAnimals() {
    	return this.animals;
    }
    
    public List<Person> getPersons() {
    	return this.persons;
    }
    
    public List<Building> getBuildings() {
    	return this.buildings;
    }


    // Add building
    public void addBuilding(Building b) throws CustomInputMismatchException {
    	if( b == null ) {
    		throw new CustomInputMismatchException("addBuilding Error: attempt to add a Null building");
    	}
    	
    	if( this.buildings != null ) {
    		this.buildings.add(b);
    	}
    	
    	return;
    }

    // Display all this.buildings
    public String displayAllBuildings() {
    	String output = "";
    	
    	if( this.buildings == null ) {
    		return output;
    	}
    	
    	for(Building b : this.buildings) {
    		output += b.toString();
    	}
    	
    	output += "\n";
    	
    	return output;
    }

    // Add person
    public void addPerson(Person p) throws CustomInputMismatchException {
    	if( p == null ) {
    		throw new CustomInputMismatchException("addPerson Error: attempt to add a Null person");
    	}
    	
    	if( this.persons != null ) {
    		this.persons.add(p);
    	}
    	
    	return;
    }

    // Display all this.persons
    public String displayAllPersons() {
    	String output = "";
    	
    	if( this.persons == null ) {
    		return output;
    	}
    	
    	for(Person p : this.persons) {
    		output += p.toString() + "\n";
    	}
    	
    	return output;
    }
    

    // Add animal
    public void addAnimal(Animal a) throws CustomInputMismatchException {
    	if( a == null ) {
    		throw new CustomInputMismatchException("addAnimal Error: attempt to add a Null animal");
    	}
    	
    	if( this.animals != null ) {
    		this.animals.add(a);
    	}
    	
    	return;
    }

    // Display all this.animals
    public String displayAllAnimals() {
    	String output = "";
    	
    	if( this.animals == null ) {
    		return output;
    	}
    	
    	ByteArrayOutputStream outputStream;
    	PrintStream printStream;
    	PrintStream originalStream;
    	for(Animal a : this.animals) {
    		output += a.toString() + "\n";
    		
    		outputStream = new ByteArrayOutputStream();
    		printStream = new PrintStream(outputStream);
    		originalStream = System.out;
    		System.setOut(printStream);
    		a.makeSound();  // capture console output to printStream
    		a.move();
    		System.out.flush();  // force console output to print remaining bytes within stream buffer
    		System.setOut(originalStream);  // reset to console output stream
    		output += outputStream.toString();
    	}
    	
    	return output;
    }

    // Selection sort to sort this.animals by age
    public void sortAnimalsByAge() {
    	if( this.animals == null ) {
    		return;
    	}
    	
    	// use basic Bubble Sort algorithm to sort by age
    	boolean swapped;
    	Animal lower_animal;
    	Animal higher_animal;  // both to be used for swapping
    	for(int n = 0; n < this.animals.size() - 1; ++n) {
    		swapped = false;
    		for(int m = 0; m < this.animals.size() - n - 1; ++m) {
    			if( this.animals.get(m).getAge() > this.animals.get(m+1).getAge() ) {
    				higher_animal = this.animals.get(m);
    				lower_animal = this.animals.get(m+1);
    				this.animals.set(m, lower_animal);
    				this.animals.set(m+1, higher_animal);
    				swapped = true;
    			}
    		}
    		
    		if( !swapped ) {
    			break;
    		}
    	}
    	return;
    }
    
    // Selection sort to sort this.animals by name
    public void sortAnimalsByName() {
    	if( this.animals == null ) {
    		return;
    	}
    	
    	// use basic Bubble Sort algorithm to sort by name
    	boolean swapped;
    	Animal lower_animal;
    	Animal higher_animal;  // both to be used for swapping
    	for(int n = 0; n < this.animals.size() - 1; ++n) {
    		swapped = false;
    		for(int m = 0; m < this.animals.size() - n - 1; ++m) {
    			if( this.animals.get(m).getName().compareTo(this.animals.get(m+1).getName()) > 0 ) {
    				higher_animal = this.animals.get(m);
    				lower_animal = this.animals.get(m+1);
    				this.animals.set(m, lower_animal);
    				this.animals.set(m+1, higher_animal);
    				swapped = true;
    			}
    		}
    		
    		if( !swapped ) {
    			break;
    		}
    	}
    	return;
    }

    // Search for an animal by name
    public Animal searchAnimalsByName(String name) throws CustomInputMismatchException {
    	Animal found_animal = null;
    	
    	if( name.isEmpty() ) {
    		throw new CustomInputMismatchException("Error: Animal name to search for is empty");
    	}
    	
    	for(Animal a : this.animals) {
    		if( a.getName().equals(name) ) {
    			String type_animal;
    			String[] split_animal_string = a.toString().split("\\[");
    			
    			if( split_animal_string.length > 0 ) {
    				type_animal = split_animal_string[0].trim();
    				
    				switch(type_animal) {
    					case "Bird":
    						found_animal = new Bird(a.getName(), a.getAge(), a.getSpecies(), a.getColor(), a.getImagePath());
    						break;
    					case "Dog":
    						found_animal = new Dog(a.getName(), a.getAge(), a.getSpecies(), a.getColor(), a.getImagePath());
    						break;
    					case "Horse":
    						found_animal = new Horse(a.getName(), a.getAge(), a.getSpecies(), a.getColor(), a.getImagePath());
    						break;
    					case "Lion":
    						found_animal = new Lion(a.getName(), a.getAge(), a.getSpecies(), a.getColor(), a.getImagePath());
    						break;
    					default:
    						break;
    				}
    			}
    			break;
    		}
    	}
    	
    	return found_animal;
    }

}