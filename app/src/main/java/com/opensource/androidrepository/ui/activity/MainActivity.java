package com.opensource.androidrepository.ui.activity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.opensource.androidrepository.R;
import com.opensource.androidrepository.ui.custom.CommomDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        findViewById(R.id.txt).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt:
                //弹出提示框
                new CommomDialog(this, R.style.dialog, "您确定删除此信息？", new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if(confirm){
                            Toast.makeText(MainActivity.this,"点击确定", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }

                    }
                })
                        .setTitle("提示").show();
                break;
        }
    }
}
