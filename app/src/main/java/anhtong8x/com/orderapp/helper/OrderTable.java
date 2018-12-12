package anhtong8x.com.orderapp.helper;

/**
 * Created by Administrator on 12/4/2018.
 */

public class OrderTable {
    public int id;
    public String title;
    public int status;

    public OrderTable() {
    }

    public OrderTable(int id, String title, int status) {
        this.title = title;
        this.id = id;
        this.status = status;
    }
}
