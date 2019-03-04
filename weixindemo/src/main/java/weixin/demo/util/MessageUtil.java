package weixin.demo.util;

import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import weixin.demo.pojo.XmlParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageUtil {
    public static final String MESSAGE_TEXT = "text";

    public static final String MESSAGE_IMAGE = "image";

    public static final String MESSAGE_VOICE = "voice";

    public static final String MESSAGE_VIDEO = "video";

    public static final String MESSAGE_SHORTVIDEO = "shortvideo";

    public static final String MESSAGE_LINK = "link";

    public static final String MESSAGE_LOCATION = "location";

    public static final String MESSAGE_EVENT = "event";

    public static final String MESSAGE_SUBSCRIBE = "subscribe";

    public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";

    public static final String MESSAGE_CLICK = "CLICK";

    public static final String MESSAGE_VIEW = "VIEW";

    public static final String MESSAGE_SCAN = "SCAN";

    /**
     *  XML格式转为map对象
     * @param request
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
        Map<String, String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();
        InputStream is = request.getInputStream();
        Document doc = reader.read(is);
        Element root = doc.getRootElement();
        List<Element> list = root.elements();
        for(Element e : list) {
            map.put(e.getName(), e.getText());
        }
        is.close();
        return map;
    }

    public static String objectToXml(XmlParam xmlParam) {
        XStream xs = new XStream();
        xs.alias("xml", xmlParam.getClass());
        return xs.toXML(xmlParam);
    }

    /**
     *  关注主菜单
     * @return
     */
    public static String menuText() {
        StringBuffer sb = new StringBuffer();
        sb.append("欢迎关注史上最可爱的小贝贝~~~请选择：");
        sb.append("1、小贝贝真可爱\n\n");
        sb.append("2、小贝贝最可爱\n\n");
        sb.append("回复 ? 调出主菜单");
        return sb.toString();
    }

    public static String initText(String fromUserName, String toUserName, String content) {
        XmlParam message = new XmlParam();
        message.setFromUserName(toUserName);
        message.setToUserName(fromUserName);
        message.setMsgType(MESSAGE_TEXT);
        message.setCreateTime(new Date().getTime()+"");
        message.setContent(content);
        return objectToXml(message);
    }
}
