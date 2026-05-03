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

//Each day has a specific discount rate associated with it.
//Weekdays have 10% discount, weekends have no discount
public enum DayOfWeek {
    MONDAY(0.10),
    TUESDAY(0.10),
    WEDNESDAY(0.10),
    THURSDAY(0.10),
    FRIDAY(0.10),
    SATURDAY(0.0),
    SUNDAY(0.0);

    private final double discount; // instance variable

    //Constructor for the DayOfWeek enum
    DayOfWeek(double discount) {
        this.discount = discount;
    }

    //Public method provides access to the discount value associated with a specific enum constant.
    public double getDiscount() {
        return discount;
    }
    
    @Override
    public String toString() {
    	switch(this) {
    	case MONDAY:
    		return "MONDAY";
    	case TUESDAY:
    		return "TUESDAY";
    	case WEDNESDAY:
    		return "WEDNESDAY";
    	case THURSDAY:
    		return "THURSDAY";
    	case FRIDAY:
    		return "FRIDAY";
    	case SATURDAY:
    		return "SATURDAY";
    	case SUNDAY:
    		return "SUNDAY";
    	default:
    		return "";
    	}
    }
}

