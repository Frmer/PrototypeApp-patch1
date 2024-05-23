package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

import connect.Connect;
import main.BarangViewer;

public class CustomerMenu {

	Scanner scan = new Scanner(System.in);
	Vector<String> customerIDList = new Vector<>();
	Vector<String> customerNameList = new Vector<>();
	Vector<String> customerUsernameList = new Vector<>();
	Vector<String> customerPhoneList = new Vector<>();
	Vector<String> customerLPList = new Vector<>();
	Vector<String> customerVPList = new Vector<>();
	Vector<String> customerComplaintsList = new Vector<>();
	Vector<String> CustomerPasswordList = new Vector<>();
	
	Connect connect = Connect.getConnection();
	String idNumber_Query;
//	int idNumber_INCR;
//	BarangViewer barangViewer = new Main();
	
//	Main main = new Main();
	String customerName = null;
	String customerPassword;
	
	public CustomerMenu() {
		
		int menu;
		String customerID = null;
		
		String customerPhone = null;
		String customerLP = null;
		String customerVP = null;
		String customerComplaints = null;
		
		int menu_customer;
//		Customer customer = new Customer(customerID, customerName, customerPhone, customerLP, customerVP, customerComplaints);
		
		getCustomerDBData();
		
//		for (int i = 0; i < customerIDList.size(); i++) {
//			System.out.println(customerIDList.get(i)); 
//		}
		
		do {
			System.out.println("Welcome to prototypeAPP");
			System.out.println("1. Login");
			System.out.println("2. Register");
			System.out.println("3. Exit");
			menu = scan.nextInt(); scan.nextLine();
			
			if (menu == 1) {
				do {
					System.out.println("Please input your Username : ");
					customerName = scan.nextLine();
					
					if (customerUsernameList.contains(customerName)) {
						
						String getPasswordQuery = String.format("SELECT CustomerPassword FROM userdata WHERE CustomerUsername = '%s'", customerName);
						ResultSet getPassword = connect.executeQuery(getPasswordQuery);
						String userpassword = null;
						
						try {
							if (getPassword.next()) {
								userpassword = getPassword.getString("CustomerPassword");
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						do {
							System.out.println("Input password : ");
							customerPassword = scan.nextLine();
							
							if (customerPassword.equals(userpassword)) {
								
								System.out.println("Welcome "+customerName);
								System.out.println("===================");
//								
								do {
//									barangViewer.viewBarang();
									System.out.println("1. Order");
									System.out.println("2. Exit");
									menu_customer = scan.nextInt(); scan.nextLine();
									if (menu_customer == 1) {
										insertCustomerOrder();
										System.out.println("");
									}
									
								} while (menu_customer != 2);
								
								
							} else {
								System.out.println("Wrong password");
							}
						} while (!customerPassword.equals(userpassword));
		
					} else {
						System.out.println("Invalid Credentials");
					}
					
				} while (customerName.isEmpty() || !customerUsernameList.contains(customerName));
				
				
				
			} else if (menu == 2) {
				registerUserData();
				
			}
			
		} while (menu != 3);
		
	}

	private void registerUserData() {
		String regUsername;
		String regPassword;
		String regCustomerName;
		String regCustomerPhone;
		String regCustomerLP;
		String regCustomerVP;
		String regAlamat;
//		String regCustomerComplaints;
		
		System.out.println("Register Page");
		System.out.println("=================");
		do {
			System.out.println("Input username : ");
			regUsername = scan.nextLine();
			if (customerUsernameList.contains(regUsername)) {
				System.out.println("Username sudah diambil, mohon pilih username yang lain");
			}
		} while (regUsername.isEmpty() || customerUsernameList.contains(regUsername));
		
		
		
		String confirmPassword = null;
		do {
			
			System.out.println("=================");
			System.out.println("Input password : ");
			regPassword = scan.nextLine();
			if (!regPassword.isEmpty()) {
				System.out.println("Input password konfirmasi");
				confirmPassword = scan.nextLine();
				if (!confirmPassword.equals(regPassword)) {
					System.out.println("Password konfirmasi harus sama dengan password");
				}
			}
		} while (regPassword.isEmpty() || !confirmPassword.equals(regPassword));
		
		do {
			System.out.println("=================");
			System.out.println("Input nama lengkap : ");
			regCustomerName = scan.nextLine();
		} while (regCustomerName.isEmpty());
		
		
		do {
			System.out.println("=================");
			System.out.println("Input nomor telepon [1 - 15 angka] :");
			regCustomerPhone = scan.nextLine();
		} while (regCustomerPhone.length() > 15 || regCustomerPhone.length() < 1 || regCustomerPhone.isEmpty());
		
		do {
			System.out.println("=================");
			System.out.println("Input Nomor polisi [Format : B9999XXX] 9 = Angka X = Huruf : ");
			regCustomerLP = scan.nextLine();
		} while (regCustomerLP.length() > 8 || regCustomerLP.isEmpty());
		
		do {
			System.out.println("=================");
			System.out.println("Input tipe vespa : ");
			regCustomerVP = scan.nextLine();
		} while (regCustomerVP.isEmpty());
		
		do {
			System.out.println("=================");
			System.out.println("Input alamat : ");
			regAlamat = scan.nextLine();
		} while (regAlamat.isEmpty());
		
//		do {
//			System.out.println("=================");
//			System.out.println("Input Keluhan [Tulis 'back' untuk kembali] : ");
//			regCustomerComplaints = scan.nextLine();
//		} while (regCustomerComplaints.isEmpty());
		
		
		
		idNumber_Query = "SELECT COUNT(CustomerID) FROM Customer";
		String datenow = "SELECT date_format(curdate(), '%d%m%Y')";
		int idNumber_INCR = 0;
		String datepart = null;
		
		ResultSet rsCount = connect.executeQuery(idNumber_Query);
		try {
			if (rsCount.next()) {
				idNumber_INCR = rsCount.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultSet rsDate = connect.executeQuery(datenow);
		
		try {
			if (rsDate.next()) {
				datepart = rsDate.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String customerIDvessel = "CS" + datepart + (idNumber_INCR+1);
		
		Customer customer = new Customer(customerIDvessel, regCustomerName, regCustomerPhone, regCustomerLP, regCustomerVP, null, regAlamat);
		
		String insertCustomerQuery = String.format("INSERT INTO customer VALUES('%s', '%s', '%s', '%s', '%s', '%s')", 
				customer.getCustomerID(), 
				customer.getCustomerName(), 
				customer.getCustomerPhone(), 
				customer.getCustomerLP(), 
				customer.getCustomerVP(),
				customer.getCustomerAddress());
		
		connect.executeUpdate(insertCustomerQuery);
		
		String insertUserdataQuery = String.format("INSERT INTO userdata VALUES('%s', '%s', '%s')", 
				customer.getCustomerID(), 
				regCustomerName, 
				regPassword);
		
		connect.executeUpdate(insertUserdataQuery);
		
		
		
	}

	private void getCustomerDBData() {
		String CustomerID;
		String CustomerIDQuery = "SELECT CustomerID FROM Customer";
		String CustomerPassQuery = "SELECT CustomerPassword FROM userdata";
		String CustomerPass;
		String CustomerUsernameQuery = "SELECT CustomerUsername FROM userdata";
		String CustomerUsername;
//		String CustomerIDQuery = "SELECT CustomerID FROM Customer";
//		ResultSet rs = connect.executeQuery(query);
		
		ResultSet customerIDData = connect.executeQuery(CustomerIDQuery);
		
		try {
			while(customerIDData.next()) {
				CustomerID = customerIDData.getString("CustomerID");
				customerIDList.add(CustomerID);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultSet customerPassData = connect.executeQuery(CustomerPassQuery);
		
		try {
			while(customerPassData.next()) {
				CustomerPass = customerPassData.getString("CustomerPassword");
				CustomerPasswordList.add(CustomerPass);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultSet CUR = connect.executeQuery(CustomerUsernameQuery);
		
		try {
			while (CUR.next()) {
				CustomerUsername = CUR.getString("CustomerUsername");
				customerUsernameList.add(CustomerUsername);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
	private void viewBarang() {
//		String blank = "";
		String query = "SELECT * FROM Barang";
		
		ResultSet rs = connect.executeQuery(query);
//		String query_size = "SELECT COUNT(BarangID) FROM Barang";
//		ResultSet qs = connect.executeQuery(query_size);
	
		Vector<String> barangIDlist = new Vector<>();
		Vector<String> barangNamelist = new Vector<>();
		Vector<Integer> barangPricelist = new Vector<>();
		Vector<Integer> barangStocklist = new Vector<>();
		
		try {
			while (rs.next()) {
					barangIDlist.add(rs.getString("BarangID"));
					barangNamelist.add(rs.getString("BarangName"));
					barangPricelist.add(rs.getInt("BarangPrice"));
					barangStocklist.add(rs.getInt("BarangStock"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("=============================================================");
		System.out.println("| ID Barang    | Nama Barang  | Harga Barang | Stock Barang |");
		System.out.println("=============================================================");
//		System.out.printf("|ID Barang %7s|Nama Barang %7s|Harga Barang %7s|Stock Barang %7s|\n", blank, blank, blank, blank);
		
		for (int i = 0; i < barangIDlist.size(); i++) {
	        System.out.printf("| %-12s | %-12s | %-12d | %-12d |\n",
	                barangIDlist.get(i), barangNamelist.get(i), barangPricelist.get(i), barangStocklist.get(i));
	    }
		System.out.println("=============================================================");
		
	}

	private void insertCustomerOrder() {
		String customerID = null;
		String transactionID = null;
		
		String transactionDate = null;
		String transactionDateQuery;
		
		int quantity = 1;
		
		String tipeBarang = null;
		
		int hargaBarang = 0;
		
		String barangID = null;
		String searchBarangID = null;
		String penguranganStockQuery = null;
		
		String searchCustomerName = customerName;
		
		String keluhan;
		String status = "Menunggu pembayaran";
		
		int KMterakhir = 0;
		
		int idNumber_INCR = 0;
		
		idNumber_Query = "SELECT COUNT(TransactionID) FROM UC_Transaction";
		
		String datenow = "SELECT date_format(curdate(), '%d%m%Y')";
		String datePart = null;
		ResultSet rs = connect.executeQuery(datenow);
		
		try {
			if (rs.next()) {
				datePart = rs.getString(1);
//				System.out.println(idBarang);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultSet rsCount = connect.executeQuery(idNumber_Query);
		try {
			if (rsCount.next()) {
				idNumber_INCR = rsCount.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		do {
			viewBarang();
			System.out.println("Input ID barang : ");
			searchBarangID = scan.nextLine();
		} while (searchBarangID.isBlank() || searchBarangID.isEmpty());
		
		do {
			System.out.println("Input quantity barang : ");
			quantity = scan.nextInt(); scan.nextLine();
		} while (quantity == 0);
		
		System.out.println("Input Keluhan : ");
		keluhan = scan.nextLine();
		
		do {
			System.out.println("Input KM Terakhir : ");
			KMterakhir = scan.nextInt(); scan.nextLine();
		} while (KMterakhir == 0);
			
		
		transactionID = "TH" + datePart + (idNumber_INCR+2);// bagian sini saya ganti
		transactionDateQuery = "SELECT CURDATE()"; //udah
		String tipeBarangQuery = String.format("SELECT BarangName FROM Barang WHERE BarangID = '%s'", searchBarangID); 
		String hargaBarangQuery = String.format("SELECT BarangPrice FROM Barang WHERE BarangID = '%s'", searchBarangID);
		String customerIDQuery = String.format("SELECT CustomerID FROM userdata WHERE CustomerUsername = '%s'", searchCustomerName);
		
		String barangIDQuery = String.format("SELECT BarangID FROM Barang WHERE BarangID = '%s'", searchBarangID);
		
		ResultSet rsTDQ = connect.executeQuery(transactionDateQuery);
		
		try {
			if (rsTDQ.next()) {
				transactionDate = rsTDQ.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultSet rsTBQ = connect.executeQuery(tipeBarangQuery);
		
		try {
			if (rsTBQ.next()) {
				tipeBarang = rsTBQ.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultSet rsHBQ = connect.executeQuery(hargaBarangQuery);
		
		try {
			if (rsHBQ.next()) {
				hargaBarang = rsHBQ.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultSet rsBIQ = connect.executeQuery(barangIDQuery);
		
		
		try {
			if (rsBIQ.next()) {
				barangID = rsBIQ.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultSet rsCSIQ = connect.executeQuery(customerIDQuery);
		
		try {
			if (rsCSIQ.next()) {
				customerID = rsCSIQ.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String queryInsertUC_Transaction = String.format("INSERT INTO UC_Transaction "
				+ "VALUES('%s', '%s', '%s', '%s', '%d')", 
				transactionID, transactionDate, status, keluhan, KMterakhir);
		
		connect.executeUpdate(queryInsertUC_Transaction);
		
		String queryInsertUC_TransactionDetail = String.format("INSERT INTO UC_TransactionDetail VALUES('%s', '%s', '%d', '%s', '%s', '%d')", 
				customerID, transactionID, quantity, barangID, tipeBarang, hargaBarang);
		
		
		connect.executeUpdate(queryInsertUC_TransactionDetail);
		
		penguranganStockQuery = String.format("UPDATE `barang` SET `BarangStock` = BarangStock-%d  WHERE `barang`.`BarangID` = '%s'",quantity , searchBarangID);
		connect.executeUpdate(penguranganStockQuery);
		
		
		String confirmTambahanBarang;
		
		do {
			System.out.println("Apakah ada tambahan barang?[Ya/Tidak]");
			confirmTambahanBarang = scan.nextLine();
			if (confirmTambahanBarang.equalsIgnoreCase("Ya")) {
				do {
					viewBarang();
					System.out.println("Input ID barang : ");
					searchBarangID = scan.nextLine();
				} while (searchBarangID.isBlank() || searchBarangID.isEmpty());
				
				do {
					System.out.println("Input quantity barang : ");
					quantity = scan.nextInt(); scan.nextLine();
				} while (quantity == 0);
				
				tipeBarangQuery = String.format("SELECT BarangName FROM Barang WHERE BarangID = '%s'", searchBarangID); 
				hargaBarangQuery = String.format("SELECT BarangPrice FROM Barang WHERE BarangID = '%s'", searchBarangID);
				barangIDQuery = String.format("SELECT BarangID FROM Barang WHERE BarangID = '%s'", searchBarangID);
				
				rsTBQ = connect.executeQuery(tipeBarangQuery);
				
				try {
					if (rsTBQ.next()) {
						tipeBarang = rsTBQ.getString(1);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				rsHBQ = connect.executeQuery(hargaBarangQuery);
				
				try {
					if (rsHBQ.next()) {
						hargaBarang = rsHBQ.getInt(1);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				rsBIQ = connect.executeQuery(barangIDQuery);
				
				
				try {
					if (rsBIQ.next()) {
						barangID = rsBIQ.getString(1);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

				
				queryInsertUC_TransactionDetail = String.format("INSERT INTO UC_TransactionDetail VALUES('%s', '%s', '%d', '%s', '%s', '%d')", 
						customerID, transactionID, quantity, barangID, tipeBarang, hargaBarang);
				
				
				connect.executeUpdate(queryInsertUC_TransactionDetail);
				
				
			}
//			penguranganStockQuery = String.format("UPDATE `barang` SET `BarangStock` = BarangStock-%d  WHERE `barang`.`BarangID` = '%s'",quantity , searchBarangID);
//			connect.executeUpdate(penguranganStockQuery);
			
			
		} while (confirmTambahanBarang.isBlank() || confirmTambahanBarang.isEmpty() || !confirmTambahanBarang.equalsIgnoreCase("Tidak"));
		
		penguranganStockQuery = String.format("UPDATE `barang` SET `BarangStock` = BarangStock-%d  WHERE `barang`.`BarangID` = '%s'",quantity , searchBarangID);
		connect.executeUpdate(penguranganStockQuery);
		
	}
	
	public static void main(String[] args) {
		new CustomerMenu();

	}

}
