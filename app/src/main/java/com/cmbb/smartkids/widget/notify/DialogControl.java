package com.cmbb.smartkids.widget.notify;

import android.content.DialogInterface;

public interface DialogControl {

    public abstract void hideWaitDialog();

    public abstract WaitDialog showWaitDialog();

    public abstract WaitDialog showWaitDialog(int resid);

    public abstract WaitDialog showWaitDialog(String text);

    public abstract WaitDialog showCancelableWaitDialog(int resid,
                                                        DialogInterface.OnCancelListener listener);
}
