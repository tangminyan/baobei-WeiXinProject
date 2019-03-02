package weixin.demo.util;

import org.springframework.util.ObjectUtils;

import java.security.MessageDigest;
import java.util.Arrays;

public class CheckUtil {
    public static boolean checkSignature(String tooken, String signature, String timestamp, String nonce) {
        String[] arr = {tooken, timestamp, nonce};
        Arrays.sort(arr);
        StringBuffer sb = new StringBuffer();
        for (String s : arr) {
            sb.append(s);
        }
        String tmp = getSha1(sb.toString());
        return tmp.equals(signature);
    }

    private static String getSha1(String str) {
        if (ObjectUtils.isEmpty(str)) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5','6','7','8','9','a','b','c','d','e','f'};
        try{
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));
            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for(int i = 0; i < j; i++) {
                byte b = md[i];
                buf[k++] = hexDigits[b >>> 4 & 0xf];
                buf[k++] = hexDigits[b & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
