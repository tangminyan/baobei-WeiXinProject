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

    @Test
    public void testUpload() throws IOException {
//        AccessToken token = WeiXinUtil.getAccessToken();
//        System.out.println("token为: " + token.getToken());
//        System.out.println("有效时间为: " + token.getExpiresIn());
        String path = "D:\\WorkSpaces\\mygithub\\IMG_0694.JPG";
        String MediaId = WeiXinUtil.upload(path, "19_hml5asWSGFpqB1RFgGlAFQsWxjdiyo9dz7u4N3RzUqLnGUF5tPrEUkpCrPB8Tq8ZJ5l1Sj__O1DS7tCd9CQOGlRBVxG410gqBT0tHmscqdGyb9jVPEZIZ-YlBdULQF_geveJyP21vEEZOAvxMMVdAFAVVE",
                "thumb");
        System.out.println(MediaId);
    }

}
