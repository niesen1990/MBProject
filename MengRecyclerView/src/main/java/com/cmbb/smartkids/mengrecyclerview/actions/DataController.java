package com.cmbb.smartkids.mengrecyclerview.actions;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.cmbb.smartkids.mengrecyclerview.interfaces.AdapterRespondent;
import com.cmbb.smartkids.mengrecyclerview.interfaces.DataProvider;
import com.cmbb.smartkids.mengrecyclerview.interfaces.UIRespondent;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 9:33
 */
public abstract class DataController<T> implements DataProvider<T> {

    private boolean isBusy = false;
    private boolean isEnd = false;
    public List<T> mRepository;

    //分页的count
    private int mPageSize = 0;
    private int mNextTimeOffset = 0;
    private int mRequestTimes = 0;

    // 是否保存数据
    private boolean cache = false;

    private List<UIRespondent<T>> mUIRespondents;
    private List<AdapterRespondent> mAdapterRespondents;

    protected enum ActionType {
        Initialize,
        More,
        Refresh
    }

    /**
     * 对应Adapter响应
     */
    public enum AdapterMessageType {
        INSERT,
        CHANGE,
        REMOVE
    }

    /**
     * 对用UI响应
     */
    public enum UIMessageType {
        OnDataSetChanged,
        InitializeStart,
        InitializeDone,
        LoadingMoreStart,
        LoadMoreDone,
        RefreshingStart,
        RefreshingDone,
        End
    }

    public boolean isBusy() {
        return isBusy;
    }

    public boolean isEmpty() {
        return mRepository == null || mRepository.size() == 0;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public boolean isCache() {
        return cache;
    }

    public void setCache(boolean cache) {
        this.cache = cache;
    }

    public int getSize() {

        if (mRepository == null) {
            return 0;
        }

        return mRepository.size();
    }

    public int getRequestOffset() {
        return mNextTimeOffset;
    }

    /**
     * @return int 获取每页的数量
     */
    public int getPageSize() {
        return mPageSize;
    }

    /**
     * @param pageSize int 设置每页的数量
     */
    public void setPageSize(int pageSize) {
        mPageSize = pageSize;
    }

    public List<T> getData() throws Exception {
        return mRepository;
    }

    /**
     * 清除数据
     */
    public void clear() {
        if (mRepository != null) {
            mRepository.clear();
            mNextTimeOffset = 0;
            mRequestTimes = 0;
        }
    }

    /**
     * 顶部添加数据
     *
     * @param data T
     */
    public void add(T data) {
        if (data != null) {
            mRepository.add(0, data);
            dispatchAdapterMessage(AdapterMessageType.INSERT, 0);
        }
    }

    /**
     * 尾部添加数据
     *
     * @param data T
     */
    public void append(T data) {
        if (data != null) {
            mRepository.add(data);
            dispatchAdapterMessage(AdapterMessageType.INSERT, mRepository.size() - 1);
        }
    }

    /**
     * 顶部添加数据
     *
     * @param data List<T>
     */
    public void add(List<T> data) {
        if (data == null) {
            isEnd = true;
            return;
        }
        if (mRepository == null) {
            mRepository = new ArrayList<>();
        }
        if (data.size() < mPageSize) {
            isEnd = true;
        }
        mRepository.addAll(0, data);
    }

    /**
     * 尾部添加数据
     *
     * @param data List<T> data
     */
    public void append(List<T> data) {
        if (data == null) {
            isEnd = true;
            return;
        }
        if (mRepository == null) {
            mRepository = new ArrayList<>();
        }
        if (data.size() < mPageSize) {
            isEnd = true;
        }
        mRepository.addAll(data);
    }

    public T getData(int position) {
        return mRepository.get(position);
    }

    public void addUIRespondent(UIRespondent<T> respondent) {
        if (mUIRespondents == null) {
            mUIRespondents = new ArrayList<>();
        }
        mUIRespondents.add(respondent);
    }

    public void addAdapterRespondent(AdapterRespondent respondent) {
        if (mAdapterRespondents == null) {
            mAdapterRespondents = new ArrayList<>();
        }
        mAdapterRespondents.add(respondent);
    }

    /**
     * 初始化数据
     */
    public final void initialize() {
        if ((isEnd || isBusy)) {
            return;
        }
        isBusy = true;
        doInitialize(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                initFailure(e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    Log.i("response", "response = " + response.toString());
                    List<T> data = doParser(response);
                    initSuccess(data);

                } else {
                    Log.i("response", "response = " + response.toString());
                }
            }
        });
    }

    /**
     * 初始化成功
     *
     * @param data List<T>
     */
    public void initSuccess(List<T> data) {
        add(data);
        mRequestTimes++;
        mNextTimeOffset = mPageSize * mRequestTimes;
        dispatchAdapterMessage(AdapterMessageType.CHANGE, 0);
        dispatchUIMessage(UIMessageType.InitializeDone, null, data);
        if (isEnd) {
            dispatchUIMessage(UIMessageType.End, null, null);
        }
        isBusy = false;
        // 是否保存数据
        if (isCache()) {
            doSave(data);
        }
    }

    /**
     * 初始化失败
     */
    public void initFailure(Exception e) {
        dispatchUIMessage(UIMessageType.InitializeDone, e, null);
        isBusy = false;
    }

    /**
     * 刷新
     */
    public final void refresh() {
        isBusy = true;
        dispatchUIMessage(UIMessageType.InitializeStart, null, null);
        dispatchUIMessage(UIMessageType.RefreshingStart, null, null);
        doRefresh(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                refreshFailure(e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                List<T> data = doParser(response);
                refreshSuccess(data);
            }
        });

    }

    /**
     * 刷新成功
     *
     * @param data List<T>
     */
    public void refreshSuccess(List<T> data) {
        add(data);
        mRequestTimes++;
        mNextTimeOffset = mPageSize * mRequestTimes;
        dispatchAdapterMessage(AdapterMessageType.CHANGE, 0);
        dispatchUIMessage(UIMessageType.RefreshingDone, null, data);
        if (isEnd) {
            dispatchUIMessage(UIMessageType.End, null, null);
        }
        isBusy = false;
    }

    /**
     * 刷新失败
     *
     * @param e
     */
    public void refreshFailure(Exception e) {
        dispatchUIMessage(UIMessageType.RefreshingDone, e, null);
        isBusy = false;
    }

    /**
     * 加载更多
     */
    public final void more() {
        if ((isEnd || isBusy)) {
            return;
        }
        isBusy = true;
        dispatchUIMessage(UIMessageType.LoadingMoreStart, null, null);
        doMore(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                moreFailure(e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                List<T> data = doParser(response);
                moreSuccess(data);
            }
        });
    }

    /**
     * 加载更多成功
     *
     * @param data List<T>
     */
    public void moreSuccess(List<T> data) {
        append(data);
        mRequestTimes++;
        mNextTimeOffset = mPageSize * mRequestTimes;
        dispatchAdapterMessage(AdapterMessageType.CHANGE, 0);
        dispatchUIMessage(UIMessageType.LoadMoreDone, null, data);
        if (isEnd) {
            dispatchUIMessage(UIMessageType.End, null, null);
        }
        isBusy = false;
    }

    /**
     * 加载更多失败
     *
     * @param e
     */
    public void moreFailure(Exception e) {
        dispatchUIMessage(UIMessageType.LoadMoreDone, e, null);
        isBusy = false;
    }

    private void dispatchAdapterMessage(AdapterMessageType type, int position) {
        Handler handler = new AdapterPosterHandler(Looper.getMainLooper());
        Message msg = Message.obtain(handler);
        msg.arg1 = type.ordinal();
        msg.arg2 = position;
        msg.sendToTarget();
    }

    private void dispatchUIMessage(UIMessageType type, Exception e, List<T> data) {
        if (mUIRespondents == null || mUIRespondents.size() == 0) {
            return;
        }
        Handler handler = new UIPosterHandler(Looper.getMainLooper());
        Message msg = Message.obtain(handler);
        msg.arg1 = type.ordinal();
        msg.obj = data != null ? data : e;
        msg.sendToTarget();
    }

    public class AdapterPosterHandler extends Handler {

        public AdapterPosterHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            for (AdapterRespondent adapterRespondent : mAdapterRespondents) {
                AdapterMessageType type = AdapterMessageType.values()[msg.arg1];
                switch (type) {
                    case INSERT:
                        adapterRespondent.onDataInsert(msg.arg1);
                        break;
                    case REMOVE:
                        adapterRespondent.onDataRemove(msg.arg2);
                        break;
                    case CHANGE:
                        adapterRespondent.onDataChanged();
                        break;
                }
            }
        }

    }

    public class UIPosterHandler extends Handler {

        private UIPosterHandler(Looper looper) {
            super(looper);
        }


        @Override
        public void handleMessage(Message msg) {
            if (getLooper() != Looper.getMainLooper()) {
                throw new RuntimeException("Can not post UI update in non-main thread");
            }
            for (UIRespondent<T> ui : mUIRespondents) {
                UIMessageType type = UIMessageType.values()[msg.arg1];
                Object o = msg.obj;
                List<T> data = o instanceof List ? (List<T>) o : null;
                Exception e = o instanceof Exception ? (Exception) o : null;
                switch (type) {
                    case InitializeStart:
                        ui.onInitializeStart();
                        break;
                    case InitializeDone:
                        ui.onInitializeDone(e, data);
                        break;
                    case LoadingMoreStart:
                        ui.onLoadingMoreStart();
                        break;
                    case LoadMoreDone:
                        ui.onLoadMoreDone(e, data);
                        break;
                    case RefreshingStart:
                        ui.onRefreshingStart();
                        break;
                    case RefreshingDone:
                        ui.onRefreshDone(e, data);
                        break;
                    case End:
                        ui.onEnd();
                        break;
                }
            }
        }
    }

}
