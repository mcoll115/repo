


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * This panel is the basic panel, inside which other panels are placed.  
 * Before beginning to implement, design the structure of your GUI in order to 
 * understand what panels go inside which ones, and what buttons or other components
 * go in which panels.  
 * @author ralexander
 *
 */
//make the main panel's layout be a VBox
public class FXMainPane extends VBox {

	//student Task #2:
	//  declare five buttons, a label, and a textfield
	private Button hello_btn, howdy_btn, chinese_btn, clear_btn, exit_btn;
	
	private Label feedback_lbl;
	
	private TextField feedback_txtfld;
	
	//  declare two HBoxes
	private HBox buttons_hbox, feedback_hbox;
	
	//student Task #4:
	//  declare an instance of DataManager
	private DataManager data_mgr;
	
	/**
	 * The MainPanel constructor sets up the entire GUI in this approach.  Remember to
	 * wait to add a component to its containing component until the container has
	 * been created.  This is the only constraint on the order in which the following 
	 * statements appear.
	 */
	FXMainPane() {
		//student Task #2:
		//  instantiate the buttons, label, and textfield
		hello_btn = new Button("Hello");
		howdy_btn = new Button("Howdy");
		chinese_btn = new Button("Chinese");
		clear_btn = new Button("Clear");
		exit_btn = new Button("Exit");
		
		feedback_lbl = new Label("Feedback:");

		feedback_txtfld = new TextField("");
		
		//  instantiate the HBoxes
		buttons_hbox = new HBox();
		feedback_hbox = new HBox();
		
		//student Task #4:
		//  instantiate the DataManager instance
		data_mgr = new DataManager();
		
		// register event-handler with buttons
		hello_btn.setOnAction(new ButtonHandler());
		howdy_btn.setOnAction(new ButtonHandler());
		chinese_btn.setOnAction(new ButtonHandler());
		clear_btn.setOnAction(new ButtonHandler());
		exit_btn.setOnAction(new ButtonHandler());
		
		//  set margins and set alignment of the components
		Insets myspacing = new Insets(5);
		HBox.setMargin(hello_btn, myspacing);
		HBox.setMargin(howdy_btn, myspacing);
		HBox.setMargin(chinese_btn, myspacing);
		HBox.setMargin(clear_btn, myspacing);
		HBox.setMargin(exit_btn, myspacing);
		
		HBox.setMargin(feedback_lbl, myspacing);
		HBox.setMargin(feedback_txtfld, myspacing);
		
		// set alignment to center position within window
		buttons_hbox.setAlignment(Pos.CENTER);
		feedback_hbox.setAlignment(Pos.CENTER);
		
		//student Task #3:
		//  add the label and textfield to one of the HBoxes
		feedback_hbox.getChildren().addAll(feedback_lbl, feedback_txtfld);
		
		//  add the buttons to the other HBox
		buttons_hbox.getChildren().addAll(hello_btn, howdy_btn, chinese_btn, clear_btn, exit_btn);
		
		//  add the HBoxes to this FXMainPanel (a VBox)
		getChildren().addAll(buttons_hbox, feedback_hbox);
		
	}
	
	//Task #4:
	//  create a private inner class to handle the button clicks
	class ButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			if(event.getSource() == hello_btn) {
				feedback_txtfld.setText(data_mgr.getHello());
			}
			else if( event.getSource() == howdy_btn ) {
				feedback_txtfld.setText(data_mgr.getHowdy());
			}
			else if( event.getSource() == chinese_btn ) {
				feedback_txtfld.setText(data_mgr.getChinese());
			}
			else if( event.getSource() == clear_btn ) {
				feedback_txtfld.setText("");
			}
			else if( event.getSource() == exit_btn ) {
				Platform.exit();
				System.exit(0);
			}
			
			// After looking at the console results and the javafx documentation
			//   The target is replaced with the source object id if not set during Event object creation
			//System.out.println("Source: ");
			//System.out.println(event.getSource());
			//System.out.println("Target: ");
			//System.out.println(event.getTarget());
		}
	}
}
	
