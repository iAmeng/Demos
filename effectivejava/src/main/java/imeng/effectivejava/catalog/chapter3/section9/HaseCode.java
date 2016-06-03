package imeng.effectivejava.catalog.chapter3.section9;

/**
 * 0 hashcode :散列码
 * 1 hashcode 的作用:用来判断两个对象是不是相等的. 如果hashcode不相等, 那么两个对象是不equals的;
 *                                            如果hashcode相等,两个对象不一定equals.
 * 2 31 : 素数, 11111[2], i*31 = (i << 5 ) - 1 ; 同时，31 不是很大，可以尽量避免溢出。
 *
 * @Author : Administrator
 * @Date : 2016/5/26 17:10
 * @Version:
 */
public class HaseCode extends Object{
    private volatile int hashcode;


    int areacode;
    int prefix;
    int lineNumber;

    @Override
    public int hashCode() {
        int result = hashcode;

        if(result == 0) {
        result = 17;
        result = 31*result + areacode;
        result = 31*result + prefix;
        result = 31*result + lineNumber;
        }

        return result;
    }
}
