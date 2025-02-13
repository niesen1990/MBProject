package com.cmbb.smartkids.widget.notify;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.tools.TDevice;
import com.cmbb.smartkids.tools.log.Log;

public class PinterestToast {
    public static final long DURATION_LONG = 5000L;
    public static final long DURATION_MEDIUM = 3500L;
    public static final long DURATION_SHORT = 2500L;
    private static final String TAG = "PinterestToast";
    private long _duration = 3500l;
    private ToastView _toastVw;
    private Activity activity;

    public PinterestToast(Activity activity) {
        init(activity);
    }

    public PinterestToast(Activity activity, String message, int icon,
                          String action, int actionIcon, long l) {
        _duration = l;
        init(activity);
        setMessage(message);
        setMessageIc(icon);
        setAction(action);
        setActionIc(actionIcon);
    }

    private void init(Activity activity) {
        this.activity = activity;
        _toastVw = new ToastView(activity);
        setLayoutGravity(81);
    }

    public long getDuration() {
        return _duration;
    }

    public void setAction(String s) {
        _toastVw.actionTv.setText(s);
    }

    public void setActionIc(int i) {
        _toastVw.actionIv.setImageResource(i);
    }

    public void setDuration(long l) {
        _duration = l;
    }

    public void setLayoutGravity(int i) {
        if (i != 0) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-2,
                    -2);
            params.gravity = i;
            int j = TDevice.dip2px(16, activity);
            params.setMargins(j, j, j, j);
            _toastVw.setLayoutParams(params);
        }
    }

    public void setMessage(String s) {
        _toastVw.messageTv.setText(s);
    }

    public void setMessageIc(int i) {
        _toastVw.messageIc.setImageResource(i);
    }

    public void show() {
        final ViewGroup content = (ViewGroup) ((Activity) _toastVw.getContext())
                .findViewById(android.R.id.content);
        if (content != null) {
            ObjectAnimator.ofFloat(_toastVw, "alpha", 0.0F).setDuration(0L)
                    .start();
            content.addView(_toastVw);
            ObjectAnimator.ofFloat(_toastVw, "alpha", 0.0F, 1.0F)
                    .setDuration(167L).start();
            _toastVw.postDelayed(new Runnable() {

                @Override
                public void run() {
                    ObjectAnimator animator = ObjectAnimator.ofFloat(_toastVw,
                            "alpha", 1.0F, 0.0F);
                    animator.setDuration(100L);
                    animator.addListener(new Animator.AnimatorListener() {

                        @Override
                        public void onAnimationStart(Animator animation) {
                            // TODO Auto-generated method stub
                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {
                            // TODO Auto-generated method stub
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            content.removeView(_toastVw);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }
                    });
                    animator.start();
                }
            }, _duration);
        } else {
            Log.e(TAG, "Toast not shown! Content view is null!");
        }
    }

    private class ToastView extends FrameLayout {

        public ImageView actionIv;
        public TextView actionTv;
        public ImageView messageIc;
        public TextView messageTv;

        public ToastView(Context context) {
            this(context, null);
        }

        public ToastView(Context context, AttributeSet attributeset) {
            this(context, attributeset, 0);
        }

        public ToastView(Context context, AttributeSet attributeset, int i) {
            super(context, attributeset, i);
            init();
        }

        private void init() {
            LayoutInflater.from(getContext()).inflate(
                    R.layout.view_pinterest_toast, this, true);
            messageTv = (TextView) findViewById(R.id.title_tv);
            messageIc = (ImageView) findViewById(R.id.icon_iv);
            actionTv = (TextView) findViewById(R.id.title_tv);
            actionIv = (ImageView) findViewById(R.id.icon_iv);
        }
    }
}
