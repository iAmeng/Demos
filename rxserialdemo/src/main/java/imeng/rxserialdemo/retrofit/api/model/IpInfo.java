package imeng.rxserialdemo.retrofit.api.model;

/**
 * @Author : Administrator
 * @Date : 2016/7/6 10:51
 * @Version:
 */
public class IpInfo {
    public String country;
    public String country_id;
    public String area;
    public String area_id;
    public String ip;

    @Override
    public String toString() {
        return country + "\n"+ country_id + "\n"+ area + "\n" + area_id +"\n"+ ip;
    }
}
