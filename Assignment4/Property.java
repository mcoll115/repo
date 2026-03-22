/**
 * Course: CMSC 203 CRN 32324
 * Instructor: Grigoriy Grinberg
 * Description: This a property management program that allows a user to add rental properties
 * and display the management company's complete property portfolio.
 * Due Date: 03/23/2026
 * Platform/Compiler: Eclipse / javac
 * Integrity Pledge:
 * I pledge that I have completed the programming assignment independently. I have not copied
 * the code from a student or any other source. I have not given my code to any student or any
 * other repository (other than as described in the Deliverables for this assignment).
 * Student: Marcus Kemel Collins
 */

// Property class to hold information about each property
public class Property {
	private String propertyName;
	private String city;
	private double rentAmount;
	private String owner;
	
	/*
	 * Default constructor for Property class
	 */
	Property() {
		this.propertyName = new String("");
		this.city = new String("");
		this.rentAmount = 0.0F;
		this.owner = new String("");
	}
	
	/*
	 * Parameterized constructor for Property class
	 * @param pName property name or title
	 * @param cityName name of city property is within
	 * @param rent amount of monthly rent for property
	 * @param ownerName name of rental property owner
	 */
	Property(String pName, String cityName, double rent, String ownerName) {
		this.propertyName = new String(pName);
		this.city = new String(cityName);
		this.rentAmount = rent;
		this.owner = new String(ownerName);
	}
	
	/*
	 * Copy constructor for Property class
	 * @param p Property class instance to copy values from
	 */
	Property(Property p) {
		this.propertyName = new String(p.propertyName);
		this.city = new String(p.city);
		this.rentAmount = p.rentAmount;
		this.owner = new String(p.owner);
	}
	
	//---------//
	// Getters //
	//---------//
	
	/*
	 * Gets the property name of the Property instance
	 * @return property name
	 */
	public String getPropertyName() {
		return( new String(this.propertyName) );
	}
	
	/*
	 * Gets the city name within the Property instance
	 * @return city name
	 */
	public String getCity() {
		return( new String(this.city) );
	}
	
	/*
	 * Gets the rental amount from the Property instance
	 * @return rental amount
	 */
	public double getRentAmount() {
		return( this.rentAmount );
	}
	
	/*
	 * Gets the owner's name of the Property instance
	 * @return owner's name
	 */
	public String getOwner() {
		return( new String(this.owner) );
	}
	
	//---------//
	// Setters //
	//---------//
	
	/*
	 * Sets a new name for the Property instance
	 * @param pName new name of the property
	 */
	public void setPropertyName(String pName) {
		this.propertyName = new String(pName);
	}
	
	/*
	 * Sets a new city name/title for the Property instance
	 * @param cityName new city name of the property
	 */
	public void setCity(String cityName) {
		this.city = new String(cityName);
	}
	
	/*
	 * Sets a new rental amount for the Property instance
	 * @param pName new rent amount of the property
	 */
	public void setRentAmount(double rent) {
		this.rentAmount = rent;
	}
	
	/*
	 * Sets a new owner's name for the Property instance
	 * @param pName new owner of the property
	 */
	public void setOwner(String ownerName) {
		this.owner = new String(ownerName);
	}
	
	//---------------//
	// Misc. Methods //
	//---------------//
	
	/*
	 * Prints the class instance information as a formatted string
	 * @return the formatted information for the Property instance
	 */
	public String toString() {
		return( String.format("Name: %s | Location: %s | Owner: %s | Rent: $%,.2f", this.propertyName, this.city, this.owner, this.rentAmount) );
	}
}
