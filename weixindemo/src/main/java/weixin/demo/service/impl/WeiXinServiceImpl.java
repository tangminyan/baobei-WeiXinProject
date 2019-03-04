package weixin.demo.service.impl;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Service;
import weixin.demo.pojo.XmlParam;
import weixin.demo.service.WeixinService;
import weixin.demo.util.CheckUtil;
import weixin.demo.util.MessageUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by tangminyan on 2019/3/4.
 */
@Service
public class WeiXinServiceImpl implements WeixinService {
    @Override
    public void getConnect(String tooken, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();
        if(CheckUtil.checkSignature(tooken, signature, timestamp, nonce)) {
            out.print(echostr);
        }
    }

    @Override
    public void replayContent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String str;
        PrintWriter out = response.getWriter();
        try{
            Map<String, String> map = MessageUtil.xmlToMap(request);
            String toUserName = map.get("ToUserName");
            String fromUserName = map.get("FromUserName");
            String createTime = map.get("CreateTime");
            String msgType = map.get("MsgType");
            String content = map.get("Content");
            String msgId = map.get("MsgId");
            if(MessageUtil.MESSAGE_TEXT.equals(msgType)) {
                XmlParam message = new XmlParam();
                message.setFromUserName(toUserName);
                message.setToUserName(fromUserName);
                message.setMsgType(msgType);
                message.setCreateTime(createTime);
                message.setMsgId(msgId);
                message.setContent("你好，"+ fromUserName +", 我是"+toUserName+", 接收到您的消息为："+
                        content);
                str = MessageUtil.objectToXml(message);
                out.print(str);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            out.print("");
            out.close();
        }
    }

    @Override
    public void autoAnswer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String str = null;
        PrintWriter out = response.getWriter();
        try{
            Map<String, String> map = MessageUtil.xmlToMap(request);
            String toUserName = map.get("ToUserName");
            String fromUserName = map.get("FromUserName");
            String createTime = map.get("CreateTime");
            String msgType = map.get("MsgType");
            String content = map.get("Content");
            String msgId = map.get("MsgId");
            if(MessageUtil.MESSAGE_TEXT.equals(msgType)) {
                switch (map.get("Content")) {
                    case "1": str = MessageUtil.initText(toUserName, fromUserName,
                            "对啊！我也是这么觉得！小贝贝超可爱！");
                    case "2": str = MessageUtil.initText(toUserName, fromUserName,
                            "是呢！小贝贝无人能敌！");
                    default: str = MessageUtil.initText(toUserName, fromUserName,
                            "不知道你在说啥，反正知道你在夸小贝贝！");
                }
            } else if (MessageUtil.MESSAGE_EVENT.equals(msgType)) {
                if(MessageUtil.MESSAGE_SUBSCRIBE.equals(map.get("Event"))) {
                    str = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
                }
            }
            out.print(str);
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            out.print("");
            out.close();
        }
    }
}
