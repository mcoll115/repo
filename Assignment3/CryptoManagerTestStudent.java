/**
 * Course: CMSC 203 CRN 32324
 * Instructor: Grigoriy Grinberg
 * Description: This is a utility class that encrypts and decrypts a phrase using three
 * different approaches (Vigenere, Playfair, Caesar).
 * Due Date: 03/09/2026
 * Platform/Compiler: Eclipse / javac
 * Integrity Pledge:
 * I pledge that I have completed the programming assignment independently. I have not copied
 * the code from a student or any other source. I have not given my code to any student or any
 * other repository (other than as described in the Deliverables for this assignment).
 * Student: Marcus Kemel Collins
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CryptoManagerTestStudent {

	private String failure_string = "Selected string is not in bounds. Try again.";
	
	@Test
	void testIsStringInBoundsFalse() {
		assertFalse(CryptoManager.isStringInBounds("{hello world`"));
	}
	
	@Test
	void testIsStringInBoundsTrue() {
		assertTrue(CryptoManager.isStringInBounds("I AM NOT HOME!"));
	}

	@Test
	void testVigenereEncryptionFailure() {
		assertEquals(failure_string,
				CryptoManager.vigenereEncryption("{hello world`", "STUARTMILL"));
	}

	@Test
	void testVigenereEncryptionDecryption() {
		String text = "THINGS DO NOT CHANGE; WE CHANGE.";
		String key = "OZYMANDIAS";
		assertEquals(text, CryptoManager.vigenereDecryption(
				CryptoManager.vigenereEncryption(text, key), 
				key));
	}

	@Test
	void testPlayfairEncryptionFailure() {
		String text = "`goodbye jungle|";
		String key = "KEYNOTE";
		
		assertEquals(failure_string, CryptoManager.playfairEncryption(text, key));
	}

	@Test
	void testPlayfairEncryptionDecryption() {
		String text = "THINGS DO NOT CHANGE; WE CHANGE.";
		String key = "OZYMANDIAS";
		
		assertEquals(text, 
				CryptoManager.playfairDecryption(CryptoManager.playfairEncryption(text, key), 
													key));
	}

	@Test
	void testCaesarEncryptionFailure() {
		String text = "A POSITIVE ATTITUDE MAY| NOT SOLVE ALL YOUR PROBLEMS,";
		int key = 13;
		
		assertEquals(failure_string, CryptoManager.caesarEncryption(text, key));
	}

	@Test
	void testCaesarEncryptionDecryption() {
		String text = "A POSITIVE ATTITUDE MAY NOT SOLVE ALL YOUR PROBLEMS,";
		int key = 13;
		
		assertEquals(text, 
				CryptoManager.caesarDecryption(CryptoManager.caesarEncryption(text, key), 
												key));
	}
	
	@Test
	void testVigenereEncryptionDecrytionSeparated() {
		String text = "A BANKER IS A FELLOW WHO LENDS YOU HIS UMBRELLA...";
		String key = "EXAMPLES";
		
		String cipher = CryptoManager.vigenereEncryption(text, key);
		String plain = CryptoManager.vigenereDecryption(cipher, key);
		
		assertEquals(text, plain);
	}

	@Test
	void testPlayfairEncryptionDecrytionSeparated() {
		String text = "A BANKER IS A FELLOW WHO LENDS YOU HIS UMBRELLA...";
		String key = "EXAMPLES";
		
		String cipher = CryptoManager.playfairEncryption(text, key);
		String plain = CryptoManager.playfairDecryption(cipher, key);
		
		assertEquals(text, plain);
	}
	
	@Test
	void testCaesarEncryptionDecrytionSeparated() {
		String text = "A BANKER IS A FELLOW WHO LENDS YOU HIS UMBRELLA...";
		int key = 70;
		
		String cipher = CryptoManager.caesarEncryption(text, key);
		String plain = CryptoManager.caesarDecryption(cipher, key);
		
		assertEquals(text, plain);
	}
	
	@Test
	void testCaesarEncryptionLargeKey() {
		String text = "A POSITIVE ATTITUDE MAY NOT SOLVE ALL YOUR PROBLEMS,";
		int key = 255;
		
		String cipher = CryptoManager.caesarEncryption(text, key);
		String plain = CryptoManager.caesarDecryption(cipher, key);
		
		assertEquals(text, plain);
	}
	
	@Test
    public void testVigenereWraparound() {
        String text = "X";
        String key = "E";
        assertEquals(text,
                CryptoManager.vigenereDecryption(
                        CryptoManager.vigenereEncryption(text, key), key));
    }
    
    @Test
	void testCaesarEncryptionNegativeKey() {
		String text = "A POSITIVE ATTITUDE MAY NOT SOLVE ALL YOUR PROBLEMS,";
		int key = -10;
		
		String cipher = CryptoManager.caesarEncryption(text, key);
		String plain = CryptoManager.caesarDecryption(cipher, key);
		
		assertEquals(text, plain);
	}
}
