package anhtong8x.com.orderapp.helper;

import android.app.Application;

public class MyApplication extends Application {
    private String token;
    private int idUser;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
