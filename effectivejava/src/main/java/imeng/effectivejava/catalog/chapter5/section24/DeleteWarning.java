package imeng.effectivejava.catalog.chapter5.section24;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/6/8.
 */
public class DeleteWarning {
    Set<String> as = new HashSet(); //warning

    Set<String> aset = new HashSet<>();

    @SuppressWarnings("unchecked") //除非可以证明代码是类型安全的，才可用这个注解。
    Set<String> ass = new HashSet();

}
