package imeng.effectivejava.catalog.chapter4.section21;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/8.
 */
public class functionObject {
    private static class StrLenCmp implements Compatator<String>, Serializable {
        @Override
        public int compare(String s1, String s2) {
            return s1.length() - s2.length();
        }
    }

    //used
    public static final Compatator<String> STRING_COMPATATOR = new StrLenCmp();

    //...used STRING_COMPARATOR
}
