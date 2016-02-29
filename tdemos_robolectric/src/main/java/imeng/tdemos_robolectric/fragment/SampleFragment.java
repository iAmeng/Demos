package imeng.tdemos_robolectric.fragment;
/**
 * @project: Demos
 * @Author : Administrator
 * @Date : 2016/2/29 12:53
 * @Version:
 */
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import imeng.tdemos_robolectric.R;


public class SampleFragment extends Fragment {


    public SampleFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sample, container, false);
    }

}
