package imeng.rxserialdemo.retrofit.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import imeng.rxserialdemo.retrofit.api.params.QXT1001Body;
import imeng.rxserialdemo.retrofit.api.response.Qxt1001Response;

/**
 * @Author : Administrator
 * @Date : 2016/7/6 16:16
 * @Version:
 */
public interface QXT1001 {
    @POST("Adapter")
    Call<Qxt1001Response> registerDevice(@Query("ADAPTER") String adapter, @Body QXT1001Body body);

    @FormUrlEncoded
    @POST("Adapter")
    Call<Qxt1001Response> registerDevice(@Query("ADAPTER") String adapter, @Field("UQID") String uqid);

    @FormUrlEncoded
    @POST("Adapter")
    Call<Qxt1001Response> registerDevice(@Query("ADAPTER") String adapter, @FieldMap Map<String, String> map);
}
