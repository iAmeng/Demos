package imeng.rxserialdemo.retrofit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;
import imeng.rxserialdemo.R;
import imeng.rxserialdemo.retrofit.api.GitHubService;
import imeng.rxserialdemo.retrofit.api.IpService;
import imeng.rxserialdemo.retrofit.api.QXT1001;
import imeng.rxserialdemo.retrofit.api.params.QXT1001Body;
import imeng.rxserialdemo.retrofit.api.response.GetIpInfoResponse;
import imeng.rxserialdemo.retrofit.api.response.GitHubInfoResponse;
import imeng.rxserialdemo.retrofit.api.response.Qxt1001Response;
import imeng.utilslib4android.utils.L;
import imeng.utilslib4android.utils.T;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author : Administrator
 * @Date : 2016/7/5 16:50
 * @Version:
 */
public class AtyRetrofit extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        ButterKnife.bind(this);
        L.isDebug = true;
    }

    // @OnClick(R.id.retrofit_a_bt)
    @OnClick({R.id.retrofit_a_bt, R.id.retrofit_b_bt, R.id.retrofit_c_bt})
    public void click(View v) {
        int vid = v.getId();
        switch (vid) {
            case R.id.retrofit_a_bt:
                T.showLong(this, "a");
                callGitHubRetrofit();
                break;
            case R.id.retrofit_b_bt:
                T.showLong(this, "b");
                callIpInfoRetrofit();
                break;
            case R.id.retrofit_c_bt:
//                callQxtApi();
                callRetrofitPost();
                break;

            default:
                break;
        }

    }


    /*
     * https://api.github.com/users/{user}/repos
     */
    public void callGitHubRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        GitHubService service = retrofit.create(GitHubService.class);


        Call<List<GitHubInfoResponse>> repos = service.listRepos("iameng");

        // // 同步调用
        // try {
        // Response<List<Repo>> datas = repos.execute();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }

        // 异步调用
        repos.enqueue(new Callback<List<GitHubInfoResponse>>() {
            @Override
            public void onResponse(Call<List<GitHubInfoResponse>> call,
                    Response<List<GitHubInfoResponse>> response) {
                List<GitHubInfoResponse> data = response.body();
                for (GitHubInfoResponse r : data) {
                    L.e("fooo_e", r.name);
                }
            }

            @Override
            public void onFailure(Call<List<GitHubInfoResponse>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    /*
     * restful : query http://ip.taobao.com/service/getIpInfo.php?ip=222.191.236.252
     */
    public void callIpInfoRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://ip.taobao.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        IpService service = retrofit.create(IpService.class);

        Call<GetIpInfoResponse> ipInfo = service.getIpInfo("222.191.236.252");

        ipInfo.enqueue(new Callback<GetIpInfoResponse>() {
            @Override
            public void onResponse(Call<GetIpInfoResponse> call,
                    Response<GetIpInfoResponse> response) {
                GetIpInfoResponse ipInfo = response.body();
                L.e("fooo_e", "" + ipInfo.data.toString());
            }

            @Override
            public void onFailure(Call<GetIpInfoResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    /*
     * http://192.168.8.216:9002/Adapter?ADAPTER=QXT1001 附带数据
     */
    public void callQxtApi() {

         OkHttpClient.Builder httpClient = new OkHttpClient.Builder();



        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.8.216:9001/")
                .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();

        QXT1001 service = retrofit.create(QXT1001.class);

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


        QXT1001Body body = new QXT1001Body("123456789");
        try {
            service.registerDevice("QXT1001", body);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Call<Qxt1001Response> response = service.registerDevice(body);
//        service.registerDevice();
          //      Call<Qxt1001Response> response = service.registerDevice("123457896");
        L.e("fooo_e", "call dd api");
/*
        response.enqueue(new Callback<Qxt1001Response>() {
            @Override
            public void onResponse(Call<Qxt1001Response> call, Response<Qxt1001Response> response) {
            }

            @Override
            public void onFailure(Call<Qxt1001Response> call, Throwable t) {

            }
        });*/
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

