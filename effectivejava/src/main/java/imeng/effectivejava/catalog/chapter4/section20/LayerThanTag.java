package imeng.effectivejava.catalog.chapter4.section20;

/**
 * tag类 - 标签类：通过各种tag 来区分具体状态。
 * 推荐使用 分层结构。
 * Created by Administrator on 2016/6/3.
 */
public class LayerThanTag {
    enum Shape{ RECTANGLE, CIRCLE};
    final Shape shape = Shape.CIRCLE;

    /**
     * ...
     */

    /**
     * 典型的tag类。
     */
    double area() {
       switch (shape) {
           case RECTANGLE:
               break;
           case CIRCLE:
               break;
           default:
               break;
       }

        return 0.1d;
    }

     /**
     * ...
     */
}
