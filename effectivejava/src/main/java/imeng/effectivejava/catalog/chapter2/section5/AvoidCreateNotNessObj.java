package imeng.effectivejava.catalog.chapter2.section5;

/**
 * @Author : Administrator
 * @Date : 2016/5/25 17:03
 * @Version:
 */
public class AvoidCreateNotNessObj {
    public AvoidCreateNotNessObj() {
        // [1]
        String string = new String("hello"); //not recommand, create a new String obj;
        String stringr = "hello"; //yes.

        //[2]
        Long sumL = 0L;
        for(long i = 0; i < Integer.MAX_VALUE; i++) {
            sumL += i; //create a new Long obj everytime;  自装箱。
        }

        long suml = 0l;
        for(long i = 0; i<Integer.MAX_VALUE; i++) {
            suml += i; //base type;
        }
    }
}
