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
public class Arena implements Building {
	//instance variables
    private String color;
    private double length;
    private double width;
    private String buildingType;
    private String imagePath;

    //constructor
    public Arena(String color, double length, double width, String imagePath) {
        this.color = color;
        this.length = length;
        this.width = width;
        this.buildingType = "Arena";
        this.imagePath = imagePath;
    }

    @Override
    public void setSize(double length, double width) {
    	this.length = length;
    	this.width = width;
    }

    @Override
	public double getLength() {
    	return this.length;
    }

    @Override
	public double getWidth() {
    	return this.width;
    }

    @Override
	public void setColor(String color_value) {
    	this.color = new String(color_value);
    }

    @Override
	public String getColor() {
    	return new String(this.color);
    }

    @Override
	public void setBuildingType(String bldg_type) {
    	this.buildingType = new String(bldg_type);
    }

    @Override
	public String getBuildingType() {
    	return new String(this.buildingType);
    }

    @Override
    public String getImagePath() { 
    	return new String(this.imagePath); 
    }
    
    @Override
    public String toString() {
        return String.format("\nBuilding Type: %s \nColor: %s \nSize: %.1f x %.1f\n", 
        		this.buildingType, this.color, this.length, this.width);      
    }   
}

