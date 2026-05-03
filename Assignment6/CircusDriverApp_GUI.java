/*
 * Class: CMSC203 CRN 32324
 * Instructor: Grigoriy Grinberg
 * Description:
 * This is a program to make the user interface to setup Bob’s Circus for users to buy tickets, 
 * view the animals within the circus and the buildings within the circus.
 * Due: 05/04/2026
 * Platform/compiler: Eclipse / javac
 * Integrity Pledge:
 * I pledge that I have completed the programming assignment independently. 
 * I have not copied the code from a student or any source. 
 * I have not given my code to any student.
 * Name: Marcus Kemel Collins
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CircusDriverApp_GUI extends Application {

    private Circus circus = new Circus();
    private VBox animalListBox;
    private TextArea outputArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        stage.setTitle("Bob's Circus Management System");

        // ================= OUTPUT AREA =================
        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setPrefHeight(770);

        // ================= MENU BUTTONS =================
        Button addAnimalBtn = new Button("Add Animal");
        Button addPersonBtn = new Button("Add Person");
        Button addBuildingBtn = new Button("Add Building");
        Button ticketBtn = new Button("Generate Ticket");

        Button displayAnimalsBtn = new Button("Display All Animals");
        Button displayPersonsBtn = new Button("Display All Persons");
        Button displayBuildingsBtn = new Button("Display All Buildings");

        Button sortAgeBtn = new Button("Sort Animals by Age");
        Button sortNameBtn = new Button("Sort Animals by Name");
        Button searchBtn = new Button("Search Animal by Name");

        Button exitBtn = new Button("Exit");

        FlowPane menu = new FlowPane(10, 10,
                addAnimalBtn, addPersonBtn, addBuildingBtn, ticketBtn,
                displayAnimalsBtn, displayPersonsBtn, displayBuildingsBtn,
                sortAgeBtn, sortNameBtn, searchBtn, exitBtn
        );
        menu.setAlignment(Pos.CENTER);

        VBox leftPanel = new VBox(10, menu, outputArea);
        leftPanel.setPrefWidth(600);

        // ================= ANIMAL DISPLAY =================
        animalListBox = new VBox(8);
        animalListBox.setPadding(new Insets(10));

        ScrollPane animalScrollPane = new ScrollPane(animalListBox);
        animalScrollPane.setFitToWidth(true);
        animalScrollPane.setPrefHeight(800);

        VBox animalPanel = new VBox(8,
                new Label("Image + Details"),
                animalScrollPane
        );
        animalPanel.setPrefHeight(850);
        animalPanel.setPadding(new Insets(10));
        animalPanel.setStyle("-fx-border-color: gray;");

        // ================= ROOT =================
        HBox root = new HBox(15, leftPanel, animalPanel);
        root.setPadding(new Insets(15));

        stage.setScene(new Scene(root, 800, 600));
        stage.show();

        // ================= ACTIONS =================
        addAnimalBtn.setOnAction(e -> handleAddAnimal());
        addPersonBtn.setOnAction(e -> handleAddPerson());
        addBuildingBtn.setOnAction(e -> handleAddBuilding());
        ticketBtn.setOnAction(e -> handleGenerateTicket());

        displayAnimalsBtn.setOnAction(e -> {
        	outputArea.clear();
            displayAnimalsWithImages();
            //outputArea.setText(captureConsole(circus::displayAllAnimals));
            outputArea.setText(circus.displayAllAnimals());
        });

        displayPersonsBtn.setOnAction(e -> {
        	outputArea.clear();
        	displayPersonsWithImages();
        	outputArea.setText(circus.displayAllPersons());
        });

        displayBuildingsBtn.setOnAction(e -> {
            outputArea.clear();
            displayBuildingsWithImages();
            outputArea.setText(circus.displayAllBuildings());
        });

        sortAgeBtn.setOnAction(e -> circus.sortAnimalsByAge());
        sortNameBtn.setOnAction(e -> circus.sortAnimalsByName());
        searchBtn.setOnAction(e -> handleSearchAnimal());
        exitBtn.setOnAction(e -> stage.close());
    }

    // ==================== TICKET GENERATION ====================
    private void handleGenerateTicket() {

        animalListBox.getChildren().clear();

        StringBuilder allDetails = new StringBuilder();
        allDetails.append("Ticket Calculation Details:\n");
        allDetails.append("---------------------------\n");

        boolean moreTickets = true;

        while (moreTickets) {

            List<String> dayChoices = new ArrayList<>();
            for (DayOfWeek d : DayOfWeek.values()) {
                int pct = (int) (d.getDiscount() * 100);
                dayChoices.add(d.name().charAt(0) + d.name().substring(1).toLowerCase()
                        + " (" + pct + "% discount)");
            }

            ChoiceDialog<String> dayDialog = new ChoiceDialog<>(dayChoices.get(0), dayChoices);
            dayDialog.setHeaderText("Select Day of Week");
            Optional<String> dayOpt = dayDialog.showAndWait();
            if (dayOpt.isEmpty()) break;

            String selectedDay = dayOpt.get();
            String day = selectedDay.split(" ")[0];

            List<String> customerChoices = List.of(
                    "Child (10% discount)",
                    "Student (10% discount)",
                    "Adult",
                    "Senior (5% discount)"
            );

            ChoiceDialog<String> customerDialog = new ChoiceDialog<>(customerChoices.get(0), customerChoices);
            customerDialog.setHeaderText("Select Customer Type");
            Optional<String> customerOpt = customerDialog.showAndWait();
            if (customerOpt.isEmpty()) break;

            String customerTypeSelected = customerOpt.get();

            double customerDiscount = switch (customerTypeSelected) {
                case "Child (10% discount)", "Student (10% discount)" -> 0.10;
                case "Senior (5% discount)" -> 0.05;
                default -> 0.0;
            };

            List<String> seatChoices = List.of(
                    "Lower Level (no discount)",
                    "T-Level (5% discount) ",
                    "Upper Level (10% discount)"
            );

            ChoiceDialog<String> seatDialog = new ChoiceDialog<>(seatChoices.get(0), seatChoices);
            seatDialog.setHeaderText("Select Seat Level");
            Optional<String> seatOpt = seatDialog.showAndWait();
            if (seatOpt.isEmpty()) break;

            String seatLevel = seatOpt.get();

            // Dialogs to gather base price and tickets
            String ticket_dialog_title = seatLevel + " Details";
            
            // text input dialog for the following fields
            TextInputDialog price_dialog = new TextInputDialog();
            TextInputDialog ticket_dialog = new TextInputDialog();
            
            // storage for dialog strings entered
            String[] dialog_storage = {"", ""};
            
            // Get base ticket price
            price_dialog.setTitle(ticket_dialog_title);
            price_dialog.setHeaderText("Enter base price: ");
            price_dialog.showAndWait().filter(s -> !s.isEmpty()).ifPresent(price_entered -> {
            	dialog_storage[0] = price_entered.replace(",", "");
            });
            double basePrice = Double.parseDouble(dialog_storage[0]);
            price_dialog.getEditor().clear();
            
            // Get number of tickets
            ticket_dialog.setTitle(ticket_dialog_title);
            ticket_dialog.setHeaderText("Enter number of tickets: ");
            ticket_dialog.showAndWait().filter(s -> !s.isEmpty()).ifPresent(tickets_entered -> {
            	dialog_storage[1] = tickets_entered;
            });
            int ticketCount = Integer.parseInt(dialog_storage[1]);
            ticket_dialog.getEditor().clear();

            double singleTicketPrice = calculateTicketPrice(day, basePrice, seatLevel, customerDiscount);
            double totalPrice = singleTicketPrice * ticketCount;

            allDetails.append(getSingleTicketDetails(day, basePrice, seatLevel, customerDiscount));
            allDetails.append(String.format("Number of Tickets: %d%n", ticketCount));
            allDetails.append(String.format("Total Price: $%.2f%n", totalPrice));
            allDetails.append("---------------------------\n");

            Alert moreAlert = new Alert(Alert.AlertType.CONFIRMATION);
            moreAlert.setHeaderText("Do you want to buy more tickets?");
            moreAlert.setContentText("Click OK for Yes, Cancel for No");

            Optional<ButtonType> moreOpt = moreAlert.showAndWait();
            moreTickets = moreOpt.isPresent() && moreOpt.get() == ButtonType.OK;
        }

        allDetails.append("\nEnjoy the show!");
        outputArea.setText(allDetails.toString());
    }


    private void handleAddAnimal() {
        ChoiceDialog<String> dialog =
                new ChoiceDialog<>("Dog", "Dog", "Horse", "Bird", "Lion");
        dialog.setHeaderText("Select Animal Type");
        Optional<String> type = dialog.showAndWait();
        if (type.isEmpty()) return;
        
        // text input dialog for the following fields
        TextInputDialog text_dialog = new TextInputDialog();
        
        String animal_dialog_title = "Animal Details";
        
        // storage for dialog strings entered
        String[] dialog_storage = {""};
        
        // Getting animal details
		
        // Get animal name
        text_dialog.setTitle(animal_dialog_title);
        text_dialog.setHeaderText("Enter animal name: ");
        text_dialog.showAndWait().filter(s -> !s.isEmpty()).ifPresent(name_entered -> {
        	dialog_storage[0] = name_entered;
        });
        String name = dialog_storage[0];
        text_dialog.getEditor().clear();
        
        // Get animal age
        text_dialog.setTitle(animal_dialog_title);
        text_dialog.setHeaderText("Enter animal age: ");
        text_dialog.showAndWait().filter(s -> !s.isEmpty()).ifPresent(age_entered -> {
        	dialog_storage[0] = age_entered;
        });
        int age = Integer.parseInt(dialog_storage[0]);
        text_dialog.getEditor().clear();
        
        // Get animal species
        text_dialog.setTitle(animal_dialog_title);
        text_dialog.setHeaderText("Enter animal species: ");
        text_dialog.showAndWait().filter(s -> !s.isEmpty()).ifPresent(species_entered -> {
        	dialog_storage[0] = species_entered;
        });
        String species = dialog_storage[0];
        text_dialog.getEditor().clear();
        
        // Get animal color
        text_dialog.setTitle(animal_dialog_title);
        text_dialog.setHeaderText("Enter animal color: ");
        text_dialog.showAndWait().filter(s -> !s.isEmpty()).ifPresent(color_entered -> {
        	dialog_storage[0] = color_entered;
        });
        String color = dialog_storage[0];
        text_dialog.getEditor().clear();
        
        // Get animal image path
        text_dialog.setTitle(animal_dialog_title);
        text_dialog.setHeaderText("Enter animal image path: ");
        text_dialog.showAndWait().filter(s -> !s.isEmpty()).ifPresent(imgpath_entered -> {
        	dialog_storage[0] = imgpath_entered;
        });
        String imagePath = dialog_storage[0];
        text_dialog.getEditor().clear();
        
        Animal animal = switch (type.get()) {
            case "Dog" -> new Dog(name, age, species, color, imagePath);
            case "Horse" -> new Horse(name, age, species, color, imagePath);
            case "Bird" -> new Bird(name, age, species, color, imagePath);
            case "Lion" -> new Lion(name, age, species, color, imagePath);
            default -> null;
        };

        if (animal != null) circus.addAnimal(animal);
        
        return;
    }

    
    private void handleAddPerson() {
    	ChoiceDialog<String> dialog =
                new ChoiceDialog<>("Clerk", "Clerk", "Acrobat");
        dialog.setHeaderText("Select Person Type");
        Optional<String> type = dialog.showAndWait();
        if (type.isEmpty()) return;
        
        // text input dialog for the following fields
        TextInputDialog text_dialog = new TextInputDialog();
        
        // storage for dialog strings entered
        String[] dialog_storage = {""};
        
        // Getting animal details
        String person_dialog_title = "Person Details";
		
        // Get person name
        text_dialog.setTitle(person_dialog_title);
        text_dialog.setHeaderText("Enter name: ");
        text_dialog.showAndWait().filter(s -> !s.isEmpty()).ifPresent(name_entered -> {
        	dialog_storage[0] = name_entered;
        });
        String name = dialog_storage[0];
        text_dialog.getEditor().clear();
        
        // Get person age
        text_dialog.setTitle(person_dialog_title);
        text_dialog.setHeaderText("Enter age: ");
        text_dialog.showAndWait().filter(s -> !s.isEmpty()).ifPresent(age_entered -> {
        	dialog_storage[0] = age_entered;
        });
        int age = Integer.parseInt(dialog_storage[0]);
        text_dialog.getEditor().clear();
        
        // Get person years worked
        text_dialog.setTitle(person_dialog_title);
        text_dialog.setHeaderText("Enter years worked: ");
        text_dialog.showAndWait().filter(s -> !s.isEmpty()).ifPresent(yw_entered -> {
        	dialog_storage[0] = yw_entered;
        });
        int years_worked = Integer.parseInt(dialog_storage[0]);
        text_dialog.getEditor().clear();
        
        // Get person job
        text_dialog.setTitle(person_dialog_title);
        text_dialog.setHeaderText("Enter job: ");
        text_dialog.showAndWait().filter(s -> !s.isEmpty()).ifPresent(job_entered -> {
        	dialog_storage[0] = job_entered;
        });
        String job = dialog_storage[0];
        text_dialog.getEditor().clear();
        
        // Get person image path
        text_dialog.setTitle(person_dialog_title);
        text_dialog.setHeaderText("Enter person image path: ");
        text_dialog.showAndWait().filter(s -> !s.isEmpty()).ifPresent(imgpath_entered -> {
        	dialog_storage[0] = imgpath_entered;
        });
        String imagePath = dialog_storage[0];
        text_dialog.getEditor().clear();
        
        Person person = switch (type.get()) {
            case "Clerk" -> new Clerk(name, age, years_worked, job, imagePath);
            case "Acrobat" -> new Acrobatic(name, age, years_worked, job, imagePath);
            default -> null;
        };

        if (person != null) circus.addPerson(person);
        
    	return;
    }

    
    private void handleAddBuilding() {
    	ChoiceDialog<String> dialog =
                new ChoiceDialog<>("Arena", "Arena", "Ticketing Office");
        dialog.setHeaderText("Select Building Type");
        Optional<String> type = dialog.showAndWait();
        if (type.isEmpty()) return;
        
        String building_dialog_title = "Building Details";
        
        // text input dialog for the following fields
        TextInputDialog text_dialog = new TextInputDialog();
        
        // storage for dialog strings entered
        String[] dialog_storage = {""};
        
        // Getting building details
		
        // Get building name
        text_dialog.setTitle(building_dialog_title);
        text_dialog.setHeaderText("Enter building color: ");
        text_dialog.showAndWait().filter(s -> !s.isEmpty()).ifPresent(color_entered -> {
        	dialog_storage[0] = color_entered;
        });
        String color = dialog_storage[0];
        text_dialog.getEditor().clear();
        
        // Get building length
        text_dialog.setTitle(building_dialog_title);
        text_dialog.setHeaderText("Enter building length: ");
        text_dialog.showAndWait().filter(s -> !s.isEmpty()).ifPresent(length_entered -> {
        	dialog_storage[0] = length_entered.replace(",", "");
        });
        double length = Double.parseDouble(dialog_storage[0]);
        text_dialog.getEditor().clear();
        
        // Get building width
        text_dialog.setTitle(building_dialog_title);
        text_dialog.setHeaderText("Enter building width: ");
        text_dialog.showAndWait().filter(s -> !s.isEmpty()).ifPresent(width_entered -> {
        	dialog_storage[0] = width_entered.replace(",", "");
        });
        double width = Double.parseDouble(dialog_storage[0]);
        text_dialog.getEditor().clear();
        
        // Get building image path
        text_dialog.setTitle(building_dialog_title);
        text_dialog.setHeaderText("Enter building image path: ");
        text_dialog.showAndWait().filter(s -> !s.isEmpty()).ifPresent(imgpath_entered -> {
        	dialog_storage[0] = imgpath_entered;
        });
        String imagePath = dialog_storage[0];
        text_dialog.getEditor().clear();
        
        Building building = switch (type.get()) {
            case "Arena" -> new Arena(color, length, width, imagePath);
            case "Ticketing Office" -> new TicketingOffice(color, length, width, imagePath);
            default -> null;
        };

        if (building != null) circus.addBuilding(building);
        
        return;
    }

    
    private void displayAnimalsWithImages() {
    	if( circus.getAnimals().size() == 0 ) {
    		return;
    	}
    	
    	animalListBox.getChildren().clear();
    	
    	for(Animal a : circus.getAnimals()) {
    		try {
    			//System.out.println("image url == " + a.getImagePath());
    			File file = new File(a.getImagePath());
    			//System.out.println("image file exists == " + file.exists());
    			FileInputStream filestream = new FileInputStream(file);
    			//Image curr_image = new Image(a.getImagePath());
    			Image curr_image = new Image(filestream);
    			ImageView imageview = new ImageView(curr_image);
    			imageview.setFitHeight(150.0);
    			imageview.setFitWidth(200.0);
    			imageview.setPreserveRatio(true);
    			
    			Label imagecaption = new Label("Name: " + a.getName());
    			
    			animalListBox.getChildren().addAll(imageview, imagecaption);
    		}
    		catch(FileNotFoundException e) {
    			System.out.println("error: " + e.getMessage());
    		}
    		catch(IllegalArgumentException e) {
    			System.out.println("error: " + e.getMessage());
    		}
    	}
        return;  
    }

    
    private void displayPersonsWithImages() {
    	if( circus.getPersons().size() == 0 ) {
    		return;
    	}
    	
    	animalListBox.getChildren().clear();
    	
    	for(Person p : circus.getPersons()) {
    		try {
    			File file = new File(p.getImagePath());
    			FileInputStream filestream = new FileInputStream(file);
    			
    			Image curr_image = new Image(filestream);
    			ImageView imageview = new ImageView(curr_image);
    			imageview.setFitHeight(150.0);
    			imageview.setFitWidth(200.0);
    			imageview.setPreserveRatio(true);
    			
    			Label imagecaption = new Label("Name: " + p.getName());
    			
    			animalListBox.getChildren().addAll(imageview, imagecaption);
    		}
    		catch(FileNotFoundException e) {
    			System.out.println("error: " + e.getMessage());
    		}
    		catch(IllegalArgumentException e) {
    			System.out.println("error: " + e.getMessage());
    		}
    	}
        return;
    }

    
    private void displayBuildingsWithImages() {
    	if( circus.getBuildings().size() == 0 ) {
    		return;
    	}
    	
    	animalListBox.getChildren().clear();
    	
    	for(Building b : circus.getBuildings()) {
    		try {
    			File file = new File(b.getImagePath());
    			FileInputStream filestream = new FileInputStream(file);
    			
    			Image curr_image = new Image(filestream);
    			ImageView imageview = new ImageView(curr_image);
    			imageview.setFitHeight(150.0);
    			imageview.setFitWidth(200.0);
    			imageview.setPreserveRatio(true);
    			
    			Label imagecaption = new Label("Type: " + b.getBuildingType());
    			
    			animalListBox.getChildren().addAll(imageview, imagecaption);
    		}
    		catch(FileNotFoundException e) {
    			System.out.println("error: " + e.getMessage());
    		}
    		catch(IllegalArgumentException e) {
    			System.out.println("error: " + e.getMessage());
    		}
    	}
        return;
    }
    
    private void handleSearchAnimal() {
        TextInputDialog text_dialog = new TextInputDialog();
    	
    	String[] dialog_storage = {""};
    	
    	text_dialog.setTitle("Animal Search");
    	text_dialog.setHeaderText("Enter name to search: ");
    	text_dialog.showAndWait().filter(s -> !s.isEmpty()).ifPresent(name_entered -> {
        	dialog_storage[0] = name_entered;
        });
        String search_name = dialog_storage[0];
        text_dialog.getEditor().clear();
        
        Animal found_animal = circus.searchAnimalsByName(search_name);
    	
        if( found_animal != null ) {
        	outputArea.clear();
        	outputArea.setText("Found animal!\n" + found_animal.toString());
        }
    	return;
    }

    
    private double calculateTicketPrice(String dayOfWeek, double basePrice, String seatLevel, double customerDiscount) {
    	double price_total = 0.0;
    	
    	double day_discount =  
    	switch(dayOfWeek.toUpperCase()) {
    		case "MONDAY" -> DayOfWeek.MONDAY.getDiscount();
    		case "TUESDAY" -> DayOfWeek.TUESDAY.getDiscount();
    		case "WEDNESDAY" -> DayOfWeek.WEDNESDAY.getDiscount();
    		case "THURSDAY" -> DayOfWeek.THURSDAY.getDiscount();
    		case "FRIDAY" -> DayOfWeek.FRIDAY.getDiscount();
    		case "SATURDAY" -> DayOfWeek.SATURDAY.getDiscount();
    		case "SUNDAY" -> DayOfWeek.SUNDAY.getDiscount();
    		default -> 0.0;
    	};
    	
    	double seat_discount = 
    	switch(seatLevel) {
    		case "Lower Level (no discount)" -> 0.0;
    		case "T-Level (5% discount) " -> 0.05;
    		case "Upper Level (10% discount)" -> 0.10;
    		default -> 0.0;
    	};
    	
    	price_total = (1.0 - (day_discount + seat_discount + customerDiscount)) * basePrice;
    	
    	return price_total;
    }

    private String getSingleTicketDetails(String dayOfWeek, double basePrice, String seatLevel, double customerDiscount) {
        String ticket_details = "";
        
        double day_discount =  
            switch(dayOfWeek.toUpperCase()) {
            	case "MONDAY" -> DayOfWeek.MONDAY.getDiscount();
            	case "TUESDAY" -> DayOfWeek.TUESDAY.getDiscount();
            	case "WEDNESDAY" -> DayOfWeek.WEDNESDAY.getDiscount();
            	case "THURSDAY" -> DayOfWeek.THURSDAY.getDiscount();
            	case "FRIDAY" -> DayOfWeek.FRIDAY.getDiscount();
            	case "SATURDAY" -> DayOfWeek.SATURDAY.getDiscount();
            	case "SUNDAY" -> DayOfWeek.SUNDAY.getDiscount();
            	default -> 0.0;
        };
            	
        double seat_discount = 
            switch(seatLevel) {
            	case "Lower Level (no discount)" -> 0.0;
            	case "T-Level (5% discount) " -> 0.05;
            	case "Upper Level (10% discount)" -> 0.10;
            	default -> 0.0;
        };
        
        ticket_details = String.format("[Day: %s, Day Discount: %.2f%%, Customer Discount: %.2f%%, Seat Level/Discount: %s]\n",
        		dayOfWeek.toUpperCase(), (day_discount * 100.0), (customerDiscount * 100.0), seatLevel );
        
    	return ticket_details;
    }
    
}

