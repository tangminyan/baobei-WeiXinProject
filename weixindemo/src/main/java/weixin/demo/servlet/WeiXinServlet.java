package weixin.demo.servlet;

public class WeiXinServlet {
    /*public void doGet(String tooken, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();
        if(CheckUtil.checkSignature(tooken, signature, timestamp, nonce)) {
            out.print(echostr);
        }
    }*/

    /*public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String str;
        PrintWriter out = response.getWriter();
        out.print("");
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
            } else if(MessageUtil.MESSAGE_EVENT.equals(msgType)) {
                if(MessageUtil.MESSAGE_SUBSCRIBE.equals(map.get("Event"))) {
                    MessageUtil.initText(fromUserName, toUserName, MessageUtil.menuText());
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            out.print("");
            out.close();
        }
    }*/
}
