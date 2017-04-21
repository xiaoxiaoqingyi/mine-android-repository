package com.opensource.androidrepository.ui.activity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.opensource.androidrepository.R;
import com.opensource.androidrepository.ui.custom.CommomDialog;
import com.opensource.androidrepository.ui.custom.MenuPopupWindow;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        findViewById(R.id.txt).setOnClickListener(this);
        menu =(TextView)findViewById(R.id.menu);
        menu.setOnClickListener(this);
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
            case R.id.menu:
                MenuPopupWindow menuPopupWindow = new MenuPopupWindow(this, new MenuPopupWindow.OnItemClickListener() {
                    @Override
                    public void onItemClick(PopupWindow popupWindow, int position) {

                        popupWindow.dismiss();
                    }
                });
                menuPopupWindow.showAsDropDown(menu, -200, 40);
                break;
        }
    }
}
