package anhtong8x.com.orderapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static String BASE_URL = "http://192.168.1.118:44353/";

    private static Retrofit retrofit = null;
    public static Retrofit getRetrofit (){
        if ( retrofit == null ){
            retrofit  = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
