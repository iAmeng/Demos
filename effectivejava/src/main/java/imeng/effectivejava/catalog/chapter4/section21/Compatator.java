package imeng.effectivejava.catalog.chapter4.section21;

/**
 * 函数对象策略
 * Created by Administrator on 2016/6/8.
 */
public interface Compatator<T> {
    public int compare(T t1, T t2);
}
