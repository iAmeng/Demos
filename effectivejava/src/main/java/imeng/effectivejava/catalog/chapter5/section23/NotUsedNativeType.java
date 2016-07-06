package imeng.effectivejava.catalog.chapter5.section23;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/8.
 */
public class NotUsedNativeType {
    List la = new ArrayList(); //不建议使用。

    List<String> lst = new ArrayList<>(); //建议使用。
    List<String> lst2 = new ArrayList<String>(); //不反对。
}
