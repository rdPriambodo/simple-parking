package finalsbd;

import java.sql.*;

public class koneksi {

    private Connection koneksi;

    public Connection getKoneksi() {
        if (koneksi == null) {
            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                System.out.println("Class Driver Ditemukan");
                try {
                    String url = "jdbc:jtds:sqlserver://127.0.0.1:1433/SBD_Parkir";
                    koneksi = DriverManager.getConnection(url, "tes", "123");
                    System.out.println("Koneksi Database sukses");
                } catch (SQLException se) {
                    System.out.println("Koneksi Database Gagal error:" + se);
                    System.exit(0);
                }
            } catch (ClassNotFoundException cnfe) {
                System.out.println("Class tidak ditemukan, error: " + cnfe);
                System.exit(0);
            }
        }
        return koneksi;
    }
}
