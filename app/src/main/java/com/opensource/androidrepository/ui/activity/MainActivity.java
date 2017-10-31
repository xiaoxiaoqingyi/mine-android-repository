package com.opensource.androidrepository.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.opensource.androidrepository.R;
import com.opensource.androidrepository.ui.custom.CommomDialog;
import com.opensource.androidrepository.ui.custom.MenuPopupWindow;
import com.opensource.androidrepository.ui.custom.ShareDialog;
import com.opensource.androidrepository.util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity{

    private ListView mListview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        mListview = (ListView) findViewById(R.id.listview);
        mListview.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1,getData()));
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(MainActivity.this, AllDialogActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, LinkTextActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, DrawFocusRectActivity.class));
                        break;
                }
            }
        });
    }

    private List<String> getData(){
        List<String> data = new ArrayList<>();
        data.add("所有弹框");
        data.add("TextView文本识别，点击并跳转");
        data.add("动态画矩形框，焦点框");

        return data;
   }


}
