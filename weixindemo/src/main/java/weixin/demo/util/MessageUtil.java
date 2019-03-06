package weixin.demo.util;

import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import weixin.demo.pojo.Image;
import weixin.demo.pojo.ImageMessage;
import weixin.demo.pojo.News;
import weixin.demo.pojo.NewsMessge;
import weixin.demo.pojo.TextMessage;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageUtil {
    public static final String MESSAGE_TEXT = "text";

    public static final String MESSAGE_IMAGE = "image";

    public static final String MESSAGE_THUMB = "thumb"; //缩略图

    public static final String MESSAGE_VOICE = "voice";

    public static final String MESSAGE_VIDEO = "video";

    public static final String MESSAGE_SHORTVIDEO = "shortvideo";

    public static final String MESSAGE_LINK = "link";

    public static final String MESSAGE_LOCATION = "location";

    public static final String MESSAGE_NEWS = "news";

    public static final String MESSAGE_EVENT = "event";

    public static final String MESSAGE_SUBSCRIBE = "subscribe";

    public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";

    public static final String MESSAGE_CLICK = "CLICK";

    public static final String MESSAGE_VIEW = "VIEW";

    public static final String MESSAGE_SCAN = "SCAN";

    /**
     * XML格式转为map对象
     *
     * @param request
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
        Map<String, String> map = new HashMap<>();
        SAXReader reader = new SAXReader();
        InputStream is = request.getInputStream();
        Document doc = reader.read(is);
        Element root = doc.getRootElement();
        List<Element> list = root.elements();
        for (Element e : list) {
            map.put(e.getName(), e.getText());
        }
        is.close();
        return map;
    }

    /**
     * text消息转xml
     *
     * @param param
     * @return
     */
    public static String objectToXml(TextMessage param) {
        XStream xs = new XStream();
        xs.alias("xml", param.getClass());
        return xs.toXML(param);
    }

    /**
     * 图文消息转xml
     *
     * @param newsMessge
     * @return
     */
    public static String newsMessageToXml(NewsMessge newsMessge) {
        XStream xs = new XStream();
        xs.alias("xml", newsMessge.getClass());
        xs.alias("item", new News().getClass());
        return xs.toXML(newsMessge);
    }

    /**
     * 图片消息转xml
     *
     * @param imageMessage
     * @return
     */
    public static String imageMessageToXml(ImageMessage imageMessage) {
        XStream xs = new XStream();
        xs.alias("xml", imageMessage.getClass());
        return xs.toXML(imageMessage);
    }

    /**
     * 关注主菜单
     *
     * @return
     */
    public static String menuText() {
        StringBuffer sb = new StringBuffer();
        sb.append("欢迎关注史上最可爱的汤汤~~~请选择：");
        sb.append("1、小汤汤真可爱\n\n");
        sb.append("2、小汤汤最可爱\n\n");
        sb.append("3、获取专区信息\n\n");
        sb.append("4、小汤汤图图一枚\n\n");
        sb.append("回复 ? 调出主菜单");
        return sb.toString();
    }

    /**
     * 初始化文字消息
     *
     * @param fromUserName
     * @param toUserName
     * @param content
     * @return
     */
    public static String initText(String fromUserName, String toUserName, String content) {
        TextMessage message = new TextMessage();
        message.setFromUserName(toUserName);
        message.setToUserName(fromUserName);
        message.setMsgType(MESSAGE_TEXT);
        message.setCreateTime(new Date().getTime() + "");
        message.setContent(content);
        return objectToXml(message);
    }

    /**
     * 初始化图文消息
     *
     * @param fromUserName
     * @param toUserName
     * @return
     */
    public static String initNewsMessage(String fromUserName, String toUserName) {
        List<News> newsList = new ArrayList<>();
        NewsMessge newsMessage = new NewsMessge();
        //组建一条图文↓ ↓ ↓
        News newsItem = new News();
        newsItem.setTitle("欢迎来到小汤汤专区");
        newsItem.setDescription("奶萌汤~");
        newsItem.setPicUrl("https://avatars1.githubusercontent.com/u/41277621?s=400&u=6c05d70a4da70d2170d642c547fc1be4c0646790&v=4");
        newsItem.setUrl("avatars1.githubusercontent.com");
        newsList.add(newsItem);

        //组装图文消息相关信息
        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(new Date().getTime() + "");
        newsMessage.setMsgType(MESSAGE_NEWS);
        newsMessage.setArticles(newsList);
        newsMessage.setArticleCount(newsList.size());

        //调用newsMessageToXml将图文消息转化为XML结构并返回
        return MessageUtil.newsMessageToXml(newsMessage);
    }

    /**
     * 初始化图片消息
     *
     * @param fromUserName
     * @param toUserName
     * @return
     */
    public static String initImageMessage(String fromUserName, String toUserName) {
        String message = null;
        Image img = new Image();
        img.setMediaId("VUr39RFqZX86cx2wWI9daoGjGDxKIKcLWY7Cgp6-XgZtYMFlw4StT2_TX5h8JOyR");
        String url = WeiXinUtil.DOWNLOAD_URL.replace("ACCESS_TOKEN", "")
                .replace("MEDIA_ID", "VUr39RFqZX86cx2wWI9daoGjGDxKIKcLWY7Cgp6-XgZtYMFlw4StT2_TX5h8JOyR");
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setFromUserName(toUserName);
        imageMessage.setToUserName(fromUserName);
        imageMessage.setMsgType(MESSAGE_IMAGE);
        imageMessage.setCreateTime(new Date().getTime() + "");
        imageMessage.setImage(img);
        message = imageMessageToXml(imageMessage);
//        return message;
        return WeiXinUtil.download(url);
    }


}






