package weixin.demo.servlet;

import org.dom4j.DocumentException;
import weixin.demo.pojo.XmlParam;
import weixin.demo.util.CheckUtil;
import weixin.demo.util.Message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class WeiXinServlet {
    public void doGet(String tooken, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();
        if(CheckUtil.checkSignature(tooken, signature, timestamp, nonce)) {
            out.print(echostr);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String str = null;
        PrintWriter out = response.getWriter();
        out.print("");
        try{
            Map<String, String> map = Message.xmlToMap(request);
            String toUserName = map.get("ToUserName");
            String fromUserName = map.get("FromUserName");
            String createTime = map.get("CreateTime");
            String msgType = map.get("MsgType");
            String content = map.get("Content");
            String msgId = map.get("MsgId");
            if("text".equals(msgType)) {
                XmlParam message = new XmlParam();
                message.setFromUserName(toUserName);
                message.setToUserName(fromUserName);
                message.setMsgType(msgType);
                message.setCreateTime(createTime);
                message.setMsgId(msgId);
                message.setContent("你好，"+ fromUserName +", 我是"+toUserName+", 接收到您的消息为："+
                content);
                str = Message.objectToXml(message);
                out.print(str);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            out.print("");
            out.close();
        }
    }
}
