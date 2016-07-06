package lixh.intentargs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import imeng.utilslib4android.utils.L;
import imeng.utilslib4android.utils.T;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_action_category_home)
    public void ca(View v) {
        T.isShow = true;
        T.showShort(this, "bt click");

        L.isDebug = true;
        L.e("..");

        /*Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);// 设置Intent Action属性
        intent.setType("vnd.android.cursor.item/phone");// 设置Intent Type 属性
        //主要是获取通讯录的内容
        startActivity(intent); // 启动Activity
*/
        /*
         * 跳转到Home。
         */
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);// 添加Action属性
        intent.addCategory(Intent.CATEGORY_HOME);// 添加Category属性
        startActivity(intent);// 启动Activity
    }

}
