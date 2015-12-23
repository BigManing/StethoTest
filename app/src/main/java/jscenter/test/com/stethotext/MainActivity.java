package jscenter.test.com.stethotext;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
/**
 * 作者：BigManing
 * 时间：2015年12月23日 15:02:23
 * 邮箱：lingshui2001@qq.com
 * 详情：简单的界面
 */
public class MainActivity extends Activity {

    private EditText url;
    private EditText key;
    private EditText value;
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        initView();
    }

    private void initView() {
        url = (EditText) findViewById(R.id.url);
        key = (EditText) findViewById(R.id.key);
        value = (EditText) findViewById(R.id.value);
        content = (TextView) findViewById(R.id.content);
        // 请求网络
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 请求对象
                Request request=new Request.Builder()
                        .url(getText(url))
                        .build();
                // 网络请求
                MyApp.okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }
                    @Override
                    public void onResponse(Response response) throws IOException {
                        //   响应成功   吧数据显示到文本中
                        final String str = response.body().string();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                content.setText(str);
                            }
                        });
                    }
                });
            }
        });
        // sp 保存数据
        findViewById(R.id.sp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSharedPreferences("Setting", MODE_PRIVATE).edit().
                        putString(getText(key),getText(value)).
                        commit();

            }
        });
    }

    private String getText(EditText key) {
        return key.getText().toString().trim();
    }

}
