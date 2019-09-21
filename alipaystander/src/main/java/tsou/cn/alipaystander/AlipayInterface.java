package tsou.cn.alipaystander;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2018/4/10 0010.
 */

public interface AlipayInterface {
    public void onCreate(Bundle savedInstanceState);

    public void onStart();

    public void onResume();

    public void onPause();

    public void onStop();

    public void onDestroy();

    public void onSaveInstanceState(Bundle outState);

    public boolean onTouchEvent(MotionEvent event);

    public void onBackPressed();

    /**
     * 需要支付宝注入个淘票票上下文
     */
    public void attach(AppCompatActivity appCompatActivity);
}
