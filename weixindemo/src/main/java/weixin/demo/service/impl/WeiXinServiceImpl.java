package weixin.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import weixin.demo.pojo.AccessToken;
import weixin.demo.pojo.TextMessage;
import weixin.demo.service.WeixinService;
import weixin.demo.util.CheckUtil;
import weixin.demo.util.MessageUtil;
import weixin.demo.util.WeiXinUtil;

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
        out.close();
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
                TextMessage message = new TextMessage();
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
        } catch (Exception e) {
            out.print("出错啦出错啦");
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
            String msgType = map.get("MsgType");
            String content = map.get("Content");
            if(MessageUtil.MESSAGE_TEXT.equals(msgType)) {
                switch (content) {
                    case "1": str = MessageUtil.initText(fromUserName, toUserName,
                            "对啊！我也是这么觉得！小贝贝超可爱！");
                    break;
                    case "2": str = MessageUtil.initText(fromUserName, toUserName,
                            "是呢！小贝贝可爱到无人能敌！");
                    break;
                    case "3": str = MessageUtil.initNewsMessage(fromUserName, toUserName);
                    break;
                    default: str = MessageUtil.initText(fromUserName, toUserName,
                            "不知道你在说啥，反正知道你在夸小贝贝！");
                    break;
                }
            } else if (MessageUtil.MESSAGE_EVENT.equals(msgType)) {
                if(MessageUtil.MESSAGE_SUBSCRIBE.equals(map.get("Event"))) {
                    str = MessageUtil.initText(fromUserName, toUserName, MessageUtil.menuText());
                }
            } else {
                str = MessageUtil.initText(fromUserName, toUserName, "还是:\n"+MessageUtil.menuText());
            }
            System.out.println(str);
            out.print(str);
        } catch (Exception e) {
            out.print("出错啦出错啦");
            e.printStackTrace();
        } finally {
            out.print("");
            out.close();
        }
    }

    @Override
    public AccessToken getAccessToken() throws IOException {
        AccessToken token = new AccessToken();
        String url = WeiXinUtil.ACCESS_TOKEN_URL;
        JSONObject json = WeiXinUtil.doGetStr(url);
        if(json != null) {
            token.setToken(json.getString("access_token"));
            token.setExpiresIn(json.getInteger("expires_in"));
        }
        return token;
    }
}
