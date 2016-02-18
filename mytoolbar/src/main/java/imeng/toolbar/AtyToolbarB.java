package imeng.toolbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

/**
 * AtyToolbarB : 单独使用Toolbar.
 *
 * @author ：Lixh
 * @date : 2016/2/18 9:38
 * @since ：
 * @see :
 * @version :
 */
public class AtyToolbarB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_toolbar_b);

        // 单独使用Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // App Logo
        toolbar.setLogo(R.mipmap.ic_launcher);
        // Title
        toolbar.setTitle("My Title");
        // Sub Title
        toolbar.setSubtitle("Sub title");
        // setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.aty_toolbar_a_ment);
        // Navigation Icon 要設定在 setSupoortActionBar后 才有作用
        // 否則會出現 back button
        toolbar.setNavigationIcon(R.mipmap.icon_nav);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Menu item click 设定在 setSupportActionBar 之后才有作用
        toolbar.setOnMenuItemClickListener(onMenuItemClick);



    }

    /**
     * desc:onMenuItemClick
     * 
     * @rturn a
     * @author Lixh created at 2016/2/18 10:12
     *         <p/>
     *         Administrator
     *
     *
     *         AtyToolbarB
     */
    private Toolbar.OnMenuItemClickListener onMenuItemClick =
            new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    String msg = "";
                    switch (menuItem.getItemId()) {
                        case R.id.action_edit:
                            break;
                        case R.id.action_settings:
                            break;
                        default:
                            break;
                    }
                    return true;
                }
            };

}
