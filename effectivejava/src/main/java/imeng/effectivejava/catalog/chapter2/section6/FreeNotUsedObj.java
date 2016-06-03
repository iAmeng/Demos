package imeng.effectivejava.catalog.chapter2.section6;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * 泄露地点 ： 1 自己管理，没有及时释放。
 *           2 缓存。
 *           3 监听器， 和 回调。
 * @Author : Administrator
 * @Date : 2016/5/26 13:33
 * @Version:
 */
public class FreeNotUsedObj {

    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public FreeNotUsedObj() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;

    }

    public Object pop() {
        if(size == 0) {
            throw new EmptyStackException();
        }

        /*
        Object result = elements[--size];
        elements[size] = null;
        return result;
        */

        return elements[--size]; //直接返回，会导致泄露。

    }

    public void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2*size +1);
        }
    }


}
