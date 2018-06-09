package com.plusend.secure;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;

public class SignUtil {

    static {
        System.loadLibrary("sign");
    }

    private static native int sign(Context context);

    public static void checkSign(Context context) {
        if (sign(context) != 1) {
            throw (new RuntimeException("Please don't hack me."));
        }
    }

    /**
     * 获取签名 HashCode
     */
    public static int getSignature(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            Signature[] signs = packageInfo.signatures;
            Signature sign = signs[0];
            return sign.hashCode();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
