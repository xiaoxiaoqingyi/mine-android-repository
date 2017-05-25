package com.opensource.androidrepository.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.TextView;

import com.opensource.androidrepository.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkTextActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_link_text);
        init();
    }

    private void init(){
        TextView link_txt = (TextView)findViewById(R.id.link_txt);
        Linkify.addLinks(link_txt, mentionsPattern, mentionsScheme,matchFilter,transformFilter);
        Linkify.addLinks(link_txt, trendsPattern, trendsScheme, null,transformFilter);
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
