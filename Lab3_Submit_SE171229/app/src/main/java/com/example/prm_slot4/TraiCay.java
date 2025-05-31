package com.example.prm_slot4;

public class TraiCay {
    private String Ten;
    private String MoTa;
    private int Hinh;

    public TraiCay(String ten, String mota, int hinh) {
        Ten = ten;
        MoTa = mota;
        Hinh = hinh;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getMota() {
        return MoTa;
    }

    public void setMota(String mota) {
        MoTa = mota;
    }

    public int getHinh() {
        return Hinh;
    }

    public void setHinh(int hinh) {
        Hinh = hinh;
    }
}
