package com.cmbb.smartkids.mengrecyclerview.interfaces;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/6/30 11:24
 */
public interface AdapterRespondent {

    /**
     * 数据发生变化
     */
    void onDataChanged();

    /**
     * 数据插入
     *
     * @param position int
     */
    void onDataInsert(int position);

    /**
     * 数据移除
     *
     * @param position int
     */
    void onDataRemove(int position);
}
