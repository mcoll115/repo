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

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * JUnit test class for the static methods within the SalesDataUtility class 
 */
class SalesDataUtilityTest {

	// sales data for testing utility functions
	private double[][] test_sales;
	
	// failure case for various utility functions
	private final double failure_value = 0;
	
	// commonly used indices
	private final int row0 = 0;
	private final int row1 = 1;
	private final int row2 = 2;
	private final int row_1 = -1;
	
	@BeforeEach
	void setUp() throws Exception {
		this.test_sales = new double[3][];
		
		this.test_sales[0] = new double[3];
		this.test_sales[1] = new double[4];
		this.test_sales[2] = new double[2];
		
		this.test_sales[0][0] = 500.0;
		this.test_sales[0][1] = 250.0;
		this.test_sales[0][2] = 450.0;
		this.test_sales[1][0] = 50.0;
		this.test_sales[1][1] = 50.0;
		this.test_sales[1][2] = 100.0;
		this.test_sales[1][3] = 300.0;
		this.test_sales[2][0] = 100.0;
		this.test_sales[2][1] = 200.0;
	}
	
	@AfterEach
	void tearDown() throws Exception {
		System.gc();
	}

	@Test
	@DisplayName("Testing getTotal")
	void testGetTotal() {
		final double total = 2000.0;
		assertEquals(
			total, 
			SalesDataUtility.getTotal(this.test_sales), 
			0.0001, 
			"Totals are not within delta to be considered equal."
		);
	}
	
	@Test
	@DisplayName("Testing getAverage")
	void testGetAverage() {
		final double average = ((double) 2000.0) / ((double) 9);
		assertEquals(
			average,
			SalesDataUtility.getAverage(this.test_sales),
			0.0001,
			"Averages are not within delta to be considered equal."
		);
	}
	
	@Test
	@DisplayName("Testing getRowTotal")
	void testGetRowTotal() {
		final double row0_total = 500.0 + 250.0 + 450.0; // Row 0
		final int row5 = 5;
		
		assertAll("row total checks",
			() -> assertEquals(row0_total,
					SalesDataUtility.getRowTotal(this.test_sales, this.row0),
					0.0001,
					"Row totals are not within delta to be considered equal."
			),
			() -> assertEquals(this.failure_value,
					SalesDataUtility.getRowTotal(this.test_sales, this.row_1),
					"Row(-1) totals did not return zero for out-of-bounds index."
			),
			() -> assertEquals(this.failure_value,
					SalesDataUtility.getRowTotal(this.test_sales, row5),
					"Row(5) totals did not return zero for out-of-bounds index."
			),
			() -> assertEquals(this.failure_value,
					SalesDataUtility.getRowTotal(null, this.row0),
					"Null data did not return zero as expected."
			)
		);
		
	}

	@Test
	@DisplayName("Testing getColumnTotal")
	void testGetColumnTotal() {
		final int col0 = 0;
		final int col2 = 2;
		final double col0_total = 500.0 + 50.0 + 100.0; // Col 0
		final double col2_total = 450.0 + 100.0 + 0.0; // Col 2
		final int col5 = 5;
		final int col_1 = -1;
		
		assertAll("column total checks",
			() -> assertEquals(col0_total,
					SalesDataUtility.getColumnTotal(this.test_sales, col0),
					0.0001,
					"Column(0) totals are not within delta to be considered equal."
			),
			() -> assertEquals(col2_total,
					SalesDataUtility.getColumnTotal(this.test_sales, col2),
					0.0001,
					"Column(2) totals are not within delta to be considered equal."
			),
			() -> assertEquals(0,
					SalesDataUtility.getColumnTotal(this.test_sales, col_1),
					"Column(-1) total did not return zero for out-of-bounds index."
			),
			() -> assertEquals(0,
					SalesDataUtility.getColumnTotal(this.test_sales, col5),
					"Column(5) total did not return zero for out-of-bounds index."
			),
			() -> assertEquals(0,
					SalesDataUtility.getColumnTotal(null, col2),
					"Null data did not return zero as expected."
			)
		);
	}
	
	@Test
	@DisplayName("Testing getHighestInRow")
	void testGetHighestInRow() {
		final double row1_max = 300.0;
	
		assertAll("highest checks",
			() -> assertEquals(row1_max, 
					SalesDataUtility.getHighestInRow(this.test_sales, this.row1),
					0.0001,
					"Mismatch between highest in row 1 and expected value of 300.0"
			),
			() -> assertEquals(this.failure_value,
					SalesDataUtility.getHighestInRow(this.test_sales, this.row_1),
					"Row(-1) did not return expected [highest] value of 0.0"
			),
			() -> assertEquals(this.failure_value,
					SalesDataUtility.getHighestInRow(null, this.row_1),
					"Null data did not return expected [highest] value of 0.0"
			)
		);
	}
	
	@Test
	@DisplayName("Testing getLowestInRow")
	void testGetLowestInRow() {
		final double row0_min = 250.0;
		
		assertAll("lowest checks",
			() -> assertEquals(row0_min, 
					SalesDataUtility.getLowestInRow(this.test_sales, this.row0),
					0.0001,
					"Mismatch between lowest in row 0 and expected value of 250.0"
			),
			() -> assertEquals(this.failure_value,
					SalesDataUtility.getLowestInRow(this.test_sales, this.row_1),
					"Row(-1) did not return expected [lowest] value of 0.0"
			),
			() -> assertEquals(this.failure_value,
					SalesDataUtility.getLowestInRow(null, this.row_1),
					"Null data did not return expected [lowest] value of 0.0"
			)
		);
	}
	
	@Test
	@DisplayName("Testing getHighestInArray")
	void testGetHighestInArray() {
		final double overall_max = 500.0;
		
		assertAll("overall highest checks",
			() -> assertEquals(overall_max, 
					SalesDataUtility.getHighestInArray(this.test_sales),
					0.0001,
					"Mismatch between highest value and expected value of 500.0"
			),
			() -> assertEquals(this.failure_value,
					SalesDataUtility.getHighestInArray(null),
					"Null data did not return expected [highest] value of 0.0"
			)
		);
	}
	
	@Test
	@DisplayName("Testing getLowestInArray")
	void testGetLowestInArray() {
		final double overall_min = 50.0;
		
		assertAll("overall lowest checks",
			() -> assertEquals(overall_min, 
					SalesDataUtility.getLowestInArray(this.test_sales),
					0.0001,
					"Mismatch between lowest value and expected value of 50.0"
			),
			() -> assertEquals(this.failure_value,
					SalesDataUtility.getLowestInArray(null),
					"Null data did not return expected [lowest] value of 0.0"
			)
		);
	}
}
