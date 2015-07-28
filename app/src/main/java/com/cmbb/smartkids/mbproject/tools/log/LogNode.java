package com.cmbb.smartkids.mbproject.tools.log;

public interface LogNode {

    public void println(int priority, String tag, String msg, Throwable tr);

}
