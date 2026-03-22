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

// Management Company class to hold Properties
public class ManagementCompany {
	private static int MAX_PROPERTIES = 5;
	private String name;
	private String taxId;
	private Property[] properties = new Property[MAX_PROPERTIES];
	private int propertyCount = 0;
	
	/*
	 * Parameterized constructor for ManagementCompany instance
	 */
	ManagementCompany(String n, String tid) {
		this.name = new String(n);
		this.taxId = new String(tid);
		this.propertyCount = 0;
	}
	
	/*
	 * Copy constructor for ManagementCompany instance
	 */
	ManagementCompany(ManagementCompany mc) {
		this.name = new String(mc.name);
		this.taxId = new String(mc.taxId);
		this.propertyCount = mc.propertyCount;
		for(int n = 0; n < mc.propertyCount; ++n) {
			this.properties[n] = new Property(mc.properties[n]);
		}
	}
	
	/*
	 * Adds a new property to the properties array
	 * @param p Property instance to add to array of properties (if allowed)
	 * @return index of added property or -1 to indicate error
	 */
	public int addProperty(Property p) {
		if(this.propertyCount == MAX_PROPERTIES) {
			return -1;
		}
		
		if( this.propertyCount >= 0 ) {
			this.properties[this.propertyCount] = new Property(p);
		}
		this.propertyCount += 1;
		
		return( this.propertyCount - 1 );
	}
	
	/*
	 * Calculates the total rent collected from properties
	 * @return The total amount of rent available to be collected
	 */
	public double totalRent() {
		double total_rent = 0;
		
		if( this.propertyCount > 0 ) {
			for(int n = 0; n < this.propertyCount; ++n) {
				total_rent += this.properties[n].getRentAmount();
			}
		}
		
		return total_rent;
	}
	
	/*
	 * Gets the current number of properties added
	 * @return The number of properties currently in properties array
	 */
	public int getPropertyCount() {
		return this.propertyCount;
	}
	
	/*
	 * Creates a display string about the ManagementCompany instance and its properties
	 * @return The display string about the management company
	 */
	public String toString() {
		String output = new String("");
		
		output += String.format("Name: %s\n", this.name);
		output += String.format("Tax ID: %s\n", this.taxId);
		for(int n = 0; n < this.propertyCount; ++n) {
			output += String.format("Property %d: %s\n", n+1, this.properties[n].toString());
		}
		
		return output;
	}
}
