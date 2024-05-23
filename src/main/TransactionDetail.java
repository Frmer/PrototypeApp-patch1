package main;

public class TransactionDetail {

	private String customerID;
	private String transactionID;
	private int quantity;
	private String barangID;
	private String barangName;
	private int barangHarga;
	public TransactionDetail(String customerID, String transactionID, int quantity, String barangID, String barangName,
			int barangHarga) {
		super();
		this.customerID = customerID;
		this.transactionID = transactionID;
		this.quantity = quantity;
		this.barangID = barangID;
		this.barangName = barangName;
		this.barangHarga = barangHarga;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getBarangID() {
		return barangID;
	}
	public void setBarangID(String barangID) {
		this.barangID = barangID;
	}
	public String getBarangName() {
		return barangName;
	}
	public void setBarangName(String barangName) {
		this.barangName = barangName;
	}
	public int getBarangHarga() {
		return barangHarga;
	}
	public void setBarangHarga(int barangHarga) {
		this.barangHarga = barangHarga;
	}
	
	
	
	
	
}
