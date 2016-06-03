package imeng.atyandfragmentcommunacation.common.base;

import android.app.Activity;
import android.support.v4.app.Fragment;

import imeng.atyandfragmentcommunacation.common.functions.Functions;

/**
 * @Author : Administrator
 * @Date : 2016/4/11 11:28
 * @Version:
 */
public class FrgBase extends Fragment{
    protected AtyBase mBaseActivity;
    /** * 函数的集合 */
    protected Functions mFunctions;

    /** * activity调用此方法进行设置Functions
     * @param functions */
    public void setFunctions(Functions functions){
        this.mFunctions = functions;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //呼叫activity进行回调方法的设置
        if(activity instanceof BaseActivity){
            mBaseActivity = (BaseActivity)activity;
            mBaseActivity.setFunctionsForFragment(getId());
        }
    }
}
