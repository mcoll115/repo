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

/**
 * The utility class to calculate sales statistics using static methods.
 */
public class SalesDataUtility {
	/**
	 * Calculates the summation (or total) of all sales data
	 * within the ragged 2D array.
	 * @param data The ragged 2D array of daily sales data.
	 * @return The total sales from all elements within data
	 */
	public static double getTotal(double[][] data) {
		// If a null value is entered, return 0 instead of throwing an exception
		if( data == null ) {
			return 0;
		}
		
		double sum = 0;  // the resulting summation of values
		int curr_row_size = -1;  // the current row's length
		
		for(int row = 0; row < data.length; ++row) {
			curr_row_size = data[row].length;
			for(int col = 0; col < curr_row_size; ++col) {
				sum += data[row][col];
			}
		}
		
		return sum;
	}
	
	/**
	 * Calculates the overall average sales value for all sales data.
	 * @param data The ragged 2D array of daily sales data.
	 * @return The average [daily] sales value from all elements within data. 
	 */
	public static double getAverage(double[][] data) {
		// If a null value is entered, return 0 instead of throwing an exception.
		if( data == null ) {
			return 0;
		}
		
		// the total number of elements
		int total_size = 0;
		for(int row = 0; row < data.length; ++row) {
			total_size += data[row].length;
		}
			
		if(total_size == 0 ) {
			return 0;
		}
		
		// the average value
		double average = 0.0;
		
		// the total sales value
		double sum = SalesDataUtility.getTotal(data);
		
		average = sum / ((double) total_size);
		
		return average;
	}
	
	/**
	 * Calculates the summation (or total) for the given row within the sales data.
	 * @param data The ragged 2D array of daily sales data.
	 * @param row The row number for which to calculate a total.
	 * @return The row's total sales or zero if row is out-of-bounds.
	 */
	public static double getRowTotal(double[][] data, int row) {
		// Check that a null reference has not been entered
		if( data == null ) {
			return 0;
		}
		
		// Check that row entered is within index bounds
		if( (row >= data.length) || (row < 0) ) {
			return 0;
		}
		
		// The output row total
		double row_total = 0;
		
		for(int col = 0; col < data[row].length; ++col) {
			row_total += data[row][col];
		}
		
		return row_total;	
	}
	
	/**
	 * Calculates the summation (or total) for the given column within the sales data.
	 * @param data The ragged 2D array of daily sales data.
	 * @param row The column number for which to calculate a total.
	 * @return The column's total sales or zero if column is out-of-bounds.
	 */
	public static double getColumnTotal(double[][] data, int col) {
		// Check that a null reference has not been entered
		if( data == null ) {
			return 0;
		}
		
		// Check for invalid [negative] index
		if( col < 0 ) {
			return 0;
		}
		
		// The output column total
		double column_total = 0;
		
		for(int row = 0; row < data.length; ++row) {
			if( col >= data[row].length ) {
				continue;
			}
			else {
				column_total += data[row][col];
			}
			System.out.println(row + " " + column_total);
		}
		
		return column_total;	
	}
	
	/**
	 * Finds the maximum sale for the given row within the sales data.
	 * @param data The ragged 2D array of daily sales data.
	 * @param row The row number for which to calculate a maximum.
	 * @return The row's highest sale or zero if row is out-of-bounds.
	 */
	public static double getHighestInRow(double[][] data, int row) {
		// Check that a null reference has not been entered
		if( data == null ) {
			return 0;
		}
		
		// Check that row entered is within index bounds
		if( (row >= data.length) || (row < 0) ) {
			return 0;
		}
		
		// Check that row has at least one value
		if( data[row].length < 1 ) {
			return 0;
		}
		
		// The output row maximum
		double row_max = data[row][0];
		
		for(int col = 0; col < data[row].length; ++col) {
			if(data[row][col] > row_max ) {
				row_max = data[row][col];
			}
		}
		
		return row_max;	
	}
	
	/**
	 * Finds the minimum sale for the given row within the sales data.
	 * @param data The ragged 2D array of daily sales data.
	 * @param row The row number for which to calculate a minimum.
	 * @return The row's lowest sale or zero if row is out-of-bounds.
	 */
	public static double getLowestInRow(double[][] data, int row) {
		// Check that a null reference has not been entered
		if( data == null ) {
			return 0;
		}
		
		// Check that row entered is within index bounds
		if( (row >= data.length) || (row < 0) ) {
			return 0;
		}
		
		// Check that row has at least one value
		if( data[row].length < 1 ) {
			return 0;
		}
		
		// The output row minimum
		double row_min = data[row][0];
		
		for(int col = 0; col < data[row].length; ++col) {
			if(data[row][col] < row_min ) {
				row_min = data[row][col];
			}
		}
		
		return row_min;	
	}
	
	/**
	 * Finds the maximum sale in the overall sales data.
	 * @param data The ragged 2D array of sales data
	 * @return The overall highest sale or zero if data is null
	 */
	public static double getHighestInArray(double[][] data) {
		// Check that a null reference has not been entered
		if( data == null ) {
			return 0;
		}
		
		double overall_max = data[0][0];  // the overall maximum value
		double row_max = data[0][0];  // the maximum per row
		
		// iterate over each row, calculate a max, compare, repeat
		for(int row = 0; row < data.length; ++row) {
			row_max = SalesDataUtility.getHighestInRow(data, row);
			
			if( row_max > overall_max ) {
				overall_max = row_max;
			}
		}
		
		return overall_max;
	}
	
	/**
	 * Finds the minimum sale in the overall sales data.
	 * @param data The ragged 2D array of sales data
	 * @return The overall lowest sale or zero if data is null
	 */
	public static double getLowestInArray(double[][] data) {
		// Check that a null reference has not been entered
		if( data == null ) {
			return 0;
		}
		
		double overall_min = data[0][0];  // the overall minimum value
		double row_min = data[0][0];  // the minimum per row
		
		// iterate over each row, calculate a min, compare, repeat
		for(int row = 0; row < data.length; ++row) {
			row_min = SalesDataUtility.getLowestInRow(data, row);
			
			if( row_min < overall_min ) {
				overall_min = row_min;
			}
		}
		
		return overall_min;
	}
}
