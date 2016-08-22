package imeng.rxserialdemo.rxjavademo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.view.ViewAttachEvent;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import imeng.rxserialdemo.R;
import imeng.utilslib4android.utils.L;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

public class RxActivity extends AppCompatActivity {
    private static final String TAG = "RxActivity";

    @BindView(R.id.bt_a)
    Button bt_a;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.aty_rx);
        ButterKnife.bind(this);

        RxView.attachEvents(bt_a).throttleFirst(1000, TimeUnit.HOURS).subscribe(new Action1<ViewAttachEvent>() {
            @Override
            public void call(ViewAttachEvent viewAttachEvent) {

            }
        });

    }

    @OnClick(R.id.bt_a)
    public void toDo(View v) {
        L.d(TAG , "click");
        int id = v.getId();
        switch (id) {
            case R.id.bt_a:
                callRxA();
                break;
            default:
                break;
        }
    }

    public void callRxA() {
        L.d(TAG, "call on Rxa");
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) { //这个要 subscribe() 发生才会被调用.
                L.d(TAG, "0 ___ call on subscribe");
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();
            }
        });

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onStart() { //subscribe 之后 首先调动onStart, 然后 OnSubscribe(上边)
                super.onStart();
                L.d(TAG, "1 ___ onStart");
            }

            @Override
            public void onCompleted() {
                L.d(TAG, "3 ___ onComp");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                L.d(TAG, "2 ___ onNext " + s);
            }
        };

        observable.subscribe(subscriber);
    }

}
