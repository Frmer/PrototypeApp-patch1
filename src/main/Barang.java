package main;

public class Barang {

	private String barangID;
	private String barangName;
	private int barangHarga;
	private int barangStock;
	public Barang(String barangID, String barangName, int barangHarga, int barangStock) {
		super();
		this.barangID = barangID;
		this.barangName = barangName;
		this.barangHarga = barangHarga;
		this.barangStock = barangStock;
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
	public int getBarangStock() {
		return barangStock;
	}
	public void setBarangStock(int barangStock) {
		this.barangStock = barangStock;
	}
	
	
	
	
}
