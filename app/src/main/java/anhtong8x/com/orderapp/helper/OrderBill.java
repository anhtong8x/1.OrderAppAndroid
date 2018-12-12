package anhtong8x.com.orderapp.helper;

/**
 * Created by Administrator on 12/5/2018.
 */

public class OrderBill {
    public int IdBill;
    public String NameFood;
    public int FinihFood;
    public int PendingFood;
    public int Quanity;

    public OrderBill(int idBill, String nameFood, int finihFood, int pendingFood, int quanity) {
        IdBill = idBill;
        NameFood = nameFood;
        FinihFood = finihFood;
        PendingFood = pendingFood;
        Quanity = quanity;


    }
}
