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

class ClerkTestStudent {
	
	private Clerk clerk;
	
	@BeforeEach
	public void setUp() {
		clerk = new Clerk("Daniel", 36, 8, "Clerk", "./images/clerk-1950s.jpg");
	}

	@Test
	public void testToString() {
		String clerk_to_string = "Clerk - Name: Daniel, Age: 36, Job: Clerk, Years Worked: 8";
		
		assertEquals(clerk_to_string, clerk.toString(), "testToString Error: unexpected to string.");
	}

	@Test
	public void testGetName() {
		assertEquals("Daniel", clerk.getName(), "testGetName Error: unexpected name retrieved.");
	}

	@Test
	public void testGetAge() {
		assertEquals(36, clerk.getAge(), "testGetAge Error: unexpected age retrieved.");
	}

	@Test
	public void testGetYearsWorked() {
		assertEquals(8, clerk.getYearsWorked(), "testGetYearsWorked Error: unexpected years worked retrieved.");
	}

	@Test
	public void testGetJob() {
		assertEquals("Clerk", clerk.getJob(), "testGetJob Error: unexpected job retrieved.");
	}

	@Test
	public void testGetImagePath() {
		assertEquals("./images/clerk-1950s.jpg", 
				clerk.getImagePath(), 
				"testGetImagePath Error: unexpected image path retrieved.");
	}

}
