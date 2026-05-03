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
//package BobsCircus;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LionTestStudent {

    private Lion lion;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        lion = new Lion("Sylvester", 8, "Feline", "Light Tan", "./images/lion.png");
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        outputStream.reset();
    }

    @Test
    public void testGetName() {
        assertEquals("Sylvester", lion.getName());
    }

    @Test
    public void testGetAge() {
        assertEquals(8, lion.getAge());
    }

    @Test
    public void testGetSpecies() {
        assertEquals("Feline", lion.getSpecies());
    }

    @Test
    public void testGetColor() {
        assertEquals("Light Tan", lion.getColor());
    }

    @Test
    public void testGetImagePath() {
        assertEquals("./images/lion.png", lion.getImagePath());
    }

    @Test
    public void testToString() {
        String expected =
            "Lion [Name: Sylvester, Age: 8, Species: Feline, Color: Light Tan]";
        assertEquals(expected, lion.toString());
    }

    // ===== makeSound TEST (portable) =====
    @Test
    public void testMakeSound() {
        lion.makeSound();
        assertEquals("Roar!!" + System.lineSeparator(),
                     outputStream.toString());
    }

    // ===== move TEST (portable, matches extra newline) =====
    @Test
    public void testMove() {
        lion.move();

        String output = outputStream.toString()
                                    .replace("\r\n", "\n");

        assertEquals("Stalks silently.\n\n", output);
    }
}
