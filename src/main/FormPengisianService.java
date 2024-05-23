package main;

public class FormPengisianService {

	private String formID;
	private String formDate;
	private String tipeService;
	private int hargaService;
	public FormPengisianService(String formID, String formDate, String tipeService, int hargaService) {
		super();
		this.formID = formID;
		this.formDate = formDate;
		this.tipeService = tipeService;
		this.hargaService = hargaService;
	}
	public String getFormID() {
		return formID;
	}
	public void setFormID(String formID) {
		this.formID = formID;
	}
	public String getFormDate() {
		return formDate;
	}
	public void setFormDate(String formDate) {
		this.formDate = formDate;
	}
	public String getTipeService() {
		return tipeService;
	}
	public void setTipeService(String tipeService) {
		this.tipeService = tipeService;
	}
	public int getHargaService() {
		return hargaService;
	}
	public void setHargaService(int hargaService) {
		this.hargaService = hargaService;
	}
	
	
	
}
