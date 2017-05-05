package com.opensource.androidrepository.ui.activity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.opensource.androidrepository.R;
import com.opensource.androidrepository.ui.custom.CommomDialog;
import com.opensource.androidrepository.ui.custom.MenuPopupWindow;
import com.opensource.androidrepository.ui.custom.ShareDialog;
import com.opensource.androidrepository.util.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView menu;

    /**
     * 以下常量是为了在AndroidManifest.xml 下 Activity标签下配置，为了启动activity
     */
    public static final String MENTIONS_SCHEMA ="devdiv://weibo_mention";
    public static final String TRENDS_SCHEMA ="devdiv://weibo_trend";
    public static final String PARAM_CONTENT ="content";


    //用于正则表达， 匹配 @某人
    Pattern mentionsPattern = Pattern.compile("@(\\w+?)(?=\\W|$)(.)");
    String mentionsScheme = String.format("%s/?%s=", MENTIONS_SCHEMA, PARAM_CONTENT);

    //用于正则表达， 匹配  #话题#
    Pattern trendsPattern = Pattern.compile("#(\\w+)#");
    String trendsScheme = String.format("%s/?%s=", TRENDS_SCHEMA, PARAM_CONTENT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        findViewById(R.id.txt).setOnClickListener(this);
        findViewById(R.id.share).setOnClickListener(this);
        menu =(TextView)findViewById(R.id.menu);
        menu.setOnClickListener(this);

        TextView link_txt = (TextView)findViewById(R.id.link_txt);
        Linkify.addLinks(link_txt, mentionsPattern, mentionsScheme,matchFilter,transformFilter);
        Linkify.addLinks(link_txt, trendsPattern, trendsScheme, null,transformFilter);
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
            case R.id.share:
                new ShareDialog(MainActivity.this, R.style.dialog, new ShareDialog.OnItemClickListener() {
                    @Override
                    public void onClick(Dialog dialog, int position) {
                        dialog.dismiss();
                        switch (position){
                            case 1:
                                Utils.toast(MainActivity.this,"微信好友");
                                break;
                            case 2:
                                Utils.toast(MainActivity.this,"朋友圈");
                                break;
                            case 3:
                                Utils.toast(MainActivity.this,"QQ");
                                break;
                            case 4:
                                Utils.toast(MainActivity.this,"微博");
                                break;
                        }
                    }
                }).show();
                break;
        }
    }



    Linkify.MatchFilter matchFilter = new Linkify.MatchFilter() {

        @Override
        public boolean acceptMatch(CharSequence s, int start, int end) {

            return s.charAt(end-1) !='.';
        }

    };

    Linkify.TransformFilter transformFilter = new Linkify.TransformFilter() {
        @Override
        public String transformUrl(Matcher match, String url) {
            return match.group(1);
        }
    };
}
