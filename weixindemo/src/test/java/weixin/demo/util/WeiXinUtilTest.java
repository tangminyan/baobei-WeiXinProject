package weixin.demo.util;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import weixin.demo.pojo.AccessToken;
import weixin.demo.service.WeixinService;
import weixin.demo.service.impl.WeiXinServiceImpl;

import java.io.IOException;

/**
 * Created by tangminyan on 2019/3/4.
 */
public class WeiXinUtilTest {

    WeixinService weixinService = new WeiXinServiceImpl();

    @Test
    public void testToken() throws IOException {
        AccessToken token = weixinService.getAccessToken();
        System.out.println("token为: " + token.getToken());
        System.out.println("有效时间为: " + token.getExpiresIn());
    }

}
