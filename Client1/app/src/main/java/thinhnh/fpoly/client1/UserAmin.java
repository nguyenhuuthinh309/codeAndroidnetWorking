package thinhnh.fpoly.client1;

import java.io.Serializable;

public class UserAmin implements Serializable {
    private  int id;
    private  String email;
    private  String mk;
    private  String tensan;

    public UserAmin(int id, String email, String mk, String tensan) {

        this.id = id;
        this.email = email;
        this.mk = mk;
        this.tensan = tensan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }

    public String getTensan() {
        return tensan;
    }

    public void setTensan(String tensan) {
        this.tensan = tensan;
    }

    @Override
    public String toString() {
        return "UserAmin{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", mk='" + mk + '\'' +
                ", tensan='" + tensan + '\'' +
                '}';
    }
}
