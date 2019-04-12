package com.techelevator.model;

public class CsvFileEntry {

	private int id;
	private String name;
	private String accountType;
	private long amount;
	
	public CsvFileEntry() {
		
	}
	
	public CsvFileEntry(String id, String name, String accountType, String amount) {
		this.id = Integer.parseInt(id);
		this.name = name;
		this.accountType = accountType;
		this.amount = Long.parseLong(amount);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	
	
	
}
