package finalsbd;

import java.sql.*;

public class test {

    public static void main(String[] args) throws SQLException {
//        TempatParkir tempat = new TempatParkir();
//        tempat.createRecord("A", "1", "1", false);
//        tempat.createRecord("A", "1", "2", false);
//        tempat.updateRecord("A", "1", "2", true);
//        ResultSet all = tempat.getAll();
//        while (all.next()) {
//            String id = all.getString("id_parkir");
//            String lantai = all.getString("lantai");
//            String blok = all.getString("blok");
//            String no_lokasi = all.getString("no_lokasi");
//            Boolean status = all.getBoolean("status");
//
//            String output = "tempat #%s: %s - %s - %s - %b";
//            System.out.println(String.format(output, id, lantai, blok, no_lokasi, status));
//        }
//
//        tempat.deleteRecord("1A2");
//
//        ResultSet tempat1 = tempat.getRecord("1A1");
//        tempat1.next();
//        String id = tempat1.getString("id_parkir");
//        String lantai = tempat1.getString("lantai");
//        String blok = tempat1.getString("blok");
//        String no_lokasi = tempat1.getString("no_lokasi");
//        Boolean status = tempat1.getBoolean("status");
//
//        String output = "tempat #%s: %s - %s - %s - %b";
//        System.out.println(String.format(output, id, lantai, blok, no_lokasi, status));
//
//        System.out.println("---------------------------------------------------------------");
        DetailParkir detail = new DetailParkir();
//        detail.createRecord("mobil1", 3000, "Budi", "B 12345", "1A1", null, null);
//        detail.createRecord("mobil2", 3000, "Joko", "B 54321", "1A1", null, null);
//        detail.updateRecord("mobil2", 5000, "Joko", "B 54321", "1A1", null, null);
//
//        ResultSet details = detail.getAll();
//        while (details.next()) {
//            String no_kartu = details.getString("no_kartu");
//            int tarif = details.getInt("tarif");
//            String petugas = details.getString("petugas");
//            String plat = details.getString("plat");
//            Boolean id_parkir = details.getBoolean("id_parkir");
//
//            output = "tempat #%s: %x - %s - %s - %b";
//            System.out.println(String.format(output, no_kartu, tarif, petugas, plat, id_parkir));
//        }
//
//        detail.deleteRecord("mobil2");
//
//        ResultSet detail1 = detail.getRecord("mobil1");
//        detail1.next();
//        String no_kartu = detail1.getString("no_kartu");
//        int tarif = detail1.getInt("tarif");
//        String petugas = detail1.getString("petugas");
//        String plat = detail1.getString("plat");
//        Boolean id_parkir = detail1.getBoolean("id_parkir");
//
//        System.out.println(String.format(output, no_kartu, tarif, petugas, plat, id_parkir));
//        
//        detail.deleteRecord("mobil1");
//        tempat.deleteRecord("1A1");
//        System.out.println("-------------------------------------------------------------------------------------");
//        
////        tempat.createRecord("1A", "A", "1", "1", false);
////        tempat.createRecord("2A", "A", "1", "2", false);
////        tempat.createRecord("3A", "A", "1", "3", false);
////        tempat.createRecord("4A", "A", "1", "4", false);
//        
//        //detail.masukParkir("Andi", "B 12345");
//        //detail.masukParkir("Rama", "B 12345");
//        //detail.masukParkir("Dika", "B 12345");
//        //detail.keluarParkir("08:58:41  ");
//        //detail.keluarParkir("08:57:54  ");
        System.out.println(detail.biayaParkir("1501105828"));
//        System.out.println(detail.biayaParkir("08:57:54  "));

    }

}
