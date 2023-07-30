package thinhnh.fpoly.myapplication;

public class Prd {
    private String  manv,tennv,diemtb,anh;

    public Prd() {
    }

    public Prd(String manv, String tennv, String diemtb, String anh) {
        this.manv = manv;
        this.tennv = tennv;
        this.diemtb = diemtb;
        this.anh = anh;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public String getDiemtb() {
        return diemtb;
    }

    public void setDiemtb(String diemtb) {
        this.diemtb = diemtb;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }
}
