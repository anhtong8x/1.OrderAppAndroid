package anhtong8x.com.orderapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import anhtong8x.com.orderapp.helper.MyApplication;
import anhtong8x.com.orderapp.helper.OrderTable;
import anhtong8x.com.orderapp.helper.SwipeListSalesAdapter;
import anhtong8x.com.orderapp.model.Table.OrderTableResponse;
import anhtong8x.com.orderapp.network.ApiClient;
import anhtong8x.com.orderapp.network.ApiInterface;
import anhtong8x.com.orderapp.network.ApiListResponse;
import anhtong8x.com.orderapp.network.ApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalesActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private String TAG = SalesActivity.class.getSimpleName();

    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private SwipeListSalesAdapter adapter;
    private List<OrderTableResponse> lst;
    List<OrderTableResponse> tem;

    private Toolbar toolbar;

    private Activity activity = SalesActivity.this;
    MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        // itent
        String tk = getIntent().getStringExtra("token");
        int idUser = getIntent().getIntExtra("idUser",0);
        Toast.makeText(activity, "Role: " + idUser , Toast.LENGTH_LONG).show();

         myApplication = (MyApplication)getApplicationContext();
        Log.d(TAG, "Token: " + myApplication.getToken());

        // toolbar
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // listview
        listView = (ListView) findViewById(R.id.listView);
        lst = new ArrayList<>();
        adapter = new SwipeListSalesAdapter(this, lst);
        listView.setAdapter(adapter);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        fetchMovies();
                                    }
                                }
        );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                int status = lst.get(position).getStatus();
                Intent intent;
                if (status == 3) {
                    // ban dang ban goi api update
                    new AlertDialog.Builder(SalesActivity.this)
                            .setTitle("Thông báo")
                            .setMessage("Xác nhận đã dọn bàn xong !")
                            .setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //Toast.makeText(getApplicationContext(), "Hủy", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("Đồng ý", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // neu ban dang ban ( status = 3 ). thi gan no ve 1 ban da don xong
                                    OrderTableResponse item = lst.get(position);
                                    item.setStatus(1);
                                    editData("Bearer " + myApplication.getToken(), item, position);
                                }
                            })
                            .show();
                }else if(status == 2){
                    // ban dang co khach goi hoa don
                    intent = new Intent(activity, BillActivity.class);
                    intent.putExtra("idTable", lst.get(position).getId());
                    startActivity(intent);
                }else if(status == 1){
                    // ban k co khach goi menu
                    intent = new Intent(activity, OrderMenuActivity.class);
                    intent.putExtra("idTable", lst.get(position).getId());
                    startActivity(intent);
                }

            }
        });

    }

    @Override
    public void onRefresh() {
        fetchMovies();
    }

    private void fetchMovies() {
        // showing refresh animation before making http call
        swipeRefreshLayout.setRefreshing(true);

        getData();

        // stopping swipe refresh
        swipeRefreshLayout.setRefreshing(false);
    }

    // get api all table
    private void getData(){
        try{
            ApiInterface api = ApiClient.getRetrofit().create(ApiInterface.class);
            Call<ApiListResponse<OrderTableResponse>> call = api.getOrderTableList();
            Log.d("LoginActivity: ", "" + call.request().url());
            call.enqueue(new Callback<ApiListResponse<OrderTableResponse>>() {
                @Override
                public void onResponse(Call<ApiListResponse<OrderTableResponse>> call, Response<ApiListResponse<OrderTableResponse>> response) {
/*                    Log.d(TAG, "" + response.code());
                    Log.d(TAG, "" + response.body().getData().size());*/
                    lst.clear();
                    lst.addAll(response.body().getData());
                    adapter.notifyDataSetChanged();

                }

                @Override
                public void onFailure(Call<ApiListResponse<OrderTableResponse>> call, Throwable t) {
                    call.cancel();
                }
            });
        }catch (Exception ex){
            Log.d("SalesActivity: ", " error get list table" );
        }
    }

    // update table
    private void editData(String token, final OrderTableResponse orderTableResponse, final int pos){
        try{
            ApiInterface api = ApiClient.getRetrofit().create(ApiInterface.class);
            Call<ApiResponse<OrderTableResponse>> call = api.editOrderTable(token,orderTableResponse);
            Log.d("LoginActivity: ", "" + call.request().url());
            call.enqueue(new Callback<ApiResponse<OrderTableResponse>>() {
                @Override
                public void onResponse(Call<ApiResponse<OrderTableResponse>> call, Response<ApiResponse<OrderTableResponse>> response) {
                    Log.d(TAG, "" + response.code());
                    Log.d(TAG, "" + response.body().getData());

                    if(response.code() == 200){
                        lst.set(pos, orderTableResponse);
                        adapter.notifyDataSetChanged();
                    }

                }

                @Override
                public void onFailure(Call<ApiResponse<OrderTableResponse>> call, Throwable t) {
                    call.cancel();
                }
            });
        }catch (Exception ex){
            Log.d("SalesActivity: ", " error get list table" );
        }
    }

}
