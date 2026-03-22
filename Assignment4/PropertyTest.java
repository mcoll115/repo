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

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

// Testing for the Property class independent of ManagementCompany class 
class PropertyTest {

	@Test
	void testPropertyDefault() {
		Property p = new Property();
		
		assertAll("Default constructor testing",
			() -> assertNotNull(p, "property p was not default constructed"),
			() -> assertEquals("Name:  | Location:  | Owner:  | Rent: $0.00", p.toString(), "property p does not have default values")
		);
	}
	
	@Test
	void testPropertyCopyConstructor() {
		Property p1 = new Property("kick properties, llc", "south park", 250.0f, "ike broflovski");
		
		assertNotNull(p1, "property p1 is null");
		Property p2 = new Property(p1);
		
		assertNotNull(p2, "property p2 is null");
		p2.setRentAmount(900.0);
		p2.setCity("denver");
		
		assertAll("Property copy construction testing:",
			() -> assertNotSame(p1, p2, "property p1 is the same object as property p2"),
			() -> assertNotEquals(p2.toString(), p1.toString(), "property p2 has same values as property p1")
		);
	}
	
	@Test
	void testPropertyGetters() {
		Property p = new Property("mad city properties, llc", "louisville", 100.0f, "kenny");
		
		assertAll("Property Getters testing:",
			() -> assertNotNull(p, "property p was not constructed"),
			() -> assertEquals("mad city properties, llc", p.getPropertyName(), "property names was not retrieved properly"),
			() -> assertEquals("louisville", p.getCity(), "property city was not retrieved properly"),
			() -> assertEquals(100.0f, p.getRentAmount(), 0.001f, "property rental amount was not retrieved properly"),
			() -> assertEquals("kenny", p.getOwner(), "property owner was not retrieved properly")
		);
	}

	@Test
	void testPropertySetters() {
		Property p = new Property("mad city properties, llc", "louisville", 100.0f, "kenny");
		
		assertNotNull(p, "property p was not constructed");
		
		p.setPropertyName("apple orchard, inc.");
		p.setCity("miami");
		p.setRentAmount(300);
		p.setOwner("stan");
		
		assertAll("Property Setters testing:",
			() -> assertEquals("apple orchard, inc.", p.getPropertyName(), "property names was not set properly"),
			() -> assertEquals("miami", p.getCity(), "property city was not set properly"),
			() -> assertEquals(300.0f, p.getRentAmount(), 0.001f, "property rental amount was not set properly"),
			() -> assertEquals("stan", p.getOwner(), "property owner was not set properly")
		);
	}
	
	@Test
	void testPropertyToString() {
		Property p = new Property("apple orchard, inc.", "oakland", 525.0, "kyle south");
		
		assertAll("Property toString() testing:",
			() -> assertNotNull(p, "property was not constructed"),
			() -> assertEquals("Name: apple orchard, inc. | Location: oakland | Owner: kyle south | Rent: $525.00", p.toString(), "property values were not displayed correctly")
		);
	}
}
