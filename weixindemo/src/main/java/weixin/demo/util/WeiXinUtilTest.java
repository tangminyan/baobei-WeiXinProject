package weixin.demo.util;


import org.junit.Test;
import weixin.demo.pojo.AccessToken;

import java.io.IOException;

/**
 * Created by tangminyan on 2019/3/4.
 */
public class WeiXinUtilTest {
    @Test
    public void testToken() throws IOException {
        AccessToken token = WeiXinUtil.getAccessToken();
        System.out.println("token为: " + token.getToken());
        System.out.println("有效时间为: " + token.getExpiresIn());
    }

}
