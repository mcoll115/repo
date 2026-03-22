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
 
public class PropertyDriver {
		
	public static void main(String[] args) {
		
		// Management Company instances
		String mc_title1 = new String("Speakeasy Limited, LLC");
		String mc_taxid1 = new String("IRS-574638");
		ManagementCompany mgtCompany1 = new ManagementCompany(mc_title1, mc_taxid1);
		
		ManagementCompany mgtCompany2;
		
		// Property instances
		Property p1 = new Property("kick properties, llc", "south park", 4000.0f, "ike broflovski");
		Property p2 = new Property("man man, llc", "denver", 90000.0f, "gerald marsh");
		Property p3 = new Property("rocksteady holdings, inc", "aurora", 3000.0f, "helpful vets, inc.");
		Property p4 = new Property("authority today, inc", "mesa", 2500.0f, "kevin gadsden");
		
		// Adding properties to management company 1
		mgtCompany1.addProperty(p1);
		mgtCompany1.addProperty(p2);
		
		// copying management company 1 using copy constructor then adding properties to the copy
		mgtCompany2 = new ManagementCompany(mgtCompany1);
		mgtCompany2.addProperty(p3);
		mgtCompany2.addProperty(p4);
		
		// printing the contents of both management companies for comparison and contrasting
		System.out.println("Management Company 1 Properties:");
		System.out.println(mgtCompany1.toString());
		System.out.println(String.format("Total rent: %,.2f", mgtCompany1.totalRent()));
		System.out.println("");
		
		System.out.println("Management Company 2 Properties:");
		System.out.println(mgtCompany2.toString());
		System.out.println(String.format("Total rent: %,.2f", mgtCompany2.totalRent()));
		System.out.println("");
		System.out.println("Programmed by student Marcus K. Collins for CMSC203.");
	}

}
