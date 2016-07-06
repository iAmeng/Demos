package imeng.rxserialdemo.retrofit.api;

import imeng.rxserialdemo.retrofit.api.response.GetIpInfoResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @Author : Administrator
 * @Date : 2016/7/6 10:28
 * @Version:
 */
public interface IpService {
    @GET("service/getIpInfo.php")
    Call<GetIpInfoResponse> getIpInfo(@Query("ip") String ip);
}
