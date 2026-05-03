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
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class TicketingOfficeTestStudent {
	private TicketingOffice ticket_office;

	@BeforeEach
	public void setUp() {
		ticket_office = new TicketingOffice("Dark Lavender", 90.0, 70.0, "./images/TicketOffice.jpg");
	}

	@Test
	public void testSetSize() {
		ticket_office.setSize(150.0, 100.0);
		
		assertAll("testing setting ticketing office size",
			() -> assertEquals(150.0, ticket_office.getLength(), 0.00001, "TicketingOffice.setSize error: length not set correctly."),
			() -> assertEquals(100.0, ticket_office.getWidth(), 0.00001, "TicketingOffice.setSize error: width not set correctly.")
		);
	}

	@Test
	public void testGetLength() {
		assertEquals(90.0, ticket_office.getLength(), 0.00001, 
				"TicketingOffice.getLength error: length not close enough to be considered equal.");
	}

	@Test
	public void testGetWidth() {
		assertEquals(70.0, ticket_office.getWidth(), 0.00001, 
				"TicketingOffice.getWidth error: width not close enough to be considered equal.");
	}

	@Test
	public void testSetColor() {
		ticket_office.setColor("Mirrored Glass");
		assertEquals("Mirrored Glass", ticket_office.getColor(), 
				"TicketingOffice.setColor error: color incorrectly set.");
	}

	@Test
	public void testGetColor() {
		assertEquals("Dark Lavender", ticket_office.getColor(), 
				"TicketingOffice.getColor error: unexpected color retrieved.");
	}

	@Test
	public void testSetBuildingType() {
		ticket_office.setBuildingType("Skyscraper");
		
		assertEquals("Skyscraper", ticket_office.getBuildingType(), 
				"TicketingOffice.setBuildingType error: building type incorrectly set.");
	}

	@Test
	public void testGetBuildingType() {
		assertEquals("Ticketing Office", ticket_office.getBuildingType(), 
				"TicketingOffice.getBuildingType error: unexpected building type retrieved.");
	}

	@Test
	public void testGetImagePath() {
		assertEquals("./images/TicketOffice.jpg", ticket_office.getImagePath(), 
				"TicketingOffice.getImagePath error: unexpected image path retrieved.");
	}

	@Test
	public void testToString() {
		String ticket_office_to_string = 
				"\nBuilding Type: Ticketing Office \nColor: Dark Lavender \nSize: 90.0 x 70.0\n";
		
		assertEquals(ticket_office_to_string, ticket_office.toString(), "TicketingOffice.toString error: unexpected to string.");
	}
}
