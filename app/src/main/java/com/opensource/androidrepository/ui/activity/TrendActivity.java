package com.opensource.androidrepository.ui.activity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.opensource.androidrepository.R;

public class TrendActivity extends AppCompatActivity {

    private static final Uri PROFILE_URI = Uri.parse(MainActivity.TRENDS_SCHEMA);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trend);
        init();
    }

    private void init(){

        TextView txt = (TextView)findViewById(R.id.txt);

        //获取用户点击的 话题
        Uri uri = getIntent().getData();
        if (uri !=null && PROFILE_URI.getScheme().equals(uri.getScheme())) {
            String content = uri.getQueryParameter(MainActivity.PARAM_CONTENT);
            setTitle(content);
            txt.setText("获取到的内容为："+content);
        }
    }
}
