package imeng.singlestonedemo.singlestone;

/**
 * 双校验锁模式
 * @project: DDZH
 * @Author : Administrator
 * @Date : 2016/3/29 9:29
 * @Version:
 */
public class DoubleCheckSyncSingleStone {
    private volatile  static DoubleCheckSyncSingleStone singleStone = null;
    private DoubleCheckSyncSingleStone() {

    }

    public static DoubleCheckSyncSingleStone getInstance() {
        if(singleStone == null) {
            synchronized (DoubleCheckSyncSingleStone.class) {
                if(singleStone == null) {
                    singleStone = new DoubleCheckSyncSingleStone();
                }
            }
        }
        return singleStone;
    }
}
