package main;

public class TransactionHeader {

	private String transactionID;
	private String transactionDate;
	private String cashierID;
	private String mekanikID;
	private String status;
	private String keluhan;
	private int KMterakhir;
	public TransactionHeader(String transactionID, String transactionDate, String cashierID, String mekanikID,
			String status, String keluhan, int kMterakhir) {
		super();
		this.transactionID = transactionID;
		this.transactionDate = transactionDate;
		this.cashierID = cashierID;
		this.mekanikID = mekanikID;
		this.status = status;
		this.keluhan = keluhan;
		KMterakhir = kMterakhir;
	}
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getCashierID() {
		return cashierID;
	}
	public void setCashierID(String cashierID) {
		this.cashierID = cashierID;
	}
	public String getMekanikID() {
		return mekanikID;
	}
	public void setMekanikID(String mekanikID) {
		this.mekanikID = mekanikID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getKeluhan() {
		return keluhan;
	}
	public void setKeluhan(String keluhan) {
		this.keluhan = keluhan;
	}
	public int getKMterakhir() {
		return KMterakhir;
	}
	public void setKMterakhir(int kMterakhir) {
		KMterakhir = kMterakhir;
	}
	
	
	
	
	
	
	
	
	
}
