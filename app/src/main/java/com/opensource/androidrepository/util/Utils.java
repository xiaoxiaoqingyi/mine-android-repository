package com.opensource.androidrepository.util;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator
 */
public class Utils {


    public static void toast(Context mContext, String content){
        Toast.makeText(mContext,content, Toast.LENGTH_SHORT).show();
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int getDisplayWidth(Activity context){
        WindowManager wm = context.getWindowManager();

        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }

    public static int getDisplayWHHeigth(Activity context){
        WindowManager wm = context.getWindowManager();

        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }

    /**
     * 数字时间串
     * @return
     */
    public static String getDateNumber(){
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 随机num个字母
     * @return
     */
    public static String randomCapital(int num){
        String chars = "abcdefghijklmnopqrstuvwxyz";
        StringBuffer buffer = new StringBuffer();
        for (int i =0; i< num; i++){
            buffer.append(chars.charAt((int)(Math.random() * 26)));
        }
        return  buffer.toString();
    }


    /**
     *  获取 时间字符串（yyyy-MM-dd HH:mm:ss），如 2017-04-01 12:10:22
     * @param date
     * @return
     */
    public static String getDateString(Date date){
        if(date != null){
            return  sdf.format(new Date());
        }
        return  sdf.format(new Date());
    }

    private final static long minute = 60 * 1000;// 1分钟
    private final static long hour = 60 * minute;// 1小时
    private final static long day = 24 * hour;// 1天
    private final static long month = 31 * day;// 月
    private final static long year = 12 * month;// 年

    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 返回文字描述的日期
     *
     * @param date
     * @return
     */
    public static String getTimeFormatText(Date date) {
        if (date == null) {
            return null;
        }
        long diff = new Date().getTime() - date.getTime();
        long r = 0;
        if (diff > year) {
            r = (diff / year);
            return r + "年前";
        }
        if (diff > month) {
            r = (diff / month);
            return r + "个月前";
        }
        if (diff > day) {
            r = (diff / day);
            return r + "天前";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "个小时前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }

    /**
     * 返回文字描述的日期
     *
     * @param dateStr 日期字符串
     * @return
     */
    public static String getTimeFormatText(String dateStr) {

        Date date = null ;
        try {
            date = sdf.parse(dateStr);
        }catch (Exception e){
             return  "";
        }

        return  getTimeFormatText(date);
    }


}
