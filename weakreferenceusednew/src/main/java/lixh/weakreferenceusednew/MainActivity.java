package lixh.weakreferenceusednew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        Button bt_weakused = (Button) findViewById(R.id.bt_wekused);
        Button bt_callgc = (Button) findViewById(R.id.bt_callgc);


        final WeakUsedClass weakUsedClass = new WeakUsedClass();
        final WeakReference<WeakUsedClass> wc = new WeakReference<WeakUsedClass>(weakUsedClass);

        bt_weakused.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"click",Toast.LENGTH_SHORT).show();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
        //                WeakUsedClass weakUsedClass = new WeakUsedClass();
         //               WeakReference<WeakUsedClass> wc = new WeakReference<WeakUsedClass>(weakUsedClass);
                        int i = 0;
                        while (true) {
                            if (wc.get() != null) {
                                i++;
                                WeakUsedClass wk = wc.get();
                                Log.e("fooo", "Object is alive for " + i + " loops - " + wk.toString());
                                try {
                                    Thread.sleep(10);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Log.e("fooo", "Object has been collected.");
                                break;
                            }
                        }

                    }
                }).start();
            }
        });

        bt_callgc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"gc",Toast.LENGTH_SHORT).show();
                System.gc();
            }
        });
    }
}
