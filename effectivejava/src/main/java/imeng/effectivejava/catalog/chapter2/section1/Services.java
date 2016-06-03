package imeng.effectivejava.catalog.chapter2.section1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 第二张 第一条 用静态方法代替构造方法。
 * @Author : Administrator
 * @Date : 2016/5/25 14:38
 * @Version:
*/
public class Services {
    private Services() {}

    private static final Map<String, Provider> providers = new ConcurrentHashMap<>(); //线程安全的Hashmap

    private static final String DEFAULT_PROVIDER_NAME = "<def>";

    public static void registerDefaultProvider(Provider p) {
        registerProvider(DEFAULT_PROVIDER_NAME, p);
    }

    public static void registerProvider(String name, Provider p) {
        providers.put(name, p);
    }

    public static Service newInstance() {
        return newInstance(DEFAULT_PROVIDER_NAME);

    }

    public static Service newInstance(String name) {
        Provider p = providers.get(name);
        if(p == null) {
            throw new IllegalArgumentException("No provider registered with name: " + name);
        }

        return p.newService();
    }


}
