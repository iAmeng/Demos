package imeng.androidstandardwidgetdemos.popwindow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import imeng.androidstandardwidgetdemos.R;

/**
 * 实验popwindow的相关知识
 * @project: DDZH
 * @Author : Administrator
 * @Date : 2016/4/1 14:59
 * @Version:
 */
public class AtyPopwindow extends AppCompatActivity implements View.OnClickListener{
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.atypopwindow);
        bt = (Button) findViewById(R.id.bt);
        bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        showPopupWindow(v);

    }

    private void showPopupWindow(View view) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(this).inflate(
                R.layout.aty_popwindow_pop, null);

        /**
         * popwindow尺寸
         * focusable = true , 锁定整个屏幕, = false popwindow之外可点*/

        final PopupWindow popupWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, 300, false);


//        popupWindow.setOutsideTouchable(true);
        popupWindow.setOutsideTouchable(false);

        popupWindow.setTouchable(true);
        /** 设置好参数之后再show
         * xoff, yoff, popwindow相对 view的位置. 屏幕坐标系  */
        popupWindow.showAsDropDown(view, 100,-300);

    }
}
