package main;

public class FormPengisianServiceDetail {

	private String formID;
	private String customerID;
	private int quantity;
	public FormPengisianServiceDetail(String formID, String customerID, int quantity) {
		super();
		this.formID = formID;
		this.customerID = customerID;
		this.quantity = quantity;
	}
	public String getFormID() {
		return formID;
	}
	public void setFormID(String formID) {
		this.formID = formID;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}
