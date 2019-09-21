package tsou.cn.taopiaopiao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SceondActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 点击下个页面
     */
    private Button mBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sceond);
        initView();

    }

    private void initView() {
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn:
                startActivity(new Intent(that, OtherActivity.class));
                break;
        }
    }
}
