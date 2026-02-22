/**
 * Class: CMSC203
 * Instructor: Grigoriy Grinberg
 * Description: The class that creates the GUI layout and acts as the executable
 * entry point.
 * Due: 02/23/2026
 * Platform/compiler: Eclipse / javac
 * Integrity Pledge:
 * I pledge that I have completed the programming assignment independently. I have not 
 * copied the code from a student or any source. I have not given my code to any
 * student.
 * Name: Marcus Kemel Collins
 */
package application;
	
import javafx.application.Application;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.GridPane;

import javafx.scene.control.*;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;

import javafx.scene.text.*;

import javafx.geometry.*;
/**
 * Application class and interface layout & controls setup
 */
public class PatientDriverApp extends Application {
	private Patient patient;
	
	private Procedure procedure_1, procedure_2, procedure_3;
	
	private TextField name_first_txtfld, name_middle_txtfld, name_last_txtfld;
	
	private TextField patient_phone_txtfld,
					  patient_street_txtfld, patient_city_txtfld, 
					  patient_state_txtfld, patient_zip_txtfld;
	
	private TextField patient_ecname_txtfld, patient_ecphone_txtfld;
	
	private TextField proc_name_1_txtfld, proc_name_2_txtfld, proc_name_3_txtfld;
	private TextField proc_date_1_txtfld, proc_date_2_txtfld, proc_date_3_txtfld;
	private TextField proc_pract_1_txtfld, proc_pract_2_txtfld, proc_pract_3_txtfld;
	private TextField proc_charge_1_txtfld, proc_charge_2_txtfld, proc_charge_3_txtfld;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			//BorderPane root = new BorderPane();
			
			// Layout containers
			GridPane maingrid = new GridPane();
			
			GridPane patient_info_grid = new GridPane();
			GridPane procedure_1_grid = new GridPane();
			GridPane procedure_2_grid = new GridPane();
			GridPane procedure_3_grid = new GridPane();
			
			VBox patient_info_area = new VBox(20); // number is in pixels for gap between itself and others
			HBox procedure_info_area = new HBox(20); 
			VBox procedures_vbox = new VBox(20);
			
			// set padding of N pixels between new Layouts, Panes, Grids added
			maingrid.setHgap(50); // horizontal gap between objects in same row
			maingrid.setVgap(10); // vertical gap between rows
			//
			patient_info_grid.setHgap(5);
			patient_info_grid.setVgap(10);
			//
			procedure_1_grid.setHgap(5);
			procedure_1_grid.setVgap(10);
			//
			procedure_2_grid.setHgap(5);
			procedure_2_grid.setVgap(10);
			//
			procedure_3_grid.setHgap(5);
			procedure_3_grid.setVgap(10);
			
			//----------------------------------------//
			// Patient Information section components //
			//----------------------------------------//
			
			// Patient Info Section Header
			Font patient_infohdr_fontset = Font.font("System", FontWeight.BLACK, 16);
			Label patient_infohdr_lbl = new Label("Patient Info");
			patient_infohdr_lbl.setFont(patient_infohdr_fontset);
			
			// Patient Names (First, Middle, Last)
			Label name_first_lbl = new Label("First Name: ");
			name_first_txtfld = new TextField();
			
			Label name_middle_lbl = new Label("Middle Name: ");
			name_middle_txtfld = new TextField();

			Label name_last_lbl = new Label("Last Name: ");
			name_last_txtfld = new TextField();
			
			// Patient Phone
			Label patient_phone_lbl = new Label("Phone Number: ");
			patient_phone_txtfld = new TextField("000-000-0000");
			
			// Patient Address Info (Street, City, State, ZIP Code)
			Label patient_street_lbl = new Label("Street: ");
			patient_street_txtfld = new TextField();
			
			Label patient_city_lbl = new Label("City: ");
			patient_city_txtfld = new TextField();
			
			Label patient_state_lbl = new Label("State: ");
			patient_state_txtfld = new TextField();
			
			Label patient_zip_lbl = new Label("ZIP Code: ");
			patient_zip_txtfld = new TextField();
			
			// Patient Emergency Contact Info
			Font header_ecinfo_fontset = Font.font("System", FontWeight.NORMAL, 14);
			Label header_ecinfo_lbl = new Label("Emergency Contact");
			header_ecinfo_lbl.setFont(header_ecinfo_fontset);
		
			Label patient_ecname_lbl = new Label("Name: ");
			patient_ecname_txtfld = new TextField();
		
			Label patient_ecphone_lbl = new Label("Phone: ");
			patient_ecphone_txtfld = new TextField("111-111-1111");
			
			// Button to initiate saving of Patient Info
			Button patient_save_btn = new Button("Save Patient");
			
			patient_save_btn.setOnAction(event -> {
				patient = new Patient();
				patient.setNameFirst(name_first_txtfld.getText());
				patient.setNameMiddle(name_middle_txtfld.getText());
				patient.setNameLast(name_last_txtfld.getText());
				patient.setPhoneNumber(patient_phone_txtfld.getText());
				patient.setStreetAddress(patient_street_txtfld.getText());
				patient.setCity(patient_city_txtfld.getText());
				patient.setState(patient_state_txtfld.getText());
				patient.setZipCode(patient_zip_txtfld.getText());
				patient.setECName(patient_ecname_txtfld.getText());
				patient.setECPhoneNumber(patient_ecphone_txtfld.getText());
				// Alternative 1
				// patient = new Patient(name_first_txtfld.getText(), name_middle_txtfld.getText(), ...);
				//   would this Alternative be a memory leak???
			});
			

			// Patient Info Section (Grid) Layout
			patient_info_grid.add(name_first_lbl, 0, 0);    // add to grid column 0, row 0
			patient_info_grid.add(name_first_txtfld, 1, 0); // add to grid column 1, row 0
			patient_info_grid.add(name_middle_lbl, 0, 1);
			patient_info_grid.add(name_middle_txtfld, 1, 1);
			patient_info_grid.add(name_last_lbl, 0, 2);
			patient_info_grid.add(name_last_txtfld, 1, 2);
						
			patient_info_grid.add(patient_phone_lbl, 0, 3);
			patient_info_grid.add(patient_phone_txtfld, 1, 3);
						
			patient_info_grid.add(patient_street_lbl, 0, 4);
			patient_info_grid.add(patient_street_txtfld, 1, 4);
			patient_info_grid.add(patient_city_lbl, 0, 5);
			patient_info_grid.add(patient_city_txtfld, 1, 5);
			patient_info_grid.add(patient_state_lbl, 0, 6);
			patient_info_grid.add(patient_state_txtfld, 1, 6);
			patient_info_grid.add(patient_zip_lbl, 0, 7);
			patient_info_grid.add(patient_zip_txtfld, 1, 7);
			
			patient_info_grid.add(header_ecinfo_lbl, 0, 9);
			
			patient_info_grid.add(patient_ecname_lbl, 0, 10);
			patient_info_grid.add(patient_ecname_txtfld, 1, 10);
			patient_info_grid.add(patient_ecphone_lbl, 0, 11);
			patient_info_grid.add(patient_ecphone_txtfld, 1, 11);
						
			patient_info_grid.add(patient_save_btn, 0, 13);
			
			
			// Patient Info Area setup
			// Add In Order: Header, Info Grid
			patient_info_area.getChildren().add(patient_infohdr_lbl);
			patient_info_area.getChildren().add(patient_info_grid);
			patient_info_area.setAlignment(Pos.CENTER);
			
			patient_info_area.setPadding(new Insets(10));
			patient_info_area.setStyle("-fx-border-color: black; -fx-border-width: 3px; -fx-border-radius: 4px;");
			
			
			//---------------------//
			// Procedure Info Area //
			//---------------------//
			// Procedure Box 1 components
			Label proc_hdr_1_lbl = new Label("Procedure 1");
			
			Label proc_name_1_lbl = new Label("Name: ");
			proc_name_1_txtfld = new TextField();
			
			Label proc_date_1_lbl = new Label("Date: ");
			proc_date_1_txtfld = new TextField("MM/DD/YYYY");
			
			Label proc_pract_1_lbl = new Label("Practitioner: ");
			proc_pract_1_txtfld = new TextField();
			
			Label proc_charge_1_lbl = new Label("Cost: ");
			proc_charge_1_txtfld = new TextField("0.00");
			
			// Button to save procedure 1 information
			Button proc_save_1_btn = new Button("Save 1");
			
			proc_save_1_btn.setOnAction(event -> {				
				procedure_1 = new Procedure(
					proc_name_1_txtfld.getText(),
					proc_date_1_txtfld.getText(),
					proc_pract_1_txtfld.getText(),
					Double.parseDouble(proc_charge_1_txtfld.getText())
				);
			});
			
			// Procedure Box 1 Layout setup
			procedure_1_grid.add(proc_hdr_1_lbl, 0, 0);
			procedure_1_grid.add(proc_name_1_lbl, 0, 2);
			procedure_1_grid.add(proc_name_1_txtfld, 1, 2);
			procedure_1_grid.add(proc_date_1_lbl, 0, 3);
			procedure_1_grid.add(proc_date_1_txtfld, 1, 3);
			procedure_1_grid.add(proc_pract_1_lbl, 0, 4);
			procedure_1_grid.add(proc_pract_1_txtfld, 1, 4);
			procedure_1_grid.add(proc_charge_1_lbl, 0, 5);
			procedure_1_grid.add(proc_charge_1_txtfld, 1, 5);
			procedure_1_grid.add(proc_save_1_btn, 1, 6);
			
			// border using CSS around Procedure 1 box
			procedure_1_grid.setPadding(new Insets(10));
			procedure_1_grid.setStyle("-fx-border-color: darkgrey; -fx-border-width: 2px; -fx-border-radius: 4px;");
			
			
			// Procedure Box 2 components
			Label proc_hdr_2_lbl = new Label("Procedure 2");
			
			Label proc_name_2_lbl = new Label("Name: ");
			proc_name_2_txtfld = new TextField();
			
			Label proc_date_2_lbl = new Label("Date: ");
			proc_date_2_txtfld = new TextField("MM/DD/YYYY");
			
			Label proc_pract_2_lbl = new Label("Practitioner: ");
			proc_pract_2_txtfld = new TextField();
			
			Label proc_charge_2_lbl = new Label("Cost: ");
			proc_charge_2_txtfld = new TextField("0.00");
			
			Button proc_save_2_btn = new Button("Save 2");
			
			proc_save_2_btn.setOnAction(event -> {
				procedure_2 = new Procedure(
					proc_name_2_txtfld.getText(),
					proc_date_2_txtfld.getText(),
					proc_pract_2_txtfld.getText(),
					Double.parseDouble(proc_charge_2_txtfld.getText())
				);
			});
			
			// Procedure Box 2 Layout setup
			procedure_2_grid.add(proc_hdr_2_lbl, 0, 0);
			procedure_2_grid.add(proc_name_2_lbl, 0, 2);
			procedure_2_grid.add(proc_name_2_txtfld, 1, 2);
			procedure_2_grid.add(proc_date_2_lbl, 0, 3);
			procedure_2_grid.add(proc_date_2_txtfld, 1, 3);
			procedure_2_grid.add(proc_pract_2_lbl, 0, 4);
			procedure_2_grid.add(proc_pract_2_txtfld, 1, 4);
			procedure_2_grid.add(proc_charge_2_lbl, 0, 5);
			procedure_2_grid.add(proc_charge_2_txtfld, 1, 5);
			procedure_2_grid.add(proc_save_2_btn, 1, 6);
			
			procedure_2_grid.setPadding(new Insets(10));
			procedure_2_grid.setStyle("-fx-border-color: darkgrey; -fx-border-width: 2px; -fx-border-radius: 4px;");
			
			// Procedure Box 3 components
			Label proc_hdr_3_lbl = new Label("Procedure 3");
			
			Label proc_name_3_lbl = new Label("Name: ");
			proc_name_3_txtfld = new TextField();
			
			Label proc_date_3_lbl = new Label("Date: ");
			proc_date_3_txtfld = new TextField("MM/DD/YYYY");
			
			Label proc_pract_3_lbl = new Label("Practitioner: ");
			proc_pract_3_txtfld = new TextField();
			
			Label proc_charge_3_lbl = new Label("Cost: ");
			proc_charge_3_txtfld = new TextField("0.00");
			
			Button proc_save_3_btn = new Button("Save 3");
			
			proc_save_3_btn.setOnAction(event -> {
				procedure_3 = new Procedure(
					proc_name_3_txtfld.getText(),
					proc_date_3_txtfld.getText(),
					proc_pract_3_txtfld.getText(),
					Double.parseDouble(proc_charge_3_txtfld.getText())
				);
			});
			
			// Procedure Box 3 Layout setup
			procedure_3_grid.add(proc_hdr_3_lbl, 0, 0);
			procedure_3_grid.add(proc_name_3_lbl, 0, 2);
			procedure_3_grid.add(proc_name_3_txtfld, 1, 2);
			procedure_3_grid.add(proc_date_3_lbl, 0, 3);
			procedure_3_grid.add(proc_date_3_txtfld, 1, 3);
			procedure_3_grid.add(proc_pract_3_lbl, 0, 4);
			procedure_2_grid.add(proc_pract_3_txtfld, 1, 4);
			procedure_3_grid.add(proc_charge_3_lbl, 0, 5);
			procedure_3_grid.add(proc_charge_3_txtfld, 1, 5);
			procedure_3_grid.add(proc_save_3_btn, 1, 6);
			
			procedure_3_grid.setPadding(new Insets(10));
			procedure_3_grid.setStyle("-fx-border-color: darkgrey; -fx-border-width: 2px; -fx-border-radius: 4px;");
			
			// Add all procedure boxes to their own info area
			procedure_info_area.getChildren().addAll(procedure_1_grid, procedure_2_grid, procedure_3_grid);
			
			Font procedures_area_hdr_fontset = Font.font("System", FontWeight.BLACK, 16);
			Label procedures_area_hdr_lbl = new Label("Procedures");
			procedures_area_hdr_lbl.setFont(procedures_area_hdr_fontset);
			
			// Add layout components to the Procedures Info Box
			procedures_vbox.getChildren().add(procedures_area_hdr_lbl);
			procedures_vbox.getChildren().add(procedure_info_area);
			procedures_vbox.setAlignment(Pos.CENTER);
			
			
			//----------------------------//
			// Display of output messages //
			//----------------------------//
			Button display_info_btn = new Button("Display Info");
			
			TextArea info_display = new TextArea();
			double info_display_height = 400.0;
			double info_display_width  = 600.0;
			double info_display_vscroll = 10.0;
			
			// Use displayPatient and displayProcedure class methods here
			display_info_btn.setOnAction(event -> {
				info_display.clear(); // reset to nothing in display area
				
				String to_display = "\n"; // setup text to place in display area
				
				// add to text if Patient or Procedures exist
				to_display += "Patient Info:\n";
				if(patient != null) {
					to_display += displayPatient(patient);
					to_display += "\n";
				}
				
				if(procedure_1 != null) {
					to_display += "Procedure 1:\n";
					to_display += displayProcedure(procedure_1);
					to_display += "\n";
				}
				
				if(procedure_2 != null) {
					to_display += "Procedure 2:\n";
					to_display += displayProcedure(procedure_2);
					to_display += "\n";
				}
				
				if(procedure_3 != null) {
					to_display += "Procedure 3:\n";
					to_display += displayProcedure(procedure_3);
					to_display += "\n";
				}
				
				to_display += String.format("Total Procedure Costs: $%,.2f\n", calculateTotalCharges(procedure_1, procedure_2, procedure_3));
				to_display += "\n";
				
				to_display += "The program was developed by a Student: Marcus K Collins 02/23/2026\n";
				
				info_display.setText(to_display);
			});
			
			// Set dimensions of display area and initial text
			info_display.setPrefHeight(info_display_height);
			info_display.setPrefWidth(info_display_width);
			info_display.setText("");
			info_display.setEditable(false);
			info_display.setScrollTop(info_display_vscroll);
			
			// Columns are left-to-right AND Rows are top-to-bottom
			maingrid.setPadding(new Insets(5));
			maingrid.add(patient_info_area, 1, 0);
			maingrid.add(procedures_vbox, 2, 0);
			maingrid.add(display_info_btn, 2, 3);  // column 2, row 3
			maingrid.add(info_display, 2, 5);
			
			//Scene scene = new Scene(root,800,400);
			double mainscene_height = 1000;
			double mainscene_width  = 1300;
			Scene scene = new Scene(maingrid, mainscene_width, mainscene_height);
			
			primaryStage.setTitle("CMSC203 Patient Application!");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Function to calculate the sum of all procedures saved in the GUI
	 * @param proc_1 first procedure to use
	 * @param proc_2 second procedure to use
	 * @param proc_3 third procedure to use
	 * @return the sum of the costs from the procedure arguments entered
	 */
	public static double calculateTotalCharges(Procedure proc_1, Procedure proc_2, Procedure proc_3) {
		double total_charges = 0;
		
		if( proc_1 != null ) { total_charges += proc_1.getProcedureCost(); }
		if( proc_2 != null ) { total_charges += proc_2.getProcedureCost(); }
		if( proc_3 != null ) { total_charges += proc_3.getProcedureCost(); }
		
		return(total_charges);
	}
	
	/**
	 * Function to use Patient class's toString method
	 * @param patient instance of Patient holding information
	 * @return The formatted information from the Patient instance
	 */
	public String displayPatient(Patient patient) {
		if( patient != null ) {
			return( patient.toString() );
		}
		else {
			return "";
		}
	}
	
	/**
	 * Function to use Procedure class's toString method
	 * @param procedure instance of Procedure holding information
	 * @return The formatted information from the Procedure instance
	 */
	public String displayProcedure(Procedure procedure) {
		if( procedure != null ) {
			return( procedure.toString() );
		}
		else {
			return "";
		}
	}
	
	/**
	 * Entry point for the executable
	 * @param args array of Strings holding the command-line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
