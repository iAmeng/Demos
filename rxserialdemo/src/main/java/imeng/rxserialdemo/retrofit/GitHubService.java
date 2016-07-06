package imeng.rxserialdemo.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @Author : Administrator
 * @Date : 2016/7/6 10:28
 * @Version:
 */
public interface GitHubService {
    //@POST("users/{user}/repos")
    @GET("users/{user}/repos") //get and post cannot exchange
    Call<List<GitHubInfoResponse>> listRepos(@Path("user") String user);
}
