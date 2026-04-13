/**
 * Course: CMSC203 CRN 32324
 * Instructor: Grigoriy Grinberg
 * 
 * Date: 04/13/2026
 * Student: Marcus K Collins
 */
public class CheckingAccount extends BankAccount {
	private final static double FEE = 0.15;
	
	public CheckingAccount(String name, double amount) {
		super(name, amount);
		String account_new = new String(super.getAccountNumber());
		account_new += "-10";
		super.setAccountNumber(account_new);
	}
	
	@Override
	public boolean withdraw(double amount) {
		double full_amount = amount + FEE;
		return super.withdraw(full_amount);
	}
}
