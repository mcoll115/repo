/**
 * This is a utility class that encrypts and decrypts a phrase using three
 * different approaches. 
 * 
 * The first approach is called the Vigenere Cipher.Vigenere encryption 
 * is a method of encrypting alphabetic text based on the letters of a keyword.
 * 
 * The second approach is Playfair Cipher. It encrypts two letters (a digraph) 
 * at a time instead of just one.
 * 
 * The third approach is Caesar Cipher. It is a simple replacement cypher. 
 * 
 * @author Huseyin Aygun
 * @version 8/3/2025
 *
 */
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
import java.util.ArrayList;

public class CryptoManager { 

    private static final char LOWER_RANGE = ' ';
    private static final char UPPER_RANGE = '_';
    private static final int RANGE = UPPER_RANGE - LOWER_RANGE + 1;
    // Use 64-character matrix (8X8) for Playfair cipher
    // NOTE: a SPACE character needed to be added after '9' to make this "work" for the pre-built tests 
    private static final String ALPHABET64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 !\"#$%&'()*+,-./:;<=>?@[\\]^_";

    public static boolean isStringInBounds(String plainText) {
        for (int i = 0; i < plainText.length(); i++) {
            if (!(plainText.charAt(i) >= LOWER_RANGE && plainText.charAt(i) <= UPPER_RANGE)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Function to build the Playfair matrix used during either encryption or decryption.
     * @param key the text string to use for encryption and decryption
     * @return the two-dimensional array of char's (shape: 8x8) to use for encryption and/or decryption
     */
    private static char[][] buildPlayfairMatrix(String key) {
    	final int NUMROWS = 8;
    	final int NUMCOLS = 8; // Playfair Matrix is always square but usually 5x5
    	char[][] playfairMat = new char[NUMROWS][NUMCOLS];
    	
    	int pfRow = 0, pfCol = 0; // the row and column within the Playfair Square to set a character
    	
    	// the temporary store for the ALPHABET64 to add to and remove from; thereby keeping track of repeat
    	// characters in the key and the remaining characters after the key length has been reached
    	ArrayList<Character> alphabet_set = new ArrayList<Character>();
    	
    	// fill the ArrayList character set
    	for(int n = 0; n < ALPHABET64.length(); ++n) {
    		alphabet_set.add(Character.valueOf(ALPHABET64.charAt(n)));
    	}
    	
    	// the location within the char_set list that a key character was found
    	// NOTE: -1 will be used to indicate a character not in the list 
    	//     isStringInBounds method should already have handled this, but ...
    	int alphabetset_index = -1;
    	
    	char key_char;
    	
    	// iterate through the key to build the matrix
    	for(int n = 0; n < key.length(); ++n) {
    		alphabetset_index = -1;    // reset to -1 each iteration
  			key_char = key.charAt(n);  // grab the current key character
  			
    		// iterate through the character set list
    		for(int m = 0; m < alphabet_set.size(); ++m) {
    			if( key_char == alphabet_set.get(m).charValue() ) {
    				alphabetset_index = m;
    				break;
    			}
    		}// end FOR(find current char in alphabet_set)
    		
    		// set the playfairMat[pfRow][pfCol] = key_char IF not a repeat value
    		if( alphabetset_index != -1 ) {
    			playfairMat[pfRow][pfCol] = key_char;
    			
    			alphabet_set.remove(alphabetset_index);
    			
    			// update the current indices within the Playfair Square
    			++pfCol;
    			if(pfCol == NUMCOLS) {
    				++pfRow;
    				if( pfRow == NUMROWS ) {
    					break;
    				}
    				pfCol = 0;
    			}
    		}// end IF(alphabetset_index not equal to -1)
    	}// end FOR(key string iteration)
    	
    	// continue to fill the matrix if the key is shorter than 64 characters
    	if( pfRow < NUMROWS ) {
    		for(int n = 0; n < alphabet_set.size(); ++n) {
    			playfairMat[pfRow][pfCol] = alphabet_set.get(n).charValue();
    			++pfCol;
    			if( pfCol == NUMCOLS ) {
    				++pfRow;
    				pfCol = 0;
    				if(pfRow == NUMROWS) {
    					break;
    				}
    			}
    		}
    	}// end IF(pfRow index less than NUMROWS) [fill remainder of matrix]
    	
    	return playfairMat;
    }

	/**
	 * Vigenere Cipher is a method of encrypting alphabetic text 
	 * based on the letters of a keyword. It works as below:
	 * 		Choose a keyword (e.g., KEY).
	 * 		Repeat the keyword to match the length of the plaintext.
	 * 		Each letter in the plaintext is shifted by the position of the 
	 * 		corresponding letter in the keyword (A = 0, B = 1, ..., Z = 25).
	 * @param plainText the plain text to be encrypted/enciphered
	 * @param key the key (a string) used to encrypt/encipher the plain text string
	 * @return the encrypted/enciphered string or an error message if the plain text string is out-of-bounds
	 */   

    public static String vigenereEncryption(String plainText, String key) {
    	// output enciphered text
    	String ciphertext = ""; 
    	
    	// check the string characters are "in bounds"
    	if( !isStringInBounds(plainText) ) {
    		ciphertext = "Selected string is not in bounds. Try again.";
    		return ciphertext;
    	}
    	
    	int lenKeyText = key.length(); // length of key text
    	
    	int key_index = 0; // index of key character to use for enciphering
    	
    	int shift_amount = 0; // amount to shift the plaintext character by to encipher
    	
    	// iterate through the plaintext string character-by-character
    	int currPlainChar = 0, currCipherChar = 0, currResChar;
    	for(int n = 0; n < plainText.length(); ++n) {
    		currPlainChar = (int) plainText.charAt(n);
    		
    		currCipherChar = (int) key.charAt(key_index);
    		shift_amount = currCipherChar - ((int) LOWER_RANGE);
    		
    		// shift the plain character and append to the resulting string
    		currResChar = currPlainChar + shift_amount;
    			
    		/*
    		 * NOTE: When enciphering, we ensure that the character is between LOWER_RANGE and UPPER_RANGE
    		 */
    		if( currResChar > ((int) UPPER_RANGE) ) {
    			// Calculate a new shift amount to move from the LOWER_RANGE character
    			int modified_shift = currResChar - ((int) UPPER_RANGE);
    			
    			// NOTE: handle very specific edge case of key at end of alphabet range but text at end of symbol range
    			if( modified_shift == shift_amount ) {
    				currResChar = modified_shift;
    			}
    			else {
    				currResChar = ((int) LOWER_RANGE) + modified_shift;
    			}
    		}

    		ciphertext += (char) currResChar;
    		
    		// update the key_index value by incrementing and checking that it is not past the key length
    		++key_index;
    		if( key_index == lenKeyText ) {
    			key_index = 0;
    		}
    	}
    	
    	return ciphertext;
    }

    // Vigenere Decryption
    /**
     * The decryption function to pair with the vigenereEncryption method. 
     * Provides the inverse result of the encryption method.
     * @param encryptedText the encrypted/enciphered text string to be turned to plain text.
     * @param key the text string to use for decrypting/deciphering the input string to plain text.
     * @return the plain text [original] message string
     */
    public static String vigenereDecryption(String encryptedText, String key) {
    	// output deciphered text (updated iteratively)
    	String plaintext = ""; 
    	
    	// check the string characters are "in bounds"
    	if( !isStringInBounds(encryptedText) ) {
    		plaintext = "Selected string is not in bounds. Try again.";
    		return plaintext;
    	}
    	
    	int lenKeyText = key.length(); // length of key text
    	
    	int key_index = 0; // index of key character to use for enciphering
    	
    	int shift_amount = 0; // amount to shift the plaintext character by to encipher
    	
    	// the loop to make the substitution per-character from the string
    	int currChar = 0, currDecipherChar = 0, currResChar = 0;
    	for(int n = 0; n < encryptedText.length(); ++n) {
    		currChar = (int) encryptedText.charAt(n);
    		
    		currDecipherChar = (int) key.charAt(key_index);
    			
    		// calculate the shift amount by accounting for the lowest character's value
    		shift_amount = currDecipherChar - ((int) LOWER_RANGE);
    			
    		// shift the enciphered character and append to the resulting string
    		currResChar = currChar - shift_amount;
    		
    		/*
    		 * NOTE: When enciphering, we ensure that the character is between LOWER_RANGE and UPPER_RANGE
    		 * So, if the subtraction results in less than OR equal to LOWER_RANGE, we need to wrap back around
    		 * to the UPPER_RANGE end of the alphabet. 
    		 * So currChar - LOWER_RANGE will be negative and adding UPPER_RANGE will force it to wrap back
    		 * within the range LOWER_RANGE to UPPER_RANGE.
    		 */
    		if( currResChar < ((int) LOWER_RANGE) ) {
    			int modified_shift = currResChar - ((int) LOWER_RANGE);

				// NOTE: handle very specific edge case of key at alphabet range length and text at symbol range length
    			if( modified_shift == -((int) LOWER_RANGE) ) {
    				currResChar = (int) UPPER_RANGE;
    			}
    			else {
    				currResChar = modified_shift + ((int) UPPER_RANGE);
    			}
    		}// end IF(result char less than LOWER_RANGE)
    			
    		plaintext += (char) currResChar; // append the resulting character to the output string
    		
    		// update the key_index value by incrementing and checking that it is not past the key length
    		++key_index;
    		if( key_index == lenKeyText ) {
    			key_index = 0;
    		}
    	}// end FOR(ciphertext iteration)
    	
    	return plaintext;
    }


	/**
	 * Playfair Cipher encrypts two letters at a time instead of just one.
	 * It works as follows:
	 * A matrix (8X8 in our case) is built using a keyword
	 * Plaintext is split into letter pairs (e.g., ME ET YO UR).
	 * Encryption rules depend on the positions of the letters in the matrix:
	 *     Same row: replace each letter with the one to its right.
	 *     Same column: replace each with the one below.
	 *     Rectangle: replace each letter with the one in its own row but in the column of the other letter in the pair.
	 * @param plainText The text string to convert to an encrypted/enciphered string
	 * @param key The text string used to control the encryption/encipherment of the plain text string
	 * @return The resulting string from the encryption or an error message indicating the plain text string was out-of-bounds
	 */    

    public static String playfairEncryption(String plainText, String key) {
		 // output string storage (updated iteratively)
         String ciphertext = ""; 
         
         // check if string is "in-bounds" before processing
         if( !isStringInBounds(plainText) ) {
         	ciphertext = "Selected string is not in bounds. Try again.";
    		return ciphertext;
         }
         
         // build the Playfair Square for encryption
         char[][] playfair_matrix = buildPlayfairMatrix(key);
         
         
         // make a copy of the incoming plaintext and make sure it's length can be 
         // divided into digraphs
         // Rule 2
         String plaintext_copy = new String(plainText);
         if( (plainText.length() % 2) != 0 ) {
        	 plaintext_copy += "X";
         }
         
         // iterate through the plaintext copy and get digraphs
         int fcRow, fcCol; // first character row and column positions
         int scRow, scCol; // second character row and column positions
         
         char firstChar, secondChar;  // first and second character within a digraph
         
         for(int n = 0; n < (plaintext_copy.length()-1); ) {
        	 firstChar = plaintext_copy.charAt(n);
        	 secondChar = plaintext_copy.charAt(n+1);
        	 
        	 // Rule 1: look for repeated characters in a digraph and pad with 'X' as needed - IGNORED SORT OF
        	 n += 2;
        	 
        	 // search entire matrix for first and second character
        	 fcRow = -1;
        	 fcCol = -1;
        	 scRow = -1;
        	 scCol = -1;
        	 for(int row = 0; row < playfair_matrix.length; ++row) {
        		 for(int col = 0; col < playfair_matrix[row].length; ++col) {
        			 if( firstChar == playfair_matrix[row][col] ) {
        				 fcRow = row;
        				 fcCol = col;
        			 }
        			 
        			 if( secondChar == playfair_matrix[row][col] ) {
        				 scRow = row;
        				 scCol = col;
        			 }
        		 }
        	 }// end for(row){for(col)}
        	 
        	 // obey main rules for grabbing cipher characters from the matrix
        	 if( fcRow == scRow ) {
        		 // Rule 3: if in same row, get character to the immediate right column
        		 //    include a same row wraparound
        		 if( (fcCol + 1) == playfair_matrix[0].length ) {
        			 ciphertext += playfair_matrix[fcRow][0];
        		 }
        		 else {
        			 ciphertext += playfair_matrix[fcRow][fcCol + 1];
        		 }
        		 
        		 if( (scCol + 1) == playfair_matrix[0].length ) {
        			 ciphertext += playfair_matrix[scRow][0];
        		 }
        		 else {
        			 ciphertext += playfair_matrix[scRow][scCol + 1];
        		 }
        	 }
        	 else if( fcCol == scCol ) {
        		 // Rule 4: if in same column, get character to immediate down row
        		 //    include a same column wraparound
        		 if( (fcRow + 1) == playfair_matrix[0].length ) {
        			 ciphertext += playfair_matrix[0][fcCol];
        		 }
        		 else {
        			 ciphertext += playfair_matrix[fcRow + 1][fcCol];
        		 }
        		 
        		 if( (scRow + 1) == playfair_matrix[0].length ) {
        			 ciphertext += playfair_matrix[0][scCol];
        		 }
        		 else {
        			 ciphertext += playfair_matrix[scRow + 1][scCol];
        		 }
        	 }
        	 else {
        		 // Rule 5: if forming a rectangle, get character from same row, BUT
        		 //    in the column from the other character
        		 ciphertext += playfair_matrix[fcRow][scCol];
        		 ciphertext += playfair_matrix[scRow][fcCol];
        	 }// end IF-ELSEIF-ELSE( fcRow==scRow, fcCol==scCol)
         }// end for(plaintext iteration)
         
    	return ciphertext;
    }

    // Vigenere Decryption
    /**
     * The decryption pair for the playfairEncryption method.
     * @param encryptedText the encrypted text string to be converted to plain text.
     * @param key the text string controlling the decryption to plain text
     * @return the plain text string resulting from the decryption algorithm
     */
    public static String playfairDecryption(String encryptedText, String key) {
         // the output string to store the return result (updated iteratively)
         String plaintext = "";
         
         // build the Playfair Square for decryption
         char[][] playfair_matrix = buildPlayfairMatrix(key);
         
         String ciphertext_copy = new String(encryptedText);

		// detect when a digraph ended with 'X' to determine if repeat characters were present
         boolean plaintext_sub_ending_is_x = false;
         
         // iterate through the ciphertext copy and get digraphs
         int fcRow, fcCol; // first character row and column positions
         int scRow, scCol; // second character row and column positions
         
         char firstChar, secondChar;  // first and second character within a digraph
         
         for(int n = 0; n < (ciphertext_copy.length()-1); ) {
        	 firstChar = ciphertext_copy.charAt(n);
        	 secondChar = ciphertext_copy.charAt(n+1);
        	 n += 2;
        	 
        	 // search entire matrix for first and second character
        	 fcRow = -1;
        	 fcCol = -1;
        	 scRow = -1;
        	 scCol = -1;
        	 for(int row = 0; row < playfair_matrix.length; ++row) {
        		 for(int col = 0; col < playfair_matrix[row].length; ++col) {
        			 if( firstChar == playfair_matrix[row][col] ) {
        				 fcRow = row;
        				 fcCol = col;
        			 }
        			 
        			 if( secondChar == playfair_matrix[row][col] ) {
        				 scRow = row;
        				 scCol = col;
        			 }
        		 }
        	 }// end for(row){for(col)}
        	 
        	 // obey main rules for grabbing cipher characters from the matrix
        	 if( fcRow == scRow ) {
        		 // Rule 3: if in same row, get character to the immediate left column
        		 //    include a same row wraparound
        		 if( (fcCol - 1) == -1 ) {
        			 plaintext += playfair_matrix[fcRow][playfair_matrix.length - 1];
        		 }
        		 else {
        			 plaintext += playfair_matrix[fcRow][fcCol - 1];
        		 }
        		 
        		 if( (scCol - 1) == -1 ) {
        			 plaintext += playfair_matrix[scRow][playfair_matrix.length - 1];
        		 }
        		 else {
        			 plaintext += playfair_matrix[scRow][scCol - 1];
        		 }
        	 }
        	 else if( fcCol == scCol ) {
        		 // Rule 4: if in same column, get character to immediate down row
        		 //    include a same column wraparound
        		 if( (fcRow - 1) == -1 ) {
        			 plaintext += playfair_matrix[playfair_matrix.length - 1][fcCol];
        		 }
        		 else {
        			 plaintext += playfair_matrix[fcRow - 1][fcCol];
        		 }
        		 
        		 if( (scRow - 1) == -1 ) {
        			 plaintext += playfair_matrix[playfair_matrix.length - 1][scCol];
        		 }
        		 else {
        			 plaintext += playfair_matrix[scRow - 1][scCol];
        		 }
        	 }
        	 else {
        		 // Rule 5: if forming a rectangle, get character from same row, BUT
        		 //    in the column from the other character
        		 plaintext += playfair_matrix[fcRow][scCol];
        		 plaintext += playfair_matrix[scRow][fcCol];
        	 }// end IF-ELSEIF-ELSE( fcRow==scRow, fcCol==scCol, )
        	
        	 if( plaintext_sub_ending_is_x ) {
        		 if( plaintext.charAt(plaintext.length() - 4) == plaintext.charAt(plaintext.length() - 2) ) {
        			 String sub1 = plaintext.substring(0, plaintext.length() - 3);
        			 String sub2 = plaintext.substring(plaintext.length() - 2);
        			 plaintext = sub1.concat(sub2);
        		 }
        		 plaintext_sub_ending_is_x = false;
        	 }// end IF(endswith 'X') [substring extraction]
        	 
        	 if( plaintext.charAt(plaintext.length() - 1) == 'X' ) {
        		 plaintext_sub_ending_is_x = true;
        	 }
        	 
         }// end FOR(ciphertext iteration)
         
         if( plaintext_sub_ending_is_x ) {
        	 plaintext = plaintext.substring(0, plaintext.length() - 1);
        	
        	 plaintext_sub_ending_is_x = false;
         }
         
         return plaintext;
    }

    /**
     * Caesar Cipher is a simple substitution cipher that replaces each letter in a message 
     * with a letter some fixed number of positions down the alphabet. 
     * For example, with a shift of 3, 'A' would become 'D', 'B' would become 'E', and so on.
     * @param plainText the plain text string to be converted to an encrypted/enciphered string
     * @param key the integer to shift the text string characters for encryption
     * @return the encrypted/enciphered text string or an error message indicating the plain text string was out-of-bounds
     */    
    public static String caesarEncryption(String plainText, int key) {
    	// output cipher message or error message
    	String ciphertext = ""; 
    	
    	int shift_amount = 0; // amount to shift each character
    	
    	if( !isStringInBounds(plainText) ) {
    		ciphertext = "Selected string is not in bounds. Try again.";
    		return ciphertext;
    	}
    	
    	// check that key value is within the proper range for shifting characters
    	if( key > RANGE ) {
    		shift_amount = key % RANGE;
    	}
    	else if( key < 0 ) {
    		shift_amount = key;
    		while( shift_amount < 0 ) { 
    			shift_amount += RANGE;
    		}
    	}
    	else {
    		shift_amount = key;
    	}
    	
    	// loop through characters and shift each to form the ciphertext
    	int currChar = 0, currPlainChar = 0;
    	for(int n = 0; n < plainText.length(); ++n) {
    		currPlainChar = (int) plainText.charAt(n);
 
    		currChar = currPlainChar;
    		currChar += shift_amount;
    			
    		/*
    		 * NOTE: Ensure when enciphering that output character stays between LOWER_RANGE and UPPER_RANGE
    		 */
    		if( currChar > ((int) UPPER_RANGE)) {
    			currChar = (currChar - ((int) UPPER_RANGE) - 1) + ((int) LOWER_RANGE);
    		}
    		ciphertext += (char)(currChar);
    	}
    	
    	// return results of enciphering when no errors occur
    	return ciphertext;
    	
    }

    // Caesar Decryption
    /**
     * The inverse process of decrypting a text string produced by the caesarEncryption method.
     * @param encryptedText the encrypted/enciphered text string to be inverted to plain text
     * @param key the integer used to originally shift the plain text characters
     * @return the plain text [original] message after decryption
     */
    public static String caesarDecryption(String encryptedText, int key) {
    	// output deciphered message or error message
    	String plaintext = "";
    	
    	int shift_amount = 0; // amount to shift each character
    	
    	if( !isStringInBounds(encryptedText) ) {
    		plaintext = "Selected string is not in bounds. Try again.";
    		return plaintext;
    	}
    	
    	// check that key value is within the proper range for shifting characters
    	if( key > RANGE ) {
    		shift_amount = key % RANGE;
    	}
    	else if( key < 0 ) {
    		shift_amount = key;
    		while( shift_amount < 0 ) { 
    			shift_amount += RANGE;
    		}
    	}
    	else {
    		shift_amount = key;
    	}
    	
    	// loop through characters and shift each to form the plaintext
    	int currChar = 0;
    	for(int n = 0; n < encryptedText.length(); ++n) {
    		currChar = (int) encryptedText.charAt(n);
    		
    		currChar -= shift_amount;
    			
    		if( currChar < ((int) LOWER_RANGE) ) {
    			currChar = (currChar - ((int) LOWER_RANGE) + ((int) UPPER_RANGE) + 1);
    		}
    			
    		plaintext += (char)(currChar);
    	}
    	
    	// return results of enciphering when no errors occur
    	return plaintext;
    }    

}
