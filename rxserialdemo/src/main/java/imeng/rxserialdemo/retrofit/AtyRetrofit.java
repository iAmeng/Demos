package imeng.rxserialdemo.retrofit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import imeng.rxserialdemo.R;
import imeng.rxserialdemo.retrofit.api.response.GetIpInfoResponse;
import imeng.rxserialdemo.retrofit.api.response.GitHubInfoResponse;
import imeng.utilslib4android.utils.L;
import imeng.utilslib4android.utils.T;
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

    //@OnClick(R.id.retrofit_a_bt)
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
                callDDApi();
                break;
            default:
                break;
        }

    }


    /*
     * https://api.github.com/users/{user}/repos
     */
    public void callGitHubRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);


        Call<List<GitHubInfoResponse>> repos = service.listRepos("iameng");

        //        // 同步调用
        //        try {
        //            Response<List<Repo>> datas = repos.execute();
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        }

        // 异步调用
        repos.enqueue(new Callback<List<GitHubInfoResponse>>() {
            @Override
            public void onResponse(Call<List<GitHubInfoResponse>> call, Response<List<GitHubInfoResponse>> response) {
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
     * restful : query
     * http://ip.taobao.com/service/getIpInfo.php?ip=222.191.236.252
     */
    public void callIpInfoRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ip.taobao.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IpService service = retrofit.create(IpService.class);

        Call<GetIpInfoResponse> ipInfo = service.getIpInfo("222.191.236.252");

        ipInfo.enqueue(new Callback<GetIpInfoResponse>() {
            @Override
            public void onResponse(Call<GetIpInfoResponse> call, Response<GetIpInfoResponse> response) {
                GetIpInfoResponse ipInfo = response.body();
                L.e("fooo_e", "" + ipInfo.data.toString());
            }

            @Override
            public void onFailure(Call<GetIpInfoResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public void callDDApi() {

    }


}
