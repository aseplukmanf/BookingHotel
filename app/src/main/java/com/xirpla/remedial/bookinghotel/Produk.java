package com.xirpla.remedial.bookinghotel;

import android.os.Parcel;
import android.os.Parcelable;

public class Produk implements Parcelable {
    private String name, remarks, photo,harga;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String name) {
        this.harga = harga;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.remarks);
        dest.writeString(this.photo);
    }

    public Produk() {
    }
    protected Produk(Parcel in) {
        this.name = in.readString();
        this.remarks = in.readString();
        this.photo = in.readString();
    }
    public static final Creator<Produk> CREATOR = new Creator<Produk>() {
        @Override
        public Produk createFromParcel(Parcel source) {
            return new Produk(source);
        }
        @Override
        public Produk[] newArray(int size) {
            return new Produk[size];
        }
    };
}
