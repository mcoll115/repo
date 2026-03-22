/**
 * Course: CMSC 203 CRN 32324
 * Instructor: Grigoriy Grinberg
 * Description: This a property management program that allows a user to add rental properties
 * and display the management company's complete property portfolio.
 * Due Date: 03/23/2026
 * Platform/Compiler: Eclipse / javac
 * Integrity Pledge:
 * I pledge that I have completed the programming assignment independently. I have not copied
 * the code from a student or any other source. I have not given my code to any student or any
 * other repository (other than as described in the Deliverables for this assignment).
 * Student: Marcus Kemel Collins
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.geometry.*;


/*
 * Driver for property management GUI
 */
public class PropertyAppFX extends Application {
	// the instance for the Management Company
	private String mc_title = new String("Skeleton Key Holdings, LLC");
	private String mc_taxid = new String("IRS-109238");
	private ManagementCompany mgtCompany = new ManagementCompany(this.mc_title, this.mc_taxid);
	
	// user input fields
	private TextField propertyName_txtfld;
	private TextField city_txtfld;
	private TextField rent_txtfld;
	private TextField owner_txtfld;
	
	// display area
	private TextArea info_display_txta;

	//
	@Override
	public void start(Stage primaryStage) {
		try {
			// Values for window dimensions
			final double windowHeight = 800;
			final double windowWidth = 1000;
			final double textfieldWidth = 250;
			
			// Title for window
			String window_title = String.format("CMSC203 %s Property Management GUI", this.mc_title); 
			primaryStage.setTitle(window_title);
			
			// Labels for the user input text fields
			Label propertyName_lbl = new Label("Property Name:  ");
			Label city_lbl = new Label("City: ");
			Label rent_lbl = new Label("Rent ($): ");
			Label owner_lbl = new Label("Owner's Name: ");
			
			// Instantiate property input fields
			this.propertyName_txtfld = new TextField();
			this.city_txtfld = new TextField();
			this.rent_txtfld = new TextField();
			this.owner_txtfld = new TextField();
			
			this.propertyName_txtfld.setPrefWidth(textfieldWidth);
			this.city_txtfld.setPrefWidth(textfieldWidth);
			this.rent_txtfld.setPrefWidth(textfieldWidth);
			this.owner_txtfld.setPrefWidth(textfieldWidth);
			
			// Label for display area
			Label info_display_lbl = new Label("Property Info");
			
			// Instantiate display area (text) and set dimensions & properties
			double displayHeight = 400;
			double displayWidth = 800;
			double displayScroll = 10; // pixels
			
			this.info_display_txta = new TextArea();
			this.info_display_txta.setPrefHeight(displayHeight);
			this.info_display_txta.setPrefWidth(displayWidth);
			this.info_display_txta.setScrollTop(displayScroll);
			this.info_display_txta.setEditable(false);
			this.info_display_txta.setWrapText(true);
			
			
			// Buttons for user interactivity (event handlers add below)
			Button addProperty_btn = new Button("Add Property");
			Button clearFields_btn = new Button("Clear Input");
			Button showPropertyInfo_btn = new Button("Show Property Info");
			Button showTotalRent_btn = new Button("Show Total Rent");
			
			// Layout objects to hold controls, labels, etc.
			/*
			 * +----------------------------------------------------+
			 * | input fields    Add_Property button                |
			 * |                 Clear_Fields button                |
			 * |                                                    |
			 * | Show_Property_Info button   Show_Total_Rent button |
			 * | display text area                                  |
			 * +----------------------------------------------------+ 
			 */
			
			// grid container for overall window layout objects
			GridPane windowGrid = new GridPane();
			
			// grid container for Property input fields and related buttons
			GridPane propertyOverallGrid = new GridPane();
			HBox propertyOverall_hbox = new HBox(20);  // 20 pixels between itself and other objects
			
			// grid container for Property input fields
			GridPane propertyInputGrid = new GridPane();
			
			// grid container for Property input fields' controls
			GridPane propertyControlGrid = new GridPane();
			
			// grid container for display buttons
			GridPane displayButtonsGrid = new GridPane();
			
			// grid container for display area and label
			GridPane displayAreaGrid = new GridPane();
			
			
			// Add labels and input fields to propertyInputGrid
			propertyInputGrid.setVgap(10); // set vertical gap between objects in grid
			propertyInputGrid.add(propertyName_lbl, 0, 0);
			propertyInputGrid.add(this.propertyName_txtfld, 1, 0);
			propertyInputGrid.add(city_lbl, 0, 1);
			propertyInputGrid.add(this.city_txtfld, 1, 1);
			propertyInputGrid.add(rent_lbl, 0, 2);
			propertyInputGrid.add(this.rent_txtfld, 1, 2);
			propertyInputGrid.add(owner_lbl, 0, 3);
			propertyInputGrid.add(this.owner_txtfld, 1, 3);
			
			// Add buttons to propertyControlGrid
			propertyControlGrid.setVgap(10);
			propertyControlGrid.add(addProperty_btn, 1, 1);
			propertyControlGrid.add(clearFields_btn, 1, 3);
			
			// Add property layouts to propertyOverall HBox
			propertyOverall_hbox.setPadding(new Insets(10));  // padding between HBox border and internal objects
			propertyOverall_hbox.setStyle("-fx-border-color: darkblue; -fx-border-width: 4px; -fx-border-radius: 4px;");
			propertyOverall_hbox.setAlignment(Pos.TOP_LEFT);
			
			propertyOverall_hbox.getChildren().add(propertyInputGrid);
			propertyOverall_hbox.getChildren().add(propertyControlGrid);
			
			propertyOverallGrid.add(propertyOverall_hbox, 0, 0);
			
			// Add display buttons to displayButtons HBox & Grid			
			displayButtonsGrid.setHgap(20);
			displayButtonsGrid.add(showPropertyInfo_btn, 8, 0);
			displayButtonsGrid.add(showTotalRent_btn, 9, 0);
			
			
			// Add display area & label to grid
			displayAreaGrid.setVgap(5);
			displayAreaGrid.add(info_display_lbl, 0, 0);
			displayAreaGrid.add(this.info_display_txta, 0, 1);
			
			// Add interactivity to buttons (via Lambda event handlers)
			// Clear input fields button
			clearFields_btn.setOnAction(event -> {
				this.propertyName_txtfld.clear();
				this.city_txtfld.clear();
				this.rent_txtfld.clear();
				this.owner_txtfld.clear();
			});
			
			// Add property to management company
			addProperty_btn.setOnAction(event -> {
				double rent_dbl_conv = Double.parseDouble(this.rent_txtfld.getText());
				
				Property p = new Property(
					this.propertyName_txtfld.getText(),
					this.city_txtfld.getText(),
					rent_dbl_conv,
					this.owner_txtfld.getText()
				);
				
				int p_index = this.mgtCompany.addProperty(p);
				int prop_count = this.mgtCompany.getPropertyCount();
				
				String add_prop_msg = new String("");
				if( p_index == -1 ) {
					add_prop_msg = String.format("\nError (%d): Cannot add new property. Company holdings already full (%d properties).\n", p_index, prop_count);
				}
				else {
					add_prop_msg = String.format("\nAdded new property (at index %d) to company. Now has %d properties.\n", p_index, prop_count);
				}
				
				this.info_display_txta.appendText(add_prop_msg);
			});
			
			// Show management company property info
			showPropertyInfo_btn.setOnAction(event -> {
				String mgt_info_str = new String("\nManagement Properties\n");
				
				mgt_info_str += "=====================\n";
				mgt_info_str += String.format("%s\n", this.mgtCompany.toString());
				
				this.info_display_txta.appendText(mgt_info_str);
			});
			
			// Show the total rent for the management company
			showTotalRent_btn.setOnAction(event -> {
				String total_rent_str = new String("");
				total_rent_str += String.format("\n%s Total Rent: $%,.2f\n", this.mc_title, this.mgtCompany.totalRent());
				
				this.info_display_txta.appendText(total_rent_str);
			});
			
			// Add layout objects/elements to main window grid
			windowGrid.setPadding(new Insets(5));
			windowGrid.setVgap(10);
			windowGrid.add(propertyOverallGrid, 0, 0); // column 0, row 0
			windowGrid.add(displayButtonsGrid, 0, 2);
			windowGrid.add(displayAreaGrid, 0, 4);
			
			// Final setup and display of window
			Scene scene = new Scene(windowGrid, windowWidth, windowHeight);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
