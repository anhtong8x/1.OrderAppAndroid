package anhtong8x.com.orderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import anhtong8x.com.orderapp.helper.OderMenuAdapter;
import anhtong8x.com.orderapp.helper.OrderBill;
import anhtong8x.com.orderapp.helper.SwipeListSalesAdapterBill;

public class OrderMenuActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private List<OrderBill> lst;
    private ListView listView;
    private OderMenuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_menu);

        // id
        int _idTable = getIntent().getIntExtra("idTable",0);
        int _idBill_1 = getIntent().getIntExtra("idBill_1",0);
        //Toast.makeText(this, "" + _id, Toast.LENGTH_LONG).show();

        // toolbar
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Menu Quan");

        // group order. menu spinner chon nhom mon

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.notifyDataSetChanged();
            }
        });

        lst = new ArrayList<>();
        adapter = new OderMenuAdapter(this, lst);
        listView.setAdapter(adapter);
    }
}
