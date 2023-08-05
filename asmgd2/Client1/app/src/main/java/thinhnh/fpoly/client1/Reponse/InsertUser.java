package thinhnh.fpoly.client1.Reponse;

import thinhnh.fpoly.client1.DTO.UserAmin;

public class InsertUser {
    private UserAmin UserAmin;//khong duoc viet sai ten bang
    private String message;

    public thinhnh.fpoly.client1.DTO.UserAmin getUserAmin() {
        return UserAmin;
    }

    public String getMessage() {
        return message;
    }
}
