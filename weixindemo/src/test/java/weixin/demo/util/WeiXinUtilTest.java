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
        String MediaId = WeiXinUtil.upload(path, "19_TOn8qzCqyqnAyJXoGq0peyxuNdN8pOFyZgehNIhmuzpf225T7-G6PDwYyJx9H6SRKNrMc--xvC1wx7_kCM4l0k-vvgM6fN4gLa-LjcOdT7JUiR3ekh50A86fQ4ntc4lLu7BJ2MpBD4xnzIZPVOPbAGARKL",
                "thumb");
        System.out.println(MediaId);
    }

}
