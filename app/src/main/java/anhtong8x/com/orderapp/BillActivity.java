package anhtong8x.com.orderapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import anhtong8x.com.orderapp.helper.OrderBill;
import anhtong8x.com.orderapp.helper.OrderTable;
import anhtong8x.com.orderapp.helper.SwipeListSalesAdapter;
import anhtong8x.com.orderapp.helper.SwipeListSalesAdapterBill;

public class BillActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private Toolbar toolbar;

    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private SwipeListSalesAdapterBill adapter;
    private List<OrderBill> lst;

    int offSet = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        int _id = getIntent().getIntExtra("idTable",0);
        //Toast.makeText(this, "" + _id, Toast.LENGTH_LONG).show();

        // toolbar
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ban " +  _id + " Hoa don");

        // button
        Button btnUpdate = (Button)findViewById(R.id.btnUpdate);
        Button btnNew = (Button)findViewById(R.id.btnNewOrder);
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillActivity.this, OrderMenuActivity.class);
                intent.putExtra("idTable", 0);
                intent.putExtra("idBill_1", 0);
                startActivity(intent);
            }
        });

        // group order. menu spinner chon nhom mon

        // swiper
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.notifyDataSetChanged();
            }
        });

        lst = new ArrayList<>();
        adapter = new SwipeListSalesAdapterBill(this, lst);
        listView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);

                                        fetchMovies();
                                    }
                                }
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bill_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.action_add:
                //addSomething();
                return true;
            case R.id.action_settings:
                //startSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRefresh() {
        fetchMovies();
    }

    private void fetchMovies() {
        // showing refresh animation before making http call
        swipeRefreshLayout.setRefreshing(true);

        offSet = offSet + 1;
        OrderBill m = new OrderBill(offSet,"Hau song" + offSet, 2, offSet, 0);

        //lst.add(1, m);
        lst.add(m);

        adapter.notifyDataSetChanged();
        // stopping swipe refresh
        swipeRefreshLayout.setRefreshing(false);
    }
}
