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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AcrobaticTestStudent {
	private Acrobatic acrobat;

	@BeforeEach
	public void setUp() {
		acrobat = new Acrobatic("Rita", 22, 5, "Acrobat", "./images/acrobat.jpg");
	}

	@Test
	public void testToString() {
		String acrobat_to_string = "Acrobatic - Name: Rita, Age: 22, Job: Acrobat, Years Worked: 5";
		
		assertEquals(acrobat_to_string, acrobat.toString(), "Acrobatic.toString error: unexpected to string.");
	}

	@Test
	public void testGetName() {
		assertEquals("Rita", acrobat.getName(), "Acrobatic.getName error: unexpected name retrieved.");
	}

	@Test
	public void testGetAge() {
		assertEquals(22, acrobat.getAge(), "Acrobatic.getAge error: unexpected age retrieved.");
	}

	@Test
	public void testGetYearsWorked() {
		assertEquals(5, acrobat.getYearsWorked(), "Acrobatic.getYearsWorked error: unexpected years worked retrieved.");
	}

	@Test
	public void testGetJob() {
		assertEquals("Acrobat", acrobat.getJob(), "Acrobatic.getJob error: unexpected job retrieved.");
	}

	@Test
	public void testGetImagePath() {
		assertEquals("./images/acrobat.jpg", acrobat.getImagePath(), "Acrobatic.getImagePath error: unexpected image path retrieved.");
	}
}
