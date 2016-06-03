package imeng.effectivejava.catalog.chapter4.section14;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author : Administrator
 * @Date : 2016/5/31 17:01
 * @Version:
 */
public class MinAccessAuth {

    //注意 这个final 修饰的是 PRIVATE_VALUES 哦， 其内部的数值是可以改变的。
    private static final Integer[] PRIVATE_VALUSE = {1,2,3,4,5,6,7,8,9,0};

    /**
     * 访问私有域方法A
     */
    public static final List<Integer> VALUES = Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUSE));

    /**
     * 访问私有域方法B
     */
    public static final Integer[] values() {
        return PRIVATE_VALUSE.clone();
    }

    public static void main(String args[]) {
        System.out.println("Hello World");
        PRIVATE_VALUSE[0] = 2;
        for(int i=0; i<PRIVATE_VALUSE.length; i++) {
            System.out.println("i " + i + " value=" + PRIVATE_VALUSE[i]);
        }
    }


}
