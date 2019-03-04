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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Message {
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
}
