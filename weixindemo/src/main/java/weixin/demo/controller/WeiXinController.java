package weixin.demo.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import weixin.demo.servlet.WeiXinServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/weixin")
public class WeiXinController {
    public static final String tooken = "baobei";

    @RequestMapping("/servlet/demo")
    public void weixinConnect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        WeiXinServlet weiXinServlet = new WeiXinServlet();
        weiXinServlet.doGet(tooken, request, response);
    }
}
