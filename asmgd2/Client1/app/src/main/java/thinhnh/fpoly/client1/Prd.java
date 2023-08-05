package thinhnh.fpoly.client1;

public class Prd {
    private int id;
    private String  tenkhach, sdt,ngaythue,khunggio,tongtien,trangthai;

    public Prd() {
    }

    public Prd(int id, String tenkhach, String sdt, String ngaythue, String khunggio, String tongtien, String trangthai) {
        this.id = id;
        this.tenkhach = tenkhach;
        this.sdt = sdt;
        this.ngaythue = ngaythue;
        this.khunggio = khunggio;
        this.tongtien = tongtien;
        this.trangthai = trangthai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenkhach() {
        return tenkhach;
    }

    public void setTenkhach(String tenkhach) {
        this.tenkhach = tenkhach;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getNgaythue() {
        return ngaythue;
    }

    public void setNgaythue(String ngaythue) {
        this.ngaythue = ngaythue;
    }

    public String getKhunggio() {
        return khunggio;
    }

    public void setKhunggio(String khunggio) {
        this.khunggio = khunggio;
    }

    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
}
