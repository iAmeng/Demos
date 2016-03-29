package imeng.singlestonedemo.singlestone;

/**
 * 静态内部类模式.
 * @project: DDZH
 * @Author : Administrator
 * @Date : 2016/3/29 9:32
 * @Version:
 */
public class InnerStaticClassSingleStone {
    private InnerStaticClassSingleStone() {

    }
    private static class SingletonHolder{
        private static InnerStaticClassSingleStone singleStone = new InnerStaticClassSingleStone();
    }
    public static InnerStaticClassSingleStone getInstance() {
        return SingletonHolder.singleStone;
    }
}
