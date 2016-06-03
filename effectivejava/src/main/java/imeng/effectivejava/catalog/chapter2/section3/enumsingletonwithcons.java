package imeng.effectivejava.catalog.chapter2.section3;

/**
 * @Author : Administrator
 * @Date : 2016/5/25 16:37
 * @Version:
 */
public enum enumsingletonwithcons {
    ENUMINSTANCE_A(1),
    ENUMINSTANCE_B(2);


    int mInt;
    enumsingletonwithcons(int a) {
        mInt = a;
    }
}
