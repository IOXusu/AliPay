package tsou.cn.taopiaopiao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 没有安装
 * <p>
 * 上下文   一般都是系统注入
 */
public class MainActivity extends BaseActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView) findViewById(R.id.img);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //系统注入的上下文：MainActivity.this
                Toast.makeText(that,"点击",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(that,SceondActivity.class));
            }
        });
    }
}
