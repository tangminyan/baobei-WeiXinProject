package weixin.demo.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by tangminyan on 2019/3/4.
 */
public class WeiXinUtil {
    private final static String APPID = "wxbb9b0898c6e16633";
    private final static String APPSECRET = "a477c6b2271e1a3841dbd69178be54bc";
    public final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
            + APPID + "&secret=" + APPSECRET;

    public static JSONObject doGetStr(String url) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        JSONObject jsonObject = null;
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if(entity != null) {
            String result = EntityUtils.toString(entity, "UTF-8");
            jsonObject = JSONObject.parseObject(result);
        }
        return jsonObject;
    }

    public static JSONObject doPostStr(String url, String outStr) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        post.setEntity(new StringEntity(outStr, "UTF-8"));
        HttpResponse response = client.execute(post);
        String result = EntityUtils.toString(response.getEntity(), "UTF-8");
        return JSONObject.parseObject(result);
    }

    /*@Cacheable(value = "access_token", key = "token")
    public static AccessToken getAccessToken() throws IOException {
        AccessToken token = new AccessToken();
        String url = ACCESS_TOKEN_URL;
        JSONObject json = doGetStr(url);
        if(json != null) {
            token.setToken(json.getString("access_token"));
            token.setExpiresIn(json.getInteger("expires_in"));
        }
        return token;
    }*/
}
