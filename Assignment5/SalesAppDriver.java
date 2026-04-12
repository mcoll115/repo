/**
 * Course: CMSC 203 CRN 32324
 * Instructor: Grigoriy Grinberg
 * Description: This program will calculate statistics for daily sales data located in a text
 * file (provided by the user either through a GUI or a console).
 * Due Date: 04/13/2026
 * Platform/Compiler: Eclipse / javac
 * Integrity Pledge:
 * I pledge that I have completed the programming assignment independently. I have not copied
 * the code from a student or any other source. I have not given my code to any student or any
 * other repository (other than as described in the Deliverables for this assignment).
 * Student: Marcus Kemel Collins
 */

import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Scanner;

/**
 * Class to prompt user for input and output filenames to
 * read daily sales data from and writes summary statistics to.
 */
public class SalesAppDriver {
	/**
	 * main driver for console/terminal version of SalesApp
	 * @param args Array of strings entered on terminal/console line
	 */
	public static void main(String[] args) {
		// console input from keyboard
		Scanner keybd = new Scanner(System.in);

		// Filenames for input and output
		String sales_filename;
		String summary_filename;
		
		// Prompt for input filename
		System.out.println("Please enter sales data filename: ");
		sales_filename = keybd.nextLine();
		
		// Prompt for output filename
		System.out.println("Please enter summary filename: ");
		summary_filename = keybd.nextLine();
		
		// Close the keyboard scanner input
		keybd.close();
		
		// Read sales data from file & write summary to file
		try {
			double[][] sales = SalesFileIO.readSalesData(sales_filename.toString());
			
			SalesFileIO.writeSummary(summary_filename.toString(), sales);
		}
		catch(FileNotFoundException e) {
			System.out.println(e);
		}
		catch(IOException e) {
			System.out.println(e);
		}
		
	}

}
