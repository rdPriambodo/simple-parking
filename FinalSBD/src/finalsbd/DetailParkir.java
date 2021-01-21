package finalsbd;

import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class DetailParkir {

    koneksi Connection = new koneksi();
    Connection conn = Connection.getKoneksi();

    //fungsi untuk CRUD
    public void createRecord(String no_kartu, int tarif, String petugas, String plat, String id_parkir, Timestamp waktu_masuk, Timestamp waktu_keluar) throws SQLException {
        String sql = "INSERT INTO detail_parkir (no_kartu, tarif, petugas, plat, id_parkir, waktu_masuk, waktu_keluar) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, no_kartu);
        statement.setInt(2, tarif);
        statement.setString(3, petugas);
        statement.setString(4, plat);
        statement.setString(5, id_parkir);
        statement.setTimestamp(6, waktu_masuk);
        statement.setTimestamp(7, waktu_keluar);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("insert detail_parkir berhasil");
        }
    }

    public ResultSet getAll() throws SQLException {
        String sql = "SELECT * FROM detail_parkir";

        Statement statement = conn.createStatement();
        return statement.executeQuery(sql);
    }

    public ResultSet getRecord(String no_kartu) throws SQLException {
        String sql = "SELECT * FROM detail_parkir WHERE no_kartu=?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, no_kartu);
        return statement.executeQuery();
    }

    public void updateRecord(String no_kartu, int tarif, String petugas, String plat, String id_parkir, Timestamp waktu_masuk, Timestamp waktu_keluar) throws SQLException {
        String sql = "UPDATE detail_parkir SET tarif=?, petugas=?, plat=?, id_parkir=?, waktu_masuk=?, waktu_keluar=? WHERE no_kartu=?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, tarif);
        statement.setString(2, petugas);
        statement.setString(3, plat);
        statement.setString(4, id_parkir);
        statement.setTimestamp(5, waktu_masuk);
        statement.setTimestamp(6, waktu_keluar);
        statement.setString(7, no_kartu);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("data berhasil di update");
        }
    }

    public void deleteRecord(String no_kartu) throws SQLException {
        String sql = "DELETE FROM detail_parkir WHERE no_kartu=?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, no_kartu);
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("record berhasil dihapus");
        }
    }

    //fungsi buat transaksi
    public void masukParkir(String petugas, String plat) throws SQLException {
        //datetime dijadiin id
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd:MM:HH:mm:ss");
        String no_kartu = time.format(myFormatObj).replace(":", "");

        //mendapatkan waktu masuk
        LocalDateTime masuk = LocalDateTime.now();
        Timestamp waktu_masuk = Timestamp.valueOf(masuk);

        //mencari tempat parkir kosong
        String sql = "SELECT TOP 1 id_parkir FROM tempat_parkir WHERE status=0";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        String id_parkir = rs.getString("id_parkir");

        //membuat record di detail_parkir
        createRecord(no_kartu, 0, petugas, plat, id_parkir, waktu_masuk, null);

        //mengupdate status tempat parkir
        String query = "UPDATE tempat_parkir SET status=1 WHERE id_parkir=?";
        PreparedStatement statement2 = conn.prepareStatement(query);
        statement2.setString(1, id_parkir);
        statement2.executeUpdate();
    }

    public void keluarParkir(String no_kartu) throws SQLException {
        //mendapatkan waktu keluar
        LocalDateTime keluar = LocalDateTime.now();
        Timestamp waktu_keluar = Timestamp.valueOf(keluar);

        //mengupdate waktu keluar
        String sql = "UPDATE detail_parkir SET waktu_keluar=? WHERE no_kartu=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setTimestamp(1, waktu_keluar);
        statement.setString(2, no_kartu);
        statement.executeUpdate();

        ResultSet rs = getRecord(no_kartu);
        rs.next();

        //mengupdate status tempat parkir
        String query = "UPDATE tempat_parkir SET status=0 WHERE id_parkir=?";
        PreparedStatement statement2 = conn.prepareStatement(query);
        statement2.setString(1, rs.getString("id_parkir"));
        statement2.executeUpdate();
    }

    public int biayaParkir(String no_kartu) throws SQLException {
        //mendapatkan waktu masuk dan keluar
        ResultSet rs = getRecord(no_kartu);
        rs.next();
        Timestamp masuk = rs.getTimestamp("waktu_masuk");
        Timestamp keluar = rs.getTimestamp("waktu_keluar");

        //menghitung lama parkir
        long lama_parkir = keluar.getTime() - masuk.getTime();
        int seconds = (int) lama_parkir / 1000;
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        seconds = (seconds % 3600) % 60;
        if (minutes > 0 || seconds > 0) {
            hours++;
        }

        //menghitung biaya
        int biaya = 0;
        if (hours <= 4) {
            biaya = 10000;
        } else if (hours <= 8) {
            biaya = 15000;
        } else if (hours <= 12) {
            biaya = 20000;
        } else {
            int tambahan = 10000 * (hours - 12);
            biaya = 20000 + tambahan;
        }

        //update tarif
        String sql = "UPDATE detail_parkir SET tarif=? WHERE no_kartu=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, biaya);
        statement.setString(2, no_kartu);
        statement.executeUpdate();

        return biaya;
    }

    public boolean hasRecord(String no_kartu) throws SQLException {
        ResultSet rs = getRecord(no_kartu);

        return rs.next();
    }

}
