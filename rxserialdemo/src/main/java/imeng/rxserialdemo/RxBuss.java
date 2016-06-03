package imeng.rxserialdemo;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * @project: DDZH
 * @Author : Administrator
 * @Date : 2016/4/7 12:58
 * @Version:
 */
public class RxBuss {
        private static RxBuss mRxBus = null;
        /**
         * PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
         */

        private Subject<Object, Object> mRxBusObserverable = new SerializedSubject<>(PublishSubject.create());

        public static synchronized RxBuss getInstance() {
            if (mRxBus == null) {
                mRxBus = new RxBuss();
            }
            return mRxBus;
        }

        public void post(Object o) {
            mRxBusObserverable.onNext(o);
        }

        public Observable<Object> toObserverable() {
            return mRxBusObserverable;
        }

        /**
         * 判断是否有订阅者
         */
        public boolean hasObservers() {
            return mRxBusObserverable.hasObservers();
        }
}
