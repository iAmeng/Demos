package com.lian.viewpagertest;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TextView mTextView;
    private LinearLayout mLinearLayout;
    private View mView;
    private List<ImageView> mDataList;
    private int diatance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initEvent();
    }

    /**
     * 初始化数据
     */

    private void initData() {
        int[] sorce = new int[]{R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e};

        mDataList = new ArrayList<ImageView>();

        for (int i = 0;i < sorce.length;i ++){
            ImageView img = new ImageView(getApplicationContext());
            img.setImageResource(sorce[i]);
            mDataList.add(img);

            //添加底部灰点
            View v = new View(getApplicationContext());
            v.setBackgroundResource(R.drawable.gray_circle);
            //指定其大小
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20,20);
            if (i != 0)
                params.leftMargin = 20;
            v.setLayoutParams(params);
            mLinearLayout.addView(v);
        }

        mViewPager.setAdapter(new MyAdapter());

        //设置每次加载时第一页在MAX_VALUE / 2 - Extra 页，造成用户无限轮播的错觉
        int startPage = Integer.MAX_VALUE / 2;
        int extra = startPage % mDataList.size();
        startPage = startPage - extra;
        mViewPager.setCurrentItem(startPage);
    }

    /**
     * ViewPager的容器
     */

    public class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            //告诉容器我们的数据长度为Integer.MAX_VALUE，这样就可以一直滚动
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //若position超过mDataList.size()，所以这里每次超过size又从0开始计算位置
            position = position % mDataList.size();

            ImageView img = mDataList.get(position);
            container.addView(img);

            return img;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            position = position % mDataList.size();

            container.removeView((View)object);

           // super.destroyItem(container, position, object);
        }
    }

    private void initEvent() {


        /**
         * 当底部红色小圆点加载完成时测出两个小灰点的距离，便于计算后面小红点动态移动的距离
         */
        mView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
               diatance =  mLinearLayout.getChildAt(1).getLeft() - mLinearLayout.getChildAt(0).getLeft();
                Log.d("两点间距",diatance + "测出来了");
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                //测出页面滚动时小红点移动的距离，并通过setLayoutParams(params)不断更新其位置
                position = position % mDataList.size();
                float leftMargin = diatance * (position + positionOffset);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mView.getLayoutParams();
                params.leftMargin = Math.round(leftMargin);
                mView.setLayoutParams(params);
                Log.d("红点在这",leftMargin + "");
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {

        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mTextView = (TextView) findViewById(R.id.tv);

        mLinearLayout = (LinearLayout) findViewById(R.id.ll_points);

        mView = findViewById(R.id.v_redpoint);
    }
}
