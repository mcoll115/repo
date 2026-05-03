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

class ArenaTestStudent {
	private Arena arena;

	@BeforeEach
	public void setUp() {
		arena = new Arena("Sky Blue", 50.0, 80.0, "./images/Arena.jpg");
	}

	@Test
	public void testSetSize() {
		arena.setSize(30.0, 200.0);
		
		assertAll("testing setting arena size",
			() -> assertEquals(30.0, arena.getLength(), 0.00001, "Arena.setSize error: length not set correctly."),
			() -> assertEquals(200.0, arena.getWidth(), 0.00001, "Arena.setSize error: width not set correctly.")
		);
	}

	@Test
	public void testGetLength() {
		assertEquals(50.0, arena.getLength(), 0.00001, 
				"Arena.getLength error: length not close enough to be considered equal.");
	}

	@Test
	public void testGetWidth() {
		assertEquals(80.0, arena.getWidth(), 0.00001, 
				"Arena.getWidth error: width not close enough to be considered equal.");
	}

	@Test
	public void testSetColor() {
		arena.setColor("Orange Red");
		assertEquals("Orange Red", arena.getColor(), 
				"Arena.setColor error: color incorrectly set.");
	}

	@Test
	public void testGetColor() {
		assertEquals("Sky Blue", arena.getColor(), 
				"Arena.getColor error: unexpected color retrieved.");
	}

	@Test
	public void testSetBuildingType() {
		arena.setBuildingType("Ranch");
		
		assertEquals("Ranch", arena.getBuildingType(), 
				"Arena.setBuildingType error: building type incorrectly set.");
	}

	@Test
	public void testGetBuildingType() {
		assertEquals("Arena", arena.getBuildingType(), 
				"Arena.getBuildingType error: unexpected building type retrieved.");
	}

	@Test
	public void testGetImagePath() {
		assertEquals("./images/Arena.jpg", arena.getImagePath(), 
				"Arena.getImagePath error: unexpected image path retrieved.");
	}

	@Test
	public void testToString() {
		String arena_to_string = "\nBuilding Type: Arena \nColor: Sky Blue \nSize: 50.0 x 80.0\n";
		
		assertEquals(arena_to_string, arena.toString(), "Arena.toString error: unexpected to string.");
	}
}
