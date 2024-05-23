package main;

import java.sql.ResultSet;
import java.sql.SQLException;

import connect.Connect;
import java.util.Scanner;
import java.util.Vector;

public class Main {

	Vector<String> tesVector = new Vector<>();
	Scanner scan = new Scanner(System.in);
	Connect connect = Connect.getConnection();
	String idNumber_Query;
	int idNumber_INCR;
	
	public Main() {
		
		
		int choice1;
		int choice1_1;
		
		do {
			System.out.println("PrototypeAPP Control Panel");
			System.out.println("So who are you?");
			System.out.println("1. Cashier");
			System.out.println("2. Mechanic");
			System.out.println("3. Exit");
			
			choice1 = scan.nextInt(); scan.nextLine();
			
			if (choice1 == 1) {
				
				do {
					System.out.println("1. Input Barang");
					System.out.println("2. View Barang");
					System.out.println("3. Delete Barang");
					System.out.println("4. Make an order");
					System.out.println("5. Confirm an Order");
					System.out.println("6. Exit");
					choice1_1 = scan.nextInt(); scan.nextLine();
					
					if (choice1_1 == 1) {
						inputBarang();
					}  else if (choice1_1 == 2) {
						viewBarang();
					} else if (choice1_1 == 3) {
						deleteBarang();
					} else if (choice1_1 == 4) {
						inputOrder();
					} else if (choice1_1 == 5) {
						confirmOrder();
					}
					
				} while (choice1_1 != 6);
				
			} else if (choice1 == 2) {
				int choice1_2;
				do {
					System.out.println("1. View Transaction");
					System.out.println("2. Update Transaction Status");
					System.out.println("3. Exit");
					choice1_2 = scan.nextInt(); scan.nextLine();
					if (choice1_2 == 1) {
						viewTransactionDetail();
					} else if (choice1_2 == 2) {
						updateStatusMekanik();
					}
				} while (choice1_2 != 3);
			}
			
		} while (choice1 != 3);
		
		
	}

	private void inputOrder() {
		
		String transactionID = null;
		
		String transactionDate = null;
		String transactionDateQuery;
		
		int quantity = 1;
		
		String tipeBarang = null;
		
		int hargaBarang = 0;
		
		String cashierID = null;
		String searchCashierID = null;
		
		String barangID = null;
		String searchBarangID = null;
		String penguranganStockQuery;
		
		String mekanikID = null;
		String searchMekanikID = null;
		
		String customerID = null;
		String searchCustomerID;
		
		String keluhan;
		String status;
		
		int KMterakhir = 0;
		
		idNumber_Query = "SELECT COUNT(TransactionID) FROM TransactionHeader";
		
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
		
		do {
			viewCashier();
			System.out.println("Input ID kasir : ");
			searchCashierID = scan.nextLine();
		} while (searchCashierID.isBlank() || searchCashierID.isEmpty());
		
		do {
			viewMekanik();
			System.out.println("Input ID mekanik : ");
			searchMekanikID = scan.nextLine();
		} while (searchMekanikID.isBlank() || searchMekanikID.isEmpty());
		
		do {
			viewCustomer();
			System.out.println("Input ID Customer : ");
			searchCustomerID = scan.nextLine();
		} while (searchCustomerID.isBlank() || searchCustomerID.isEmpty());
		
		
			System.out.println("Input Keluhan : ");
			keluhan = scan.nextLine();
			
		do {
			System.out.println("Input KM Terakhir : ");
			KMterakhir = scan.nextInt(); scan.nextLine();
		} while (KMterakhir == 0);
		
		
		
		transactionID = "TH" + datePart + (idNumber_INCR+1);
		transactionDateQuery = "SELECT CURDATE()"; //udah
		String tipeBarangQuery = String.format("SELECT BarangName FROM Barang WHERE BarangID = '%s'", searchBarangID); 
		String hargaBarangQuery = String.format("SELECT BarangPrice FROM Barang WHERE BarangID = '%s'", searchBarangID);
		
		String customerIDQuery = String.format("SELECT CustomerID FROM Customer WHERE CustomerID = '%s'", searchCustomerID);
		
		String barangIDQuery = String.format("SELECT BarangID FROM Barang WHERE BarangID = '%s'", searchBarangID);
		String mekanikIDQuery = String.format("SELECT MekanikID FROM Mekanik WHERE MekanikID = '%s'", searchMekanikID);
		String cashierIDQuery = String.format("SELECT CashierID FROM Cashier WHERE CashierID = '%s'", searchCashierID);
		
		penguranganStockQuery = String.format("UPDATE `barang` SET `BarangStock` = BarangStock-%d  WHERE `barang`.`BarangID` = '%s'",quantity , searchBarangID);
		
		status = "Menunggu Pembayaran";
		
//		String dateNow;// hasil dari date yang sekarang;
//		String tipeBarangV; //Vessel tipeBarang;
		
		
	
		
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
		
		ResultSet rsMIQ = connect.executeQuery(mekanikIDQuery);
		
		try {
			if (rsMIQ.next()) {
				mekanikID = rsMIQ.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultSet rsCIQ = connect.executeQuery(cashierIDQuery);
		
		try {
			if (rsCIQ.next()) {
				cashierID = rsCIQ.getString(1);
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
		
		
		
		TransactionHeader TH = new TransactionHeader(transactionID, transactionDate, cashierID, mekanikID, status, keluhan, KMterakhir);
		TransactionDetail TD = new TransactionDetail(customerID, transactionID, quantity, barangID, tipeBarang, hargaBarang);
		
		String queryInsertTransactionHeader = String.format("INSERT INTO TransactionHeader VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%d')", 
				TH.getTransactionID(), TH.getTransactionDate(), TH.getCashierID(), TH.getMekanikID(), TH.getStatus(), TH.getKeluhan(), TH.getKMterakhir());
		
		String queryInsertTransactionDetail = String.format("INSERT INTO TransactionDetail VALUES('%s', '%s', '%d', '%s', '%s', '%d')", 
				TD.getCustomerID(), TD.getTransactionID(), TD.getQuantity(), TD.getBarangID(), TD.getBarangName(), TD.getBarangHarga());
		
		connect.executeUpdate(queryInsertTransactionHeader);
		connect.executeUpdate(queryInsertTransactionDetail);
		connect.executeUpdate(penguranganStockQuery);
		
		
		
		
//		System.out.println(TH.getTransactionID());//bener
//		System.out.println(TH.getTransactionDate());
//		System.out.println(TH.getCashierID());//bener
//		System.out.println(TH.getMekanikID());//bener
//		System.out.println(TH.getStatus());
		
		String confirmTambahanBarang;
		
		do {
			
//			TH.setBarangID(null);
//			TH.setHargaBarang(0);
//			TH.setTipeBarang(null);
			
			
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
				
//				TH.setBarangID(barangID);
//				TH.setHargaBarang(hargaBarang);
//				TH.setTipeBarang(tipeBarang);
				
//				queryInsertTransactionHeader = String.format("INSERT INTO TransactionHeader VALUES('%s', '%s', '%s', '%s', '%s')", 
//						TH.getTransactionID(), TH.getTransactionDate(), TH.getCashierID(), TH.getMekanikID(), TH.getStatus());
				
				TD.setBarangID(barangID);
				TD.setBarangHarga(hargaBarang);
				TD.setBarangName(tipeBarang);
				TD.setQuantity(quantity);
				
				queryInsertTransactionDetail = String.format("INSERT INTO TransactionDetail VALUES('%s', '%s', '%d', '%s', '%s', '%d')", 
						TD.getCustomerID(), TD.getTransactionID(), TD.getQuantity(), TD.getBarangID(), TD.getBarangName(), TD.getBarangHarga());
				
//				connect.executeUpdate(queryInsertTransactionHeader);
				connect.executeUpdate(queryInsertTransactionDetail);
				
				
			}
			penguranganStockQuery = String.format("UPDATE `barang` SET `BarangStock` = BarangStock-%d  WHERE `barang`.`BarangID` = '%s'",quantity , searchBarangID);
			connect.executeUpdate(penguranganStockQuery);
			
			
		} while (confirmTambahanBarang.isBlank() || confirmTambahanBarang.isEmpty() || !confirmTambahanBarang.equalsIgnoreCase("Tidak"));
		
		penguranganStockQuery = String.format("UPDATE `barang` SET `BarangStock` = BarangStock-%d  WHERE `barang`.`BarangID` = '%s'",quantity , searchBarangID);
		connect.executeUpdate(penguranganStockQuery);

		
		
	}

	private void deleteBarang() {
		String deleteInput;
		viewBarang();
		System.out.println("Input ID barang yang ingin dihapus : ");
		deleteInput = scan.nextLine();
		String deleteQuery = String.format("DELETE FROM Barang WHERE BarangID = '%s'", deleteInput);
		connect.executeUpdate(deleteQuery);
		
		
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

	private void inputBarang() {
		
		idNumber_Query = "SELECT COUNT(BarangID) FROM Barang";
		String idBarang = null;
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
		
		idBarang = "BR" + datePart + (idNumber_INCR+1);
		
		String namaBarang;
		int hargaBarang;
		int stockBarang;
		
		do {
			System.out.println("Input nama barang : ");
			namaBarang = scan.nextLine();
		} while (namaBarang.isBlank() || namaBarang.isEmpty());
		
		do {
			System.out.println("Input harga barang : ");
			hargaBarang = scan.nextInt(); scan.nextLine();
		} while (hargaBarang == 0);
		
		do {
			System.out.println("Input stock barang : ");
			stockBarang = scan.nextInt(); scan.nextLine();
		} while (stockBarang == 0);
		
		
		
		Barang barang = new Barang(idBarang, namaBarang, hargaBarang, stockBarang);
		
		String query = String.format("INSERT INTO Barang(BarangID, BarangName, BarangPrice, BarangStock) VALUES('%s', '%s', '%d', '%d')", barang.getBarangID(), barang.getBarangName(), barang.getBarangHarga(), barang.getBarangStock());
		connect.executeUpdate(query);
		
	}

	private void viewCashier() {
//		String blank = "";
		String query = "SELECT * FROM Cashier";
		ResultSet rs = connect.executeQuery(query);
		
		Vector<String> cashierIDlist = new Vector<>();
		Vector<String> CashierNamelist = new Vector<>();
		
		try {
			while (rs.next()) {
				cashierIDlist.add(rs.getString("CashierID"));
				CashierNamelist.add(rs.getString("CashierName"));
					
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("===============================");
		System.out.println("| ID Cashier   | Nama Cashier |");
		System.out.println("===============================");
		
		for (int i = 0; i < cashierIDlist.size(); i++) {
	        System.out.printf("| %-12s | %-12s |\n",
	        		cashierIDlist.get(i), CashierNamelist.get(i));
	    }
		System.out.println("===============================");
		
	}
	
	private void viewMekanik() {
//		String blank = "";
		String query = "SELECT * FROM Mekanik";
		ResultSet rs = connect.executeQuery(query);
		
		Vector<String> mekanikIDlist = new Vector<>();
		Vector<String> mekanikNamelist = new Vector<>();
		
		try {
			while (rs.next()) {
				mekanikIDlist.add(rs.getString("MekanikID"));
				mekanikNamelist.add(rs.getString("MekanikName"));
					
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("===============================");
		System.out.println("| ID Cashier   | Nama Cashier |");
		System.out.println("===============================");
		
		for (int i = 0; i < mekanikIDlist.size(); i++) {
	        System.out.printf("| %-12s | %-12s |\n",
	        		mekanikIDlist.get(i), mekanikNamelist.get(i));
	    }
		System.out.println("===============================");
	}
	
	private void viewCustomer() {
//		String blank = "";
		String query = "SELECT * FROM Customer";
		ResultSet rs = connect.executeQuery(query);
		
		Vector<String> customerIDlist = new Vector<>();
		Vector<String> customerNamelist = new Vector<>();
		Vector<String> customerPhonelist = new Vector<>();
		Vector<String> customerLPlist = new Vector<>();
		Vector<String> customerVespalist = new Vector<>();
//		Vector<String> customerKeluhanlist = new Vector<>();
		
		try {
			while (rs.next()) {
				customerIDlist.add(rs.getString("CustomerID"));
				customerNamelist.add(rs.getString("CustomerName"));
				customerPhonelist.add(rs.getString("CustomerPhone"));
				customerLPlist.add(rs.getString("CustomerLP"));
				customerVespalist.add(rs.getString("CustomerVP"));
//				customerKeluhanlist.add(rs.getString("CustomerComplaints"));
					
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("======================================================================================");
		System.out.println("|  ID Customer   | Nama Customer  |  Phone Number  |  Customer LP   | Customer Vespa |");
		System.out.println("======================================================================================");
		
		for (int i = 0; i < customerIDlist.size(); i++) {
	        System.out.printf("| %-14s | %-14s | %-14s | %-14s | %-14s |\n",
	        		customerIDlist.get(i), customerNamelist.get(i),customerPhonelist.get(i),customerLPlist.get(i),customerVespalist.get(i));
	    }
		System.out.println("=====================================================================================");
	}
	
	private void viewTransactionHeader() {

		String query = "SELECT * FROM TransactionHeader";
		
		ResultSet rs = connect.executeQuery(query);
		
		Vector<String> transactionIDList = new Vector<>();
		Vector<String> transactionDateList = new Vector<>();
		Vector<String> cashierIDList = new Vector<>();
		Vector<String> mekanikIDList = new Vector<>();
		Vector<String> statusList = new Vector<>();
		Vector<String> keluhanList = new Vector<>();
		Vector<Integer> UCKMterakhirList = new Vector<>();
	
		
		try {
			while (rs.next()) {
				transactionIDList.add(rs.getString("TransactionID"));
				transactionDateList.add(rs.getString("TransactionDate"));
				cashierIDList.add(rs.getString("CashierID"));
				mekanikIDList.add(rs.getString("MekanikID"));
				statusList.add(rs.getString("Status"));
				keluhanList.add(rs.getString("Keluhan"));
				UCKMterakhirList.add(rs.getInt("KMTerakhir"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("======================================================================================================================");
		System.out.println("| ID Transaction | Tgl Transaction|   ID Cashier   |   ID Mekanik  |    Status     |     Keluhan    |  KM Terakhir   |");
		System.out.println("======================================================================================================================");
		
		for (int i = 0; i < transactionIDList.size(); i++) {
	        System.out.printf("| %-14s | %-14s | %-14s | %-14s | %-14s | %-14s | %-14d |\n",
	        		transactionIDList.get(i), transactionDateList.get(i),cashierIDList.get(i),mekanikIDList.get(i),statusList.get(i), keluhanList.get(i), UCKMterakhirList.get(i));
	    }
		System.out.println("======================================================================================================================");
		
	}
	
	
	private void viewTransactionDetail() {
		viewTransactionHeader();
		String transactionID = null;
		
		System.out.println("Input the TransactionID that you want to see the details");
		transactionID = scan.nextLine();
		
		String query = String.format("SELECT cs.CustomerName, th.TransactionID , th.TransactionDate , mk.MekanikName , cs.CustomerVP , td.BarangID , td.BarangName , td.Quantity, td.BarangHarga, ch.CashierName "
				+ "FROM transactionheader th join transactiondetail td on th.TransactionID = td.TransactionID "
				+ "join barang br on br.BarangID = td.BarangID "
				+ "join cashier ch on ch.CashierID = th.CashierID "
				+ "join mekanik mk on mk.MekanikID = th.MekanikID "
				+ "join customer cs on cs.CustomerID = td.CustomerID "
				+ "WHERE th.TransactionID = '%s' ;", transactionID);
		ResultSet rs = connect.executeQuery(query);
		
		Vector<String> customerNameList = new Vector<>();
		Vector<String> transactionIDList = new Vector<>();
		Vector<String> transactionDateList = new Vector<>();
		Vector<String> mekanikNameList = new Vector<>();
		Vector<String> customerVPList = new Vector<>();
		Vector<String> barangIDList = new Vector<>();
		Vector<String> barangNameList = new Vector<>();
		Vector<String> quantityList = new Vector<>();
		Vector<String> barangHargaList = new Vector<>();
		Vector<String> cashierNameList = new Vector<>();
		
		try {
			while (rs.next()) {
				customerNameList.add(rs.getString("CustomerName"));
				transactionIDList.add(rs.getString("TransactionID"));
				transactionDateList.add(rs.getString("TransactionID"));
				mekanikNameList.add(rs.getString("MekanikName"));
				customerVPList.add(rs.getString("CustomerVP"));
				barangIDList.add(rs.getString("BarangID"));
				barangNameList.add(rs.getString("BarangName"));
				quantityList.add(rs.getString("Quantity"));
				barangHargaList.add(rs.getString("BarangHarga"));
				cashierNameList.add(rs.getString("CashierName"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("=========================================================================================================================================================================");
        System.out.println("| Customer Name    | Transaction ID   | Transaction Date | Mekanik Name    | Customer VP   | Barang ID    | Barang Name    | Quantity   | Barang Harga | Cashier Name   |");
        System.out.println("=========================================================================================================================================================================");
        
        for (int i = 0; i < transactionIDList.size(); i++) {
            System.out.printf("| %-16s | %-16s | %-16s | %-15s | %-13s | %-12s | %-14s | %-10s | %-12s | %-14s |\n",
                    customerNameList.get(i), transactionIDList.get(i), transactionDateList.get(i),
                    mekanikNameList.get(i), customerVPList.get(i), barangIDList.get(i),
                    barangNameList.get(i), quantityList.get(i), barangHargaList.get(i), cashierNameList.get(i));
        }
        System.out.println("=========================================================================================================================================================================");
		
	}

	private void updateStatusMekanik() {
		String transactionIDsearch;
		int statusDecision;
		
	
		viewTransactionHeader();
		
		do {
			System.out.println("Input TransactionID : ");
			transactionIDsearch = scan.nextLine();
		} while (transactionIDsearch.isBlank() || transactionIDsearch.isEmpty());
		
		do {
			viewTransactionHeader();
			System.out.println("Update menjadi apa?");
			System.out.println("1. Sedang Dikerjakan");
			System.out.println("2. Selesai");
			System.out.println("3. Dibatalkan");
			System.out.println("4. Exit");
			statusDecision = scan.nextInt(); scan.nextLine();
			if (statusDecision == 1) {
				String query = String.format("UPDATE TransactionHeader SET Status = 'Sedang Dikerjakan' WHERE TransactionID = '%s'", transactionIDsearch);
				connect.executeUpdate(query);
			} else if (statusDecision == 2) {
				
				int updateKMterakhir;
				int currentKM = 0;
				String query = String.format("SELECT KMTerakhir FROM TransactionHeader WHERE TransactionID = '%s'", transactionIDsearch);
				
				ResultSet rs = connect.executeQuery(query);
				
				
				
				try {
					while (rs.next()) {
						currentKM = rs.getInt("KMTerakhir");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				do {
					System.out.println("Input KM Selanjutnya : ");
					updateKMterakhir = scan.nextInt(); scan.nextLine();
					if (currentKM > updateKMterakhir) {
						System.out.println("Tidak boleh lebih kecil dari KM sebelumnya");
					}
				} while (currentKM > updateKMterakhir);
				
				query = String.format("UPDATE TransactionHeader SET Status = 'Selesai' WHERE TransactionID = '%s'", transactionIDsearch);
				connect.executeUpdate(query);
				
				query = String.format("UPDATE TransactionHeader SET KMTerakhir = '%d' WHERE TransactionID = '%s'", updateKMterakhir,transactionIDsearch);
				connect.executeUpdate(query);
				
				
			} if (statusDecision == 3) {
				String query = String.format("UPDATE TransactionHeader SET Status = 'Dibatalkan' WHERE TransactionID = '%s'", transactionIDsearch);
				connect.executeUpdate(query);
			}
			
		} while (statusDecision != 4);
		
		
	}
		
	private void viewUC_Transaction() {
		String query = "SELECT * FROM UC_Transaction";
		
		ResultSet rs = connect.executeQuery(query);
		
		Vector<String> UCtransactionIDList = new Vector<>();
		Vector<String> UCtransactionDateList = new Vector<>();
		Vector<String> UCstatusList = new Vector<>();
		Vector<String> UCkeluhanList = new Vector<>();
		Vector<Integer> UCKMterakhirList = new Vector<>();
	
		
		try {
			while (rs.next()) {
				UCtransactionIDList.add(rs.getString("TransactionID"));
				UCtransactionDateList.add(rs.getString("TransactionDate"));
				UCstatusList.add(rs.getString("StatusPemesanan"));
				UCkeluhanList.add(rs.getString("Keluhan"));
				UCKMterakhirList.add(rs.getInt("KMTerakhir"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("=====================================================================================");
		System.out.println("| ID Transaction | Tgl Transaction|    Status     |     Keluhan    |  KM Terakhir   |");
		System.out.println("=====================================================================================");
		
		for (int i = 0; i < UCtransactionIDList.size(); i++) {
	        System.out.printf("| %-14s | %-14s | %-14s | %-14s | %-14d |\n",
	        		UCtransactionIDList.get(i), UCtransactionDateList.get(i),UCstatusList.get(i), UCkeluhanList.get(i), UCKMterakhirList.get(i));
	    }
		System.out.println("=====================================================================================");
	}
	
	private void viewUC_TransactionDetail() {
		viewUC_Transaction();
		
		System.out.println("Input the TransactionID that you want to see the details");
		String transactionID = scan.nextLine();
		
		String query = String.format("SELECT cs.CustomerName, th.TransactionID , th.TransactionDate , cs.CustomerVP , td.BarangID , td.BarangName , td.Quantity, td.BarangHarga "
				+ "FROM UC_transaction th join UC_transactiondetail td on th.TransactionID = td.TransactionID "
				+ "join barang br on br.BarangID = td.BarangID "
				+ "join customer cs on cs.CustomerID = td.CustomerID "
				+ "WHERE th.TransactionID = '%s' ;", transactionID);
		ResultSet rs = connect.executeQuery(query);
		
		Vector<String> UCcustomerNameList = new Vector<>();
		Vector<String> UCtransactionIDList = new Vector<>();
		Vector<String> UCtransactionDateList = new Vector<>();
		Vector<String> UCcustomerVPList = new Vector<>();
		Vector<String> UCbarangIDList = new Vector<>();
		Vector<String> UCbarangNameList = new Vector<>();
		Vector<String> UCquantityList = new Vector<>();
		Vector<String> UCbarangHargaList = new Vector<>();
		
		
		try {
			while (rs.next()) {
				UCcustomerNameList.add(rs.getString("CustomerName"));
				UCtransactionIDList.add(rs.getString("TransactionID"));
				UCtransactionDateList.add(rs.getString("TransactionID"));
				UCcustomerVPList.add(rs.getString("CustomerVP"));
				UCbarangIDList.add(rs.getString("BarangID"));
				UCbarangNameList.add(rs.getString("BarangName"));
				UCquantityList.add(rs.getString("Quantity"));
				UCbarangHargaList.add(rs.getString("BarangHarga"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("======================================================================================================================================");
        System.out.println("| Customer Name    | Transaction ID   | Transaction Date | Customer VP   | Barang ID    | Barang Name    | Quantity   | Barang Harga |");
        System.out.println("======================================================================================================================================");
        
        for (int i = 0; i < UCtransactionIDList.size(); i++) {
            System.out.printf("| %-16s | %-16s | %-16s | %-13s | %-12s | %-14s | %-10s | %-12s |\n",
            		UCcustomerNameList.get(i), UCtransactionIDList.get(i), UCtransactionDateList.get(i),
            		UCcustomerVPList.get(i), UCbarangIDList.get(i),
            		UCbarangNameList.get(i), UCquantityList.get(i), UCbarangHargaList.get(i));
        }
        System.out.println("======================================================================================================================================");
		
	}
	
	private void confirmOrder() {
		String searchTransactionID;
		String searchCashierID;
		String searchMekanikID;
		String insertMigrationUCTQuery;
		String deletePreviousDateQuery;
		String transactionDate = null;
		String status = "Menunggu Pembayaran";
		
		String transactionDateQuery = "SELECT CURDATE()";
		ResultSet TDQ = connect.executeQuery(transactionDateQuery);
		
		try {
			if (TDQ.next()) {
				transactionDate = TDQ.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String transactionID = null;
		String mekanikID = null;
		String cashierID = null;
		
		do {
			viewUC_Transaction();
			System.out.println("Input TransactionID yang ingin di konfirmasi");
			searchTransactionID = scan.nextLine();
		} while (searchTransactionID.isEmpty());
		
			do {
				viewCashier();
				System.out.println("Input ID kasir : ");
				searchCashierID = scan.nextLine();
			} while (searchCashierID.isBlank() || searchCashierID.isEmpty());
			
			do {
				viewMekanik();
				System.out.println("Input ID mekanik : ");
				searchMekanikID = scan.nextLine();
			} while (searchMekanikID.isBlank() || searchMekanikID.isEmpty());
			
			String transactionIDQuery = String.format("SELECT TransactionID FROM UC_Transaction WHERE TransactionID = '%s'", searchTransactionID);
			
			ResultSet TIQ = connect.executeQuery(transactionIDQuery);
			
			try {
				if (TIQ.next()) {
					transactionID = TIQ.getString("TransactionID");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String cashierIDQuery = String.format("SELECT CashierID FROM Cashier WHERE CashierID = '%s'", searchCashierID);
			
			ResultSet CIQ = connect.executeQuery(cashierIDQuery);
			
			try {
				if (CIQ.next()) {
					cashierID = CIQ.getString("CashierID");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String mekanikIDQuery = String.format("SELECT MekanikID FROM Mekanik WHERE MekanikID = '%s'", searchMekanikID);
			
			ResultSet MIQ = connect.executeQuery(mekanikIDQuery);
			
			try {
				if (MIQ.next()) {
					mekanikID = MIQ.getString("MekanikID");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//bagian kebawah bermasalah FIND FIX!
			insertMigrationUCTQuery = String.format("INSERT INTO TransactionHeader(TransactionID, TransactionDate, CashierID, MekanikID, Status, Keluhan, KMTerakhir)"
					+ " VALUES('%s','%s','%s','%s',"
					+ "(SELECT StatusPemesanan FROM UC_Transaction WHERE TransactionID = '%s'),"
					+ "(SELECT Keluhan FROM UC_Transaction WHERE TransactionID = '%s'),"
					+ "(SELECT KMTerakhir FROM UC_Transaction WHERE TransactionID = '%s'))",  
					transactionID, transactionDate, cashierID, mekanikID, transactionID, transactionID, transactionID);
			
			connect.executeUpdate(insertMigrationUCTQuery);
			
			deletePreviousDateQuery = String.format("DELETE FROM UC_Transaction WHERE transactionID = '%s'", transactionID);
			connect.executeUpdate(deletePreviousDateQuery);
			
			String insertingDetailQuery = String.format("INSERT INTO TransactionDetail(CustomerID, TransactionID, Quantity, BarangID, BarangName, BarangHarga) "
					+ "SELECT CustomerID, TransactionID, Quantity, BarangID, BarangName, BarangHarga FROM UC_TransactionDetail WHERE TransactionID = '%s'", transactionID);
			
			connect.executeUpdate(insertingDetailQuery);
			
			deletePreviousDateQuery = String.format("DELETE FROM UC_TransactionDetail WHERE transactionID = '%s'", transactionID);
			connect.executeUpdate(deletePreviousDateQuery);
			
		
	}
	
	public static void main(String[] args) {
		new Main();

	}

}
