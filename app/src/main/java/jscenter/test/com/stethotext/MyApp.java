package jscenter.test.com.stethotext;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp.StethoInterceptor;
import com.squareup.okhttp.OkHttpClient;

/**
 * 作者：BigManing
 * 时间：2015年12月23日 15:02:23
 * 邮箱：lingshui2001@qq.com
 * 详情： 初始化  stetho
 */
public class MyApp extends Application {

    public static OkHttpClient okHttpClient;

    @Override
    public void onCreate() {
        // 1 初始化    stetho
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
        // 2 网络 拦截   全局就这一个 实例
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
        okHttpClient.networkInterceptors().add(new StethoInterceptor());
        super.onCreate();
    }
}
