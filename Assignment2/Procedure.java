package application;
/**
 * Class: CMSC203
 * Instructor: Grigoriy Grinberg
 * Description: A class to store and display procedure information about
 * a patient (no inheritance or linkage).
 * Due: 02/23/2026
 * Platform/compiler: Eclipse / javac
 * Integrity Pledge:
 * I pledge that I have completed the programming assignment independently. I have not 
 * copied the code from a student or any source. I have not given my code to any
 * student.
 * Name: Marcus Kemel Collins
 */
public final class Procedure {
	private String procedure_name;
	private String practitioner;
	private String procedure_date;
	private double procedure_cost;
	
	/**
	 * Default Constructor (no arguments).
	 */
	public Procedure() {
		procedure_name = new String();
		practitioner = new String();
		procedure_date = new String();
		procedure_cost = (double) 0.0;
	}
	
	/**
	 * Constructs new Procedure from procedure name and date.
	 * @param name_procedure Name/Title of a new procedure
	 * @param date_procedure Date of a new procedure
	 */
	public Procedure(String name_procedure, String date_procedure) 
	{
		procedure_name = new String(name_procedure);
		practitioner = new String();
		procedure_date = new String(date_procedure);
		procedure_cost = (double) 0.0;
	}
	
	/**
	 * Constructs new Procedure from procedure name and date.
	 * @param name_procedure Name/Title of a new procedure
	 * @param date_procedure Date of a new procedure
	 * @param practitioner_procedure Practitioner of a new procedure
	 * @param charge_procedure Cost/Charge of a new procedure
	 */
	public Procedure(String name_procedure, String date_procedure,
			String practitioner_procedure, double charge_procedure) 
	{
		procedure_name = new String(name_procedure);
		practitioner = new String(practitioner_procedure);
		procedure_date = new String(date_procedure);
		procedure_cost = charge_procedure;
	}
	
	/**
	 * Gets the charge (or cost) of the procedure instance.
	 * @return double representing the cost of a procedure
	 */
	public double getProcedureCost() {
		return procedure_cost;
	}
	
	/**
	 * Gets the name of the procedure instance.
	 * @return string representing the name of a procedure 
	 */
	public String getProcedureName() {
		return procedure_name;
	}
	
	/**
	 * Gets the date the procedure instance was performed.
	 * @return string representing the date of a procedure
	 */
	public String getProcedureDate() {
		return procedure_date;
	}
	
	/**
	 * Gets the name of the practitioner listed for the procedure instance.
	 * @return string representing the name of a practitioner for a
	 * procedure instance
	 */
	public String getPractitioner() {
		return practitioner;
	}
	
	/**
	 * Sets a new name of a procedure instance.
	 * @param name_proc updated name of the procedure
	 */
	public void setProcedureName(String name_proc) {
		procedure_name = new String(name_proc);
	}
	
	/**
	 * Sets a new date for a procedure instance.
	 * @param date_proc updated date for the procedure
	 */
	public void setProcedureDate(String date_proc) {
		procedure_date = new String(date_proc);
	}
	
	/**
	 * Sets a new cost for a procedure instance.
	 * @param cost_proc updated cost for the procedure
	 */
	public void setProcedureCost(double cost_proc) {
		procedure_cost = cost_proc;
	}
	
	/**
	 * Forms a full procedure info string for display in a message, etc.
	 * @return string representing the full procedure instance info
	 */
	public String toString() {
		String full_info = new String("");
		full_info = full_info.concat("\tProcedure:      ").concat(procedure_name.isEmpty() ? "null" : getProcedureName()).concat("\n");
		full_info = full_info.concat("\tProcedure Date: ").concat(procedure_date.isEmpty() ? "null" : getProcedureDate()).concat("\n");
		full_info = full_info.concat("\tPractitioner:   ").concat(practitioner.isEmpty() ? "null" : getPractitioner()).concat("\n");
		full_info = full_info.concat("\tProcedure Charge: ").concat(String.format("$%,.2f", getProcedureCost())).concat("\n");
		
		return full_info;
	}
}
