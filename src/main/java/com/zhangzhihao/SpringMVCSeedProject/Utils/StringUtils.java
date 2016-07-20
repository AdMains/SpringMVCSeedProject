package com.zhangzhihao.SpringMVCSeedProject.Utils;


public class StringUtils {
    /**
     * 是Null或""吗？
     *
     * @param value 需要判断的对象
     * @return 是Null或""吗？
     */
    private boolean isNullOrEmpty(final Object value) {
        if (value instanceof String)
            return "".equals(value);
        return value == null;
    }
}
