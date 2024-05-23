package main;


import connect.Connect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public interface BarangViewer {
	
	@SuppressWarnings("exports")
	Connect connect = Connect.getConnection();
	
		default void viewBarang() {
//			String blank = "";
			String query = "SELECT * FROM Barang";
			ResultSet rs = connect.executeQuery(query);
//			String query_size = "SELECT COUNT(BarangID) FROM Barang";
//			ResultSet qs = connect.executeQuery(query_size);
		
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
//			System.out.printf("|ID Barang %7s|Nama Barang %7s|Harga Barang %7s|Stock Barang %7s|\n", blank, blank, blank, blank);
			
			for (int i = 0; i < barangIDlist.size(); i++) {
		        System.out.printf("| %-12s | %-12s | %-12d | %-12d |\n",
		                barangIDlist.get(i), barangNamelist.get(i), barangPricelist.get(i), barangStocklist.get(i));
		    }
			System.out.println("=============================================================");
			
		
	}
}
