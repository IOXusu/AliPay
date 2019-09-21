package tsou.cn.alipay;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 加载
     */
    private Button mLoad;
    /**
     * 点击
     */
    private Button mClick;
    String[] mPermissionList = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PluginManager.getInstance().setContext(this);
        initView();
    }

    private void initView() {
        mLoad = (Button) findViewById(R.id.load);
        mLoad.setOnClickListener(this);
        mClick = (Button) findViewById(R.id.click);
        mClick.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.load://加载
                // 缺少权限时, 进入权限配置页面
                ActivityCompat.requestPermissions(MainActivity.this,mPermissionList, 100);
                break;
            case R.id.click://点击
                Intent intent = new Intent(this, ProxyActivity.class);
                // intent.putExtra("className", "淘票票的全类名   MainActivity");
                intent.putExtra("className", PluginManager.getInstance().getEntryActivityName());
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100:
                boolean camera = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean readExternalStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                if (grantResults.length > 0 && camera && readExternalStorage) {
                    File file = new File(Environment.getExternalStorageDirectory(), "plugin.apk");
                    PluginManager.getInstance().loadPath(file.getAbsolutePath());
                } else {
                    Toast.makeText(this.getApplicationContext(),"请设置必要权限",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
