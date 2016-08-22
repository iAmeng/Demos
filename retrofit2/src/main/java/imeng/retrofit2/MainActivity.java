package imeng.retrofit2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("fooo", "oncreate");
    }

    public void onClick(View v) {
        Log.e("fooo", "fafa");
        callRetrofitPost();
    }

    /*
     * http://192.168.8.216:9002/Adapter?ADAPTER=QXT1001 附带数据
     */
    public void callRetrofitPost() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();



        // Retrofit retrofit = new
        // Retrofit.Builder().baseUrl("http://192.168.8.216:9001/").addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();
        // Retrofit retrofit = new
        // Retrofit.Builder().baseUrl("http://192.168.8.216:9001/").client(httpClient.build()).build();
        // Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.8.216:9001/").build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.8.216:9001/")
                .addConverterFactory(GsonConverterFactory.create()).build();


        // Retrofit retrofit = new
        // Retrofit.Builder().baseUrl("http://192.168.8.216:9001/").addConverterFactory(GsonConverterFactory.create()).build();
        QXT1001 service = retrofit.create(QXT1001.class);
        QXT1001Body body = new QXT1001Body("123456789");
        try {
            Log.e("fooo", "aaa");
            Call<Qxt1001Response> response = service.registerDevice("QXT1001", body);
            response.enqueue(new Callback<Qxt1001Response>() {
                @Override
                public void onResponse(Call<Qxt1001Response> call,
                        Response<Qxt1001Response> response) {
                    Qxt1001Response data = response.body();

                    Log.e("fooo", response.toString());
                    Log.e("fooo", "fooooo");
                    Log.e("fooo", data.ADAPTER);
                }

                @Override
                public void onFailure(Call<Qxt1001Response> call, Throwable t) {
                    t.printStackTrace();
                }
            });
            // service.registerDevice("7654321");
            Log.e("fooo", "bbbbbb");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Call<Qxt1001Response> response = service.registerDevice("QXT1001","123456789");
        response.enqueue(new Callback<Qxt1001Response>() {
            @Override
            public void onResponse(Call<Qxt1001Response> call, Response<Qxt1001Response> response) {
                Qxt1001Response data = response.body();
                Log.e("fooo", data.ADAPTER);
            }

            @Override
            public void onFailure(Call<Qxt1001Response> call, Throwable t) {
                t.printStackTrace();
            }
        });


        Map<String, String> params = new HashMap<String, String>();

        params.put("ADAPTER", "QXT1001");
        params.put("BRAND", "mi");
        params.put("MODEL", "mi-3");
        params.put("OS", "Android");
        params.put("OSVERSION", "6.0");
        params.put("SCREEN", "5.0");
        params.put("RESOLUTION", "1080*1920");
        params.put("MIDU", "20");
        params.put("UQID", "987654321");
        params.put("TVERSION", "350");
        params.put("TYPE", "A");
        params.put("VERSION", "350");

        Call<Qxt1001Response> responseMap = service.registerDevice("QXT1001",params);
        responseMap.enqueue(new Callback<Qxt1001Response>() {
            @Override
            public void onResponse(Call<Qxt1001Response> call, Response<Qxt1001Response> response) {
                Qxt1001Response data = response.body();
                Log.e("fooo_map", data.ADAPTER);
            }

            @Override
            public void onFailure(Call<Qxt1001Response> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }
}
