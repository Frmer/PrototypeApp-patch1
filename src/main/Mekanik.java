package main;

public class Mekanik {

	private String mekanikID;
	private String menanikName;
	public Mekanik(String mekanikID, String menanikName) {
		super();
		this.mekanikID = mekanikID;
		this.menanikName = menanikName;
	}
	
	public void jalankanService(){
		System.out.println("Fixing");
	}
	
	public String getMekanikID() {
		return mekanikID;
	}
	public void setMekanikID(String mekanikID) {
		this.mekanikID = mekanikID;
	}
	public String getMenanikName() {
		return menanikName;
	}
	public void setMenanikName(String menanikName) {
		this.menanikName = menanikName;
	}
	
	
	
}
