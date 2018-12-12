package anhtong8x.com.orderapp.network;

import anhtong8x.com.orderapp.model.Table.OrderTableResponse;
import anhtong8x.com.orderapp.model.test;
import anhtong8x.com.orderapp.model.user.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiInterface {

    // login
    @POST("/api/Account/Login")
    Call<ApiResponse<LoginResponse>> getUserLogin(@Body test loginRequest);

    // table
    @GET("/api/Table/Gets")
    Call<ApiListResponse<OrderTableResponse>> getOrderTableList();

    @PUT("/api/Table/Edit")
    Call<ApiResponse<OrderTableResponse>> editOrderTable(@Header("Authorization") String token, @Body OrderTableResponse orderTableResponse);

}
