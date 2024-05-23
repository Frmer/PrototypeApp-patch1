package main;

public class Cashier {

	private String cashierID;
	private String cashierName;
	public Cashier(String cashierID, String cashierName) {
		super();
		this.cashierID = cashierID;
		this.cashierName = cashierName;
	}
	
	public void daftarkanPesanan() {
		System.out.println("Pesanan customer berhasil di daftarkan");
	}
	
	public void prosesPembayaran() {
		System.out.println("Pembayaran berhasil");
	}
	
	public String getCashierID() {
		return cashierID;
	}
	public void setCashierID(String cashierID) {
		this.cashierID = cashierID;
	}
	public String getCashierName() {
		return cashierName;
	}
	public void setCashierName(String cashierName) {
		this.cashierName = cashierName;
	}
	
	
	
}
