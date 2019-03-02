package weixin.demo.servlet;

import weixin.demo.util.CheckUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
}
