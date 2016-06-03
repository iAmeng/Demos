package imeng.atyandfragmentcommunacation;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import imeng.atyandfragmentcommunacation.common.base.AtyBase;
import imeng.atyandfragmentcommunacation.common.functions.Functions;

public class MainActivity extends AtyBase{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override public void setFunctionsForFragment(int fragmentId) {
        super.setFunctionsForFragment(fragmentId);
        switch (fragmentId) {
            case R.id.fragment_main:
                FragmentManager fm = getSupportFragmentManager();
                BaseFragment fragment = (BaseFragment) fm.findFragmentById(fragmentId);
                //开始添加functions
                fragment.setFunctions(new Functions()
                        .addFunction(new Functions.FunctionNoParamAndResult(MainFragment.FUNCTION_NO_PARAM_NO_RESULT) {

                            @Override
                            public void function() {
                                Toast.makeText(MainActivity.this, "成功调用无参无返回值方法", Toast.LENGTH_LONG).show();
                            }
                        }).
                                addFunction(new Functions.FunctionWithParam(MainFragment.FUNCTION_HAS_PARAM_NO_RESULT) {
                                    @Override
                                    public void function(Integer o) {
                                        Toast.makeText(MainActivity.this, "成功调用有参无返回值方法 参数值=" + o, Toast.LENGTH_LONG).show(); } }));
        }
    }
}
