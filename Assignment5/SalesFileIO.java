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

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;


/**
 * Class to handle reading sales data from and 
 * writing statistics summary information to a text file. 
 */
public class SalesFileIO {

	/**
	 * The function that will use a filename from which
	 * to read the daily sales data numbers.
	 * @param filename The name of the input file containing the sales data
	 * @return The ragged 2D array of daily sales data as read from the file
	 */
	public static double[][] readSalesData(String filename) throws FileNotFoundException, NumberFormatException {
		// Create File object and check for file existence before continuing
		File infile = new File(filename);
		
		if( !infile.exists() ) {
			//System.out.println("File (" + filename + ") does not exist!");
			return new double[0][0];
		}
		
		// Declare variable to be used for storage list while reading text file
		ArrayList<ArrayList<Double>> sales_storage = new ArrayList<>();
		ArrayList<Integer> sales_row_size_storage = new ArrayList<Integer>();
		
		// Create storage for current line, line_number and row_size
		StringBuilder line = new StringBuilder();
		int line_number = 0;
		int curr_row_num_elem = 0;
		
		// Iterate through file line-by-line
		try(Scanner intext = new Scanner(infile)) {
			while( intext.hasNext() ) {
				sales_storage.add(new ArrayList<Double>());
				line.append(intext.nextLine());
				
				String[] line_split = line.toString().trim().split(" ");
				curr_row_num_elem = 0;
				for(int n = 0; n < line_split.length; ++n) {
					sales_storage.get(line_number).add(Double.valueOf(line_split[n].trim()));
					++curr_row_num_elem;
				}
				sales_row_size_storage.add(Integer.valueOf(curr_row_num_elem));
				
				line.delete(0, line.length());
				++line_number;
			}
		}
		catch(NumberFormatException e) {
			throw e;
		}
		
		// An existing empty file was entered by the user
		if( sales_storage.size() == 0 ) {
			return new double[0][0];
		}
		
		// Declare variable to be used for output
		double[][] sales_output = new double[sales_storage.size()][];
		
		// Iterate through storage and create output ragged array
		int curr_row_size = 0;
		for(int n = 0; n < sales_storage.size(); ++n) {
			curr_row_size = sales_row_size_storage.get(n).intValue();
			sales_output[n] = new double[curr_row_size];
			
			for(int m = 0; m < curr_row_size; ++m) {
				sales_output[n][m] = sales_storage.get(n).get(m).doubleValue();
			}
		}
		
		return sales_output;
	}
	
	public static void writeSummary(String filename, double[][] data) throws IOException {
		// Check for null data reference
		if( data == null ) {
			return;
		}
		
		// Calculate the total sales
		double total_sales = SalesDataUtility.getTotal(data);
		
		// Calculate the overall average sale
		double avg_sale = SalesDataUtility.getAverage(data);
		
		// Find the highest sale
		double highest_sale = SalesDataUtility.getHighestInArray(data);
		
		// Find the lowest sale
		double lowest_sale = SalesDataUtility.getLowestInArray(data);
		
		// Open the output file for writing
		FileWriter outfile = new FileWriter(filename, false);
		
		// Start writing to file and calculate the rows' & columns' totals 
		//  to write
		double curr_row_total = 0;
		double curr_col_total = 0;
		int max_number_of_cols = 0;
		
		try( PrintWriter outtext = new PrintWriter(outfile) ) {
			outtext.println(String.format("Total Sales: %.2f\n", total_sales));
			outtext.println(String.format("Average Sale: %.2f\n", avg_sale));
			outtext.println(String.format("Highest Sale: %.2f\n", highest_sale));
			outtext.println(String.format("Lowest Sale: %.2f\n", lowest_sale));
			
			// Determine the maximum number of columns
			max_number_of_cols = data[0].length;
			
			// Calculate & print the row totals
			for(int row = 0; row < data.length; ++row) {
				curr_row_total = SalesDataUtility.getRowTotal(data, row);
				outtext.println(String.format("Row %d Total: %.2f\n", row, curr_row_total));
				
				// Check while iterating through the row totals
				if( data[row].length > max_number_of_cols ) {
					max_number_of_cols = data[row].length;
				}
			}
			
			// Calculate &  print the column totals
			for(int col = 0; col < max_number_of_cols; ++col) {
				curr_col_total = SalesDataUtility.getColumnTotal(data, col);
				outtext.println(String.format("Column %d Total: %.2f\n", col, curr_col_total));
			}
		}
		
		// The try block should handle this close, but just in case
		outfile.close();
		
		return;
	}
}
