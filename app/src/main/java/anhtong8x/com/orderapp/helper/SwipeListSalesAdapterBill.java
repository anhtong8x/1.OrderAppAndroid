package anhtong8x.com.orderapp.helper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import anhtong8x.com.orderapp.R;

/**
 * Created by Administrator on 12/5/2018.
 */

public class SwipeListSalesAdapterBill extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<OrderBill> lst;

    public SwipeListSalesAdapterBill(Activity activity, List<OrderBill> lst) {
        this.activity = activity;
        this.lst = lst;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row_bill, null);
        TextView title = (TextView) convertView.findViewById(R.id.title);

        // find view
        final CheckBox radFinish = (CheckBox)convertView.findViewById(R.id.rad_finish);
        final CheckBox radPending = (CheckBox)convertView.findViewById(R.id.rad_pending);
        final TextView txtQuanity = (TextView) convertView.findViewById(R.id.txt_quanity);
        Button btnSub = (Button)convertView.findViewById(R.id.btnSub);
        Button btnAdd = (Button)convertView.findViewById(R.id.btnAdd);

        // set text
        title.setText(lst.get(position).NameFood);
        radFinish.setText("Da hoan thanh: " + lst.get(position).FinihFood);
        radPending.setText("Dang doi: " + lst.get(position).PendingFood);
        txtQuanity.setText("" + lst.get(position).Quanity);

        radFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtQuanity.setText("" + lst.get(position).FinihFood);
                radPending.setChecked(false);
            }
        });

        radPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtQuanity.setText("" + lst.get(position).PendingFood);
                radFinish.setChecked(false);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            // Click Finish
            // Tang dang xong thi giam dang doi
            if(radFinish.isChecked() && radPending.isChecked() == false  ){
                lst.get(position).FinihFood = lst.get(position).FinihFood + 1;
                if(lst.get(position).PendingFood > 0) {
                    lst.get(position).PendingFood = lst.get(position).PendingFood - 1;
                }

                txtQuanity.setText("" + lst.get(position).FinihFood);
                radFinish.setText("Da hoan thanh: " + lst.get(position).FinihFood);
                radPending.setText("Dang doi: " + lst.get(position).PendingFood);
            }
            // Click Pending
            if (radFinish.isChecked() == false && radPending.isChecked()){
                lst.get(position).PendingFood = lst.get(position).PendingFood + 1;
                radPending.setText("Dang doi: " + lst.get(position).PendingFood);
                txtQuanity.setText("" + lst.get(position).PendingFood);
            }

            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Click Finish
                // Giam da xong thi tang dang doi
                if(radFinish.isChecked() && radPending.isChecked() == false  ){
                    if(lst.get(position).FinihFood == 0) return;

                    lst.get(position).FinihFood = lst.get(position).FinihFood - 1;
                    lst.get(position).PendingFood = lst.get(position).PendingFood + 1;

                    radFinish.setText("Da hoan thanh: " + lst.get(position).FinihFood);
                    radPending.setText("Dang doi: " + lst.get(position).PendingFood);
                    txtQuanity.setText("" + lst.get(position).FinihFood);
                }

                // Click Pending
                if (radFinish.isChecked() == false && radPending.isChecked()){
                    if(lst.get(position).PendingFood == 0) return;

                    lst.get(position).PendingFood = lst.get(position).PendingFood - 1;
                    radPending.setText("Dang doi: " + lst.get(position).PendingFood);
                    txtQuanity.setText("" + lst.get(position).PendingFood);
                }
            }
        });

        return convertView;

    }
}
