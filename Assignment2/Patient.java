package application;
/**
 * Class: CMSC203
 * Instructor: Grigoriy Grinberg
 * Description: A class to hold patient information and displays 
 * information for each field it holds.
 * Due: 02/23/2026
 * Platform/compiler: Eclipse / javac
 * Integrity Pledge:
 * I pledge that I have completed the programming assignment independently. I have not 
 * copied the code from a student or any source. I have not given my code to any
 * student.
 * Name: Marcus Kemel Collins
*/
public final class Patient {
	private String name_first, name_middle, name_last;
	private String phone_number;
	private String street_address, city, state, zip_code;
	private String ec_name, ec_phone;
	
	// Constructors
	/**
	 * Default constructor (no arguments)
	 */
	public Patient() {
		name_first = new String();
		name_middle = new String();
		name_last = new String();
		phone_number = new String();
		street_address = new String();
		city = new String();
		state = new String();
		zip_code = new String();
		ec_name = new String();
		ec_phone = new String();
	}
	
	/**
	 * Constructs new patient instance from name arguments.
	 * @param first_name First Name of new patient
	 * @param middle_name Middle Name of new patient
	 * @param last_name Last Name of new patient
	 */
	public Patient(String first_name, String middle_name, String last_name) {
		name_first = new String(first_name);
		name_middle = new String(middle_name);
		name_last = new String(last_name);
		phone_number = new String();
		street_address = new String();
		city = new String();
		state = new String();
		zip_code = new String();
		ec_name = new String();
		ec_phone = new String();
	}
	
	/**
	 * Constructs new patient instance from full set of arguments.
	 * @param first_name First Name of new patient
	 * @param middle_name Middle Name of new patient
	 * @param last_name Last Name of new patient
	 * @param phone Phone Number of new patient
	 * @param address_street Street Address portion of address of new patient
	 * @param address_city City portion of address of new patient
	 * @param address_state State portion of address of new patient
	 * @param address_zip ZIP Code portion of address of new patient
	 * @param name_emergency Name of Emergency Contact of new patient
	 * @param phone_emergency Phone Number of Emergency Contact of new patient
	 */
	public Patient(String first_name, String middle_name, String last_name,
			String phone, 
			String address_street, String address_city, String address_state, String address_zip,
			String name_emergency, String phone_emergency)
	{
		name_first = new String(first_name);
		name_middle = new String(middle_name);
		name_last = new String(last_name);
		phone_number = new String(phone);
		street_address = new String(address_street);
		city = new String(address_city);
		state = new String(address_state);
		zip_code = new String(address_zip);
		ec_name = new String(name_emergency);
		ec_phone = new String(phone_emergency);
	}
	
	/**
	 * Gets the patient's first name.
	 * @return string representing first name of patient instance
	 */
	public String getNameFirst() {
		return name_first;
	}
	
	/**
	 * Gets the patient's middle name.
	 * @return string representing middle name of patient instance
	 */
	public String getNameMiddle() {
		return name_middle;
	}
	
	/**
	 * Gets the patient's last name.
	 * @return string representing last name of patient instance
	 */
	public String getNameLast() {
		return name_last;
	}
	
	/**
	 * Gets the patient's phone number.
	 * @return string representing the phone number of the patient instance
	 */
	public String getPhoneNumber() {
		return phone_number;
	}
	
	/**
	 * Gets the patient's street address portion of the address.
	 * @return string representing the street portion of the address 
	 * for a patient instance
	 */
	public String getStreetAddress() {
		return street_address;
	}
	
	/**
	 * Gets the patient's city portion of the address.
	 * @return string representing the city portion of the address
	 * for a patient instance
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Gets the patient's street address.
	 * @return string representing the state portion of the address
	 * for a patient instance
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * Gets the patient's ZIP Code portion of the address.
	 * @return string representing the ZIP code portion of the address
	 * for a patient instance
	 */
	public String getZipCode() {
		return zip_code;
	}
	
	/**
	 * Gets the patient's Emergency Contact's name.
	 * @return string representing the name of the Emergency Contact 
	 * for a patient instance
	 */
	public String getECName() {
		return ec_name;
	}
	
	/**
	 * Gets the patient's Emergency Contact's phone number.
	 * @return string representing the phone number of the Emergency Contact 
	 * for a patient instance
	 */
	public String getECPhoneNumber() {
		return ec_phone;
	}
	
	/**
	 * Sets a new first name for a patient.
	 * @param updated_first new name of the patient
	 */
	public void setNameFirst(String updated_first) {
		name_first = new String(updated_first);
	}
	
	/**
	 * Sets a new middle name for a patient.
	 * @param updated_middle new name of the patient
	 */
	public void setNameMiddle(String updated_middle) {
		name_middle = new String(updated_middle);
	}
	
	/**
	 * Sets a new last name for a patient.
	 * @param updated_last new name of the patient
	 */
	public void setNameLast(String updated_last) {
		name_last = new String(updated_last);
	}
	
	/**
	 * Sets a new phone number for a patient.
	 * @param updated_phone new name of the patient
	 */
	public void setPhoneNumber(String updated_phone) {
		phone_number = new String(updated_phone);
	}
	
	/**
	 * Sets a new street address for a patient.
	 * @param updated_street new street in address of the patient
	 */
	public void setStreetAddress(String updated_street) {
		street_address = new String(updated_street);
	}
	
	/**
	 * Sets a new city in address for a patient.
	 * @param updated_city new city in address of the patient
	 */
	public void setCity(String updated_city) {
		city = new String(updated_city);
	}
	
	/**
	 * Sets a new state in address for a patient.
	 * @param updated_state new state in address of the patient
	 */
	public void setState(String updated_state) {
		state = new String(updated_state);
	}
	
	/**
	 * Sets a new ZIP Code for a patient.
	 * @param updated_zip new ZIP Code in address of the patient
	 */
	public void setZipCode(String updated_zip) {
		zip_code = new String(updated_zip);
	}
	
	/**
	 * Sets a new Emergency Contact name for a patient.
	 * @param updated_ecname new name in Emergency Contact of the patient
	 */
	public void setECName(String updated_ecname) {
		ec_name = new String(updated_ecname);
	}
	
	/**
	 * Sets a new Emergency Contact phone for a patient.
	 * @param updated_ecphone new phone in Emergency Contact of the patient
	 */
	public void setECPhoneNumber(String updated_ecphone) {
		ec_phone = new String(updated_ecphone);
	}
	
	/**
	 * Forms a patient's full name from the first, middle and last names.
	 * @return string representing the full name of a patient space-delimited
	 */
	public String buildFullName() {
		return( String.join(" ", getNameFirst(), getNameMiddle(), getNameLast()) );
	}
	
	/**
	 * Forms the full address from a patient's street address, city, state and ZIP code.
	 * @return string representing the full address of a patient space-delimited
	 */
	public String buildAddress() {
		return( String.join(" ", getStreetAddress(), getCity(), getState(), getZipCode()) );
	}
	
	/**
	 * Forms the full message for a patient's Emergency Contact.
	 * @return string representing the full Emergency Contact info of 
	 * a patient space-delimited
	 */
	public String buildEmergencyContact() {
		return( String.join(" ", getECName(), getECPhoneNumber()) );
	}
	
	/**
	 * Forms the full information message for a patient instance.
	 * @return string representing the full patient info (with newlines and spaces)
	 */
	public String toString() {
		String full_info = new String("");
		//full_info += "\tName: " + buildFullName() + "\n";
		full_info = full_info.concat("\tName:    ").concat(buildFullName()).concat("\n");
		full_info = full_info.concat("\tPhone:   ").concat(getPhoneNumber()).concat("\n");
		full_info = full_info.concat("\tAddress: ").concat(buildAddress()).concat("\n");
		full_info = full_info.concat("\tEmergency Contact: ").concat(buildEmergencyContact()).concat("\n");
		
		return full_info;
	}
}
