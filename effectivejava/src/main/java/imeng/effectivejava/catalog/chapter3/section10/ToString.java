package imeng.effectivejava.catalog.chapter3.section10;

/**
 * toString 返回有用的信息。
 * @Author : Administrator
 * @Date : 2016/5/27 10:33
 * @Version:
 */
public class ToString {
    int areaCode;
    int prefix;
    int lineNumber;


    @Override
    public String toString() {
        return String.format("(%03d) %03d-%04d", areaCode, prefix, lineNumber);
    }
}
