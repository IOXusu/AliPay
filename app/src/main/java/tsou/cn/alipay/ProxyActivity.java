package tsou.cn.alipay;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import tsou.cn.alipaystander.AlipayInterface;

/**
 * Created by Administrator on 2018/4/10 0010.
 * <p>
 * 专门去插桩使用
 */

public class ProxyActivity extends AppCompatActivity {
    //要跳转的 淘票票Activity
    private String className;
    private AlipayInterface alipayInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        className = getIntent().getStringExtra("className");
        /**
         * 通过className不能拿到类名
         * 原生是加载过的类名
         */
        //  Class clazz = getClassLoader().loadClass(className);
        //   Class.forName(className);
        try {
            //加载该Activity的字节码对象
            Class activityClass = getClassLoader().loadClass(className);
            Constructor constructor = activityClass.getConstructor(new Class[]{});
            //创建该Activity的示例
            Object newInstance = constructor.newInstance(new Object[]{});
            //程序健壮性检查
            if (newInstance instanceof AlipayInterface) {
                /**
                 * 使用定义接口标准
                 * 传递生命周期
                 */
                alipayInterface = (AlipayInterface) newInstance;
                //将代理Activity的实例传递给三方Activity
                alipayInterface.attach(this);
                //创建bundle用来与三方apk传输数据
                Bundle bundle = new Bundle();
                //调用三方Activity的onCreate，
                alipayInterface.onCreate(bundle);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    /**
     * 重新，通过className拿到类名
     *
     * @return
     */
    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getDexClassLoader();
    }

    /**
     * @return
     */
    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getResources();
    }

    @Override
    protected void onStart() {
        if (alipayInterface != null)
            alipayInterface.onStart();
        super.onStart();
    }

    @Override
    protected void onResume() {
        if (alipayInterface != null)
            alipayInterface.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (alipayInterface != null)
            alipayInterface.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        if (alipayInterface != null)
            alipayInterface.onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (alipayInterface != null)
            alipayInterface.onDestroy();
        super.onDestroy();
    }

    @Override
    public void startActivity(Intent intent) {
        String className = intent.getStringExtra("className");
        Intent intent1 = new Intent(this, ProxyActivity.class);
        intent1.putExtra("className",className);
        super.startActivity(intent1);
    }
}
