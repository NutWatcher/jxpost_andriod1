package com.activity;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.imooc.weixin6_0.R;


public class Activity_AccountInfo extends Activity {

    private TextView title_textView ;
    private ImageButton button ;
    private ImageButton button1 ;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Rect rect = new Rect();
        button.getWindowVisibleDisplayFrame(rect);
        Window window = getWindow();
        int statusBarHeight = rect.top;// 状态栏的高度
        int contentViewTop = window.findViewById(Window.ID_ANDROID_CONTENT).getTop(); // 标题栏跟状态栏的总体高度
        int titleBarHeight = contentViewTop - statusBarHeight;// 标题栏的高度：用上面的值减去状态栏的高度及为标题栏高度
        LinearLayout.LayoutParams para = new LinearLayout.LayoutParams(titleBarHeight,titleBarHeight);
        button.setLayoutParams(para);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); // 注意顺序
        setContentView(R.layout.activity_activity__account_info);// 注意顺序
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,      // 注意顺序
                R.layout.title);

        button1 = (ImageButton)findViewById(R.id.title_right_imageButton);
        button1.setVisibility(View.GONE);
        button = (ImageButton) findViewById(R.id.title_imageButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity_AccountInfo.this.setResult(RESULT_OK);
                Activity_AccountInfo.this.finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity__account_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
