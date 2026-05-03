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

public class BirdTestStudent {

    private Bird bird;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        bird = new Bird("Happy", 1, "Avian", "Blue", "./images/Bird.png");
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        outputStream.reset();
    }

    @Test
    public void testGetName() {
        assertEquals("Happy", bird.getName());
    }

    @Test
    public void testGetAge() {
        assertEquals(1, bird.getAge());
    }

    @Test
    public void testGetSpecies() {
        assertEquals("Avian", bird.getSpecies());
    }

    @Test
    public void testGetColor() {
        assertEquals("Blue", bird.getColor());
    }

    @Test
    public void testGetImagePath() {
        assertEquals("./images/Bird.png", bird.getImagePath());
    }

    @Test
    public void testToString() {
        String expected =
            "Bird [Name: Happy, Age: 1, Species: Avian, Color: Blue]";
        assertEquals(expected, bird.toString());
    }

    // ===== makeSound TEST (portable) =====
    @Test
    public void testMakeSound() {
        bird.makeSound();
        assertEquals("Chirp" + System.lineSeparator(),
                     outputStream.toString());
    }

    // ===== move TEST (portable, matches extra newline) =====
    @Test
    public void testMove() {
        bird.move();

        String output = outputStream.toString()
                                    .replace("\r\n", "\n");

        assertEquals("Hops around.\n\n", output);
    }
}
