package imeng.singlestonedemo.singlestone;

/**
 * 枚举单例, 这个是推荐模式.
 * @project: DDZH
 * @Author : Administrator
 * @Date : 2016/3/29 9:34
 * @Version:
 */
public enum EnumSingleton {

    INSTANCE {
        @Override
        protected void work() {

        }
    };

    protected abstract void work();
}
