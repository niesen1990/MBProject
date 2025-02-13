package com.cmbb.smartkids.widget.notify;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.tools.TDevice;
import com.cmbb.smartkids.tools.log.Log;

public class WaitDialog extends Dialog {

    private TextView _messageTv;

    public WaitDialog(Context context) {
        super(context);
        init(context);
    }

    public WaitDialog(Context context, int defStyle) {
        super(context, defStyle);
        init(context);
    }

    protected WaitDialog(Context context, boolean cancelable, OnCancelListener listener) {
        super(context, cancelable, listener);
        init(context);
    }

    public static boolean dismiss(WaitDialog dialog) {
        if (dialog != null) {
            dialog.dismiss();
            return false;
        } else {
            return true;
        }
    }

    public static void hide(Context context) {
        if (context instanceof DialogControl)
            ((DialogControl) context).hideWaitDialog();
    }

    public static boolean hide(WaitDialog dialog) {
        if (dialog != null) {
            dialog.hide();
            return false;
        } else {
            return true;
        }
    }

    private void init(Context context) {
        setCancelable(false);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_wait, null);
        _messageTv = (TextView) view.findViewById(R.id.waiting_tv);
        setContentView(view);
    }

    public static void show(Context context) {
        if (context instanceof DialogControl)
            ((DialogControl) context).showWaitDialog();
    }

    public static boolean show(WaitDialog waitdialog) {
        boolean flag;
        if (waitdialog != null) {
            waitdialog.show();
            flag = false;
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (TDevice.isTablet()) {
            int i = TDevice.dip2px(360, getContext());
            if (i < TDevice.getScreenWidth()) {
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.width = i;
                getWindow().setAttributes(params);
            }
        }
    }

    public void setMessage(int message) {
        _messageTv.setText(message);
    }

    public void setMessage(String message) {
        _messageTv.setText(message);
    }
}
