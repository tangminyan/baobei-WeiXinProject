package weixin.demo.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by tangminyan on 2019/3/4.
 */
@Slf4j
public class WeiXinUtil {
    private final static String APPID = "wxbb9b0898c6e16633";
    private final static String APPSECRET = "a477c6b2271e1a3841dbd69178be54bc";
    public final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
            + APPID + "&secret=" + APPSECRET;
    private final static String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

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

    public static String upload(String filePath, String accessToken, String type) throws IOException {
        File file = new File(filePath);
        if(!file.exists() || !file.isFile()) {
            throw new IOException("文件不存在");
        }
        String url = UPLOAD_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);
        URL urlObj = new URL(url);
        //获得一个新的HttpURLConnection对象
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
        con.setRequestMethod("POST");
        //设置是否从httpUrlConnection读入
        con.setDoInput(true);
        //设置是否向httpUrlConnection输出
        con.setDoOutput(true);
        //Post 请求不能使用缓存
        con.setUseCaches(false);
        //设置请求头消息
        con.setRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("Charset", "UTF-8");
        String boundary = "----------" + System.currentTimeMillis();
        con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

        StringBuilder sb = new StringBuilder();
        sb.append("--");
        sb.append(boundary);
        sb.append("\r\n");
        sb.append("Content-Disposition:form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n" + file.getName() + "\"\r\n");

        byte[] head = sb.toString().getBytes("utf-8");
        OutputStream out = new DataOutputStream(con.getOutputStream());
        out.write(head);

        //把文件以流文件形式推入url
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = in.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
        in.close();
        byte[] foot = ("\r\n--" + boundary + "--\r\n").getBytes("utf-8");
        out.write(foot);
        out.flush();
        out.close();

        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        String result = null;
        try {
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = null;
            while((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            if(result == null){
                result = buffer.toString();
            }
        } catch (IOException e) {
            log.info(e.getMessage());
            e.printStackTrace();
        } finally {
            if(reader != null) {
                reader.close();
            }
        }

        JSONObject jsonObject = JSONObject.parseObject(result);
        System.out.println(jsonObject);
        String typeName = "media_id";
        if(!"image".equals(type)) {
            typeName = type + "_media_id";
        }
        String mediaId = jsonObject.getString(typeName);
        return mediaId;
    }

}
















