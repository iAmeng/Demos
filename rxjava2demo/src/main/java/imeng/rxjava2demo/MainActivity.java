package imeng.rxjava2demo;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureOverflowStrategy;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/*
 * ref : 文章 http://gank.io/post/560e15be2dca930e00da1083
 * ref : 文章 http://www.itdadao.com/articles/c15a733258p0.html
 * ref : demo https://github.com/amitshekhariitbhu/RxJava2-Android-Samples
 * ref : 文章 http://blog.csdn.net/yyh352091626/article/details/53304728
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    Button mButton;
    Button mButtonScheduler;
    ImageView mImageview;

    int drawableRes = R.mipmap.ic_launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView() {
        mButton = (Button) findViewById(R.id.bt_a);
        mButton.setOnClickListener(this);
        mImageview = (ImageView) findViewById(R.id.iv_a);
        mButtonScheduler = (Button) findViewById(R.id.bt_b);
        mButtonScheduler.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.bt_a:
                genObservableA();
                break;
            case R.id.bt_b:
                genScheduler();
            default:
                break;
        }

    }

    public void genObservableA() {
        /***************************************************************************
         * 一  创建 Observer - 观察者                                             *
         * 一个用于 observable, 一个用于 flowable?
         ***************************************************************************/
        Observer<String> observer = new Observer<String>() {
            Disposable disposable = null;

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe!");
                disposable = d;
            }

            @Override
            public void onNext(String value) {
                Log.d(TAG, "Item: " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "Error!");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "Completed!");
                //取消订阅了?
                disposable.dispose();
            }
        };


        /***************************************************************************
         * 二  创建 Obervable - 被观察者                                             *
         ***************************************************************************/
        /**
         * 方法一
         */
        Observable observable_a = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {
                e.onNext("Hello aaaaaaaaaa");
                e.onNext("Hi aaaaaaaaaaaa");
                e.onNext("Aloha aaaaaaaaaaa");
                e.onComplete();
            }
        });
        observable_a.subscribe(observer);

        /**
         * 方法二
         */
        Observable observale_b = Observable.just("Hello bbbbbbbb", "Hi bbbbbbbbbb", "Aloha bbbbbbbbbbbb");
        observale_b.subscribe(observer);
        // 将会依次调用：
        // onNext("Hello");
        // onNext("Hi");
        // onNext("Aloha");
        // onCompleted();

        /**
         * 方法三
         */
        String[] words = {"Hello ccccccccccc", "Hi ccccccccccccc", "Aloha cccccccccccc"};
        Observable observable_c = Observable.fromArray(words);
        observable_c.subscribe(observer);
        // 将会依次调用：
        // onNext("Hello");
        // onNext("Hi");
        // onNext("Aloha");
        // onCompleted();

        /****************************************************************************************/
        /* flowable
        /****************************************************************************************/
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };


//        BackpressureStrategy.LATEST  处理一会 1-127, 最后一个9999 -- 默认是 128个event.
//        BackpressureStrategy.DROP 0-127 ,
//        BackpressureStrategy.ERROR 0, 然后exception了. consume不了了.
//        BackpressureStrategy.MISSING  当设置onBackpressureBuffer(...), overflow 会有些响应. 具体应用场景? 设置以后,还能保存 最后128个event





        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter e) throws Exception {
//                for (int i = 0; i < 1000; i++) {
                    for (int i = 0; i < 10; i++) {
                    Log.e(TAG, "Emittere " + i);
                    e.onNext(i);
                }
                e.onComplete();
            }

        }, BackpressureStrategy.MISSING).onBackpressureBuffer(128, new Action() {
            @Override
            public void run() throws Exception {
                Log.e(TAG, "Emittere errr");
            }
        }, BackpressureOverflowStrategy.DROP_OLDEST).subscribeOn(Schedulers.computation()).observeOn(Schedulers.newThread()).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d("JG", integer.toString());
                Thread.sleep(1000);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d("JG", throwable.toString());
            }
        });

        /*********************************/
        // Consumer - subscriber 的另外的形式
        /*********************************/

        Consumer<String> onNextConsumer = new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e(TAG, "Consumer onNext " + s);
            }
        };

        Consumer<Throwable> onErrorConsumer = new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e(TAG, "Consumer Err " + throwable.toString());
            }
        };

        Consumer<Disposable> onSubConsumer = new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                Log.e(TAG, "Disposable Onsubscribe!");
            }
        };

        Action onCompleteAction = new Action() {
            @Override
            public void run() throws Exception {
                Log.e(TAG, "Action Complete!");
            }
        };

        // 自动创建 Subscriber ，并使用 onNextAction、 onErrorAction 和 onCompletedAction 来定义 onNext()、 onError() 和 onCompleted()
        // Flowable 一样的.
        observable_a.subscribe(onNextConsumer, onErrorConsumer, onCompleteAction, onSubConsumer);

        //
        /*************************************************/
        // 画一个 图 , 作为第一阶段使用Rxjava的 总结.
        /*************************************************/
        Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {
//                Drawable drawable = getTheme().getDrawable(drawableRes);
                Resources res = getResources();
                Drawable drawable = res.getDrawable(drawableRes);
                e.onNext(drawable);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Drawable>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Drawable value) {
                mImageview.setImageDrawable(value);

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void genScheduler() {
        /*
         * 基本的
         */
        Observable.just(1, 2, 3, 4).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG, "Scheduler integer = " + integer.toString());
            }
        });

        /*
         * map  - 映射 一对一 映射.
         */

        Observable.just("1", "2", "3", "4").map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {
                //把 String 映射为 integer
                return Integer.valueOf(s);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                if (integer instanceof Integer) {
                    Log.e(TAG, "Map String -> Integer = " + integer.toString());
                } else {
                    Log.e(TAG, "ERRR Map String -> Integer" + integer.toString());
                }
            }
        });

        /**
         * read list 单个发射 event. 多 对 一
         */
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(1);
        list.add(5);

        Flowable.fromIterable(list).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG, "Map String -> Integer = " + integer.toString());
            }
        });

        /*
         * flatmap 映射 一对多的映射
         */

        /* 查询 学生 A B的 各科成绩 */

        final List aList = new ArrayList();
        aList.add("a_1");
        aList.add("a_2");
        aList.add("a_3");
        aList.add("a_4");

        Observable.fromArray("a").flatMap(new Function<String, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(String s) throws Exception {
                return Observable.fromIterable(aList);
            }
        }).subscribe(string -> Log.e(TAG, "FlatMap ->a to " + string));

        /**
         * flatmap  多对多 100 个学生 查询 各学科成绩.
         */
        final List bList = new ArrayList();
        bList.add("b_1");
        bList.add("b_2");
        bList.add("b_3");
        bList.add("b_4");

        final List cList = new ArrayList();
        bList.add("c_1");
        bList.add("c_2");
        bList.add("c_3");
        bList.add("c_4");


        List students = new ArrayList();
        students.add("b");
        students.add("c");

        Observable.fromIterable(students).flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(String s) throws Exception {
                return Observable.fromIterable(genScores(s));
            }
        }).subscribe(o -> Log.e(TAG, "n-n" + o.toString()));

        /**
         * Lift() 这个方法比较特别, 也不知道怎么用.
         */
    }

    private List<String> genScores(String name) throws Exception{
        if (name.equals("b")) {
            final List aList = new ArrayList();
            aList.add("Bs_1");
            aList.add("Bs_2");
            aList.add("Bs_3");
            aList.add("Bs_4");
            return aList;
        }

        if (name.equals("c")) {
            final List aList = new ArrayList();
            aList.add("Cs_1");
            aList.add("Cs_2");
            aList.add("Cs_3");
            aList.add("Cs_4");
            return aList;
        }
        throw new Exception("Err KEy");
    }

    public void changeThread() {
        Observable.just(1, 2, 3, 4) // IO 线程，由 subscribeOn() 指定
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .map(integer -> {
                    return String.valueOf(integer);
                }) // 新线程，由 observeOn() 指定
                .observeOn(Schedulers.io())
                .map(s -> {
                    return Integer.valueOf(s);
                }) // IO 线程，由 observeOn() 指定
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> Log.e(TAG, "thread")); // Android 主线程，由 observeOn() 指定
    }

    /**
     * 2016-11-25 Rxbind
     */








}
