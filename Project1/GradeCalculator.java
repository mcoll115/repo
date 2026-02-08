/***
 * Class: CMSC203
 * CRN: 32324
 * Instructor: Grigoriy Grinberg
 * Description: Writes a grade average and summary to a file based on a weighted average calculation.
 * Due: 02/09/2026
 * Platform Compiler: javac
 * Integrity Pledge:
 * I pledge that I have completed the program assignment independently. I have not copied the code
 * from a student or any other source. I have not given my code to any student or any other 
 * repository.
 * Name:  MARCUS KEMEL COLLINS
****/
import java.util.Scanner;
import java.io.*;

public class GradeCalculator {
	public static void main(String[] args) throws IOException {
		/* Grade range cutoffs */
		final double Amin = 90.0, Amax = 100.0;
		final double Bmin = 80.0;
		final double Cmin = 70.0;
		final double Dmin = 60.0;
		
		/* Plus/Minus Grading Cutoffs (pre-calculated by percentage points) */
		final double lowerPct = 30;
		final double upperPct = 30;
		final double Alower = Amin + ((Amax - Amin) * lowerPct / 100);
		final double Aupper = Amax - ((Amax - Amin) * upperPct/ 100);
		
		final double Blower = Bmin + ((Amin - Bmin) * lowerPct / 100);
		final double Bupper = Amin - ((Amin - Bmin) * upperPct / 100);
		
		final double Clower = Cmin + ((Bmin - Cmin) * lowerPct / 100);
		final double Cupper = Bmin - ((Bmin - Cmin) * upperPct / 100);
		
		final double Dlower = Dmin + ((Cmin - Dmin) * lowerPct / 100);
		final double Dupper = Cmin - ((Cmin - Dmin) * upperPct / 100);
		
		/* Default configuration values for grading & course title */
		boolean config_defaults_used = true;
		String categories_weights_default = "Projects,40; Quizzes,30; Exams,30;";
		int categories_present = 3;
		String course_title = "CMSC203 Computer Science I";
		
		/* Create variables to hold default filenames - Input & Output */
		String configfile_default = "gradeconfig.txt";
		String inputfile_default = "grades_input.txt";
		String outputfile_default = "grades_report.txt";
		
		/* Storage for Input & Output filename */
		String input_filename = inputfile_default;
		String output_filename = outputfile_default;
		
		if( args.length == 0 ) {
			/* Continue as expected using default values, input, output, etc. */
		}
		else if( args.length == 1 ) {
			input_filename = args[0];
		}
		else if( args.length == 2 ) {
			input_filename = args[0];
			output_filename = args[1];
		}
		
		/* Storage for final category weights string */
		String categories_weights = "";
		File configname = new File(configfile_default);
		if( configname.exists() ) {
			int category_weight_sum = 0;
			try(Scanner file_config = new Scanner(configname)) {
				course_title = file_config.nextLine();
				categories_present = file_config.nextInt();
				categories_weights = "";
				
				// Process the configuration file line-by-line (with specific format & structure)
				for(int line_counter = 0; line_counter < categories_present; ++line_counter) {
					String config_cat = file_config.next();
					String config_weight = file_config.next();
					categories_weights += config_cat + "," + config_weight + "; ";
					file_config.nextLine();
					category_weight_sum += Integer.valueOf(config_weight);
				}
			}
			
			// Set to false before checking the weight sum for correctness
			config_defaults_used = false;
			
			if( category_weight_sum != 100 ) {
				config_defaults_used = true;
				categories_weights = categories_weights_default;
				System.out.println("WARNING: category weights entered do not sum to 100. Using default weights ==>");
				System.out.println(categories_weights);
			}
		}
		else {
			categories_weights = categories_weights_default;
		}// end IF-ELSE(configname exists)
		
		// Create object to reference for writing to the summary output file
		File filename_in = new File(input_filename);
		FileWriter filename_out = new FileWriter(output_filename, false);
		
		if( filename_in.exists() ) {
			// Print a welcome banner to screen //
			System.out.println("\n\t\tWelcome to " + course_title + "!\n");
			
			// Temporary line storage
			String line;
			int lineNumber = 0;
			
			// Storage for student's name
			String name_last = "", name_first = "", name_full = "";
			
			// Storage for category, number of grades, each grade, category grades summation, category_average
			String category = "";
			int number_of_grades = 0;
			double category_grades_sum = 0;
			double category_grades_average = 0;
			double weighted_category_average = 0;
			double weighted_overall_average = 0;
			
			try( Scanner file_in = new Scanner(filename_in); PrintWriter file_out = new PrintWriter(filename_out); ) {
				// Print course title to file
				file_out.println("Course Title: " + course_title);
				
				// Read input file line-by-line
				while( file_in.hasNext() ) {
					line = file_in.nextLine();
					if( lineNumber == 0 ) {
						name_first = line;
						++lineNumber;
					}
					else if( lineNumber == 1 ) {
						name_last = line;
						
						// Create string with student's full name
						name_full = name_first + " " + name_last;
						System.out.println("Student Name: " + name_full); // print to screen
						file_out.println("Student Name: " + name_full + "\n");  // print to file
						++lineNumber;
					}
					else if( lineNumber > 1 ) {
						category = line;
						
						if( !categories_weights.contains(category) ) {
							// Skipping lines of file if the category is not valid (by configuration standards)
							System.out.println("WARNING: Category not present in configuration (skipping lines): " + category);
							file_in.nextLine();
							file_in.nextLine();
							lineNumber += 3;
						}
						else {
							int catIndex = categories_weights.indexOf(category);
							int commaIndex = categories_weights.indexOf(",", catIndex);
							int semicolonIndex = categories_weights.indexOf(";", commaIndex);
							
							double curr_cat_weight = (double) Integer.valueOf(
									categories_weights.substring(commaIndex+1, semicolonIndex));
							
							System.out.println("category: " + category);
							System.out.println(category + " weight (%): " + curr_cat_weight);
							
							category_grades_sum = 0;
							number_of_grades = file_in.nextInt();
							for(int count = 0; count < number_of_grades; ++count) {
								category_grades_sum += file_in.nextDouble();
							}
							file_in.nextLine(); // discard newline character after reading .nextDouble()
							
							System.out.print(category + " Average: ");
							if( number_of_grades > 0 ) {
								// Make sure not to divide by 0 (zero)
								category_grades_average = category_grades_sum / number_of_grades;
								System.out.println(category_grades_average);
							}
							else {
								System.out.println("0");
							}
							System.out.println("");
							file_out.println("Category: " + category + " | Weight: " + curr_cat_weight + "% | Average: " + category_grades_average);
							lineNumber += 3;
							
							// Weighted average for current category
							weighted_category_average = (category_grades_average * curr_cat_weight) / 100;
							System.out.println(category + " (weighted) Average: " + weighted_category_average);
							
							// Overall weighted average
							weighted_overall_average += weighted_category_average;
						}
					}// end IF-ELSE-IFs(lineNumber)
				}
				System.out.println("\nOverall (weighted) Average: " + weighted_overall_average);
				file_out.println("\nOverall (weighted) Average: " + weighted_overall_average);
				
				// Set a letter grade based on weighted overall average
				char base_letter_grade = 'G';
				
				if( weighted_overall_average >= Amin ) {
					base_letter_grade = 'A';
				} // A range
				else if( weighted_overall_average >= Bmin ) {
					base_letter_grade = 'B';
				} // B range
				else if( weighted_overall_average >= Cmin )  {
					base_letter_grade = 'C';
				} // C range
				else if( weighted_overall_average >= Dmin ) {
					base_letter_grade = 'D';
				} // D range
				else {
					base_letter_grade = 'F';
				} // F range
				
				System.out.println("Letter grade: " + base_letter_grade + "\n");
				
				// Ask user about +/- grading using a WHILE loop
				boolean use_plus_minus = false;
				boolean plus_minus_answer_valid = false;
				String plus_minus_answer = "";
				Scanner kbd_input = new Scanner(System.in);
				
				do {
					System.out.print("Apply +/- grading? (Y/N): ");
					plus_minus_answer = kbd_input.nextLine();
					
					if( (plus_minus_answer.equals("Y")) || 
						(plus_minus_answer.equals("y")) ) 
					{
						plus_minus_answer_valid = true;
						use_plus_minus = true;
					}
					else if( (plus_minus_answer.equals("N")) || 
							 (plus_minus_answer.equals("n")) ) 
					{
						plus_minus_answer_valid = true;
						use_plus_minus = false;
					}
				}
				while( !plus_minus_answer_valid );
				
				kbd_input.close(); // keyboard input is no longer needed
				
				// Print extra information to console if using plus/minus grading scale
				if( use_plus_minus ) {
					// Print grade ranges
					System.out.println("\nGrading will change as follows:");
					System.out.print("\tA- ==> " + Amin + " to " + Alower);
					System.out.println(" | A+ ==> " + Aupper + " to " + Amax);
					System.out.print("\tB- ==> " + Bmin + " to " + Blower);
					System.out.println(" | B+ ==> " + Bupper + " to " + Amin);
					System.out.print("\tC- ==> " + Cmin + " to " + Clower);
					System.out.println(" | C+ ==> " + Cupper + " to " + Bmin);
					System.out.print("\tD- ==> " + Dmin + " to " + Dlower);
					System.out.println(" | D+ ==> " + Dupper + " to " + Cmin);
					System.out.println("\tF  ==> has no +/- range!\n");
					
					switch(base_letter_grade) {
						case 'A':
							if( (weighted_overall_average >= Amin) && (weighted_overall_average <= Alower) ) {
								System.out.println("Letter grade +/-: A-");
								file_out.println("Letter grade +/-: A-");
							}
							else if( (weighted_overall_average >= Aupper) && (weighted_overall_average <= Amax) ) {
								System.out.println("Letter grade +/-: A+");
								file_out.println("Letter grade +/-: A+");
							}
							break;
						case 'B':
							if( (weighted_overall_average >= Bmin) && (weighted_overall_average <= Blower) ) {
								System.out.println("Letter grade +/-: B-");
								file_out.println("Letter grade +/-: B-");
							}
							else if( (weighted_overall_average >= Bupper) && (weighted_overall_average < Amin) ) {
								System.out.println("Letter grade +/-: B+");
								file_out.println("Letter grade +/-: B+");
							}
							break;
						case 'C':
							if( (weighted_overall_average >= Cmin) && (weighted_overall_average <= Clower) ) {
								System.out.println("Letter grade +/-: C-");
								file_out.println("Letter grade +/-: C-");
							}
							else if( (weighted_overall_average >= Cupper) && (weighted_overall_average < Bmin) ) {
								System.out.println("Letter grade +/-: C+");
								file_out.println("Letter grade +/-: C+");
							}
							break;
						case 'D':
							if( (weighted_overall_average >= Dmin) && (weighted_overall_average <= Dlower) ) {
								System.out.println("Letter grade +/-: D-");
								file_out.println("Letter grade +/-: D-");
							}
							else if( (weighted_overall_average >= Dupper) && (weighted_overall_average < Cmin) ) {
								System.out.println("Letter grade +/-: D+");
								file_out.println("Letter grade +/-: D+");
							}
							break;
						case 'F':
							// print message to file .println("Letter grade +/-: F");
							file_out.println("Letter grade +/-: F");
							break;
						default:
							System.out.println("ERROR: Invalid letter grade encountered. Continuing report...");
							break;
					}// end SWITCH(letter grade)		
				}
				else {
					file_out.println("\nLetter Grade: " + base_letter_grade);
				}// end IF-ELSE(using plus/minus grading)
				
				if( config_defaults_used ) {
					file_out.println("\nConfiguration default values were used.");
				}
				else {
					file_out.println("\nConfiguration values read from: " + configfile_default);
				}
				
				file_out.println("\nInput grades read from:    " + input_filename);
				file_out.println("Grade summary written to:  " + output_filename);
			}// end try-resource
		}
		else {
			System.out.println("ERROR: File \"" + filename_in.getName() + "\" not found!");
		}// end IF-ELSE(grades file exists)
	}// end MAIN()
}