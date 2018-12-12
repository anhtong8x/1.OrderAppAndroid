package anhtong8x.com.orderapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import anhtong8x.com.orderapp.helper.MyApplication;
import anhtong8x.com.orderapp.model.test;
import anhtong8x.com.orderapp.model.user.LoginResponse;
import anhtong8x.com.orderapp.network.ApiClient;
import anhtong8x.com.orderapp.network.ApiInterface;
import anhtong8x.com.orderapp.network.ApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    //-----------
    //
    EditText edtUserName, edtPassWord;
    TextView txtMsg;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtMsg = (TextView)findViewById(R.id.txtMsg);
        edtUserName = (EditText)findViewById(R.id.edt_username);
        edtPassWord = (EditText)findViewById(R.id.edt_password);
        Button btnLogin = (Button)findViewById(R.id.btnLogin);

        txtMsg.setText("");
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle(R.string.title_pro_login);
        progressDialog.setCancelable(false);

        btnLogin.setOnClickListener(onClickLogin);

    }

    View.OnClickListener onClickLogin = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            try{
                //1. check input
                String uName = edtUserName.getText().toString().trim();
                String pWord = edtPassWord.getText().toString().trim();

                if(uName.equals("")){
                    txtMsg.setText("Tài khoản không được để trống!");
                    return;
                }

                if(pWord.equals("")){
                    txtMsg.setText("Tài khoản không được để trống!");
                    return;
                }

                progressDialog.show();

                //2. get token
                ApiInterface api = ApiClient.getRetrofit().create(ApiInterface.class);
                Call<ApiResponse<LoginResponse>> call = api.getUserLogin(new test(uName,pWord));
                Log.d("LoginActivity: ", "" + call.request().url());
                call.enqueue(new Callback<ApiResponse<LoginResponse>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<LoginResponse>> call, Response<ApiResponse<LoginResponse>> response) {
                    /*Log.d("LoginActivity", "" + response.code());
                    Log.d("LoginActivity", "" + response.body().getMessage());
                    Log.d("LoginActivity", "" + response.body().getData().getToken());*/

                        // open new activity
                        String tk = response.body().getData().getToken();
                        int idUser = response.body().getData().getId();

                        // luu vao bien application. nho khai bao trong manifest
                        MyApplication myApplication = (MyApplication)getApplicationContext();
                        myApplication.setIdUser(idUser);
                        myApplication.setToken(tk);

                        Intent intent = new Intent(LoginActivity.this, SalesActivity.class);
                        intent.putExtra("token",tk);
                        intent.putExtra("idUser", idUser);
                        startActivity(intent);
                        finish();
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<ApiResponse<LoginResponse>> call, Throwable t) {
                        call.cancel();
                        progressDialog.dismiss();
                    }
                });

            }catch (Exception ex){
                progressDialog.dismiss();
            }
        }
    };

}// end class
