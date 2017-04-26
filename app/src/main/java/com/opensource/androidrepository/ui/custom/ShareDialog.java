package com.opensource.androidrepository.ui.custom;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.opensource.androidrepository.R;

/**
 * 分享页面
 */
public class ShareDialog extends Dialog implements View.OnClickListener{


    private Activity mContext;
    private OnItemClickListener mListener;


    public ShareDialog(Activity context) {
        super(context);
        this.mContext = context;
    }


    public ShareDialog(Activity context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    public ShareDialog(Activity context, int themeResId, OnItemClickListener mListener) {
        super(context, themeResId);
        this.mContext = context;
        this.mListener = mListener;
    }

    protected ShareDialog(Activity context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_share);
        setCanceledOnTouchOutside(false);
        initView();
        getWindow().setWindowAnimations(R.style.dialogWindowAnim); //设置窗口弹出动画

        //全屏处理
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        WindowManager wm = mContext.getWindowManager();

        lp.width = wm.getDefaultDisplay().getWidth(); //设置宽度
        getWindow().setAttributes(lp);
    }

    private void initView(){
         findViewById(R.id.lay_cancel).setOnClickListener(this);
         findViewById(R.id.target1).setOnClickListener(this);
         findViewById(R.id.target2).setOnClickListener(this);
         findViewById(R.id.target3).setOnClickListener(this);
         findViewById(R.id.target4).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lay_cancel:
                dismiss();
                break;
            case R.id.target1:
                if(mListener != null){
                    mListener.onClick(this, 1);
                }
                break;
            case R.id.target2:
                if(mListener != null){
                    mListener.onClick(this, 2);
                }
                break;
            case R.id.target3:
                if(mListener != null){
                    mListener.onClick(this, 3);
                }
                break;
            case R.id.target4:
                if(mListener != null){
                    mListener.onClick(this, 4);
                }
                break;

        }
    }

    public interface OnItemClickListener{
        void onClick(Dialog dialog, int position);
    }
}
