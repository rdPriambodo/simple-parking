package finalsbd;

import java.sql.*;

public class TempatParkir {

    koneksi Connection = new koneksi();
    Connection conn = Connection.getKoneksi();

    //fungsi untuk CRUD
    public void createRecord(String blok, String lantai, String no_lokasi, boolean status) throws SQLException {
        String sql = "INSERT INTO tempat_parkir (id_parkir, blok, lantai, no_lokasi, status) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, lantai + "_" + blok + "_" + no_lokasi);
        statement.setString(2, blok);
        statement.setString(3, lantai);
        statement.setString(4, no_lokasi);
        statement.setBoolean(5, status);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("insert tempat_parkir berhasil");
        }
    }

    public ResultSet getAll() throws SQLException {
        String sql = "SELECT * FROM tempat_parkir";

        Statement statement = conn.createStatement();
        return statement.executeQuery(sql);
    }

    public ResultSet getRecord(String id_parkir) throws SQLException {
        String sql = "SELECT * FROM tempat_parkir WHERE id_parkir=?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, id_parkir);
        return statement.executeQuery();
    }

    public void updateRecord(String blok, String lantai, String no_lokasi, boolean status) throws SQLException {
        String sql = "UPDATE tempat_parkir SET blok=?, lantai=?, no_lokasi=?, status=? WHERE id_parkir=?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, blok);
        statement.setString(2, lantai);
        statement.setString(3, no_lokasi);
        statement.setBoolean(4, status);
        statement.setString(5, lantai.replace(" ", "") + "_" + blok.replace(" ", "") + "_" + no_lokasi.replace(" ", ""));

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("data berhasil di update");
        }
    }

    public void deleteRecord(String id_parkir) throws SQLException {
        String sql = "DELETE FROM tempat_parkir WHERE id_parkir=?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, id_parkir);
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("record berhasil dihapus");
        }
    }

    public boolean parkirPenuh() throws SQLException {
        boolean penuh = true;
        ResultSet rs = getAll();

        while (rs.next()) {
            if (!rs.getBoolean("status")) {
                penuh = false;
                break;
            }
        }

        return penuh;
    }
    
    public boolean hasRecord(String id_parkir) throws SQLException {
        ResultSet rs = getRecord(id_parkir);

        return rs.next();
    }

}
