package imeng.toolbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * AtyToolbarA : Toolbar 在程序中 替代 Actionbar , 这个变化比较小 , 看不出toolbar的优势啊.
 *
 * @author ：Lixh
 * @date : 2016/2/18 9:38
 * @since ：
 * @see :
 * @version :
 */
public class AtyToolbarA extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_toolbar_a);

        // 在程序中替代Actionbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.aty_toolbar_a_ment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                return true;
            case android.R.id.home:
                super.onBackPressed();// 返回
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
