package com.zhangzhihao.SpringMVCSeedProject.Utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class LogUtils {
    private static Logger logger = LoggerFactory.getLogger(LogUtils.class);

    public static void LogToDB(Exception ex) {

        StackTraceElement stackTraceElement = ex.getStackTrace()[0];
        //出错行
        int lineNumber = stackTraceElement.getLineNumber();
        //方法签名
        String methodName = stackTraceElement.getMethodName();
        //获得类名
        String className = stackTraceElement.getClassName();

        logger.error("方法" + className + "." + methodName, "参数" + stackTraceElement, "错误行：" + lineNumber, "时间" + new Date(), "异常内容" + ex.toString());
    }
}
