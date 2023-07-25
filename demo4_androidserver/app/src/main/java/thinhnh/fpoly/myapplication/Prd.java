package thinhnh.fpoly.myapplication;

public class Prd {
    private String  Idsp, tensp,giasp,soluong;

    public Prd() {
    }

    public Prd(String idsp, String tensp, String giasp, String soluong) {
        Idsp = idsp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.soluong = soluong;
    }

    public String getIdsp() {
        return Idsp;
    }

    public void setIdsp(String idsp) {
        Idsp = idsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getGiasp() {
        return giasp;
    }

    public void setGiasp(String giasp) {
        this.giasp = giasp;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }
}
