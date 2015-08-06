package com.cmbb.smartkids.mengrecyclerview.interfaces;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Response;

import java.util.List;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 9:31
 */
public interface DataProvider<T> {

    void doInitialize(Callback callback);

    void doRefresh(Callback callback);

    void doMore(Callback callback);

    List<T>  doParser(Response response);

    void doSave(List<T> data);

}
