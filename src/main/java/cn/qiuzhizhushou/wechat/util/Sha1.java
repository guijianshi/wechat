package cn.qiuzhizhushou.wechat.util;

import java.security.MessageDigest;

/**
 * Created by IDEA.
 * User: mc
 * Date: 19/3/11
 * Time: 下午7:57
 */
public class Sha1
{

    private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    public static Boolean checkSHA1(String origin, String sign)
    {
        String makeSign = SHA1Encode(origin, null);
        if (sign.equals(makeSign)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * MD5加密
     *
     * @param origin
     * @param charsetname
     * @return
     */
    public static String MD5Encode(String origin, String charsetname) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            if ((charsetname == null) || ("".equals(charsetname))) {
                return bytesToHexString(md.digest(origin.getBytes()));
            } else {
                return bytesToHexString(md.digest(origin.getBytes(charsetname)));
            }
        } catch (Exception localException) {
        }
        return null;
    }

    /**
     * sha1加密
     *
     * @param origin
     * @param charsetname
     * @return
     */
    public static String SHA1Encode(String origin, String charsetname) {
        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA1");
            if ((charsetname == null) || ("".equals(charsetname))) {
                return bytesToHexString(sha1.digest(origin.getBytes()));
            } else {
                return bytesToHexString(sha1.digest(origin.getBytes(charsetname)));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static String bytesToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            int n = b[i];
            if (n < 0) {
                n += 256;
            }
            int d1 = n / 16;
            int d2 = n % 16;
            resultSb.append(hexDigits[d1] + hexDigits[d2]);
        }
        return resultSb.toString();
    }
}
