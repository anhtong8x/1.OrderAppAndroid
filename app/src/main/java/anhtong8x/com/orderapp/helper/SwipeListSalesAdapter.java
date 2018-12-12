package anhtong8x.com.orderapp.helper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import anhtong8x.com.orderapp.R;
import anhtong8x.com.orderapp.model.Table.OrderTableResponse;

/**
 * Created by Administrator on 12/4/2018.
 */

public class SwipeListSalesAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<OrderTableResponse> lst;
    private String[] bgColors;

    public SwipeListSalesAdapter(Activity activity, List<OrderTableResponse> lst) {
        this.activity = activity;
        this.lst = lst;
        bgColors = activity.getApplicationContext().getResources().getStringArray(R.array.movie_serial_bg);
    }

    @Override
    public int getCount() {
        return lst.size();
    }

    @Override
    public Object getItem(int position) {
        return lst.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row_sales, null);

        TextView serial = (TextView) convertView.findViewById(R.id.serial);
        TextView title = (TextView) convertView.findViewById(R.id.title);

        // set id ban neu id < 10 thi + 0
        String str = "";
        int id = lst.get(position).getId();
        if(id < 10) { str = "0" + id;}
        else{str = String.valueOf(id); }
        serial.setText(str);

        title.setText(lst.get(position).getName());

        // set color idTable
        String color = "#24c6d5"; // ban khong co khac
        int status = lst.get(position).getStatus();
        if(status == 2){
            // ban dang co khach
            color = "#fcba59";
        }else if(status == 3){
            // ban dang ban
            color = "#ff484d";
        }
        serial.setBackgroundColor(Color.parseColor(color));

        return convertView;
    }
}
