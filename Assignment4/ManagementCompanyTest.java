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

class ManagementCompanyTest {
	
	// Values to use within tests for convenience
	private String mgtcomp1 = new String("Ghost Rae, Inc.");
	private String taxid1 = new String("I-1994");
	
	private String mgtcomp2 = new String("Method Deck, LLC");
	private String taxid2 = new String("F-1995");
	
	private Property p1 = new Property("Stark Towers", "New York", 1000000.0, "Tony Stark");
	private Property p2 = new Property("Sunny Headquarters", "Los Angeles", 900000.0, "Palladin, LLC");
	
	private Property emptyProp = new Property();
	
	@Test
	void testMgtCompConstructor() {
		ManagementCompany mc = new ManagementCompany(mgtcomp1, taxid1);
		
		assertAll("ManagementCompany constructor and values testing:",
			() -> assertNotNull(mc, "management company instance not created/constructed"),
			() -> assertEquals(0, mc.getPropertyCount(), "management company has incorrect property count")
		);
	}
	
	@Test
	void testMgtCompAddProperty() {
		ManagementCompany mc = new ManagementCompany(mgtcomp1, taxid1);
		
		assertNotNull(mc, "management company instance not created");
		
		int p_index = mc.addProperty(this.p1);
		
		assertAll("ManagementCompany adding properties:",
			() -> assertEquals(1, mc.getPropertyCount(), "management company property not added correctly"),
			() -> assertEquals(0, p_index, "management company returns incorrect index")
		);
	}
	
	@Test
	void testMgtCompAddPropertyAfterFull() {
		ManagementCompany mc = new ManagementCompany(mgtcomp1, taxid1);
		
		assertNotNull(mc, "management company instance not created");
		
		int p_index = 0;
		for(int n = 1; n < 7; ++n) {
			p_index = mc.addProperty(emptyProp);
		}
		
		assertEquals(-1, p_index, "management company add property does not error correctly");
	}
	
	@Test
	void testMgtCompTotalRent() {
		double total_rent_expected = this.p1.getRentAmount() + this.p2.getRentAmount();
		
		ManagementCompany mc = new ManagementCompany(mgtcomp1, taxid1);
		
		assertNotNull(mc, "management company instance not created");
		
		mc.addProperty(this.p1);
		int p_index = mc.addProperty(this.p2);
		
		assertAll("ManagementCompany total rent calculation:",
			() -> assertEquals(2, mc.getPropertyCount(), "management company has incorrect amount of properties"),
			() -> assertEquals(total_rent_expected, mc.totalRent(), 0.001, "management company rental total is incorrect"),
			() -> assertEquals(1, p_index, "management company has incorrect index after adding properties")
		);
	}
	
	@Test
	void testMgtCompToString() {
		ManagementCompany mc = new ManagementCompany(mgtcomp1, taxid1);
		
		assertNotNull(mc, "management company instance not created");
		
		mc.addProperty(this.p1);
		mc.addProperty(this.p2);
		
		String expected_string = new String("");
		expected_string += String.format("Name: %s\n", mgtcomp1);
		expected_string += String.format("Tax ID: %s\n", taxid1);
		expected_string += String.format("Property 1: %s\n", this.p1.toString());
		expected_string += String.format("Property 2: %s\n", this.p2.toString());
		
		assertEquals(expected_string, mc.toString(), "management company display string incorrectly written");
	}
	
	@Test
	void testMgtCompCopyConstructor() {
		ManagementCompany mc1 = new ManagementCompany(mgtcomp1, taxid1);
		
		ManagementCompany mc2 = new ManagementCompany(mc1);
		
		assertNotNull(mc1, "management company instance not created");
		mc1.addProperty(p1);
		
		assertAll("ManagementCompany copy construction testing:",
			() -> assertNotNull(mc2, "management company 2 instance not created"),
			() -> assertNotSame(mc2, mc1, "management company instances are the same object (or reference)"),
			() -> assertNotEquals(mc2.toString(), mc1.toString(), "management company instances have same values")
		);
	}

}
