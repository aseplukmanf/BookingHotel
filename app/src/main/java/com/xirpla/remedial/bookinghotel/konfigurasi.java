package com.xirpla.remedial.bookinghotel;

public class konfigurasi {

    public static final String URL_UPDATE_EMP = "http://192.168.43.228/api_android/htl/sudahBayar.php";
    public static final String URL_GET_ALL= "http://192.168.43.228/api_android/htl/data_belum_dibayar.php";
    public static final String URL_GET_EMP = "http://192.168.43.228/api_android/htl/tampilTransaksi.php?id_transaksi=";


    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_EMP_ID = "id_transaksi";
    public static final String KEY_EMP_NAMA = "nama_lengkap";
    public static final String KEY_EMP_NAMA_HOTEL = "nama_hotel";
    public static final String KEY_EMP_KETERANGAN = "keterangan";
    public static final String KEY_EMP_ALAMAT = "alamat";
    public static final String KEY_EMP_ALAMAT_HOTEL = "alamat_hotel";
    public static final String KEY_EMP_CHECK_IN = "check_in";
    public static final String KEY_EMP_CHECK_OUT = "check_out";
    public static final String KEY_EMP_STATUS = "status";
    public static final String KEY_EMP_PEMBAYARAN = "pembayaran";
    public static final String KEY_EMP_HARGA = "harga";
    public static final String KEY_EMP_JML_TMU = "jml_tamu";
    public static final String KEY_EMP_LAMA_MENG = "lama_menginap";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="transaksi";
    public static final String TAG_ID = "id_transaksi";
    public static final String TAG_NAMA = "nama_lengkap";
    public static final String TAG_NAMA_HOTEL = "nama_hotel";
    public static final String TAG_KETERANGAN = "keterangan";
    public static final String TAG_ALAMAT = "alamat";
    public static final String TAG_ALAMAT_HOTEL = "alamat_hotel";
    public static final String TAG_CHECK_IN = "check_in";
    public static final String TAG_CHECK_OUT = "check_out";
    public static final String TAG_STATUS = "status";
    public static final String TAG_PEMBAYARAN = "pembayaran";
    public static final String TAG_HARGA = "harga";
    public static final String TAG_JML_TMU = "jml_tamu";
    public static final String TAG_LAMA_MENG = "lama_menginap";

    //ID karyawan
    //emp itu singkatan dari Employee
    public static final String EMP_ID = "emp_id_transaksi";
}
