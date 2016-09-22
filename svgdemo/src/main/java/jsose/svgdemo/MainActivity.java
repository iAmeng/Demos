package jsose.svgdemo;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button mButtonA;
    ImageView mImageViewA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButtonA = (Button) findViewById(R.id.bt_a);
        mImageViewA = (ImageView) findViewById(R.id.img_a);
        mButtonA.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.bt_a:
                Drawable drawable = mImageViewA.getDrawable();
                if (drawable instanceof Animatable) {
                    Animatable able = (Animatable) drawable;
                    able.start();
                }
                break;
            default:
                break;
        }
        
        
    }
}
