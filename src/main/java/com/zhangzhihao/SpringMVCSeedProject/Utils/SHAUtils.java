package com.zhangzhihao.SpringMVCSeedProject.Utils;


import javax.validation.constraints.NotNull;
import java.security.MessageDigest;

import static com.zhangzhihao.SpringMVCSeedProject.Utils.LogUtils.LogToDB;

public class SHAUtils {
    public static String getSHA_1(@NotNull final String inStr) {
        return baseSHA(inStr, "SHA-1");
    }

    public static String getSHA_256(@NotNull final String inStr) {
        return baseSHA(inStr, "SHA-256");
    }

    private static String baseSHA(@NotNull final String inStr, @NotNull final String messageDigestInstance) {
        MessageDigest md = null;
        String outStr = null;
        try {
            md = MessageDigest.getInstance(messageDigestInstance);
            byte[] digest = md.digest(inStr.getBytes("UTF-8"));
            outStr = bytetoString(digest);
        } catch (Exception ex) {
            LogToDB(ex);
        }
        return outStr;
    }


    public static String bytetoString(@NotNull final byte[] digest) {
        String str = "";
        String tempStr = "";

        for (int i = 0; i < digest.length; i++) {
            tempStr = (Integer.toHexString(digest[i] & 0xff));
            if (tempStr.length() == 1) {
                str = str + "0" + tempStr;
            } else {
                str = str + tempStr;
            }
        }
        return str.toLowerCase();
    }
}
