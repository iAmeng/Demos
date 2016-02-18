package imeng.toolbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * AtyToolbarE : custom toolbar view
 *
 * @author ：Lixh
 * @date : 2016/2/18 9:38
 * @since ：
 * @see :
 * @version :
 */
public class AtyToolbarE extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_toolbar_e);
    }

    public void backButtonHandler(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.toolbar_left_image:
                onBackPressed();
                break;
            case R.id.toolbar_right_image:
                Toast.makeText(this, "rightclick", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
