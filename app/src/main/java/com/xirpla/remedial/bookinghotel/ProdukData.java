package com.xirpla.remedial.bookinghotel;

import java.util.ArrayList;

public class ProdukData {
    public static String[][] data = new String[][]{
            {"Ibis Budget Bandung Asia Afrika", "Jalan Asia Afrika No. 128, Pusat Kota Bandung, Asia Afrika, Bandung, Jawa Barat, Indonesia, 40261","280000", "https://d1nabgopwop1kh.cloudfront.net/hotel-asset/30000002000032563_wh_3"},
            {"Grand Asrilia Hotel Convention dan Restaurant", "Jalan Pelajar Pejuang 45 No. 123, Lengkong, Bandung, Jawa Barat, Indonesia, 40264 ","351000", "https://d1nabgopwop1kh.cloudfront.net/hotel-asset/30000002000269598_wh_29"},
            {"Serela Merdeka Hotel", "Jl. Purnawarman No. 23 Bandung, Dago, Bandung, Jawa Barat, Indonesia, 40111","433000", "https://d1nabgopwop1kh.cloudfront.net/hotel-asset/30000001000009640_wh_3"},
            };

    public static ArrayList<Produk> getListData(){
        Produk produk = null;
        ArrayList<Produk> list = new ArrayList<>();
        for (String[] aData : data) {
            produk = new Produk();
            produk.setName(aData[0]);
            produk.setRemarks(aData[1]);
            produk.setHarga(aData[2]);
            produk.setPhoto(aData[3]);

            list.add(produk);
        }

        return list;
    }
}
