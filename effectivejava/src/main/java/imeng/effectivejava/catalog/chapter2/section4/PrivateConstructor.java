package imeng.effectivejava.catalog.chapter2.section4;

/**
 * @Author : Administrator
 * @Date : 2016/5/25 16:43
 * @Version:
 */
public class PrivateConstructor {
    //本类不需要被实例化.
    private PrivateConstructor() {
        throw new AssertionError();
    }
}
