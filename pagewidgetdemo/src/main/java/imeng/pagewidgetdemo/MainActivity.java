package imeng.pagewidgetdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import imeng.pagewidgetdemo.utils.LogUtils;
import imeng.pagewidgetdemo.utils.ToastUtils;
import imeng.pagewidgetdemo.view.readview.OnReadStateChangeListener;
import imeng.pagewidgetdemo.view.readview.PageWidget;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.flReadWidget)
    FrameLayout flReadWidget;

    private PageWidget mPageWidget;
//    private Recommend.RecommendBooks recommendBooks;

    private List<String> mChapterList;

    private int currentChapter = 1;

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initViews();
    }
    private void initViews() {
        //mPageWidget = new PageWidget(this, recommendBooks._id, mChapterList, new ReadListener());// 页面
        mChapterList = new ArrayList<>();
        mChapterList.add("1");
        mChapterList.add("2");
        mChapterList.add("3");
        mChapterList.add("4");
        mChapterList.add("5");

        mPageWidget = new PageWidget(this, "510f60ec59762e9453000007", mChapterList, new ReadListener());// 页面

        /*
        registerReceiver(receiver, intentFilter);
        if (SharedPreferencesUtil.getInstance().getBoolean(Constant.ISNIGHT, false)) {
            mPageWidget.setTextColor(ContextCompat.getColor(this, R.color.chapter_content_night),
                                     ContextCompat.getColor(this, R.color.chapter_title_night));
        }
        */
        flReadWidget.removeAllViews();

        initWebView();
        flReadWidget.addView(mWebView);
        flReadWidget.addView(mPageWidget);
    }

    class ReadListener implements OnReadStateChangeListener {
        @Override
        public void onChapterChanged(int chapter) {
            LogUtils.i("onChapterChanged:" + chapter);
            currentChapter = chapter;
            /*
            mTocListAdapter.setCurrentChapter(currentChapter);
            // 加载前一节 与 后三节
            for (int i = chapter - 1; i <= chapter + 3 && i <= mChapterList.size(); i++) {
                if (i > 0 && i != chapter
                        && CacheManager.getInstance().getChapterFile(recommendBooks._id, i) == null) {
                    mPresenter.getChapterRead(mChapterList.get(i - 1).link, i);
                }
            }
            */
        }

        @Override
        public void onPageChanged(int chapter, int page) {
            LogUtils.i("onPageChanged:" + chapter + "-" + page);
        }

        @Override
        public void onLoadChapterFailure(int chapter) {
            LogUtils.i("onLoadChapterFailure:" + chapter);
            /*
            startRead = false;
            if (CacheManager.getInstance().getChapterFile(recommendBooks._id, chapter) == null)
                mPresenter.getChapterRead(mChapterList.get(chapter - 1).link, chapter);
                */
        }

        @Override
        public void onCenterClick() {
            LogUtils.i("onCenterClick");
            /*
            toggleReadBar();
            */
        }

        @Override
        public void onFlip() {
            ToastUtils.getSingleToast("onFlip", Toast.LENGTH_SHORT);
            /*
            hideReadBar();
            */
            int x=1+(int)(Math.random()*50);
            if(x>25) {
                mWebView.loadUrl("https://www.baidu.com");
            } else {
                mWebView.loadUrl("http://news.163.com");
            }
        }
    }

    public void initWebView() {
        mWebView = new WebView(this);
        mWebView.getSettings().setJavaScriptEnabled(true) ;
        mWebView.loadUrl("http://www.xuanyusong.com") ;
        //获取WebSettings对象,设置缩放
        mWebView.getSettings().setUseWideViewPort(true) ;
        mWebView.getSettings().setLoadWithOverviewMode(true) ;
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("访问网址：", url) ;
                //m_wv2.loadUrl(url) ;
                return true ;
            }
        }) ;
        /*
        m_wv2 = (WebView)findViewById(R.id.wv2) ;
        //获取WebSettings对象,设置单列
        m_wv2.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN) ;
        m_wv2.getSettings().setJavaScriptEnabled(true) ;
        m_wv2.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("访问网址：",url) ;
                m_wv2.loadUrl(url) ;
                return true ;
            }
        }) ;
        */
    }
}
