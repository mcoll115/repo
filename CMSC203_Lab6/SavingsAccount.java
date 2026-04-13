/**
 * Course: CMSC203 CRN 32324
 * Instructor: Grigoriy Grinberg
 * 
 * Date: 04/13/2026
 * Student: Marcus K Collins
 */
public class SavingsAccount extends BankAccount {
	private double rate = 2.5 / 100;
	private int savingsNumber = 0;
	private String accountNumber;
	
	public SavingsAccount(String name, double amount) {
		super(name, amount);
		this.accountNumber = super.getAccountNumber() + "-" + String.valueOf(savingsNumber);
	}
	
	public SavingsAccount(SavingsAccount oldAccount, double amount) {
		super(oldAccount, amount);
		String old_account_start = new String(oldAccount.getAccountNumber().split("-")[0]);
		int savings_num = Integer.valueOf(oldAccount.getAccountNumber().split("-")[1]);
		++savings_num;
		this.accountNumber = old_account_start + "-" + String.valueOf(savings_num);
	}
	
	@Override
	public String getAccountNumber() {
		return new String(this.accountNumber);
	}
	
	public void postInterest() {
		double interest = super.getBalance() * (rate / 12);
		super.deposit(interest);
		return;
	}
}
