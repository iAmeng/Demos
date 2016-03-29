package imeng.singlestonedemo.singlestone;

/**
 * 饿汉模式.
 * @project: DDZH
 * @Author : Administrator
 * @Date : 2016/3/29 9:25
 * @Version:
 */
public class HungrySingleStone {
    private static HungrySingleStone singleStone = new HungrySingleStone();
    private HungrySingleStone() {

    }
    public static HungrySingleStone getInstance() {
        return singleStone;
    }
}
